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
import modelo.Ruta;

/**
 *
 * @author paula
 */
public class Ctr_Ruta {
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Ctr_Ruta() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public int maxRuta(){
        int max = 0;
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(ruta_id) from ruta;");
            if(rs.next())
                max = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
        
    }
    
    public boolean insertRuta(Ruta ruta){
        try {
            ruta.setId_ruta(maxRuta() + 1);
            
            String q = "insert into Ruta(ruta_ID,direccion) values(?,?);";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1,ruta.getId_ruta());
            ps.setString(2,ruta.getDireccion());
            ps.executeUpdate();
            ps.close();
            
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
}
