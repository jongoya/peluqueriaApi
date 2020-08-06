package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Commons.CommonFunctions;
import com.example.demo.Commons.Constants;
import com.example.demo.Models.EstiloPrivado;
import com.example.demo.security.JwtValidator;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.IEstiloPrivadoService;
import com.example.demo.services.ILoginService;

@RestController
@RequestMapping("/api")
public class EstiloPrivadoController {
	
	@Autowired
	private IEstiloPrivadoService estiloPrivadoService;
	
	@Autowired
	private IDispositivoService dispositivoService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	
	@GetMapping("/get_estilo_privado/{comercioId}")
	public ResponseEntity<EstiloPrivado> getEstiloPrivado(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "comercioId")Long comercioId, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		EstiloPrivado estiloPrivado = estiloPrivadoService.findByComercioId(comercioId);
		return new ResponseEntity<EstiloPrivado>(estiloPrivado, HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("/update_estilo_privado")
	public ResponseEntity<EstiloPrivado> updateEstiloPrivado(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody EstiloPrivado estiloPrivado) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		EstiloPrivado serverEstilo = estiloPrivadoService.findByComercioId(estiloPrivado.getComercioId());
		
		if (serverEstilo == null) {
			return new ResponseEntity<EstiloPrivado>(HttpStatus.NOT_FOUND);
		}
		
		serverEstilo.setAppSmallIcon(estiloPrivado.getAppSmallIcon());
		serverEstilo.setBackgroundColor(estiloPrivado.getBackgroundColor());
		serverEstilo.setNavigationColor(estiloPrivado.getNavigationColor());
		serverEstilo.setPrimaryColor(estiloPrivado.getPrimaryColor());
		serverEstilo.setPrimaryTextColor(estiloPrivado.getPrimaryTextColor());
		serverEstilo.setSecondaryColor(estiloPrivado.getSecondaryColor());
		serverEstilo.setSecondaryTextColor(estiloPrivado.getSecondaryTextColor());
		serverEstilo.setAppName(estiloPrivado.getAppName());
		
		serverEstilo = estiloPrivadoService.updateEstilo(serverEstilo);
		
		return new ResponseEntity<EstiloPrivado>(serverEstilo, HttpStatus.OK);
	}
	
}
