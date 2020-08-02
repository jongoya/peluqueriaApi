package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.EstiloPublico;
import com.example.demo.dao.IEstiloPublicoDao;

@Service
public class EstiloPublicoServiceImpl implements IEstiloPublicoService {
	
	@Autowired
	private IEstiloPublicoDao estiloPublicoDao;

	@Override
	public EstiloPublico saveEstilo(EstiloPublico estilo) {
		return estiloPublicoDao.save(estilo);
	}

	@Override
	public EstiloPublico findByAndroidBundleId(String androidBundleId) {
		return estiloPublicoDao.findByAndroidBundleId(androidBundleId).orElse(null);
	}

	@Override
	public EstiloPublico findByIosBundleId(String iosBundleId) {
		return estiloPublicoDao.findByIosBundleId(iosBundleId).orElse(null);
	}

	@Override
	public EstiloPublico findByEstiloId(Long estiloId) {
		return estiloPublicoDao.findByEstiloId(estiloId).orElse(null);
	}

	@Override
	public EstiloPublico findByNombreApp(String nombreApp) {
		return estiloPublicoDao.findByNombreApp(nombreApp).orElse(null);
	}

	@Override
	public void deleteEstilo(EstiloPublico estiloPublico) {
		estiloPublicoDao.delete(estiloPublico);
	}
}
