package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.Models.CierreCaja;

public interface ICierreCajaService {
	public ArrayList<CierreCaja> findAll();
	public ArrayList<CierreCaja> saveCierreCajas(ArrayList<CierreCaja> cierreCajas);
	public CierreCaja saveCierreCaja(CierreCaja cierreCaja);
	public ArrayList<CierreCaja> findByComercioId(Long comercioId);
	public void deleteCierreCaja(CierreCaja cierreCaja);
	public void deleteCierreCajas(ArrayList<CierreCaja> cierreCajas);
}
