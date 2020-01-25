/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controladores.Control_Factura;
import java.time.LocalDate;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author ADMIN
 */
public abstract class Documento {
    protected int id_documento;
    protected LocalDate fecha;
    protected int numeroFactura;
    protected Personal_Caja personalCaja;
    protected Cliente cliente;
    protected List<Detalle_Venta> carrito;
    
    protected Documento(){}

    protected Documento(LocalDate fecha, int numeroFactura, Personal_Caja personalCaja, Cliente cliente, List<Detalle_Venta> carrito) {
        this.fecha = fecha;
        this.numeroFactura = numeroFactura;
        this.personalCaja = personalCaja;
        this.cliente = cliente;
        this.carrito = carrito;
    }

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public List<Detalle_Venta> getCarrito() {
        return carrito;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura() {
        Control_Factura ctr = new Control_Factura();
        this.numeroFactura = ctr.obtenerNumeroFactura();
    }

    public Personal_Caja getPersonalCaja() {
        return personalCaja;
    }

    public void setPersonalCaja(Personal_Caja personalCaja) {
        this.personalCaja = personalCaja;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public abstract void calcularMonto(List<Detalle_Venta> itemsCarrito);
    public abstract boolean comprobarValides();
    
    public void actualizarReferencias(){
        Iterator<Detalle_Venta> it = carrito.iterator();
        
        while(it.hasNext()){
            Detalle_Venta det = it.next();
            det.setDocumento(this);
        }
    }
}
