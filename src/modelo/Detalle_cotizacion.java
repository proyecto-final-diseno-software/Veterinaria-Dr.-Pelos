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
public class Detalle_cotizacion {
    public int id_detalle_ven_pro;
    public int cantidad;
    
    public double calcularPrecio(){
        return 0;
    }

    
    public int getId_detalle_ven_pro() {
        return id_detalle_ven_pro;
    }

    public void setId_detalle_ven_pro(int id_detalle_ven_pro) {
        this.id_detalle_ven_pro = id_detalle_ven_pro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
