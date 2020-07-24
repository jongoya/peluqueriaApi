package com.example.demo.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cesta")
public class Cesta implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cestaId;
	private Long clientId;
	private Long fecha;
	private boolean isEfectivo;
	private Long comercioId;
	
	public Long getCestaId() {
		return cestaId;
	}

	public void setCestaId(Long cestaId) {
		this.cestaId = cestaId;
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

	public boolean isEfectivo() {
		return isEfectivo;
	}

	public void setEfectivo(boolean isEfectivo) {
		this.isEfectivo = isEfectivo;
	}

	public Long getComercioId() {
		return comercioId;
	}

	public void setComercioId(Long comercioId) {
		this.comercioId = comercioId;
	}

	private static final long serialVersionUID = 1L;

}
