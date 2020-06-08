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
import com.example.demo.Models.CierreCaja;
import com.example.demo.security.JwtValidator;
import com.example.demo.services.ICierreCajaService;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.ILoginService;

@RestController
@RequestMapping("/api")
public class CierreCajaController {

	@Autowired
	public ICierreCajaService cierreCajaService;
	@Autowired
	private IDispositivoService dispositivoService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@GetMapping("/get_cierre_cajas/{comercioId}")
	public ResponseEntity<ArrayList<CierreCaja>> getCierreCajas(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "comercioId")Long comercioId, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<CierreCaja> cierreCajas = cierreCajaService.findByComercioId(comercioId);
		return new ResponseEntity<>(cierreCajas, HttpStatus.OK);
	}
	
	@PostMapping("/save_cierre_caja")
	public ResponseEntity<CierreCaja> saveCierreCaja(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody CierreCaja cierreCaja) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		CierreCaja resultado = cierreCajaService.saveCierreCaja(cierreCaja);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PostMapping("/save_cierre_cajas")
	public ResponseEntity<ArrayList<CierreCaja>> saveCierreCajas(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody ArrayList<CierreCaja> cierreCajas) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<CierreCaja> resultado = cierreCajaService.saveCierreCajas(cierreCajas);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
}
