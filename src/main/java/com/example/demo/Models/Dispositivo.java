package com.example.demo.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dispositivo")
public class Dispositivo implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long dispositivoId;
	
	private Long comercioId;
	private Long fecha;
	private String nombre_dispositivo;
	
	public Dispositivo() {
		
	}
	
	public Dispositivo(Long comercioId, Long fecha, String nombre_dispositivo) {
		super();
		this.comercioId = comercioId;
		this.fecha = fecha;
		this.nombre_dispositivo = nombre_dispositivo;
	}

	public Long getDispositivoId() {
		return dispositivoId;
	}

	public void setDispositivoId(Long dispositivoId) {
		this.dispositivoId = dispositivoId;
	}

	public Long getComercioId() {
		return comercioId;
	}

	public void setComercioId(Long comercioId) {
		this.comercioId = comercioId;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public String getNombre_dispositivo() {
		return nombre_dispositivo;
	}

	public void setNombre_dispositivo(String nombre_dispositivo) {
		this.nombre_dispositivo = nombre_dispositivo;
	}

	private static final long serialVersionUID = 1L;
}
