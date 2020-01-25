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
public class Ruta {
    private int id_ruta;
    private String direccion;
    
    public Ruta(String direccion) {
        this.direccion = direccion;
    }

    public Ruta(){
    }
    
    public Ruta(int id_ruta, String direccion) {
        this.id_ruta = id_ruta;
        this.direccion = direccion;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }
    
}
