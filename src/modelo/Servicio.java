/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paula
 */
public class Servicio {
    private int id_servicio;
    private Double precio_unitario;
    private String nombre;
    private String descripcion;

    public Servicio(int id_servicio, String nombre, String descripcion, Double precio_unitario) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
    }
    
    public boolean cambiarPrecio(Double precio){
        if(precio>0){
            this.precio_unitario = precio;
            return true;
        }
        return false;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio_unitario;
    }

    public void setPrecio(Double precio) {
        this.precio_unitario = precio;
    }

    public List<String> retornarAllData(){
        List<String> lista = new ArrayList<>();
        
        lista.add(Integer.toString(id_servicio));
        lista.add(nombre);
        lista.add(Double.toString(precio_unitario));
        
        return lista;
    }
}
