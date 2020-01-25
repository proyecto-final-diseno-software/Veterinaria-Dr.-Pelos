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
    private int idServicio;
    private Double precioUnitario;
    private String nombre;
    private String descripcion;

    public Servicio(int id_servicio, String nombre, String descripcion, Double precio_unitario) {
        this.idServicio = id_servicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precio_unitario;
    }
    
    public boolean cambiarPrecio(Double precio){
        if(precio>0){
            this.precioUnitario = precio;
            return true;
        }
        return false;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
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
        return precioUnitario;
    }

    public void setPrecio(Double precio) {
        this.precioUnitario = precio;
    }

    public List<String> retornarAllData(){
        List<String> lista = new ArrayList<>();
        
        lista.add(Integer.toString(idServicio));
        lista.add(nombre);
        lista.add(Double.toString(precioUnitario));
        
        return lista;
    }
}
