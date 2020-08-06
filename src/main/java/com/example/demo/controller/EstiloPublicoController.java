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
import com.example.demo.Models.EstiloPublico;
import com.example.demo.security.JwtValidator;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.IEstiloPublicoService;
import com.example.demo.services.ILoginService;

@RestController
@RequestMapping("/api/")
public class EstiloPublicoController {
	
	@Autowired
	private IEstiloPublicoService estiloPublicService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@Autowired
	private IDispositivoService dispositivoService;
	
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
	
	@CrossOrigin
	@PostMapping("/update_estilo_publico")
	public ResponseEntity<EstiloPublico> updateEstiloPublico(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody EstiloPublico estiloPublico) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		EstiloPublico serverEstilo = estiloPublicService.findByEstiloId(estiloPublico.getEstiloId());
		
		if (serverEstilo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		serverEstilo.setFondoLogin(estiloPublico.getFondoLogin());
		serverEstilo.setIconoApp(estiloPublico.getIconoApp());
		serverEstilo.setPrimaryColor(estiloPublico.getPrimaryColor());
		serverEstilo.setPrimaryTextColor(estiloPublico.getPrimaryTextColor());
		serverEstilo.setSecondaryTextColor(estiloPublico.getSecondaryTextColor());
		
		serverEstilo = estiloPublicService.updateEstilo(serverEstilo);
		
		return new ResponseEntity<>(serverEstilo, HttpStatus.OK);
	}
}
