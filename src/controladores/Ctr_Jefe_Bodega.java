/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelo.Producto;
import modelo.Ruta;

/**
 *
 * @author paula
 */
public class Ctr_Jefe_Bodega implements Control_Session{
    
    public Ruta establecerRuta(){
        return null;
    }
    
    public boolean manejarPrecio(){
        //En construccion
        return true;
    }
    
    public boolean modificarPrecioProducto(Producto p, int precio){
        //En construccion
        return true;
    }
    
    //Metodo que me retorna la seccion valida de un empleado de caja
    @Override
    public UserType verificarSesion(String cedula) {
        return null;
    }
}
