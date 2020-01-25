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
public class CtrMascota {
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;

    public CtrMascota() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public boolean insertMascota(Mascota m){
        String q = "insert into Mascota(mascota_ID,nombre,"
                    + "raza,estado,dueno_ID) values(?,?,?,?,?);";
        try (PreparedStatement ps = con.prepareStatement(q)) {
            ps.setInt(1, m.getMascotaId());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getRaza());
            ps.setString(4, m.getEstado());
            ps.setString(5, m.getCliente().getCedula());
            
            ps.executeUpdate();
            return true;
            
        } catch (Exception ex) {
            Logger.getLogger(CtrMascota.class.getName()).log(Level.SEVERE, null, ex);
            
        } 
        return false;
    }
    
    public boolean setEstadoMascota(Mascota mas, String nuevoEstado){
        String q = "update mascota set estado = \""+nuevoEstado+"\" where mascota_ID = " +mas.getMascotaId()+";";
        try (PreparedStatement ps = con.prepareStatement(q)) {
            ps.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CtrMascota.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
