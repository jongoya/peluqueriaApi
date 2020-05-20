package com.example.demo.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class EmpleadoMasServicios implements Serializable {
	
	private Empleado empleado;
	private ArrayList<Servicio> servicios;
	
	public EmpleadoMasServicios() {
		super();
	}

	public EmpleadoMasServicios(Empleado empleado, ArrayList<Servicio> servicios) {
		super();
		this.empleado = empleado;
		this.servicios = servicios;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	
}
