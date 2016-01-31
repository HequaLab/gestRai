package com.hequalab.rai.dddd;

public abstract class DefaultEvent<ID extends Identity> implements Event<ID> {

	private static final long serialVersionUID = 1L;

	private ID id;

	public DefaultEvent() {
	
	}
	
	public DefaultEvent(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}
	
}