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
	@Column(name = "caja_id")
    private Long cajaId;
	
    private Long fecha;
    
    @Column(name = "numero_servicios")
    private int numeroServicios;
    
    @Column(name = "total_caja")
    private double totalCaja;
    
    @Column(name = "total_productos")
    private double totalProductos;
    
    private double efectivo;
    private double tarjeta;
    
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
		return numeroServicios;
	}

	public void setNumeroServicios(int numeroServicios) {
		this.numeroServicios = numeroServicios;
	}

	public double getTotalCaja() {
		return totalCaja;
	}

	public void setTotalCaja(double totalCaja) {
		this.totalCaja = totalCaja;
	}

	public double getTotalProductos() {
		return totalProductos;
	}

	public void setTotalProductos(double totalProductos) {
		this.totalProductos = totalProductos;
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
    
	private static final long serialVersionUID = 1L;
}
