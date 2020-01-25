/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controladores.Ctr_BaseDatosProxy;
import controladores.Ctr_Personal_Caja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author scarlet Espinoza
 */
public class PruebaTest {
    Ctr_BaseDatosProxy base = new Ctr_BaseDatosProxy();
     Ctr_Personal_Caja cp = new Ctr_Personal_Caja();
    
    public PruebaTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() 
    
    
    @Test
    public void CompararCedula() {
        String Cedula= base.isUser("mromero", "caja");
        assertEquals("0975368545",Cedula);
    }
}
