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
import com.example.demo.Models.ClienteMasServicios;
import com.example.demo.Models.Empleado;
import com.example.demo.Models.EmpleadoMasServicios;
import com.example.demo.Models.Servicio;
import com.example.demo.security.JwtValidator;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.IEmpleadoService;
import com.example.demo.services.ILoginService;
import com.example.demo.services.IServicioService;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private IDispositivoService dispositivoService;
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private JwtValidator validator;
	
	@GetMapping("/get_empleados/{comercioId}")
	public ResponseEntity<ArrayList<Empleado>> getEmpleados(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "comercioId")Long comercioId, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		ArrayList<Empleado> empleados = empleadoService.findByComercioId(comercioId);
		return new ResponseEntity<>(empleados, HttpStatus.OK);
	}
	
	@PostMapping("/save_empleado")
	public ResponseEntity<?> saveEmpleado(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Empleado empleado) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Empleado resultado = empleadoService.saveEmpleado(empleado);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}
	
	@PutMapping("/update_empleado")
	public ResponseEntity<?> updateEmpleado(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Empleado empleado) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (empleadoService.findByEmpleadoId(empleado.getEmpleadoId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Empleado resultado = empleadoService.updateEmpleado(empleado);
			return new ResponseEntity<>(resultado, HttpStatus.OK);
		}
	}
	
	@GetMapping("get_empleado/{id}")
	public ResponseEntity<?>getEmpleadoWithId(@RequestHeader(Constants.authorizationHeaderKey) String token, @PathVariable(value = "id")Long id, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		Empleado empleado = empleadoService.findByEmpleadoId(id);
		if (empleado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(empleado, HttpStatus.OK);
		}
	}
	
	@PostMapping("/delete_empleado")
	public ResponseEntity<?> deleteEmpleado(@RequestHeader(Constants.authorizationHeaderKey) String token, @RequestHeader(Constants.uniqueDeviceIdHeaderKey) String uniqueDeviceId, @RequestBody Empleado empleado) {
		if (!CommonFunctions.hasTokenAuthorization(token, validator,  loginService)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		if (!CommonFunctions.hasAuthorization(dispositivoService, uniqueDeviceId)) {
			return new ResponseEntity<>(HttpStatus.valueOf(Constants.uniqueDeviceErrorValue));
		}
		
		if (empleadoService.findByEmpleadoId(empleado.getEmpleadoId()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			empleadoService.deleteEmpleado(empleado.getEmpleadoId());
			ArrayList<Empleado> empleados = empleadoService.findByComercioId(empleado.getComercioId());

			Empleado empleadoJefe = new Empleado();
			ArrayList<Servicio> resultados = new ArrayList<>();
			if (empleados.size() > 0) {
				empleadoJefe = getEmpleadoJefe(empleados);
				ArrayList<Servicio> servicios = servicioService.findByProfesional(empleado.getEmpleadoId());
				servicios = CommonFunctions.filterServicesByComercioId(servicios, empleado.getComercioId());
				for (Servicio servicio : servicios) {
					servicio.setEmpleadoId(empleadoJefe.getEmpleadoId());
				}
				
				resultados = servicioService.saveServicios(servicios);
			}

			return new ResponseEntity<>(new EmpleadoMasServicios(empleado, resultados), HttpStatus.OK);
		}
	}
	
	private Empleado getEmpleadoJefe(ArrayList<Empleado> empleados) {
		for (Empleado empleado : empleados) {
			if (empleado.getIs_empleado_jefe()) {
				return empleado;
			}
		}
		
		return empleados.get(0);
	}
}
