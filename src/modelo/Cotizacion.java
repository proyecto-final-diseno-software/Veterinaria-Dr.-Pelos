/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author paula
 */
public class Cotizacion {
    private int idCotizacion;
    private LocalDate fecha;
    private String estado;
    private double valor = 0;
    private Cliente cliente;
    private LinkedList<Object> detalles;

    public Cotizacion() {
        this.fecha = LocalDate.now();
        detalles = new LinkedList<>();
        
    }
    
    public void agregarProducto(Detalle_VentaProducto detallep){
        detalles.add(detallep);
        valor += detallep.calcularPrecio();
        
    }
    
    public void agregarServicio(VentaServicio detalleS){
        detalles.add(detalleS);
        valor += detalleS.precioServicio();
    }
    
//    private void calcularValorTotal(){
//        
//    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LinkedList<Object> getDetalles() {
        return detalles;
    }

    public void setDetalles(LinkedList<Object> detalles) {
        this.detalles = detalles;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
    
}
