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
public class BotonPersonalizado extends StackPane{
    private Text text;
        
        public BotonPersonalizado(String texto, int tamanoLetra, int base,int altura, Pos alineacion, Color color){
            text = new Text(texto);
            text.setFont(text.getFont().font("Arial" ,FontWeight.BOLD, tamanoLetra));
            text.setFill(Color.WHITE);
            
            Rectangle bg = new Rectangle(base, altura);
            bg.setOpacity(0.8);
            bg.setFill(color);
            bg.setEffect(new GaussianBlur(1));
            bg.setArcWidth(30);
            bg.setArcHeight(30);
            
            setAlignment(alineacion);
            getChildren().addAll(bg,text);
            
            DropShadow drop = new DropShadow (50, Color.WHITE);
            drop.setInput(new Glow()); 
            
            setOnMouseEntered(event -> bg.setOpacity(1));
            
            setOnMouseExited(event -> bg.setOpacity(0.8));
            
            setOnMousePressed(event -> setEffect(drop));
            
            setOnMouseReleased(event -> setEffect(null));
        }
        
        public BotonPersonalizado(String texto, int tamanoLetra,Pos alineacion, FontWeight textura){
            text = new Text(texto);
            text.setFont(text.getFont().font("Arial" ,textura, tamanoLetra));
            text.setFill(Color.rgb(255, 255, 255));
            
            setAlignment(alineacion);
            getChildren().addAll(text);
            
            DropShadow drop = new DropShadow (50, Color.WHITE);
            drop.setInput(new Glow()); 
            
            setOnMouseEntered(event -> text.setFill(Color.rgb(169, 177, 184)));
            
            setOnMouseExited(event -> text.setFill(Color.rgb(255, 255, 255)));
            
            setOnMousePressed(event -> setEffect(drop));
            
            setOnMouseReleased(event -> setEffect(null));
        }
        
    public void setText(String text){
        this.text.setText(text);
    }
}
