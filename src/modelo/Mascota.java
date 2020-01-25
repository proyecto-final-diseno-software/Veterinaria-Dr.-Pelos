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
public class Mascota{
    private int mascotaId;
    private String nombre;
    private String raza;
    private String estado;
    private Cliente cliente;

    public Mascota(int mascota_id, String nombre, String raza, String estado, Cliente cliente) {
        this.mascotaId = mascota_id;
        this.nombre = nombre;
        this.raza = raza;
        this.estado = estado;
        this.cliente = cliente;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public List<String> retornarAllData(){
        List<String> lista = new ArrayList<>();
        
        lista.add(Integer.toString(mascotaId));
        lista.add(nombre);
        lista.add(raza);
        lista.add(estado);
        
        return lista;
    }
}
