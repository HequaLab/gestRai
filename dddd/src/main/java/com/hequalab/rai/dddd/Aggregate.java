package com.hequalab.rai.dddd;


public interface Aggregate<ID extends Identity> {

	ID getId();

	long getVersion();

	Iterable<Event<?>> getUncommittedChanges();

	boolean hasUncommittedChanges();

	void markChangesAsCommitted();

	<C extends Context>  void loadsFromHistory(Iterable<EventStream<C>> history);

}