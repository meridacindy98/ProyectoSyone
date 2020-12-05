package com.example.ProyectoSysone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.OpcionalDao;
import com.example.ProyectoSysone.entity.Opcional;
import com.example.ProyectoSysone.entity.TipoAuto;

@Service
public class OpcionalService implements Services<Opcional, Integer> {

	@Autowired
	private OpcionalDao opcionalDao;
	
	@Override
	public void save(Opcional opcional) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Opcional opcional) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Opcional opcional) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<TipoAuto> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Opcional> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getPriceById(int opcionalId){
		return opcionalDao.getPriceById(opcionalId);
	}
	
}
