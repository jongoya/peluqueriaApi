package com.example.demo.services;

import com.example.demo.Models.EstiloPublico;


public interface IEstiloPublicoService {
	public EstiloPublico saveEstilo(EstiloPublico estilo);
	public EstiloPublico findByAndroidBundleId(String androidBundleId);
	public EstiloPublico findByIosBundleId(String iosBundleId);
	public EstiloPublico findByEstiloId(Long estiloId);
	public EstiloPublico findByNombreApp(String nombreApp);
	public void deleteEstilo(EstiloPublico estiloPublico);
}
