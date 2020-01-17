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
public class Pedido {
    protected String descripcion;
    protected Object remitente;
    protected Object destinatario;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Object getRemitente() {
        return remitente;
    }

    public void setRemitente(Object remitente) {
        this.remitente = remitente;
    }

    public Object getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Object destinatario) {
        this.destinatario = destinatario;
    }
    
    
    
    public Pedido agregarPedido(){
        return null;
    }
    
    public boolean eliminarPedido(){
        return true;
    }
    
    //REV
    public boolean listaPedido(){
        return true;
    }
    
}
