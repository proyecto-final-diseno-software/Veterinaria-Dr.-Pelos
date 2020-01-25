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
    private PrincipalContenedorCaja ventana;

    public ViewJefeBodega() {
    }

    @Override
    void mostrar_ventana(Stage primaryStage) {}

    @Override
    void close() {}

    @Override
    void cambiar_ventana(Pane root) {
        this.root = root;
        this.root.getChildren().clear();
        this.ventana = new PrincipalContenedorCaja();
        this.root.getChildren().add(ventana);
    }
    
    private class PrincipalContenedorCaja extends Parent{
        private ContenidoConstruccion ventanEnCostrucion;
        
        public PrincipalContenedorCaja(){}
        
        private void establecerContenidoAnadirUser(){
            ventanEnCostrucion = new ContenidoConstruccion(0, 0, anchoVentana, altoVentana, 0, 0, 0, 0);
            ventanEnCostrucion.establecerFuente(titulo3, titulo2, titulo1, ColorOscuro, colorClaro);
            ventanEnCostrucion.crearContenidoCentral(null);
            
            this.getChildren().add(ventanEnCostrucion);
        }
    }
}
