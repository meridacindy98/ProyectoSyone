package com.example.ProyectoSysone.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoauto")
public class TipoAuto {
	
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "tipo_Auto_Id" )
	private int tipoAutoId;
	
	@Column(length = 50)
	private String nombre;
	
	@Column(nullable = false )
	private BigDecimal precio;
	

	public TipoAuto(String nombre, BigDecimal precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public TipoAuto() {	}

	public int getTipoAutoId() {
		return tipoAutoId;
	}

	public void setTipoAutoId(int tipoAutoId) {
		this.tipoAutoId = tipoAutoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

}
