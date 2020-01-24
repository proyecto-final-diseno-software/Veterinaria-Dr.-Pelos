/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.LinkedList;

/**
 *
 * @author paula
 */
public class Directivos extends Persona{

    public Directivos(String cedula, String nombre, String apellido) {
        super(nombre, apellido, cedula);
    }
    
    public LinkedList<Venta> consultarVentas(){
        return null;
    }
    
    public LinkedList<Cliente> consultarCliente(){
        return null;
    }
    
    public LinkedList<Pedido> consultarPedidos(){
        return null;
    }
    
    public LinkedList<Servicio> consultarServicios(){
        return null;
    }
    
    public LinkedList<Producto> consultarProducto(){
        return null;
    }
    
    public void mostrarVistaDir(boolean b){
        
    }
}
