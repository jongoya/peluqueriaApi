package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.CierreCaja;
import com.example.demo.services.ICierreCajaService;

@RestController
@RequestMapping("/api")
public class CierreCajaController {

	@Autowired
	public ICierreCajaService cierreCajaService;
	
	@GetMapping("/get_cierre_cajas")
	public ResponseEntity<ArrayList<CierreCaja>> getCierreCajas() {
		ArrayList<CierreCaja> cierreCajas = cierreCajaService.findAll();
		return new ResponseEntity<>(cierreCajas, HttpStatus.OK);
	}
	
	@PostMapping("/save_cierre_caja")
	public ResponseEntity<CierreCaja> saveCierreCaja(@RequestBody CierreCaja cierreCaja) {
		CierreCaja resultado = cierreCajaService.saveCierreCaja(cierreCaja);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_cierre_cajas")
	public ResponseEntity<ArrayList<CierreCaja>> saveCierreCajas(@RequestBody ArrayList<CierreCaja> cierreCajas) {
		ArrayList<CierreCaja> resultado = cierreCajaService.saveCierreCajas(cierreCajas);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
}
