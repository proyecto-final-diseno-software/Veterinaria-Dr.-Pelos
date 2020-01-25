/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Servicio;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class CtrServicios {
    private Connection con;
    private CtrBaseDatosProxy controlDataBase;

    public CtrServicios() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public List<Servicio> filtarServicio(List<Tool> toolsUsado){
        List<Servicio> lista = new ArrayList<>();
        
        String nombreServicio = "";
        String descripcionServicio = "";
        
        String stbuscar = "select * from V_Servicios where";
        String separado = "";
        
        if(!toolsUsado.get(0).isEmplyTool()){
            nombreServicio = (String) toolsUsado.get(0).getValue();
            stbuscar += " nombre like" + "'%" + nombreServicio + "%'";
            separado = " and ";
        }
        
        if(!toolsUsado.get(2).isEmplyTool()){
            descripcionServicio = (String) toolsUsado.get(2).getValue();
            stbuscar += separado + " descripcion like" + "'%" + descripcionServicio + "%'";
        }
        
        stbuscar += ";";
        Statement st = null;
        try {
             st = con.createStatement();
            try(ResultSet rs = st.executeQuery(stbuscar)){
                while (rs.next()) {
                    String idservicio = rs.getString("servicio_ID");
                    String nombre = rs.getString("nombre");
                    String precio = rs.getString("precio_unitario");
                    String descri = rs.getString("descripcion");
                    Servicio s = new Servicio(Integer.parseInt(idservicio),nombre,descri,Double.parseDouble(precio));
                    lista.add(s);
                }
            }
            catch (SQLException ex) {
                throw new SQLException("La base de datos se desconectó inesperadamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(st!= null)st.close();
            } catch (SQLException ex) {
                Logger.getLogger(CtrServicios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return lista;
    }
}
