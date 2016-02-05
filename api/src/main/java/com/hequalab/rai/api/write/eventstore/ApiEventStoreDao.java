package com.hequalab.rai.api.write.eventstore;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.collect.ImmutableList;
import com.hequalab.rai.api.write.ApiContext;
import com.hequalab.rai.dddd.ContextProvider;
import com.hequalab.rai.dddd.Event;
import com.hequalab.rai.dddd.EventStoreDao;
import com.hequalab.rai.dddd.EventStream;
import com.hequalab.rai.dddd.Identity;
import com.hequalab.rai.dddd.UUIDIdentity;
import com.hequalab.rai.domain.user.UserId;

public class ApiEventStoreDao implements EventStoreDao<ApiContext> {

    private final SessionFactory sessionFactory;

    private final ObjectMapper mapper;

    public ApiEventStoreDao(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	this.mapper = new ObjectMapper();
	SimpleModule module = new SimpleModule();
	module.addSerializer(UUIDIdentity.class,
		new JacksonIdentitySerializer());
	module.setMixInAnnotation(Event.class, JacksonEventMixIn.class);
	mapper.registerModule(module);
	mapper.registerModule(new JodaModule());
	mapper.configure(
		com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
		false);
    }

    @Override
    public synchronized EventStream<ApiContext> append(Identity id,
	    long version, ContextProvider<ApiContext> context,
	    Iterable<Event<?>> events) {
	String serializedEvents;
	try {
	    serializedEvents = mapper
		    .writerWithType(new TypeReference<List<Event<?>>>() {
		    }).writeValueAsString(events);
	} catch (JsonProcessingException e) {
	    throw new RuntimeException(e);
	}
	LocalDateTime createdOn = LocalDateTime.now();
	long createdOnInMillis = createdOn.toDateTime(DateTimeZone.UTC)
		.getMillis();
	String createdOnToString = createdOn
		.toString(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss.SSS"));
	session().save(new ApiEventStoreEntry(id.toString(), createdOnToString,
		createdOnInMillis, version, context.getCurrent().userId(),
		serializedEvents));
	return new EventStream<ApiContext>(id, createdOn, version,
		context.getCurrent(), events);
    }

    @Override
    public Iterable<EventStream<ApiContext>> get(Identity id, long version) {
	@SuppressWarnings("unchecked")
	List<ApiEventStoreEntry> entries = criteria(id.toString())
		.add(Restrictions.le("version", version)).list();
	return streams(id, entries);
    }

    @Override
    public Iterable<EventStream<ApiContext>> get(Identity id) {
	@SuppressWarnings("unchecked")
	List<ApiEventStoreEntry> entries = criteria(id.toString()).list();
	return streams(id, entries);
    }

    @SuppressWarnings("rawtypes")
    private List<EventStream<ApiContext>> streams(Identity id,
	    List<ApiEventStoreEntry> entries) {
	ImmutableList.Builder<EventStream<ApiContext>> streams = ImmutableList
		.builder();
	for (ApiEventStoreEntry entry : entries) {
	    List<Event<?>> events;
	    try {
		events = mapper.readValue(entry.getEvents(),
			new TypeReference<List<Event>>() {
			});
	    } catch (Exception e) {
		throw new RuntimeException(e);
	    }
	    LocalDateTime createdOn = new LocalDateTime(
		    entry.getCreatedOnInMillis(), DateTimeZone.UTC);
	    ApiContext context = new ApiContext(entry.getCreatedBy());
	    EventStream<ApiContext> stream = new EventStream<ApiContext>(id,
		    createdOn, entry.getVersion(), context, events);
	    streams.add(stream);
	}
	return streams.build();
    }

    private Criteria criteria(String id) {
	return session().createCriteria(ApiEventStoreEntry.class)
		.add(Restrictions.eq("aggregateId", id))
		.addOrder(Order.asc("version"));
    }

    private Session session() {
	return sessionFactory.getCurrentSession();
    }

    @Override
    public synchronized EventStream<ApiContext> append(Identity id,
	    long version, ContextProvider<ApiContext> context,
	    Iterable<Event<?>> events, UUID userId) {
	String serializedEvents;
	try {
	    serializedEvents = mapper
		    .writerWithType(new TypeReference<List<Event<?>>>() {
		    }).writeValueAsString(events);
	} catch (JsonProcessingException e) {
	    throw new RuntimeException(e);
	}
	LocalDateTime createdOn = LocalDateTime.now();
	long createdOnInMillis = createdOn.toDateTime(DateTimeZone.UTC)
		.getMillis();
	String createdOnToString = createdOn
		.toString(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss.SSS"));
	session().save(new ApiEventStoreEntry(id.toString(), createdOnToString,
		createdOnInMillis, version, new UserId(userId.toString()),
		serializedEvents));
	return new EventStream<ApiContext>(id, createdOn, version,
		context.getCurrent(), events);
    }

}