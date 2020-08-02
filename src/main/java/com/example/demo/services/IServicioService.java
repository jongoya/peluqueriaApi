package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.Servicio;

public interface IServicioService {
	public ArrayList<Servicio> findAll();
	public Servicio saveServicio(Servicio servicio);
	public ArrayList<Servicio> saveServicios(ArrayList<Servicio> servicios);
	public Servicio findByServicioId(Long servicioId);
	public ArrayList<Servicio> findByClienteId(Long clienteId);
	public ArrayList<Servicio> findByProfesional(Long profesional);
	public Servicio updateServicio(Servicio servicio);
	public void deleteServicio(Long servicioId);
	public ArrayList<Servicio> findByComercioId(Long comercioId);
	public ArrayList<Servicio> findByRange(Long comercioId, Long fechaInicio, Long fechaFin);
	public void deleteServicios(ArrayList<Servicio> servicios);
}
