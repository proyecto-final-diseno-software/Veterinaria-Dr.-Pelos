/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controladores.CtrAdministrador;
import controladores.CtrBaseDatosProxy;
import controladores.CtrCotizacion;
import controladores.CtrDirectivos;
import controladores.CtrJefeBodega;
import controladores.CtrMascota;
import controladores.CtrPersonalCaja;
import controladores.CtrRuta;
import controladores.CtrSucursal;
import controladores.CtrVenta;
import controladores.UserType;
import controladores.Verification;
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.DetalleVentaServicio;
import modelo.Documento;
import modelo.Efectivo;
import modelo.Mascota;
import modelo.PayPal;
import modelo.PersonalCaja;
import modelo.Ruta;
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
     CtrBaseDatosProxy base = new CtrBaseDatosProxy();
     CtrPersonalCaja cp = new CtrPersonalCaja(); 
     CtrVenta v = new CtrVenta(); 
     CtrAdministrador adm=new CtrAdministrador();
     CtrJefeBodega jefe=new CtrJefeBodega();
     CtrDirectivos directivo=new CtrDirectivos();
     CtrSucursal sur= new CtrSucursal();
     CtrRuta r= new CtrRuta();
     CtrMascota mascota= new CtrMascota();
     Verification verifica= new Verification();
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
    //Esta prueba compruena si una persona es un usuario tipo admin en la base de datos
    @Test
    public void CompararIsUserAdmin() {
        String Cedula= base.isUser("mromero", "caja");
        assertEquals("0975368545",Cedula);
    }
    
    //Prueba unitaria 2
    //Esta prueba se encarga de verificar si el metodo verificarSesion del controlador del administrador funciona
    //Es decir  el codigo del metodo verificarSesion verifica que el administrado logro iniciar sesion
    //para bsucar aquel administrador de acuerdo a una cedula esta prueba ademas de confirar que es un administrador 
    // validad que se este comunicando el programa con la base correctamente.
    @Test
    public void ComprobarSesionAdministrador(){
        UserType typeAdministrador = adm.verificarSesion("0934920534");
        String type= String.valueOf(typeAdministrador);
        String esperado = "ADMINISTRADOR";
        assertEquals(esperado,type);
        
    }
    
    //Prueba unitaria 3
    // Esta prueba comprueba si se realiza correctamente la seleccion de la sucursal en la base
    //con esto se puede ver que el programa se comunica correctamente con la base y nos muestra que de 
    // acuerdo a id de la surusal retorna el objeto existente y no null
    @Test
    public void ComprobarExisteSurcursal(){
        Sucursal valorPrueba = sur.selectSucursal(1);
        assertNotNull(valorPrueba);   
    }
    
    //Prueba Unitaria 4
    // Esta prueba validad si existe en la base de datos Sespinoza como persona de caja
    // si no existe retorna null y como se puede observa este no existe por tal motivo
    // el metodo SelectPersonalCaja funciona correctamente.
    @Test
    public void ComprobarPersonaCaja(){
        PersonalCaja persona = cp.selectPersonalCaja("SEspinoza");
        assertNull(persona);    
    }
    
    //Prueba Unitaria 5
    //Esta prueba se encarga de verificar si el metodo verificarSesion del controlador del jefeDeBodega funciona
    //Es decir  el codigo del metodo verificarSesion verifica que el jefe de bodega logro iniciar sesion
    //realizar un select de la base de datos
    //para bsucar aquel jefe de acuerdo a su cedula esta prueba ademas de confirmar que es un jefeBodega 
    //valida que se este comunicando el programa con la base correctamente.
    
    @Test
    public void ComprobarSesionJefeBodega(){
        UserType typeJefeBodega = jefe.verificarSesion("0996903483");
        String type= String.valueOf(typeJefeBodega);
        String esperado = "JEFE_BODEGA";
        assertEquals(esperado,type);
        
    }
    
    //Prueba unitaria 6
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
    //Prueba Unitaria 7
    // Comprueba que se inserte una ruta y a su validad el funcionamiento con la base
    @Test
    public void ComprobarInsercionRuta() {
        Ruta ru = new Ruta();
        ru.setDireccion("Orquideas");
        boolean valor= r.insertRuta(ru);
        assertTrue(valor);
       
    }
    // Prueba 8
    //Se encarga de comprobar si funciona bien el cambio del estado de una mascota de un cliente
    @Test
    public void ComprobarSetMascota() {
        Mascota ma = new Mascota();
        Cliente c = new Cliente("0990999841","Guayacanes","042859384","Eduardo","Gonzalez");
        ma.setNombre("Ema");
        ma.setRaza("Labradora");
        ma.setCliente(c);
        boolean valor= mascota.setEstadoMascota(ma, "Domiccilio");
        assertTrue(valor);
       
    }
    
    // Prueba 9
    @Test    
   //Esta prueba se encarga de verificar si el metodo verificarSesion del controlador del personal caja funciona
    //Es decir  el codigo del metodo verificarSesion verifica que el personal de caja a logro iniciar sesion
    public void ComprobarVersionPersonalCaja(){
        UserType typePersonaCaja = cp.verificarSesion("0935843645");
        String type= String.valueOf(typePersonaCaja);
        String esperado = "PERSONAL_CAJA";
        assertEquals(esperado,type);
    }  
    
    //Prueba Unitaria 10
    // comprueba si es usaura independiente del tipo solo verifica si forma parte d elos usuarios en el sistema(base)
    @Test
    public void ComprobarUsuario(){   
        UserType type = verifica.verificacionDatosSession("jalvarez", "jefebodega");
        assertNotNull(type);
        
    }
    
    
    
    
}
