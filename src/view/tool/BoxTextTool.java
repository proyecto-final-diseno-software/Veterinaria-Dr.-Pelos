/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author ADMIN
 */
public class BoxTextTool extends StackPane implements Tool{
    private Text text;
        
    public BoxTextTool(String nombre, int ancho, int altura, Color colorFondo, Color colorBordes, Color colorLetras,int tamanoLetras, FontWeight tipo, Pos pos){
        text = new Text(nombre);
        text.setFont(text.getFont().font("Arial" , tipo, tamanoLetras));
        text.setFill(colorLetras);
            
        
        Rectangle bg = new Rectangle(ancho, altura);
        bg.setOpacity(0.8);
        bg.setFill(colorFondo);

        if(colorBordes != null){
            bg.setStroke(colorBordes);
            bg.setStrokeWidth(0.5);
        }
                
        bg.setEffect(new GaussianBlur(1));
            
        setAlignment(pos);
            
        getChildren().addAll(bg, text);
    }
    
    public BoxTextTool(String nombre, Color colorLetras,int tamanoLetras, FontWeight tipo){
        text = new Text(nombre);
        text.setFont(text.getFont().font("Arial" , tipo, tamanoLetras));
        text.setFill(colorLetras);
            
        getChildren().add(text);
    }
    
    public void setText(String newText){
        this.text.setText(newText);
    }

    @Override
    public boolean isEmplyTool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiarTool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
