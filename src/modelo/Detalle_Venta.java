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
    protected Venta venta;
    protected Cotizacion cotizacion;

    public Detalle_Venta(int cantidad) {
        this.venta = null;
        this.cotizacion = null;
        this.cantidad = cantidad;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }
    
    public abstract double calcularPrecio();
    public abstract String getNombre();

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
