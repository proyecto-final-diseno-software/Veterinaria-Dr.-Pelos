/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.tool.Boton;

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
        
        public PrincipalContenedorCaja(){
            menuLateral = new VBox();
            menuLateral.setAlignment(Pos.CENTER_LEFT);
            
            int anchoLateral = anchoVentana / 5;
            
            logo = new ImageView(new Image("util/logo2.png"));
            logo.setFitHeight(altoVentana / 10);
            logo.setFitWidth(anchoLateral);
            
            Rectangle fondo = new Rectangle(anchoVentana, altoVentana);
            fondo.setFill(Color.rgb(250, 250, 250));
            
            Rectangle barraSuperior = new Rectangle(anchoVentana, altoVentana / 10);
            barraSuperior.setFill(ColorOscuro);
            barraSuperior.setOpacity(0.7);
            
            Rectangle barraLateral = new Rectangle(anchoLateral, altoVentana);
            barraLateral.setFill(colorClaro);
            barraLateral.setOpacity(0.5);
            
            int altoBotones = 45;
            
            Boton botonAlamcen = new Boton("Almacen", 25, anchoLateral, altoBotones, Pos.CENTER_LEFT);
            Boton botonCompras = new Boton("Compras", 25, anchoLateral, altoBotones, Pos.CENTER_LEFT);
            Boton botonVentas = new Boton("Ventas", 25, anchoLateral, altoBotones, Pos.CENTER_LEFT);
            
            menuLateral.getChildren().addAll(logo, botonAlamcen, botonCompras, botonVentas);
            
            getChildren().addAll(fondo, barraLateral, barraSuperior , menuLateral);
        }
    }
}
