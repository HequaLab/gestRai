package com.hequalab.rai.api.write;

import com.hequalab.rai.dddd.Context;
import com.hequalab.rai.domain.user.UserId;

public class ApiContext implements Context {

	private static final long serialVersionUID = 1L;

	private final UserId userId;

	public ApiContext(UserId userId) {
		this.userId = userId;
	}
	
	public UserId userId() {
		return userId;
	}

}