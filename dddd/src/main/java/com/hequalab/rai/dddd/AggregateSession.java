package com.hequalab.rai.dddd;

import java.util.UUID;

public interface AggregateSession {

	<T extends Aggregate<ID>, ID extends Identity> T get(Class<T> type, ID id, long version);

	<T extends Aggregate<ID>, ID extends Identity> T get(Class<T> type, ID id);

	<T extends Aggregate<?>> void save(T aggregate);
	<T extends Aggregate<?>> void save(UUID userId,T aggregate);
	
}