package com.leelab.blogproject.notification.vo;

/**
 * Builder for NotificationVo
 * */
public class NotificationBuilder {

	private NotificationVo notification;
	
	public NotificationBuilder() {
		notification = new NotificationVo();
	}
	
	public NotificationBuilder setLink(String link) {
		notification.setLink(link);
		return this;
	}
	
	public NotificationBuilder setNotificator(String notificator) {
		notification.setNotificator(notificator);
		return this;
	}
	
	public NotificationBuilder setMessage(String message) {
		notification.setMessage(message);
		return this;
	}
	
	public NotificationVo build() {
		return notification;
	}

	public NotificationBuilder setNotificateTarget(String user_id) {
		notification.setNotificate_target(user_id);
		return this;
	}
}
