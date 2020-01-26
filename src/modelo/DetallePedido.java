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
    private Producto producto;
    
    public DetallePedido(Producto p, int cantidad){
        this.producto = p;
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

    public int getIdDetalleProducto() {
        return idDetalleProducto;
    }

    public void setIdDetalleProducto(int idDetalleProducto) {
        this.idDetalleProducto = idDetalleProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    
}
