package com.example.ProyectoSysone.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoSysone.entity.AutomovilOpcional;

@Repository
public interface AutomovilOpcionalDao extends JpaRepository<AutomovilOpcional, Integer> {
	
	List< AutomovilOpcional > findByAutomovilAutomovilId( int automovilId );
	
	@Query(value = "SELECT * FROM automovilopcional WHERE automovil_Id = :automovilId AND opcional_Id = :opcionalId", nativeQuery = true)
	AutomovilOpcional findByAutomovilIdAndOpcionalId( int automovilId, int opcionalId );
	
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM automovilopcional WHERE automovil_Id = :automovilId AND opcional_Id = :opcionalId")
	Boolean validateAutomovilAutomovilId( int automovilId,  int opcionalId);
	
	@Query("SELECT COUNT(*) FROM automovilopcional WHERE opcional_Id = :opcionalId")
	int getCountAutomovilOpcionalByOpcionalId( int opcionalId );
	
	@Query( value = "SELECT opcional_Id FROM automovilopcional WHERE automovil_Id = :automovilId", nativeQuery = true )
	List<Integer> getOpcionalIdListByAutomovilId( int automovilId );
}
