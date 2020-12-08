package com.example.ProyectoSysone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.Automovil;

@Repository
public interface AutomovilDao extends JpaRepository<Automovil, Integer>{
			
	@Query("SELECT tipoAutoId FROM TipoAuto WHERE automovilId = :automovilId")
	int findTipoAutoIdByAutomovilId(int automovilId);
	
	
	
}
