package com.hequalab.rai.domain.user.events;

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.user.UserId;

public class UserPasswordUpdated extends DefaultEvent<UserId> {

	private static final long serialVersionUID = 1L;

	private String password;

	@SuppressWarnings("unused")
	private UserPasswordUpdated() {

	}

	public UserPasswordUpdated(UserId id, String password) {
		super(id);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

}