/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
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
import javafx.stage.Stage;
import view.tool.Boton;
import view.tool.FieldText;

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
        private final Color ColorOscuro = Color.rgb(25, 141, 216);
        
        private ImageView logo;
        
        private VBox menuLateral;
        
        private HBox paneCentral;
        
        private VBox colunma1;
        private VBox colunma2;
        
        private FlowPane pane1;
        private FlowPane pane2;
        private FlowPane pane3;
        private FlowPane pane4;
        
        List<Boton> allBoton = new ArrayList<>();
        
        public PrincipalContenedorCaja(){
            pane1 = new FlowPane();
            pane2 = new FlowPane();
            pane3 = new FlowPane();
            pane4 = new FlowPane();
            
            menuLateral = new VBox();
            menuLateral.setAlignment(Pos.CENTER_LEFT);
            
            paneCentral = new HBox(20);
            
            colunma1 = new VBox(15);
            colunma2 = new VBox(15);
            
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
            int tamanoLetras = 20;
            
            VBox paneCliente = new VBox();
            
            Boton botonClientes = new Boton("Clientes", tamanoLetras, anchoLateral, altoBotones, Pos.CENTER_LEFT, true);
            
            Boton botonCrearClientes = new Boton(" - Añadir cliente", tamanoLetras - 5, anchoLateral, altoBotones - 20, Pos.CENTER_LEFT, false);
            botonCrearClientes.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonCrearClientes);
            });
            
            Boton botonConsultarClientes = new Boton(" - Consultar cliente", tamanoLetras - 5, anchoLateral, altoBotones - 20, Pos.CENTER_LEFT, false);
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
            
            Boton botonInventario = new Boton("Inventario", tamanoLetras, anchoLateral, altoBotones, Pos.CENTER_LEFT, false);
            botonInventario.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonInventario);
            });
            
            allBoton.add(botonInventario);
            
            Boton botonProveedores = new Boton("Proveedores", tamanoLetras, anchoLateral, altoBotones, Pos.CENTER_LEFT, false);
            botonProveedores.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonProveedores);
            });
            
            allBoton.add(botonProveedores);
            
            Boton botonReportes = new Boton("Reportes", tamanoLetras, anchoLateral, altoBotones, Pos.CENTER_LEFT, false);
            botonReportes.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonReportes);
            });
            
            allBoton.add(botonReportes);
            
            Boton botonVentas = new Boton("Ventas", tamanoLetras, anchoLateral, altoBotones, Pos.CENTER_LEFT, false);
            botonVentas.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonVentas);
                cambiarContenidoVentas(anchoLateral + 50, tamanoLetras);
            });
            
            allBoton.add(botonVentas);
            
            Boton botonEmpleados = new Boton("Empleados", tamanoLetras, anchoLateral, altoBotones, Pos.CENTER_LEFT, false);
            botonEmpleados.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonEmpleados);
            });
            
            allBoton.add(botonEmpleados);
            
            Boton botonFacturacion = new Boton("Facturacion", tamanoLetras, anchoLateral, altoBotones, Pos.CENTER_LEFT, false);
            botonFacturacion.setOnMousePressed(seccionClientes -> {
                comportamientoBoton(botonFacturacion);
            });
            
            allBoton.add(botonFacturacion);
            
            menuLateral.getChildren().addAll(logo, paneCliente, botonInventario, botonProveedores, botonReportes, botonVentas, botonEmpleados, botonFacturacion);
            
            colunma1.getChildren().addAll(pane1, pane2);
            colunma2.getChildren().addAll(pane3, pane4);
            
            paneCentral.getChildren().addAll(colunma1, colunma2);
            paneCentral.setTranslateX(anchoLateral + 15);
            paneCentral.setTranslateY(altoSuperior + 25);
            
            getChildren().addAll(barraLateral, barraSuperior , menuLateral, paneCentral);
        }
        
        private void generarsecciones(FlowPane pane){
            pane.setAlignment(Pos.CENTER);
            pane.getChildren().clear();
            pane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-10, 0, -10, 0))));
        }
        
        private void cambiarContenidoVentas(int reduccionx, int tamanoLetras){
            int colunma1 = 2 * (anchoVentana - reduccionx) / 3;
            int colunma2 = (anchoVentana - reduccionx) / 3;
            
            int alto = 300;
            
            pane1.setPrefWrapLength(colunma1);
            pane2.setPrefWrapLength(colunma1);
            pane3.setPrefWrapLength(colunma2);
            pane4.setPrefWrapLength(colunma2);
            
            generarsecciones(pane1);
            generarsecciones(pane2);
            generarsecciones(pane3);
            generarsecciones(pane4);
            
            HBox ssecionBuscador = new HBox();
            
            FieldText textFieldBuscador = new FieldText("Buscar Producto" ,tamanoLetras, Pos.CENTER, 3 * colunma1 / 5, Color.WHITE);
            Boton botonBuscar = new Boton("Buscar", tamanoLetras, 130, 44, Pos.CENTER, ColorOscuro);
            botonBuscar.setOnMousePressed(buscarArticulo -> {
                System.out.println("Buscando " + textFieldBuscador.getText());
            });
            
            ssecionBuscador.getChildren().addAll(textFieldBuscador, botonBuscar);
            pane1.getChildren().addAll(ssecionBuscador);
        }
        
        private void alldesmarcar(){
            ListIterator<Boton> it = allBoton.listIterator();
            
            while(it.hasNext()){
                Boton temp = it.next();
                temp.desmarcar();
            }
        }
        
        private void comportamientoBoton(Boton boton){
            alldesmarcar();
                if(!boton.isPresionado())
                    boton.marcar();
        }
    }
}
