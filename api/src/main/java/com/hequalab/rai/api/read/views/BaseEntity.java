package com.hequalab.rai.api.read.views;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	protected Long id;
        
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
        
	@Transient
	public boolean isNew() {
		return id == null;
	}

	@Override
	public boolean equals(Object model) {		
		if(super.equals(model)) return true;
		return model != null && getId() != null && getId().equals(((BaseEntity)model).getId());
	}
		
}