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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Cliente;
import com.example.demo.Models.ClienteMasServicios;
import com.example.demo.Models.Servicio;
import com.example.demo.services.IClienteService;
import com.example.demo.services.IServicioService;
import com.example.demo.services.Utils.ServicesUtils;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IServicioService servicioService;
	
	@GetMapping("/get_clientes")
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> getClientes() {
		return clienteService.findAll();
	}
	
	@PostMapping("/save_cliente")
	public ResponseEntity<?> saveClienteYServicios(@RequestBody ClienteMasServicios body) {
		Cliente cliente = clienteService.save(body.getCliente());
		ArrayList<Servicio> servicios = body.getServicios();
		
		for (Servicio servicio: servicios) {
			servicio.setClientId(cliente.getId());
			servicio.setNombre(cliente.getNombre());
			servicio.setApellidos(cliente.getApellidos());
		}
		
		ArrayList<Servicio> resultados = servicioService.saveServicios(servicios);
		body.setCliente(cliente);
		body.setServicios(resultados);
		
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	
	@GetMapping("/get_cliente/{id}")
	public ResponseEntity<?> getCliente(@PathVariable(value = "id")Long id) {
		Cliente resultado = clienteService.findByClienteId(id);
		
		if (resultado != null) {
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@PutMapping("/update_cliente")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
		Cliente clienteServer = clienteService.findByClienteId(cliente.getId());
		if (clienteServer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			clienteServer = ServicesUtils.updateCliente(cliente, clienteServer);
			Cliente resultado = clienteService.updateCliente(clienteServer);
			ArrayList<Servicio> servicios = servicioService.findByClienteId(cliente.getId());
			
			for (Servicio servicio : servicios) {
				servicio.setNombre(resultado.getNombre());
				servicio.setApellidos(resultado.getApellidos());
			}
			
			ArrayList<Servicio> resultados = servicioService.saveServicios(servicios);
			
			return new ResponseEntity<>(new ClienteMasServicios(clienteServer, resultados), HttpStatus.OK);
		}
	}
}
