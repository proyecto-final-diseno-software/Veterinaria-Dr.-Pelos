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
    private double total;
    private double descuento;
    private double subtotal;
    private int numeroFactura;
    private Personal_Caja personalCaja;
    
    
    public double calcularMonto(){
        if(descuento >0){
            return total*(1 - (descuento/100));
        }
        return total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }


    public Personal_Caja getPersonalCaja() {
        return personalCaja;
    }

    public void setPersonalCaja(Personal_Caja personalCaja) {
        this.personalCaja = personalCaja;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    
    
    
}
