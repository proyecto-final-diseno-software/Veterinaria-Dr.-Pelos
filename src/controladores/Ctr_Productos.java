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
import modelo.Producto;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class Ctr_Productos {
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Ctr_Productos() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public List<Producto> filtarProductos(List<Tool> toolsUsado){
        List<Producto> lista = new ArrayList<>();
        
        String nombreProducto = "";
        String categoriaProducto = "";
        String descripcionProducto = "";
        
        String stbuscar = "select * from V_Productos where";
        String separado = "";
        
        if(!toolsUsado.get(0).isEmplyTool()){
            nombreProducto = (String) toolsUsado.get(0).getValue();
            stbuscar += " nombre like" + "'%" + nombreProducto + "%'";
            separado = " and ";
        }
        
        if(!toolsUsado.get(1).isEmplyTool()){
            if(!(toolsUsado.get(1).getValue().toString().equals("Ninguna")))
                categoriaProducto = (String) toolsUsado.get(1).getValue().toString();
            stbuscar += separado + " nombre_c like '%"+categoriaProducto+"%'";
            separado = " and ";
        }
        if(!toolsUsado.get(2).isEmplyTool()){
            descripcionProducto = (String) toolsUsado.get(2).getValue();
            stbuscar += separado + " descripcion like" + "'%" + descripcionProducto + "%'";
        }
        
        stbuscar += ";";
        
        try (Statement st = con.createStatement()) {
            try(ResultSet rs = st.executeQuery(stbuscar)){
                while (rs.next()) {
                    int idProducto = rs.getInt("producto_ID");
                    String nombre = rs.getString("nombre");
                    String precio = rs.getString("precio_unitario");
                    String descri = rs.getString("descripcion");
                    String categoria = rs.getString("nombre_c");
                    Producto p = new Producto(idProducto,nombre,Double.parseDouble(precio),descri,new Categoria(categoria,""));
                    lista.add(p);
                }
            } catch (SQLException ex) {
                throw new SQLException("La base de datos se desconectó inesperadamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ctr_Personal_Caja.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lista;
    }
}
