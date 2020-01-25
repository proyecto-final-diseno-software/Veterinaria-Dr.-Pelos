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
import modelo.Cotizacion;

/**
 *
 * @author ADMIN
 */
public class CtrCotizacion {
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;
    private final ControlFactura controlFactura;

    public CtrCotizacion() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
        this.controlFactura = new ControlFactura();
    }
    
    public boolean insertCotizacion(Cotizacion c){
            c.setIdDocumento(maxCotizacion()+1);
            controlFactura.insertFactura(c);
            String q = "insert into Cotizacion(fecha, valor, cliente_ID, personal_caja_ID) values(?,?,?,?);";
        try (PreparedStatement ps = con.prepareStatement(q)) {
            
            LocalDate fecha = c.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(1, fechasql);
            ps.setFloat(2, (float)c.getValor());
            ps.setString(3, c.getCliente().getCedula());
            ps.setString(4, c.getPersonalCaja().getCedula());
            
            ps.executeUpdate();
            return true;
            
        } catch (Exception ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    private int maxCotizacion(){
        int max = 0;
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
            rs = stmt.executeQuery("select max(cotizacion_ID) from cotizacion");
            if(rs.next())
                max = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null)rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return max;
    }
}
