package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.TipoServicio;

public interface ITipoServicioService {
	public ArrayList<TipoServicio> findAll();
	public TipoServicio saveTipoServicio(TipoServicio servicio);
	public TipoServicio findByTipoServicioId(Long id);
}
