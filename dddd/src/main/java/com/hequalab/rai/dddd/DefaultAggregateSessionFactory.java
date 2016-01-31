package com.hequalab.rai.dddd;


public class DefaultAggregateSessionFactory<C extends Context> implements AggregateSessionFactory {

	private static ThreadLocal<AggregateSession> current = new ThreadLocal<AggregateSession>();
	
	private final EventStore<C> eventStore;

	private final ContextProvider<C> contextProvider;

	public DefaultAggregateSessionFactory(EventStore<C> eventStore, ContextProvider<C> contextProvider) {
		this.eventStore = eventStore;
		this.contextProvider = contextProvider;
	}
	
	@Override
	public AggregateSession getCurrent() {
		if (current.get() != null) {
			return current.get();
		} else {
			throw new IllegalStateException("Aggregate session is not open");
		}
	}

	@Override
	public void createSession() {
		if (current.get() == null) {
			current.set(new DefaultAggregateSession<C>(eventStore, contextProvider));
		} else {
			throw new IllegalStateException("Aggregate session is already open");
		}
		
	}

	@Override
	public void closeSession() {
		current.set(null);
	}

}