package com.hequalab.rai.domain.user.events;

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.user.UserId;

public class UserLogin extends DefaultEvent<UserId> {

	private static final long serialVersionUID = 1L;

	private String ip;
	private String userAgent;
	private String accessTokenGenerato;

	@SuppressWarnings("unused")
	private UserLogin() {

	}

	public UserLogin(UserId id, String ip, String userAgent, String accessTokenGenerato) {
		super(id);
		this.setIp(ip);
		this.setUserAgent(userAgent);
		this.setAccessTokenGenerato(accessTokenGenerato);
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getAccessTokenGenerato() {
		return accessTokenGenerato;
	}

	public void setAccessTokenGenerato(String accessTokenGenerato) {
		this.accessTokenGenerato = accessTokenGenerato;
	}

}
