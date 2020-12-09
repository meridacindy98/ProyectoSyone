package com.example.ProyectoSysone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProyectoSysone.dao.AutomovilDao;
import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;
import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.entity.Opcional;
import com.example.ProyectoSysone.service.AutomovilOpcionalService;
import com.example.ProyectoSysone.service.AutomovilService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProyectoSysoneApplication.class })
@Transactional
public class ProyectoSysoneApplicationTests {

	@Autowired
	private AutomovilService automovilService;

	@Autowired
	private AutomovilOpcionalService automovilOpcionalService;

	@Autowired
	private AutomovilDao automovilDao;

	@Autowired
	private AutomovilOpcionalDao automovilOpcionalDao;

	// Guardar un automovil OK
	@Test
	public void saveAutomovilOk() {

		List<Integer> opcionalList = Arrays.asList(1, 2, 3);

		Automovil automovil = automovilService.save(1, opcionalList);
		assertThat(automovilDao.existsById(automovil.getAutomovilId())).isTrue();

	}

	// Intenta guardar un auntomovil con un tipo de auto que no existe
	@Test
	public void saveAutomovilTipoAutoNoExist() {
		List<Integer> opcionalList = Arrays.asList(1, 2, 3);

		assertThatThrownBy(() -> automovilService.save(-1, opcionalList)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("El tipo de auto ingresado no existe.");

	}

	// Guarda un automovil SIN opcionales validando que el calculo del precio final sea correcto
	@Test
	public void saveAutomovilTotalPriceWithoutOpcionalOk() {

		List<Integer> opcionalList = Arrays.asList();

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat(automovil.getPrecioFinal()).isEqualTo(BigDecimal.valueOf(230000.00).setScale(2));

	}

	// Guarda un automovil CON MAS de un opcional validando que el calculo del precio final sea correcto
	@Test
	public void saveAutomovilTotalPriceWithOpcionalesOk() {

		List<Integer> opcionalList = Arrays.asList(1, 2);

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat(automovil.getPrecioFinal()).isEqualTo(BigDecimal.valueOf(262000.00).setScale(2));

	}

	// Guarda un automovil CON UN opcional validando que el calculo del precio final sea correcto
	@Test
	public void saveAutomovilTotalPriceWithOneOpcionalOk() {

		List<Integer> opcionalList = Arrays.asList(1);

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat(automovil.getPrecioFinal()).isEqualTo(BigDecimal.valueOf(242000.00).setScale(2));

	}

	// Intenta guardar un automovil con opcionales repetidos
	@Test
	public void saveAutomovilTotalPriceWithOpcionalesRepetidos() {

		List<Integer> opcionalList = Arrays.asList(2, 2);

		assertThatThrownBy(() -> automovilService.save(1, opcionalList)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("No se aceptan opcionales duplicados");

	}

	// Guarda un automovil SIN opcionales
	@Test
	public void saveAutomovilTotalPriceWithOpcionalesNull() {

		Automovil automovil = automovilService.save(1, null);

		assertThat(automovil.getPrecioFinal()).isEqualTo(BigDecimal.valueOf(230000.00).setScale(2));

	}

	// Guarda un automovil con UN opcional y valida que haya persistido bien en AutomovilOpcional
	@Test
	public void saveAutomovilWithOpcionalOk() {
		List<Integer> opcionalList = Arrays.asList(1);

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat(automovilOpcionalDao.findByAutomovilAutomovilId(automovil.getAutomovilId())).hasSize(1)
				.extracting("opcional", Opcional.class)
				.allMatch(opcional -> opcional.getNombre().equals("Techo corredizo"));

	}

	// Intenta guarda un automovil ingresando un opcional que no existe
	@Test
	public void saveAutomovilWithOpcionalNoexist() {
		List<Integer> opcionalList = Arrays.asList(-1);

		assertThatThrownBy(() -> automovilService.save(1, opcionalList)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Uno de los opcionales ingresados no existe.");

	}

	// Borrar un automovil SIN opcionales
	@Test
	public void deleteAutomovilOk() {
		automovilService.deleteAutomovil(1);
		assertThat(automovilDao.existsById(1)).isFalse();
	}

	// Borrar un automovil CON opcionales
	@Test
	public void deleteAutomovilWithOpcionalesOk() {
		automovilService.deleteAutomovil(2);
		assertThat(automovilDao.existsById(2)).isFalse();
		assertThat(automovilOpcionalDao.existsById(3)).isFalse();
	}

	// Borrar un automovil que NO existe
	@Test
	public void deleteAutomovilNoExist() {
		assertThatThrownBy(() -> automovilService.deleteAutomovil(6)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("El automovil ingresado no existe");
	}

	// Borrar un opcional de un automovil (OpcionalAutomovil)
	@Test
	public void deleteAutomovilOpcional() {
		List<Integer> opcionalList = Arrays.asList(1);
		automovilOpcionalService.deleteAutomovilOpcionalByAutomovilIdAndOpcinalList(2, opcionalList);
		assertThat(automovilOpcionalDao.validateAutomovilAutomovilId(2, 1)).isFalse();
	}

	// Borrar un opcional de un automovil (OpcionalAutomovil) y modificar el precioFinal de Automovil
	@Test
	public void deleteAutomovilOpcionalWithUpdatePrecioFinal() {
		List<Integer> opcionalList = Arrays.asList(1);
		automovilOpcionalService.deleteAutomovilOpcionalByAutomovilIdAndOpcinalList(2, opcionalList);

		assertThat(automovilDao.findById(2).get().getPrecioFinal())
				.isEqualTo(BigDecimal.valueOf(264000.00).setScale(2));

	}
	
	// Borrar un opcional que NO existe para el automovil
	@Test
	public void deleteAutomovilOpcionalNoExist() {
		List<Integer> opcionalList = Arrays.asList(4);
		assertThatThrownBy(
				() -> automovilOpcionalService.deleteAutomovilOpcionalByAutomovilIdAndOpcinalList(2, opcionalList))
						.isInstanceOf(IllegalArgumentException.class)
						.hasMessage("Uno de los opcionales ingresados no existe para este automovil");
	}	

	//Modificar el tipo de auto de un automovil y modifcar el precioFinal del automovil
	@Test
	public void updateTipoAutoIdAutomovilWithUpdatePrecioFinal() {
		automovilService.updateTipoAuto(1, 2);
		assertThat(automovilDao.findById(1).get().getPrecioFinal()).isEqualTo(BigDecimal.valueOf(277000.00).setScale(2));
	}
	
	//Modificar con un Automovil que no existe 
		@Test 
		public void updateAutomovilNoExist() {
			assertThatThrownBy(() -> automovilService.updateTipoAuto(6, 2)).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("El automovil ingresado no existe");
		}
	
	//Modificar un Automovil con un tipo de auto que no existe
	@Test 
	public void updateTipoAutoIdAutomovilNoExist() {
		assertThatThrownBy(() -> automovilService.updateTipoAuto(1, 5)).isInstanceOf(IllegalArgumentException.class)
		.hasMessage("El tipo de auto ingresado no existe.");
	}
	
	//agregar uno o mas opcionales a un automovil
	@Test
	public void addAutomovilOpcionalByAutomovilId() {
		
	}
	
}
