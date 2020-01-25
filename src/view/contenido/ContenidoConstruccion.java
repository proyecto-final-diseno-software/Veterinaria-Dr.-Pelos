/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.List;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import view.tool.BoxTextTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoConstruccion extends Contenido implements ContenidoCentral{

    public ContenidoConstruccion(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior) {
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        this.paneCentral = new HBox();
    }

    @Override
    public void establecerPaneles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpirarContenido() {
        
    }

    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        BoxTextTool cabeceraTexto = new BoxTextTool("Funcionalidad en construccion", Color.BLACK, 50, FontWeight.BOLD);
        paneCentral.getChildren().add(cabeceraTexto);
        this.getChildren().add(paneCentral);
    }
    
}
