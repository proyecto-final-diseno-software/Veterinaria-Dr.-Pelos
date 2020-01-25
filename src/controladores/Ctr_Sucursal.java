/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Sucursal;

/**
 *
 * @author ADMIN
 */
public class Ctr_Sucursal {
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Ctr_Sucursal() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public Sucursal selectSucursal(int id){
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sucursal where sucursal_ID ="+id+";");
            if(rs.next()){
                String nombre = rs.getString(2);
                String direccion = rs.getString(3);
                boolean services = rs.getBoolean(4);
                Sucursal s = new Sucursal(id,nombre,direccion,services);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
