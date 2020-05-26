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
    private Long empleadoId;
    private String nombre;
    private String apellidos;
    private Long fecha;
    private String telefono;
    private String email;
    private Long red_color_value;
    private Long green_color_value;
    private Long blue_color_value;
    private Long comercioId;
    private Long is_empleado_jefe;
    
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
		return red_color_value;
	}

	public void setRedColorValue(Long redColorValue) {
		this.red_color_value = redColorValue;
	}

	public Long getGreenColorValue() {
		return green_color_value;
	}

	public void setGreenColorValue(Long greenColorValue) {
		this.green_color_value = greenColorValue;
	}

	public Long getBlueColorValue() {
		return blue_color_value;
	}

	public void setBlueColorValue(Long blueColorValue) {
		this.blue_color_value = blueColorValue;
	}
    
	public Long getComercioId() {
		return comercioId;
	}

	public void setComercioId(Long comercioId) {
		this.comercioId = comercioId;
	}

	public Long getIs_empleado_jefe() {
		return is_empleado_jefe;
	}

	public void setIs_empleado_jefe(Long is_empleado_jefe) {
		this.is_empleado_jefe = is_empleado_jefe;
	}



	private static final long serialVersionUID = 1L;
}
