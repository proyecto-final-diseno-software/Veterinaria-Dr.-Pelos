/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;
import modelo.Servicio;


/**
 * Clase en construccion
 * @author paula
 */
public class Ctr_Administrador implements Control_Session{
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Ctr_Administrador() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    
    public void crearProducto(Producto p){
    }
    
    public void crearServicio(Servicio s){
    }
        
    public void modificarProducto(Producto p){
    }
    
    public void modificarServicio(Servicio s){
    }
    
    public void descartarProducto(Producto p){
    }
    
    public void descartarServicio(Servicio s){
    }
    
    //Metodo que me retorna la seccion valida de un empleado de caja
    @Override
    public UserType verificarSesion(String cedula) {
        Statement stmt;
        try {
            stmt = con.createStatement();
            String q = "select usuario_ID from Usuario join Administrador on Usuario.usuario_ID = Administrador.cedula where Usuario.usuario_ID =\""+cedula+"\"";
            ResultSet rs = stmt.executeQuery(q);
            if(rs.next()){
                return UserType.ADMINISTRADOR;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}
