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
public class Ctr_Cotizacion {
    private final Connection con;
    private final Ctr_BaseDatosProxy controlDataBase;
    private final Control_Factura controlFactura;

    public Ctr_Cotizacion() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
        this.controlFactura = new Control_Factura();
    }
    
    public boolean insertCotizacion(Cotizacion c){
        try {
            c.setId_documento(maxCotizacion()+1);
            controlFactura.insertFactura(c);
            
            PreparedStatement ps = con.prepareStatement("insert into Cotizacion(fecha, valor, cliente_ID, personal_caja_ID) values(?,?,?,?);");
       
            LocalDate fecha = c.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(1, fechasql);
            ps.setFloat(2, (float)c.getValor());
            ps.setString(3, c.getCliente().getCedula());
            ps.setString(4, c.getPersonalCaja().getCedula());
            
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    private int maxCotizacion(){
        int max = 0;
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(cotizacion_ID) from cotizacion");
            if(rs.next())
                max = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
}
