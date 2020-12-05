package com.example.ProyectoSysone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.entity.TipoAuto;
import com.example.ProyectoSysone.service.TipoAutoService;

@RestController
public class TipoAutoController {

	@Autowired
	private TipoAutoService tipoAutoService;
	
	@GetMapping("/tiposAuto")
	List<TipoAuto> filAllTipoAuto(){
		return tipoAutoService.findAll();
	}
	
}
