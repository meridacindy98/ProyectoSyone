package com.example.ProyectoSysone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;
import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.entity.AutomovilOpcional;
import com.example.ProyectoSysone.entity.Opcional;

@Service
public class AutomovilOpcionalService {

	@Autowired
	private AutomovilOpcionalDao automovilOpcionalDao;
	
	@Autowired
	private AutomovilService automovilService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	
	public AutomovilOpcional save( AutomovilOpcional automovilOpcional ) {
		return automovilOpcionalDao.save(automovilOpcional);
	}
	
	public void deleteAutomovilOpcional( AutomovilOpcional automovilOpcional ) {
		automovilOpcionalDao.delete(automovilOpcional);
	}
		
	public void deleteAutomovilOpcionalByAutomovilIdAndOpcinalList( int automovilId, List<Integer> opcionalIdList ) {			
		
		
		automovilService.getAutomovilById( automovilId );
				
		opcionalIdList.stream().forEach( opcionalId -> {
			if ( !automovilOpcionalDao.validateAutomovilAutomovilId(automovilId, opcionalId) ) {
				throw new IllegalArgumentException("Uno de los opcionales ingresados no existe para este automovil");
			}
		} );
		
		for (Integer opcionalId : opcionalIdList) {
			AutomovilOpcional automovilOpcional = automovilOpcionalDao.findByAutomovilIdAndOpcionalId(automovilId, opcionalId);
			automovilOpcionalDao.delete(automovilOpcional);
		}
		
		automovilService.updatePrecioFInal(automovilId);

	}
		
	public List<AutomovilOpcional> getAutomovilOpcionalByAutomovilId( int automovilId ) {
		return automovilOpcionalDao.findByAutomovilAutomovilId(automovilId);
	}
	
	public int getCountAutomovilOpcionalByOpcionalId( int opcionalId ) {
		return automovilOpcionalDao.getCountAutomovilOpcionalByOpcionalId(opcionalId);
	}
	
	public Long getCountAllAutomovilOpcional() {
		return automovilOpcionalDao.count();
	}
	
	public List<Opcional> getOpcionalListByAutomovilId( int automovilId ){
		List<Integer> opcionalIdList =  automovilOpcionalDao.getOpcionalIdListByAutomovilId(automovilId);
		
		List<Opcional> opcionalList = new ArrayList<Opcional>();
		
		for ( Integer opcionalId : opcionalIdList ) {
			opcionalList.add( opcionalService.findOpcionalById(opcionalId) );			
		}
		
		return opcionalList;
	}
	
	
}
