/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import modelo.Cliente;
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
}
