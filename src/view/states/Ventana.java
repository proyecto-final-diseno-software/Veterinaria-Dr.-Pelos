/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.tool.BotonTool;
import view.tool.Tool;

/**
 *
 * @author paula
 */
public abstract class Ventana {
    protected int anchoVentana;
    protected int altoVentana;
    
    protected Pane root;
    
    protected List<BotonTool> botonesLateral = new ArrayList<>();
    
    protected final Color colorClaro = Color.rgb(40, 171, 223);
    protected final Color ColorOscuro = Color.rgb(25, 141, 216);
        
    protected ImageView logo;
        
    protected VBox menuLateral;
    protected HBox paneCentral;
    
    protected int titulo1;
    protected int titulo2;
    protected int titulo3;
    
    public void crear_ventana(Stage primaryStage, int anchoVentana, int altoVentana){
        this.anchoVentana = anchoVentana;
        this.altoVentana = altoVentana;
        
        if(primaryStage != null)
            mostrar_ventana(primaryStage);
    }
    
    protected boolean comprobarCampos(List<Tool> tools){
        ListIterator<Tool> it = tools.listIterator();
        
        while(it.hasNext()){
            if(it.next().isEmplyTool())
                return false;
        }
        return true;
    }
    
    protected void comportamientoMenuLateral(BotonTool boton){
        ListIterator<BotonTool> it = botonesLateral.listIterator();
        
        while(it.hasNext()){
            BotonTool temp = it.next();
            temp.desmarcar();
        }
        
        if(!boton.isPresionado())
            boton.marcar();
    }
    
    abstract void mostrar_ventana(Stage primaryStage);
    abstract void cambiar_ventana(Pane root);
    abstract void close();
}
