package com.example.ProyectoSysone.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.Opcional;

@Repository
public interface OpcionalDao extends JpaRepository<Opcional, Integer>{
	
	@Query(value = "SELECT precio FROM opcional WHERE opcional_Id = :opcionalId", nativeQuery = true)
	BigDecimal findPrecioByOpcionalId(int opcionalId);
		
}
