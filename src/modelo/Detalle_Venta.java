/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ADMIN
 */
public abstract class Detalle_Venta {
    protected int cantidad;
    protected int id_detalle_ventaProducto;

    public Detalle_Venta(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public abstract double calcularPrecio();

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
