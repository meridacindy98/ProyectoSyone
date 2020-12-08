package com.example.ProyectoSysone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;
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
		
		opcionalIdList.stream().forEach( opcionalId -> {
			if ( !automovilOpcionalDao.validateAutomovilAutomovilId(automovilId, opcionalId) ) {
				throw new IllegalArgumentException("Uno de los opcionales ingresados no existe para este automovil");
			}
		} );
		
		for (Integer opcionalId : opcionalIdList) {
			AutomovilOpcional automovilOpcional = automovilOpcionalDao.findByAutomovilAutomovilIdAndOpcionalId(automovilId, opcionalId);
			automovilOpcionalDao.delete(automovilOpcional);
			Opcional opcional =  opcionalService.findOpcionalById(opcionalId);
			opcionalService.updateMoreCantidadOpcional(opcional);
		}
		
		automovilService.updatePrecioFInal(automovilId);

	}
		
	public List<AutomovilOpcional> findByAutomovilId( int automovilId ) {
		return automovilOpcionalDao.findByAutomovilAutomovilId(automovilId);
	}
	
	
}
