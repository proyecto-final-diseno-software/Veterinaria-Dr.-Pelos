/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.List;
import java.util.ListIterator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public abstract class Contenido extends Parent{
    protected int reduccionX;
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
    protected HBox paneCentral;
    
    protected VBox colunma1;
    protected VBox colunma2;
    
    protected int anchoColunma1;
    protected int anchoColunma2;
    
    protected int titulo3;
    protected int titulo2;
    protected int titulo1;
    protected Color ColorOscuro;
    protected Color colorClaro;
    
    public Contenido(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior){
        this.reduccionX = reduccionx;
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
    
    public abstract void establecerPaneles();
    
    protected void establecerFondoUnico(Pos pos){
        this.colunma1.getChildren().add(pane1);
        this.paneCentral.getChildren().add(colunma1);
        pane1.setPrefWrapLength(anchoVentana - reduccionX);
        generarsecciones(pane1, pos);
    }
            
    protected void generarsecciones(FlowPane pane, Pos pos){
        pane.setAlignment(pos);
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-10, 0, -20, 0))));
    }
        
    protected boolean comprobarCampos(List<Tool> tools){
        ListIterator<Tool> it = tools.listIterator();
        
        while(it.hasNext()){
            if(it.next().isEmplyTool())
                return false;
        }
        return true;
    }
}
