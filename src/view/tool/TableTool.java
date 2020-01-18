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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

/**
 *
 * @author ADMIN
 */
public class TableTool extends StackPane implements Tool{
    private VBox panelCentral;
    private VBox panelProductos;
    private int ancho;
    
    private final Color colorCabecera = Color.rgb(249, 249, 249);
    private final Color colorElementos = Color.rgb(253, 253, 253);
    
    private int anchoColumna;
    private int altura;
    
    private int titulo3;
    
    private BoxTextTool mensaje;
    
    public TableTool(int ancho, List<String> lista, String mensajeCentral, int titulo3){
        panelCentral = new VBox();
       
       panelProductos = new VBox(2);
       
       this.ancho = ancho;
       this.anchoColumna = ancho / lista.size();
       this.altura = 35;
       this.titulo3 = titulo3;
       
       Fila cabecera = new Fila(lista , anchoColumna, altura, colorCabecera, Color.rgb(200, 200, 200),Color.BLACK);
       
       mensaje = new BoxTextTool(mensajeCentral, anchoColumna, 75, Color.WHITE, null, Color.rgb(234, 200, 65), 30, FontWeight.NORMAL, Pos.CENTER);
       
       panelProductos.getChildren().add(mensaje);
       
       panelCentral.getChildren().addAll(cabecera, panelProductos);
       
       getChildren().add(panelCentral);
    }
    
    public TableTool(int ancho, int titulo1){
       panelCentral = new VBox();
       
       this.ancho = ancho;
       this.anchoColumna = ancho / 2 - 15;
       this.altura = 55;
       this.titulo3 = titulo1;
       
       Fila fila1 = new Fila("Descuento a todos los\nproductos %:", "Establecer Descuento", anchoColumna, altura, Color.WHITE, Color.RED);
       
       Fila fila2 = new Fila("Monto fijo de descuento:", "Establecer Descuento",anchoColumna, altura, Color.WHITE, Color.RED);
       
       Fila fila3 = new Fila("Subtotal:", "$0.00", anchoColumna, altura, Color.rgb(241, 255, 236), Color.BLACK );
       
       Fila fila4 = new Fila("Total:", "$0.00", anchoColumna, altura, Color.WHITE, Color.BLACK);
       
       panelCentral.getChildren().addAll(fila1, fila2, fila3, fila4);
       
       getChildren().add(panelCentral);
    }

    @Override
    public boolean isEmplyTool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class Fila extends StackPane{
        private HBox pane;
        
        public Fila(List<String> elementos, int ancho,int altura, Color colorFondo,Color ColorBordes, Color colorLetras){
            pane = new HBox();
            
            ListIterator<String> it = elementos.listIterator();
            
            while(it.hasNext()){
                BoxTextTool nuevoCuadro = new BoxTextTool( it.next(), ancho, altura,  colorFondo, ColorBordes, colorLetras, titulo3, FontWeight.NORMAL, Pos.CENTER);
                pane.getChildren().add(nuevoCuadro);
            }
            
            getChildren().add(pane);
        }
        
        public Fila(String dato, String elemento,int ancho, int altura, Color colorFondo, Color colorLetras){
            pane = new HBox();
            
            BoxTextTool cuadroDato = new BoxTextTool(dato, ancho, altura,  colorFondo, null, Color.BLACK, titulo3, FontWeight.NORMAL, Pos.CENTER_LEFT);
            BoxTextTool cuadroElemento = new BoxTextTool(elemento, ancho, altura,  colorFondo, null, colorLetras, titulo3, FontWeight.NORMAL, Pos.CENTER_RIGHT);
            
            pane.getChildren().addAll(cuadroDato, cuadroElemento);
            
            getChildren().add(pane);
        }
        
        public Fila(List<String> elementos, BotonTool boton){
            pane = new HBox();
            
            ListIterator<String> it = elementos.listIterator();
            
            while(it.hasNext()){
                BoxTextTool nuevoCuadro = new BoxTextTool( it.next(), anchoColumna + 1, altura,  colorElementos, null, Color.BLACK, titulo3, FontWeight.NORMAL, Pos.CENTER);
                pane.getChildren().add(nuevoCuadro);
            }     
            
            pane.setAlignment(Pos.CENTER_LEFT);
            
            if(boton != null)
                pane.getChildren().add(boton);
            
            getChildren().add(pane);
        }
    }
    
    public void anadirItem(List<String> listData, BotonTool boton){
        if(panelProductos.getChildren().contains(mensaje))
            panelProductos.getChildren().clear();
        
        panelProductos.getChildren().add(new Fila(listData, boton));
    }
    
    public void limpiarContenido(){
        panelProductos.getChildren().clear();
    }
}
