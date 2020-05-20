package com.example.demo.Models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
    private Long serviceId;
	
	@Column(name = "client_id")
    private Long clientId;
    private String nombre;
    private String apellidos;
    private Long fecha;
    private Long profesional;
    private ArrayList <Long> servicios = new ArrayList<>();
    private String observacion;
    private double precio;
    
    public Servicio() {
    	
    }

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public Long getProfesional() {
		return profesional;
	}

	public void setProfesional(Long profesional) {
		this.profesional = profesional;
	}

	public ArrayList<Long> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Long> servicios) {
		this.servicios = servicios;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
    
	private static final long serialVersionUID = 1L;
}
