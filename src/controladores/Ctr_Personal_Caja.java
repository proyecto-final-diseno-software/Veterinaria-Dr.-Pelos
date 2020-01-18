/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.Mascota;
import modelo.Producto;
import modelo.Servicio;
import modelo.Venta;
import view.tool.Tool;

/**
 *
 * @author paula
 */
public class Ctr_Personal_Caja {
    
    public void realizarCotizacion(){
        
    }
    
    private boolean insertMascota(Connection con, Mascota m){
        try {
            PreparedStatement ps = con.prepareStatement("insert into Mascota(mascota_ID,nombre,"
                    + "raza,estado,dueno_ID) values(?,?,?,?,?);");
            ps.setInt(1, m.getMascota_id());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getEstado());
            ps.setString(5, m.getCliente().getCedula());
            
            ps.executeUpdate();
            //Connection con1 = Ctr_BaseDatos.getConnection();
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    private boolean insertCotizacion(Connection con, Cotizacion c){
        try {
            PreparedStatement ps = con.prepareStatement("insert into venta(venta_ID,fecha,n_factura,sub_total,total,descuento,personal_cajas_ID, forma_pago_ID) values(?,?,?,?,?,?,?,?);");
            ps.setInt(1, c.getIdCotizacion());
            
            LocalDate fecha = c.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(2, fechasql);
            
            ps.setFloat(3, (float)c.getValor());
            ps.setString(4, c.getCliente().getCedula());
            //ps.setString(5, c.getPersonal_caja().getId_persona_caja());
            
            ps.executeUpdate();
            //Connection con1 = Ctr_BaseDatos.getConnection();
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    private boolean insertVenta(Connection con, Venta v){
        try {
            PreparedStatement ps = con.prepareStatement("insert into venta(venta_ID,fecha,n_factura,sub_total,total,descuento,personal_cajas_ID, forma_pago_ID) values(?,?,?,?,?,?,?,?);");
            ps.setInt(1, v.getId_venta());
            
            LocalDate fecha = v.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(2, fechasql);
            
            ps.setInt(3, v.getNumeroFactura());
            
            
            ps.setFloat(4, (float)v.getSubtotal());
            ps.setFloat(5, (float)v.getTotal() );
            ps.setFloat(6, (float)v.getDescuento());
            ps.setInt(7, v.getPersonalCaja().getId_persona_caja());
            
            ps.executeUpdate();
            //Connection con1 = Ctr_BaseDatos.getConnection();
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
            
    public Cliente createCliente(List<Tool> list){
        String cedula = (String) list.get(0).getValue();
        String nombre = (String) list.get(1).getValue();
        String apellido = (String) list.get(2).getValue();
        String direccion = (String) list.get(3).getValue();
        String numTelefonico = (String) list.get(4).getValue();
        
        return new Cliente(cedula, nombre, apellido, direccion, numTelefonico);
    }
    
    public boolean addClienteDataBase(Cliente cliente){
//        if(insertPersona(con, cliente)){
//            return insertCliente(con, cliente);
//        }
//        else{
//            return false;
//        }

//        
        return true;
    }
    
    private boolean insertPersona(Connection con, Cliente c){
        try {
            PreparedStatement ps = con.prepareStatement("insert into persona(cedula,nombre,apellido) values(?,?,?);");
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.executeUpdate();
            //Connection con1 = DBTools.getConnection();
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
        
    }
    
    private boolean insertCliente(Connection con, Cliente c){
        try {
            PreparedStatement ps = con.prepareStatement("insert into cliente(cedula,direccion,telefono) values(?,?,?);");
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getNum_telefonico());
            ps.executeUpdate();
            ps.close();
            //Connection con1 = DBTools.getConnection();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    public List<Producto> filtarProductos(List<Tool> toolsUsado){
        //Estos ya son los campos a filtara ya estan extraidos de la interfaz
        String nombreProducto = (String) toolsUsado.get(0).getValue();
        String categoriaProducto = (String) toolsUsado.get(0).getValue();
        String descripcionProducto = (String) toolsUsado.get(2).getValue();
        
        List<Producto> lista = new ArrayList<>();
        
        lista.add(new Producto("1", 12, "Papas", "Papas Ricas", new Categoria("Alimento", "Aliemntos")));
        lista.add(new Producto("2", 30, "Tortolines", "cHIFLES", new Categoria("Alimento", "Aliemntos")));
        
        return lista;
    }
    
    public List<Servicio> filtarServicio(List<Tool> toolsUsado){
        //Estos ya son los campos a filtara ya estan extraidos de la interfaz
        String nombreServicio = (String) toolsUsado.get(0).getValue();
        String descripcionServicio = (String) toolsUsado.get(2).getValue();
        
        List<Servicio> lista = new ArrayList<>();
        
        lista.add(new Servicio(1, "Corte de pelo", "Cortamos pelo", 20));
        lista.add(new Servicio(1, "Baño", "Lo bañamos", 40));
        
        return lista;
    }
    
}
