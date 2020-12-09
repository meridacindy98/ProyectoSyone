package com.example.ProyectoSysone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.model.Stat;
import com.example.ProyectoSysone.service.StatsService;


@RestController
public class StatsController {
	
	@Autowired
	private StatsService statsService;
	
	@GetMapping("/stats")
	public ResponseEntity<Stat> getStats() {
		return new ResponseEntity<>(statsService.getStats(), HttpStatus.OK);
	}
	
	
}
