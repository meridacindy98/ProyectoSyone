package com.example.ProyectoSysone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.TipoAuto;

@Repository
public interface TipoAutoDao extends JpaRepository<TipoAuto, Integer> {
	
	@Query("SELECT precio FROM TipoAuto WHERE tipoAutoId = :tipoAutoId")
	int getPriceById(@Param("tipoAutoId") int tipoAutoId);
	
}
