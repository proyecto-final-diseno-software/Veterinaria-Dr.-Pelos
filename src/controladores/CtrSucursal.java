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
public class CtrSucursal {
    private Connection con;
    private CtrBaseDatosProxy controlDataBase;

    public CtrSucursal() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public Sucursal selectSucursal(int id){
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
             rs = stmt.executeQuery("select * from sucursal where sucursal_ID ="+id+";");
            if(rs.next()){
                String nombre = rs.getString(2);
                String direccion = rs.getString(3);
                boolean services = rs.getBoolean(4);
                return new Sucursal(id,nombre,direccion,services);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CtrSucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
