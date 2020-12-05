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
@Table
public class Automovil {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int automovilId;
	
	@ManyToOne( optional = false )
	@JoinColumn(name="tipoAutoId")
	private TipoAuto tipoAuto;
	
	@Column(nullable = false)
	private int precioFinal;

	public int getAutomovilId() {
		return automovilId;
	}

	public void setAutomovilId(int automovilId) {
		this.automovilId = automovilId;
	}

	public TipoAuto getTipoAuto() {
		return tipoAuto;
	}

	public void setTipoAuto(TipoAuto tipoAuto) {
		this.tipoAuto = tipoAuto;
	}

	public int getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(int precioFinal) {
		this.precioFinal = precioFinal;
	}
	
}
