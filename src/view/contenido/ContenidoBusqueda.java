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
import javafx.scene.text.FontWeight;
import modelo.Producto;
import modelo.Servicio;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
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
    
    public ContenidoBusqueda(int ancho, int alto, int titulo2, BotonTool cerrar, List<Producto> listProducto, List<Servicio> ListServicios){
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
        
        List<String> listaCategorias = new ArrayList<>();
        listaCategorias.add("Opcion 1");
        ComBoxTool<String> comboCategorias = new ComBoxTool(anchoField, "Categoria:" , listaCategorias, titulo2);
        toolsUsado.add(comboCategorias);
        
        this.cerrar = cerrar;
        cerrar.setTranslateX(anchoField - 130);
        cerrar.setTranslateY(-35);
        
        TextFieldTool busquedaDescripcion = new TextFieldTool("Buscar por descripcion", "Descripcion:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        toolsUsado.add(busquedaDescripcion);
        
        List<String> listaTipo = new ArrayList<>();
        listaTipo.add("Servicio");
        listaTipo.add("Producto");
        ComBoxTool<String> comboTipo = new ComBoxTool(anchoField, "Tipo:" , listaTipo, titulo2);
        toolsUsado.add(comboTipo);
        
        BotonTool BotonBuscarProducto = new BotonTool("Buscar", titulo2, 100, titulo2 * 2 + 3, Color.GAINSBORO);
        BotonBuscarProducto.setTranslateY(16);
        
        cabecera.getChildren().addAll(busquedaNombre, comboCategorias, busquedaDescripcion, comboTipo, BotonBuscarProducto,cerrar);
        
        List<String> camposProductos = new ArrayList<>();
        camposProductos.add("Codigo");
        camposProductos.add("Nombre");
        camposProductos.add("Precio Unitario");
        camposProductos.add("Descripcion");
        camposProductos.add("Categoria");
        camposProductos.add("");
        
        List<String> camposServicios = new ArrayList<>();
        camposServicios.add("Codigo");
        camposServicios.add("Nombre");
        camposServicios.add("Costo");
        camposServicios.add("Descripcion");
        camposServicios.add("");
        
        BotonBuscarProducto.setOnMousePressed(buscarProducto -> {
            if(comprobarCampos(toolsUsado)){
                if(((String) comboTipo.getValue()).equals("Producto"))
                    insertarProductosBusqueda(ancho, camposProductos, listProducto);
                else
                    insertarServiciosBusqueda(ancho, camposServicios, ListServicios);
            }
        });
        
        tablaProductos = new TableTool(ancho, camposProductos, "No hay articulos con esta descripcion", titulo2);
        paneTabla.getChildren().add(tablaProductos);
        
        filas.getChildren().addAll(cabecera, paneTabla);
        
        getChildren().add(filas);
    }
    
    public ContenidoBusqueda(int ancho, int alto, int titulo1, int titulo2){
        List<Tool> toolsUsado = new ArrayList<>();
        
        filas = new VBox(20);
        filas.setAlignment(Pos.TOP_LEFT);
        
        paneTabla = new Pane();
        
        int anchoField = ancho / 4;
        
        HBox Tutuo = new HBox(5);
        BoxTextTool cabeceraTexto = new BoxTextTool("Busqueda de envios", Color.BLACK, titulo1, FontWeight.BOLD);
        Tutuo.getChildren().add(cabeceraTexto);
        
        HBox cabecera = new HBox(5);
        
        TextFieldTool busquedaID = new TextFieldTool("Buscar por id", "ID:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        toolsUsado.add(busquedaID);
        
        TextFieldTool busquedaCliente = new TextFieldTool("Buscar por Cliente", "Cliente:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        toolsUsado.add(busquedaCliente);
        
        List<String> listaTipo = new ArrayList<>();
        listaTipo.add("Mercaderia");
        listaTipo.add("Mascota");
        ComBoxTool<String> comboTipo = new ComBoxTool(anchoField, "Tipo:" , listaTipo, titulo2);
        toolsUsado.add(comboTipo);
        
        BotonTool BotonBuscarProducto = new BotonTool("Buscar", titulo2, 100, titulo2 * 2 + 3, Color.GAINSBORO);
        BotonBuscarProducto.setTranslateY(16);
        
        cabecera.getChildren().addAll(busquedaID, busquedaCliente, comboTipo, BotonBuscarProducto);
        
        List<String> camposMercaderia = new ArrayList<>();
        camposMercaderia.add("Codigo");
        camposMercaderia.add("Fecha");
        camposMercaderia.add("Monto Total");
        camposMercaderia.add("Descuento");
        
        camposMercaderia.add("");
        
        List<String> camposMascotas = new ArrayList<>();
        camposMascotas.add("Codigo");
        camposMascotas.add("Nombre");
        camposMascotas.add("Raza");
        camposMascotas.add("");
        
        BotonBuscarProducto.setOnMousePressed(buscarProducto -> {
            
                if(((String) comboTipo.getValue()).equals("Mercaderia"))
                    filtarMercaderia(ancho, camposMercaderia);
                else
                    filtarMascota(ancho, camposMascotas);
        });
        
        tablaProductos = new TableTool(ancho, camposMercaderia, "No hay articulos con esta descripcion", titulo2);
        paneTabla.getChildren().add(tablaProductos);
        
        filas.getChildren().addAll(Tutuo, cabecera, paneTabla);
        
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
    
    private void insertarProductosBusqueda(int ancho, List<String> camposProductos, List<Producto> listProducto){
        paneTabla.getChildren().clear();
                
        tablaProductos = new TableTool(ancho, camposProductos, "No hay productos con esta descripcion",titulo2);
        
        List<Producto> listaProductos = ctrCaja.filtarProductos(toolsUsado);
        
        ListIterator<Producto> it = listaProductos.listIterator();
        
        while(it.hasNext()){
            Producto pro = it.next();
            
            BotonTool botonAnadirCarrito = new BotonTool("Añadir", titulo2, 100, titulo2 + 5, Color.GREEN);
            botonAnadirCarrito.setOnMousePressed(anadirCarrito -> {
                listProducto.add(pro);
            });
            
            tablaProductos.anadirItem(pro.retornarAllData(), botonAnadirCarrito);
        }
        
        paneTabla.getChildren().add(tablaProductos);
    }
    
    private void insertarServiciosBusqueda(int ancho, List<String> camposServicios, List<Servicio> listServicios){
        paneTabla.getChildren().clear();
                
        tablaProductos = new TableTool(ancho, camposServicios, "No hay productos con esta descripcion",titulo2);
        
        List<Servicio> listaProductos = ctrCaja.filtarServicio(toolsUsado);
        
        ListIterator<Servicio> it = listaProductos.listIterator();
        
        while(it.hasNext()){
            Servicio ser = it.next();
            
            BotonTool botonAnadirCarrito = new BotonTool("Añadir", titulo2, 100, titulo2 + 5, Color.GREEN);
            botonAnadirCarrito.setOnMousePressed(anadirCarrito -> {
                listServicios.add(ser);
            });
            
            tablaProductos.anadirItem(ser.retornarAllData(), botonAnadirCarrito);
        }
        
        paneTabla.getChildren().add(tablaProductos);
    }
    
    private boolean comprobarCampos(List<Tool> toolUsados){
        ListIterator<Tool> it = toolUsados.listIterator();
        
        while(it.hasNext()){
            if(it.next().isEmplyTool())
                return false;
        }
        
        return true;
    }
}
