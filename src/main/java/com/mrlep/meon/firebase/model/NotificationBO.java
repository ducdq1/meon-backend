package com.mrlep.meon.firebase.model;

public class NotificationBO {
	public static final  String TOPIC ="/topics/";
	String to ;
	Notification notification;
	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

}
