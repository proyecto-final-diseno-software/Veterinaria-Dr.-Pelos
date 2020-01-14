/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author paula
 */
public class Usuario extends Persona{
    private String usuario;
    private String contrasena;
    private boolean permiso_administrados;
    private int id_usuario;
    
    public boolean acceder(){
        return true;
    }
    
    public boolean restrablecerConexion(){
        return true;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isPermiso_administrados() {
        return permiso_administrados;
    }

    public void setPermiso_administrados(boolean permiso_administrados) {
        this.permiso_administrados = permiso_administrados;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
}
