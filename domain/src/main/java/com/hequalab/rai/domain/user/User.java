package com.hequalab.rai.domain.user;

import com.hequalab.rai.dddd.DeletableAggregate;
import com.hequalab.rai.domain.user.events.RoleAddedToUser;
import com.hequalab.rai.domain.user.events.RoleRemovedFromUser;
import com.hequalab.rai.domain.user.events.UserCreated;
import com.hequalab.rai.domain.user.events.UserDeleted;
import com.hequalab.rai.domain.user.events.UserLogin;
import com.hequalab.rai.domain.user.events.UserPasswordUpdated;
import com.hequalab.rai.domain.user.events.UserUpdated;

public class User extends DeletableAggregate<User, UserId, UserDeleted> {

	private User(UserId id) {
		super(id, UserDeleted.class);
	}

	public User(UserId id, String userName, String firstName,
			String lastName, String email, String password, String divisione, String superiore) {
		this(id);
		applyChange(new UserCreated(id, userName,
				firstName, lastName, email, password, divisione, superiore));
	}

	public User updatePassword(String newPassword) {
		applyChange(new UserPasswordUpdated(getId(), newPassword));
		return this;
	}

	public User update(String userName, String firstName,
			String lastName, String email, String divisione, String superiore) {
		applyChange(new UserUpdated(getId(), userName, firstName, lastName, email, divisione, superiore));
		return this;
	}

	public User addRole(Role role) {
		applyChange(new RoleAddedToUser(getId(), role));
		return this;
	}

	public User removeRole(Role role) {
		applyChange(new RoleRemovedFromUser(getId(), role));
		return this;
	}

	public User login(String ip, String userAgent, String accessTokenGenerato) {
		applyChange(new UserLogin(getId(),ip, userAgent, accessTokenGenerato));
		return this;
	}

}