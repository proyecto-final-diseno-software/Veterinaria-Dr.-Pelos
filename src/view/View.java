/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import view.states.View_ssesion;

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
        View_ssesion PaginaPrincipal = new View_ssesion();
        PaginaPrincipal.crear_ventana(theStage, 1200, 900);
        
        //Stage VentanaPaginaPuesto = new Stage();
        //PaginaPuesto PaginaPuesto = new PaginaPuesto();
        //PaginaPuesto.controladorPaginaPrincipal(VentanaPaginaPuesto);
        
        //Stage VentanaPaginaRegistro = new Stage();
        //PaginaRegistro PaginaRegistro = new PaginaRegistro();
        //PaginaRegistro.controladorPaginaPrincipal(VentanaPaginaRegistro);
    }
    
}
