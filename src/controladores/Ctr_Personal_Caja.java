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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.Detalle_Venta;
import modelo.Mascota;
import modelo.Persona;
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
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Ctr_Personal_Caja() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public void realizarCotizacion(){
        
    }
    
    //Devielde el personal de caja con un numero usuario de la tabla usuarios recivido
    public Personal_Caja selectPersonalCaja(String ususario){
        return new Personal_Caja("1234567890", "Filomeno", "Gonzalez", new Sucursal(1, "Mi casa", "Cerca de mi casa", true));
    }
    
    private boolean insertMascota(Mascota m){
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
    
    //actualiza el estado de la mascota
    public boolean setEstadoMascota(Mascota mas){
        return true;
    }
    
    private boolean insertCotizacion(Cotizacion c){
        try {
            PreparedStatement ps = con.prepareStatement("insert into Cotizacion(cotizacion_ID,fecha,valor,cliente_ID,personal_cajas_ID) values(?,?,?,?,?);");
            ps.setInt(1, c.getIdCotizacion());
            LocalDate fecha = c.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(2, fechasql);
            ps.setFloat(3, (float)c.getValor());
            ps.setString(4, c.getCliente().getCedula());
            ps.setString(5, c.getPersonal_caja().getCedula());
            
            ps.executeUpdate();
            //Connection con1 = Ctr_BaseDatos.getConnection();
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    public boolean insertVenta(Venta v){
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
            ps.setString(7, v.getPersonalCaja().getCedula());
            
            ps.executeUpdate();
            //Connection con1 = Ctr_BaseDatos.getConnection();
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    //En este metodo guarde los detaller de denta dependiendo si es un detalle de venta de producto o un detalle de venta de servicios para solucionar la relacion de muchos a muchos
    public boolean guardarDetalleVenta(Detalle_Venta ventas){
        return true;
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
        if(insertPersona(cliente)){
            return insertCliente( cliente);
        }
        return false;
    }
    
    private boolean insertPersona(Cliente c){
        try {
            PreparedStatement ps = con.prepareStatement("insert into persona(cedula,nombre,apellido) values(?,?,?);");
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private boolean insertCliente(Cliente c){
        try {
            PreparedStatement ps = con.prepareStatement("insert into cliente(cedula,direccion,telefono) values(?,?,?);");
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getNum_telefonico());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<Producto> filtarProductos(List<Tool> toolsUsado){
        List<Producto> lista = new ArrayList<>();
        
        String nombreProducto = "";
        String categoriaProducto = "";
        String descripcionProducto = "";
        
        String stbuscar = "select * from V_Productos where";
        String separado = "";
        
        if(!toolsUsado.get(0).isEmplyTool()){
            nombreProducto = (String) toolsUsado.get(0).getValue();
            stbuscar += " nombre like" + "'%" + nombreProducto + "%'";
            separado = " and ";
        }
        
        if(!toolsUsado.get(1).isEmplyTool()){
            if(!(toolsUsado.get(1).getValue().toString().equals("Ninguna")))
                categoriaProducto = (String) toolsUsado.get(1).getValue().toString();
            stbuscar += separado + " nombre_c like '%"+categoriaProducto+"%'";
            separado = " and ";
        }
        if(!toolsUsado.get(2).isEmplyTool()){
            descripcionProducto = (String) toolsUsado.get(2).getValue();
            stbuscar += separado + " descripcion like" + "'%" + descripcionProducto + "%'";
        }
        
        stbuscar += ";";
        
        try (Statement st = con.createStatement()) {
            try(ResultSet rs = st.executeQuery(stbuscar)){
                while (rs.next()) {
                    String idProducto = rs.getString("producto_ID");
                    String nombre = rs.getString("nombre");
                    String precio = rs.getString("precio_unitario");
                    String descri = rs.getString("descripcion");
                    String categoria = rs.getString("nombre_c");
                    Producto p = new Producto(idProducto,Double.parseDouble(precio),nombre,descri,new Categoria(categoria,""));
                    lista.add(p);
                }
            } catch (SQLException ex) {
                throw new SQLException("La base de datos se desconectó inesperadamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

    //retorna una lista con solo un cliente que cumpla con la cedula que se esta mandando en caso de no hacer reotrna una lista vacia
    public List<Cliente> selectCliente(String cedula){
        List<Cliente> lista = new ArrayList<>();
        
        String stbuscarCliente = "SELECT * FROM Cliente where cedula = '" + cedula + "';";
        
        String stbuscarPersona = "SELECT * FROM Persona where cedula = '" + cedula + "';";
        
        try (Statement st = con.createStatement()) {
            try(ResultSet rsPersona = st.executeQuery(stbuscarPersona)){
                
                String cedulaPersona = null;
                String nombrePersona = null;
                String apellidoPersona = null;
                
                
                while(rsPersona.next()){
                    cedulaPersona = rsPersona.getString("cedula");
                    nombrePersona = rsPersona.getString("nombre");
                    apellidoPersona = rsPersona.getString("apellido");
                }
                
                try(ResultSet rsCliente = st.executeQuery(stbuscarCliente)){
                    
                    Cliente cliente;
                    
                    while (rsCliente.next()) {
                        String direccionCliente = rsCliente.getString("direccion");
                        String telefonoCliente = rsCliente.getString("telefono");
                        cliente = new Cliente(cedulaPersona, nombrePersona, apellidoPersona, direccionCliente, telefonoCliente);
                        lista.add(cliente);
                    }
                    
                } catch (SQLException ex) {
                    throw new SQLException("La base de datos se desconectó inesperadamente." + ex);
                }
                
            } catch (SQLException ex) {
                throw new SQLException("La base de datos se desconectó inesperadamente." +ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    //devuelve todos los productos que tiene considencia con los parametros enviados
    public List<Servicio> filtarServicio(List<Tool> toolsUsado){
        List<Servicio> lista = new ArrayList<>();
        
        String nombreServicio = "";
        String descripcionServicio = "";
        
        String stbuscar = "select * from V_Servicios where";
        String separado = "";
        
        if(!toolsUsado.get(0).isEmplyTool()){
            nombreServicio = (String) toolsUsado.get(0).getValue();
            stbuscar += " nombre like" + "'%" + nombreServicio + "%'";
            separado = " and ";
        }
        
        if(!toolsUsado.get(2).isEmplyTool()){
            descripcionServicio = (String) toolsUsado.get(2).getValue();
            stbuscar += separado + " descripcion like" + "'%" + descripcionServicio + "%'";
        }
        
        stbuscar += ";";
        
        try (Statement st = con.createStatement()) {
            try(ResultSet rs = st.executeQuery(stbuscar)){
                while (rs.next()) {
                    String idservicio = rs.getString("servicio_ID");
                    String nombre = rs.getString("nombre");
                    String precio = rs.getString("precio_unitario");
                    String descri = rs.getString("descripcion");
                    Servicio s = new Servicio(Integer.parseInt(idservicio),nombre,descri,Double.parseDouble(precio));
                    lista.add(s);
                }
            }
            catch (SQLException ex) {
                throw new SQLException("La base de datos se desconectó inesperadamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
    
    //Este metodo tiene que retornar todas las mascota que tengan como cable foranea al cliente enviado
    public List<Mascota> selectMascotasCliente(Cliente cli){
        List<Mascota> listMascota = new ArrayList<>();
        
        listMascota.add(new Mascota(20, "Coffe", "Mestozo", "Sucursal", cli));
        listMascota.add(new Mascota(21, "Filomeno", "Mestizo", "Domicilio",cli));
        listMascota.add(new Mascota(22, "Cuy", "Mestizo", "Translado a domicilio",cli));
        listMascota.add(new Mascota(22, "Gato", "Mestizo", "Translado a sucursal",cli));
        
        return listMascota;
    }
    
    public List<Categoria> selectAllCategorias(){
        List<Categoria> lista = new ArrayList<>();
        
        String stbuscar = "SELECT * FROM categoria;";
        
        try (Statement st = con.createStatement()) {
            try(ResultSet rs = st.executeQuery(stbuscar)){
                Categoria categoria;
                while (rs.next()) {
                    String nombreCategoria = rs.getString("nombre_c");
                    String descriCategoria = rs.getString("descripcion");
                    categoria = new Categoria(nombreCategoria, descriCategoria);
                    lista.add(categoria);
                }
            } catch (SQLException ex) {
                throw new SQLException("La base de datos se desconectó inesperadamente." + ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }

    //Metodo que me retorna la seccion valida de un empleado de caja retorn su enum correspondiente
    @Override
    public UserType verificarSesion(String user, String pass) {
        return UserType.PERSONAL_CAJA;
    }
}
