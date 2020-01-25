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
import modelo.Ruta;

/**
 *
 * @author paula
 */
public class Ctr_Jefe_Bodega implements Control_Session{
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;
    
    public Ctr_Jefe_Bodega(){
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
        
    }
    
    public Ruta establecerRuta(){
        return null;
    }
    
    public boolean manejarPrecio(){
        //En construccion
        return true;
    }
    
    public boolean modificarPrecioProducto(Producto p, int precio){
        //En construccion
        return true;
    }
    
    //Metodo que me retorna la seccion valida de un empleado de caja
    @Override
    public UserType verificarSesion(String cedula) {
        Statement stmt;
        try {
            stmt = con.createStatement();
            String q = "select usuario_ID from Usuario join Jefe_Bodega on Usuario.usuario_ID = Jefe_Bodega.cedula where Usuario.usuario_ID =\""+cedula+"\"";
            ResultSet rs = stmt.executeQuery(q);
            if(rs.next()){
                return UserType.JEFE_BODEGA;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
