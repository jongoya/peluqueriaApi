package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.Models.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {
	public Optional<Cliente> findById(Long clienteId);
	public ArrayList<Cliente> findByComercioId(Long comercioId);
}
