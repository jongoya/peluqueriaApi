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
import com.example.demo.Models.Servicio;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.INotificationService;
import com.example.demo.services.IServicioService;

@RestController
@RequestMapping("/api")
public class ServicioController {
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private INotificationService notificationService;
	@Autowired
	private IDispositivoService dispositivoService;
	
	@GetMapping("/get_servicios")
	public ResponseEntity<ArrayList<Servicio>> getServicios(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Servicio> servicios = servicioService.findAll();
		return new ResponseEntity<>(servicios, HttpStatus.OK);
	}
	
	@PostMapping("/save_servicio")
	public ResponseEntity<Servicio> saveServicio(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Servicio servicio) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Servicio resultado = servicioService.saveServicio(servicio);
		
		ArrayList<Notification> notifications = notificationService.findNotificationsByType(Constants.cadenciaNotificationType);
		for (Notification notification: notifications) {
			if (notification.getClientId() == servicio.getClientId()) {
				notificationService.deleteNotificationById(notification.getNotificationId());
			}
		}
		
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_servicios")
	public ResponseEntity<ArrayList<Servicio>> saveServicios(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody ArrayList<Servicio> servicios) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Servicio> resultados = servicioService.saveServicios(servicios);
		
		ArrayList<Notification> notifications = notificationService.findNotificationsByType(Constants.cadenciaNotificationType);
		for (Notification notification: notifications) {
			if (notification.getClientId() == servicios.get(0).getClientId()) {
				notificationService.deleteNotificationById(notification.getNotificationId());
			}
		}
		return new ResponseEntity<>(resultados, HttpStatus.CREATED);
	}
	
	@GetMapping("/get_servicio/{id}")
	public ResponseEntity<?> getServicioByServicioId(@PathVariable(value = "id")Long id, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Servicio servicio = servicioService.findByServicioId(id); 
		if (servicio == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(servicio, HttpStatus.OK);
		}
	}
	
	@GetMapping("/get_servicios_client/{id}")
	public ResponseEntity<?> getServiciosByClientId(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @PathVariable(value = "id")Long id) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Servicio> servicios = servicioService.findByClienteId(id);
		if (servicios.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(servicios, HttpStatus.OK);
		}
	}
	
	@PutMapping("/update_servicio")
	public ResponseEntity<?> updateServicio(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Servicio servicio) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (servicioService.findByServicioId(servicio.getServiceId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Servicio resultado = servicioService.updateServicio(servicio);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete_servicio")
	public ResponseEntity<Void> deleteServicio(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Servicio servicio) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (servicioService.findByServicioId(servicio.getServiceId()) != null) {
			servicioService.deleteServicio(servicio.getServiceId());
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
