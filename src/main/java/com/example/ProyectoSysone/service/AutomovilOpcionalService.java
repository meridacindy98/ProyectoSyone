package com.example.ProyectoSysone.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;
import com.example.ProyectoSysone.entity.AutomovilOpcional;

@Service
public class AutomovilOpcionalService {

	@Autowired
	private AutomovilOpcionalDao automovilOpcionalDao;
	
	public AutomovilOpcional save( AutomovilOpcional automovilOpcional ) {
		return automovilOpcionalDao.save(automovilOpcional);
	}
	
	public void deleteAutomovilOpcional( AutomovilOpcional automovilOpcional ) {
		automovilOpcionalDao.delete(automovilOpcional);
	}
		
	public void deleteAutomovilOpcionalByAutomovilIdAndOpcinalList( int automovilId, List<Integer> opcionalIdList ) {			
		
		opcionalIdList.stream().forEach( opcionalId -> {
			if ( !automovilOpcionalDao.validateAutomovilAutomovilId(automovilId, opcionalId) ) {
				throw new IllegalArgumentException("Uno de los opcionales ingresados no existe para este automovil");
			}
		} );
		
		for (Integer opcionalId : opcionalIdList) {
			AutomovilOpcional automovilOpcional = automovilOpcionalDao.findByAutomovilAutomovilIdAndOpcionalId(automovilId, opcionalId);
			automovilOpcionalDao.delete(automovilOpcional);
		}

	}
		
	public List<AutomovilOpcional> findByAutomovilAutomovilId( int automovilId ) {
		return automovilOpcionalDao.findByAutomovilAutomovilId(automovilId);
	}
	
	
}
