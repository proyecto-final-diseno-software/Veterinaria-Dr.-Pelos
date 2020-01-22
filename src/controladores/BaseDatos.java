/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;

/**
 *
 * @author paula
 */
public interface BaseDatos {
    public abstract Connection getConnection();
    public abstract void disconnect();
}
