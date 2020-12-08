package com.example.ProyectoSysone.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Automovil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "automovilId")
    private int automovilId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipoAutoId")
    private TipoAuto tipoAuto;

    @Column(name = "precioFinal")
    private BigDecimal precioFinal;

    public Automovil( TipoAuto tipoAuto, BigDecimal precioFinal) {
        this.tipoAuto = tipoAuto;
        this.precioFinal = precioFinal;
    }
    
    public Automovil( ) {}

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

	public BigDecimal getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(BigDecimal precioFinal) {
		this.precioFinal = precioFinal;
	}
    
    

}
