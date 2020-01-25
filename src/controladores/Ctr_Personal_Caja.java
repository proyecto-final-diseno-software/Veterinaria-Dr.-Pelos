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
import modelo.Detalle_VentaProducto;
import modelo.Detalle_VentaServicio;
import modelo.Efectivo;
import modelo.Forma_pago;
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
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Ctr_Personal_Caja() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public void realizarCotizacion(){
        
    }
    
    //Devielde el personal de caja con un numero usuario de la tabla usuarios recivido
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
        //return new Personal_Caja("0975368545", "Filomeno", "Gonzalez", new Sucursal(1, "Mi casa", "Cerca de mi casa", true));
    }
    
    public Sucursal selectSucursal(int id){
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sucursal where sucursal_ID ="+id+";");
            if(rs.next()){
                String nombre = rs.getString(2);
                String direccion = rs.getString(3);
                boolean services = rs.getBoolean(4);
                Sucursal s = new Sucursal(id,nombre,direccion,services);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    
    public boolean insertCotizacion(Cotizacion c){
        try {
            c.setId_documento(maxCotizacion()+1);
            
            PreparedStatement ps = con.prepareStatement("insert into Cotizacion(cotizacion_ID,fecha,valor,cliente_ID,personal_cajas_ID) values(?,?,?,?,?);");
            
            ps.setInt(1, c.getId_documento());
            LocalDate fecha = c.getFecha();
            Date fechasql = Date.valueOf(fecha);
            ps.setDate(2, fechasql);
            ps.setFloat(3, (float)c.getValor());
            ps.setString(4, c.getCliente().getCedula());
            ps.setString(5, c.getPersonalCaja().getCedula());
            
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
            v.setId_documento(maxVenta()+1);
            
            PreparedStatement ps = con.prepareStatement("insert into venta(fecha, n_factura, sub_total, total, descuento, personal_cajas_ID, forma_pago_ID, id_cliente) values(?,?,?,?,?,?,?,?);");
            
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
            
            ps.executeUpdate();
            
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    
    //En este metodo guarde los detaller de denta dependiendo si es un detalle de venta de producto o un detalle de venta de servicios para solucionar la relacion de muchos a muchos
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
    
    public Forma_pago retornaMetodoPago(){
        Forma_pago efectivo = new Efectivo();
        return efectivo;
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
                    int idProducto = rs.getInt("producto_ID");
                    String nombre = rs.getString("nombre");
                    String precio = rs.getString("precio_unitario");
                    String descri = rs.getString("descripcion");
                    String categoria = rs.getString("nombre_c");
                    Producto p = new Producto(idProducto,nombre,Double.parseDouble(precio),descri,new Categoria(categoria,""));
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
        Statement stmt;
        try {
            stmt = con.createStatement();
            String q = "select * from Mascota where dueno_id = \""+cli.getCedula()+"\"";
            ResultSet rs = stmt.executeQuery(q);
            while(rs.next()){
                int mascota_id = rs.getInt(1);
                String nombre = rs.getString(2);
                String raza = rs.getString(3);
                String estado = rs.getString(4);
                
                Mascota m = new Mascota(mascota_id,nombre,raza,estado,cli);
                listMascota.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
