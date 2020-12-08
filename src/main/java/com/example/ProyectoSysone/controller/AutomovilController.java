package com.example.ProyectoSysone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.model.RequestPostAutomovil;
import com.example.ProyectoSysone.model.ResponsePostAutomovil;
import com.example.ProyectoSysone.service.AutomovilOpcionalService;
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
	
	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;
		
	@GetMapping("/automoviles")
	public List<Automovil> getAllAutomoviles(){
		return automovilService.getAllAutomoviles();
	}	
	
	@PostMapping( path = "/automovil", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponsePostAutomovil saveAutomovil( @RequestBody RequestPostAutomovil request ) {		
		
		ResponsePostAutomovil response = new ResponsePostAutomovil();
		
		if ( request.getTipoAutoId() != 0 ) {
			response.setAutomovil( automovilService.save( request.getTipoAutoId(), request.getOpcionalList() ) );
		} else {
			System.out.println("Se debe seleccionar si o si un tipo de auto");
		}	
		
		response.setOpcionalList( automovilOpcionalService.getOpcionalListByAutomovilId( response.getAutomovil().getAutomovilId() ) );
		
		return response;
	}
	
	
	
}
