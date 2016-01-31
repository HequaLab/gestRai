package com.hequalab.rai.dddd;


public interface AggregateSession {

	<T extends Aggregate<ID>, ID extends Identity> T get(Class<T> type, ID id, long version);

	<T extends Aggregate<ID>, ID extends Identity> T get(Class<T> type, ID id);

	<T extends Aggregate<?>> void save(T aggregate);
	
}