package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Notification;

public interface INotificationDao extends CrudRepository<Notification, Long> {
	public Optional<Notification> findByNotificationId(Long notification_id);
}
