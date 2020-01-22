/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelo.Producto;
import modelo.Servicio;


/**
 * Clase en construccion
 * @author paula
 */
public class Ctr_Administrador implements Control_Session{
    
    public void crearProducto(Producto p){
    }
    
    public void crearServicio(Servicio s){
    }
        
    public void modificarProducto(Producto p){
    }
    
    public void modificarServicio(Servicio s){
    }
    
    public void descartarProducto(Producto p){
    }
    
    public void descartarServicio(Servicio s){
    }
    
    //Metodo que me retorna la seccion valida de un empleado de caja
    @Override
    public UserType verificarSesion(String user, String pass) {
        return null;
    }
}
