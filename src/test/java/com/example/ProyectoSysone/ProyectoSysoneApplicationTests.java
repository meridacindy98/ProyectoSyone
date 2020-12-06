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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.ProyectoSysone.dao.AutomovilDao;
import com.example.ProyectoSysone.dao.AutomovilOpcionalDao;
import com.example.ProyectoSysone.dao.OpcionalDao;
import com.example.ProyectoSysone.dao.TipoAutoDao;
import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.entity.AutomovilOpcional;
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
		System.out.println(automovilDao.count());
		Automovil automovil = automovilService.save(1, opcionalList);
//		Assert.isTrue(automovilDao.existsById(automovil.getAutomovilId()), "El automovil no se guardo");
		assertThat(automovilDao.existsById(automovil.getAutomovilId())).isTrue();
	}

	@Test
	public void saveAutomovilError() {
		List<Integer> opcionalList = Arrays.asList(1, 2, 3);

		assertThatThrownBy(() -> automovilService.save(-1, opcionalList)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("El tipo auto no existia.");

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
		
				assertThatThrownBy(() -> automovilService.save(1, opcionalList))
		.isInstanceOf(IllegalArgumentException.class)
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

		assertThat( automovilOpcionalDao.findByAutomovilAutomovilId( automovil.getAutomovilId() ) )
				.hasSize(1)
				.extracting("opcional",Opcional.class)
				.allMatch(opcional -> opcional.getNombre().equals("Techo corredizo"));
		
	}
	
	public void saveAutomovilWithOpcionalError() {				
		List<Integer> opcionalList = Arrays.asList(-1);

		Automovil automovil = automovilService.save(1, opcionalList);

		assertThat( automovilOpcionalDao.findByAutomovilAutomovilId( automovil.getAutomovilId() ) );		
		
	}
	
	
	

}
