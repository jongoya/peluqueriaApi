package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Models.TipoServicio;
import com.example.demo.dao.ITipoServicioDao;

@Service
public class TipoServicioServiceImpl implements ITipoServicioService {
	
	@Autowired
	public ITipoServicioDao tipoServicioDao;

	@Override
	@Transactional(readOnly = true)
	public ArrayList<TipoServicio> findAll() {
		return(ArrayList<TipoServicio>) tipoServicioDao.findAll();
	}

	@Override
	@Transactional
	public TipoServicio saveTipoServicio(TipoServicio servicio) {
		return (TipoServicio) tipoServicioDao.save(servicio);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoServicio findByTipoServicioId(Long id) {
		return (TipoServicio) tipoServicioDao.findByServicioId(id).orElse(null);
	}
}
