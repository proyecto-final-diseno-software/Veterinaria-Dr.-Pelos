/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class View_ssesion extends Ventana{
    private PrincipalContenedor ventana;
    
    public final static VBox contendeorTurnos = new VBox(5);
    public final static VBox contenedorPuestos = new VBox(5);
    
    @Override
    public void mostrar_ventana(Stage primaryStage){
        Pane root = new Pane();
        root.setPrefSize(anchoVentana, altoVentana);
        
        this.ventana = new PrincipalContenedor(primaryStage);
        
        root.getChildren().add(ventana);
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void close() {
        
    }
    
    private class PrincipalContenedor extends Parent{
        
        public PrincipalContenedor(Stage primaryStage){
            
            Rectangle bg = new Rectangle(anchoVentana, altoVentana);
            bg.setFill(Color.rgb(253, 255, 255));
            
            DropShadow drop = new DropShadow (100, Color.WHITE);
            drop.setInput(new Glow());
            
            CuadroColaMensaje cuadroPruebaInferior = new CuadroColaMensaje("Veterinaria Dr.Pelos.", 1140, 50, Pos.CENTER_LEFT, Color.rgb(88, 180, 228), Color.WHITE);
            
            referencia();
            
            getChildren().addAll(bg, cuadroPruebaInferior);
            
        }
    }
    
    private class CuadroColaMensaje extends StackPane{
        private Text text;
        
        public CuadroColaMensaje(String nombre, int ancho,int altura, Pos alineacion, Color colorFondo, Color Letras){
            text = new Text(nombre);
            text.setFont(text.getFont().font("Arial" ,FontWeight.BOLD, 35));
            text.setFill(Letras);
            
            Rectangle bg = new Rectangle(ancho, altura);
            bg.setOpacity(0.8);
            bg.setFill(colorFondo);
            bg.setArcWidth(10);
            bg.setArcHeight(10);
            
            setAlignment(alineacion);
            
            bg.setEffect(new GaussianBlur(1));
            
            getChildren().addAll(bg,text);
        }
    }
    
    private void referencia(){
        CuadroColaMensaje cuadroPruebaPuesto = new CuadroColaMensaje("PUESTO", 250, 50, Pos.CENTER, Color.rgb(100, 135, 170), Color.WHITE);
        contenedorPuestos.getChildren().add(cuadroPruebaPuesto);
        CuadroColaMensaje cuadroPrueba = new CuadroColaMensaje("TURNO", 250, 50, Pos.CENTER, Color.rgb(104, 183, 229), Color.WHITE);
        contendeorTurnos.getChildren().add(cuadroPrueba);
    }
}
