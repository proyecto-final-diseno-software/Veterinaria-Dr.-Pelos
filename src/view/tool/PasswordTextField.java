/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ADMIN
 */
public class PasswordTextField extends StackPane{
    private PasswordField text;
    private Rectangle cuadroFondo;
    
    public PasswordTextField(int size, Pos pos, int largo, Color colorCuadro){
        text = new PasswordField();
        text.setFont(text.getFont().font(size));
        
        if(colorCuadro!=null){
            cuadroFondo =  new Rectangle(largo, 50);
            cuadroFondo.setFill(colorCuadro);
            cuadroFondo.setOpacity(0);
            getChildren().add(cuadroFondo);
        }
        
        setAlignment(pos);
        getChildren().add(text);
    }
    
    public boolean isEmpty() {
        if(text.getText().isEmpty()){
            //cuadroFondo.setOpacity(0.2);
            return false;
        }
        //cuadroFondo.setOpacity(0);
        return true;
    }
    
    public String getInformacion() {
        return text.getText();
    }
    
    public void limpiar(){
        text.clear();
    }
}
