/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author ADMIN
 */
public class TextFieldTool extends StackPane{
    private TextField ingresoTexto;
    private VBox paneCentral;
    
    public TextFieldTool(String textFon, int size, Pos pos, int largo, int alto){
        ingresoTexto = new TextField();
        ingresoTexto.setFont(ingresoTexto.getFont().font(size));
        ingresoTexto.setPromptText(textFon);
        ingresoTexto.setPrefHeight(alto);
        ingresoTexto.setPrefWidth(largo);
        
        setAlignment(pos);
        
        getChildren().add(ingresoTexto);
    }
    
    public TextFieldTool(String textFon, String cabecera, int size, Pos pos, int largo, int alto){
        paneCentral = new VBox(10);
        
        ingresoTexto = new TextField();
        ingresoTexto.setFont(ingresoTexto.getFont().font(size));
        ingresoTexto.setPromptText(textFon);
        ingresoTexto.setPrefHeight(alto);
        ingresoTexto.setPrefWidth(largo);
        
        Text textCabecera = new Text(cabecera);
        textCabecera.setFont(textCabecera.getFont().font("Arial" , FontWeight.BOLD, size));
        textCabecera.setFill(Color.BLACK);
        
        setAlignment(pos);
        
        paneCentral.getChildren().addAll(textCabecera, ingresoTexto);
        
        getChildren().add(paneCentral);
    }
    
    public boolean validacionInt() {
        try {
            Integer.parseInt(ingresoTexto.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public boolean isEmpty() {
        if(ingresoTexto.getText().isEmpty()){
            //cuadroFondo.setOpacity(0.2);
            return false;
        }
        //cuadroFondo.setOpacity(0);
        return true;
    }
    
    public String getText() {
        return ingresoTexto.getText();
    }
    
    public void limpiar(){
        ingresoTexto.clear();
    }
}
