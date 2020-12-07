package com.example.ProyectoSysone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;
import com.example.ProyectoSysone.entity.AutomovilOpcional;

@Service
public class AutomovilOpcionalService {

	@Autowired
	private AutomovilOpcionalDao automovilOpcionalDao;
	
	public AutomovilOpcional save( AutomovilOpcional automovilOpcional ) {
		return automovilOpcionalDao.save(automovilOpcional);
	}
	
	public void deleteAutomovilOpcional( AutomovilOpcional automovilOpcional ) {
		automovilOpcionalDao.delete(automovilOpcional);
	}
	
}
