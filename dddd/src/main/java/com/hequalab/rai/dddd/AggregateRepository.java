package com.hequalab.rai.dddd;


public interface AggregateRepository<T extends Aggregate<ID>, ID extends Identity> {

	T get(ID id, long version);
	
	T get(ID id);

	void save(T aggregate);

}