package com.hequalab.rai.dddd;

public class DefaultEventStore<C extends Context> implements EventStore<C> {

	private final EventStoreDao<C> dao;
		
	private final EventPublisher<C> pub;

	public DefaultEventStore(EventStoreDao<C> dao, EventPublisher<C> pub) {
		this.dao = dao;
		this.pub = pub;
	}

	@Override
	public EventStream<C> append(Identity id, long version, ContextProvider<C> contextProvider, Iterable<Event<?>> events) {
                return pub.publish(dao.append(id, version, contextProvider, events));
	}

	@Override
	public Iterable<EventStream<C>> get(Identity id, long version) {
		return dao.get(id, version);
	}

	@Override
	public Iterable<EventStream<C>> get(Identity id) {
		return dao.get(id);
	}

}