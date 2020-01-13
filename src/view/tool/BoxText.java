/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author ADMIN
 */
public class BoxText extends StackPane{
    private Text text;
    
    public BoxText(String text, int tamanoLetras, Color letras, FontWeight textura, Pos alineacion, int ancho, int altura, Color colorCuadro){
            this.text = new Text(text);
            this.text.setFont(this.text.getFont().font("Arial" , textura, tamanoLetras));
            this.text.setFill(letras);
            
            if(colorCuadro!=null){
                Rectangle cuadro = new Rectangle(ancho, altura);
                cuadro.setFill(colorCuadro);
                getChildren().add(cuadro);
            }
            
            setAlignment(alineacion);
            
            getChildren().add(this.text);
        }
    public void CambiarMensaje(String nombre){
        this.text.setText(nombre);
    }
}
