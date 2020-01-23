/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;

/**
 *
 * @author scarlet Espinoza
 */
public class Ctr_BaseDatosProxy implements BaseDatos{
    private static final Ctr_BaseDatos realBaseDatos = new Ctr_BaseDatos();
    private static final Connection conectionBaseCentral = realBaseDatos.getConnection();

    private Ctr_BaseDatosProxy() {}
    
    @Override
    public Connection getConnection() {
        return conectionBaseCentral;
    }
    
    public static Connection obtenerConnection(){
        return conectionBaseCentral;
    }

    @Override
    public void disconnect() {}
    
}
