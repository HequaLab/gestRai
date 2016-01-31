package com.hequalab.rai.api.write.eventstore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.hequalab.rai.domain.user.UserId;

@Entity
@Table(name = "event_store",
	indexes = {@Index(columnList = "aggregateId")},
	uniqueConstraints = {@UniqueConstraint(columnNames = {"aggregateId","version"})})
public class ApiEventStoreEntry {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String aggregateId;
        
	protected String createdOn;

	protected Long createdOnInMillis;
	
	private long version;
	
	@Type(type="UserIdType")
	private UserId createdBy;
	
	@Lob
	private String events;

	@SuppressWarnings("unused")
	private ApiEventStoreEntry() {

	}

	public ApiEventStoreEntry(String aggregateId, String createdOn,
                Long createdOnInMillis, long version, 
                UserId createdBy, String events) {
		this.aggregateId = aggregateId;
		this.version = version;
		this.createdOn = createdOn;
                this.createdOnInMillis = createdOnInMillis;
		this.createdBy = createdBy;
		this.events = events;
	}

	public Long getId() {
		return id;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public long getVersion() {
		return version;
	}

	public String getCreatedOn() {
		return createdOn;
	}
        
    public Long getCreatedOnInMillis() {
		return createdOnInMillis;
	}

	public UserId getCreatedBy() {
		return createdBy;
	}

	public String getEvents() {
		return events;
	}

}