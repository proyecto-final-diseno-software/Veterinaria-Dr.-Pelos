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
public class Detalle_VentaProducto extends Detalle_Venta{
    private final Producto producto;

    public Detalle_VentaProducto(int cantidad, Producto producto) {
        super(cantidad);
        this.producto = producto;
    }
    
    @Override
    public double calcularPrecio(){
        return producto.getPrecioUnitario()*cantidad;
    }

    public Producto getProducto() {
        return producto;
    }
}
