package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Notification;
import com.example.demo.dao.INotificationDao;

@Service
public class NotificationServiceImpl implements INotificationService {
	
	@Autowired
	public INotificationDao notificationDao;

	@Override
	public ArrayList<Notification> findAll() {
		return (ArrayList<Notification>) notificationDao.findAll();
	}

	@Override
	public ArrayList<Notification> findNotificationsByType(String type) {
		return (ArrayList<Notification>) notificationDao.findByType(type);
	}

	@Override
	public Notification findNotificationById(Long notificationId) {
		return (Notification) notificationDao.findByNotificationId(notificationId).orElse(null);
	}

	@Override
	public ArrayList<Notification> saveNotifications(ArrayList<Notification> notifications) {
		return (ArrayList<Notification>) notificationDao.saveAll(notifications);
	}

	@Override
	public Notification saveNotification(Notification notification) {
		return (Notification) notificationDao.save(notification);
	}

	@Override
	public Notification updateNotification(Notification notification) {
		return (Notification) notificationDao.save(notification);
	}

	@Override
	public void deleteNotificationById(Long notificationId) {
		notificationDao.deleteById(notificationId);
	}

	@Override
	public void deleteNotifications(ArrayList<Notification> notifications) {
		notificationDao.deleteAll(notifications);
	}

	@Override
	public ArrayList<Notification> updateNotifications(ArrayList<Notification> notifications) {
		return (ArrayList<Notification>) notificationDao.saveAll(notifications);
	}

	@Override
	public ArrayList<Notification> findByComercioId(Long comercioId) {
		return notificationDao.findByComercioId(comercioId);
	}
}
