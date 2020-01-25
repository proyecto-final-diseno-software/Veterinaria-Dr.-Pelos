/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Mascota;

/**
 *
 * @author ADMIN
 */
public class Ctr_Mascota {
    private final Connection con;
    private final Ctr_BaseDatosProxy controlDataBase;

    public Ctr_Mascota() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public boolean insertMascota(Mascota m){
        try {
            PreparedStatement ps = con.prepareStatement("insert into Mascota(mascota_ID,nombre,"
                    + "raza,estado,dueno_ID) values(?,?,?,?,?);");
            ps.setInt(1, m.getMascota_id());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getEstado());
            ps.setString(5, m.getCliente().getCedula());
            
            ps.executeUpdate();
            //Connection con1 = Ctr_BaseDatos.getConnection();
            ps.close();
            return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } 
    }
    
    public boolean setEstadoMascota(Mascota mas, String nuevoEstado){
        String q = "update mascota set estado = \""+nuevoEstado+"\" where mascota_ID = " +mas.getMascota_id()+";";
        try (PreparedStatement ps = con.prepareStatement(q)) {
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Ctr_Mascota.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
