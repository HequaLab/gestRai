package com.hequalab.rai.dddd;

import java.util.UUID;

public interface EventStore<C extends Context> {

	EventStream<C> append(Identity id, long version, ContextProvider<C> contextProvider, Iterable<Event<?>> events);
	EventStream<C> append(Identity id, long version, ContextProvider<C> contextProvider, Iterable<Event<?>> events, UUID userId);
	
	Iterable<EventStream<C>> get(Identity id, long version);
	Iterable<EventStream<C>> get(Identity id);
}