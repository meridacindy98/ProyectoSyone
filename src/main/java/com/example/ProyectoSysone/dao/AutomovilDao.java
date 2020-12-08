package com.example.ProyectoSysone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.Automovil;

@Repository
public interface AutomovilDao extends JpaRepository<Automovil, Integer>{
				
	@Query("SELECT COUNT(*) FROM Automovil WHERE tipoAUtoId = :tipoAUtoId")
	int getCountAutomovilesByTipoAutoId( int tipoAUtoId );
		
}
