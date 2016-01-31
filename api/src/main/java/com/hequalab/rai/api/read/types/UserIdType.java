package com.hequalab.rai.api.read.types;

import com.hequalab.rai.domain.user.UserId;

public class UserIdType extends UUIDIdentityType<UserId> {

	public UserIdType() {
		super(UserId.class);
	}

}