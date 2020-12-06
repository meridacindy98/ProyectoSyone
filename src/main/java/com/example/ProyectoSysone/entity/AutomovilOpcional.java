package com.example.ProyectoSysone.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public class AutomovilOpcional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int automovilOpcionalId;	
	
	@ManyToOne( optional = false )
	@JoinColumn(name="automovilId")
	private Automovil automovil;
		
	@ManyToOne( optional = true )
	@JoinColumn(name="opcionalId")
	private Opcional opcional;

	public AutomovilOpcional(Automovil automovil, Opcional opcional) {
		this.automovil = automovil;
		this.opcional = opcional;
	}
	
	public AutomovilOpcional() {}

	public int getAutomovilOpcionalId() {
		return automovilOpcionalId;
	}

	public void setAutomovilOpcionalId(int automovilOpcionalId) {
		this.automovilOpcionalId = automovilOpcionalId;
	}

	public Automovil getAutomovil() {
		return automovil;
	}

	public void setAutomovil(Automovil automovil) {
		this.automovil = automovil;
	}

	public Opcional getOpcional() {
		return opcional;
	}

	public void setOpcional(Opcional opcional) {
		this.opcional = opcional;
	}

	
	
	
}
