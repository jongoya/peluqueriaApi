package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Models.EstiloPublico;

public interface IEstiloPublicoDao extends CrudRepository<EstiloPublico, Long> {
	public Optional<EstiloPublico> findByAndroidBundleId(String androidBundleId);
	public Optional<EstiloPublico> findByIosBundleId(String iosBundleId);
	public Optional<EstiloPublico> findByEstiloId(Long estiloId);
	public Optional<EstiloPublico> findByNombreApp(String nombreApp);
}
