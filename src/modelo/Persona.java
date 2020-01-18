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
public class Persona {
    protected String cedula;
    protected String nombre;
    protected String apellido;

    public Persona(String cedula, String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }
}
