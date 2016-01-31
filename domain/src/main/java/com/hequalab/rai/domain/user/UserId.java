package com.hequalab.rai.domain.user;

import com.hequalab.rai.dddd.UUIDIdentity;

public class UserId extends UUIDIdentity {
	
	private static final long serialVersionUID = 1L;
	
	public UserId() {
		super();
	}

	public UserId(String uuid) {
		super(uuid);
	}

}