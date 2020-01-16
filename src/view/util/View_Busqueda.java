/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modelo.Producto;
import view.tool.BotonTool;
import view.tool.ComBoxTool;
import view.tool.TableTool;
import view.tool.TextFieldTool;

/**
 *
 * @author ADMIN
 */
public class View_Busqueda extends Parent{
    private VBox filas;
    private Pane paneTabla;
    
    private BotonTool cerrar;
    
    private TableTool tablaProductos;
    
    public View_Busqueda(int ancho, int alto, int titulo2, BotonTool cerrar){
        filas = new VBox(5);
        filas.setAlignment(Pos.TOP_LEFT);
        filas.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-15))));
        filas.setPrefHeight(alto);
        filas.setPrefWidth(ancho);
        
        paneTabla = new Pane();
        
        int anchoField = ancho / 5 - 5;
        
        HBox cabecera = new HBox(5);
        
        TextFieldTool busquedaNombre = new TextFieldTool("Buscar por nombre", "Nombre:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        
        List<String> listaCategorias = new ArrayList<>();
        listaCategorias.add("Opcion 1");
        ComBoxTool<String> comboCategorias = new ComBoxTool(anchoField, "Categoria:" , listaCategorias, titulo2);
        
        this.cerrar = cerrar;
        cerrar.setTranslateX(anchoField - 130);
        cerrar.setTranslateY(-35);
        
        TextFieldTool busquedaDescripcion = new TextFieldTool("Buscar por descripcion", "Descripcion:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        
        List<String> listaTipo = new ArrayList<>();
        listaTipo.add("Servicio");
        listaTipo.add("Producto");
        ComBoxTool<String> comboTipo = new ComBoxTool(anchoField, "Tipo:" , listaTipo, titulo2);
        
        BotonTool BotonBuscarProducto = new BotonTool("Buscar", titulo2, 100, titulo2 * 2 + 3, Color.GAINSBORO);
        BotonBuscarProducto.setTranslateY(16);
        
        cabecera.getChildren().addAll(busquedaNombre, comboCategorias, busquedaDescripcion, comboTipo, BotonBuscarProducto,cerrar);
        
        List<String> camposProductos = new ArrayList<>();
        camposProductos.add("Codigo");
        camposProductos.add("Nombre");
        camposProductos.add("Descripcion");
        camposProductos.add("Precio Unitario");
        camposProductos.add("");
        
        List<String> camposServicios = new ArrayList<>();
        camposServicios.add("Codigo");
        camposServicios.add("Nombre");
        camposServicios.add("Descripcion");
        camposServicios.add("Colaborador");
        camposServicios.add("Costo");
        camposServicios.add("");
        
        BotonBuscarProducto.setOnMousePressed(buscarProducto -> {
            if(comboTipo.getValue() != null){
                if(((String) comboTipo.getValue()).equals("Producto"))
                    filtarProducto(ancho, camposProductos);
                else
                    filtrarServicio(ancho, camposServicios);
            }
        });
        
        tablaProductos = new TableTool(ancho, camposProductos, "No hay articulos con esta descripcion");
        paneTabla.getChildren().add(tablaProductos);
        
        filas.getChildren().addAll(cabecera, paneTabla);
        
        getChildren().add(filas);
    }
    
    private void filtarProducto(int ancho, List<String> camposProductos){
        paneTabla.getChildren().clear();
                
        tablaProductos = new TableTool(ancho, camposProductos, "No hay articulos en el carrito");
        
        paneTabla.getChildren().add(tablaProductos);
    }
    
    private void filtrarServicio(int ancho, List<String> camposServicios){
        paneTabla.getChildren().clear();
        
        tablaProductos = new TableTool(ancho, camposServicios, "No hay servicios con esta descripcion");
        
        paneTabla.getChildren().add(tablaProductos);
    }
}
