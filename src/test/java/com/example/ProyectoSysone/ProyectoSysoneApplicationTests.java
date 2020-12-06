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

import com.example.ProyectoSysone.dao.AutomovilDao;
import com.example.ProyectoSysone.entity.Automovil;
import com.example.ProyectoSysone.service.AutomovilService;
import com.example.ProyectoSysone.service.TipoAutoService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProyectoSysoneApplication.class})
public class ProyectoSysoneApplicationTests {

    @Autowired
    private AutomovilService automovilService;
    
    @Autowired
    private AutomovilDao automovilDao;
              
    @Test
    public void saveAutomovilOk(){   	    	
    	
    	List<Integer> opcionalList = Arrays.asList(1,2,3);	
    	        	
        Automovil automovil = automovilService.save( 1,  opcionalList);        
      
        assertThat(automovilDao.existsById( automovil.getAutomovilId() )).isTrue();        
             
    }
    
    @Test
    public void saveAutomovilError(){
    	List<Integer> opcionalList = Arrays.asList(1,2,3);	    	
     
        assertThatThrownBy(() -> automovilService.save( -1,  opcionalList))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("El tipo auto no existia.");
       
    }
    
    @Test
    public void saveAutomovilTotalPriceOk(){   	    	
    	
        Automovil automovil = automovilService.save( 1,  null);        
      
        assertThat( automovil.getPrecioFinal() ).isEqualTo( BigDecimal.valueOf(230000.00).setScale(2) );        
             
    }

}
