package com.hequalab.rai.api.auth;

import com.hequalab.rai.api.read.views.user.UserView;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Wither;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
@Wither
public class AccessToken {
	@JsonProperty("access_token_id")
	@NotNull
	private UUID accessTokenId;

	@JsonProperty("user")
	@NotNull
	private UserView user;
	

	@JsonProperty("last_access_utc")
	@NotNull
	private DateTime lastAccessUTC;


	public UserView getUser() {
		return user;
	}

	public void setUser(UserView user) {
		this.user = user;
	}

	public AccessToken(UUID accessTokenId, UserView user, DateTime lastAccessUTC) {
		this.accessTokenId = accessTokenId;
		this.user = user;
		this.lastAccessUTC = lastAccessUTC;
	}

	public UUID getAccessTokenId() {
		return this.accessTokenId;
	}

	public AccessToken withLastAccessUTC(DateTime dateTime) {
		this.lastAccessUTC = dateTime;
		return this;
	}

	public ReadableInstant getLastAccessUTC() {
		return this.lastAccessUTC;
	}

}