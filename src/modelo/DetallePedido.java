/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author paula
 */
public class DetallePedido {
    private int idDetalleProducto;
    private int cantidad;
    private Producto p;
    
    public DetallePedido(Producto p, int cantidad){
        this.p = p;
        this.cantidad = cantidad;
    }
    
    public boolean cambiarCantidad(int numero){
        //si aun hay articulos disponibles
        if(numero >0){
            this.cantidad = numero;
            return true;
        }
        return false;
        
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
