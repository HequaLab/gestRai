package com.hequalab.rai.api.read.views.user;

import org.hibernate.SessionFactory;

import com.hequalab.rai.api.read.views.AbstractViewWriter;
import com.hequalab.rai.api.write.ApiContext;
import com.hequalab.rai.dddd.EventListener;
import com.hequalab.rai.dddd.EventStream;
import com.hequalab.rai.domain.user.events.RoleAddedToUser;
import com.hequalab.rai.domain.user.events.RoleRemovedFromUser;
import com.hequalab.rai.domain.user.events.UserCreated;
import com.hequalab.rai.domain.user.events.UserDeleted;
import com.hequalab.rai.domain.user.events.UserPasswordUpdated;
import com.hequalab.rai.domain.user.events.UserUpdated;

public class UserViewWriter extends AbstractViewWriter<UserView>  {

	public UserViewWriter(SessionFactory sessionFactory) {
		super(sessionFactory, UserView.class, "userId");
	}

	@EventListener
	public void apply(UserCreated event, EventStream<ApiContext> stream) {
		UserView u = new UserView();
		u.setUserId(event.getId());
		u.setUserName(event.getUserName());
		u.setFirstName(event.getFirstName());
		u.setLastName(event.getLastName());
		u.setEmail(event.getEmail());
		u.setPassword(event.getPassword());
		u.setDivisione(event.getDivisione());
		u.setSuperiore(event.getSuperiore());
		session().save(u);
	}

	@EventListener
	public void apply(UserUpdated event, EventStream<ApiContext> stream) {
		UserView u = find(event.getId());
		u.setUserName(event.getUserName());
		u.setFirstName(event.getFirstName());
		u.setLastName(event.getLastName());
		u.setEmail(event.getEmail());
		u.setDivisione(event.getDivisione());
		u.setSuperiore(event.getSuperiore());
		session().update(u);
	}

	@EventListener
	public void apply(UserPasswordUpdated event, EventStream<ApiContext> stream) {
		UserView u = find(event.getId());
		u.setPassword(event.getPassword());
		session().update(u);
	}

	@EventListener
	public void apply(UserDeleted event, EventStream<ApiContext> stream) {
		session().delete(find(event.getId()));
	}

	@EventListener
	public void apply(RoleAddedToUser event, EventStream<ApiContext> stream) {
		UserView u = find(event.getId());
		u.getRoles().add(event.getRole());
		session().update(u);
	}

	@EventListener
	public void apply(RoleRemovedFromUser event, EventStream<ApiContext> stream) {
		UserView u = find(event.getId());
		u.getRoles().remove(event.getRole());
		session().update(u);
	}

}