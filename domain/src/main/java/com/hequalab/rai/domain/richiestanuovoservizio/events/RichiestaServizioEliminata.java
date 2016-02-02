package com.hequalab.rai.domain.richiestanuovoservizio.events;

import org.joda.time.LocalDateTime;

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.richiestanuovoservizio.RichiestaNuovoServizioId;
import com.hequalab.rai.domain.user.UserId;

public class RichiestaServizioEliminata
	extends DefaultEvent<RichiestaNuovoServizioId> {
    
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private RichiestaServizioEliminata() {
    }
    
    UserId user;
    LocalDateTime timeStamp;

    public RichiestaServizioEliminata(RichiestaNuovoServizioId id, UserId user, LocalDateTime timeStamp) {
	super(id);
	this.setUser(user);
	this.setTimeStamp(timeStamp);
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

}
