package com.example.ProyectoSysone.model;

import java.util.List;

public class RequestPost {

	private int tipoAutoId;
	private List<Integer> opcionalList;
	
	public int getTipoAutoId() {
		return tipoAutoId;
	}
	public void setTipoAutoId(int tipoAutoId) {
		this.tipoAutoId = tipoAutoId;
	}
	public List<Integer> getOpcionalList() {
		return opcionalList;
	}
	public void setOpcionalList(List<Integer> opcionalList) {
		this.opcionalList = opcionalList;
	}
	
}
