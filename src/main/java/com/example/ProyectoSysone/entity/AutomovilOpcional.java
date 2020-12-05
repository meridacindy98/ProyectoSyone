package com.example.ProyectoSysone.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class AutomovilOpcional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int autoMovilDetalleId;	
	
	@ManyToOne( optional = false )
	@JoinColumn(name="automovilId")
	private Automovil automovil;
		
	@ManyToOne( optional = true )
	@JoinColumn(name="opcionalId")
	private Opcional opcional;
}
