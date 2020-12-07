package com.example.ProyectoSysone.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOAUTO")
public class TipoAuto {
	
	@Id
    @GeneratedValue( strategy = GenerationType.AUTO )
	private int tipoAutoId;
	
	@Column(length = 50)
	private String nombre;
	
	@Column(nullable = false )
	private BigDecimal precio;
	
	@Column(nullable = false )
	private int cantidad;

	public TipoAuto(String nombre, BigDecimal precio, int cantidad) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
