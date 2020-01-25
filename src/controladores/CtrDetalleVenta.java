/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DetalleVenta;
import modelo.DetalleVentaProducto;
import modelo.DetalleVentaServicio;
import modelo.Venta;

/**
 *
 * @author ADMIN
 */
public class CtrDetalleVenta {
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;

    public CtrDetalleVenta() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public boolean guardarDetalleVenta(DetalleVenta ventas){
        String separador = "";
        
        if(ventas.getDocumento() instanceof Venta)
            separador = "venta_ID";
        else
            separador = "cotizacion_ID";
        PreparedStatement ps = null;
        try {
            if(ventas instanceof DetalleVentaProducto){
                
                ps = con.prepareStatement("insert into DetalleVentaProducto(cantidad ,"+separador+", producto_ID) values(?, ?, ?);");
                
                ps.setInt(1, ventas.getCantidad());
                ps.setInt(2, ventas.getDocumento().getIdDocumento());
                ps.setInt(3, ((DetalleVentaProducto) ventas).getProducto().getIdProducto());
                    
                ps.executeUpdate();
                ps.close();
                
                return true;
            } else {
                
                ps = con.prepareStatement("insert into detalleventaservicio(cantidad ,"+separador+", servicio_ID) values(?, ?, ?);");
                
                ps.setInt(1, ventas.getCantidad());
                ps.setInt(2, ventas.getDocumento().getIdDocumento());
                ps.setInt(3, ((DetalleVentaServicio) ventas).getServicio().getIdServicio());
                
                ps.executeUpdate();
                ps.close();
                
                return true;
            }
        
        } catch (Exception ex) {
            Logger.getLogger(CtrDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            try {
                if(ps != null) ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
