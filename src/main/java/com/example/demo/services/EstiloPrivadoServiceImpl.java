package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.EstiloPrivado;
import com.example.demo.dao.IEstiloPrivadoDao;

@Service
public class EstiloPrivadoServiceImpl implements IEstiloPrivadoService {
	
	@Autowired
	private IEstiloPrivadoDao estiloPrivadoDao;

	@Override
	public EstiloPrivado saveEstilo(EstiloPrivado estilo) {
		return estiloPrivadoDao.save(estilo);
	}

	@Override
	public EstiloPrivado findByEstiloId(Long estiloId) {
		return estiloPrivadoDao.findByEstiloId(estiloId).orElse(null);
	}

	@Override
	public EstiloPrivado findByComercioId(Long comercioId) {
		return estiloPrivadoDao.findByComercioId(comercioId);
	}

	@Override
	public void deleteEstilo(EstiloPrivado estiloPrivado) {
		estiloPrivadoDao.delete(estiloPrivado);
	}

	@Override
	public EstiloPrivado updateEstilo(EstiloPrivado estiloPrivado) {
		return estiloPrivadoDao.save(estiloPrivado);
	}
}
