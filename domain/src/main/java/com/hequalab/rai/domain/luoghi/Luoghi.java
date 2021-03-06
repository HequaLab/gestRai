package com.hequalab.rai.domain.luoghi;

/*
 * Class generated by AppWizard
 */

import com.hequalab.rai.domain.luoghi.events.LuoghiCreated;
import com.hequalab.rai.domain.luoghi.events.LuoghiDeleted;
import com.hequalab.rai.domain.luoghi.events.LuoghiUpdated;
import com.hequalab.rai.dddd.DeletableAggregate;


public class Luoghi extends DeletableAggregate<Luoghi,LuoghiId,LuoghiDeleted> {

	private Luoghi(LuoghiId id) {
		super(id, LuoghiDeleted.class);
	}

	public Luoghi(LuoghiId id ,String indirizzo,String descrizione,String cap,String divisione) {
		this(id);
		applyChange(new LuoghiCreated(id , indirizzo, descrizione, cap,divisione));
	}
	
	public Luoghi update( String indirizzo,String descrizione,String cap,String divisione) {
		applyChange(new LuoghiUpdated(getId() , indirizzo, descrizione, cap,divisione));
		return this;
	}

}