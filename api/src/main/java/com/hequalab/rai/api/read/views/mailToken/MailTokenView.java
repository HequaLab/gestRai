package com.hequalab.rai.api.read.views.mailToken;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.hequalab.rai.api.read.views.BaseEntity;
import com.hequalab.rai.domain.user.UserId;

@Entity
@Table(name = "mailtoken_view")
public class MailTokenView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Type(type = "UserIdType")
    UserId user;
    
    String accessToken;

    LocalDateTime expiration;

    public UserId getUser() {
	return user;
    }

    public void setUser(UserId user) {
	this.user = user;
    }

    public String getAccessToken() {
	return accessToken;
    }

    public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
    }

    public LocalDateTime getExpiration() {
	return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
	this.expiration = expiration;
    }

}
