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
    private boolean permiso_administrador;
    private int id_usuario;

    public Usuario(String cedula, String nombre, String apellido) {
        super(cedula, nombre, apellido);
    }
    
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

    public boolean isPermiso_administrador() {
        return permiso_administrador;
    }

    public void setPermiso_administrador(boolean permiso_administrador) {
        this.permiso_administrador = permiso_administrador;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
}
