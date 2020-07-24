package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Venta;
import com.example.demo.dao.IVentaDao;

@Service
public class VentaServiceImpl implements IVentaService {
	
	@Autowired
	private IVentaDao ventaDao;

	@Override
	public ArrayList<Venta> findAll() {
		return (ArrayList<Venta>) ventaDao.findAll();
	}

	@Override
	public Venta saveVenta(Venta venta) {
		return ventaDao.save(venta);
	}

	@Override
	public Venta findByVentaId(Long ventaId) {
		return ventaDao.findByVentaId(ventaId).orElse(null);
	}

	@Override
	public Venta updateVenta(Venta venta) {
		return ventaDao.save(venta);
	}

	@Override
	public void deleteVenta(Long ventaId) {
		ventaDao.deleteById(ventaId);
	}

	@Override
	public ArrayList<Venta> findByComercioId(Long comercioId) {
		return ventaDao.findByComercioId(comercioId);
	}

	@Override
	public ArrayList<Venta> findByCestaId(Long cestaId) {
		return ventaDao.findByCestaId(cestaId);
	}

	@Override
	public ArrayList<Venta> saveVentas(ArrayList<Venta> ventas) {
		return (ArrayList<Venta>) ventaDao.saveAll(ventas);
	}

	@Override
	public void deleteVentas(ArrayList<Venta> ventas) {
		ventaDao.deleteAll(ventas);
	}

}
