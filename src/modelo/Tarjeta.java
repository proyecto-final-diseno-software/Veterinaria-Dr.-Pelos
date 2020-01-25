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
    
    private String num_cuenta;

    public Tarjeta(float impuesto, String num_cuenta) {
        this.impuesto = impuesto;
        this.num_cuenta = num_cuenta;
    }
    
    public Tarjeta(){}
    
    @Override
    public boolean pagar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrar_monto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean validarTarjeta(){
        return true;
    }

    @Override
    public int getId_FormaPago() {
        return formaPagoID;
    }

    @Override
    public void setFormaPagoID(int forma_pago_ID) {
        this.formaPagoID = forma_pago_ID;
    }
    
    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public String getNum_cuenta() {
        return num_cuenta;
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
