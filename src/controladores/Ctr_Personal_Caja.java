/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.List;
import modelo.Cliente;
import view.tool.Tool;

/**
 *
 * @author paula
 */
public class Ctr_Personal_Caja {
    
    
    public void agregarCliente(Cliente c){
        //Agregar a base de datos
    }

    public void realizarCotizacion(){
        
    }
    
    public Cliente createCliente(List<Tool> list){
        String cedula = (String) list.get(0).getValue();
        String nombre = (String) list.get(1).getValue();
        String apellido = (String) list.get(2).getValue();
        String direccion = (String) list.get(3).getValue();
        String numTelefonico = (String) list.get(4).getValue();
        
        return new Cliente(cedula, nombre, apellido, direccion, numTelefonico);
    }
    
    public boolean addClienteDataBase(Cliente cliente){
        return true;
    }
    
}
