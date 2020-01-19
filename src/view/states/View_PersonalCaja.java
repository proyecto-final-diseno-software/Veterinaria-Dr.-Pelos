/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import controladores.Ctr_Personal_Caja;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Detalle_Venta;
import modelo.Detalle_VentaProducto;
import modelo.Detalle_VentaServicio;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.TextFieldTool;
import view.tool.TableTool;
import view.tool.Tool;
import view.contenido.ContenidoAnadirUsuario;
import view.contenido.ContenidoBusqueda;
import view.contenido.ContenidoEspecificarDetalle;
import view.contenido.ContenidoPerfil;
import view.contenido.ContenidoTraslado;

/**
 *
 * @author paula
 */
public class View_PersonalCaja extends Ventana{
    private PrincipalContenedorCaja ventana;
    
     private Ctr_Personal_Caja contolCaja;

    @Override
    void mostrar_ventana(Stage primaryStage) {
    
    }

    @Override
    void close() {
        
    }

    @Override
    void cambiar_ventana(Pane root) {
        this.root = root;
        this.root.getChildren().clear();
        
        this.ventana = new PrincipalContenedorCaja();
        
        this.root.getChildren().add(ventana);
        
        this.contolCaja = new Ctr_Personal_Caja();
    }
    
    private class PrincipalContenedorCaja extends Parent{
        private VBox colunma1;
        private VBox colunma2;
        
        private FlowPane pane1;
        private FlowPane pane2;
        private FlowPane pane3;
        private FlowPane pane4;
        
        private int anchoLateral;
        private int altoSuperior;
        
        public PrincipalContenedorCaja(){
            pane1 = new FlowPane();
            pane2 = new FlowPane();
            pane3 = new FlowPane();
            pane4 = new FlowPane();
            
            menuLateral = new VBox();
            menuLateral.setAlignment(Pos.CENTER_LEFT);
            
            paneCentral = new HBox(20);
            paneFondo = new Pane();
            
            colunma1 = new VBox(35);
            colunma2 = new VBox(35);
            
            titulo1 = 30;
            titulo2 = 20;
            titulo3 = 15;
            
            anchoLateral = anchoVentana / 7;
            altoSuperior = altoVentana / 10;
            
            logo = new ImageView(new Image("util/logo2.png"));
            logo.setFitHeight(altoVentana / 10);
            logo.setFitWidth(anchoLateral);
            
            Rectangle barraSuperior = new Rectangle(anchoVentana, altoSuperior);
            barraSuperior.setFill(ColorOscuro);
            barraSuperior.setOpacity(0.7);
            
            Rectangle barraLateral = new Rectangle(anchoLateral, altoVentana);
            barraLateral.setFill(colorClaro);
            barraLateral.setOpacity(0.5);
            
            int altoBotones = 55;
            
            VBox paneCliente = new VBox();
            
            BotonTool botonClientes = new BotonTool("Clientes", titulo2, anchoLateral, altoBotones, true);
            
            BotonTool botonCrearClientes = new BotonTool(" - Añadir cliente", titulo2 - 5, anchoLateral, altoBotones - 20, false);
            botonCrearClientes.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonCrearClientes);
                cambiarContenidoAnadirUser(anchoLateral + 50);
            });
            
            BotonTool botonConsultarClientes = new BotonTool(" - Consultar cliente", titulo2 - 5, anchoLateral, altoBotones - 20, false);
            botonConsultarClientes.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonConsultarClientes);
            });
            botonClientes.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonClientes);
                if(paneCliente.getChildren().contains(botonCrearClientes)){
                    paneCliente.getChildren().removeAll(botonCrearClientes, botonConsultarClientes);
                } else{
                    paneCliente.getChildren().addAll(botonCrearClientes, botonConsultarClientes);
                }
            });
            
            paneCliente.getChildren().add(botonClientes);
            
            botonesLateral.add(botonClientes);
            botonesLateral.add(botonCrearClientes);
            botonesLateral.add(botonConsultarClientes);
            
            BotonTool botonInventario = new BotonTool("Inventario", titulo2, anchoLateral, altoBotones, false);
            botonInventario.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonInventario);
            });
            
            botonesLateral.add(botonInventario);
            
            BotonTool botonProveedores = new BotonTool("Proveedores", titulo2, anchoLateral, altoBotones, false);
            botonProveedores.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonProveedores);
            });
            
            botonesLateral.add(botonProveedores);
            
            BotonTool botonReportes = new BotonTool("Reportes", titulo2, anchoLateral, altoBotones, false);
            botonReportes.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonReportes);
            });
            
            botonesLateral.add(botonReportes);
            
            BotonTool botonVentas = new BotonTool("Ventas", titulo2, anchoLateral, altoBotones, false);
            botonVentas.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonVentas);
                cambiarContenidoVentas(anchoLateral + 50, altoSuperior + 50);
            });
            
            botonesLateral.add(botonVentas);
            
            VBox paneTraslado = new VBox();
            
            BotonTool botonTraslado = new BotonTool("Traslado", titulo2, anchoLateral, altoBotones, true);
            
            BotonTool botonTrasladoMascota = new BotonTool(" - Mascotas", titulo2 - 5, anchoLateral, altoBotones - 20, false);
            botonTrasladoMascota.setOnMousePressed(trasladoMascotas -> {
                comportamientoMenuLateral(botonTrasladoMascota);
                cambiarContenidoTraslado(anchoLateral + 50, "Traslado de mascotas", "Buscar Cliente");
            });
            botonesLateral.add(botonTrasladoMascota);
            
            BotonTool botonTransladoMercaderia = new BotonTool(" - Consultas entregas", titulo2 - 5, anchoLateral, altoBotones - 20, false);
            botonTransladoMercaderia.setOnMousePressed(trasladoMercaderia -> {
                comportamientoMenuLateral(botonTransladoMercaderia);
                cambiarConsultantregas(anchoLateral + 50, altoSuperior + 50);
            });
            botonesLateral.add(botonTransladoMercaderia);
            
            botonTraslado.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonTraslado);
                if(paneTraslado.getChildren().contains(botonTransladoMercaderia))
                    paneTraslado.getChildren().removeAll(botonTransladoMercaderia, botonTrasladoMascota);
                else
                    paneTraslado.getChildren().addAll(botonTransladoMercaderia, botonTrasladoMascota);
            });
            
            paneTraslado.getChildren().add(botonTraslado);
            
            botonesLateral.add(botonTraslado);
            
            BotonTool botonFacturacion = new BotonTool("Facturacion", titulo2, anchoLateral, altoBotones, false);
            botonFacturacion.setOnMousePressed(seccionClientes -> {
                comportamientoMenuLateral(botonFacturacion);
            });
            
            botonesLateral.add(botonFacturacion);
            
            menuLateral.getChildren().addAll(logo, paneCliente, botonInventario, botonProveedores, botonReportes, botonVentas, paneTraslado, botonFacturacion);
            
            paneFondo.setTranslateX(anchoLateral + 15);
            paneFondo.setTranslateY(altoSuperior + 25);
            
            paneFondo.getChildren().add(paneCentral);
            
            getChildren().addAll(barraLateral, barraSuperior , menuLateral, paneFondo);
        }
        
        private void cambiarContenidoAnadirUser(int reduccionx){
            establecerFondoUnico(reduccionx, Pos.CENTER_LEFT);
            
            List<Tool> toolUsados = new ArrayList<>();
            
            Pane mensajeError = new Pane();
            
            HBox botonesPane = new HBox(5);
            BotonTool guardarCliente = new BotonTool("Guardar Cliente", titulo2, 200, titulo2 * 2, ColorOscuro);
            guardarCliente.setOnMousePressed(guardarNuevoCliente -> {
                mensajeError.getChildren().clear();
                if(comprobarCampos(toolUsados)){
                    Cliente nuevoCliente = contolCaja.createCliente(toolUsados);
                    if(contolCaja.addClienteDataBase(nuevoCliente))
                        cambiarPefilCliente(reduccionx, nuevoCliente);
                    else
                        mensajeError.getChildren().add(new BoxTextTool("Hubo un error al crear el usuario\nintento mas tarde.", Color.RED, titulo3, FontWeight.BOLD));
                } else
                    mensajeError.getChildren().add(new BoxTextTool("Por favor ingrese los datos faltantes.", Color.RED, titulo3, FontWeight.BOLD));
            });
            BotonTool cancelarCliente = new BotonTool("Cancelar", titulo2, 200, titulo2 * 2, colorClaro);
            cancelarCliente.setOnMousePressed(cancelarNuevoCliente -> limpiarVentana());
            
            botonesPane.getChildren().addAll(cancelarCliente, guardarCliente);
            
            int mitadVentanaActiva = (anchoVentana - reduccionx) / 2 - 25;
            
            ContenidoAnadirUsuario ventanaAnadirUsuario = new ContenidoAnadirUsuario(mitadVentanaActiva, titulo1, titulo2);
            ventanaAnadirUsuario.crearContenidoCentral(toolUsados);
            ventanaAnadirUsuario.anadirSellecionInferior(botonesPane);
            ventanaAnadirUsuario.anadirSellecionInferior(mensajeError);
            
            pane1.getChildren().add(generarPaneCentral(ventanaAnadirUsuario));
        }
        
        private void cambiarContenidoVentas(int reduccionx, int reduccionY){
            List<Detalle_Venta> itemsCarrito = new ArrayList<>();
            
            limpiarVentana();
            
            colunma1.getChildren().addAll(pane1, pane2);
            colunma2.getChildren().addAll(pane3, pane4);
            
            paneCentral.getChildren().addAll(colunma1, colunma2);
            
            int anchoColunma1 = 2 * (anchoVentana - reduccionx) / 3;
            int anchoColunma2 = (anchoVentana - reduccionx) / 3;
            
            pane1.setPrefWrapLength(anchoColunma1);
            pane2.setPrefWrapLength(anchoColunma1);
            pane3.setPrefWrapLength(anchoColunma2);
            pane4.setPrefWrapLength(anchoColunma2);
            
            generarsecciones(pane1, Pos.CENTER);
            generarsecciones(pane2, Pos.CENTER);
            generarsecciones(pane3, Pos.CENTER);
            generarsecciones(pane4, Pos.CENTER);
            
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
                    getChildren().remove(ventana_busqueda);
                    insertarItems(itemsCarrito, tablaRegistro);
                });
                
                getChildren().add(ventana_busqueda);
                
                ventana_busqueda.setTranslateX(reduccionx - 20);
                ventana_busqueda.setTranslateY(reduccionY - 20);
            });
            
            HBox ssecionBuscadorCliente = new HBox();
            
            TextFieldTool textFieldBuscadorCliente = new TextFieldTool("Buscar Cliente" ,titulo2, Pos.CENTER, 3 * anchoColunma2 / 6, 40);
            BotonTool botonBuscarCliente = new BotonTool("Buscar", titulo2 - 1, 90, 40, ColorOscuro);
            botonBuscarCliente.setOnMousePressed(buscarCliente -> {
                System.out.println("Buscando Cliente" + ((String) textFieldBuscadorCliente.getValue()));
            });
            
            ssecionBuscador.getChildren().addAll(textFieldBuscador, botonBuscar);
            
            ssecionBuscadorCliente.getChildren().addAll(textFieldBuscadorCliente, botonBuscarCliente);
            
            TableTool tablaPago = new TableTool(anchoColunma2, titulo3);
            
            pane1.getChildren().add(ssecionBuscador);
            pane2.getChildren().add(tablaRegistro);
            pane3.getChildren().add(ssecionBuscadorCliente);
            pane4.getChildren().add(tablaPago);
        }
        
        private void insertarItems(List<Detalle_Venta> itemsCarrito, TableTool table){
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
                    
                    ContenidoEspecificarDetalle detalleArticulo = new ContenidoEspecificarDetalle(anchoVentana - (anchoLateral + 15) , altoVentana - (altoSuperior + 25),titulo1, titulo2, det);
                    detalleArticulo.crearContenidoCentral(new ArrayList<>());
                    detalleArticulo.anadirBotones(botonSacarDetalles, botonEliminar);
                    
                    botonSacarDetalles.setOnMousePressed(eliminarVnetanEmerrgente -> {
                        paneFondo.getChildren().remove(detalleArticulo);
                        detalleArticulo.setCantidad();
                        insertarItems(itemsCarrito, table);
                    });
                    
                    botonEliminar.setOnMousePressed(eleiminarDeCarrito -> {
                        paneFondo.getChildren().remove(detalleArticulo);
                        itemsCarrito.remove(det);
                        insertarItems(itemsCarrito, table);
                    });
                    
                    
                    paneFondo.getChildren().add(detalleArticulo);
                });
                
                table.anadirItem(dataItem, especificarDetalles);
            }
    }
        
        private void cambiarContenidoTraslado(int reduccionx, String tipo, String cabeceraField ){
            establecerFondoUnico(reduccionx, Pos.CENTER_LEFT);
            
            ContenidoTraslado ventanaTraslado= new ContenidoTraslado(reduccionx, tipo, cabeceraField, titulo1, titulo2, ColorOscuro, anchoVentana);
            
            pane1.getChildren().add(generarPaneCentral(ventanaTraslado));
        }
        
        private void cambiarConsultantregas(int reduccionX, int reduccionY){
            establecerFondoUnico(reduccionX, Pos.TOP_LEFT);
            
            ContenidoBusqueda ventanaConsultaEntrega = new ContenidoBusqueda(anchoVentana - reduccionX - 40, altoVentana - reduccionY - 10, titulo1, titulo2);
            
            pane1.getChildren().add(generarPaneCentral(ventanaConsultaEntrega));
        }
        
        private void cambiarPefilCliente(int reduccionX, Cliente cliente){
            establecerFondoUnico(reduccionX, Pos.TOP_LEFT);
            ContenidoPerfil ventanaPerfilCliente= new ContenidoPerfil(anchoVentana - reduccionX,titulo1, titulo2, cliente);
            ventanaPerfilCliente.crearContenidoCentral(null);
            pane1.getChildren().add(generarPaneCentral(ventanaPerfilCliente));
        }
        
        private Pane generarPaneCentral(Parent parent){
            Pane pane = new Pane();
            pane.setTranslateX(20);
            pane.getChildren().add(parent);
            
            return pane;
        }
        
        private void establecerFondoUnico(int reduccionx, Pos pos){
            limpiarVentana();
            colunma1.getChildren().add(pane1);
            paneCentral.getChildren().addAll(colunma1);
            pane1.setPrefWrapLength(anchoVentana - reduccionx);
            generarsecciones(pane1, pos);
        }
        
        private void limpiarVentana(){
            colunma1.getChildren().clear();
            colunma2.getChildren().clear();
            
            paneCentral.getChildren().clear();
        }
            
        private void generarsecciones(FlowPane pane, Pos pos){
            pane.setAlignment(pos);
            pane.getChildren().clear();
            pane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-10, 0, -20, 0))));
        }
    }
}