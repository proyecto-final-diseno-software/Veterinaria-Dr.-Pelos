/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ADMIN
 */
public class ComboxPersonal extends StackPane{
    private final ComboBox informacion;
    private final Rectangle bg;
    
    public ComboxPersonal(int largo, ComboBox cajita){
        informacion = cajita;
        
        bg =  new Rectangle(largo, 50);
        bg.setFill(Color.RED);
        bg.setOpacity(0);
        
        setAlignment(Pos.CENTER_RIGHT);
        
        getChildren().addAll(bg, informacion);
    }
    
    public boolean isEmpltyCom() {
        if(informacion.getValue()==null){
            bg.setOpacity(0.2);
            return false;
        }
        bg.setOpacity(0);
        return true;
    }
    
    public Object getInformacionCom(){
        return informacion.getValue();
    }
}
