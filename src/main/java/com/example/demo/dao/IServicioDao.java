package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Servicio;

public interface IServicioDao extends CrudRepository<Servicio, Long> {
	public Optional<Servicio> findByServiceId(Long serviceId);
	public List<Servicio> findByClientId(Long clientId);
	public List<Servicio> findByEmpleadoId(Long empleadoId);
}
