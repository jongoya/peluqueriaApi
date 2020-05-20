package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.TipoServicio;

public interface ITipoServicioDao extends CrudRepository<TipoServicio, Long> {
	public Optional<TipoServicio> findByServicioId(Long servicio_id);
}
