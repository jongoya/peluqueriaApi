package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.Models.ProductoModel;

public interface IProductoDao extends CrudRepository<ProductoModel, Long>  {
	public Optional<ProductoModel> findByProductoId(Long productoId);
	public Optional<ProductoModel> findByCodigoBarras(String codigoBarras);
	public ArrayList<ProductoModel> findByComercioId(Long comercioId);
}
