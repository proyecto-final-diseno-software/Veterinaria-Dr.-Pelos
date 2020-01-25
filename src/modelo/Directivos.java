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
        return new LinkedList<>();
    }
    
    public LinkedList<Cliente> consultarCliente(){
        return new LinkedList<>();
    }
    
    public LinkedList<Pedido> consultarPedidos(){
        return new LinkedList<>();
    }
    
    public LinkedList<Servicio> consultarServicios(){
        return new LinkedList<>();
    }
    
    public LinkedList<Producto> consultarProducto(){
        return new LinkedList<>();
    }
    
    public void mostrarVistaDir(boolean b){
        //mostrarVista
        
    }
}
