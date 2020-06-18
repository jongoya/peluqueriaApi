package com.example.demo.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginMasDispositivos implements Serializable {

	private Login login;
	private ArrayList<Dispositivo> dispositivos;
	private EstiloPrivado estiloPrivado;
	
	public LoginMasDispositivos(Login login, ArrayList<Dispositivo> dispositivos, EstiloPrivado estiloPrivado) {
		super();
		this.login = login;
		this.dispositivos = dispositivos;
		this.estiloPrivado = estiloPrivado;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public ArrayList<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(ArrayList<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public EstiloPrivado getEstiloPrivado() {
		return estiloPrivado;
	}

	public void setEstiloPrivado(EstiloPrivado estiloPrivado) {
		this.estiloPrivado = estiloPrivado;
	}
}
