package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.Dispositivo;
import com.example.demo.Models.Servicio;

public interface IDispositivoService {
	public Dispositivo save(Dispositivo dispositivo);
	public Dispositivo findByDispositivoId(Long dispositivoId);
	public ArrayList<Dispositivo> findByComercioId(Long comercioId);
	public void deleteDispositivo(Long dispositivoId);
	public Dispositivo findByUniqueDeviceId(String uniqueDeviceId);
}
