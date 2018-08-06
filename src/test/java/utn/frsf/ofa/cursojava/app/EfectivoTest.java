/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.app;

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author NataliaKieffer
 */
public class EfectivoTest {
    
    public EfectivoTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testSomeMethod() {
    }
    
    /**
     *
     */
    @Test
    public void testSalarioSinHorasExtras() {
        System.out.println("salario sin horas extras: ");
        Efectivo empleado = new Efectivo();
        
        empleado.setHorasTrabajadas(40);
        empleado.setHorasMinimasObligatorias(40);
        empleado.setComision(2000.0);
        empleado.setSueldoBasico(3000.0);
        
        Double expResult = 3000.0 + 2000.0;
        Double result = empleado.salario();
        
        System.out.println(result);
        
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSalario2HorasExtras() {
        System.out.println("salario 2 horas extras: ");
        Efectivo empleado = new Efectivo();
        
        empleado.setHorasTrabajadas(42);
        empleado.setHorasMinimasObligatorias(40);
        empleado.setComision(2000.0);
        empleado.setSueldoBasico(3000.0);
        
        Double expResult = 3000.0 + 2000.0 + 3000.0;
        Double result = empleado.salario();
        
        System.out.println(result); 
        
        assertEquals(expResult, result);
        
    }
    
}