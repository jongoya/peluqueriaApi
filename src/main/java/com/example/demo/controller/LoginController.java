package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Dispositivo;
import com.example.demo.Models.Login;
import com.example.demo.services.IDispositivoService;
import com.example.demo.services.ILoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired IDispositivoService dispositivoService;
	
	@PostMapping("/register_comercio")
	public ResponseEntity<Login> registerComercio(@RequestBody Login login) {
		if (loginService.findByComercioId(login.getComercioId()) != null) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		} else {
			login.setActive(false);
			Login response = loginService.save(login);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
	}
	
	@PostMapping("/login_comercio")
	public ResponseEntity<Login> loginComercio(@RequestBody Login login) {
		Login comercioLogin = loginService.findByUsuario(login.getUsuario());
		
		if (comercioLogin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (comercioLogin.getNombre().compareTo(login.getNombre()) != 0) {
			return new ResponseEntity<>(HttpStatus.valueOf(411));//Revisamos que se esta logeando en el comercio adecuado
		}
		
		if (comercioLogin.getPassword().compareTo(login.getPassword()) != 0) {
			return new ResponseEntity<>(HttpStatus.valueOf(412));
		}
		
		boolean dispositivoRegistrado = false;
		ArrayList<Dispositivo> dispositivos = dispositivoService.findByComercioId(comercioLogin.getComercioId());
		for (Dispositivo dispositivo : dispositivos) {
			if (dispositivo.getNombre_dispositivo().compareTo(login.getNombre_dispositivo()) == 0) {
				dispositivoRegistrado = true;
			}
		}
		
		if (dispositivos.size() == comercioLogin.getNumero_dispositivos() && !dispositivoRegistrado) {
			return new ResponseEntity<>(HttpStatus.valueOf(413));
		}
		
		if (!dispositivoRegistrado) {
			dispositivoService.save(new Dispositivo(comercioLogin.getComercioId(), new Date().getTime(), login.getNombre_dispositivo()));
		}
		
		if (!comercioLogin.isActive()) {
			comercioLogin.setActive(true);
			comercioLogin.setToken(generarToken());
			comercioLogin = loginService.updateLogin(comercioLogin);
		}
		
		return new ResponseEntity<>(comercioLogin, HttpStatus.OK);
	}
	
	private String generarToken() {
		//TODO implementacion del token
		return "token";
	}
}
