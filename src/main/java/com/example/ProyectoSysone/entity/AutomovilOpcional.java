package com.example.ProyectoSysone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "automovilopcional")
public class AutomovilOpcional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="automovil_Opcional_Id")
	private int automovilOpcionalId;	
	
	@ManyToOne( optional = false )
	@JoinColumn(name="automovil_Id")
	private Automovil automovil;
		
	@ManyToOne( optional = true )
	@JoinColumn(name="opcional_Id")
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
