package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.Cesta;

public interface ICestaService {
	public ArrayList<Cesta> findAll();
	public Cesta saveCesta(Cesta cesta);
	public Cesta findByCestaId(Long cestaId);
	public Cesta updateCesta(Cesta cesta);
	public void deleteCesta(Long cestaId);
	public ArrayList<Cesta> findByComercioId(Long comercioId);

}
