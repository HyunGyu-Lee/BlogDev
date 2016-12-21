package com.leelab.blogproject.utils.mail;

import javax.mail.MessagingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailTemplate {
	
	private JavaMailSender mailSender;
	private String from;
	private String encoding;
	
	public MailTemplate(){}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	public void send(String to, String title, String content) throws MessagingException {
		MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage(), true, encoding);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setText("", content);
		helper.setSubject(title);
		mailSender.send(helper.getMimeMessage());
	}
	
	public void send(String[] to, String title, String content) {
		
	}
}
