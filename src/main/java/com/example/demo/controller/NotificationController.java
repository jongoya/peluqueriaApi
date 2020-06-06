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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Commons.CommonFunctions;
import com.example.demo.Commons.Constants;
import com.example.demo.Models.Notification;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.INotificationService;

@RestController
@RequestMapping("/api")
public class NotificationController {

	@Autowired
	public INotificationService notificationService;
	@Autowired
	private IDispositivoService dispositivoService;
	
	@GetMapping("/get_notifications")
	public ResponseEntity<ArrayList<Notification>> getNotifications(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Notification> notifications = notificationService.findAll();
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}
	
	@GetMapping("/get_notifications_type/{type}")
	public ResponseEntity<ArrayList<Notification>> getNotificationsByType(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @PathVariable(value = "type")String type) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Notification> notifications = notificationService.findNotificationsByType(type);
		return new ResponseEntity<>(notifications, HttpStatus.OK);
	}
	
	@PostMapping("/save_notifications")
	public ResponseEntity<ArrayList<Notification>> saveNotifications(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody ArrayList<Notification> notifications) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Notification> resultado = notificationService.saveNotifications(notifications);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_notification")
	public ResponseEntity<Notification> saveNotification(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Notification notification) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Notification resultado = notificationService.saveNotification(notification);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PutMapping("/update_notification")
	public ResponseEntity<Notification> updateNotification(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Notification notification) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (notificationService.findNotificationById(notification.getNotificationId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Notification resultado = notificationService.updateNotification(notification);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@PutMapping("/update_notifications")
	public ResponseEntity<ArrayList<Notification>> updateNotifications(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody ArrayList<Notification> notifications) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		for (Notification notification : notifications) {
			if (notificationService.findNotificationById(notification.getNotificationId()) == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		ArrayList<Notification> notificaciones = notificationService.updateNotifications(notifications);
		return new ResponseEntity<>(notificaciones, HttpStatus.OK);
	}
	
	@PostMapping("/delete_notification")
	public ResponseEntity<Notification> deleteNotification(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Notification notification) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (notificationService.findNotificationById(notification.getNotificationId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		notificationService.deleteNotificationById(notification.getNotificationId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/delete_notifications")
	public ResponseEntity<Notification> deleteNotifications(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody ArrayList<Notification> notifications) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		notificationService.deleteNotifications(notifications);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
