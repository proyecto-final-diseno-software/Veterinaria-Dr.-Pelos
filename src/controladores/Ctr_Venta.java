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
import modelo.Efectivo;
import modelo.Forma_pago;
import modelo.PayPal;
import modelo.Tarjeta;
import modelo.Venta;

/**
 *
 * @author ADMIN
 */
public class Ctr_Venta {
    private final Connection con;
    private final Ctr_BaseDatosProxy controlDataBase;
    private final Ctr_Pedido controlPedido;

    public Ctr_Venta() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
        this.controlPedido = new Ctr_Pedido();
    }
    
    private int maxVenta(){
        int max = 0;
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(venta_ID) from Venta");
            if(rs.next())
                max = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
    
    public boolean insertVenta(Venta v){
        try {
            insertForma_Pago(v.getForma_pago_ID());
            
            if(v.getPedido() != null)
                this.controlPedido.insertPedido(v.getPedido());
            
            v.setId_documento(maxVenta() + 1);
            
            PreparedStatement ps = con.prepareStatement("insert into venta(fecha, n_factura, sub_total, total, descuento, personal_cajas_ID, forma_pago_ID, id_cliente, pedido_ID) values(?,?,?,?,?,?,?,?,?);");
            
            LocalDate fecha = v.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(1, fechasql);
            ps.setInt(2, v.getNumeroFactura());
            ps.setFloat(3, (float)v.getSubtotal());
            ps.setFloat(4, (float)v.getTotal() );
            ps.setFloat(5, (float)v.getDescuento());
            ps.setString(6, v.getPersonalCaja().getCedula());
            ps.setInt(7, v.getForma_pago_ID().getId_FormaPago());
            ps.setString(8, v.getCliente().getCedula());
            if(v.getPedido() != null)
                ps.setInt(9, v.getPedido().getPedido_ID());
            else
                ps.setNull(9, 0);
            
            ps.executeUpdate();
            
            ps.close();
            
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    private int maxForma_Pago(){
        int max = 0;
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(forma_pago_ID) from Forma_Pago");
            if(rs.next())
                max = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
    
     private boolean insertForma_Pago(Forma_pago formpago){
        try {
            formpago.setForma_pago_ID(maxForma_Pago() + 1);
            
            PreparedStatement ps = con.prepareStatement("insert into Forma_Pago(forma_pago_ID,impuesto,descripcion) values(?,?,?);");
            
            ps.setFloat(1, formpago.getId_FormaPago());
            ps.setFloat(2, formpago.getImpuesto());
            ps.setString(3, formpago.getDescripcion());
            
            ps.executeUpdate();
            ps.close();
            
            if(formpago instanceof Efectivo)
                return insertEfectivo(formpago);
            
            if(formpago instanceof PayPal)
                return insertPaypal(formpago);
            
            if(formpago instanceof Tarjeta)
                return insertTarjeta(formpago);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
        return false;
    }
     
     
    private boolean insertEfectivo(Forma_pago formpago){
        try {
            Efectivo efectivo = (Efectivo) formpago;
            PreparedStatement ps = con.prepareStatement("insert into Pago_Efectivo(efectivo_ID,cantidad_efectivo) values(?,?);");
            
            ps.setInt(1, efectivo.getId_FormaPago());
            ps.setFloat(2,(float) efectivo.getCantidad_efectivo());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private boolean insertTarjeta(Forma_pago formpago){
        try {
            Tarjeta pago = (Tarjeta) formpago;
            PreparedStatement ps = con.prepareStatement("insert into Pago_Tarjeta(tarjeta_ID,num_cuenta) values(?,?);");
            
            ps.setInt(1, pago.getId_FormaPago());
            ps.setString(2, pago.getNum_cuenta());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
    private boolean insertPaypal(Forma_pago formpago){
        try {
            PayPal pago = (PayPal) formpago;
            PreparedStatement ps = con.prepareStatement("insert into Pago_PayPal(payPal_ID,correoElectronico) values(?,?);");
            
            ps.setInt(1, pago.getId_FormaPago());
            ps.setString(2, pago.getCorreo_electronico());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
