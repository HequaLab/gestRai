package com.hequalab.rai.domain.richiestanuovoservizio.events;

import org.joda.time.LocalDateTime;

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.richiestanuovoservizio.RichiestaNuovoServizioId;
import com.hequalab.rai.domain.user.UserId;

public class RichiestaServizioErogata
	extends DefaultEvent<RichiestaNuovoServizioId> {
    
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private RichiestaServizioErogata() {
    }
    
    UserId user;
    LocalDateTime timeStamp;
    private String allegato;

    public RichiestaServizioErogata(RichiestaNuovoServizioId id, UserId user, LocalDateTime timeStamp, String allegato) {
	super(id);
	this.setUser(user);
	this.setTimeStamp(timeStamp);
	this.setAllegato(allegato);
    }

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

	public String getAllegato() {
		return allegato;
	}

	public void setAllegato(String allegato) {
		this.allegato = allegato;
	}

}
