/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.tool.Boton;
import view.tool.FieldText;

/**
 *
 * @author ADMIN
 */
public class View_ssesion extends Ventana{
    private PrincipalContenedor ventana;
    
    @Override
    public void mostrar_ventana(Stage primaryStage){
        this.root = new Pane();
        this.root.setPrefSize(anchoVentana, altoVentana);
        
        this.ventana = new PrincipalContenedor();
        
        this.root.getChildren().add(ventana);
        Scene scene = new Scene(this.root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void close() {
        
    }

    @Override
    void cambiar_ventana(Pane newContenido) {
        
    }
    
    private class PrincipalContenedor extends Parent{
        private final Color colorClaro = Color.rgb(40, 171, 223);
        private final Color ColorOscuro = Color.rgb(25, 141, 216);
        
        private ImageView logo;
        
        private VBox contenedor;
        
        public PrincipalContenedor(){
            contenedor = new VBox(15);
            contenedor.setTranslateX(anchoVentana / 2 - anchoVentana / 8);
            contenedor.setTranslateY(anchoVentana / 2 - anchoVentana / 3);
            contenedor.setAlignment(Pos.CENTER);
            
            logo = new ImageView(new Image("util/logo.png"));
            logo.setFitHeight(116);
            logo.setFitWidth(96);
            
            Rectangle fondo = new Rectangle(anchoVentana, altoVentana);
            fondo.setFill(Color.rgb(250, 250, 250));
            
            Rectangle cuadroCentral = new Rectangle(anchoVentana / 3, altoVentana / 2);
            cuadroCentral.setOpacity(0.3);
            cuadroCentral.setFill(colorClaro);
            cuadroCentral.setTranslateX(anchoVentana / 2 - anchoVentana / 6);
            cuadroCentral.setTranslateY(altoVentana / 2 - altoVentana / 4);
            cuadroCentral.setArcWidth(30);
            cuadroCentral.setArcHeight(30);
            
            //CuadroColaMensaje cuadroPruebaInferior = new CuadroColaMensaje("Veterinaria Dr.Pelos.", 1140, 50, Pos.CENTER_LEFT, Color.rgb(88, 180, 228), Color.WHITE);
            
            Boton buttonLogin = new Boton("Login", 25, anchoVentana / 4, 45, Pos.CENTER, ColorOscuro);
            
            buttonLogin.setOnMousePressed(cambioVentana -> {
                View_PersonalCaja newContent = new View_PersonalCaja();
                newContent.crear_ventana(null, anchoVentana, altoVentana);
                newContent.cambiar_ventana(root);
            });
            
            
            FieldText entradoUser = new FieldText("Ingrese su usuario" ,20 , Pos.CENTER, anchoVentana / 4, null);
            
            FieldText entradoPassword = new FieldText("Ingrese su contraseña", 20 , Pos.CENTER, anchoVentana / 4, null);
            
            contenedor.getChildren().addAll(logo, entradoUser, entradoPassword, buttonLogin);
            
            getChildren().addAll(fondo, cuadroCentral, contenedor);
            
        }
    }
}
