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
public class Efectivo implements FormaPago{
    private int formaPagoID;
    private float impuesto;
    private String descripcion;
    private double cantidad_efectivo;

    public double getCantidad_efectivo() {
        return cantidad_efectivo;
    }

    public Efectivo(){
        
    }
    
    public Efectivo(float impuesto, double cantidad_efectivo) {
        this.descripcion  = "Efectivo";
        this.impuesto = impuesto;
        this.cantidad_efectivo = cantidad_efectivo;
    }
    
    @Override
    public boolean pagar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFormaPagoID(int formaPagoID) {
        this.formaPagoID = formaPagoID;
    }

    @Override
    public void registrar_monto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getId_FormaPago() {
        return formaPagoID;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCantidad_efectivo(double cantidad_efectivo) {
        this.cantidad_efectivo = cantidad_efectivo;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

    @Override
    public float getImpuesto() {
        return this.impuesto;
    }
    
    
}
