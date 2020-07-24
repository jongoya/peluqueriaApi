package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.Venta;

public interface IVentaService {
	public ArrayList<Venta> findAll();
	public Venta saveVenta(Venta venta);
	public Venta findByVentaId(Long ventaId);
	public Venta updateVenta(Venta venta);
	public void deleteVenta(Long ventaId);
	public ArrayList<Venta> findByComercioId(Long comercioId);
	public ArrayList<Venta> findByCestaId(Long cestaId);
	public ArrayList<Venta> saveVentas(ArrayList<Venta> ventas);
	public void deleteVentas(ArrayList<Venta> ventas);
}
