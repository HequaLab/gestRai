package com.hequalab.rai.api.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.hequalab.rai.api.Api;

public class ClientMail {

    private HtmlEmail email;
    private String hostName;
    private Integer smtp;
    private String userName;
    private String pwd;
    private String alias;
    private Boolean ssl;

    public ClientMail() throws EmailException {
	MailClientConf conf = Api.mailClientConf;

	this.hostName = conf.getHostName();
	this.setSmtp(conf.getSmtp());
	this.userName = conf.getUserName();
	this.pwd = conf.getPwd();
	this.ssl = conf.getSsl();
	this.alias = conf.getAlias();

	email = new HtmlEmail();
	email.setDebug(true);
	email.setHostName(this.hostName);
	email.setSmtpPort(this.smtp);
	email.setAuthenticator(
		new DefaultAuthenticator(this.userName, this.pwd));
	email.setSSLOnConnect(this.ssl);
	email.setFrom(this.userName, this.getAlias());
    }

    public void sendEmail(String to, String oggetto, String testo)
	    throws EmailException {
	email.addTo(to);
	email.setSubject(oggetto);
	email.setHtmlMsg(testo);
	email.setTextMsg("Your email client does not support HTML messages");
	email.send();
    }

    public void sendEmail(String to, String oggetto, String testo,
	    String attachmentURL, String attachmentDescr,
	    String attachmentName) {

    }

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

    public Boolean getSSL() {
	return ssl;
    }

    public void setSSL(Boolean sSL) {
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

}
