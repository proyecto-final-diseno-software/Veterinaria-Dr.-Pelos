/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author ADMIN
 */
public class TableTool extends StackPane{
    private VBox panelCentral;
    private VBox panelProductos;
    private int ancho;
    
    private final Color colorCabecera = Color.rgb(249, 249, 249);
    private final Color colorElementos = Color.rgb(240, 240, 240);
    
    private int anchoColumna;
    private int altura;
    private int tamanoLetras;
    
    private BoxTextTool mensaje;
    
    public TableTool(int ancho, List<String> lista, String mensajeCentral){
       panelCentral = new VBox();
       
       panelProductos = new VBox(2);
       
       this.ancho = ancho;
       this.anchoColumna = ancho / lista.size();
       this.altura = 35;
       this.tamanoLetras = 13;
       
       Fila cabecera = new Fila(lista , anchoColumna, altura, colorCabecera, Color.rgb(200, 200, 200),Color.BLACK, tamanoLetras);
       
       mensaje = new BoxTextTool(mensajeCentral, anchoColumna, 75, Color.WHITE, null, Color.rgb(234, 200, 65), 30, FontWeight.NORMAL, Pos.CENTER);
       
       panelProductos.getChildren().add(mensaje);
       
       panelCentral.getChildren().addAll(cabecera, panelProductos);
       
       getChildren().add(panelCentral);
    }
    
    public TableTool(int ancho){
       panelCentral = new VBox();
       
       this.ancho = ancho;
       this.anchoColumna = ancho / 2 - 15;
       this.altura = 55;
       this.tamanoLetras = 15;
       
       Fila fila1 = new Fila("Descuento a todos los\nproductos %:", "Establecer Descuento", anchoColumna, altura, Color.WHITE, Color.RED, tamanoLetras);
       
       Fila fila2 = new Fila("Monto fijo de descuento:", "Establecer Descuento",anchoColumna, altura, Color.WHITE, Color.RED,tamanoLetras);
       
       Fila fila3 = new Fila("Subtotal:", "$0.00", anchoColumna, altura, Color.rgb(241, 255, 236), Color.BLACK ,tamanoLetras);
       
       Fila fila4 = new Fila("Total:", "$0.00", anchoColumna, altura, Color.WHITE, Color.BLACK,tamanoLetras * 2);
       
       panelCentral.getChildren().addAll(fila1, fila2, fila3, fila4);
       
       getChildren().add(panelCentral);
    }
    
    private class Fila extends StackPane{
        private HBox pane;
        
        public Fila(List<String> elementos, int ancho,int altura, Color colorFondo,Color ColorBordes, Color colorLetras ,int tamanoLetras){
            pane = new HBox();
            
            ListIterator<String> it = elementos.listIterator();
            
            while(it.hasNext()){
                BoxTextTool nuevoCuadro = new BoxTextTool( it.next(), ancho, altura,  colorFondo, ColorBordes, colorLetras, tamanoLetras, FontWeight.NORMAL, Pos.CENTER);
                pane.getChildren().add(nuevoCuadro);
            }
            
            getChildren().add(pane);
        }
        
        public Fila(String dato, String elemento,int ancho, int altura, Color colorFondo, Color colorLetras ,int tamanoLetras){
            pane = new HBox();
            
            BoxTextTool cuadroDato = new BoxTextTool(dato, ancho, altura,  colorFondo, null, Color.BLACK, tamanoLetras, FontWeight.NORMAL, Pos.CENTER_LEFT);
            BoxTextTool cuadroElemento = new BoxTextTool(elemento, ancho, altura,  colorFondo, null, colorLetras, tamanoLetras, FontWeight.NORMAL, Pos.CENTER_RIGHT);
            
            pane.getChildren().addAll(cuadroDato, cuadroElemento);
            
            getChildren().add(pane);
        }
    }
    
    public void anadirProducto(BotonTool boton){
        if(panelProductos.getChildren().contains(mensaje))
            panelProductos.getChildren().clear();
    }
}
