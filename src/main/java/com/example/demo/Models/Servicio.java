package com.example.demo.Models;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;
    private Long clientId;
    private Long fecha;
    private Long empleadoId;
    private ArrayList <Long> servicios = new ArrayList<>();
    private String observacion;
    private double precio;
    private Long comercioId;

	@Transient
	private String unique_deviceId;
    
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

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public Long getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
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
    
	public Long getComercioId() {
		return comercioId;
	}

	public void setComercioId(Long comercioId) {
		this.comercioId = comercioId;
	}

	public String getUnique_deviceId() {
		return unique_deviceId;
	}

	public void setUnique_deviceId(String unique_deviceId) {
		this.unique_deviceId = unique_deviceId;
	}

	private static final long serialVersionUID = 1L;
}
