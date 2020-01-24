/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import java.util.List;
import java.util.ListIterator;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

/**
 *
 * @author ADMIN
 */
public class FilaTool extends StackPane implements Tool{
    private HBox pane;
    
    public FilaTool(List<String> elementos, int ancho,int altura, Color colorFondo, Color ColorBordes, Color colorLetras, int titulo3){
        pane = new HBox();
        
        ListIterator<String> it = elementos.listIterator();
        
        while(it.hasNext()){
            BoxTextTool nuevoCuadro = new BoxTextTool( it.next(), ancho, altura,  colorFondo, ColorBordes, colorLetras, titulo3, FontWeight.NORMAL, Pos.CENTER);
            pane.getChildren().add(nuevoCuadro);
        }
        
        getChildren().add(pane);
    }
        
        public FilaTool(String dato, String elemento,int ancho, int altura, Color colorFondo, Color colorLetras, int titulo3){
            pane = new HBox();
            
            BoxTextTool cuadroDato = new BoxTextTool(dato, ancho, altura,  colorFondo, null, Color.BLACK, titulo3, FontWeight.NORMAL, Pos.CENTER_LEFT);
            BoxTextTool cuadroElemento = new BoxTextTool(elemento, ancho, altura,  colorFondo, null, colorLetras, titulo3, FontWeight.NORMAL, Pos.CENTER_RIGHT);
            
            pane.getChildren().addAll(cuadroDato, cuadroElemento);
            
            getChildren().add(pane);
        }
        
        public FilaTool(List<String> elementos, BotonTool boton, int altura, int ancho,Color colorElementos, int titulo3){
            pane = new HBox();
            
            ListIterator<String> it = elementos.listIterator();
            
            while(it.hasNext()){
                BoxTextTool nuevoCuadro = new BoxTextTool( it.next(), ancho + 1, altura,  colorElementos, null, Color.BLACK, titulo3, FontWeight.NORMAL, Pos.CENTER);
                pane.getChildren().add(nuevoCuadro);
            }     
            
            pane.setAlignment(Pos.CENTER_LEFT);
            
            if(boton != null)
                pane.getChildren().add(boton);
            
            getChildren().add(pane);
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
