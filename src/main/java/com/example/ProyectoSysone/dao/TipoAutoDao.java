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
	
}
