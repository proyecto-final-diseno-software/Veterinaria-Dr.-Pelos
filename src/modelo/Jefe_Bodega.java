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
public class Jefe_Bodega extends Persona {

    public Jefe_Bodega(String cedula, String nombre, String apellido) {
        super(nombre, apellido, cedula);
    }

    public boolean imprimirDocEnvio(){
        //Funcionalidad en construccion
        return true;
    }
    
    
    
    
    
    
}
