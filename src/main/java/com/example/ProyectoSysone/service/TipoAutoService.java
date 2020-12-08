package com.example.ProyectoSysone.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoSysone.dao.TipoAutoDao;
import com.example.ProyectoSysone.entity.TipoAuto;

@Service
public class TipoAutoService {

	@Autowired
	private TipoAutoDao tipoAutoDao;
	
	public TipoAuto findById( int tipoAutoId ) {
		return tipoAutoDao.findById(tipoAutoId).get();
	}
	
	public BigDecimal findPrecioByTipoAutoId( int tipoAutoId ) {
		return tipoAutoDao.findPrecioByTipoAutoId(tipoAutoId);
	}
	
	public void updateLessCantidadTipoAuto( TipoAuto tipoAuto ) {
		tipoAuto.setCantidad( tipoAuto.getCantidad() - 1 );
		tipoAutoDao.save(tipoAuto);
	}
	
	public void updateMoreCantidadTipoAuto( TipoAuto tipoAuto ) {
		tipoAuto.setCantidad( tipoAuto.getCantidad() + 1 );
		tipoAutoDao.save(tipoAuto);
	}
	
	public Boolean validateStockTipoAuto( int tipoAutoId ) {
		return tipoAutoDao.validateStockTipoAuto(tipoAutoId);
	}
	
}
