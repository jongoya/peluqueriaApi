package com.example.demo.services;

import com.example.demo.Models.EstiloPrivado;

public interface IEstiloPrivadoService {
	public EstiloPrivado saveEstilo(EstiloPrivado estilo);
	public EstiloPrivado findByEstiloId(Long estiloId);
	public EstiloPrivado findByComercioId(Long comercioId);
}
