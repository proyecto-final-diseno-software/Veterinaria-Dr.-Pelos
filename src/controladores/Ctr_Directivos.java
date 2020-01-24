/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import modelo.Usuario;

/**
 *
 * @author paula
 */
public class Ctr_Directivos implements Control_Session{
    
    public void asignarAdministrador(Usuario u){
        //En constuccion
    }
    
    //Metodo que me retorna la seccion valida de un empleado de caja
    @Override
    public UserType verificarSesion(String cedula) {
        return null;
    }
}
