/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controladores.Ctr_Administrador;
import controladores.Ctr_BaseDatosProxy;
import controladores.Ctr_Directivos;
import controladores.Ctr_Jefe_Bodega;
import controladores.Ctr_Personal_Caja;
import controladores.Ctr_Sucursal;
import controladores.Ctr_Venta;
import controladores.UserType;
import modelo.Cliente;
import modelo.Detalle_VentaServicio;
import modelo.Efectivo;
import modelo.PayPal;
import modelo.Personal_Caja;
import modelo.Servicio;
import modelo.Sucursal;
import modelo.Tarjeta;
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
     Ctr_Venta v = new Ctr_Venta(); 
     Ctr_Administrador adm=new Ctr_Administrador();
     Ctr_Jefe_Bodega jefe=new Ctr_Jefe_Bodega();
     Ctr_Directivos directivo=new Ctr_Directivos();
     Ctr_Sucursal sur= new Ctr_Sucursal();
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
    
    // Prueba Unitaria 1
    //Esta prueba compruena si una persona es un usuario tipo admin
    @Test
    public void CompararIsUserAdmin() {
        String Cedula= base.isUser("mromero", "caja");
        assertEquals("0975368545",Cedula);
    }
    
    //Prueba Unitaria 2
    //Esta prueba validad si se reliza un pago en efectivo y si se inserta a la base de datos
    @Test
    public void ComprobarInsercionEfectivo() {
        Efectivo efec = new Efectivo((float)1.5,6.0);
        boolean Inserta =cp.insertEfectivo(efec);
        assertTrue(Inserta);
       
    }
    
    //Prueba unitaria 3
    //Esta prueba se encarga de verificar si el metodo verificarSesion del administrador funciona
    //Es decir  el codigo del metodo verificarSesion realizar un select de la base de datos
    //para bsucar aquel administrador de acuerdo a una cedula esta prueba ademas de confirar que es un administrador 
    // validad que se este comunicando el programa con la base correctamente.
    @Test
    public void ComprobarSesionAdministrador(){
        UserType typeAdministrador = adm.verificarSesion("0934920534");
        String type= String.valueOf(typeAdministrador);
        String esperado = "ADMINISTRADOR";
        assertEquals(esperado,type);
        
    }
    //Prueba unitaria 4
    // Esta prueba comprueba si se realiza correctamente la seleccion de la sucursal en la base
    //con esto se puede ver que el programa se comunica correctamente con la base y nos muestra que de 
    // acuerdo a id de la surusal retorna el objeto existente y no null
    @Test
    public void ComprobarExisteSurcursal(){
        Sucursal valorPrueba = sur.selectSucursal(1);
        assertNotNull(valorPrueba);   
    }
    
    //Prueba Unitaria 5
    // Esta prueba validad si existe en la base de datos Sespinoza como persona de caja
    // si no existe retorna null y como se puede observa este no existe por tal motivo
    // el metodo SelectPersonalCaja funciona correctamente.
    @Test
    public void ComprobarPersonaCaja(){
        Personal_Caja persona = cp.selectPersonalCaja("SEspinoza");
        assertNull(persona);
        
        
    }
    //Prueba unitaria 6
    ///Esta prueba validad si se realiza un pago con tarjeta y si se sinserta a la base de datos correctamente
    @Test
    public void ComprobarInsercionPagoTarjeta() {
        Tarjeta t = new Tarjeta(5,"923092343");
        boolean Inserta =v.insertTarjeta(t);
        assertTrue(Inserta);
       
    }
    //Prueba Unitaria 7
    //Esta prueba se encarga de verificar si el metodo verificarSesion del jefeDeBodega funciona
    //Es decir  el codigo del metodo verificarSesion realizar un select de la base de datos
    //para bsucar aquel jefe de acuerdo a su cedula esta prueba ademas de confirmar que es un jefeBodega 
    // validad que se este comunicando el programa con la base correctamente.
    
    @Test
    public void ComprobarSesionJefeBodega(){
        UserType typeJefeBodega = jefe.verificarSesion("0996903483");
        String type= String.valueOf(typeJefeBodega);
        String esperado = "JEFE_BODEGA";
        assertEquals(esperado,type);
        
    }
    
    //Prueba unitaria 8
    //Esta prueba se encarga de verificar si el metodo verificarSesion del directivo funciona
    //Es decir  el codigo del metodo verificarSesion realizar un select de la base de datos
    //para bsucar aquel directivo de acuerdo a su cedula esta prueba ademas de confirmar que es un directivo 
    // validad que se este comunicando el programa con la base correctamente.
     @Test
    public void ComprobarSesionDirectivos(){
        UserType typeDirectivo = directivo.verificarSesion("0964784725");
        String type= String.valueOf(typeDirectivo);
        String esperado = "DIRECTIVO";
        assertEquals(esperado,type);
        
    }
    
     //Prueba unitaria 9
    ///Esta prueba validad si se realiza un pago con Paypal y si se sinserta a la base de datos correctamente
    public void ComprobarInsercionPayPal() {
        PayPal pl = new PayPal((float)3, "aaa@hotmail.com");
        boolean Inserta =cp.insertPaypal(pl);;
        assertTrue(Inserta);
       
    }
    //Prueba Unitaria 10
    
    
    
    
}
