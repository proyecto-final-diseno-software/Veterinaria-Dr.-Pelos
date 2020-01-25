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
import modelo.Categoria;

/**
 *
 * @author ADMIN
 */
public class CtrCategorias {
    private final Connection con;
    private final CtrBaseDatosProxy controlDataBase;

    public CtrCategorias() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public List<Categoria> selectAllCategorias(){
        List<Categoria> lista = new ArrayList<>();
        String stbuscar = "SELECT * FROM categoria;";
        try (Statement st = con.createStatement()) {
            tryCategoria(st,lista,stbuscar);
        } catch (SQLException ex) {
            Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    private void tryCategoria(Statement st,List<Categoria> lista, String stbuscar){
        try(ResultSet rs = st.executeQuery(stbuscar)){
                Categoria categoria;
                while (rs.next()) {
                    String nombreCategoria = rs.getString("nombre_c");
                    String descriCategoria = rs.getString("descripcion");
                    categoria = new Categoria(nombreCategoria, descriCategoria);
                    lista.add(categoria);
                }
            } catch (SQLException ex) {

                Logger.getLogger(CtrPersonalCaja.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}
