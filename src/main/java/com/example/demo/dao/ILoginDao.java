package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.Models.Login;
import com.example.demo.Models.Notification;

public interface ILoginDao extends CrudRepository<Login, Long> {
	public Optional<Login> findByComercioId(Long comercioId);
	public Optional<Login> findByUsuario(String usuario);
}
