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
    private String nombre;
    private String descripcion;
    private ArrayList<String> colaboraciones;
    private long precio_unitario;

    public Servicio(int id_servicio, String nombre, String descripcion, long precio_unitario) {
        this.id_servicio = id_servicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.colaboraciones = new ArrayList<>();
        this.precio_unitario = precio_unitario;
    }
    
    public boolean cambiarPrecio(long precio){
        if(precio>0){
            this.precio_unitario = precio;
            return true;
        }
        return false;
    }
    
    public boolean agregarColaboraciones(String colaboracion){
        if(colaboracion == null || colaboraciones.contains(colaboracion)) return false;
        return colaboraciones.add(colaboracion);
    }
    
    public boolean eliminarColaboraciones(String colaboracion){
        if(colaboracion == null || !colaboraciones.contains(colaboracion)) return false;
        return colaboraciones.remove(colaboracion);
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

    public ArrayList<String> getColaboraciones() {
        return colaboraciones;
    }

    public void setColaboraciones(ArrayList<String> colaboraciones) {
        this.colaboraciones = colaboraciones;
    }

    public long getPrecio() {
        return precio_unitario;
    }

    public void setPrecio(long precio) {
        this.precio_unitario = precio;
    }

    public List<String> retornarAllData(){
        List<String> lista = new ArrayList<>();
        
        lista.add(Integer.toString(id_servicio));
        lista.add(nombre);
        lista.add(Long.toString(precio_unitario));
        lista.add(descripcion);
        
        return lista;
    }
}
