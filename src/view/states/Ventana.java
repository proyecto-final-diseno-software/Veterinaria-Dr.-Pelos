/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author paula
 */
public abstract class Ventana {
    protected int anchoVentana;
    protected int altoVentana;
    
    public void crear_ventana(Stage primaryStage, int anchoVentana, int altoVentana){
        this.anchoVentana = anchoVentana;
        this.altoVentana = altoVentana;
        mostrar_ventana(primaryStage);
    }
    
    abstract void mostrar_ventana(Stage primaryStage);
    
    abstract void close();
    
    private class PrincipalContenedor extends Parent{};
}
