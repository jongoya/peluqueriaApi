package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.Dispositivo;

public interface IDispositivoDao extends CrudRepository<Dispositivo, Long> {
	public Optional<Dispositivo> findByDispositivoId(Long dispositivoId);
	public ArrayList<Dispositivo>findByComercioId(Long comercioId);
	public Optional<Dispositivo> findByUniqueDeviceId(String unique_deviceId);
}
