/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Producto;
import modelo.Servicio;
import view.tool.Tool;

/**
 *
 * @author paula
 */
public class Ctr_Personal_Caja {
    
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
    
    
    public List<Producto> filtarProductos(List<Tool> toolsUsado){
        //Estos ya son los campos a filtara ya estan extraidos de la interfaz
        String nombreProducto = (String) toolsUsado.get(0).getValue();
        String categoriaProducto = (String) toolsUsado.get(0).getValue();
        String descripcionProducto = (String) toolsUsado.get(2).getValue();
        
        List<Producto> lista = new ArrayList<>();
        
        lista.add(new Producto("1", 12, "Papas", "Papas Ricas", new Categoria("Alimento", "Aliemntos")));
        lista.add(new Producto("2", 30, "Tortolines", "cHIFLES", new Categoria("Alimento", "Aliemntos")));
        
        return lista;
    }
    
    public List<Servicio> filtarServicio(List<Tool> toolsUsado){
        //Estos ya son los campos a filtara ya estan extraidos de la interfaz
        String nombreServicio = (String) toolsUsado.get(0).getValue();
        String descripcionServicio = (String) toolsUsado.get(2).getValue();
        
        List<Servicio> lista = new ArrayList<>();
        
        lista.add(new Servicio(1, "Corte de pelo", "Cortamos pelo", 20));
        lista.add(new Servicio(1, "Baño", "Lo bañamos", 40));
        
        return lista;
    }
    
}
