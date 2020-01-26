/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import controladores.CtrPersonalCaja;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.DetalleVenta;
import modelo.DetalleVentaProducto;
import modelo.DetalleVentaServicio;
import modelo.Documento;
import modelo.PersonalCaja;
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
    private Documento documento;
    
    private CtrPersonalCaja ctr;
    
    private PersonalCaja personal;
    
    private TableVentaTool tablaPago;
    private TableTool tablaRegistro;
    
    public ContenidoVentas(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior, PersonalCaja personal){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        this.personal = personal;
        
        ctr = new CtrPersonalCaja();
        
        this.paneFondo = new Pane();
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
        List<DetalleVenta> itemsCarrito = new ArrayList<>();
        
        documento = new Venta();
        
        tablaPago = new TableVentaTool(anchoColunma2, titulo3);
        documento.calcularMonto(itemsCarrito);
        
        if(documento instanceof Venta)
            tablaPago.actualizarMonto(((Venta) documento).getSubtotal());
        else
            tablaPago.actualizarMonto(((Cotizacion) documento).getValor());
        
        List<String> lista = new ArrayList<>();
        lista.add("Nombre"); lista.add("Precio"); lista.add("Cantidad"); lista.add("Total");
        tablaRegistro = new TableTool(anchoColunma1 - 100, lista, "No hay articulos en el carrito", titulo3);
        
        HBox secionBuscador = new HBox();
        
        TextFieldTool textFieldBuscador = new TextFieldTool("Buscar Producto" ,titulo2, Pos.CENTER, 3 * anchoColunma1 / 5, 44);
        BotonTool botonBuscar = new BotonTool("Buscar", titulo2, 130, 44, colorOscuro);
        botonBuscar.setOnMousePressed(buscarArticulo -> {
            BotonTool cerrar = new BotonTool("X", titulo2, titulo2 * 2, titulo2 * 2, Color.RED);
            
            ContenidoBusqueda ventanaBusqueda = new ContenidoBusqueda(anchoVentana - reduccionX - 10, altoVentana - reduccionY - 10, titulo2, cerrar, documento.getCarrito(), personal.getSucursal().isOfreceServicios());
            
            cerrar.setOnMousePressed(cerrarVentana -> {
                getChildren().remove(ventanaBusqueda);
                insertarItems();
                documento.calcularMonto(documento.getCarrito());
                
                if(documento instanceof Venta)
                    tablaPago.actualizarMonto(((Venta) documento).getSubtotal());
                else
                    tablaPago.actualizarMonto(((Cotizacion) documento).getValor());
            });
            
            getChildren().add(ventanaBusqueda);
            
            ventanaBusqueda.setTranslateX(15);
            ventanaBusqueda.setTranslateY(5);
        });
            
        VBox paneCliente = new VBox(10);
        paneCliente.setAlignment(Pos.CENTER);
        
        VBox dataCliente = new VBox(5);
        Pane paneError = new Pane();
        
        HBox ssecionBuscadorCliente = new HBox();
        
        TextFieldTool textFieldBuscadorCliente = new TextFieldTool("Buscar Cliente", "Busqueda por cedula:" ,titulo2, Pos.CENTER, 3 * anchoColunma2 / 6, 40);
        BotonTool botonBuscarCliente = new BotonTool("Buscar", titulo2 - 1, 90, 40, colorOscuro);
        botonBuscarCliente.setOnMousePressed(buscarCliente -> {
            if(!textFieldBuscadorCliente.isEmplyTool()){
                List<Cliente> clientes = ctr.selectCliente((String) textFieldBuscadorCliente.getValue());
                if(!clientes.isEmpty()){
                    dataCliente.getChildren().clear();
                    documento.setCliente(clientes.get(0));
                    BoxTextTool dataNombreCliente = new BoxTextTool("Nombre: " + clientes.get(0).getNombre() + clientes.get(0).getApellido(), Color.BLACK, titulo3, FontWeight.NORMAL);
                    BoxTextTool dataCedulaCliente = new BoxTextTool("Cedula: " + clientes.get(0).getCedula(), Color.BLACK, titulo3, FontWeight.NORMAL);
                    dataCliente.setAlignment(Pos.CENTER_LEFT);
                    dataCliente.getChildren().addAll(dataNombreCliente, dataCedulaCliente);
                }
            }
        });
        botonBuscarCliente.setTranslateY(15);
        
        BotonTool botonConfirmarVenta = new BotonTool("Confirmar detalles", titulo2 - 1, 200, 40, Color.GREEN);
        botonConfirmarVenta.setOnMousePressed(validarVenta -> {
            paneError.getChildren().clear();
            documento.setFecha(LocalDate.now());
            documento.setNumeroFactura();
            documento.setPersonalCaja(personal);
            if(documento.comprobarValides()){
                ContenidoDetallesVenta confirmaDocuemnto = new ContenidoDetallesVenta(reduccionX, reduccionY, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior, documento, this);
                confirmaDocuemnto.establecerFuente(titulo3, titulo2, titulo1, colorOscuro, colorClaro);
                confirmaDocuemnto.crearContenidoCentral(new ArrayList<>());
                
                this.paneFondo.getChildren().add(confirmaDocuemnto);
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
        
        paneFondo.getChildren().add(paneCentral);
        
        this.getChildren().add(paneFondo);
    }
    
    
    private void insertarItems(){
        tablaRegistro.limpiarContenido();
        
        Iterator<DetalleVenta> it = documento.getCarrito().iterator();
        
        while(it.hasNext()){
            DetalleVenta det = it.next();
            
            List<String> dataItem = new ArrayList<>();
            
            if(det instanceof DetalleVentaProducto ){
                    dataItem.add(((DetalleVentaProducto) det).getProducto().getNombre());
                    dataItem.add(Double.toString(((DetalleVentaProducto) det).getProducto().getPrecioUnitario()));
                    dataItem.add(Integer.toString(det.getCantidad()));
                    dataItem.add(Double.toString(((DetalleVentaProducto) det).calcularPrecio()));
            } else if(det instanceof DetalleVentaServicio){
                    dataItem.add(((DetalleVentaServicio) det).getServicio().getNombre());
                    dataItem.add(Double.toString(((DetalleVentaServicio) det).getServicio().getPrecio()));
                    dataItem.add(Integer.toString(det.getCantidad()));
                    dataItem.add(Double.toString(((DetalleVentaServicio) det).calcularPrecio()));
                }
                
                BotonTool especificarDetalles = new BotonTool("Editar", titulo2, 100, titulo2 + 10, colorOscuro);
                especificarDetalles.setOnMousePressed(cambiarDetalle -> {
                    BotonTool botonSacarDetalles = new BotonTool("Aceptar", titulo2 - 1, 90, 40, colorOscuro);
                    BotonTool botonEliminar = new BotonTool("Eliminar", titulo2 - 1, 90, 40, Color.RED);
                    
                    ContenidoEspecificarDetalle detalleArticulo = new ContenidoEspecificarDetalle(reduccionX, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior, det);
                    detalleArticulo.establecerFuente(titulo3, titulo2, titulo1, colorOscuro, colorClaro);
                    detalleArticulo.crearContenidoCentral(new ArrayList<>());
                    detalleArticulo.anadirBotones(botonSacarDetalles, botonEliminar);
                    
                    botonSacarDetalles.setOnMousePressed(eliminarVnetanEmerrgente -> {
                        getChildren().remove(detalleArticulo);
                        detalleArticulo.setCantidad();
                        insertarItems();
                        documento.calcularMonto(documento.getCarrito());
                        if(documento instanceof Venta)
                            tablaPago.actualizarMonto(((Venta) documento).getSubtotal());
                        else
                            tablaPago.actualizarMonto(((Cotizacion) documento).getValor());
                    });
                    
                    botonEliminar.setOnMousePressed(eleiminarDeCarrito -> {
                        getChildren().remove(detalleArticulo);
                        documento.getCarrito().remove(det);
                        insertarItems();
                        documento.calcularMonto(documento.getCarrito());
                        if(documento instanceof Venta)
                            tablaPago.actualizarMonto(((Venta) documento).getSubtotal());
                        else
                            tablaPago.actualizarMonto(((Cotizacion) documento).getValor());
                    });
                    
                    getChildren().add(detalleArticulo);
                });
                
                tablaRegistro.anadirItem(dataItem, especificarDetalles);
        }
    }

    public Pane getPaneFondo() {
        return paneFondo;
    }

    @Override
    public void limpirarContenido() {
        tablaPago.actualizarMonto(0);
        tablaRegistro.limpiarContenido();
        documento.calcularMonto(new ArrayList<>());
    }
}
