package com.hequalab.rai.api.resources;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.hibernate.SessionFactory;

import com.codahale.metrics.annotation.Timed;
import com.hequalab.rai.api.dtos.Page;
import com.hequalab.rai.api.dtos.user.UserCreate;
import com.hequalab.rai.api.dtos.user.UserUpdate;
import com.hequalab.rai.api.dtos.user.UserUpdatePassword;
import com.hequalab.rai.api.params.ExtJsParams;
import com.hequalab.rai.api.params.UserIdParam;
import com.hequalab.rai.api.read.views.user.UserView;
import com.hequalab.rai.dddd.AggregateSessionFactory;
import com.hequalab.rai.domain.user.Role;
import com.hequalab.rai.domain.user.User;
import com.hequalab.rai.domain.user.UserId;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersRes extends AbstractRes {

    public UsersRes(AggregateSessionFactory aggregateSessionFactory,
	    SessionFactory sessionFactory) {
	super(aggregateSessionFactory, sessionFactory);
    }

    @GET
    @UnitOfWork
    @Timed
    @CacheControl(noCache = true)
    public Page<UserView> list(@Auth UserView user,
	    @DefaultValue("1") @QueryParam("page") Integer page,
	    @DefaultValue("10") @QueryParam("limit") Integer size,
	    @DefaultValue("") @QueryParam("filter") String filter,
	    @DefaultValue("") @QueryParam("sort") String sort)
		    throws Exception {

	ExtJsParams filterParams = new ExtJsParams(
		"select wv from UserView wv ", "wv");
	filterParams.addFilters(filter);
	filterParams.addOrders(sort);
	@SuppressWarnings("unchecked")
	List<UserView> dnv = hibSess()
		.createQuery(filterParams.getSqlStatement()).list();

	return page(dnv, page, size);
	// return page(hibSess().createCriteria(UserView.class), page, size);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Timed
    @CacheControl(noCache = true)
    public UserView fetch(@Auth UserView user,
	    @PathParam("id") UserIdParam id) {
	return (UserView) hibSess()
		.createQuery("from UserView where userId = :id")
		.setParameter("id", id.get()).uniqueResult();
    }

    @POST
    @UnitOfWork
    @Timed
    public UserView create(@Auth UserView userAuth, @Valid UserCreate form,
	    @Context UriInfo uriInfo) throws IllegalAccessException {

	/*
	 * Check privilegi: gli utenti possono essere gestiti solo da utenti
	 * "Consorzio" [Admin]
	 */

	if (!userAuth.getRoles().contains(Role.Consorzio))
	    return null;

	UserId id = new UserId();
	User user = new User(id, form.getUserName(), form.getFirstName(),
		form.getLastName(), form.getEmail(), form.getPassword(),
		form.getDivisione(), form.getSuperiore());

	aggSess().save(user);
	aggSess().save(user.addRole(form.getRoles()));
	UserView uv = new UserView();
	uv.setUserId(id);
	uv.setEmail(form.getEmail());
	uv.setFirstName(form.getFirstName());
	uv.setLastName(form.getLastName());
	uv.setPassword(form.getPassword());
	uv.setUserName(form.getUserName());
	return uv;
    }

    @PUT
    @Path("{id}")
    @UnitOfWork
    @Timed
    public UserView update(@Auth UserView user, @PathParam("id") UserIdParam id,
	    @Valid UserUpdate rep) throws IllegalAccessException {

	UserView recOld = (UserView) hibSess()
		.createQuery("from UserView where userId = :id")
		.setParameter("id", id.get()).uniqueResult();

	String userName = rep.getUserName() != null ? rep.getUserName()
		: recOld.getUserName();
	String firstName = rep.getFirstName() != null ? rep.getFirstName()
		: recOld.getFirstName();
	String lastName = rep.getLastName() != null ? rep.getLastName()
		: recOld.getLastName();
	String divisione = rep.getDivisione() != null ? rep.getDivisione()
		: recOld.getDivisione();
	String email = rep.getEmail() != null ? rep.getEmail()
		: recOld.getEmail();
	String superiore = rep.getSuperiore() != null ? rep.getSuperiore()
		: recOld.getSuperiore();
	Set<Role> roleOld = recOld.getRoles();

	aggSess().save(aggSess().get(User.class, id.get()).update(userName,
		firstName, lastName, email, divisione, superiore));

	if ((rep.getRoles() != null && (user.getRoles().contains(Role.Consorzio)
		|| user.getRoles().contains(Role.Manager)))) {
	    for (Role role : roleOld) {
		aggSess().save(
			aggSess().get(User.class, id.get()).removeRole(role));
	    }
	    aggSess().save(aggSess().get(User.class, id.get())
		    .addRole(rep.getRoles()));
	}

	UserView uv = new UserView();
	uv.setUserId(id.get());
	uv.setEmail(email);
	uv.setFirstName(firstName);
	uv.setLastName(lastName);
	uv.setUserName(userName);
	uv.setDivisione(divisione);

	return uv;
    }

    @PUT
    @Path("{id}/password")
    @UnitOfWork
    @Timed
    public void updatePassword(@Auth UserView user,
	    @PathParam("id") UserIdParam id, @Valid UserUpdatePassword rep)
		    throws IllegalAccessException {
	aggSess().save(aggSess().get(User.class, id.get())
		.updatePassword(rep.getPassword()));
	// TODO: Non funiona perchÃ¨ passo anche i roles
    }

    @DELETE
    @Path("{id}")
    @UnitOfWork
    @Timed
    public void delete(@Auth UserView user, @PathParam("id") UserIdParam id)
	    throws IllegalAccessException {
	aggSess().save(aggSess().get(User.class, id.get()).delete());
    }

    @POST
    @Path("{id}/roles/{role}")
    @UnitOfWork
    @Timed
    public void addRole(@Auth UserView user, @PathParam("id") UserIdParam id,
	    @PathParam("role") Role role) throws IllegalAccessException {
	aggSess().save(aggSess().get(User.class, id.get()).addRole(role));
    }

    @DELETE
    @Path("{id}/roles/{role}")
    @UnitOfWork
    @Timed
    public void deleteRole(@Auth UserView user, @PathParam("id") UserIdParam id,
	    @PathParam("role") Role role) throws IllegalAccessException {
	aggSess().save(aggSess().get(User.class, id.get()).removeRole(role));
    }

}