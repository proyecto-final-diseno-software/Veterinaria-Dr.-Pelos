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
import modelo.PayPal;
import modelo.Personal_Caja;
import modelo.Producto;
import modelo.Servicio;
import modelo.Sucursal;
import modelo.Tarjeta;
import modelo.Venta;
import view.tool.Tool;

/**
 *
 * @author paula
 */
public class Ctr_Personal_Caja implements Control_Session{
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;
    private Ctr_Cliente controlCliente;

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
            
            v.setId_documento(maxVenta() + 1);
            
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
    
    public boolean insertTarjeta(Forma_pago formpago){
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
    
    public boolean insertPaypal(Forma_pago formpago){
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
