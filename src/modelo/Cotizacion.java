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
public class Cotizacion {
    private int idCotizacion;
    private LocalDate fecha;
    private double valor = 0;
    private Cliente cliente;
    private Personal_Caja personal_caja;

    public Cotizacion() {
        this.fecha = LocalDate.now();
        
    }
    
    public void agregarProducto(Detalle_VentaProducto detallep){
        valor += detallep.calcularPrecio();
        
    }
    
    public void agregarServicio(VentaServicio detalleS){
        valor += detalleS.precioServicio();
    }
    
//    private void calcularValorTotal(){
//        
//    }

    public Personal_Caja getPersonal_caja() {
        return personal_caja;
    }

    public void setPersonal_caja(Personal_Caja personal_caja) {
        this.personal_caja = personal_caja;
    }
    
    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
    
}
