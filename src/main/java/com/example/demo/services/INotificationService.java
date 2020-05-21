package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.Notification;

public interface INotificationService {
	public ArrayList<Notification> findAll();
	public ArrayList<Notification>findNotificationsByType(String type);
	public Notification findNotificationById(Long notificationId);
	public ArrayList<Notification> saveNotifications(ArrayList<Notification> notifications);
	public Notification saveNotification(Notification notification);
	public Notification updateNotification(Notification notification);
	public void deleteNotificationById(Long notificationId);
	public void deleteNotifications(ArrayList<Notification> notifications);
}
