package com.example.ProyectoSysone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.AutomovilOpcional;

@Repository
public interface AutomovilOpcionalDao extends JpaRepository<AutomovilOpcional, Integer> {
	
	List< AutomovilOpcional > findByAutomovilAutomovilId( int automovilId );
	
	@Query(value = "SELECT * FROM AutomovilOpcional WHERE automovilId = :automovilId AND opcionalId = :opcionalId", nativeQuery = true)
	AutomovilOpcional findByAutomovilIdAndOpcionalId( int automovilId, int opcionalId );
	
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM AutomovilOpcional WHERE automovilId = :automovilId AND opcionalId = :opcionalId")
	Boolean validateAutomovilAutomovilId( int automovilId,  int opcionalId);
	
	@Query("SELECT COUNT(*) FROM AutomovilOpcional WHERE opcionalId = :opcionalId")
	int getCountAutomovilOpcionalByOpcionalId( int opcionalId );
	
	@Query( value = "SELECT opcional_Id FROM AutomovilOpcional WHERE automovil_Id = :automovilId", nativeQuery = true )
	List<Integer> getOpcionalIdListByAutomovilId( int automovilId );
}
