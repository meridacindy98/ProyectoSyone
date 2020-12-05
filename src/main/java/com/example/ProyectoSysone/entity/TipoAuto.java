package com.example.ProyectoSysone.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TipoAuto {
	
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	private int tipoAutoId;
	
	@Column(length = 50)
	private String nombre;
	
	@Column(nullable = false )
	private BigDecimal precio;
	
	@Column(nullable = false )
	private int cantidad;
	
	
}
