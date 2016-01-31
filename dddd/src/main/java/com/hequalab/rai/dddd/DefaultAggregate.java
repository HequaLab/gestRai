package com.hequalab.rai.dddd;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public abstract class DefaultAggregate<ID extends Identity> implements Aggregate<ID> {

	private final ID id;

	private long version;
	
	private final List<Event<?>> changes = Lists.newArrayList();
	
	private static final Map<Class<? extends DefaultAggregate<?>>,
		Map<Class<? extends Event<?>>, Method>>
			handlers = Maps.newHashMap();

	public DefaultAggregate(ID id) {
		this.id = id;
	}

	@Override
	public ID getId() {
		return id;
	}

	@Override
	public long getVersion() {
		return version;
	}

	@Override
	public Iterable<Event<?>> getUncommittedChanges() {
		return ImmutableList.copyOf(changes);
	}

	@Override
	public boolean hasUncommittedChanges() {
		return !changes.isEmpty();
	}

	@Override
	public void markChangesAsCommitted() {
		version++;
		changes.clear();
	}

	@Override
	public <C extends Context> void loadsFromHistory(Iterable<EventStream<C>> history) {
		this.changes.clear();
		for (EventStream<C> stream : history) {
			for (Event<?> e : stream.getEvents()) {
				applyChange(e, false);
			}
			this.version = stream.getVersion();
		}
	}

	protected void applyChange(Event<?> event) {
		applyChange((Event<?>) event, true);
	}

	private void applyChange(Event<?> event, boolean isNew) {
		dispatchInternally(event);
		if (isNew) {
			changes.add(event);
		}
	}
	
	private void dispatchInternally(Event<?> event) {
		if (handlers.get(this.getClass()) == null) {
			initHandlers();
		}
		dispatchInternally(event, event.getClass());
	}

	private void dispatchInternally(Event<?> event, Class<?> type) {
		Class<?> handlerType = null;
		if (handlers.get(this.getClass()).containsKey(type)) {
			handlerType = type;
		} else if (type.getSuperclass() != null
                        && type.getSuperclass().getInterfaces().length > 0
                        && handlers.get(this.getClass()).containsKey(type.getSuperclass().getInterfaces()[0])) {
                        dispatchInternally(event, type.getSuperclass().getInterfaces()[0]);
		}// Add here other strategies
		if (handlerType != null) {
			try {
				handlers.get(this.getClass()).get(type).invoke(this, event);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}									
		}
	}

	@SuppressWarnings("unchecked")
	private void initHandlers() {
		handlers.put((Class<? extends DefaultAggregate<?>>) this.getClass(), Maps.<Class<? extends Event<?>>, Method>newHashMap());
		for (Method method : Reflection.annotatedMethods(this, EventListener.class)) {
			Class<? extends Event<?>> eventClass = (Class<? extends Event<?>>) method.getParameterTypes()[0];
			handlers.get(this.getClass()).put(eventClass, method);
		}		
	}

}