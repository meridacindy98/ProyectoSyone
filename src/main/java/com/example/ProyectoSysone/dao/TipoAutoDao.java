package com.example.ProyectoSysone.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.TipoAuto;

@Repository
public interface TipoAutoDao extends JpaRepository<TipoAuto, Integer> {
	
	@Query("SELECT precio FROM TipoAuto WHERE tipo_Auto_Id = :tipoAutoId")
	BigDecimal findPrecioByTipoAutoId(int tipoAutoId);
	
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM TipoAuto WHERE cantidad > 0 and tipo_Auto_Id = :tipoAutoId")
	Boolean validateStockTipoAuto(int tipoAutoId);
	
	
}
