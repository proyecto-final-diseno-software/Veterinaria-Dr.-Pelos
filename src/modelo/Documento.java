/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controladores.ControlFactura;
import java.time.LocalDate;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author ADMIN
 */
public abstract class Documento {
    protected int idDocumento;
    protected LocalDate fecha;
    protected int numeroFactura;
    protected PersonalCaja personalCaja;
    protected Cliente cliente;
    protected List<DetalleVenta> carrito;
    
    protected Documento(){}

    protected Documento(LocalDate fecha, int numeroFactura, PersonalCaja personalCaja, Cliente cliente, List<DetalleVenta> carrito) {
        this.fecha = fecha;
        this.numeroFactura = numeroFactura;
        this.personalCaja = personalCaja;
        this.cliente = cliente;
        this.carrito = carrito;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public List<DetalleVenta> getCarrito() {
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
        ControlFactura ctr = new ControlFactura();
        this.numeroFactura = ctr.obtenerNumeroFactura();
    }

    public PersonalCaja getPersonalCaja() {
        return personalCaja;
    }

    public void setPersonalCaja(PersonalCaja personalCaja) {
        this.personalCaja = personalCaja;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public abstract void calcularMonto(List<DetalleVenta> itemsCarrito);
    public abstract boolean comprobarValides();
    
    public void actualizarReferencias(){
        Iterator<DetalleVenta> it = carrito.iterator();
        
        while(it.hasNext()){
            DetalleVenta det = it.next();
            det.setDocumento(this);
        }
    }
}
