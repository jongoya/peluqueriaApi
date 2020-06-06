package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Commons.CommonFunctions;
import com.example.demo.Commons.Constants;
import com.example.demo.Models.Cliente;
import com.example.demo.Models.ClienteMasServicios;
import com.example.demo.Models.Dispositivo;
import com.example.demo.Models.Notification;
import com.example.demo.Models.Servicio;
import com.example.demo.services.IClienteService;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.INotificationService;
import com.example.demo.services.IServicioService;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IServicioService servicioService;
	@Autowired INotificationService notificacionService;
	
	@Autowired
	private IDispositivoService dispositivoService;
	
	@GetMapping("/get_clientes")
	public ResponseEntity<ArrayList<Cliente>> getClientes(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping("/save_cliente")
	public ResponseEntity<ClienteMasServicios> saveClienteYServicios(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody ClienteMasServicios body) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Cliente cliente = clienteService.save(body.getCliente());
		ArrayList<Servicio> servicios = body.getServicios();
		
		for (Servicio servicio: servicios) {
			servicio.setClientId(cliente.getId());
		}
		
		ArrayList<Servicio> resultados = servicioService.saveServicios(servicios);
		body.setCliente(cliente);
		body.setServicios(resultados);
		
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	
	@GetMapping("/get_cliente/{id}")
	public ResponseEntity<?> getCliente(@PathVariable(value = "id")Long id, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Cliente resultado = clienteService.findByClienteId(id);
		
		if (resultado != null) {
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/update_cliente")
	public ResponseEntity<?> updateCliente(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Cliente cliente) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (clienteService.findByClienteId(cliente.getId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Cliente resultado = clienteService.updateCliente(cliente);
			ArrayList<Servicio> servicios = servicioService.findByClienteId(cliente.getId());
			
			return new ResponseEntity<>(new ClienteMasServicios(resultado, servicios), HttpStatus.OK);
		}
	}
	
	@PutMapping("update_notificacion_personalizada")
	public ResponseEntity<?> updateNotificacionPersonalizada(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Cliente cliente) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Cliente serverCliente = clienteService.findByClienteId(cliente.getId());
		if (serverCliente != null) {
			serverCliente.setFechaNotificacionPersonalizada(cliente.getFechaNotificacionPersonalizada());
			Cliente resultado = clienteService.updateCliente(serverCliente);
			if (cliente.getFechaNotificacionPersonalizada() == 0) {
				ArrayList<Notification> notifications = notificacionService.findNotificationsByType(Constants.personalizadaNotificationType);
				ArrayList<Notification> notificacionesAEliminar = new ArrayList<>();
				for (Notification notificacion : notifications) {
					if (notificacion.getClientId() == cliente.getId()) {
						notificacionesAEliminar.add(notificacion);
					}
				}
				
				notificacionService.deleteNotifications(notificacionesAEliminar);
			}
			
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
