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
public class CtrBaseDatosProxy implements BaseDatos{
    private static final CtrBaseDatos realBaseDatos = new CtrBaseDatos();
    private static final Connection conectionBaseCentral = realBaseDatos.getConnection();

    public CtrBaseDatosProxy(){
        //Constructor
    }
    
    @Override
    public Connection getConnection() {
        return conectionBaseCentral;
    }
    
    public Connection obtenerConnection(){
        return conectionBaseCentral;
    }

    @Override
    public void disconnect() {
        //Desconecta
    }
    
    //Recibe los datos y retorna la cedula a la que pertene el usuario
    public String isUser(String user, String pass){
        ResultSet rs = null;
        try (Statement stmt = conectionBaseCentral.createStatement()) {
            rs = stmt.executeQuery("select usuario_ID from usuario "
                    + "where usuario=\""+user+"\" and passwordUser = \""+pass+"\";");
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CtrBaseDatosProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
