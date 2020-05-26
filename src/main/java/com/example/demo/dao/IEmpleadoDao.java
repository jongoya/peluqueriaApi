package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Empleado;

public interface IEmpleadoDao extends CrudRepository<Empleado, Long> {
	public Optional<Empleado> findByEmpleadoId(Long empleadoId);

}
