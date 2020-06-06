package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Commons.CommonFunctions;
import com.example.demo.Commons.Constants;
import com.example.demo.Models.TipoServicio;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.ITipoServicioService;

@RestController
@RequestMapping("/api")
public class TipoServicioController {
	
	@Autowired
	public ITipoServicioService tipoServicioService;
	@Autowired
	private IDispositivoService dispositivoService;
	
	@GetMapping("/get_tipo_servicios")
	public ResponseEntity<ArrayList<TipoServicio>> getTipoServicios(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<TipoServicio> servicios = tipoServicioService.findAll();
		return new ResponseEntity<>(servicios, HttpStatus.OK);
	}
	
	@GetMapping("/get_tipo_servicio/{id}")
	public ResponseEntity<?> getTipoServicio(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @PathVariable(value = "id")Long id) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		TipoServicio servicio = tipoServicioService.findByTipoServicioId(id);
		
		if (servicio == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(servicio, HttpStatus.OK);
		}
	}
	
	@PostMapping("/save_tipo_servicio")
	public ResponseEntity<TipoServicio> saveTipoServicio(@RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody TipoServicio tipoServicio) {
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		TipoServicio resultado = tipoServicioService.saveTipoServicio(tipoServicio);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
}
