package com.example.ProyectoSysone.service;

import java.math.BigDecimal;

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
	
}
