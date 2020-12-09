package com.example.ProyectoSysone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.Automovil;

@Repository
public interface AutomovilDao extends JpaRepository<Automovil, Integer>{
				
	@Query(value = "SELECT COUNT(*) FROM automovil WHERE tipo_Auto_Id = :tipoAutoId", nativeQuery = true)
	int getCountAutomovilesByTipoAutoId( int tipoAutoId );
		
}
