/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Documento;

/**
 *
 * @author ADMIN
 */
public class ControlFactura {
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;

    public ControlFactura() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public int obtenerNumeroFactura(){
        int max = 0;
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
             rs = stmt.executeQuery("select max(n_factura) from Factura");
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
                Logger.getLogger(ControlFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return max + 1;
    }
    
    public boolean insertFactura(Documento doc){
        try (PreparedStatement ps = con.prepareStatement("insert into Factura(n_factura,fecha) values(?,?);")) {
            
            ps.setInt(1, doc.getNumeroFactura());
            LocalDate fecha = doc.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(2, fechasql);
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
                Logger.getLogger(ControlFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
