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
public class Pedido_Traslado extends Pedido{
    private int id_pedidoTraslado;
    private Jefe_Bodega jefeBodega;

    public Pedido_Traslado(Sucursal remitente, Cliente destinatario, Ruta ruta) {
        super(remitente, destinatario, ruta);
    }
    
    
}
