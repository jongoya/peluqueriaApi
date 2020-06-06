package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Dispositivo;
import com.example.demo.dao.IDispositivoDao;

@Service
public class DispositivoServiceImpl implements IDispositivoService {
	
	@Autowired
	private IDispositivoDao dispositivoDao;

	@Override
	public Dispositivo save(Dispositivo dispositivo) {
		return dispositivoDao.save(dispositivo);
	}

	@Override
	public Dispositivo findByDispositivoId(Long dispositivoId) {
		return dispositivoDao.findByDispositivoId(dispositivoId).orElse(null);
	}

	@Override
	public ArrayList<Dispositivo> findByComercioId(Long comercioId) {
		return dispositivoDao.findByComercioId(comercioId);
	}

	@Override
	public void deleteDispositivo(Long dispositivoId) {
		dispositivoDao.deleteById(dispositivoId);
	}

	@Override
	public Dispositivo findByUniqueDeviceId(String uniqueDeviceId) {
		return dispositivoDao.findByUniqueDeviceId(uniqueDeviceId).orElse(null);
	}
}
