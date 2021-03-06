package com.example.ProyectoSysone.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "opcional")
public class Opcional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "opcional_Id" )
	private int opcionalId;
	
    @Column(length = 5)
	private String codigo;
	
    @Column(length = 50)
	private String nombre;
	
    @Column(nullable = false )
	private BigDecimal precio;
     
	public Opcional(String codigo, String nombre, BigDecimal precio) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public Opcional() {}

	public int getOpcionalId() {
		return opcionalId;
	}

	public void setOpcionalId(int opcionalId) {
		this.opcionalId = opcionalId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
