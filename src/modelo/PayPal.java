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
public class PayPal implements FormaPago{
    private int formaPagoID;
    private float impuesto;
    private String descripcion;
    
    private String correoElectronico;

    public PayPal(float impuesto, String correo_electronico) {
        this.impuesto = impuesto;
        this.correoElectronico = correo_electronico;
    }
    
    public PayPal(){}
    
    
    public boolean confirmarCorreo(){
        return true;
    }

    @Override
    public void setFormaPagoID(int forma_pago_ID) {
        this.formaPagoID = forma_pago_ID;
    }
    
    @Override
    public boolean pagar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
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
