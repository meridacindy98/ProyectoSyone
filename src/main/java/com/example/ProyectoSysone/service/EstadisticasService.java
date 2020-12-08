package com.example.ProyectoSysone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.entity.Opcional;
import com.example.ProyectoSysone.entity.TipoAuto;
import com.example.ProyectoSysone.model.Automoviles;
import com.example.ProyectoSysone.model.Estadistica;
import com.example.ProyectoSysone.model.Opcionales;

@Service
public class EstadisticasService {
	
	@Autowired
	private AutomovilService automovilService;
	
	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;
	
	@Autowired
	private TipoAutoService tipoAutoService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	public Estadistica getEstadisticas() {
		Estadistica estadistica = new Estadistica();
				
		int countAutomovil =  automovilService.getCountAllAutomovil().intValue();		
		List<TipoAuto> tipoAutoList = tipoAutoService.findAll();
		List<Automoviles> automovilesList = new ArrayList<Automoviles>();
		for ( TipoAuto tipoAuto: tipoAutoList ) {
			Automoviles automoviles = new Automoviles();
			automoviles.setModelo( tipoAuto.getNombre() );
			automoviles.setCantidad( automovilService.getCountAutomovilesByTipoAutoId( tipoAuto.getTipoAutoId() ) );
			automoviles.setPorcentaje( calculatePercentage( automoviles.getCantidad(), countAutomovil) );
			automovilesList.add(automoviles);
		}			
		estadistica.setAutomovilList(automovilesList);
		
		int countAutomovilOpcional =  automovilOpcionalService.getCountAllAutomovilOpcional().intValue();
		List<Opcional> opcionalList = opcionalService.findAll();
		List<Opcionales> opcionalesList = new ArrayList<Opcionales>();
		for ( Opcional opcional: opcionalList ) {
			Opcionales opcionales = new Opcionales();
			opcionales.setOpcional( opcional.getNombre() );
			opcionales.setCantidad( automovilOpcionalService.getCountAutomovilOpcionalByOpcionalId( opcional.getOpcionalId() ) );
			opcionales.setPorcentaje( calculatePercentage( opcionales.getCantidad() , countAutomovilOpcional) );
			opcionalesList.add(opcionales);
		}		
		estadistica.setOpcionalList(opcionalesList);
		
		estadistica.setCantidadAutomoviles(countAutomovil);
		
		return estadistica;
		
	}
	
	 private double calculatePercentage(int obtained, int total) {
	        return obtained * 100 / total;
	 }
	
}
