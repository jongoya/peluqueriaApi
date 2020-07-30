package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.Login;

public interface ILoginService {
	public Login save(Login login);
	public Login findByComercioId(Long comercioId);
	public Login findByUsuario(String usuario);
	public Login updateLogin(Login login);
	public ArrayList<Login> findAllLogins();
}
