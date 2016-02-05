package com.hequalab.rai.api.utility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class ClientMail {

	private HtmlEmail email;
	private String hostName;
	private Integer smtp;
	private String userName;
	private String pwd;
	private String alias;
	private Boolean ssl;

	public ClientMail(MailClientConf conf) throws EmailException {

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
		//email.setSocketTimeout(8000);
		email.setSSLOnConnect(this.ssl);
		email.setFrom(this.userName, this.getAlias());
		System.out.println("Inizio.. fine process");
	}

	public void sendEmail(String to, String oggetto, String testo)
			throws EmailException {
		System.out.println("Inizio..");
		email.addTo(to);
		email.setSubject(oggetto);
		email.setHtmlMsg(testo);
		email.setTextMsg("Your email client does not support HTML messages");
		email.send();
		System.out.println("Inviata");
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

/* 
 * When you create an instance of MultiPartEmail (or HtmlEmail), set its
 * properties, and then invoke the send() method, the object will internally
 * invoke the following methods:
 * 
 * buildMimeMessage() sendMimeMessage() It is ok to invoke sendMimeMessage()
 * multiple times, such as a send-with-retry scenario. The problem is that
 * buildMimeMessage() can only be invoked once. When you rely on the send()
 * method of the base Email class, you get the exception found by the original
 * poster.
 * 
 * The solution is to use the two methods I just mentioned when your Email
 * object is a MultiPartEmail. You explicitly invoke buildMimeMessage() once,
 * then invoke sendMimeMessage() one or more times.
 */
