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

/**
 *
 * @author scarlet Espinoza
 */
public class Ctr_BaseDatosProxy implements BaseDatos{
    private static final Ctr_BaseDatos realBaseDatos = new Ctr_BaseDatos();
    private static final Connection conectionBaseCentral = realBaseDatos.getConnection();

    public Ctr_BaseDatosProxy() {}
    
    @Override
    public Connection getConnection() {
        return conectionBaseCentral;
    }
    
    public Connection obtenerConnection(){
        return conectionBaseCentral;
    }

    @Override
    public void disconnect() {}
    
    //Recibe los datos y retorna la cedula a la que pertene el usuario
    public String isUser(String user, String pass){
        Statement stmt;
        try {
            stmt = conectionBaseCentral.createStatement();
            ResultSet rs = stmt.executeQuery("select usuario_ID from usuario "
                    + "where usuario=\""+user+"\" and passwordUser = \""+pass+"\";");
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
