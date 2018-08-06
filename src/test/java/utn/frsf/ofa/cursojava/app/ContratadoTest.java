/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Natalia Kieffer
 */
public class ContratadoTest {
    
    public ContratadoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
    }

@Test
    public void testSalario40HorasTrabajadas() {
        System.out.println("salario 40 horas trabajadas en la semana: ");
        Contratado empleado = new Contratado();
        
        empleado.setHorasTrabajadas(40);
        empleado.setMontoPorHora(300.0);
        
        Double expResult = 40* 300.0;
        Double result = empleado.salario();
        
        System.out.println(result); 
        
        assertEquals(expResult, result);
        
    }    
}
