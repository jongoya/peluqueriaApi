package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.Empleado;

public interface IEmpleadoService {
	public ArrayList<Empleado> findAll();
	public Empleado saveEmpleado(Empleado empleado);
	public Empleado findByEmpleadoId(Long empleadoId);
	public Empleado updateEmpleado(Empleado empleado);
	public void deleteEmpleado(Long empleadoId);
	public ArrayList<Empleado> findByComercioId(Long comercioId);
}
