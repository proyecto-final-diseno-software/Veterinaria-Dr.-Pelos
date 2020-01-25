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
public class PersonalCaja extends Persona {
    private String area;
    private Sucursal sucursal;

    public PersonalCaja(String cedula, String nombre, String apellido, Sucursal sucursal) {
        super(cedula, nombre, apellido);
        this.sucursal = sucursal;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public String getCedula() {
        return cedula;
    }
    
    
}
