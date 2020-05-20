package com.example.demo.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    private String nombre;
    private String apellidos;
	private Long fecha;
	private String telefono;
	private String email;
	private String direccion;
	private String imagen;
	private String observaciones;
	
	@Column(name = "cadencia_visita")
	private String cadenciaVisita;
	
	@Column(name = "notificacion_personalizada")
	private Long notificacionPersonalizada;
	
	public Cliente() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCadenciaVisita() {
		return cadenciaVisita;
	}

	public void setCadenciaVisita(String cadenciaVisita) {
		this.cadenciaVisita = cadenciaVisita;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getNotificacionPersonalizada() {
		return notificacionPersonalizada;
	}

	public void setNotificacionPersonalizada(Long notificacionPersonalizada) {
		this.notificacionPersonalizada = notificacionPersonalizada;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	private static final long serialVersionUID = 1L;
}
