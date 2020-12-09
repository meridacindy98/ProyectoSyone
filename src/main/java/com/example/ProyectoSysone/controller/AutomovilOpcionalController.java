package com.example.ProyectoSysone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.model.RequestAutomovilOpcional;
import com.example.ProyectoSysone.model.ResponseAutomovilOpcional;
import com.example.ProyectoSysone.service.AutomovilOpcionalService;

@RestController
public class AutomovilOpcionalController {

	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;
	
	@DeleteMapping("/automovilOpcional")
	public ResponseEntity<ResponseAutomovilOpcional> deleteAutomovilById( @RequestBody RequestAutomovilOpcional request) {		
		return new ResponseEntity<>( automovilOpcionalService.deleteAutomovilOpcionalByAutomovilId( request.getAutomovilId() , request.getOpcionalIdList() ),HttpStatus.OK);
	}
	
	@PostMapping("/automovilOpcional")
	public ResponseEntity<ResponseAutomovilOpcional> addAutomovilOpcional( @RequestBody RequestAutomovilOpcional request) {		
		return new ResponseEntity<>( automovilOpcionalService.addAutomovilOpcionalByAutomovilId( request.getAutomovilId() , request.getOpcionalIdList() ), HttpStatus.OK);
	}		
	
	
	
	
	
}
