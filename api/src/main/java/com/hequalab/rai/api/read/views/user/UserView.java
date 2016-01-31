package com.hequalab.rai.api.read.views.user;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.hequalab.rai.api.read.views.BaseEntity;
import com.hequalab.rai.domain.user.Role;
import com.hequalab.rai.domain.user.UserId;

@Entity
@Table(name = "user_view")
public class UserView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Type(type = "UserIdType")
    private UserId userId;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
    
    private String divisione;
    private String superiore;

    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name = "user_role_view", joinColumns = @JoinColumn(name = "user_id"), 
        indexes = {@Index(columnList = "user_id")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"})})
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles = Sets.newHashSet();

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String unitCode) {
        this.password = unitCode;
    }

    public Set<Role> getRoles() {
        roles.size();
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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