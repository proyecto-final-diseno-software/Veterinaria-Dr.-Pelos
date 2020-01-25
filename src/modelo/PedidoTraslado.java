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
public class PedidoTraslado extends Pedido{
    private int idPedidoTraslado;
    private JefeBodega jefeBodega;

    public PedidoTraslado(String descripcion, Sucursal remitente, Cliente destinatario, Ruta ruta) {
        super(descripcion, remitente, destinatario, ruta);
    }

    public int getIdPedidoTraslado() {
        return idPedidoTraslado;
    }

    public void setIdPedidoTraslado(int idPedidoTraslado) {
        this.idPedidoTraslado = idPedidoTraslado;
    }

    public JefeBodega getJefeBodega() {
        return jefeBodega;
    }

    public void setJefeBodega(JefeBodega jefeBodega) {
        this.jefeBodega = jefeBodega;
    }
    
    
}
