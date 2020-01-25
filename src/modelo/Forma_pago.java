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
public interface Forma_pago {
    boolean pagar();
    void registrar_monto();
    int getId_FormaPago();
    String getDescripcion();
    float getImpuesto();
}
