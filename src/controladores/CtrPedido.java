/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pedido;

/**
 *
 * @author paula
 */
public class CtrPedido {
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;
    private final CtrRuta controlRuta;
    
    public CtrPedido(){
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
        this.controlRuta = new CtrRuta();
    }
    
    public int maxPedido(){
        int max = 0;
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
             rs = stmt.executeQuery("select max(pedido_ID) from pedido;");
            if(rs.next())
                max = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CtrPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return max;
    }
    
    public boolean insertPedido(Pedido pedido){
        
        this.controlRuta.insertRuta(pedido.getRuta());
        pedido.setPedidoID(maxPedido() + 1);
        String q = "insert into Pedido(pedido_ID,descripcion,remitente_ID,destinatario_ID,ruta_ID) values(?,?,?,?,?);";

        try (PreparedStatement ps = con.prepareStatement(q)) {
            
            ps.setInt(1, pedido.getPedidoID());
            ps.setString(2, pedido.getDescripcion());
            ps.setInt(3, pedido.getRemitente().getIdSucursal());
            ps.setString(4, pedido.getDestinatario().getCedula());
            ps.setInt(5, pedido.getRuta().getIdRuta());
            ps.executeUpdate();
            
            return true;
            
        } catch (Exception ex) {
            Logger.getLogger(CtrPedido.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
}
