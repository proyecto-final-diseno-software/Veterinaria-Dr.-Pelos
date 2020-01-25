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
public class DetalleVentaServicio extends DetalleVenta{
    private final Servicio servicio;
    
    public DetalleVentaServicio(int cantidad, Servicio servicio) {
        super(cantidad);
        this.servicio =servicio;
    }

    @Override
    public double calcularPrecio() {
        return servicio.getPrecio() * cantidad;
    }

    public Servicio getServicio() {
        return servicio;
    }

    @Override
    public String getNombre() {
        return servicio.getNombre();
    }
}
