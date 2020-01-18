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
public class Detalle_VentaServicio extends Detalle_Venta{
    private Servicio servicio;
    
    
    public Detalle_VentaServicio(int cantidad, Servicio servicio) {
        super(cantidad);
        this.servicio =servicio;
    }

    @Override
    public double calcularPrecio() {
        return servicio.getPrecio() * cantidad;
    }
    
}
