package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Models.Servicio;
import com.example.demo.dao.IServicioDao;

@Service
public class ServicioServiceImpl implements IServicioService {

	@Autowired
	private IServicioDao servicioDao;

	@Override
	@Transactional(readOnly = true)
	public ArrayList<Servicio> findAll() {
		return (ArrayList<Servicio>) servicioDao.findAll();
	}

	@Override
	@Transactional
	public Servicio saveServicio(Servicio servicio) {
		return (Servicio)servicioDao.save(servicio);
	}

	@Override
	@Transactional
	public ArrayList<Servicio> saveServicios(ArrayList<Servicio> servicios) {
		return (ArrayList<Servicio>) servicioDao.saveAll(servicios);
	}

	@Override
	@Transactional(readOnly = true)
	public Servicio findByServicioId(Long servicioId) {
		return (Servicio) servicioDao.findByServiceId(servicioId).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<Servicio> findByClienteId(Long clienteId) {
		return (ArrayList<Servicio>)servicioDao.findByClientId(clienteId);
	}

	@Override
	@Transactional
	public Servicio updateServicio(Servicio servicio) {
		return (Servicio)servicioDao.save(servicio);
	}

	@Override
	@Transactional
	public void deleteServicio(Long servicioId) {
		servicioDao.deleteById(servicioId);
	}

	@Override
	public ArrayList<Servicio> findByProfesional(Long profesional) {
		return (ArrayList<Servicio>)servicioDao.findByEmpleadoId(profesional);
	}
}
