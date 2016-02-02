package com.hequalab.rai.api.utility;

public class MailClientConf {
    public String getHostName() {
        return hostName;
    }
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public Boolean getSsl() {
        return ssl;
    }
    public void setSsl(Boolean sSL) {
        ssl = sSL;
    }
    public Integer getSmtp() {
	return smtp;
    }
    public void setSmtp(Integer smtp) {
	this.smtp = smtp;
    }
    public String getAlias() {
	return alias;
    }
    public void setAlias(String alias) {
	this.alias = alias;
    }
    private String hostName;
    private Integer smtp;
    private String userName;
    private String pwd;
    private Boolean ssl;
    private String alias;
}
