package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Models.Empleado;
import com.example.demo.dao.IEmpleadoDao;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Override
	public ArrayList<Empleado> findAll() {
		return (ArrayList<Empleado>) empleadoDao.findAll();
	}

	@Override
	public Empleado saveEmpleado(Empleado empleado) {
		return (Empleado) empleadoDao.save(empleado);
	}

	@Override
	public Empleado findByEmpleadoId(Long empleadoId) {
		return (Empleado) empleadoDao.findByEmpleadoId(empleadoId).orElse(null);
	}

	@Override
	public Empleado updateEmpleado(Empleado empleado) {
		return (Empleado) empleadoDao.save(empleado);
	}

	@Override
	public void deleteEmpleado(Long empleadoId) {
		empleadoDao.deleteById(empleadoId);
	}
}
