package com.hequalab.rai.dddd;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;

import org.joda.time.LocalDateTime;

public class MemoryEventStoreDao<C extends Context> implements EventStoreDao<C> {

	private final ListMultimap<Identity, EventStream<C>> queue;

	public MemoryEventStoreDao() {
		queue = ArrayListMultimap.create();
	}

	@Override
	public synchronized EventStream<C> append(Identity id, long version, 
			ContextProvider<C> contextProvider, Iterable<Event<?>> events) {
		int actual = queue.get(id).size() + 1;
		if (actual != version) {
			throw new IllegalStateException("Aggregate " + id + " excpected version is " + version + " but actual is " + actual);
		}
		EventStream<C> stream = new EventStream<C>(id, LocalDateTime.now(), version, contextProvider.getCurrent(), events);
		queue.put(stream.getId(), stream);
                return stream;
	}

	@Override
	public Iterable<EventStream<C>> get(Identity id, long version) {
		List<EventStream<C>> copy = queue.get(id).subList(0, (int) version);
		Collections.reverse(copy);
		return ImmutableList.copyOf(copy);
	}

	@Override
	public Iterable<EventStream<C>> get(Identity id) {
		List<EventStream<C>> copy = Lists.newArrayList(queue.get(id));
		Collections.reverse(copy);
		return ImmutableList.copyOf(copy);
	}

	@Override
	public EventStream<C> append(Identity id, long version,
		ContextProvider<C> contextProvider, Iterable<Event<?>> events,
		UUID userId) {
		int actual = queue.get(id).size() + 1;
		if (actual != version) {
			throw new IllegalStateException("Aggregate " + id + " excpected version is " + version + " but actual is " + actual);
		}
		EventStream<C> stream = new EventStream<C>(id, LocalDateTime.now(), version, contextProvider.getCurrent(), events);
		queue.put(stream.getId(), stream);
                return stream;
	}

}