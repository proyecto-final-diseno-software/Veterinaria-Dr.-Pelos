/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

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
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.TextFieldTool;
import view.tool.TableTool;
import view.tool.Tool;
import view.util.View_Busqueda;

/**
 *
 * @author paula
 */
public class View_PersonalCaja extends Ventana{
    private PrincipalContenedorCaja ventana;

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
    }
    
    private class PrincipalContenedorCaja extends Parent{
        private VBox colunma1;
        private VBox colunma2;
        
        private FlowPane pane1;
        private FlowPane pane2;
        private FlowPane pane3;
        private FlowPane pane4;
        
        public PrincipalContenedorCaja(){
            pane1 = new FlowPane();
            pane2 = new FlowPane();
            pane3 = new FlowPane();
            pane4 = new FlowPane();
            
            menuLateral = new VBox();
            menuLateral.setAlignment(Pos.CENTER_LEFT);
            
            paneCentral = new HBox(20);
            
            colunma1 = new VBox(35);
            colunma2 = new VBox(35);
            
            titulo1 = 30;
            titulo2 = 20;
            titulo3 = 15;
            
            int anchoLateral = anchoVentana / 7;
            int altoSuperior = altoVentana / 10;
            
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
                }
                else{
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
            });
            botonesLateral.add(botonTrasladoMascota);

            
            BotonTool botonTransladoMercaderia = new BotonTool(" - Mercaderia", titulo2 - 5, anchoLateral, altoBotones - 20, false);
            botonTransladoMercaderia.setOnMousePressed(trasladoMercaderia -> {
                comportamientoMenuLateral(botonTransladoMercaderia);
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
            
            paneCentral.setTranslateX(anchoLateral + 15);
            paneCentral.setTranslateY(altoSuperior + 25);
            
            getChildren().addAll(barraLateral, barraSuperior , menuLateral, paneCentral);
        }
        
        public void limpiarVentana(){
            colunma1.getChildren().clear();
            colunma2.getChildren().clear();
            
            paneCentral.getChildren().clear();
        }
        
        private void generarsecciones(FlowPane pane, Pos pos){
            pane.setAlignment(pos);
            pane.getChildren().clear();
            pane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-10, 0, -10, 0))));
        }
        
        private void cambiarContenidoAnadirUser(int reduccionx){
            List<Tool> toolUsados = new ArrayList<>();
            
            establecerFondoUnico(reduccionx);
            
            Pane mensajeError = new Pane();
            
            VBox paneIngresoDatos = new VBox(20);
            paneIngresoDatos.setTranslateX(20);
            
            HBox cabecera = new HBox(5);
            BoxTextTool cabeceraTexto = new BoxTextTool("Registro Cliente", Color.BLACK, titulo1, FontWeight.BOLD);
            cabecera.getChildren().add(cabeceraTexto);
            
            int mitadVentanaActiva = (anchoVentana - reduccionx) / 2 - 25;
            
            HBox primero = new HBox(20);
            TextFieldTool textFieldNombre = new TextFieldTool("Ingrese nombre del nuevo cliente", "Nombre:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            TextFieldTool textFieldApellido = new TextFieldTool("Ingrese apellido del nuevo cliente", "Apellido:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            primero.getChildren().addAll(textFieldNombre, textFieldApellido);
            
            toolUsados.add(textFieldNombre);
            toolUsados.add(textFieldApellido);
            
            HBox segundo = new HBox(20);
            TextFieldTool textFieldDireccion = new TextFieldTool("Ingrese direccion del nuevo cliente", "Direccion:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            TextFieldTool textFieldtelefono = new TextFieldTool("Ingrese el telefono del nuevo cliente", "Telefono:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            segundo.getChildren().addAll(textFieldDireccion, textFieldtelefono);
            
            toolUsados.add(textFieldDireccion);
            toolUsados.add(textFieldtelefono);
            
            HBox botones = new HBox(5);
            BotonTool guardarCliente = new BotonTool("Guardar Cliente", titulo2, 200, titulo2 * 2, ColorOscuro);
            guardarCliente.setOnMousePressed(guardarNuevoCliente -> {
                if(comprobarCampos(toolUsados))
                    System.out.println("Valido");
                else{
                    mensajeError.getChildren().clear();
                    mensajeError.getChildren().add(new BoxTextTool("Por favor ingrese los datos faltantes", Color.RED, titulo1, FontWeight.BOLD));
                }
            });
            BotonTool cancelarCliente = new BotonTool("Cancelar", titulo2, 200, titulo2 * 2, colorClaro);
            cancelarCliente.setOnMousePressed(cancelarNuevoCliente -> {
                limpiarVentana();
            });
            botones.getChildren().addAll(cancelarCliente, guardarCliente);
            
            paneIngresoDatos.getChildren().addAll(cabecera, primero, segundo, botones, mensajeError);
            pane1.getChildren().add(paneIngresoDatos);
        }
        
        private void cambiarContenidoVentas(int reduccionx, int reduccionY){
            limpiarVentana();
            
            colunma1.getChildren().addAll(pane1, pane2);
            colunma2.getChildren().addAll(pane3, pane4);
            
            paneCentral.getChildren().addAll(colunma1, colunma2);
            
            int colunma1 = 2 * (anchoVentana - reduccionx) / 3;
            int colunma2 = (anchoVentana - reduccionx) / 3;
            
            int alto = 300;
            
            pane1.setPrefWrapLength(colunma1);
            pane2.setPrefWrapLength(colunma1);
            pane3.setPrefWrapLength(colunma2);
            pane4.setPrefWrapLength(colunma2);
            
            generarsecciones(pane1, Pos.CENTER);
            generarsecciones(pane2, Pos.CENTER);
            generarsecciones(pane3, Pos.CENTER);
            generarsecciones(pane4, Pos.CENTER);
            
            HBox ssecionBuscador = new HBox();
            
            TextFieldTool textFieldBuscador = new TextFieldTool("Buscar Producto" ,titulo2, Pos.CENTER, 3 * colunma1 / 5, 44);
            BotonTool botonBuscar = new BotonTool("Buscar", titulo2, 130, 44, ColorOscuro);
            botonBuscar.setOnMousePressed(buscarArticulo -> {
                System.out.println("Buscando " + ((String) textFieldBuscador.getValue()));
                
                BotonTool cerrar = new BotonTool("X", titulo2, titulo2 * 2, titulo2 * 2, Color.RED);
                
                View_Busqueda ventana_busqueda = new View_Busqueda(anchoVentana - reduccionx - 10, altoVentana - reduccionY - 10, titulo2, cerrar);
                
                cerrar.setOnMousePressed(cerrar_vetana -> getChildren().remove(ventana_busqueda));
                
                getChildren().add(ventana_busqueda);
                
                ventana_busqueda.setTranslateX(reduccionx - 20);
                ventana_busqueda.setTranslateY(reduccionY - 20);
            });
            
            HBox ssecionBuscadorCliente = new HBox();
            
            TextFieldTool textFieldBuscadorCliente = new TextFieldTool("Buscar Cliente" ,titulo2, Pos.CENTER, 3 * colunma2 / 6, 40);
            BotonTool botonBuscarCliente = new BotonTool("Buscar", titulo2 - 1, 90, 40, ColorOscuro);
            botonBuscarCliente.setOnMousePressed(buscarCliente -> {
                System.out.println("Buscando Cliente" + ((String) textFieldBuscadorCliente.getValue()));
            });
            
            List<String> lista = new ArrayList<>();
            lista.add("Nombre");
            lista.add("Precio");
            lista.add("Cantidad");
            lista.add("Descuento");
            lista.add("Total");
            
            TableTool tablaRegistro = new TableTool(colunma1, lista, "No hay articulos en el carrito");
            
            ssecionBuscador.getChildren().addAll(textFieldBuscador, botonBuscar);
            
            ssecionBuscadorCliente.getChildren().addAll(textFieldBuscadorCliente, botonBuscarCliente);
            
            TableTool tablaPago = new TableTool(colunma2);
            
            pane1.getChildren().add(ssecionBuscador);
            pane2.getChildren().add(tablaRegistro);
            pane3.getChildren().add(ssecionBuscadorCliente);
            pane4.getChildren().add(tablaPago);
        }
        
        private void cambiarContenidoTrasladoMascotas(int reduccionx){
            List<Tool> toolUsados = new ArrayList<>();
            
            establecerFondoUnico(reduccionx);
            
            Pane mensajeError = new Pane();
            
            VBox paneIngresoDatos = new VBox(20);
            paneIngresoDatos.setTranslateX(20);
            
            HBox cabecera = new HBox(5);
            BoxTextTool cabeceraTexto = new BoxTextTool("Translado Mascotas", Color.BLACK, titulo1, FontWeight.BOLD);
            cabecera.getChildren().add(cabeceraTexto);
            
            int mitadVentanaActiva = (anchoVentana - reduccionx) / 2 - 25;
            
            HBox primero = new HBox(20);
            TextFieldTool textFieldNombre = new TextFieldTool("Ingrese nombre del nuevo cliente", "Nombre:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            TextFieldTool textFieldApellido = new TextFieldTool("Ingrese apellido del nuevo cliente", "Apellido:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            primero.getChildren().addAll(textFieldNombre, textFieldApellido);
            
            toolUsados.add(textFieldNombre);
            toolUsados.add(textFieldApellido);
            
            HBox segundo = new HBox(20);
            TextFieldTool textFieldDireccion = new TextFieldTool("Ingrese direccion del nuevo cliente", "Direccion:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            TextFieldTool textFieldtelefono = new TextFieldTool("Ingrese el telefono del nuevo cliente", "Telefono:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            segundo.getChildren().addAll(textFieldDireccion, textFieldtelefono);
            
            toolUsados.add(textFieldDireccion);
            toolUsados.add(textFieldtelefono);
            
            HBox botones = new HBox(5);
            BotonTool guardarCliente = new BotonTool("Guardar Cliente", titulo2, 200, titulo2 * 2, ColorOscuro);
            guardarCliente.setOnMousePressed(guardarNuevoCliente -> {
                if(comprobarCampos(toolUsados))
                    System.out.println("Valido");
                else{
                    mensajeError.getChildren().clear();
                    mensajeError.getChildren().add(new BoxTextTool("Por favor ingrese los datos faltantes", Color.RED, titulo1, FontWeight.BOLD));
                }
            });
            BotonTool cancelarCliente = new BotonTool("Cancelar", titulo2, 200, titulo2 * 2, colorClaro);
            cancelarCliente.setOnMousePressed(cancelarNuevoCliente -> {
                limpiarVentana();
            });
            botones.getChildren().addAll(cancelarCliente, guardarCliente);
            
            paneIngresoDatos.getChildren().addAll(cabecera, primero, segundo, botones, mensajeError);
            pane1.getChildren().add(paneIngresoDatos);
        }
        
        private void establecerFondoUnico(int reduccionx){
            limpiarVentana();
            colunma1.getChildren().add(pane1);
            paneCentral.getChildren().addAll(colunma1);
            pane1.setPrefWrapLength(anchoVentana - reduccionx);
            generarsecciones(pane1, Pos.CENTER_LEFT);
        }
    }
}