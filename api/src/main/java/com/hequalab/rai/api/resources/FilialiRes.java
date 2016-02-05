package com.hequalab.rai.api.resources;

/*
 * Class generated by AppWizard
 */

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;
import java.io.IOException;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hequalab.rai.api.dtos.Page;
import com.hequalab.rai.api.params.ExtJsParams;
import com.hequalab.rai.api.params.FilialiIdParam;
import com.hequalab.rai.api.read.views.filiali.FilialiView;
import com.hequalab.rai.dddd.AggregateSessionFactory;

import com.hequalab.rai.api.read.views.user.UserView;
import io.dropwizard.auth.Auth;

import com.hequalab.rai.api.dtos.filiali.FilialiCreate;
import com.hequalab.rai.api.dtos.filiali.FilialiUpdate;

import com.hequalab.rai.domain.filiali.Filiali;
import com.hequalab.rai.domain.filiali.FilialiId;

@SuppressWarnings("unused")
@Path("/filiali")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FilialiRes extends AbstractRes {

	public FilialiRes(AggregateSessionFactory aggregateSessionFactory,
			SessionFactory sessionFactory) {
		super(aggregateSessionFactory, sessionFactory);
	}

	@GET
	@UnitOfWork
	@Timed
	@CacheControl(noCache = true)
	public Page<FilialiView> list(@Auth UserView user,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("10") @QueryParam("limit") Integer size,
			@DefaultValue("") @QueryParam("filter") String filter,
			@DefaultValue("") @QueryParam("sort") String sort)
					throws Exception {

		ExtJsParams filterParams = new ExtJsParams(
				"select wv from FilialiView wv ", "wv");
		filterParams.addFilters(filter);
		filterParams.addOrders(sort);
		@SuppressWarnings("unchecked")
		List<FilialiView> dnv = hibSess()
				.createQuery(filterParams.getSqlStatement()).list();

		return page(dnv, page, size);
	}

	@GET
	@Path("/{id}")
	@UnitOfWork
	@Timed
	@CacheControl(noCache = true)
	public FilialiView fetch(@PathParam("id") FilialiIdParam id) {
		return (FilialiView) hibSess()
				.createQuery("from FilialiView where filialiId = :id")
				.setParameter("id", id.get()).uniqueResult();
	}

	@DELETE
	@Path("{id}")
	@UnitOfWork
	@Timed
	public void delete(@Auth UserView user, @PathParam("id") FilialiIdParam id)
			throws IllegalAccessException, JsonParseException,
			JsonMappingException, IOException {
		aggSess().save(user.getUserId().getUuid(), aggSess().get(Filiali.class, id.get()).delete());
	}

	@POST
	@UnitOfWork
	@Timed
	public FilialiView create(@Auth UserView user, @Valid FilialiCreate form,
			@Context UriInfo uriInfo) throws IllegalAccessException {

		FilialiId id = new FilialiId();
		Filiali rec = new Filiali(id, form.getNome());
		aggSess().save(user.getUserId().getUuid(), rec);

		FilialiView uv = new FilialiView();
		uv.setFilialiId(id);
		uv.setNome(form.getNome());
		return uv;
	}

	@PUT
	@Path("{id}")
	@UnitOfWork
	@Timed
	public FilialiView update(@Auth UserView user,
			@PathParam("id") FilialiIdParam id, @Valid FilialiUpdate rep)
					throws IllegalAccessException {

		FilialiView recOld = (FilialiView) hibSess()
				.createQuery("from FilialiView where filialiId = :id")
				.setParameter("id", id.get()).uniqueResult();

		String nome = rep.getNome() != null ? rep.getNome() : recOld.getNome();

		aggSess().save(user.getUserId().getUuid(), aggSess().get(Filiali.class, id.get()).update(nome));

		FilialiView uv = new FilialiView();
		uv.setFilialiId(id.get());
		uv.setNome(nome);

		return uv;
	}

}