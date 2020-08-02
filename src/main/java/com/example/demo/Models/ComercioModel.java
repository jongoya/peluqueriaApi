package com.example.demo.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class ComercioModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Login login;
	private ArrayList<Dispositivo> dispositivos;
	private EstiloPrivado estiloPrivado;
	private EstiloPublico estiloPublico;
	
	public ComercioModel(Login login, ArrayList<Dispositivo> dispositivos, EstiloPrivado estiloPrivado,
			EstiloPublico estiloPublico) {
		super();
		this.login = login;
		this.dispositivos = dispositivos;
		this.estiloPrivado = estiloPrivado;
		this.estiloPublico = estiloPublico;
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

	public EstiloPublico getEstiloPublico() {
		return estiloPublico;
	}

	public void setEstiloPublico(EstiloPublico estiloPublico) {
		this.estiloPublico = estiloPublico;
	}
}
