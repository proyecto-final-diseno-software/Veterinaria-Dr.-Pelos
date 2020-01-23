/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import controladores.Ctr_Personal_Caja;
import java.util.ArrayList;
import java.util.List;
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
import modelo.Personal_Caja;
import modelo.Sucursal;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.Tool;
import view.contenido.ContenidoAnadirUsuario;
import view.contenido.ContenidoBusqueda;
import view.contenido.ContenidoPerfil;
import view.contenido.ContenidoTraslado;
import view.contenido.ContenidoVentas;

/**
 *
 * @author paula
 */
public class View_PersonalCaja extends Ventana{
    private PrincipalContenedorCaja ventana;
    
    private Sucursal sucursal;
    private Personal_Caja personalCaja;
    
    private Ctr_Personal_Caja contolCaja;

    public View_PersonalCaja(String user) {
        this.contolCaja = new Ctr_Personal_Caja();
        this.personalCaja = contolCaja.selectPersonalCaja(user);
        this.sucursal = personalCaja.getSucursal();
    }

    @Override
    void mostrar_ventana(Stage primaryStage) {}

    @Override
    void close() {}

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
        
        private int anchoLateral;
        private int altoSuperior;
        
        private int reduccionX;
         private int reduccionY;
        
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
            
            reduccionX = anchoLateral + 50;
            reduccionY = altoSuperior + 50;
            
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
                cambiarContenidoAnadirUser();
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
                cambiarContenidoVentas();
            });
            
            botonesLateral.add(botonVentas);
            
            VBox paneTraslado = new VBox();
            
            if(sucursal.isOfreceServicios()){
                BotonTool botonTraslado = new BotonTool("Traslado", titulo2, anchoLateral, altoBotones, true);

                BotonTool botonTrasladoMascota = new BotonTool(" - Mascotas", titulo2 - 5, anchoLateral, altoBotones - 20, false);
                botonTrasladoMascota.setOnMousePressed(trasladoMascotas -> {
                    comportamientoMenuLateral(botonTrasladoMascota);
                    cambiarContenidoTraslado("Traslado de mascotas", "Buscar Cliente");
                });
                botonesLateral.add(botonTrasladoMascota);

                BotonTool botonTransladoMercaderia = new BotonTool(" - Consultas entregas", titulo2 - 5, anchoLateral, altoBotones - 20, false);
                botonTransladoMercaderia.setOnMousePressed(trasladoMercaderia -> {
                    comportamientoMenuLateral(botonTransladoMercaderia);
                    cambiarConsultantregas();
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
            }
            
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
        
        private void cambiarContenidoAnadirUser(){
            establecerFondoUnico(Pos.CENTER_LEFT);
            
            List<Tool> toolUsados = new ArrayList<>();
            
            Pane mensajeError = new Pane();
            
            HBox botonesPane = new HBox(5);
            BotonTool guardarCliente = new BotonTool("Guardar Cliente", titulo2, 200, titulo2 * 2, ColorOscuro);
            guardarCliente.setOnMousePressed(guardarNuevoCliente -> {
                mensajeError.getChildren().clear();
                if(comprobarCampos(toolUsados)){
                    Cliente nuevoCliente = contolCaja.createCliente(toolUsados);
                    if(contolCaja.addClienteDataBase(nuevoCliente))
                        cambiarPefilCliente(nuevoCliente);
                    else
                        mensajeError.getChildren().add(new BoxTextTool("Hubo un error al crear el usuario\nintento mas tarde.", Color.RED, titulo3, FontWeight.BOLD));
                } else
                    mensajeError.getChildren().add(new BoxTextTool("Por favor ingrese los datos faltantes.", Color.RED, titulo3, FontWeight.BOLD));
            });
            BotonTool cancelarCliente = new BotonTool("Cancelar", titulo2, 200, titulo2 * 2, colorClaro);
            cancelarCliente.setOnMousePressed(cancelarNuevoCliente -> limpiarVentana());
            
            botonesPane.getChildren().addAll(cancelarCliente, guardarCliente);
            
            ContenidoAnadirUsuario ventanaAnadirUsuario = new ContenidoAnadirUsuario(reduccionX, 0, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior);
            ventanaAnadirUsuario.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventanaAnadirUsuario.establecerPaneles(pane1, pane2, pane3, pane4, paneFondo);
            ventanaAnadirUsuario.contenidoAdicional((anchoVentana - reduccionX) / 2 - 25);
            ventanaAnadirUsuario.crearContenidoCentral(toolUsados);
            ventanaAnadirUsuario.anadirSellecionInferior(botonesPane);
            ventanaAnadirUsuario.anadirSellecionInferior(mensajeError);
        }
        
        private void cambiarContenidoVentas(){
            limpiarVentana();
            
            colunma1.getChildren().addAll(pane1, pane2);
            colunma2.getChildren().addAll(pane3, pane4);
            
            int anchoColunma1 = 2 * (anchoVentana - reduccionX) / 3;
            int anchoColunma2 = (anchoVentana - reduccionX) / 3;
            
            pane1.setPrefWrapLength(anchoColunma1);
            pane2.setPrefWrapLength(anchoColunma1);
            pane3.setPrefWrapLength(anchoColunma2);
            pane4.setPrefWrapLength(anchoColunma2);
            
            generarsecciones(pane1, Pos.CENTER);
            generarsecciones(pane2, Pos.CENTER);
            generarsecciones(pane3, Pos.CENTER);
            generarsecciones(pane4, Pos.CENTER);
            
            ContenidoVentas contenVentas = new ContenidoVentas(reduccionX, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior, personalCaja);
            contenVentas.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            contenVentas.establecerPaneles(pane1, pane2, pane3, pane4, paneFondo);
            contenVentas.crearContenidoCentral(new ArrayList<>());
            
            paneCentral.getChildren().addAll(colunma1, colunma2);
        }
        
        private void cambiarContenidoTraslado(String tipo, String cabeceraField ){
            establecerFondoUnico(Pos.CENTER_LEFT);
            
            ContenidoTraslado ventanaTraslado= new ContenidoTraslado(reduccionX, 0, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior);
            ventanaTraslado.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventanaTraslado.establecerDatosAdicionales(tipo, cabeceraField);
            ventanaTraslado.establecerPaneles(pane1, null, null, null, null);
            ventanaTraslado.crearContenidoCentral(null);
        }
        
        private void cambiarConsultantregas(){
            establecerFondoUnico(Pos.TOP_LEFT);
            
            ContenidoBusqueda ventanaConsultaEntrega = new ContenidoBusqueda(anchoVentana - reduccionX - 40, altoVentana - reduccionY - 10, titulo1, titulo2);
            Pane pane = new Pane();
            pane.getChildren().add(ventanaConsultaEntrega);
            pane.setTranslateX(20);
            pane1.getChildren().add(pane);
        }
        
        private void cambiarPefilCliente(Cliente cliente){
            establecerFondoUnico(Pos.TOP_LEFT);
            
            ContenidoPerfil ventanaTraslado= new ContenidoPerfil(reduccionX, 0, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior, cliente);
            ventanaTraslado.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventanaTraslado.establecerPaneles(pane1, null, null, null, null);
            ventanaTraslado.crearContenidoCentral(null);
        }
        
        private void establecerFondoUnico(Pos pos){
            limpiarVentana();
            colunma1.getChildren().add(pane1);
            paneCentral.getChildren().addAll(colunma1);
            pane1.setPrefWrapLength(anchoVentana - reduccionX);
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