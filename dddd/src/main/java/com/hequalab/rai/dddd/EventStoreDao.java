package com.hequalab.rai.dddd;


public interface EventStoreDao<C extends Context> {

	EventStream<C> append(Identity id, long version, ContextProvider<C> contextProvider, Iterable<Event<?>> events);
	
	Iterable<EventStream<C>> get(Identity id, long version);

	Iterable<EventStream<C>> get(Identity id);

}