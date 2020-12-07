package com.example.ProyectoSysone;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.example.ProyectoSysone.dao.AutomovilDao;
import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;
import com.example.ProyectoSysone.dao.OpcionalDao;
import com.example.ProyectoSysone.dao.TipoAutoDao;
import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.entity.Opcional;
import com.example.ProyectoSysone.entity.TipoAuto;
import com.example.ProyectoSysone.service.AutomovilOpcionalService;
import com.example.ProyectoSysone.service.AutomovilService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProyectoSysoneApplication.class })
public class ProyectoSysoneApplicationTests {

	@Autowired
	private AutomovilService automovilService;

	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;

	@Autowired
	private AutomovilDao automovilDao;

	@Autowired
	private AutomovilOpcionalDao automovilOpcionalDao;

	@Autowired
	private TipoAutoDao tipoAutoDao;

	@Autowired
	private OpcionalDao opcionalDao;

	@Test
	public void saveAutomovilOk() {

		List<Integer> opcionalList = Arrays.asList(1, 2, 3);

		Automovil automovil = automovilService.save(1, opcionalList);
		//Assert.isTrue(automovilDao.existsById(automovil.getAutomovilId()), "El automovil no se guardo");
		assertThat(automovilDao.existsById(automovil.getAutomovilId())).isTrue();

	}

	@Test
	public void saveAutomovilError() {
		List<Integer> opcionalList = Arrays.asList(1, 2, 3);

		assertThatThrownBy(() -> automovilService.save(-1, opcionalList)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("El tipo auto no existe.");

	}

	@Test
	public void saveAutomovilTotalPriceWithoutOpcionalOk() {

		List<Integer> opcionalList = Arrays.asList();

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat(automovil.getPrecioFinal()).isEqualTo(BigDecimal.valueOf(230000.00).setScale(2));

	}

	@Test
	public void saveAutomovilTotalPriceWithOpcionalesOk() {

		List<Integer> opcionalList = Arrays.asList(1, 2);

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat(automovil.getPrecioFinal()).isEqualTo(BigDecimal.valueOf(262000.00).setScale(2));

	}

	@Test
	public void saveAutomovilTotalPriceWithOneOpcionalOk() {

		List<Integer> opcionalList = Arrays.asList(1);

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat(automovil.getPrecioFinal()).isEqualTo(BigDecimal.valueOf(242000.00).setScale(2));

	}

	@Test
	public void saveAutomovilTotalPriceWithOpcionalesRepetidos() {

		List<Integer> opcionalList = Arrays.asList(2, 2);

		assertThatThrownBy(() -> automovilService.save(1, opcionalList)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("No se aceptan opcionales duplicados");

	}

	@Test
	public void saveAutomovilTotalPriceWithOpcionalesNull() {

		Automovil automovil = automovilService.save(1, null);

		assertThat(automovil.getPrecioFinal()).isEqualTo(BigDecimal.valueOf(230000.00).setScale(2));

	}

	@Test
	public void saveAutomovilWithOpcionalOk() {
		List<Integer> opcionalList = Arrays.asList(1);

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat(automovilOpcionalDao.findByAutomovilAutomovilId(automovil.getAutomovilId())).hasSize(1)
				.extracting("opcional", Opcional.class)
				.allMatch(opcional -> opcional.getNombre().equals("Techo corredizo"));

	}

	// Ingresando un opcional que no existe
	@Test
	public void saveAutomovilWithOpcionalError() {
		List<Integer> opcionalList = Arrays.asList(-1);

		assertThatThrownBy(() -> automovilService.save(1, opcionalList)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Uno de los opcionales ingresados no existe.");

	}

	// Ingresando un automovil con opcionales que descuenta stock
	@Test
	public void saveAutomovilWithOpcionalDescontadoStockOK() {
		List<Integer> opcionalList = Arrays.asList(1);

		TipoAuto tipoAuto = tipoAutoDao.findById(1).get();
		int cantidadInicialTipoAuto = tipoAuto.getCantidad();

		Opcional opcional = opcionalDao.findById(opcionalList.get(0).intValue()).get();
		int cantidadInicialOpcional = opcional.getCantidad();

		automovilService.save(1, opcionalList);

		tipoAuto = tipoAutoDao.findById(1).get();
		int cantidadFinalTipoAuto = tipoAuto.getCantidad();

		opcional = opcionalDao.findById(opcionalList.get(0).intValue()).get();
		int cantidadFinalOpcional = opcional.getCantidad();

		assertThat(cantidadInicialTipoAuto).isGreaterThan(cantidadFinalTipoAuto);
		assertThat(cantidadInicialOpcional).isGreaterThan(cantidadFinalOpcional);
	}

	// Ingresando un automovil con opcionales que descuenta stock, no hay stock TIPO AUTO
	@Test
	public void saveAutomovilWithOpcionalDescontadoStockTipoAutoError() {
		List<Integer> opcionalList = Arrays.asList(1);

		assertThatThrownBy(() -> automovilService.save(3, opcionalList)).isInstanceOf(IllegalArgumentException.class)
		.hasMessage("El tipo de auto ingresado no tiene stock");

	}
	
	// Ingresando un automovil con opcionales que descuenta stock, no hay stock OPCIONAL
	@Test
	public void saveAutomovilWithOpcionalDescontadoStockOpcionalError() {
		List<Integer> opcionalList = Arrays.asList(5);

		assertThatThrownBy(() -> automovilService.save(1, opcionalList)).isInstanceOf(IllegalArgumentException.class)
		.hasMessage("El opcional ingresado no tiene stock");

	}

	// Borrar un automovil sin opcionales
	@Test
	public void deleteAutomovilOk(){		
		automovilService.deleteAutomovil(1);
		assertThat(automovilDao.existsById(1)).isFalse();
	}
	
	// Borrar un automovil CON opcionales

	// Borrar un opcional de un automovil (OpcionalAutomovil)

}
