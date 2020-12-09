package com.example.ProyectoSysone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.model.RequestDeleteAutomovilOpcional;
import com.example.ProyectoSysone.service.AutomovilOpcionalService;

@RestController
public class AutomovilOpcionalController {

	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;
	
	@DeleteMapping("/automovilOpcional")
	public ResponseEntity<HttpStatus> deleteAutomovilById( @RequestBody RequestDeleteAutomovilOpcional request) {
		automovilOpcionalService.deleteAutomovilOpcionalByAutomovilIdAndOpcinalList( request.getAutomovilId() , request.getOpcionalIdList() );
		return new ResponseEntity<>(HttpStatus.OK);
	}		
	
	
	
}
