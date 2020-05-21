package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Notification;
import com.example.demo.services.INotificationService;

@RestController
@RequestMapping("/api")
public class NotificationController {

	@Autowired
	public INotificationService notificationService;
	
	@GetMapping("/get_notifications")
	public ResponseEntity<ArrayList<Notification>> getNotifications() {
		ArrayList<Notification> notifications = notificationService.findAll();
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}
	
	@GetMapping("/get_notifications_type/{type}")
	public ResponseEntity<ArrayList<Notification>> getNotificationsByType(@PathVariable(value = "type")String type) {
		ArrayList<Notification> notifications = notificationService.findNotificationsByType(type);
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}
	
	@PostMapping("/save_notifications")
	public ResponseEntity<ArrayList<Notification>> saveNotifications(@RequestBody ArrayList<Notification> notifications) {
		ArrayList<Notification> resultado = notificationService.saveNotifications(notifications);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_notification")
	public ResponseEntity<Notification> saveNotification(@RequestBody Notification notification) {
		Notification resultado = notificationService.saveNotification(notification);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PutMapping("/update_notification")
	public ResponseEntity<Notification> updateNotification(@RequestBody Notification notification) {
		if (notificationService.findNotificationById(notification.getNotificationId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Notification resultado = notificationService.updateNotification(notification);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete_notification")
	public ResponseEntity<Notification> deleteNotification(@RequestBody Notification notification) {
		if (notificationService.findNotificationById(notification.getNotificationId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		notificationService.deleteNotificationById(notification.getNotificationId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete_notifications")
	public ResponseEntity<Notification> deleteNotifications(@RequestBody ArrayList<Notification> notifications) {
		notificationService.deleteNotifications(notifications);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
