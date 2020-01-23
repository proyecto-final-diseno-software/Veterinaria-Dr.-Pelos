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
public class Producto {
    private String id_producto;
    private String nombre;
    private double precioUnitario;
    private String descripcion;
    private Categoria categoria;

    public Producto(String id_producto, double precioUnitario, String nombre, String descripcion, Categoria categoria) {
        this.id_producto = id_producto;
        this.precioUnitario = precioUnitario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }
    
    public boolean cambiarPrecio(){
        return true;
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }
    
    public List<String> retornarAllData(){
        List<String> lista = new ArrayList<>();
        
        lista.add(id_producto);
        lista.add(nombre);
        lista.add(Double.toString(precioUnitario));
        lista.add(categoria.getNombre());
        
        return lista;
    } 
    
}
