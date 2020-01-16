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
        private final Color colorClaro = Color.rgb(40, 171, 223);
        private final Color colorOscuro = Color.rgb(25, 141, 216);
        
        private ImageView logo;
        
        private VBox menuLateral;
        
        private HBox paneCentral;
        
        private VBox colunma1;
        private VBox colunma2;
        
        private FlowPane pane1;
        private FlowPane pane2;
        private FlowPane pane3;
        private FlowPane pane4;
        
        private List<BotonTool> allBoton = new ArrayList<>();
        
        private int titulo1;
        private int titulo2;
        private int titulo3;
        
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
            
            this.titulo1 = 30;
            this.titulo2 = 20;
            this.titulo3 = 15;
            
            int anchoLateral = anchoVentana / 7;
            int altoSuperior = altoVentana / 10;
            
            logo = new ImageView(new Image("util/logo2.png"));
            logo.setFitHeight(altoVentana / 10);
            logo.setFitWidth(anchoLateral);
            
            Rectangle barraSuperior = new Rectangle(anchoVentana, altoSuperior);
            barraSuperior.setFill(colorOscuro);
            barraSuperior.setOpacity(0.7);
            
            Rectangle barraLateral = new Rectangle(anchoLateral, altoVentana);
            barraLateral.setFill(colorClaro);
            barraLateral.setOpacity(0.5);
            
            int altoBotones = 55;
            
            VBox paneCliente = new VBox();
            
            BotonTool botonClientes = new BotonTool("Clientes", titulo2, anchoLateral, altoBotones, true);
            
            BotonTool botonCrearClientes = new BotonTool(" - Añadir cliente", titulo2 - 5, anchoLateral, altoBotones - 20, false);
            botonCrearClientes.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonCrearClientes);
                cambiarContenidoAnadirUser(anchoLateral + 50);
            });
            
            BotonTool botonConsultarClientes = new BotonTool(" - Consultar cliente", titulo2 - 5, anchoLateral, altoBotones - 20, false);
            botonConsultarClientes.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonConsultarClientes);
            });
            botonClientes.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonClientes);
                if(paneCliente.getChildren().contains(botonCrearClientes)){
                    paneCliente.getChildren().removeAll(botonCrearClientes, botonConsultarClientes);
                }
                else{
                    paneCliente.getChildren().addAll(botonCrearClientes, botonConsultarClientes);
                }
            });
            
            paneCliente.getChildren().add(botonClientes);
            
            allBoton.add(botonClientes);
            allBoton.add(botonCrearClientes);
            allBoton.add(botonConsultarClientes);
            
            BotonTool botonInventario = new BotonTool("Inventario", titulo2, anchoLateral, altoBotones, false);
            botonInventario.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonInventario);
            });
            
            allBoton.add(botonInventario);
            
            BotonTool botonProveedores = new BotonTool("Proveedores", titulo2, anchoLateral, altoBotones, false);
            botonProveedores.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonProveedores);
            });
            
            allBoton.add(botonProveedores);
            
            BotonTool botonReportes = new BotonTool("Reportes", titulo2, anchoLateral, altoBotones, false);
            botonReportes.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonReportes);
            });
            
            allBoton.add(botonReportes);
            
            BotonTool botonVentas = new BotonTool("Ventas", titulo2, anchoLateral, altoBotones, false);
            botonVentas.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonVentas);
                cambiarContenidoVentas(anchoLateral + 50);
            });
            
            allBoton.add(botonVentas);
            
            BotonTool botonEmpleados = new BotonTool("Empleados", titulo2, anchoLateral, altoBotones, false);
            botonEmpleados.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonEmpleados);
            });
            
            allBoton.add(botonEmpleados);
            
            BotonTool botonFacturacion = new BotonTool("Facturacion", titulo2, anchoLateral, altoBotones, false);
            botonFacturacion.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonFacturacion);
            });
            
            allBoton.add(botonFacturacion);
            
            menuLateral.getChildren().addAll(logo, paneCliente, botonInventario, botonProveedores, botonReportes, botonVentas, botonEmpleados, botonFacturacion);
            
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
            limpiarVentana();
            
            colunma1.getChildren().add(pane1);
            
            paneCentral.getChildren().addAll(colunma1);
            
            pane1.setPrefWrapLength(anchoVentana - reduccionx);
            
            generarsecciones(pane1, Pos.CENTER_LEFT);
            
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
            
            HBox segundo = new HBox(20);
            TextFieldTool textFieldDireccion = new TextFieldTool("Ingrese direccion del nuevo cliente", "Direccion:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            TextFieldTool textFieldtelefono = new TextFieldTool("Ingrese el telefono del nuevo cliente", "Telefono:", titulo2, Pos.CENTER_LEFT, mitadVentanaActiva, titulo2);
            segundo.getChildren().addAll(textFieldDireccion, textFieldtelefono);
            
            HBox botones = new HBox(5);
            BotonTool guardarCliente = new BotonTool("Guardar Cliente", titulo2, 200, titulo2 * 2, colorOscuro);
            guardarCliente.setOnMousePressed(guardarNuevoCliente -> {
                System.out.println("Se guardara nuevo Cliente");
            });
            BotonTool cancelarCliente = new BotonTool("Cancelar", titulo2, 200, titulo2 * 2, colorClaro);
            cancelarCliente.setOnMousePressed(cancelarNuevoCliente -> {
                limpiarVentana();
            });
            botones.getChildren().addAll(cancelarCliente, guardarCliente);
            
            paneIngresoDatos.getChildren().addAll(cabecera, primero, segundo, botones);
            pane1.getChildren().add(paneIngresoDatos);
        }
        
        private void cambiarContenidoVentas(int reduccionx){
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
            BotonTool botonBuscar = new BotonTool("Buscar", titulo2, 130, 44, colorOscuro);
            botonBuscar.setOnMousePressed(buscarArticulo -> {
                System.out.println("Buscando " + textFieldBuscador.getText());
            });
            
            HBox ssecionBuscadorCliente = new HBox();
            
            TextFieldTool textFieldBuscadorCliente = new TextFieldTool("Buscar Cliente" ,titulo2, Pos.CENTER, 3 * colunma2 / 6, 40);
            BotonTool botonBuscarCliente = new BotonTool("Buscar", titulo2 - 1, 90, 40, colorOscuro);
            botonBuscar.setOnMousePressed(buscarCliente -> {
                System.out.println("Buscando Cliente" + textFieldBuscadorCliente.getText());
            });
            
            TableTool tablaRegistro = new TableTool(colunma1);
            
            ssecionBuscador.getChildren().addAll(textFieldBuscador, botonBuscar);
            
            ssecionBuscadorCliente.getChildren().addAll(textFieldBuscadorCliente, botonBuscarCliente);
            
            TableTool tablaPago = new TableTool(colunma2, null);
            
            pane1.getChildren().add(ssecionBuscador);
            pane2.getChildren().add(tablaRegistro);
            pane3.getChildren().add(ssecionBuscadorCliente);
            pane4.getChildren().add(tablaPago);
        }
        
        private void alldesmarcar(){
            ListIterator<BotonTool> it = allBoton.listIterator();
            
            while(it.hasNext()){
                BotonTool temp = it.next();
                temp.desmarcar();
            }
        }
        
        private void comportamientoBoton(BotonTool boton){
            alldesmarcar();
                if(!boton.isPresionado())
                    boton.marcar();
        }
    }
}
