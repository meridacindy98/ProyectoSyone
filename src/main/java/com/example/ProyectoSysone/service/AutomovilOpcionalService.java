package com.example.ProyectoSysone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;

@Service
public class AutomovilOpcionalService {

	@Autowired
	private AutomovilOpcionalDao automovilOpcionalDao;
	
}
