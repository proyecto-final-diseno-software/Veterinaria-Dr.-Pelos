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
public class Tarjeta implements FormaPago {
    private int formaPagoID;
    private float impuesto;
    private String descripcion;
    
    private String numCuenta;

    public Tarjeta(float impuesto, String num_cuenta) {
        this.impuesto = impuesto;
        this.numCuenta = num_cuenta;
    }
    
    public Tarjeta(){}
    
    @Override
    public boolean pagar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarMonto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean validarTarjeta(){
        return true;
    }

    @Override
    public int getIdFormaPago() {
        return formaPagoID;
    }

    @Override
    public void setFormaPagoID(int formaPagoID) {
        this.formaPagoID = formaPagoID;
    }
    
    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNumCuenta(String num_cuenta) {
        this.numCuenta = num_cuenta;
    }

    public String getNumCuenta() {
        return numCuenta;
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
