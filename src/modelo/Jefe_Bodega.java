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
public class Jefe_Bodega extends Persona {
    private int id_jefeBodega;

    public boolean imprimirDocEnvio(){
        return true;
    }
    
    
    public int getId_jefeBodega() {
        return id_jefeBodega;
    }

    public void setId_jefeBodega(int id_jefeBodega) {
        this.id_jefeBodega = id_jefeBodega;
    }
    
    
    
    
}
