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
public class Ctr_Pedido {
    private final Connection con;
    private final Ctr_BaseDatosProxy controlDataBase;
    private final Ctr_Ruta controlRuta;
    
    public Ctr_Pedido(){
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
        this.controlRuta = new Ctr_Ruta();
    }
    
    public int maxPedido(){
        int max = 0;
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(pedido_ID) from pedido;");
            if(rs.next())
                max = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
    
    public boolean insertPedido(Pedido pedido){
        try {
            this.controlRuta.insertRuta(pedido.getRuta());
            
            pedido.setPedido_ID(maxPedido() + 1);
            
            String q = "insert into Pedido(pedido_ID,descripcion,remitente_ID,destinatario_ID,ruta_ID) values(?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, pedido.getPedido_ID());
            ps.setString(2, pedido.getDescripcion());
            ps.setInt(3, pedido.getRemitente().getId_sucursal());
            ps.setString(4, pedido.getDestinatario().getCedula());
            ps.setInt(5, pedido.getRuta().getId_ruta());
            ps.executeUpdate();
            ps.close();
            
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
}
