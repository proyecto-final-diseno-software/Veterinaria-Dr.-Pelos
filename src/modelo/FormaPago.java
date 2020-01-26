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
public interface FormaPago {
    boolean pagar();
    void registrarMonto();
    int getIdFormaPago();
    String getDescripcion();
    float getImpuesto();
    void setFormaPagoID(int forma_pago_ID);
}
