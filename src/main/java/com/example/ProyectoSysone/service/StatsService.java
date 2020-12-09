package com.example.ProyectoSysone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.entity.Opcional;
import com.example.ProyectoSysone.entity.TipoAuto;
import com.example.ProyectoSysone.model.Cars;
import com.example.ProyectoSysone.model.Stat;
import com.example.ProyectoSysone.model.Opcionals;

@Service
public class StatsService {
	
	@Autowired
	private AutomovilService automovilService;
	
	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;
	
	@Autowired
	private TipoAutoService tipoAutoService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	public Stat getStats() {
		Stat stat = new Stat();
				
		int countAutomovil =  automovilService.getCountAllAutomovil().intValue();		
		List<TipoAuto> tipoAutoList = tipoAutoService.findAll();
		List<Cars> automovilesList = new ArrayList<Cars>();
		for ( TipoAuto tipoAuto: tipoAutoList ) {
			Cars automoviles = new Cars();
			automoviles.setModel( tipoAuto.getNombre() );
			automoviles.setCount( automovilService.getCountAutomovilesByTipoAutoId( tipoAuto.getTipoAutoId() ) );
			automoviles.setPercent( calculatePercentage( automoviles.getCount(), countAutomovil) );
			automovilesList.add(automoviles);
		}			
		stat.setCars(automovilesList);
		
		int countAutomovilOpcional =  automovilOpcionalService.getCountAllAutomovilOpcional().intValue();
		List<Opcional> opcionalList = opcionalService.findAll();
		List<Opcionals> opcionalesList = new ArrayList<Opcionals>();
		for ( Opcional opcional: opcionalList ) {
			Opcionals opcionales = new Opcionals();
			opcionales.setOptional( opcional.getNombre() );
			opcionales.setCount( automovilOpcionalService.getCountAutomovilOpcionalByOpcionalId( opcional.getOpcionalId() ) );
			opcionales.setPercent( calculatePercentage( opcionales.getCount() , countAutomovilOpcional) );
			opcionalesList.add(opcionales);
		}		
		stat.setOptionals(opcionalesList);
		
		stat.setCount_car(countAutomovil);
		
		return stat;
		
	}
	
	 private double calculatePercentage(int obtained, int total) {
		 if ( total != 0 ) {
			 return obtained * 100 / total; 
		 }
	      return 0;  
	 }
	
}
