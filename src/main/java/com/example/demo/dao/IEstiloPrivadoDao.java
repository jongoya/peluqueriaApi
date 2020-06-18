package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.EstiloPrivado;

public interface IEstiloPrivadoDao extends CrudRepository<EstiloPrivado, Long> {
	public Optional<EstiloPrivado> findByEstiloId(Long estiloId);
	public EstiloPrivado findByComercioId(Long comercioId);
}
