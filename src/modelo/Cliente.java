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
public class Cliente extends Persona{
    private String direccion;
    private String num_telefonico;

    public Cliente(String cedula, String nombre, String apellido, String direccion, String num_telefonico) {
        super(cedula, nombre, apellido);
        this.direccion = direccion;
        this.num_telefonico = num_telefonico;
    }
    
    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + "Cliente{" + "direccion=" + direccion + ", num_telefonico=" + num_telefonico + '}';
    }
    
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNum_telefonico() {
        return num_telefonico;
    }
}
