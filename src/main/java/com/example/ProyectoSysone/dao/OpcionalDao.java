package com.example.ProyectoSysone.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.Opcional;

@Repository
public interface OpcionalDao extends JpaRepository<Opcional, Integer>{
	
	@Query("SELECT precio FROM Opcional WHERE opcionalId = :opcionalId")
	BigDecimal findPrecioByOpcionalId(@Param("opcionalId") int opcionalId);
	

}
