package com.example.ProyectoSysone.model;

import java.util.List;

import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.entity.Opcional;

public class ResponseAutomovilOpcional {
	
	private Automovil automovil;
	private List<Opcional> opcionalList;
	
	public Automovil getAutomovil() {
		return automovil;
	}
	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}
	public List<Opcional> getOpcionalList() {
		return opcionalList;
	}
	public void setOpcionalList(List<Opcional> opcionalList) {
		this.opcionalList = opcionalList;
	}	
	
}
