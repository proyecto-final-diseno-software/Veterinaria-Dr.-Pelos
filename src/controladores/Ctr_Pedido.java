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
import modelo.Cliente;
import modelo.Pedido;
import modelo.Sucursal;

/**
 *
 * @author paula
 */
public class Ctr_Pedido {
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;
    
    public Ctr_Pedido(){
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
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
            String q = "insert into Pedido(pedido_ID,descripcion,remitente_ID,destinatario_ID,ruta_ID) values(default,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(q);;
            ps.setString(1, pedido.getDescripcion());
            ps.setInt(2, pedido.getRemitente().getId_sucursal());
            ps.setString(3, pedido.getDestinatario().getCedula());
            ps.setInt(4, pedido.getRuta().getId_ruta());
            ps.executeUpdate();
            ps.close();
            
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
}
