package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.Models.Cliente;

public interface IClienteService {
	public ArrayList<Cliente> findAll();
	public Cliente save(Cliente cliente);
	public Cliente findByClienteId(Long clienteId);
	public Cliente updateCliente(Cliente cliente);
}
