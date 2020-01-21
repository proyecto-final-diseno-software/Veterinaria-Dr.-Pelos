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
public class Repartidor extends Persona{
    private String id_repetidor;
    private String matricula_vehiculo;
    private String telefono;
    private Sucursal sucur;

    public Repartidor(String cedula, String nombre, String apellido) {
        super(cedula, nombre, apellido);
    }
    
    public void asignarRuta(){
        
    }
}
