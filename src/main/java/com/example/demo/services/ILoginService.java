package com.example.demo.services;

import com.example.demo.Models.Login;

public interface ILoginService {
	public Login save(Login login);
	public Login findByComercioId(Long comercioId);
	public Login findByUsuario(String usuario);
	public Login updateLogin(Login login);
}
