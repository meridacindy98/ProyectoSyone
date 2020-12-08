package com.example.ProyectoSysone.model;

import java.util.List;

public class Estadistica {

	private int cantidadAutomoviles;
	private List<Automoviles> automovilList;
	private List<Opcionales> opcionalList;
	
	public int getCantidadAutomoviles() {
		return cantidadAutomoviles;
	}
	public void setCantidadAutomoviles(int cantidadAutomoviles) {
		this.cantidadAutomoviles = cantidadAutomoviles;
	}
	public List<Automoviles> getAutomovilList() {
		return automovilList;
	}
	public void setAutomovilList(List<Automoviles> automovilList) {
		this.automovilList = automovilList;
	}
	public List<Opcionales> getOpcionalList() {
		return opcionalList;
	}
	public void setOpcionalList(List<Opcionales> opcionalList) {
		this.opcionalList = opcionalList;
	}
	
	
	
}
