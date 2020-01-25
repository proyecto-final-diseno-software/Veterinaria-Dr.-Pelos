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
 * Clase en construccion
 * @author paula
 */
public class CtrAdministrador implements ControlSession{
    private Connection con;
    private CtrBaseDatosProxy controlDataBase;

    public CtrAdministrador() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    /*
    En construccion
    */
    public void editarBaseDatos(){
        throw new UnsupportedOperationException();
    }
    
    //Metodo que me retorna la seccion valida de un empleado de caja
    @Override
    public UserType verificarSesion(String cedula) {
        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
            
            String q = "select usuario_ID from Usuario join Administrador on Usuario.usuario_ID = Administrador.cedula where Usuario.usuario_ID =\""+cedula+"\"";
            rs = stmt.executeQuery(q);
            if(rs.next()){
                return UserType.ADMINISTRADOR;
            }
        } catch (SQLException ex) {
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
