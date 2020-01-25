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
    private int pedidoID;
    protected String descripcion;
    protected Sucursal remitente;
    protected Cliente destinatario;
    protected Ruta ruta;

    public Pedido(String descripcion, Sucursal remitente, Cliente destinatario, Ruta ruta) {
        this.descripcion = descripcion;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.ruta = ruta;
    }

    public int getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public Sucursal getRemitente() {
        return remitente;
    }

    public void setRemitente(Sucursal remitente) {
        this.remitente = remitente;
    }

    public Cliente getDestinatario() {
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
    
    public boolean listaPedido(){
        return true;
    }
}
