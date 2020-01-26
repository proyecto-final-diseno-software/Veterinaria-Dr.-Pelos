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
    private int idRuta;
    private String direccion;
    
    public Ruta(String direccion) {
        this.direccion = direccion;
    }

    public Ruta(){
    }
    
    public Ruta(int idRuta, String direccion) {
        this.idRuta = idRuta;
        this.direccion = direccion;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }
    
}
