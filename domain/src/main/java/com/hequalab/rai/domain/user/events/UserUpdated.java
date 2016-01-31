package com.hequalab.rai.domain.user.events;

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.user.UserId;

public class UserUpdated extends DefaultEvent<UserId> {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String divisione;
    private String superiore;

    @SuppressWarnings("unused")
    private UserUpdated() {

    }

    public UserUpdated(UserId id, String userName, String firstName,
	    String lastName, String email, String divisione, String superiore) {
	super(id);
	this.userName = userName;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.setDivisione(divisione);
	this.setSuperiore(superiore);
    }

    public String getUserName() {
	return userName;
    }

    public String getFirstName() {
	return firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public String getEmail() {
	return email;
    }

    public String getDivisione() {
	return divisione;
    }

    public void setDivisione(String divisione) {
	this.divisione = divisione;
    }

    public String getSuperiore() {
	return superiore;
    }

    public void setSuperiore(String superiore) {
	this.superiore = superiore;
    }

}