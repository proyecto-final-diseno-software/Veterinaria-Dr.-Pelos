/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import java.util.List;
import javafx.geometry.Pos;
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
       
       FilaTool cabecera = new FilaTool(lista , anchoColumna, altura, colorCabecera, Color.rgb(200, 200, 200), Color.BLACK, titulo3);
       
       mensaje = new BoxTextTool(mensajeCentral, anchoColumna, 75, Color.WHITE, null, Color.rgb(234, 200, 65), 30, FontWeight.NORMAL, Pos.CENTER);
       
       panelProductos.getChildren().add(mensaje);
       
       panelCentral.getChildren().addAll(cabecera, panelProductos);
       
       getChildren().add(panelCentral);
    }
    
    public void anadirItem(List<String> listData, BotonTool boton){
        if(panelProductos.getChildren().contains(mensaje))
            panelProductos.getChildren().clear();
        
        panelProductos.getChildren().add(new FilaTool(listData, boton, altura, anchoColumna, colorElementos, titulo3));
    }
    
    public void limpiarContenido(){
        panelProductos.getChildren().clear();
    }
    
    @Override
    public boolean isEmplyTool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
