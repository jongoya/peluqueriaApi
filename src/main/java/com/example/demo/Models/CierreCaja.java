package com.example.demo.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cierre_caja")
public class CierreCaja implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cajaId;
    private Long fecha;
    private int numero_servicios;
    private double total_caja;
    private double total_productos;
    private double efectivo;
    private double tarjeta;
    private Long comercioId;
    
    public CierreCaja() {
    	
    }

	public Long getCajaId() {
		return cajaId;
	}

	public void setCajaId(Long cajaId) {
		this.cajaId = cajaId;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public int getNumeroServicios() {
		return numero_servicios;
	}

	public void setNumeroServicios(int numeroServicios) {
		this.numero_servicios = numeroServicios;
	}

	public double getTotalCaja() {
		return total_caja;
	}

	public void setTotalCaja(double totalCaja) {
		this.total_caja = totalCaja;
	}

	public double getTotalProductos() {
		return total_productos;
	}

	public void setTotalProductos(double totalProductos) {
		this.total_productos = totalProductos;
	}

	public double getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(double efectivo) {
		this.efectivo = efectivo;
	}

	public double getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(double tarjeta) {
		this.tarjeta = tarjeta;
	}
    
	public Long getComercioId() {
		return comercioId;
	}

	public void setComercioId(Long comercioId) {
		this.comercioId = comercioId;
	}

	private static final long serialVersionUID = 1L;
}
