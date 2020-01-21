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
    private int pedido_ID;
    protected String descripcion;
    protected Sucursal remitente;
    protected Cliente destinatario;
    protected Ruta ruta;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Object getRemitente() {
        return remitente;
    }

    public void setRemitente(Sucursal remitente) {
        this.remitente = remitente;
    }

    public Object getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cliente destinatario) {
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
