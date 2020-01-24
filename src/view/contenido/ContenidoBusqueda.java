/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import controladores.Ctr_Personal_Caja;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
import modelo.Categoria;
import modelo.Detalle_Venta;
import modelo.Detalle_VentaProducto;
import modelo.Detalle_VentaServicio;
import modelo.Producto;
import modelo.Servicio;
import view.tool.BotonTool;
import view.tool.ComBoxTool;
import view.tool.TableTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoBusqueda extends Parent{
    private VBox filas;
    private Pane paneTabla;
    
    private BotonTool cerrar;
    
    private TableTool tablaProductos;
    
    private Ctr_Personal_Caja ctrCaja;
    
    private int titulo2;
    
    private List<Tool> toolsUsado;
    
    public ContenidoBusqueda(int ancho, int alto, int titulo2, BotonTool cerrar, List<Detalle_Venta> itemsCarrito, boolean accesoServicios){
        this.titulo2 = titulo2;
        
        ctrCaja = new Ctr_Personal_Caja();
        
        toolsUsado = new ArrayList<>();
        
        filas = new VBox(5);
        filas.setAlignment(Pos.TOP_LEFT);
        filas.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-15))));
        filas.setPrefHeight(alto);
        filas.setPrefWidth(ancho);
        
        paneTabla = new Pane();
        
        int anchoField = ancho / 5 - 5;
        
        HBox cabecera = new HBox(5);
        
        TextFieldTool busquedaNombre = new TextFieldTool("Buscar por nombre", "Nombre:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        toolsUsado.add(busquedaNombre);
        
        List<Categoria> listCategorias = new ArrayList<>();
        listCategorias.add(new Categoria("Ninguna", "nada"));
        listCategorias.addAll(ctrCaja.selectAllCategorias());
        ComBoxTool<String> comboCategorias = new ComBoxTool(anchoField, "Categoria:" , listCategorias, titulo2);
        toolsUsado.add(comboCategorias);
        
        this.cerrar = cerrar;
        cerrar.setTranslateX(anchoField - 130);
        cerrar.setTranslateY(-35);
        
        TextFieldTool busquedaDescripcion = new TextFieldTool("Buscar por descripcion", "Descripcion:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        toolsUsado.add(busquedaDescripcion);
        
        List<String> listaTipo = new ArrayList<>();
        if(accesoServicios)
            listaTipo.add("Servicio");
        listaTipo.add("Producto");
        ComBoxTool<String> comboTipo = new ComBoxTool(anchoField, "Tipo:" , listaTipo, titulo2);
        comboTipo.getCombo().setOnAction(comprobar -> {
            quitarCategoria(comboTipo, comboCategorias);
        });
        
        BotonTool BotonBuscarProducto = new BotonTool("Buscar", titulo2, 100, titulo2 * 2 + 3, Color.GAINSBORO);
        BotonBuscarProducto.setTranslateY(16);
        
        cabecera.getChildren().addAll(busquedaNombre, comboCategorias, busquedaDescripcion, comboTipo, BotonBuscarProducto,cerrar);
        
        List<String> camposProductos = new ArrayList<>();
        camposProductos.add("Codigo");
        camposProductos.add("Nombre");
        camposProductos.add("Precio Unitario");
        camposProductos.add("Categoria");
        camposProductos.add("");
        
        List<String> camposServicios = new ArrayList<>();
        camposServicios.add("Codigo");
        camposServicios.add("Nombre");
        camposServicios.add("Costo");
        camposServicios.add("");
        
        BotonBuscarProducto.setOnMousePressed(buscarProducto -> {
            if(comprobarCampos(toolsUsado) && !comboTipo.isEmplyTool()){
                if(((String) comboTipo.getValue()).equals("Producto"))
                    insertarProductosBusqueda(ancho, camposProductos, itemsCarrito);
                else 
                    insertarServiciosBusqueda(ancho, camposServicios, itemsCarrito);
            }
        });
        
        tablaProductos = new TableTool(ancho, camposProductos, "No hay articulos con esta descripcion", titulo2);
        paneTabla.getChildren().add(tablaProductos);
        
        filas.getChildren().addAll(cabecera, paneTabla);
        
        getChildren().add(filas);
    }
    
    private void filtarMercaderia(int ancho, List<String> camposMercaderia){
        paneTabla.getChildren().clear();
                
        tablaProductos = new TableTool(ancho, camposMercaderia, "Este cliente no ha solicitado servicio a domicilio",titulo2);
        
        paneTabla.getChildren().add(tablaProductos);
    }
    
    private void filtarMascota(int ancho, List<String> camposMascota){
        paneTabla.getChildren().clear();
                
        tablaProductos = new TableTool(ancho, camposMascota, "Este cliente no posee mascotas",titulo2);
        
        paneTabla.getChildren().add(tablaProductos);
    }
    
    private void insertarProductosBusqueda(int ancho, List<String> camposProductos, List<Detalle_Venta> itemsCarrito){
        paneTabla.getChildren().clear();
                
        tablaProductos = new TableTool(ancho, camposProductos, "No hay productos con esta descripcion", titulo2);
        
        List<Producto> listaProductos = ctrCaja.filtarProductos(toolsUsado);
        
        ListIterator<Producto> it = listaProductos.listIterator();
        
        while(it.hasNext()){
            Producto pro = it.next();
            
            BotonTool botonAnadirCarrito = new BotonTool("Añadir", titulo2, 100, titulo2 + 5, Color.GREEN);
            botonAnadirCarrito.setOnMousePressed(anadirCarrito -> {
                Detalle_Venta detalle = new Detalle_VentaProducto(1, pro);
                itemsCarrito.add(detalle);
            });
            
            tablaProductos.anadirItem(pro.retornarAllData(), botonAnadirCarrito);
        }
        
        paneTabla.getChildren().add(tablaProductos);
    }
    
    private void insertarServiciosBusqueda(int ancho, List<String> camposServicios, List<Detalle_Venta> itemsCarrito){
        paneTabla.getChildren().clear();
                
        tablaProductos = new TableTool(ancho, camposServicios, "No hay productos con esta descripcion",titulo2);
        
        if(!toolsUsado.get(0).isEmplyTool() || !toolsUsado.get(2).isEmplyTool()){
        
            List<Servicio> listaProductos = ctrCaja.filtarServicio(toolsUsado);

            ListIterator<Servicio> it = listaProductos.listIterator();

            while(it.hasNext()){
                Servicio ser = it.next();

                BotonTool botonAnadirCarrito = new BotonTool("Añadir", titulo2, 100, titulo2 + 5, Color.GREEN);
                botonAnadirCarrito.setOnMousePressed(anadirCarrito -> {
                    Detalle_Venta detalle = new Detalle_VentaServicio(1, ser);
                    itemsCarrito.add(detalle);
                });

                tablaProductos.anadirItem(ser.retornarAllData(), botonAnadirCarrito);

            }
        }
        
        paneTabla.getChildren().add(tablaProductos);
    }
    
    private void quitarCategoria(ComBoxTool<String> combo,ComBoxTool<String> combo2){
        if(((String) combo.getValue()).equals("Producto"))
            combo2.setVisible(true);
        else
            combo2.setVisible(false);
    }
    
    private boolean comprobarCampos(List<Tool> toolUsados){
        ListIterator<Tool> it = toolUsados.listIterator();
        
        while(it.hasNext()){
            if(!it.next().isEmplyTool())
                return true;
        }
        
        return false;
    }
}
