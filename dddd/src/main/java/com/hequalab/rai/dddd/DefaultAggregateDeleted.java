package com.hequalab.rai.dddd;

public abstract class DefaultAggregateDeleted<ID extends Identity> implements AggregateDeleted<ID> {

	private static final long serialVersionUID = 1L;

	private ID id;

	public DefaultAggregateDeleted() {
	
	}
	
	public DefaultAggregateDeleted(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}
	
}