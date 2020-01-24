/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import controladores.Ctr_Personal_Caja;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import modelo.Cliente;
import modelo.Detalle_Venta;
import modelo.Detalle_VentaProducto;
import modelo.Detalle_VentaServicio;
import modelo.Personal_Caja;
import modelo.Venta;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.TableTool;
import view.tool.TableVentaTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoVentas extends Contenido implements ContenidoCentral{
    private Venta nuevaVenta;
    
    private Ctr_Personal_Caja ctr;
    
    private Personal_Caja personal;
    
    public ContenidoVentas(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior, Personal_Caja personal){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        this.personal = personal;
        
        ctr = new Ctr_Personal_Caja();
        
        this.paneCentral = new HBox(15);
        
        this.colunma1 = new VBox(40);
        this.colunma2 = new VBox(40);
        
        this.pane1 = new FlowPane();
        this.pane2 = new FlowPane();
        this.pane3 = new FlowPane();
        this.pane4 = new FlowPane();
    }
    
    @Override
    public void establecerPaneles(){
        colunma1.getChildren().addAll(this.pane1, this.pane2);
        colunma2.getChildren().addAll(this.pane3, this.pane4);
        
        this.anchoColunma1 = 2 * (anchoVentana - reduccionX) / 3;
        this.anchoColunma2 = (anchoVentana - reduccionX) / 3;
        
        this.pane1.setPrefWrapLength(anchoColunma1);
        this.pane2.setPrefWrapLength(anchoColunma1);
        this.pane3.setPrefWrapLength(anchoColunma2);
        this.pane4.setPrefWrapLength(anchoColunma2);
        
        generarsecciones(this.pane1, Pos.CENTER);
        generarsecciones(this.pane2, Pos.CENTER);
        generarsecciones(this.pane3, Pos.CENTER);
        generarsecciones(this.pane4, Pos.CENTER);
        
        paneCentral.getChildren().addAll(colunma1, colunma2);
    }

    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        List<Detalle_Venta> itemsCarrito = new ArrayList<>();
        
        nuevaVenta = new Venta();
        
        nuevaVenta.setPersonalCaja(personal);
        
        TableVentaTool tablaPago = new TableVentaTool(anchoColunma2, titulo3);
        nuevaVenta.calcularMontoApagar(itemsCarrito);
        tablaPago.actualizarMonto(nuevaVenta.getSubtotal());
        
        List<String> lista = new ArrayList<>();
        lista.add("Nombre"); lista.add("Precio"); lista.add("Cantidad"); lista.add("Total");
        TableTool tablaRegistro = new TableTool(anchoColunma1 - 100, lista, "No hay articulos en el carrito", titulo3);
        
        HBox secionBuscador = new HBox();
        
        TextFieldTool textFieldBuscador = new TextFieldTool("Buscar Producto" ,titulo2, Pos.CENTER, 3 * anchoColunma1 / 5, 44);
        BotonTool botonBuscar = new BotonTool("Buscar", titulo2, 130, 44, ColorOscuro);
        botonBuscar.setOnMousePressed(buscarArticulo -> {
            BotonTool cerrar = new BotonTool("X", titulo2, titulo2 * 2, titulo2 * 2, Color.RED);
            
            ContenidoBusqueda ventana_busqueda = new ContenidoBusqueda(anchoVentana - reduccionX - 10, altoVentana - reduccionY - 10, titulo2, cerrar, itemsCarrito, personal.getSucursal().isOfreceServicios());
            
            cerrar.setOnMousePressed(cerrar_ventana -> {
                getChildren().remove(ventana_busqueda);
                insertarItems(itemsCarrito, tablaRegistro, tablaPago);
                nuevaVenta.calcularMontoApagar(itemsCarrito);
                tablaPago.actualizarMonto(nuevaVenta.getSubtotal());
            });
            
            getChildren().add(ventana_busqueda);
            
            ventana_busqueda.setTranslateX(15);
            ventana_busqueda.setTranslateY(5);
        });
            
        VBox paneCliente = new VBox(10);
        paneCliente.setAlignment(Pos.CENTER);
        
        VBox dataCliente = new VBox(5);
        Pane paneError = new Pane();
        
        HBox ssecionBuscadorCliente = new HBox();
        
        TextFieldTool textFieldBuscadorCliente = new TextFieldTool("Buscar Cliente", "Busqueda por cedula:" ,titulo2, Pos.CENTER, 3 * anchoColunma2 / 6, 40);
        BotonTool botonBuscarCliente = new BotonTool("Buscar", titulo2 - 1, 90, 40, ColorOscuro);
        botonBuscarCliente.setOnMousePressed(buscarCliente -> {
            if(!textFieldBuscadorCliente.isEmplyTool()){
                List<Cliente> clientes = ctr.selectCliente((String) textFieldBuscadorCliente.getValue());
                if(!clientes.isEmpty()){
                    dataCliente.getChildren().clear();
                    nuevaVenta.setCliente(clientes.get(0));
                    BoxTextTool dataNombreCliente = new BoxTextTool("Nombre: " + clientes.get(0).getNombre() + clientes.get(0).getApellido(), Color.BLACK, titulo3, FontWeight.NORMAL);
                    BoxTextTool dataCedulaCliente = new BoxTextTool("Cedula: " + clientes.get(0).getCedula(), Color.BLACK, titulo3, FontWeight.NORMAL);
                    dataCliente.setAlignment(Pos.CENTER_LEFT);
                    dataCliente.getChildren().addAll(dataNombreCliente, dataCedulaCliente);
                }
            }
        });
        botonBuscarCliente.setTranslateY(15);
        
        BotonTool botonConfirmarVenta = new BotonTool("Confirmar venta", titulo2 - 1, 200, 40, Color.GREEN);
        botonConfirmarVenta.setOnMousePressed(validarVenta -> {
            paneError.getChildren().clear();
            nuevaVenta.setFecha(LocalDate.now());
            nuevaVenta.setForma_pago_ID(ctr.retornaMetodoPago());
            nuevaVenta.setNumeroFactura(123456789);
            nuevaVenta.setPersonalCaja(personal);
            if(nuevaVenta.comprobarValidesDeVenta()){
                if(ctr.insertVenta(nuevaVenta))
                    guardarDetallesVenta(itemsCarrito);
                else
                    paneError.getChildren().add(new BoxTextTool("\nError al registrar venta", Color.RED, titulo2, FontWeight.NORMAL));
            } else
                paneError.getChildren().add(new BoxTextTool("\nFalta informacion", Color.RED, titulo2, FontWeight.NORMAL));
        });
            
        ssecionBuscadorCliente.getChildren().addAll(textFieldBuscadorCliente, botonBuscarCliente);
        paneCliente.getChildren().addAll(ssecionBuscadorCliente, dataCliente);
        
        secionBuscador.getChildren().addAll(textFieldBuscador, botonBuscar);
        pane1.getChildren().add(secionBuscador);
        pane2.getChildren().add(tablaRegistro);
        pane3.getChildren().add(paneCliente);
        pane4.getChildren().addAll(tablaPago, botonConfirmarVenta, paneError);
        
        this.getChildren().add(paneCentral);
    }
    
    private void guardarDetallesVenta(List<Detalle_Venta> itemsCarrito){
        Iterator<Detalle_Venta> it = itemsCarrito.iterator();
        
        while(it.hasNext()){
            Detalle_Venta det = it.next();
            ctr.guardarDetalleVenta(det);
        }
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
                especificarDetalles.setOnMousePressed(cambiarDetalle -> {
                    BotonTool botonSacarDetalles = new BotonTool("Aceptar", titulo2 - 1, 90, 40, ColorOscuro);
                    BotonTool botonEliminar = new BotonTool("Eliminar", titulo2 - 1, 90, 40, Color.RED);
                    
                    ContenidoEspecificarDetalle detalleArticulo = new ContenidoEspecificarDetalle(reduccionX, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior, det);
                    detalleArticulo.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
                    detalleArticulo.crearContenidoCentral(new ArrayList<>());
                    detalleArticulo.anadirBotones(botonSacarDetalles, botonEliminar);
                    
                    botonSacarDetalles.setOnMousePressed(eliminarVnetanEmerrgente -> {
                        getChildren().remove(detalleArticulo);
                        detalleArticulo.setCantidad();
                        insertarItems(itemsCarrito, table, tablaPago);
                        nuevaVenta.calcularMontoApagar(itemsCarrito);
                        tablaPago.actualizarMonto(nuevaVenta.getSubtotal());
                    });
                    
                    botonEliminar.setOnMousePressed(eleiminarDeCarrito -> {
                        getChildren().remove(detalleArticulo);
                        itemsCarrito.remove(det);
                        insertarItems(itemsCarrito, table, tablaPago);
                        nuevaVenta.calcularMontoApagar(itemsCarrito);
                        tablaPago.actualizarMonto(nuevaVenta.getSubtotal());
                    });
                    
                    getChildren().add(detalleArticulo);
                });
                
                table.anadirItem(dataItem, especificarDetalles);
            }
    }
}
