package com.example.ProyectoSysone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.model.RequestAutomovil;
import com.example.ProyectoSysone.model.ResponseAutomovilOpcional;
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

	@GetMapping("/automovil/{automovilId}")
	public ResponseEntity<ResponseAutomovilOpcional> getAutomovilById(@PathVariable("automovilId") int automovilId){
		ResponseAutomovilOpcional response = new ResponseAutomovilOpcional();
		response.setAutomovil( automovilService.getAutomovilByIdAndValidate(automovilId) );
		response.setOpcionalList( automovilOpcionalService.getOpcionalListByAutomovilId(automovilId) );
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/automovil", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseAutomovilOpcional> saveAutomovil(@RequestBody RequestAutomovil request) {

			ResponseAutomovilOpcional response = new ResponseAutomovilOpcional();
			response.setAutomovil(automovilService.save(request.getTipoAutoId(), request.getOpcionalList()));
			response.setOpcionalList(
					automovilOpcionalService.getOpcionalListByAutomovilId(response.getAutomovil().getAutomovilId()));

			return new ResponseEntity<>(response, HttpStatus.CREATED);		
	}

	@DeleteMapping("/automovil/{automovilId}")
	public ResponseEntity<HttpStatus> deleteAutomovilById(@PathVariable("automovilId") int automovilId) {

			automovilService.deleteAutomovil(automovilId);
			return new ResponseEntity<>(HttpStatus.OK);
						
	}		
	
	@PutMapping(value="/automovil/{automovilId}/{tipoAutoId}")
	public ResponseEntity<Automovil> updateTipoAutoAutomovil(@PathVariable("automovilId") int automovilId, @PathVariable("tipoAutoId") int tipoAutoId ) {		
		return new ResponseEntity<>( automovilService.updateTipoAuto(automovilId, tipoAutoId),HttpStatus.OK);
	}	 


}
