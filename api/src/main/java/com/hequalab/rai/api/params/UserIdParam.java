package com.hequalab.rai.api.params;

import com.hequalab.rai.domain.user.UserId;

import io.dropwizard.jersey.params.AbstractParam;

public class UserIdParam extends AbstractParam<UserId> {

	public UserIdParam(String input) {
		super(input);
	}

	@Override
	public UserId parse(String input) throws Exception {
		return new UserId(input);
	}

}