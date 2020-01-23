/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author scarlet Espinoza
 */
public class Ctr_BaseDatosProxy implements BaseDatos{
    Ctr_BaseDatos realBaseDatos;
     private static Connection con;
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBMS     = "mysql";
    private static final String HOST     = "127.0.0.1";
    private static final String PORT     = "3306";
    private static final String DATABASE = "DrPelosCentral";
    private static final String USER     = "adminDrPelosLocal";
    private static final String PASSWORD = "adminLocal";

    public Ctr_BaseDatosProxy() {
        con = null;
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection("jdbc:" + DBMS + "://" + HOST + ":" + PORT + "/" + DATABASE + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Eastern&useSSL=false&allowPublicKeyRetrieval=true&useSSL=false", USER, PASSWORD);
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("ERROR"+ e);
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
