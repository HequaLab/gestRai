package com.hequalab.rai.api.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.SessionFactory;
import org.joda.time.DateTime;

import com.hequalab.rai.api.auth.AccessToken;
import com.hequalab.rai.api.auth.AccessTokenDAO;
import com.hequalab.rai.api.read.views.user.UserView;
import com.hequalab.rai.dddd.AggregateSessionFactory;
import com.codahale.metrics.annotation.Timed;

@Path("/oauth2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OAuth2Resource extends AbstractRes {
	private AccessTokenDAO accessTokenDAO;

	public OAuth2Resource(AggregateSessionFactory aggregateSessionFactory,
			SessionFactory sessionFactory, String allowedGrantTypes,
			AccessTokenDAO accessTokenDAO) {
		super(aggregateSessionFactory, sessionFactory);
		this.accessTokenDAO = accessTokenDAO;
	}

	@GET
	@Path("/token")
	@UnitOfWork
	@Timed
	public String postForToken(@QueryParam("id") String username,
			@QueryParam("pwd") String password, @Context UriInfo uriInfo) {

		if (username == null || password == null)
			throw new WebApplicationException(Response.status(
					Response.Status.NOT_ACCEPTABLE).build());

		boolean trovatoUtente = false;

		// Try to find a user with the supplied credentials.
		@SuppressWarnings("unchecked")
		List<UserView> users = hibSess().createQuery("from UserView").list();
		UserView user = null;
		for (UserView u : users) {
			if (u.getUserName() == null || u.getPassword() == null)
				continue;
			if (u.getUserName().toLowerCase().equals(username.toLowerCase())
					&& u.getPassword().equals(password)) {
				trovatoUtente = true;
				user = u;
				break;
			}
		}

		if (!trovatoUtente || user == null) {
			throw new WebApplicationException(Response.status(
					Response.Status.UNAUTHORIZED).build());
		}

		// User was found, generate a token and return it.
		AccessToken accessToken = accessTokenDAO.generateNewAccessToken(user,
				new DateTime());
		return accessToken.getAccessTokenId().toString();
	}

	@GET
	@Path("/account")
	@UnitOfWork
	@Timed
	public UserView postForToken(@Auth UserView user) {
		user.setPassword("****");
		// user.setUserName("****");
		// user.setUserId(null);
		return user;
	}

}