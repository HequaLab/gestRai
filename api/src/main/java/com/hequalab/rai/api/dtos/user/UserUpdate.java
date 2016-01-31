package com.hequalab.rai.api.dtos.user;

import com.hequalab.rai.domain.user.Role;

public class UserUpdate {

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String divisione;
    
    private Role roles;
    
    private String superiore;
    
    @SuppressWarnings("unused")
    private UserUpdate() {

    }
    
    public UserUpdate(String userName, String firstName, String lastName,
            String email, String unitCode) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = unitCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String unitCode) {
        this.password = unitCode;
    }

	public String getDivisione() {
		return divisione;
	}

	public void setDivisione(String divisione) {
		this.divisione = divisione;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public String getSuperiore() {
	    return superiore;
	}

	public void setSuperiore(String superiore) {
	    this.superiore = superiore;
	}

}