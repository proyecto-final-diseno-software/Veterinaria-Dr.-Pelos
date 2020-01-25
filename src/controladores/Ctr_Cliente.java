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
public class Ctr_Cliente {
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Ctr_Cliente() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
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
    
     
    public boolean insertCliente(Cliente c){
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
}
