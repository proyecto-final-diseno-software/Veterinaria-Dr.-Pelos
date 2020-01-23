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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private Connection con;
    private Ctr_BaseDatos ctr_BaseDatos;

    public Ctr_Personal_Caja() {
        ctr_BaseDatos= new Ctr_BaseDatos();
        con = ctr_BaseDatos.getConnection();
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
    
    //Devuelve una lista con todos los productos que cumplan con las los uno o los campos que esten si es null ignora ese campo
    public List<Producto> filtarProductos(List<Tool> toolsUsado){
        //Estos ya son los campos a filtara ya estan extraidos de la interfaz
        String nombreProducto = (String) toolsUsado.get(0).getValue();
        String categoriaProducto = (String) toolsUsado.get(1).getValue().toString();
        String descripcionProducto = (String) toolsUsado.get(2).getValue();
        
        List<Producto> lista = new ArrayList<>();
        
        //lista.add(new Producto("1", 12, "Papas", "Papas Ricas", new Categoria("Alimento", "Aliemntos")));
        //lista.add(new Producto("2", 30, "Tortolines", "cHIFLES", new Categoria("Alimento", "Aliemntos")));
        
        /*
        
        
        ResultSet rs = null;                       
        PreparedStatement st = null;
        try {
            st = con.prepareStatement("select * from V_Productos where nombre like\" + \"'\" + nombreProducto");
            //st = con.prepareStatement("Select * from Producto where nombre = ?");
            st.setString(1, nombreProducto);
            rs = st.executeQuery();
            while(rs.next()){
                Producto registro = new Producto(Integer.toString(rs.getInt("producto_ID")),
                        rs.getDouble("precio_unitario"), rs.getString("nombre"),
                        rs.getString("descripcion"),
                        retornarCategoria(rs.getInt("categoria_ID")));
                lista.add(registro);
            }
            rs.close();
            st.close();
        } catch(Exception e){
            System.out.println("Algo esta mal"+e);
        } 
        */
        
        return lista;
    }
    
    /*
    
    
    private Categoria retornarCategoria(int categoria_ID){
        ResultSet rs = null;                       
        PreparedStatement st = null;
        Categoria registro = null;
        try {            
            st = con.prepareStatement("Select * from Categoria where categoria_ID=?");
            st.setInt(1, categoria_ID);
            rs = st.executeQuery();
            while(rs.next()){
                registro = new Categoria(rs.getString("nombre_c"), rs.getString("descripcion"));
                
            }
            rs.close();
            st.close();
        } catch(Exception e){
            System.out.println("Algo esta mal"+e);
        }   
        
        return registro;
    }
    */

    //retorna una lista con solo un cliente que cumpla con la cedula que se esta mandando en caso de no hacer reotrna una lista vacia
    public List<Cliente> selectCliente(String cedula){
        List<Cliente> lista = new ArrayList<>();
        lista.add(new Cliente("0928072487", "Eduardo", "Gonzalez", "Mi casa", "090999841"));
        return lista;
    }
    
    //devuelve todos los productos que tiene considencia con los parametros enviados
    public List<Servicio> filtarServicio(List<Tool> toolsUsado){
        //Estos ya son los campos a filtara ya estan extraidos de la interfaz
        String nombreServicio = (String) toolsUsado.get(0).getValue();
        String descripcionServicio = (String) toolsUsado.get(2).getValue();
        
        List<Servicio> lista = new ArrayList<>();
        
        lista.add(new Servicio(1, "Corte de pelo", "Cortamos pelo", 20));
        lista.add(new Servicio(1, "Baño", "Lo bañamos", 40));
        
        return lista;
    }
    
    //retorna todas las mascota a las cuales pertenece el cleinte
    public List<Mascota> selectMascotasCliente(Cliente cli){
        List<Mascota> listMascota = new ArrayList<>();
        
        listMascota.add(new Mascota(20, "Coffe", "Mestozo", "Sucursal", cli));
        listMascota.add(new Mascota(21, "Filomeno", "Mestizo", "Domicilio",cli));
        listMascota.add(new Mascota(22, "Cuy", "Mestizo", "Translado a domicilio",cli));
        listMascota.add(new Mascota(22, "Gato", "Mestizo", "Translado a sucursal",cli));
        
        return listMascota;
    }
    
    public List<Categoria> selectAllCategorias(){
        List<Categoria> list = new ArrayList<>();
        
       list.add(new Categoria("Aliemtnos", "Cosas ricas"));
       list.add(new Categoria("Medicamentos", "Cosas del hpspital"));
       
       return list;
    }

    //Metodo que me retorna la seccion valida de un empleado de caja retorn su enum correspondiente
    @Override
    public UserType verificarSesion(String user, String pass) {
        return UserType.PERSONAL_CAJA;
    }
}
