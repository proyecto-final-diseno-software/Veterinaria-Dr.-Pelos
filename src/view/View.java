/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import view.states.ViewSsesion;

/**
 *
 * @author Gonzalez Beltran Eduardo Andres
 */
public class View extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage theStage) throws Exception{
        ViewSsesion paginaPrincipal = new ViewSsesion();
        paginaPrincipal.crearVentana(theStage, 1280, 720);
    }
    
}
