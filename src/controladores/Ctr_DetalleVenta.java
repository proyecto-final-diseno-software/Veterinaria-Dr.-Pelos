/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.Detalle_Venta;
import modelo.Detalle_VentaProducto;
import modelo.Detalle_VentaServicio;
import modelo.Venta;

/**
 *
 * @author ADMIN
 */
public class Ctr_DetalleVenta {
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Ctr_DetalleVenta() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public boolean guardarDetalleVenta(Detalle_Venta ventas){
        String separador = "";
        
        if(ventas.getDocumento() instanceof Venta)
            separador = "venta_ID";
        else
            separador = "cotizacion_ID";
                
        try {
            if(ventas instanceof Detalle_VentaProducto){
                
                PreparedStatement ps = con.prepareStatement("insert into DetalleVentaProducto(cantidad ,"+separador+", producto_ID) values(?, ?, ?);");
                
                ps.setInt(1, ventas.getCantidad());
                ps.setInt(2, ventas.getDocumento().getId_documento());
                ps.setInt(3, ((Detalle_VentaProducto) ventas).getProducto().getId_producto());
                    
                ps.executeUpdate();
                ps.close();
                
                return true;
            } else {
                
                PreparedStatement ps = con.prepareStatement("insert into detalleventaservicio(cantidad ,"+separador+", servicio_ID) values(?, ?, ?);");
                
                ps.setInt(1, ventas.getCantidad());
                ps.setInt(2, ventas.getDocumento().getId_documento());
                ps.setInt(3, ((Detalle_VentaServicio) ventas).getServicio().getId_servicio());
                
                ps.executeUpdate();
                ps.close();
                
                return true;
            }
        
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
}
