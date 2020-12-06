package com.example.ProyectoSysone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.AutomovilOpcional;

@Repository
public interface AutomovilOpcionalDao extends JpaRepository<AutomovilOpcional, Integer> {
	
	List< AutomovilOpcional > findByAutomovilAutomovilId( int automovilId );
	
}
