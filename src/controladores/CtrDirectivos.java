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
import modelo.Usuario;

/**
 *
 * @author paula
 */
public class CtrDirectivos implements ControlSession{
    private Connection con;
    private CtrBaseDatosProxy controlDataBase;
    
    public CtrDirectivos(){
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    /*
    Metodo en construccion
    */
    public void asignarAdministrador(Usuario u){
        throw new UnsupportedOperationException();
    }
    
    //Metodo que me retorna la seccion valida de un empleado de caja
    @Override
    public UserType verificarSesion(String cedula) {
        ResultSet rs = null;
        String q = "select usuario_ID from Usuario join Directivos on Usuario.usuario_ID = Directivos.cedula where Usuario.usuario_ID =\""+cedula+"\"";

        try (Statement stmt = con.createStatement()) {
                        
            rs = stmt.executeQuery(q);
            if(rs.next()){
                return UserType.DIRECTIVO;
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null){
                    rs.close();
                }
            } catch (SQLException|NullPointerException ex) {
                Logger.getLogger(CtrAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
