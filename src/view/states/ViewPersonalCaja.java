/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import controladores.CtrPersonalCaja;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.PersonalCaja;
import modelo.Sucursal;
import view.contenido.Contenido;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.Tool;
import view.contenido.ContenidoAnadirUsuario;
import view.contenido.ContenidoConstruccion;
import view.contenido.ContenidoConsultaEntregas;
import view.contenido.ContenidoPerfil;
import view.contenido.ContenidoTraslado;
import view.contenido.ContenidoVentas;

/**
 *
 * @author paula
 */
public class ViewPersonalCaja extends Ventana{
    private PrincipalContenedorCaja ventana;
    
    private Sucursal sucursal;
    
    private PersonalCaja personalCaja;
    
    private CtrPersonalCaja contolCaja;

    public ViewPersonalCaja(String user) {
        this.contolCaja = new CtrPersonalCaja();
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
        private int anchoLateral;
        private int altoSuperior;
        
        private int reduccionX;
        private int reduccionY;
        
        private List<Contenido> contenidos;
        
        private ContenidoAnadirUsuario ventanaAnadirUsuario;
        private ContenidoPerfil ventanaPerfil;
        private ContenidoVentas contenVentas;
        private ContenidoTraslado ventanaTraslado;
        private ContenidoConsultaEntregas ventanaConsultaEntrega;
        private ContenidoConstruccion ventaEnConstratuccion;
        
        public PrincipalContenedorCaja(){
            menuLateral = new VBox();
            menuLateral.setAlignment(Pos.CENTER_LEFT);
            
            paneCentral = new HBox(20);
            paneFondo = new Pane();
            
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
                ocultarContenidos();
                ventanaAnadirUsuario.setVisible(true);
                comportamientoMenuLateral(botonCrearClientes);
            });
            
            BotonTool botonConsultarClientes = new BotonTool(" - Consultar cliente", titulo2 - 5, anchoLateral, altoBotones - 20, false);
            botonConsultarClientes.setOnMousePressed(seccionClientes -> {
                cambiarEnConstraccion();
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
                cambiarEnConstraccion();
                comportamientoMenuLateral(botonInventario);
            });
            
            botonesLateral.add(botonInventario);
            
            BotonTool botonProveedores = new BotonTool("Proveedores", titulo2, anchoLateral, altoBotones, false);
            botonProveedores.setOnMousePressed(seccionClientes -> {
                cambiarEnConstraccion();
                comportamientoMenuLateral(botonProveedores);
            });
            
            botonesLateral.add(botonProveedores);
            
            BotonTool botonReportes = new BotonTool("Reportes", titulo2, anchoLateral, altoBotones, false);
            botonReportes.setOnMousePressed(seccionClientes -> {
                cambiarEnConstraccion();
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
                    cambiarContenidoTraslado();
                });
                botonesLateral.add(botonTrasladoMascota);

                BotonTool botonTransladoMercaderia = new BotonTool(" - Consultas entregas", titulo2 - 5, anchoLateral, altoBotones - 20, false);
                botonTransladoMercaderia.setOnMousePressed(trasladoMercaderia -> {
                    comportamientoMenuLateral(botonTransladoMercaderia);
                    cambiarConsultantregas();
                });
                botonesLateral.add(botonTransladoMercaderia);

                botonTraslado.setOnMousePressed(seccionClientes -> {
                    cambiarEnConstraccion();
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
                cambiarEnConstraccion();
                comportamientoMenuLateral(botonFacturacion);
            });
            
            botonesLateral.add(botonFacturacion);
            
            menuLateral.getChildren().addAll(logo, paneCliente, botonInventario, botonProveedores, botonReportes, botonVentas, paneTraslado, botonFacturacion);
            
            paneFondo.setTranslateX(anchoLateral + 15);
            paneFondo.setTranslateY(altoSuperior + 25);
            
            paneFondo.getChildren().add(paneCentral);
            
            getChildren().addAll(barraLateral, barraSuperior , menuLateral, paneFondo);
            
            this.contenidos = new ArrayList<>();
            
            establecerContenidoAnadirUser();
            establecerPefilCliente();
            establecerContenidoVentas();
            establecerContenidoTraslado("Traslado de mascotas", "Buscar Cliente");
            establecerConsultantregas();
            establecerEnConstracion();
        }
        
        private void establecerContenidoAnadirUser(){
            ventanaAnadirUsuario = new ContenidoAnadirUsuario(reduccionX, 0, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior);
            
            List<Tool> toolUsados = new ArrayList<>();
            
            Pane mensajeError = new Pane();            
            HBox botonesPane = new HBox(5);
            
            BotonTool guardarCliente = new BotonTool("Guardar Cliente", titulo2, 200, titulo2 * 2, ColorOscuro);
            guardarCliente.setOnMousePressed(guardarNuevoCliente -> {
                mensajeError.getChildren().clear();
                if(comprobarCampos(toolUsados)){
                    Cliente nuevoCliente = contolCaja.createCliente(toolUsados);
                    if(contolCaja.addClienteDataBase(nuevoCliente)){
                        ventanaAnadirUsuario.limpiarVentana();
                        cambiarPefilCliente(nuevoCliente);
                    } else
                        mensajeError.getChildren().add(new BoxTextTool("Hubo un error al crear el usuario\nintento mas tarde.", Color.RED, titulo3, FontWeight.BOLD));
                } else
                    mensajeError.getChildren().add(new BoxTextTool("Por favor ingrese los datos faltantes.", Color.RED, titulo3, FontWeight.BOLD));
            });
            BotonTool cancelarCliente = new BotonTool("Cancelar", titulo2, 200, titulo2 * 2, colorClaro);
            cancelarCliente.setOnMousePressed(cancelarNuevoCliente -> ventanaAnadirUsuario.limpiarVentana());
            
            botonesPane.getChildren().addAll(cancelarCliente, guardarCliente);
            
            ventanaAnadirUsuario.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventanaAnadirUsuario.contenidoAdicional((anchoVentana - reduccionX) / 2 - 25);
            ventanaAnadirUsuario.crearContenidoCentral(toolUsados);
            ventanaAnadirUsuario.anadirSellecionInferior(botonesPane);
            ventanaAnadirUsuario.anadirSellecionInferior(mensajeError);
            
            paneFondo.getChildren().add(ventanaAnadirUsuario);
            
            ventanaAnadirUsuario.setVisible(false);
            
            this.contenidos.add(ventanaAnadirUsuario);
        }
        
        private void establecerContenidoVentas(){
            contenVentas = new ContenidoVentas(reduccionX, reduccionY, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior, personalCaja);
            contenVentas.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            contenVentas.establecerPaneles();
            contenVentas.crearContenidoCentral(new ArrayList<>());
            
            paneFondo.getChildren().add(contenVentas);
            contenVentas.setVisible(false);
            this.contenidos.add(contenVentas);
        }
        
        private void cambiarContenidoVentas(){   
            ocultarContenidos();
            contenVentas.setVisible(true);
        }
        
        private void establecerContenidoTraslado(String tipo, String cabeceraField){
            
            ventanaTraslado = new ContenidoTraslado(reduccionX, 0, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior);
            ventanaTraslado.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventanaTraslado.establecerDatosAdicionales(tipo, cabeceraField);
            ventanaTraslado.crearContenidoCentral(null);
            
            paneFondo.getChildren().add(ventanaTraslado);
            ventanaTraslado.setVisible(false);
            this.contenidos.add(ventanaTraslado);
        }
        
        private void cambiarContenidoTraslado(){
            ocultarContenidos();
            ventanaTraslado.setVisible(true);
        }
        
        private void establecerConsultantregas(){
            ventanaConsultaEntrega = new ContenidoConsultaEntregas(reduccionX, reduccionY, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior);
            ventanaConsultaEntrega.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventanaConsultaEntrega.crearContenidoCentral(new ArrayList<>());
           
            paneFondo.getChildren().add(ventanaConsultaEntrega);
            ventanaConsultaEntrega.setVisible(false);
            this.contenidos.add(ventanaConsultaEntrega);
        }
        
        private void establecerEnConstracion(){
            ventaEnConstratuccion = new ContenidoConstruccion(reduccionX, reduccionY, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior);
            ventaEnConstratuccion.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventaEnConstratuccion.crearContenidoCentral(new ArrayList<>());
           
            paneFondo.getChildren().add(ventaEnConstratuccion);
            ventaEnConstratuccion.setVisible(false);
            this.contenidos.add(ventaEnConstratuccion);
        }
        
        private void cambiarEnConstraccion(){   
            ocultarContenidos();
            ventaEnConstratuccion.setVisible(true);
        }
        
        private void cambiarConsultantregas(){
            ocultarContenidos();
            ventanaConsultaEntrega.setVisible(true);
        }
        
        private void cambiarPefilCliente(Cliente cliente){
            ocultarContenidos();
            ventanaPerfil.setInformacionUser(cliente);
            ventanaPerfil.setVisible(true);
        }
        
        private void establecerPefilCliente(){
            ventanaPerfil = new ContenidoPerfil(reduccionX, 0, anchoVentana, altoVentana, 0, 0, anchoLateral, altoSuperior);
            ventanaPerfil.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventanaPerfil.crearContenidoCentral(null);
            
            paneFondo.getChildren().add(ventanaPerfil);
            ventanaPerfil.setVisible(false);
            this.contenidos.add(ventanaPerfil);
        }
        
        private void ocultarContenidos(){
            Iterator<Contenido> it = this.contenidos.iterator();
            while(it.hasNext()){
               it.next().setVisible(false);
            }
        }
    }
}