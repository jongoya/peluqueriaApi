package com.example.demo.services.Utils;

import com.example.demo.Models.Cliente;

public class ServicesUtils {

	public static Cliente updateCliente(Cliente newCliente, Cliente serverCliente) {
		serverCliente.setNombre(newCliente.getNombre());
		serverCliente.setApellidos(newCliente.getApellidos());
		serverCliente.setFecha(newCliente.getFecha());
		serverCliente.setEmail(newCliente.getEmail());
		serverCliente.setDireccion(newCliente.getDireccion());
		serverCliente.setCadenciaVisita(newCliente.getCadenciaVisita());
		serverCliente.setImagen(newCliente.getImagen());
		serverCliente.setNotificacionPersonalizada(newCliente.getNotificacionPersonalizada());
		serverCliente.setObservaciones(newCliente.getObservaciones());
		serverCliente.setTelefono(newCliente.getTelefono());
		
		return serverCliente;
	}
}
