package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.Models.Cesta;

public interface ICestaDao extends CrudRepository<Cesta, Long> {
	public Optional<Cesta> findByCestaId(Long cestaId);
	public ArrayList<Cesta> findByComercioId(Long comercioId);
}
