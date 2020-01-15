/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ADMIN
 */
public class FieldText extends StackPane{
    private TextField text;
    
    public FieldText(String textFon,int size, Pos pos, int largo, int alto){
        text = new TextField();
        text.setFont(text.getFont().font(size));
        text.setPromptText(textFon);
        text.setPrefHeight(alto);
        text.setPrefWidth(largo);
        
        setAlignment(pos);
        
        getChildren().add(text);
    }
    
    public boolean validacionInt() {
        try {
            Integer.parseInt(text.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public boolean isEmpty() {
        if(text.getText().isEmpty()){
            //cuadroFondo.setOpacity(0.2);
            return false;
        }
        //cuadroFondo.setOpacity(0);
        return true;
    }
    
    public String getText() {
        return text.getText();
    }
    
    public void limpiar(){
        text.clear();
    }
}
