/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author ADMIN
 */
public class Boton extends StackPane{
    private Text text;
        
        public Boton(String texto, int tamanoLetra, int base,int altura, Pos alineacion, Color color){
            text = new Text(texto);
            text.setFont(text.getFont().font("Arial" ,FontWeight.BOLD, tamanoLetra));
            text.setFill(Color.WHITE);
            
            Rectangle bg = new Rectangle(base, altura);
            bg.setOpacity(0.8);
            bg.setFill(color);
            bg.setEffect(new GaussianBlur(1));
            bg.setArcWidth(10);
            bg.setArcHeight(10);
            
            setAlignment(alineacion);
            getChildren().addAll(bg,text);
            
            DropShadow drop = new DropShadow (50, Color.WHITE);
            drop.setInput(new Glow()); 
            
            setOnMouseEntered(event -> bg.setOpacity(1));
            
            setOnMouseExited(event -> bg.setOpacity(0.8));
            
            setOnMousePressed(event -> setEffect(drop));
            
            setOnMouseReleased(event -> setEffect(null));
        }
        
        public Boton(String texto, int tamanoLetra, int base, int altura, Pos alineacion){
            text = new Text(texto);
            text.setFont(text.getFont().font("Arial" , tamanoLetra));
            text.setFill(Color.rgb(255, 255, 255));
            text.setTranslateX(20);
            
            
            
            Rectangle bg = new Rectangle(base, altura);
            bg.setOpacity(0);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(1));
            bg.setArcWidth(5);
            bg.setArcHeight(5);
            
            setAlignment(alineacion);
            getChildren().addAll(bg, text);
            
            DropShadow drop = new DropShadow (50, Color.WHITE);
            drop.setInput(new Glow()); 
            
            setOnMouseEntered(event -> bg.setOpacity(0.2));
            
            setOnMouseExited(event -> bg.setOpacity(0));
            
            setOnMousePressed(event -> setEffect(drop));
            
            setOnMouseReleased(event -> setEffect(null));
        }
        
    public void setText(String text){
        this.text.setText(text);
    }
}
