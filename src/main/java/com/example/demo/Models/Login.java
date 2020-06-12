package com.example.demo.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "login")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comercioId;
	private String nombre_comercio;
	private String usuario;
	private String password;
	private String token;
	private int numero_dispositivos;
	private boolean active;
	private String androidBundleId;
	private String iosBundleId;
	
	@Transient
	private String nombre_dispositivo;
	
	@Transient
	private String unique_deviceId;
	
	public Login() {
		
	}
	
	public Long getComercioId() {
		return comercioId;
	}
	
	public void setComercioId(Long comercioId) {
		this.comercioId = comercioId;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public int getNumero_dispositivos() {
		return numero_dispositivos;
	}
	
	public void setNumero_dispositivos(int numero_dispositivos) {
		this.numero_dispositivos = numero_dispositivos;
	}

	public String getNombre() {
		return nombre_comercio;
	}

	public void setNombre(String nombre) {
		this.nombre_comercio = nombre;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getNombre_dispositivo() {
		return nombre_dispositivo;
	}

	public void setNombre_dispositivo(String nombre_dispositivo) {
		this.nombre_dispositivo = nombre_dispositivo;
	}

	public String getUnique_deviceId() {
		return unique_deviceId;
	}

	public void setUnique_deviceId(String unique_deviceId) {
		this.unique_deviceId = unique_deviceId;
	}

	public String getAndroidBundleId() {
		return androidBundleId;
	}

	public void setAndroidBundleId(String androidBundleId) {
		this.androidBundleId = androidBundleId;
	}

	public String getIosBundleId() {
		return iosBundleId;
	}

	public void setIosBundleId(String iosBundleId) {
		this.iosBundleId = iosBundleId;
	}
}
