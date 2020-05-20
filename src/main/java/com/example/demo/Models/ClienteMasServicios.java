package com.example.demo.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class ClienteMasServicios implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private ArrayList<Servicio> servicios;
	
	public ClienteMasServicios() {
		
	}
	
	public ClienteMasServicios(Cliente cliente, ArrayList<Servicio> servicios) {
		this.cliente = cliente;
		this.servicios = servicios;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	
}
