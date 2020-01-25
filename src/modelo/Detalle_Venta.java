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
    protected Documento documento;

    public Detalle_Venta(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumento() {
        return documento;
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
