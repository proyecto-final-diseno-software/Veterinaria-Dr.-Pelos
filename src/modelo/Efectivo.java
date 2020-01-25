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
public class Efectivo implements Forma_pago{
    private int forma_pago_ID;
    private float impuesto;
    private String descripcion;
    
    private double cantidad_efectivo;
    
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
        return 123;
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
    
    
}
