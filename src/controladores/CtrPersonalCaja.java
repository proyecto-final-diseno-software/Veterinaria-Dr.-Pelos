/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.DetalleVenta;
import modelo.Mascota;
import modelo.PersonalCaja;
import modelo.Producto;
import modelo.Servicio;
import modelo.Sucursal;
import modelo.Venta;
import view.tool.Tool;

/**
 *
 * @author paula
 */
public class CtrPersonalCaja implements ControlSession{
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;
    private final CtrCliente controlCliente;
    private final CtrSucursal controlSucursal;
    private final CtrMascota controlMascota;
    private final CtrCotizacion controlCotizacion;
    private final CtrVenta controlVenta;
    private final CtrDetalleVenta controlDetalleVenta;
    private final CtrProductos controlProducto;
    private final CtrServicios controlServicios;
    private final CtrCategorias controlCategorias;
    
    public CtrPersonalCaja() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
        this.controlCliente = new CtrCliente();
        this.controlSucursal = new CtrSucursal();
        this.controlMascota = new CtrMascota();
        this.controlCotizacion = new CtrCotizacion();
        this.controlVenta = new CtrVenta();
        this.controlDetalleVenta = new CtrDetalleVenta();
        this.controlProducto = new CtrProductos();
        this.controlServicios = new CtrServicios();
        this.controlCategorias = new CtrCategorias();
    }
    
    public PersonalCaja selectPersonalCaja(String usuario){
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
             rs = stmt.executeQuery("select usuario,usuario_ID,personal_caja.sucursal_ID,persona.nombre,persona.apellido from usuario\n" +
                "  join persona on usuario.usuario_ID = persona.cedula\n" +
                "  join personal_caja on persona.cedula = personal_caja.cedula\n" +
                "  join sucursal on personal_caja.sucursal_ID = sucursal.sucursal_ID\n" +
                "  where Usuario.usuario=\"" + usuario + "\";");
            if(rs.next()){
                String cedula = rs.getString(2);
                int sucursalId = rs.getInt(3);
                String nombre = rs.getString(4);                
                String apellido = rs.getString(5);
                Sucursal s = selectSucursal(sucursalId);
                return new PersonalCaja(cedula,nombre,apellido,s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs!= null)rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @Override
    public UserType verificarSesion(String cedula) {
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
            String q = "select usuario_ID from Usuario join Personal_Caja on Usuario.usuario_ID = Personal_Caja.cedula where Usuario.usuario_ID =\""+cedula+"\"";
            rs = stmt.executeQuery(q);
            if(rs.next()){
                return UserType.PERSONAL_CAJA;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null)rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public Sucursal selectSucursal(int id){
        return this.controlSucursal.selectSucursal(id);
    }
    
    public boolean insertMascota(Mascota m){
        return this.controlMascota.insertMascota(m);
    }
    
    public boolean setEstadoMascota(Mascota mas, String nuevoEstado){
        return this.controlMascota.setEstadoMascota(mas, nuevoEstado);
    }
    
    public boolean insertCotizacion(Cotizacion c){
        return this.controlCotizacion.insertCotizacion(c);
    }
    
    public boolean insertVenta(Venta v){
        return this.controlVenta.insertVenta(v);
    }
    
    public boolean guardarDetalleVenta(DetalleVenta ventas){
        return this.controlDetalleVenta.guardarDetalleVenta(ventas);
    }
            
    public Cliente createCliente(List<Tool> list){
        return controlCliente.createCliente(list);
    }
    
    public boolean addClienteDataBase(Cliente cliente){
        if(controlCliente.insertPersona(cliente)){
            return controlCliente.insertCliente( cliente);
        }
        return false;
    }
    
    public List<Producto> filtarProductos(List<Tool> toolsUsado){
        return this.controlProducto.filtarProductos(toolsUsado);
    }
    
    public List<Cliente> selectCliente(String cedula){
        return this.controlCliente.selectCliente(cedula);
    }
    
    public List<Servicio> filtarServicio(List<Tool> toolsUsado){
        return this.controlServicios.filtarServicio(toolsUsado);
    }
    
    public List<Cotizacion> selectCotizacion(Cliente c){
        List<Cotizacion> lista = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String q = "select * from V_Cotizacion where cliente_ID = \""+c.getCedula()+"\";";
            try(ResultSet rs = stmt.executeQuery(q)){
                while(rs.next()){
                int cotizacionID = rs.getInt(1);
                Date fecha = rs.getDate(2);
                LocalDate fNueva = fecha.toLocalDate();
                float valor = rs.getFloat(3);
                String userCaja = rs.getString(5);
                
                Cotizacion cotizacion = new Cotizacion();
                cotizacion.setIdDocumento(cotizacionID);
                cotizacion.setValor(valor);
                cotizacion.setFecha(fNueva);
                cotizacion.setCliente(c);
                cotizacion.setPersonalCaja(this.selectPersonalCaja(userCaja));
                lista.add(cotizacion);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(stmt != null)stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
    public List<Venta> selectVenta(Cliente c){
        List<Venta> lista = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            String q = "select * from V_Ventas where id_cliente = \""+c.getCedula()+"\";";
            try (ResultSet rs = stmt.executeQuery(q)) {
            while(rs.next()){
                int ventaID = rs.getInt(1);
                Date fecha = rs.getDate(2);
                LocalDate fNueva = fecha.toLocalDate();
                int nFactura = rs.getInt(3);
                float subTotal = rs.getFloat(4);
                float total = rs.getFloat(5);
                float descuento = rs.getFloat(6);
                String userCaja = rs.getString(15);
                
                Venta v = new Venta();
                v.setNumeroFactura(nFactura);
                v.setIdDocumento(ventaID);
                v.setDescuento(descuento);
                v.setSubtotal(subTotal);
                v.setTotal(total);
                v.setFecha(fNueva);
                v.setCliente(c);
                v.setPersonalCaja(this.selectPersonalCaja(userCaja));
                lista.add(v);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(stmt != null)stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return lista;
    }
    
    public List<Mascota> selectMascotasCliente(Cliente cli){
        return this.controlCliente.selectMascotasCliente(cli);
    }
    
    public List<Categoria> selectAllCategorias(){
        return this.controlCategorias.selectAllCategorias();
    }
}
