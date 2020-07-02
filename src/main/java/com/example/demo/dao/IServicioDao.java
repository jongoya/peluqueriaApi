package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Models.Servicio;

public interface IServicioDao extends CrudRepository<Servicio, Long> {
	public Optional<Servicio> findByServiceId(Long serviceId);
	public List<Servicio> findByClientId(Long clientId);
	public List<Servicio> findByEmpleadoId(Long empleadoId);
	public ArrayList<Servicio> findByComercioId(Long comercioId);
	
	@Query(value = "select s from Servicio s where s.comercioId = :comercioId AND s.fecha > :fechaInicio AND s.fecha < :fechaFin")
	public List<Servicio> findByComercioIdAndFecha(@Param("comercioId")Long comercioId, @Param("fechaInicio")Long fechaInicio, @Param("fechaFin")Long fechaFin);
}
