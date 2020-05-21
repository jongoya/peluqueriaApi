package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Models.CierreCaja;
import com.example.demo.dao.ICierreCajaDao;

@Service
public class CierreCajaServiceImpl implements ICierreCajaService {
	
	@Autowired
	public ICierreCajaDao cierreCajaDao;

	@Override
	@Transactional(readOnly = true)
	public ArrayList<CierreCaja> findAll() {
		return (ArrayList<CierreCaja>) cierreCajaDao.findAll();
	}

	@Override
	@Transactional
	public ArrayList<CierreCaja> saveCierreCajas(ArrayList<CierreCaja> cierreCajas) {
		return (ArrayList<CierreCaja>) cierreCajaDao.saveAll(cierreCajas);
	}

	@Override
	@Transactional
	public CierreCaja saveCierreCaja(CierreCaja cierreCaja) {
		return (CierreCaja) cierreCajaDao.save(cierreCaja);
	}
}
