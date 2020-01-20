/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import modelo.Detalle_Venta;
import modelo.Detalle_VentaProducto;
import modelo.Detalle_VentaServicio;
import modelo.Venta;
import view.tool.BotonTool;
import view.tool.TableTool;
import view.tool.TableVentaTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoVentas extends Contenido implements ContenidoCentral{
    
    public ContenidoVentas(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
    }

    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        List<Detalle_Venta> itemsCarrito = new ArrayList<>();
            Venta nuevaVenta = new Venta();
            
            TableVentaTool tablaPago = new TableVentaTool(anchoColunma2, titulo3);
            tablaPago.actualizarMonto(calcularMontoApagar(itemsCarrito));
            
            List<String> lista = new ArrayList<>();
            lista.add("Nombre"); lista.add("Precio"); lista.add("Cantidad"); lista.add("Total");
            TableTool tablaRegistro = new TableTool(anchoColunma1 - 100, lista, "No hay articulos en el carrito", titulo3);
            
            HBox ssecionBuscador = new HBox();
            
            TextFieldTool textFieldBuscador = new TextFieldTool("Buscar Producto" ,titulo2, Pos.CENTER, 3 * anchoColunma1 / 5, 44);
            BotonTool botonBuscar = new BotonTool("Buscar", titulo2, 130, 44, ColorOscuro);
            botonBuscar.setOnMousePressed(buscarArticulo -> {
                BotonTool cerrar = new BotonTool("X", titulo2, titulo2 * 2, titulo2 * 2, Color.RED);
                
                ContenidoBusqueda ventana_busqueda = new ContenidoBusqueda(anchoVentana - reduccionx - 10, altoVentana - reduccionY - 10, titulo2, cerrar, itemsCarrito);
                
                cerrar.setOnMousePressed(cerrar_ventana -> {
                    paneFondo.getChildren().remove(ventana_busqueda);
                    insertarItems(itemsCarrito, tablaRegistro, tablaPago);
                    tablaPago.actualizarMonto(calcularMontoApagar(itemsCarrito));
                });
                
                paneFondo.getChildren().add(ventana_busqueda);
                
                ventana_busqueda.setTranslateX(15);
                ventana_busqueda.setTranslateY(5);
            });
            
            HBox ssecionBuscadorCliente = new HBox();
            
            TextFieldTool textFieldBuscadorCliente = new TextFieldTool("Buscar Cliente" ,titulo2, Pos.CENTER, 3 * anchoColunma2 / 6, 40);
            BotonTool botonBuscarCliente = new BotonTool("Buscar", titulo2 - 1, 90, 40, ColorOscuro);
            botonBuscarCliente.setOnMousePressed(buscarCliente -> {
                System.out.println("Buscando Cliente" + ((String) textFieldBuscadorCliente.getValue()));
            });
            
            ssecionBuscador.getChildren().addAll(textFieldBuscador, botonBuscar);
            
            ssecionBuscadorCliente.getChildren().addAll(textFieldBuscadorCliente, botonBuscarCliente);
            
            pane1.getChildren().add(ssecionBuscador);
            pane2.getChildren().add(tablaRegistro);
            pane3.getChildren().add(ssecionBuscadorCliente);
            pane4.getChildren().add(tablaPago);
    }
    
    private long calcularMontoApagar(List<Detalle_Venta> itemsCarrito){
            Iterator<Detalle_Venta> it = itemsCarrito.iterator();
            
            long monto = 0;
            
            while(it.hasNext()){
                Detalle_Venta det = it.next();
                monto += det.calcularPrecio();
            }
            
            return monto;
        }
        
        private void insertarItems(List<Detalle_Venta> itemsCarrito, TableTool table, TableVentaTool tablaPago){
            table.limpiarContenido();
            
            ListIterator<Detalle_Venta> it = itemsCarrito.listIterator();

            while(it.hasNext()){
                Detalle_Venta det = it.next();
                
                List<String> dataItem = new ArrayList<>();
                
                if(det instanceof Detalle_VentaProducto ){
                    dataItem.add(((Detalle_VentaProducto) det).getProducto().getNombre());
                    dataItem.add(Double.toString(((Detalle_VentaProducto) det).getProducto().getPrecioUnitario()));
                    dataItem.add(Integer.toString(det.getCantidad()));
                    dataItem.add(Double.toString(((Detalle_VentaProducto) det).calcularPrecio()));
                } else if(det instanceof Detalle_VentaServicio){
                    dataItem.add(((Detalle_VentaServicio) det).getServicio().getNombre());
                    dataItem.add(Double.toString(((Detalle_VentaServicio) det).getServicio().getPrecio()));
                    dataItem.add(Integer.toString(det.getCantidad()));
                    dataItem.add(Double.toString(((Detalle_VentaServicio) det).calcularPrecio()));
                }
                
                BotonTool especificarDetalles = new BotonTool("Editar", titulo2, 100, titulo2 + 10, ColorOscuro);
                especificarDetalles.setOnMousePressed(cambiarDeatalle -> {
                    BotonTool botonSacarDetalles = new BotonTool("Aceptar", titulo2 - 1, 90, 40, ColorOscuro);
                    BotonTool botonEliminar = new BotonTool("Eliminar", titulo2 - 1, 90, 40, Color.RED);
                    
                    ContenidoEspecificarDetalle detalleArticulo = new ContenidoEspecificarDetalle(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior, det);
                    detalleArticulo.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
                    detalleArticulo.establecerPaneles(pane1, pane2, pane3, pane4, paneFondo);
                    detalleArticulo.crearContenidoCentral(new ArrayList<Tool>());
                    detalleArticulo.anadirBotones(botonSacarDetalles, botonEliminar);
                    
                    botonSacarDetalles.setOnMousePressed(eliminarVnetanEmerrgente -> {
                        detalleArticulo.removerPanel();
                        detalleArticulo.setCantidad();
                        insertarItems(itemsCarrito, table, tablaPago);
                        tablaPago.actualizarMonto(calcularMontoApagar(itemsCarrito));
                    });
                    
                    botonEliminar.setOnMousePressed(eleiminarDeCarrito -> {
                        detalleArticulo.removerPanel();
                        itemsCarrito.remove(det);
                        insertarItems(itemsCarrito, table, tablaPago);
                        tablaPago.actualizarMonto(calcularMontoApagar(itemsCarrito));
                    });
                });
                
                table.anadirItem(dataItem, especificarDetalles);
            }
    }
}
