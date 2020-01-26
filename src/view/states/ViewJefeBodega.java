/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.contenido.ContenidoConstruccion;

/**
 *
 * @author paula
 */
public class ViewJefeBodega extends Ventana{

    public ViewJefeBodega() {
    }

    @Override
    void mostrarVentana(Stage primaryStage) {}

    @Override
    void close() {}

    @Override
    void cambiarVentana(Pane root) {
        this.root = root;
        this.root.getChildren().clear();
        PrincipalContenedorCaja ventana = new PrincipalContenedorCaja();
        this.root.getChildren().add(ventana);
    }
    
    private class PrincipalContenedorCaja extends Parent{
        
        public PrincipalContenedorCaja(){
            establecerContenidoAnadirUser();
        }
        
        private void establecerContenidoAnadirUser(){
            ContenidoConstruccion ventanEnCostrucion = new ContenidoConstruccion(0, 0, anchoVentana, altoVentana, 0, 0, 0, 0);
            ventanEnCostrucion.establecerFuente(titulo3, titulo2, titulo1, colorOscuro, colorClaro);
            ventanEnCostrucion.crearContenidoCentral(null);
            
            this.getChildren().add(ventanEnCostrucion);
        }
    }
}
