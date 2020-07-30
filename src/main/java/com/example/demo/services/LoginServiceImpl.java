package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Login;
import com.example.demo.dao.ILoginDao;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired 
	private ILoginDao loginDao;

	@Override
	public Login save(Login login) {
		return loginDao.save(login);
	}

	@Override
	public Login findByComercioId(Long comercioId) {
		return loginDao.findByComercioId(comercioId).orElse(null);
	}

	@Override
	public Login findByUsuario(String usuario) {
		return loginDao.findByUsuario(usuario).orElse(null);
	}

	@Override
	public Login updateLogin(Login login) {
		return loginDao.save(login);
	}

	@Override
	public ArrayList<Login> findAllLogins() {
		return (ArrayList<Login>) loginDao.findAll();
	}
}
