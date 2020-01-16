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
public class Personal_Caja extends Persona {
    private int id_persona_caja;
    private String area;

    public Personal_Caja(String nombre, String apellido) {
        super(nombre, apellido);
    }


    public int getId_persona_caja() {
        return id_persona_caja;
    }

    public void setId_persona_caja(int id_persona_caja) {
        this.id_persona_caja = id_persona_caja;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    
    
    
    
}
