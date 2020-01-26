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
import modelo.PayPal;
import modelo.Tarjeta;
import modelo.Venta;
import modelo.FormaPago;

/**
 *
 * @author ADMIN
 */
public class CtrVenta {
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;
    private final CtrPedido controlPedido;
    private final ControlFactura controlFactura;

    public CtrVenta() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
        this.controlPedido = new CtrPedido();
        this.controlFactura = new ControlFactura();
    }
    
    private int maxVenta(){
        int max = 0;
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
             rs = stmt.executeQuery("select max(venta_ID) from Venta");
            if(rs.next())
                max = rs.getInt(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                if(rs!= null)rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return max;
    }
    
    public boolean insertVenta(Venta v){
        insertFormaPago(v.getFormaPagoID());
        controlFactura.insertFactura(v);
        if(v.getPedido() != null)
            this.controlPedido.insertPedido(v.getPedido());
        v.setIdDocumento(maxVenta() + 1);
        try (PreparedStatement ps = con.prepareStatement("insert into venta(fecha, n_factura, sub_total, total, descuento, personal_cajas_ID, forma_pago_ID, id_cliente, pedido_ID) values(?,?,?,?,?,?,?,?,?);");
) {
            LocalDate fecha = v.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(1, fechasql);
            ps.setInt(2, v.getNumeroFactura());
            ps.setFloat(3, (float)v.getSubtotal());
            ps.setFloat(4, (float)v.getTotal() );
            ps.setFloat(5, (float)v.getDescuento());
            ps.setString(6, v.getPersonalCaja().getCedula());
            ps.setInt(7, v.getFormaPagoID().getIdFormaPago());
            ps.setString(8, v.getCliente().getCedula());
            if(v.getPedido() != null)
                ps.setInt(9, v.getPedido().getPedidoID());
            else
                ps.setNull(9, 0);
            
            ps.executeUpdate();
            return true;
            
        } catch (Exception ex) {
                Logger.getLogger(CtrVenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }
    
    private int maxFormaPago(){
        int max = 0;
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()){
             rs = stmt.executeQuery("select max(forma_pago_ID) from Forma_Pago");
            if(rs.next())
                max = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs!= null)rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return max;
    }
    
     private boolean insertFormaPago(FormaPago formpago){
        formpago.setFormaPagoID(maxFormaPago() + 1);
        try (PreparedStatement ps = con.prepareStatement("insert into Forma_Pago(forma_pago_ID,impuesto,descripcion) values(?,?,?);");
) {
            ps.setFloat(1, formpago.getIdFormaPago());
            ps.setFloat(2, formpago.getImpuesto());
            ps.setString(3, formpago.getDescripcion());
            
            ps.executeUpdate();
            
            if(formpago instanceof Efectivo)
                return insertEfectivo(formpago);
            
            if(formpago instanceof PayPal)
                return insertPaypal(formpago);
            
            if(formpago instanceof Tarjeta)
                return insertTarjeta(formpago);
            
        } catch (Exception ex) {
            Logger.getLogger(CtrVenta.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return false;
    }
     
     
    private boolean insertEfectivo(FormaPago formpago){
        
            Efectivo efectivo = (Efectivo) formpago;
        try (PreparedStatement ps = con.prepareStatement("insert into Pago_Efectivo(efectivo_ID,cantidad_efectivo) values(?,?);");
) {
            
            ps.setInt(1, efectivo.getIdFormaPago());
            ps.setFloat(2,(float) efectivo.getCantidadEfectivo());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CtrVenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private boolean insertTarjeta(FormaPago formpago){
        
            Tarjeta pago = (Tarjeta) formpago;
        try (PreparedStatement ps = con.prepareStatement("insert into Pago_Tarjeta(tarjeta_ID,num_cuenta) values(?,?);")) {
            ps.setInt(1, pago.getIdFormaPago());
            ps.setString(2, pago.getNumCuenta());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CtrVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    private boolean insertPaypal(FormaPago formpago){
        PayPal pago = (PayPal) formpago;
        try (PreparedStatement ps = con.prepareStatement("insert into Pago_PayPal(payPal_ID,correoElectronico) values(?,?);");
)
            {
            ps.setInt(1, pago.getIdFormaPago());
            ps.setString(2, pago.getCorreoElectronico());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CtrVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
