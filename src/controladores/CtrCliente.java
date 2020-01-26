/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.Mascota;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class CtrCliente {
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;

    public CtrCliente() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public Cliente createCliente(List<Tool> list){
        String cedula = (String) list.get(0).getValue();
        String nombre = (String) list.get(1).getValue();
        String apellido = (String) list.get(2).getValue();
        String direccion = (String) list.get(3).getValue();
        String numTelefonico = (String) list.get(4).getValue();
        
        return new Cliente(cedula, nombre, apellido, direccion, numTelefonico);
    }
    
    public boolean insertPersona(Cliente c){
        String q = "insert into persona(cedula,nombre,apellido) values(?,?,?);";
        try (PreparedStatement ps = con.prepareStatement(q)){
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApellido());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CtrCliente.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }
    
         
    public boolean insertCliente(Cliente c){
        try (PreparedStatement ps = con.prepareStatement("insert into cliente(cedula,direccion,telefono) values(?,?,?);");
){
            ps.setString(1, c.getCedula());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getNumTelefonico());
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CtrCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Cliente> selectCliente(String cedula){
        List<Cliente> lista = new ArrayList<>();
        
        String stbuscarCliente = "SELECT * FROM Cliente where cedula = '" + cedula + "';";
        
        try (Statement st = con.createStatement()) {
            tryCliente(st,cedula,stbuscarCliente,lista);
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    private void tryCliente(Statement st,String cedula,String stbuscarCliente, List<Cliente> lista){
        String stbuscarPersona = "SELECT * FROM Persona where cedula = '" + cedula + "';";
        
        try(ResultSet rsPersona = st.executeQuery(stbuscarPersona)){
                
                String cedulaPersona = null;
                String nombrePersona = null;
                String apellidoPersona = null;
                
                
                while(rsPersona.next()){
                    cedulaPersona = rsPersona.getString("cedula");
                    nombrePersona = rsPersona.getString("nombre");
                    apellidoPersona = rsPersona.getString("apellido");
                }
                
                encontrarPersona(st, cedulaPersona, nombrePersona, apellidoPersona, lista, stbuscarCliente);
                
            } catch (SQLException ex) {
            Logger.getLogger(CtrCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void encontrarPersona(Statement st, String cedulaPersona, String nombrePersona, String apellidoPersona, List<Cliente> lista,String stbuscarCliente){
        ResultSet rsCliente = null;
        
        try{
            rsCliente = st.executeQuery(stbuscarCliente);
            Cliente cliente;

            while (rsCliente.next()) {
                String direccionCliente = rsCliente.getString("direccion");
                String telefonoCliente = rsCliente.getString("telefono");
                cliente = new Cliente(cedulaPersona, nombrePersona, apellidoPersona, direccionCliente, telefonoCliente);
                lista.add(cliente);
            }
            rsCliente.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Mascota> selectMascotasCliente(Cliente cli){
        List<Mascota> listMascota = new ArrayList<>();
        String q = "select * from Mascota where dueno_id = \""+cli.getCedula()+"\"";
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()){
            rs = stmt.executeQuery(q);
            while(rs.next()){
                int mascotaId = rs.getInt(1);
                String nombre = rs.getString(2);
                String raza = rs.getString(3);
                String estado = rs.getString(4);
                
                Mascota m = new Mascota(mascotaId,nombre,raza,estado,cli);
                listMascota.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null) rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listMascota;
    }
}
