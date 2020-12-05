package com.example.ProyectoSysone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.model.RequestPost;
import com.example.ProyectoSysone.service.AutomovilService;
import com.example.ProyectoSysone.service.OpcionalService;
import com.example.ProyectoSysone.service.TipoAutoService;

@RestController
public class AutomovilController {
		
	@Autowired
	private TipoAutoService tipoAutoService;
	
	@Autowired
	private OpcionalService opcionalService;
	
	//http://localhost:8080/precioTotal?tipoAutoId=1&opcionalId=1
//	@GetMapping("/precioTotal")
//	@ResponseBody
//	String calculateTotalPriceCACA( @RequestParam(value = "tipoAutoId", required = true) int tipoAutoId, @RequestParam(value = "opcionalId", required = false) List<Integer> opcionalIdList){
//
//		Boolean duplicate = false;
//		int priceTipoAuto = 0;
//		int priceOpcional = 0;
//		int priceTotal = 0;
//		String message = "";
//		
//		//Obtengo el precio del tipo de auto
//		priceTipoAuto = tipoAutoService.getPriceById(tipoAutoId);
//						
//		if( opcionalIdList.size() > 1 ) {
//			
//			System.out.println("Se ingreso mas de un opcional");
//			
//			//Valido que no se hayan pasado opcionalId repetidos
//			for ( int i = 0; i < opcionalIdList.size();  i++ ) {
//				
//				for ( int j = i + 1; j < opcionalIdList.size();  j++ ) {
//					
//					if( (opcionalIdList.get(i)).equals(opcionalIdList.get(j)) ) {
//						duplicate = true;
//						return "Se ingresaron opcionales duplicados";
//					}
//					
//				}
//				
//			}
//			
//			if( duplicate = false ) {
//				
//				System.out.println("No hay opcionales duplicados");
//				for( Integer opcionalId : opcionalIdList ) {
//					priceOpcional = opcionalService.getPriceById(opcionalId);
//					priceOpcional ++;
//				}
//			}
//			
//		} else if ( opcionalIdList.size() == 1 ) {
//			System.out.println("Se ingreso solo un opcional");
//			priceOpcional = opcionalService.getPriceById(opcionalIdList.get(0));
//		}
//		
//		priceTotal = priceTipoAuto + priceOpcional;
//		
//		return  "tipo auto es: " + tipoAutoId + "precio: " + priceTipoAuto + "\n" +
//			    "opcionales: " + opcionalIdList + "precio: " + priceOpcional + "\n" +
//			    "PRECIO TOTAL: " + priceTotal;
//	} 
	
	//http://localhost:8080/automovil
	@PostMapping( path = "/automovil", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	RequestPost saveAutomovil( @RequestBody RequestPost request ) {		
		
		int totalPrice = 0;
		
		if ( request.getTipoAutoId() != 0 ) {
			totalPrice = calculateTotalPrice(request.getTipoAutoId(), request.getOpcionalList());
			
			
			
		} else {
			System.out.println("Se debe seleccionar si o si un tipo de auto");
		}
		
		
		return request;
	}
	
	int calculateTotalPrice( int tipoAutoId, List<Integer> opcionalList ){
		
		Boolean duplicate = false;
		int priceTipoAuto = 0;
		int priceOpcional = 0;
		int priceTotal = 0;
		String message = "";
		
		//Obtengo el precio del tipo de auto
		priceTipoAuto = tipoAutoService.getPriceById(tipoAutoId);
						
		if( opcionalList.size() > 1 ) {
			
			System.out.println("Se ingreso mas de un opcional");
			
			//Valido que no se hayan pasado opcionalId repetidos
			for ( int i = 0; i < opcionalList.size();  i++ ) {
				
				for ( int j = i + 1; j < opcionalList.size();  j++ ) {
					
					if( (opcionalList.get(i)).equals(opcionalList.get(j)) ) {
						duplicate = true;
						return 0;
					}
					
				}
				
			}
			
			if( duplicate = false ) {
				
				System.out.println("No hay opcionales duplicados");
				for( Integer opcionalId : opcionalList ) {
					priceOpcional = opcionalService.getPriceById(opcionalId);
					priceOpcional ++;
				}
			}
			
		} else if ( opcionalList.size() == 1 ) {
			System.out.println("Se ingreso solo un opcional");
			priceOpcional = opcionalService.getPriceById(opcionalList.get(0));
		}
		
		priceTotal = priceTipoAuto + priceOpcional;
		
		return priceTotal;
		
	}
	
	
}
