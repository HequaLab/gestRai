package com.hequalab.rai.dddd;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class DefaultEventBus<C extends Context> implements EventDispatcher, EventPublisher<C> {

	private final Logger logger = LoggerFactory.getLogger(DefaultEventBus.class);

	private final Multimap<Class<? extends Event<?>>, MethodEventHandler<C>> handlers;
	
	public DefaultEventBus() {
		Multimap<Class<? extends Event<?>>, MethodEventHandler<C>> unsynch = HashMultimap.create();
		this.handlers = Multimaps.synchronizedMultimap(unsynch);
	}

	@Override
	public void subscribe(Object handler) {
		for (MethodEventHandler<C> eh : getHandlers(handler)) {
			for (Class<? extends Event<?>> type : eh.getEventTypes()) {
				handlers.put(type, eh);				
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Iterable<MethodEventHandler<C>> getHandlers(Object bean) {
		List<MethodEventHandler<C>> mHandlers = Lists.newArrayList();
		for (Method method : Reflection.annotatedMethods(bean, EventListener.class)) {
			Class<? extends Event<?>> eventClass = (Class<? extends Event<?>>) method.getParameterTypes()[0];
			Iterable<Class<? extends Event<?>>> eventTypes = Lists.<Class<? extends Event<?>>>newArrayList(eventClass);
			MethodEventHandler<C> eventHandler = new MethodEventHandler<C>(bean, method, eventTypes);			
			mHandlers.add(eventHandler);
		}
		return mHandlers;
	}

	@Override
	@SuppressWarnings("unchecked")
	public EventStream<C> publish(EventStream<C> stream) {
		for (Event<?> event : stream.getEvents()) {
			logger.debug("Dispatching event " + event.getClass().getSimpleName());
			for (Class<? extends Event<?>> type : allTypes((Class<? extends Event<?>>) event.getClass())) {
				Collection<MethodEventHandler<C>> toBeInvoked = handlers.get(type);
				for (MethodEventHandler<C> handler : toBeInvoked) {
					logger.debug("Handling event " + event.getClass().getSimpleName() + " with [" + handler.getName() + "]");
					handler.handle(event, stream);			
				}
			}
		}
                return stream;
	}
	
	@SuppressWarnings("unchecked")
	private Iterable<Class<? extends Event<?>>> allTypes(Class<? extends Event<?>> clazz) {
		List<Class<? extends Event<?>>> types = Lists.newArrayList();
		Class<? extends Event<?>> c = clazz;
		while(c != null) {
			types.add(c);
			c = clazz.getSuperclass() == c ?  null : (Class<? extends Event<?>>) clazz.getSuperclass();
		}
		return types;
	}

	private static class MethodEventHandler<C extends Context> {

		private final String name;

		private final Object target;
		
		private final Method method;
		
		private final ImmutableList<Class<? extends Event<?>>> eventTypes;
				
		public MethodEventHandler(Object target, Method method,
				Iterable<Class<? extends Event<?>>> eventTypes) {
			this.name = target.getClass().getSimpleName() + "." + method.getName() + 
				"(" + method.getParameterTypes().getClass().getSimpleName() + ")";
			this.target = target;
			this.method = method;
			this.eventTypes = ImmutableList.copyOf(eventTypes);
		}

		public void handle(Event<?> event, EventStream<C> stream) {
			try {
				List<Object> args = Lists.newArrayList();
				for (Class<?> param : method.getParameterTypes()) {
					if (param.isAssignableFrom(event.getClass())) {
						args.add(event);						
					} else if (param.isAssignableFrom(stream.getClass())) {
						args.add(stream);
					} else {
						throw new IllegalStateException("Can't pass the argument of type " + param.getSimpleName());
					}
				}
				method.invoke(target, args.toArray());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public Iterable<Class<? extends Event<?>>> getEventTypes() {
			return eventTypes;
		}

		public String getName() {
			return name;
		}
	}
}