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
public class CtrRuta {
    private Connection con;
    private CtrBaseDatosProxy controlDataBase;

    public CtrRuta() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public int maxRuta(){
        int max = 0;
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
             rs = stmt.executeQuery("select max(ruta_id) from ruta;");
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
                Logger.getLogger(CtrRuta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return max;
    }
    
    public boolean insertRuta(Ruta ruta){
            ruta.setIdRuta(maxRuta() + 1);
            String q = "insert into Ruta(ruta_ID,direccion) values(?,?);";
        try (PreparedStatement ps = con.prepareStatement(q)) {
            ps.setInt(1,ruta.getIdRuta());
            ps.setString(2,ruta.getDireccion());
            ps.executeUpdate();
            return true;
            
        } catch (Exception ex) {
            Logger.getLogger(CtrRuta.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        } 
    }
    
}
