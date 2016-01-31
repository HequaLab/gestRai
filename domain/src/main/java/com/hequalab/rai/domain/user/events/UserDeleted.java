package com.hequalab.rai.domain.user.events;

import com.hequalab.rai.dddd.DefaultAggregateDeleted;
import com.hequalab.rai.domain.user.UserId;

public class UserDeleted extends DefaultAggregateDeleted<UserId> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private UserDeleted() {
	}
	
	public UserDeleted(UserId id) {
		super(id);
	}				

}