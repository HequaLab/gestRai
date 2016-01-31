package com.hequalab.rai.api.resources;

import java.util.List;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.caching.CacheControl;

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
import com.hequalab.rai.api.dtos.PageEvent;
import com.hequalab.rai.dddd.AggregateSessionFactory;
import com.hequalab.rai.api.write.eventstore.ApiEventStoreEntry;

@SuppressWarnings("unused")
@Path("/event_store")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventStoreRes extends AbstractRes {

	public EventStoreRes(AggregateSessionFactory aggregateSessionFactory,
			SessionFactory sessionFactory) {
		super(aggregateSessionFactory, sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@GET
	@UnitOfWork
	@Timed
	@CacheControl(noCache = true)
	public PageEvent<ApiEventStoreEntry> list(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("10") @QueryParam("limit") Integer size,
			@DefaultValue("") @QueryParam("filter") String filter,
			@DefaultValue("") @QueryParam("sort") String sort) throws Exception {

		return pageEvent(sessionFactory.getCurrentSession()
				.createQuery("from ApiEventStoreEntry").list(),page,size);
	}
}