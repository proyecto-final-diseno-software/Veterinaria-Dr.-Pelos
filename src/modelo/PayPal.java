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
public class PayPal implements Forma_pago{
    private int forma_pago_ID;
    private float impuesto;
    private String descripcion;
    private String correo_electronico;

    public PayPal(float impuesto, String correo_electronico) {
        this.descripcion = "PayPal";
        this.impuesto = impuesto;
        this.correo_electronico = correo_electronico;
    }
    
    public PayPal(){
        
    }
    
    
    public boolean confirmarCorreo(){
        return true;
    }

    public int getForma_pago_ID() {
        return forma_pago_ID;
    }

    public void setForma_pago_ID(int forma_pago_ID) {
        this.forma_pago_ID = forma_pago_ID;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
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
