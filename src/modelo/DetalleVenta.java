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
public abstract class DetalleVenta {
    protected int cantidad;
    protected int idDetalleVentaProducto;
    protected Documento documento;

    public DetalleVenta(int cantidad) {
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

    public int getIdDetalleVentaProducto() {
        return idDetalleVentaProducto;
    }

    public void setIdDetalleVentaProducto(int idDetalleVentaProducto) {
        this.idDetalleVentaProducto = idDetalleVentaProducto;
    }
    
}
