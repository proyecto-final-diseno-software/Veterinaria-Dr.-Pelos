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
    protected Sucursal remitente;
    protected Cliente destinatario;
    protected Ruta ruta;

    public Pedido(Sucursal remitente, Cliente destinatario, Ruta ruta) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.ruta = ruta;
    }

    public int getPedido_ID() {
        return pedido_ID;
    }

    public void setPedido_ID(int pedido_ID) {
        this.pedido_ID = pedido_ID;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
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
