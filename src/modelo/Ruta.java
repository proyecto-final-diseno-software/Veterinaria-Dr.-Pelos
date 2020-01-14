/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author paula
 */
public class Ruta {
    private static ArrayList<Ruta> rutas = new ArrayList<>();
    private int id_ruta;
    private ArrayList<String> direcciones = new ArrayList<>();

    public Ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }
    
    public boolean agregarDireccion(String direccion){
        if(direccion == null || direcciones.contains(direccion)) return false;
        else{
            return direcciones.add(direccion);
        }
    }
    
    public boolean eliminarDireccion(String direccion){
        if(direccion == null || !direcciones.contains(direccion))return false;
        else{
            return direcciones.remove(direccion);
        }
    }
    
    public static boolean agregarRuta(Ruta r){
        return rutas.add(r);
    }
    
    public static boolean eliminarRuta(Ruta r){
        return rutas.remove(r);
    }

    public static ArrayList<Ruta> getRutas() {
        return rutas;
    }

    public static void setRutas(ArrayList<Ruta> rutas) {
        Ruta.rutas = rutas;
    }

    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public ArrayList<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<String> direcciones) {
        this.direcciones = direcciones;
    }
    
}
