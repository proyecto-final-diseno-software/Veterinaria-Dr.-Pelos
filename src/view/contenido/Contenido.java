/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author ADMIN
 */
public abstract class Contenido{
    protected int reduccionx;
    protected int reduccionY;
    protected int anchoVentana;
    protected int altoVentana;
    protected int anchoLateral;
    protected int altoSuperior;
    
    protected FlowPane pane1;
    protected FlowPane pane2;
    protected FlowPane pane3;
    protected FlowPane pane4;
    protected Pane paneFondo;
    
    protected int anchoColunma1;
    protected int anchoColunma2;
    
    protected int titulo3;
    protected int titulo2;
    protected int titulo1;
    protected Color ColorOscuro;
    protected Color colorClaro;
    
    public Contenido(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior){
        this.reduccionx = reduccionx;
        this.reduccionY = reduccionY;
        this.anchoVentana = anchoVentana;
        this.altoVentana = altoVentana;
        this.anchoColunma1 = anchoColunma1;
        this.anchoColunma2 = anchoColunma2;
        this.anchoLateral = anchoLateral;
        this.altoSuperior = altoSuperior;
    }
    
    public void establecerFuente(int titulo3, int titulo2, int titulo1, Color ColorOscuro, Color colorClaro){
        this.titulo3 = titulo3;
        this.titulo2 = titulo2;
        this.titulo1 = titulo1;
        this.ColorOscuro = ColorOscuro;
        this.colorClaro = colorClaro;
    }
    
    public void establecerPaneles(FlowPane pane1, FlowPane pane2, FlowPane pane3, FlowPane pane4, Pane paneFondo){
        this.pane1 = pane1;
        this.pane2 = pane2;
        this.pane3 = pane3;
        this.pane4 = pane4;
        this.paneFondo = paneFondo;
    }
}
