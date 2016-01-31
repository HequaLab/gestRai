package com.hequalab.rai.domain.filiali;

/*
 * Class generated by AppWizard
 */

import com.hequalab.rai.domain.filiali.events.FilialiCreated;
import com.hequalab.rai.domain.filiali.events.FilialiDeleted;
import com.hequalab.rai.domain.filiali.events.FilialiUpdated;
import com.hequalab.rai.dddd.DeletableAggregate;


public class Filiali extends DeletableAggregate<Filiali,FilialiId,FilialiDeleted> {

	private Filiali(FilialiId id) {
		super(id, FilialiDeleted.class);
	}

	public Filiali(FilialiId id ,String nome) {
		this(id);
		applyChange(new FilialiCreated(id , nome));
	}
	
	public Filiali update( String nome) {
		applyChange(new FilialiUpdated(getId() , nome));
		return this;
	}

}