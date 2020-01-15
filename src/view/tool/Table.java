/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
public class Table extends StackPane{
    private VBox panelCentral;
    private VBox panelProductos;
    private int ancho;
    
    private final Color colorCabecera = Color.rgb(249, 249, 249);
    private final Color colorElementos = Color.rgb(240, 240, 240);
    
    private int anchoColumna;
    private int altura;
    private int tamanoLetras;
    
    private Cuadro mensaje;
    
    public Table(int ancho){
       panelCentral = new VBox();
       
       panelProductos = new VBox(2);
       
       this.ancho = ancho;
       this.anchoColumna = ancho / 5;
       this.altura = 35;
       this.tamanoLetras = 13;
       
       List<String> lista = new ArrayList<>();
       lista.add("Nombre");
       lista.add("Precio");
       lista.add("Cantidad");
       lista.add("Descuento");
       lista.add("Total");
       
       Fila cabecera = new Fila(lista , anchoColumna, altura, colorCabecera, Color.rgb(200, 200, 200),Color.BLACK, tamanoLetras);
       
       mensaje = new Cuadro("No hay articulos en el carrito", anchoColumna, 75, Color.WHITE, null, Color.rgb(234, 200, 65), 30, FontWeight.NORMAL, Pos.CENTER);
       
       panelProductos.getChildren().add(mensaje);
       
       panelCentral.getChildren().addAll(cabecera, panelProductos);
       
       getChildren().add(panelCentral);
    }
    
    public Table(int ancho, Color color){
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
    
    private class Cuadro extends StackPane{
        private Text text;
        
        public Cuadro(String nombre, int ancho,int altura, Color colorFondo, Color colorBordes, Color colorLetras,int tamanoLetras, FontWeight tipo, Pos pos){
            text = new Text(nombre);
            text.setFont(text.getFont().font("Arial" , tipo, tamanoLetras));
            text.setFill(colorLetras);
            
            if(colorFondo != null){
                Rectangle bg = new Rectangle(ancho, altura);
                bg.setOpacity(0.8);
                bg.setFill(colorFondo);

                if(colorBordes != null){
                    bg.setStroke(colorBordes);
                    bg.setStrokeWidth(0.5);
                }
                
                bg.setEffect(new GaussianBlur(1));
                
                getChildren().add(bg);
            }
            
            setAlignment(pos);
            
            getChildren().add(text);
        }
    }
    
    private class Fila extends StackPane{
        private HBox pane;
        
        public Fila(List<String> elementos, int ancho,int altura, Color colorFondo,Color ColorBordes, Color colorLetras ,int tamanoLetras){
            pane = new HBox();
            
            ListIterator<String> it = elementos.listIterator();
            
            while(it.hasNext()){
                Cuadro nuevoCuadro = new Cuadro( it.next(), ancho, altura,  colorFondo, ColorBordes, colorLetras, tamanoLetras, FontWeight.NORMAL, Pos.CENTER);
                pane.getChildren().add(nuevoCuadro);
            }
            
            getChildren().add(pane);
        }
        
        public Fila(String dato, String elemento,int ancho,int altura, Color colorFondo, Color colorLetras ,int tamanoLetras){
            pane = new HBox();
            
            Cuadro cuadroDato = new Cuadro(dato, ancho, altura,  colorFondo, null, Color.BLACK, tamanoLetras, FontWeight.NORMAL, Pos.CENTER_LEFT);
            Cuadro cuadroElemento = new Cuadro(elemento, ancho, altura,  colorFondo, null, colorLetras, tamanoLetras, FontWeight.NORMAL, Pos.CENTER_RIGHT);
            
            pane.getChildren().addAll(cuadroDato, cuadroElemento);
            
            getChildren().add(pane);
        }
    }
    
    public void anadirProducto(){
        if(panelProductos.getChildren().contains(mensaje))
            panelProductos.getChildren().clear();
    }
    
}
