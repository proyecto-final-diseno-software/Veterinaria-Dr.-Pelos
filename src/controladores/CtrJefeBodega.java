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
public class CtrJefeBodega implements ControlSession{
    private Connection con;
    private CtrBaseDatosProxy controlDataBase;
    
    public CtrJefeBodega(){
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
        
    }
    
    public Ruta establecerRuta(){
        return null;
    }
    
    /*
    Metodo en construccion
    */
    public boolean manejarPrecio(){
        Producto p = new Producto();
        int precio = 0;
        return modificarPrecioProducto(p,precio);
    }
    
    /*
    Metodo en construccion
    */
    public boolean modificarPrecioProducto(Producto p, int precio){
        throw new UnsupportedOperationException();
    }
    
    //Metodo que me retorna la seccion valida de un empleado de caja
    @Override
    public UserType verificarSesion(String cedula) {
        String q = "select usuario_ID from Usuario join Jefe_Bodega on Usuario.usuario_ID = Jefe_Bodega.cedula where Usuario.usuario_ID =\""+cedula+"\"";

        ResultSet rs = null;
        try (Statement stmt = con.createStatement()) {
            rs = stmt.executeQuery(q);
            if(rs.next()){
                return UserType.JEFE_BODEGA;
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
