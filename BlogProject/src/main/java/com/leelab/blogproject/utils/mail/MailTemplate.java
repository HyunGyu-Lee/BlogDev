package com.leelab.blogproject.utils.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTemplate {
	
	private String host = "smtp.gmail.com";
	private int port;
	private String username;
	private String password;

	public MailTemplate(){}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void send(String title, String content, String[] tos) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.trust", host);
		
		Session session = Session.getInstance(props, new Authenticator() {
						  	@Override
							protected PasswordAuthentication getPasswordAuthentication() {
						  		return new PasswordAuthentication(username, password);
							}
						  });
		
		MimeMessage msg = new MimeMessage(session);

		msg.setSubject(title);
		msg.setContent(content,"text/html; charset=utf-8");
		msg.setFrom(new InternetAddress(username));
		
		for(String to : tos)
		{
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		}
		
		Transport.send(msg, msg.getAllRecipients());
	}

}
