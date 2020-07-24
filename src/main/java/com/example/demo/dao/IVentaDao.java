package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.Models.Venta;

public interface IVentaDao extends CrudRepository<Venta, Long> {
	public Optional<Venta> findByVentaId(Long ventaId);
	public ArrayList<Venta> findByComercioId(Long comercioId);
	public ArrayList<Venta> findByCestaId(Long cestaId);

}
