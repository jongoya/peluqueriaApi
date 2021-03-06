package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.TipoServicio;

public interface ITipoServicioService {
	public ArrayList<TipoServicio> findAll();
	public TipoServicio saveTipoServicio(TipoServicio servicio);
	public ArrayList<TipoServicio> saveTipoServicios(ArrayList<TipoServicio> tipoServicios);
	public TipoServicio findByTipoServicioId(Long id);
	public ArrayList<TipoServicio> findByComercioId(Long comercioId);
	public void deleteTipoServicios(ArrayList<TipoServicio> tipoServicios);
}
