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
    private int id_cliente;
    private String direccion;
    private String num_telefonico;

    public Cliente(String nombre, String apellido, String direccion, String num_telefonico) {
        super(nombre, apellido);
        this.direccion = direccion;
        this.num_telefonico = num_telefonico;
        
    }
    
    
    
    
}
