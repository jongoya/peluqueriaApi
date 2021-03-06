package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.CierreCaja;

public interface ICierreCajaDao extends CrudRepository<CierreCaja, Long> {
	public Optional<CierreCaja> findByCajaId(Long cajaId);
	public ArrayList<CierreCaja> findByComercioId(Long comercioId);
}
