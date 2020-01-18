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
public class Administrador extends Persona {
    private int id_administrador;

    public Administrador(String cedula, String nombre, String apellido) {
        super(nombre, apellido, cedula);
    }

    public int getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(int id_administrador) {
        this.id_administrador = id_administrador;
    }
    
    public boolean solicitarSuministros(){
        return true;
    }
    
}
