package com.example.ProyectoSysone.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.Opcional;

@Repository
public interface OpcionalDao extends JpaRepository<Opcional, Integer>{
	
	@Query("SELECT precio FROM Opcional WHERE opcional_Id = :opcionalId")
	BigDecimal findPrecioByOpcionalId(int opcionalId);
	
//	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Opcional WHERE cantidad > 0 and opcional_Id = :opcionalId")
//	Boolean validateStockOpcional(int opcionalId);
	
}
