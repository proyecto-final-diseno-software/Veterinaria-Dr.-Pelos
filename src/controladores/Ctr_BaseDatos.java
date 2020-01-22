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
 * @author paula
 */
public class Ctr_BaseDatos implements BaseDatos {
    private static Connection con;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String pass = "saem2003";        
    private static final String url = "jdbc:mysql://localhost:3306/DrPelos";

    public Ctr_BaseDatos() {
        con = null;
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            if(con!=null) System.out.println("READY!");
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
