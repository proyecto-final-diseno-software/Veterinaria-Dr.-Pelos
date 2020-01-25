/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paula
 */
public class CtrBaseDatos implements BaseDatos {
    private static Connection con;
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBMS     = "mysql";
    private static final String HOST     = "127.0.0.1";
    private static final String PORT     = "3306";
    private static final String DATABASE = "DrPelosCentral";
    private static final String USER     = "adminDrPelos";
    private static final String PASS = "admin";

    public CtrBaseDatos() {
        try{
            Class.forName(DRIVER);
            con = DriverManager.getConnection("jdbc:" + DBMS + "://" + HOST + ":" + PORT + "/" + DATABASE + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Eastern&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false", USER, PASS);
            
        }catch(ClassNotFoundException | SQLException e){
            Logger.getLogger(CtrBaseDatos.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public Connection getConnection() {
        return con;
    }

    @Override
    public void disconnect() {
        con = null;
    }
    
}
