package com.example.ProyectoSysone.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilDao;
import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.entity.AutomovilOpcional;
import com.example.ProyectoSysone.entity.Opcional;
import com.example.ProyectoSysone.entity.TipoAuto;

@Service
public class AutomovilService {

	@Autowired
	private AutomovilDao automovilDao;

	@Autowired
	private TipoAutoService tipoAutoService;

	@Autowired
	private OpcionalService opcionalService;

	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;

	public Automovil save(int tipoAutoId, List<Integer> opcionalList) {
				
		TipoAuto tipoAuto = validateTipoAuto( tipoAutoId );

		if (opcionalList == null) {
			opcionalList = new ArrayList<>();
		}
		
		validateOpcionales(opcionalList);

		Automovil automovil = new Automovil(tipoAuto, calculateTotalPrice(tipoAutoId, opcionalList));
		automovil = automovilDao.save(automovil);

		for (Integer opcionalId : opcionalList) {
			Opcional opcional = opcionalService.findOpcionalById(opcionalId.intValue());
			AutomovilOpcional automovilOpcional = new AutomovilOpcional(automovil, opcional);
			automovilOpcionalService.save(automovilOpcional);
		}

		return automovil;
	}

	public void deleteAutomovil(int automovilId) {
		
		Automovil automovil;
		
		try {
			automovil =  automovilDao.findById( automovilId ).get();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("El automovil ingresado no existe", e);
		}
		
		List<AutomovilOpcional> automovilOpcionalList = automovilOpcionalService.getAutomovilOpcionalByAutomovilId(automovilId);
		for ( AutomovilOpcional automovilOpcional: automovilOpcionalList ) {
			automovilOpcionalService.deleteAutomovilOpcional(automovilOpcional);
		}
		
		automovilDao.delete(automovil);
	}
	
	public Automovil updateTipoAuto( int automovilId, int tipoAutoIdUpdate ) {
		
		Automovil automovil;		
		try {
			automovil = automovilDao.findById( automovilId ).get();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("El automovil ingresado no existe", e);
		}
								
		TipoAuto tipoAutoUpdate = validateTipoAuto( tipoAutoIdUpdate );
		
		automovil.setTipoAuto(tipoAutoUpdate);
		
		automovil = updatePrecioFInal( automovil.getAutomovilId() );
				
		return automovil;
	}
	
	public Automovil updatePrecioFInal( int automovilId ) {		
		Automovil automovil = automovilDao.findById( automovilId ).get();
		
		List<AutomovilOpcional> automovilOpcionalList = automovilOpcionalService.getAutomovilOpcionalByAutomovilId(automovilId);
		
		List<Integer> opcionalList = automovilOpcionalList.stream()
									 .map( automovilOpcional ->  automovilOpcional.getOpcional().getOpcionalId() )
									 .collect( Collectors.toList() );
		
		automovil.setPrecioFinal( calculateTotalPrice( automovil.getTipoAuto().getTipoAutoId(), opcionalList) );		
		
		return automovilDao.save(automovil);
	}
	
	public List <Automovil> getAllAutomoviles(){
		return automovilDao.findAll();
	}
	
	public int getCountAutomovilesByTipoAutoId( int tipoAUtoId ) {
		return automovilDao.getCountAutomovilesByTipoAutoId(tipoAUtoId);
	}
	
	public Long getCountAllAutomovil() {
		return automovilDao.count();
	}
	
	public Automovil getAutomovilByIdAndValidate( int automovilId ) {
		
		try {
			return automovilDao.findById( automovilId ).get(); 
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("El automovil ingresado no existe", e);
		}
		
	}
	
	private BigDecimal calculateTotalPrice(int tipoAutoId, List<Integer> opcionalList) {

		BigDecimal priceTipoAuto = BigDecimal.ZERO;
		BigDecimal priceOpcional = BigDecimal.ZERO;

		priceTipoAuto = tipoAutoService.findPrecioByTipoAutoId(tipoAutoId);

		priceOpcional = opcionalList.stream().map(opcional -> opcionalService.findPrecioByOpcionalId(opcional))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return priceTipoAuto.add(priceOpcional);

	}
	
	private void validateOpcionales ( List<Integer> opcionalList ) {
		
		opcionalList.stream().forEach(opcional -> {
			if (Collections.frequency(opcionalList, opcional) > 1) {
				throw new IllegalArgumentException("No se aceptan opcionales duplicados");
			}
		});
		
		opcionalList.stream().forEach(opcionalId -> {
			if (!opcionalService.existById(opcionalId.intValue())) {
				throw new IllegalArgumentException("Uno de los opcionales ingresados no existe.");
			}
		});
	}
	
	private TipoAuto validateTipoAuto( int tipoAutoId ) {
		
		if ( tipoAutoId == 0 ) {
			throw new IllegalArgumentException("Se debe seleccionar un tipo de auto.");
		}
		
		try {
			return tipoAutoService.findById(tipoAutoId);
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("El tipo de auto ingresado no existe.", e);
		}
		
	}
	
	
		
}
