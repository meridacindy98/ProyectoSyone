package com.example.ProyectoSysone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ProyectoSysone.service.AutomovilOpcionalService;

public class AutomovilOpcionalController {

	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;
	
//	@DeleteMapping("/automovil/{automovilId}/{opcionalListId}")
//	public ResponseEntity<HttpStatus> deleteAutomovilById(@PathVariable("automovilId") int automovilId, @PathVariable List<Integer> opcionalListId) {
//		automovilOpcionalService.deleteAutomovilOpcionalByAutomovilIdAndOpcinalList(automovilId, opcionalListId);
//		return new ResponseEntity<>(HttpStatus.OK);
//	}		
	
	
	
}
