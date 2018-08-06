/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.dao.jdcb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utn.frsf.ofa.cursojava.app.Contratado;
import utn.frsf.ofa.cursojava.app.Empleado;

/**
 *
 * @author usuario
 */
public class EmpleadoDaoJdbcTest {

    public EmpleadoDaoJdbcTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCrear() {
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        System.out.println("TEST DEL METODO: crear");

        String fechaString = "2002-08-01";
        SimpleDateFormat dt = new SimpleDateFormat("YYYY-MM-DD");
        Date fecha_ingreso = null;
        try {
            fecha_ingreso = dt.parse(fechaString);
        } catch (ParseException pe) {
            System.out.println("ERROR al convertir una string en date.");
            System.out.println(pe.getMessage());
        }

        //Efectivo empEfectivo = new Efectivo(4200.0, 1000.0, 9, "BELEN ARANDA", "baranda@gmail.com", "27296325870", fecha_ingreso, 6);
        
        Contratado empContratado = new Contratado(1750.0, "ESTELA TROTA", "etrota@gmail.com", "30264978331", fecha_ingreso, 7);
        
        EmpleadoDaoJdbc instance = new EmpleadoDaoJdbc();
     //   instance.crear(empEfectivo);
        instance.crear(empContratado);
    }

    @Test
    public void testActualizar() {
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        System.out.println("TEST METODO: Actualizar");
        
        EmpleadoDaoJdbc instance = new EmpleadoDaoJdbc();
        Empleado empleado = instance.buscarPorId(3);
        
        empleado.setHorasTrabajadas(9);
        int rtaCrear = instance.actualizar(empleado);
        
        assertTrue("La acción de ACTUALIZAR_EMPLEADO pudo completarse con éxito", rtaCrear == 1);
    }

    @Test
    public void testEliminar() {
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        System.out.println("ACCION TEST: eliminar");
                
        EmpleadoDaoJdbc instance = new EmpleadoDaoJdbc();
        Empleado empleado =  instance.buscarPorId(5);  
                
        instance.eliminar(empleado);
        
    }

    @Test
    public void testBuscarPorId() {
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("ACCION TEST - buscarPorId");
                
        EmpleadoDaoJdbc instance = new EmpleadoDaoJdbc();
        Empleado empleado = instance.buscarPorId(4);
        
        assertNotNull("BUSQUEDA CON EXITO", empleado);
    }

    @Test
    public void testBuscarTodos() {
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        System.out.println("ACCION TEST - buscarTodos");
        
        EmpleadoDaoJdbc instance = new EmpleadoDaoJdbc();
        //List<Empleado> expResult = null;
        int tamañoLista = 5;
        List<Empleado> result = instance.buscarTodos();
        
        assertTrue("La lista retornada es correcta", tamañoLista == result.size());
        //assertEquals(expResult, result);
        
    }

}
