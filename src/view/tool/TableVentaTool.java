/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author ADMIN
 */
public class TableVentaTool extends StackPane implements Tool{
    private VBox panelCentral;
    private int ancho;
    
    private int anchoColumna;
    private int altura;
    
    private int titulo3;
    
    public TableVentaTool(int ancho, int titulo3){
        panelCentral = new VBox();
       
        this.titulo3 = titulo3;
        this.ancho = ancho;
        this.anchoColumna = ancho / 2 - 15;
        this.altura = 55;
       
        getChildren().add(panelCentral);
    }
    
    public void actualizarMonto(double monto){
        panelCentral.getChildren().clear();
        
        FilaTool fila1 = new FilaTool("Descuento a todos los\nproductos %:", "Establecer Descuento", anchoColumna, altura, Color.WHITE, Color.RED, titulo3);
        FilaTool fila2 = new FilaTool("Monto fijo de descuento:", "Establecer Descuento",anchoColumna, altura, Color.WHITE, Color.RED, titulo3);
        FilaTool fila3 = new FilaTool("Subtotal:", Double.toString(monto), anchoColumna, altura, Color.rgb(241, 255, 236), Color.BLACK , titulo3);
        FilaTool fila4 = new FilaTool("Total:", Double.toString(monto), anchoColumna, altura, Color.WHITE, Color.BLACK, titulo3);
       
        panelCentral.getChildren().addAll(fila1, fila2, fila3, fila4);
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
