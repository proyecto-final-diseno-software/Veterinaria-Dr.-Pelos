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

    public Administrador(String cedula, String nombre, String apellido) {
        super(nombre, apellido, cedula);
    }

    
    public boolean solicitarSuministros(){
        return true;
    }
    
}
