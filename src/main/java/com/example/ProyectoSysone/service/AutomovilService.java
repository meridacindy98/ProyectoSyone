package com.example.ProyectoSysone.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.AutomovilDao;
import com.example.ProyectoSysone.entity.Automovil;
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
		
	public Automovil save( int tipoAutoId, List<Integer> opcionalList) {
		
		TipoAuto tipoAuto;
		
		try {
			tipoAuto = tipoAutoService.findById(tipoAutoId);			
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("El tipo auto no existia.",e);
		}		
		
		Automovil automovil = new Automovil( tipoAuto, calculateTotalPrice(tipoAutoId, opcionalList));		
		
		return automovilDao.save(automovil);
	}	
	
	private BigDecimal calculateTotalPrice(int tipoAutoId, List<Integer> opcionalList) {

		Boolean duplicate = false;
		BigDecimal priceTipoAuto = BigDecimal.ZERO;
		BigDecimal priceOpcional = BigDecimal.ZERO;
		BigDecimal priceTotal = BigDecimal.ZERO;
		String message = "";

		// Obtengo el precio del tipo de auto
		priceTipoAuto = tipoAutoService.findPrecioByTipoAutoId(tipoAutoId);

		if (opcionalList != null && opcionalList.size() > 1) {

			System.out.println("Se ingreso mas de un opcional");

			// Valido que no se hayan pasado opcionalId repetidos
			for (int i = 0; i < opcionalList.size(); i++) {

				for (int j = i + 1; j < opcionalList.size(); j++) {

					if ((opcionalList.get(i)).equals(opcionalList.get(j))) {
						duplicate = true;
						return null;
					}

				}

			}

			if (duplicate = false) {

				System.out.println("No hay opcionales duplicados");
				for (Integer opcionalId : opcionalList) {
					priceOpcional = opcionalService.findPrecioByOpcionalId(opcionalId);
					priceOpcional.add(priceOpcional);
				}
			}

		} else if ( opcionalList != null && opcionalList.size() == 1) {
			System.out.println("Se ingreso solo un opcional");
			priceOpcional = opcionalService.findPrecioByOpcionalId(opcionalList.get(0));
		}

		priceTotal = priceTipoAuto.add(priceOpcional);

		return priceTotal;

	}


}
