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
public class Control_Factura {
    private final Connection con;
    private final Ctr_BaseDatosProxy controlDataBase;

    public Control_Factura() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public int obtenerNumeroFactura(){
        int max = 0;
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(n_factura) from Factura");
            if(rs.next())
                max = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max + 1;
    }
    
    public boolean insertFactura(Documento doc){
        try {
            PreparedStatement ps = con.prepareStatement("insert into Factura(n_factura,fecha) values(?,?);");
            
            ps.setInt(1, doc.getNumeroFactura());
            LocalDate fecha = doc.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(2, fechasql);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
