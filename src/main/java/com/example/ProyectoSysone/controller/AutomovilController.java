package com.example.ProyectoSysone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.model.RequestPost;
import com.example.ProyectoSysone.service.AutomovilService;
import com.example.ProyectoSysone.service.OpcionalService;
import com.example.ProyectoSysone.service.TipoAutoService;

@RestController
public class AutomovilController {
		
	@Autowired
	private TipoAutoService tipoAutoService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	@Autowired
	private AutomovilService automovilService;
		
	//http://localhost:8080/automovil
	@PostMapping( path = "/automovil", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	RequestPost saveAutomovil( @RequestBody RequestPost request ) {		
		
		if ( request.getTipoAutoId() != 0 ) {
			//automovilService.save(automovil);
		} else {
			System.out.println("Se debe seleccionar si o si un tipo de auto");
		}	
		
		return request;
	}
	
	
	
}
