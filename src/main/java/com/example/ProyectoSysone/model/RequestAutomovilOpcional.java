package com.example.ProyectoSysone.model;

import java.util.List;

public class RequestAutomovilOpcional {
	
	private int automovilId;
	private List<Integer> opcionalIdList;
	
	public int getAutomovilId() {
		return automovilId;
	}
	public void setAutomovilId(int automovilId) {
		this.automovilId = automovilId;
	}
	public List<Integer> getOpcionalIdList() {
		return opcionalIdList;
	}
	public void setOpcionalIdList(List<Integer> opcionalIdList) {
		this.opcionalIdList = opcionalIdList;
	}
	
	
}
