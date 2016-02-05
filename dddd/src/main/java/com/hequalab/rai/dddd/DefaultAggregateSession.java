package com.hequalab.rai.dddd;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

public class DefaultAggregateSession<C extends Context> implements AggregateSession {

	private final EventStore<C> eventStore;
	
	private final ContextProvider<C> contextProvider;

	private final Map<Identity, Aggregate<?>> cache;

	public DefaultAggregateSession(EventStore<C> eventStore, ContextProvider<C> contextProvider) {
		this.eventStore = eventStore;
		this.contextProvider = contextProvider;
		this.cache = Maps.newLinkedHashMap();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Aggregate<ID>, ID extends Identity> T get(Class<T> type, ID id, long version) {
		if (cache.containsKey(id)) {
			if (cache.get(id).getVersion() == version) {
				return (T) cache.get(id);
			} else {
				throw new IllegalStateException(
					"Can't load different aggregate versions in same session");
			}
		} else {
			return load(type, id, version);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Aggregate<ID>, ID extends Identity> T get(Class<T> type, ID id) {
		if (cache.containsKey(id)) {
			return (T) cache.get(id);
		} else {
			return load(type, id, null);
		}
		// TODO: Cache
	}

	private <T extends Aggregate<ID>, ID extends Identity> T load(Class<T> type, ID id, Long version) {
		T aggregate;
		try {
			Constructor<T> constructor = type.getDeclaredConstructor(id.getClass());
			constructor.setAccessible(true);			
			aggregate = constructor.newInstance(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Iterable<EventStream<C>> streams = version != null ?
			eventStore.get(id, version) : eventStore.get(id);
		aggregate.loadsFromHistory(streams);
		cache.put(aggregate.getId(), aggregate);
		return (T)aggregate;
	}
	
	@Override
	public <T extends Aggregate<?>>  void save(T aggregate) {
		cache.put(aggregate.getId(), aggregate);
		if (aggregate.hasUncommittedChanges()) {
			Iterable<Event<?>> events = aggregate.getUncommittedChanges();
			aggregate.markChangesAsCommitted();
			eventStore.append(
				aggregate.getId(), aggregate.getVersion(),
				contextProvider, events);
		}
	}
	
	
	public <T extends Aggregate<?>>  void save( UUID userId, T aggregate) {
	  
	    cache.put(aggregate.getId(), aggregate);
		if (aggregate.hasUncommittedChanges()) {
			Iterable<Event<?>> events = aggregate.getUncommittedChanges();
			aggregate.markChangesAsCommitted();
			eventStore.append(
				aggregate.getId(), aggregate.getVersion(),
				contextProvider, events, userId);
		}
	}

}