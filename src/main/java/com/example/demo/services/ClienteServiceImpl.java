package com.example.demo.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Models.Cliente;
import com.example.demo.dao.IClienteDao;
import com.example.demo.services.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public ArrayList<Cliente> findAll() {
		return (ArrayList<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return (Cliente) clienteDao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findByClienteId(Long clienteId) {
		return (Cliente) clienteDao.findById(clienteId).orElse(null);
	}

	@Override
	@Transactional
	public Cliente updateCliente(Cliente cliente) {
		return (Cliente) clienteDao.save(cliente);
	}
}
