package com.hequalab.rai.dddd;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;
import com.google.common.collect.ImmutableList;
import org.joda.time.DateTimeZone;

public class EventStream<C extends Context> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Identity id;
        
	private final LocalDateTime createdOn;

	private final long version;
	
	private final C context;

	private final List<Event<?>> events;
	
	public EventStream(Identity id, LocalDateTime createdOn,
			long version, C context, Iterable<Event<?>> events) {
		this.id = id;
		this.createdOn = createdOn;
		this.version = version;
		this.context = context;
		this.events = ImmutableList.copyOf(events);
	}

	public Identity getId() {
		return id;
	}
        
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public Long getCreatedOnInMillis() {
		return createdOn != null ? createdOn.toDateTime(DateTimeZone.UTC).getMillis() : null;
	}

	public long getVersion() {
		return version;
	}

	public List<Event<?>> getEvents() {
		return events;
	}

	public Context getContext() {
		return context;
	}

}