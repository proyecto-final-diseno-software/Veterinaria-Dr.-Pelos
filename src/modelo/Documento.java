/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.List;

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

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }
}
