package com.example.ProyectoSysone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.model.RequestPostAutomovil;
import com.example.ProyectoSysone.model.ResponsePostAutomovil;
import com.example.ProyectoSysone.service.AutomovilOpcionalService;
import com.example.ProyectoSysone.service.AutomovilService;

@RestController
public class AutomovilController {

	@Autowired
	private AutomovilService automovilService;

	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;

	@GetMapping("/automoviles")
	public ResponseEntity<List<Automovil>> getAllAutomoviles() {

		if (automovilService.getAllAutomoviles().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(automovilService.getAllAutomoviles(), HttpStatus.OK);

	}

	// @GetMapping("/automoviles/{id}")

	@PostMapping(path = "/automovil", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponsePostAutomovil> saveAutomovil(@RequestBody RequestPostAutomovil request) {

			ResponsePostAutomovil response = new ResponsePostAutomovil();
			response.setAutomovil(automovilService.save(request.getTipoAutoId(), request.getOpcionalList()));
			response.setOpcionalList(
					automovilOpcionalService.getOpcionalListByAutomovilId(response.getAutomovil().getAutomovilId()));

			return new ResponseEntity<>(response, HttpStatus.CREATED);		
	}

	@DeleteMapping("/automovil/{id}")
	public ResponseEntity<HttpStatus> deleteAutomovilById(@PathVariable("id") int id) {
		automovilService.deleteAutomovil(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}		
	
	@PutMapping(value="/automovil/{automovilId}/{tipoAutoId}")
	public ResponseEntity<Automovil> updateTipoAutoAutomovil(@PathVariable("automovilId") int automovilId, @PathVariable("tipoAutoId") int tipoAutoId ) {		
		return new ResponseEntity<>( automovilService.updateTipoAuto(automovilId, tipoAutoId),HttpStatus.OK);
	}	 

	@ExceptionHandler(IllegalArgumentException.class)
	public String handleException(IllegalArgumentException ex) {
//	    ModelAndView modelAndView = new ModelAndView();
//	    modelAndView.setViewName("error");
//	    modelAndView.addObject("message", ex.getMessage());
		return ex.getMessage();
	}

}
