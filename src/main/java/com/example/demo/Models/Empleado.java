package com.example.demo.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "empleado_id")
    private Long empleadoId;
	
    private String nombre;
    private String apellidos;
    private Long fecha;
    private String telefono;
    private String email;
    
    @Column(name = "red_color_value")
    private Long redColorValue;
    
    @Column(name = "green_color_value")
    private Long greenColorValue;
    
    @Column(name = "blue_color_value")
    private Long blueColorValue;
    
    public Empleado() {
    	
    }

	public Long getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(Long empleadoId) {
		this.empleadoId = empleadoId;
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

	public Long getRedColorValue() {
		return redColorValue;
	}

	public void setRedColorValue(Long redColorValue) {
		this.redColorValue = redColorValue;
	}

	public Long getGreenColorValue() {
		return greenColorValue;
	}

	public void setGreenColorValue(Long greenColorValue) {
		this.greenColorValue = greenColorValue;
	}

	public Long getBlueColorValue() {
		return blueColorValue;
	}

	public void setBlueColorValue(Long blueColorValue) {
		this.blueColorValue = blueColorValue;
	}
    
	private static final long serialVersionUID = 1L;
}
