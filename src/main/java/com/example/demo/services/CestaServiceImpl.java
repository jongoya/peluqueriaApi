package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Models.Cesta;
import com.example.demo.dao.ICestaDao;

@Service
public class CestaServiceImpl implements ICestaService {
	
	@Autowired
	private ICestaDao cestaDao;

	@Override
	public ArrayList<Cesta> findAll() {
		return (ArrayList<Cesta>) cestaDao.findAll();
	}

	@Override
	public Cesta saveCesta(Cesta cesta) {
		return cestaDao.save(cesta);
	}

	@Override
	public Cesta findByCestaId(Long cestaId) {
		return cestaDao.findByCestaId(cestaId).orElse(null);
	}

	@Override
	public Cesta updateCesta(Cesta cesta) {
		return cestaDao.save(cesta);
	}

	@Override
	public void deleteCesta(Long cestaId) {
		cestaDao.deleteById(cestaId);
	}

	@Override
	public ArrayList<Cesta> findByComercioId(Long comercioId) {
		return cestaDao.findByComercioId(comercioId);
	}
}
