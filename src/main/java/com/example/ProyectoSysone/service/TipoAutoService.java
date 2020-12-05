package com.example.ProyectoSysone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.TipoAutoDao;
import com.example.ProyectoSysone.entity.TipoAuto;

@Service
public class TipoAutoService implements Services<TipoAuto, Integer> {

	@Autowired
	private TipoAutoDao tipoAutoDao;
	
	@Override
	public void save(TipoAuto tipoAuto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(TipoAuto tipoAuto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TipoAuto tipoAuto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<TipoAuto> findById(Integer id) {
		return tipoAutoDao.findById(id);
	}

	@Override
	public List<TipoAuto> findAll() {
		return tipoAutoDao.findAll();
	}
	
	public int getPriceById(int tipoAutoId){
		return tipoAutoDao.getPriceById(tipoAutoId);
	}

}
