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
public class Detalle_VentaProducto {
    private int cantidad;
    private int id_detalle_ventaProducto;
    
    public double calcularPrecio(){
        return 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_detalle_ventaProducto() {
        return id_detalle_ventaProducto;
    }

    public void setId_detalle_ventaProducto(int id_detalle_ventaProducto) {
        this.id_detalle_ventaProducto = id_detalle_ventaProducto;
    }
    
    
    
    
    
}
