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
public class Sucursal {
    private int idSucursal;
    private String nombre;
    private String direccion;
    private boolean ofreceServicios;

    public Sucursal(int id_sucursal, String nombre, String direccion, boolean ofreceServicios) {
        this.idSucursal = id_sucursal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ofreceServicios = ofreceServicios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public boolean isOfreceServicios() {
        return ofreceServicios;
    }
    
    
}
