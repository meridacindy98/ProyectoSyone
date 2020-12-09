package com.example.ProyectoSysone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;
import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.entity.AutomovilOpcional;
import com.example.ProyectoSysone.entity.Opcional;
import com.example.ProyectoSysone.model.ResponseAutomovilOpcional;

@Service
public class AutomovilOpcionalService {

	@Autowired
	private AutomovilOpcionalDao automovilOpcionalDao;
	
	@Autowired
	private AutomovilService automovilService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;
	
	
	public AutomovilOpcional save( AutomovilOpcional automovilOpcional ) {
		return automovilOpcionalDao.save(automovilOpcional);
	}
		
	public void deleteAutomovilOpcional( AutomovilOpcional automovilOpcional ) {
		automovilOpcionalDao.delete(automovilOpcional);
	}
		
	public ResponseAutomovilOpcional deleteAutomovilOpcionalByAutomovilId( int automovilId, List<Integer> opcionalIdList ) {			
		
		
		automovilService.getAutomovilByIdAndValidate( automovilId );
				
		opcionalIdList.stream().forEach( opcionalId -> {
			if ( !automovilOpcionalDao.validateOpcionalByAutomovilId(automovilId, opcionalId) ) {
				throw new IllegalArgumentException("Uno de los opcionales ingresados no existe para este automovil");
			}
		} );
		
		for (Integer opcionalId : opcionalIdList) {
			AutomovilOpcional automovilOpcional = automovilOpcionalDao.findByAutomovilIdAndOpcionalId(automovilId, opcionalId);
			automovilOpcionalDao.delete(automovilOpcional);
		}
		
		automovilService.updatePrecioFInal(automovilId);
		
		ResponseAutomovilOpcional response = new ResponseAutomovilOpcional();
		response.setAutomovil( automovilService.getAutomovilByIdAndValidate(automovilId) );		
		response.setOpcionalList( automovilOpcionalService.getOpcionalListByAutomovilId(automovilId) );
		
		return response;

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
	
	public ResponseAutomovilOpcional addAutomovilOpcionalByAutomovilId(int automovilId, List<Integer> opcionalList) {
		
		Automovil automovil =  automovilService.getAutomovilByIdAndValidate(automovilId);
		automovilService.validateOpcionales(opcionalList);
		
		opcionalList.stream().forEach( opcionalId -> {
			if ( automovilOpcionalDao.validateOpcionalByAutomovilId(automovilId, opcionalId) ) {
				throw new IllegalArgumentException("Uno de los opcionales ingresados ya existe para este automovil");
			}
		} );
		
		for (Integer opcionalId : opcionalList) {
			Opcional opcional = opcionalService.findOpcionalById(opcionalId.intValue());
			AutomovilOpcional automovilOpcional = new AutomovilOpcional(automovil, opcional);
			automovilOpcionalDao.save(automovilOpcional);
		}
		
		automovilService.updatePrecioFInal(automovilId);

		ResponseAutomovilOpcional response = new ResponseAutomovilOpcional();
		response.setAutomovil( automovilService.getAutomovilByIdAndValidate(automovilId) );		
		response.setOpcionalList( automovilOpcionalService.getOpcionalListByAutomovilId(automovilId) );
		
		return response;
	}
	
	
	
}
