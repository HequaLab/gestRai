package com.hequalab.rai.domain.user.events;

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.user.Role;
import com.hequalab.rai.domain.user.UserId;

public class RoleRemovedFromUser extends DefaultEvent<UserId> {

	private static final long serialVersionUID = 1L;

	private Role role;

	@SuppressWarnings("unused")
	private RoleRemovedFromUser() {

	}

	public RoleRemovedFromUser(UserId id, Role role) {
		super(id);
		this.role = role;
	}

	public Role getRole() {
		return role;
	}
		
}