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
    private String idUsuario;
    private String usuario;
    private String contrasena;
    private Sucursal sucur;
    private boolean permisoAdministrador;
    private Persona persona;

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

    public boolean isPermisoAdministrador() {
        return permisoAdministrador;
    }

    public void setPermisoAdministrador(boolean permisoAdministrador) {
        this.permisoAdministrador = permisoAdministrador;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
