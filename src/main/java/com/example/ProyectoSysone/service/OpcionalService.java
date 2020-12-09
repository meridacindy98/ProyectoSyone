package com.example.ProyectoSysone.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.OpcionalDao;
import com.example.ProyectoSysone.entity.Opcional;

@Service
public class OpcionalService  {

	@Autowired
	private OpcionalDao opcionalDao;

	public BigDecimal findPrecioByOpcionalId(int opcionalId){
		return opcionalDao.findPrecioByOpcionalId(opcionalId);
	}
	
	public Opcional findOpcionalById(int opcionalId){
		return opcionalDao.findById(opcionalId).get();
	}
	
	public boolean existById(int opcionalId){
		return opcionalDao.existsById(opcionalId);
	}
		
	public List<Opcional> findAll(){
		return opcionalDao.findAll();
	}
		
}
