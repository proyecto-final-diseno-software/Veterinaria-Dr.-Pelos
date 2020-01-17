/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author ADMIN
 */
public class ComBoxTool<E> extends StackPane implements Tool{
    private final ComboBox combo;
    private VBox paneCentral;
    
    public ComBoxTool(int largo, String cabecera,List<E> elementos, int size){
        combo = new ComboBox();
        combo.getItems().addAll(elementos);
        combo.setPrefWidth(largo);
        combo.setPrefHeight(43);
        
        paneCentral = new VBox(10);
        
        Text textCabecera = new Text(cabecera);
        textCabecera.setFont(textCabecera.getFont().font("Arial" , FontWeight.BOLD, size));
        textCabecera.setFill(Color.BLACK);
        
        setAlignment(Pos.CENTER_LEFT);
        
        paneCentral.getChildren().addAll(textCabecera, combo);
        
        getChildren().addAll(paneCentral);
    }
    
    public Object getValue(){
        return combo.getValue();
    }

    @Override
    public boolean isEmplyTool() {
        return combo.getValue() == null;
    }
}
