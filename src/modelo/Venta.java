/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author paula
 */
public class Venta {
    private int id_venta;
    private LocalDate fecha;
    private double monto_total;
    private double descuento;//En porcentaje
    
    
    public double calcularMonto(){
        if(descuento >0){
            return monto_total*(1 - (descuento/100));
        }
        return monto_total;
    }

    public Venta(int id_venta, LocalDate fecha, double monto_total, double descuento) {
        this.id_venta = id_venta;
        this.fecha = fecha;
        this.monto_total = monto_total;
        this.descuento = descuento;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
    
    
}
