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
import modelo.Detalle_Venta;
import modelo.Mascota;
import modelo.Personal_Caja;
import modelo.Producto;
import modelo.Servicio;
import modelo.Sucursal;
import modelo.Venta;
import view.tool.Tool;

/**
 *
 * @author paula
 */
public class Ctr_Personal_Caja implements Control_Session{
    private final Connection con;
    private final Ctr_BaseDatosProxy controlDataBase;
    private final Ctr_Cliente controlCliente;
    private final Ctr_Sucursal controlSucursal;
    private final Ctr_Mascota controlMascota;
    private final Ctr_Cotizacion controlCotizacion;
    private final Ctr_Venta controlVenta;
    private final Ctr_DetalleVenta controlDetalleVenta;
    private final Ctr_Productos controlProducto;
    private final Ctr_Servicios controlServicios;
    private final Ctr_Categorias controlCategorias;
    
    public Ctr_Personal_Caja() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
        this.controlCliente = new Ctr_Cliente();
        this.controlSucursal = new Ctr_Sucursal();
        this.controlMascota = new Ctr_Mascota();
        this.controlCotizacion = new Ctr_Cotizacion();
        this.controlVenta = new Ctr_Venta();
        this.controlDetalleVenta = new Ctr_DetalleVenta();
        this.controlProducto = new Ctr_Productos();
        this.controlServicios = new Ctr_Servicios();
        this.controlCategorias = new Ctr_Categorias();
    }
    
    public Personal_Caja selectPersonalCaja(String usuario){
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select usuario,usuario_ID,personal_caja.sucursal_ID,persona.nombre,persona.apellido from usuario\n" +
                "  join persona on usuario.usuario_ID = persona.cedula\n" +
                "  join personal_caja on persona.cedula = personal_caja.cedula\n" +
                "  join sucursal on personal_caja.sucursal_ID = sucursal.sucursal_ID\n" +
                "  where Usuario.usuario=\"" + usuario + "\";");
            if(rs.next()){
                String cedula = rs.getString(2);
                int sucursal_id = rs.getInt(3);
                String nombre = rs.getString(4);                
                String apellido = rs.getString(5);
                Sucursal s = selectSucursal(sucursal_id);
                Personal_Caja personal = new Personal_Caja(cedula,nombre,apellido,s);
                return personal;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public UserType verificarSesion(String cedula) {
        Statement stmt;
        try {
            stmt = con.createStatement();
            String q = "select usuario_ID from Usuario join Personal_Caja on Usuario.usuario_ID = Personal_Caja.cedula where Usuario.usuario_ID =\""+cedula+"\"";
            ResultSet rs = stmt.executeQuery(q);
            if(rs.next()){
                return UserType.PERSONAL_CAJA;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Sucursal selectSucursal(int id){
        return this.controlSucursal.selectSucursal(id);
    }
    
    public boolean insertMascota(Mascota m){
        return this.controlMascota.insertMascota(m);
    }
    
    public boolean setEstadoMascota(Mascota mas){
        return this.controlMascota.setEstadoMascota(mas);
    }
    
    public boolean insertCotizacion(Cotizacion c){
        return this.controlCotizacion.insertCotizacion(c);
    }
    
    public boolean insertVenta(Venta v){
        return this.controlVenta.insertVenta(v);
    }
    
    public boolean guardarDetalleVenta(Detalle_Venta ventas){
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
        Statement stmt;
        try {
            stmt = con.createStatement();
            String q = "select * from V_Cotizacion where cliente_ID = \""+c.getCedula()+"\";";
            ResultSet rs = stmt.executeQuery(q);
            while(rs.next()){
                int cotizacion_ID = rs.getInt(1);
                Date fecha = rs.getDate(2);
                LocalDate f_nueva = fecha.toLocalDate();
                float valor = rs.getFloat(3);
                String user_caja = rs.getString(5);
                
                Cotizacion cotizacion = new Cotizacion();
                cotizacion.setId_documento(cotizacion_ID);
                cotizacion.setValor(valor);
                cotizacion.setFecha(f_nueva);
                cotizacion.setCliente(c);
                cotizacion.setPersonalCaja(this.selectPersonalCaja(user_caja));
                lista.add(cotizacion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public List<Venta> selectVenta(Cliente c){
        List<Venta> lista = new ArrayList<>();
        Statement stmt;
        try {
            stmt = con.createStatement();
            String q = "select * from V_Ventas where id_cliente = \""+c.getCedula()+"\";";
            ResultSet rs = stmt.executeQuery(q);
            while(rs.next()){
                int venta_ID = rs.getInt(1);
                Date fecha = rs.getDate(2);
                LocalDate f_nueva = fecha.toLocalDate();
                int n_factura = rs.getInt(3);
                float sub_total = rs.getFloat(4);
                float total = rs.getFloat(5);
                float descuento = rs.getFloat(6);
                String user_caja = rs.getString(15);
                
                Venta v = new Venta();
                v.setId_documento(venta_ID);
                v.setDescuento(descuento);
                v.setSubtotal(sub_total);
                v.setTotal(total);
                v.setFecha(f_nueva);
                v.setCliente(c);
                v.setPersonalCaja(this.selectPersonalCaja(user_caja));
                lista.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
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
