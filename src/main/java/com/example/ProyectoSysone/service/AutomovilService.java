package com.example.ProyectoSysone.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

		TipoAuto tipoAuto;

		try {
			tipoAuto = tipoAutoService.findById(tipoAutoId);
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("El tipo auto no existe.", e);
		}

		Assert.isTrue(tipoAutoService.validateStockTipoAuto(tipoAutoId), "El tipo de auto ingresado no tiene stock");

		if (opcionalList == null) {
			opcionalList = new ArrayList<>();
		}

		opcionalList.stream().forEach(opcionalId -> {
			if (!opcionalService.existById(opcionalId.intValue())) {
				throw new IllegalArgumentException("Uno de los opcionales ingresados no existe.");
			}
		});

		opcionalList.stream().forEach(opcionalId -> {
			Assert.isTrue(opcionalService.validateStockOpcional(opcionalId), "El opcional ingresado no tiene stock");
		});

		Automovil automovil = new Automovil(tipoAuto, calculateTotalPrice(tipoAutoId, opcionalList));
		automovil = automovilDao.save(automovil);
		tipoAutoService.updateCantidadTipoAuto(tipoAuto);

		for (Integer opcionalId : opcionalList) {
			Opcional opcional = opcionalService.findOpcionalById(opcionalId.intValue());
			AutomovilOpcional automovilOpcional = new AutomovilOpcional(automovil, opcional);
			automovilOpcionalService.save(automovilOpcional);
			opcionalService.updateCantidadOpcional(opcional);
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
		
		List<AutomovilOpcional> automovilOpcionalList = automovilOpcionalService.findByAutomovilId(automovilId);
		for ( AutomovilOpcional automovilOpcional: automovilOpcionalList ) {
			automovilOpcionalService.deleteAutomovilOpcional(automovilOpcional);
		}
		
		automovilDao.delete(automovil);
	}
	
	public void updatePrecioFInal( int automovilId ) {
		
		Automovil automovil = automovilDao.findById( automovilId ).get();
		
		List<AutomovilOpcional> automovilOpcionalList = automovilOpcionalService.findByAutomovilId(automovilId);
		
		List<Integer> opcionalList = automovilOpcionalList.stream()
									 .map( automovilOpcional ->  automovilOpcional.getOpcional().getOpcionalId() )
									 .collect( Collectors.toList() );
		
		automovil.setPrecioFinal( calculateTotalPrice( automovil.getTipoAuto().getTipoAutoId(), opcionalList) );		
		
		updateAutomovil(automovil);

	}
	
	public void updateAutomovil( Automovil automovil ) {
		automovilDao.save(automovil);
	}

	private BigDecimal calculateTotalPrice(int tipoAutoId, List<Integer> opcionalList) {

		BigDecimal priceTipoAuto = BigDecimal.ZERO;
		BigDecimal priceOpcional = BigDecimal.ZERO;

		priceTipoAuto = tipoAutoService.findPrecioByTipoAutoId(tipoAutoId);

		opcionalList.stream().forEach(opcional -> {
			if (Collections.frequency(opcionalList, opcional) > 1) {
				throw new IllegalArgumentException("No se aceptan opcionales duplicados");
			}
		});

		priceOpcional = opcionalList.stream().map(opcional -> opcionalService.findPrecioByOpcionalId(opcional))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return priceTipoAuto.add(priceOpcional);

	}

}
