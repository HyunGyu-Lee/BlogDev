package com.leelab.blogproject.notification.vo;

import java.sql.Timestamp;

public class NotificationVo {

	public static final int state_checked = 1;
	public static final int state_not_checked = -1;
	
	private int id;
	private String notificate_target;
	private String notificator;
	private String message;
	private String link;
	private Timestamp create_at;
	private int check_state;
	
	public NotificationVo(){}

	public NotificationVo(int id, String notificate_target, String notificator, String message, String link,
			Timestamp create_at, int check_state) {
		super();
		this.id = id;
		this.notificate_target = notificate_target;
		this.notificator = notificator;
		this.message = message;
		this.link = link;
		this.create_at = create_at;
		this.check_state = check_state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotificate_target() {
		return notificate_target;
	}

	public void setNotificate_target(String notificate_target) {
		this.notificate_target = notificate_target;
	}

	public String getNotificator() {
		return notificator;
	}

	public void setNotificator(String notificator) {
		this.notificator = notificator;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Timestamp getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Timestamp create_at) {
		this.create_at = create_at;
	}

	public int getCheck_state() {
		return check_state;
	}

	public void setCheck_state(int check_state) {
		this.check_state = check_state;
	}

	@Override
	public String toString() {
		return "NotificationVo [id=" + id + ", notificate_target=" + notificate_target + ", notificator=" + notificator
				+ ", message=" + message + ", link=" + link + ", create_at=" + create_at + ", check_state="
				+ check_state + "]";
	}
	
}
