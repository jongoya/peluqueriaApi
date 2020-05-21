package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.TipoServicio;
import com.example.demo.services.ITipoServicioService;

@RestController
@RequestMapping("/api")
public class TipoServicioController {
	
	@Autowired
	public ITipoServicioService tipoServicioService;
	
	@GetMapping("/get_tipo_servicios")
	public ResponseEntity<ArrayList<TipoServicio>> getTipoServicios() {
		ArrayList<TipoServicio> servicios = tipoServicioService.findAll();
		return new ResponseEntity<>(servicios, HttpStatus.OK);
	}
	
	@GetMapping("/get_tipo_servicio/{id}")
	public ResponseEntity<?> getTipoServicio(@PathVariable(value = "id")Long id) {
		TipoServicio servicio = tipoServicioService.findByTipoServicioId(id);
		
		if (servicio == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(servicio, HttpStatus.OK);
		}
	}
	
	@PostMapping("/save_tipo_servicio")
	public ResponseEntity<TipoServicio> saveTipoServicio(@RequestBody TipoServicio tipoServicio) {
		TipoServicio resultado = tipoServicioService.saveTipoServicio(tipoServicio);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
}
