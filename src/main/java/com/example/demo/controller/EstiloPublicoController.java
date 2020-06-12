package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.EstiloPublico;
import com.example.demo.services.IEstiloPublicoService;

@RestController
@RequestMapping("/api/")
public class EstiloPublicoController {
	
	@Autowired
	private IEstiloPublicoService estiloPublicService;
	
	@GetMapping("/get_estilo_publico/{bundleId}")
	public ResponseEntity<EstiloPublico> getEstiloPublico(@PathVariable(value = "bundleId")String bundleId) {
		if (bundleId.length() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		EstiloPublico estilo = estiloPublicService.findByAndroidBundleId(bundleId);
		
		if (estilo == null) {
			estilo = estiloPublicService.findByIosBundleId(bundleId);
		}
		
		if (estilo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(estilo, HttpStatus.OK);
	}
}
