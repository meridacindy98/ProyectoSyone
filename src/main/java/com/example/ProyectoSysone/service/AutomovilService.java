package com.example.ProyectoSysone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilDao;
import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.entity.TipoAuto;

@Service
public class AutomovilService implements Services<Automovil, Integer> {

	@Autowired
	private AutomovilDao automovilDao;
	
	@Override
	public void save(Automovil automovil) {
		automovilDao.save(automovil);	
	}

	@Override
	public void delete(Automovil automovil) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Automovil automovil) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<TipoAuto> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Automovil> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
