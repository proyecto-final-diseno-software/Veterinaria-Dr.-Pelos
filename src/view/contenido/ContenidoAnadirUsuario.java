/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.Iterator;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import view.tool.BoxTextTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author Gonzalez Eduardo
 */
public class ContenidoAnadirUsuario extends Contenido implements ContenidoCentral{
    private int anchoVentanaContenido;
    
    private List<Tool> toolUsados; 
    
    private VBox paneIngresoDatos;
    
    public ContenidoAnadirUsuario(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        this.paneCentral = new HBox();
        this.colunma1 = new VBox();
        this.pane1 = new FlowPane();
        this.paneIngresoDatos = new VBox(20);
    }
    
    public void contenidoAdicional(int anchoVentanaConenido){
        this.anchoVentanaContenido = anchoVentanaConenido;
    }
    
    public void anadirSellecionInferior(Pane pane){
        paneIngresoDatos.getChildren().add(pane);
    }

    @Override
    public void crearContenidoCentral(List<Tool> tools ){
        this.toolUsados = tools;
        
        paneIngresoDatos.setTranslateX(20);
        
        establecerFondoUnico(Pos.CENTER_LEFT);
        
        HBox cabecera = new HBox(5);
        
        BoxTextTool cabeceraTexto = new BoxTextTool("Registro Cliente", Color.BLACK, titulo1, FontWeight.BOLD);
        cabecera.getChildren().add(cabeceraTexto);
        
        HBox primerPanel = new HBox(20);
        TextFieldTool textFieldCedula = new TextFieldTool("Ingrese documento de identificacion", "Cedula:", titulo2, Pos.CENTER_LEFT, anchoVentanaContenido * 2 + 20, titulo2);
        primerPanel.getChildren().add(textFieldCedula);
        toolUsados.add(textFieldCedula);

        HBox segundoPanel = new HBox(20);
        TextFieldTool textFieldNombre = new TextFieldTool("Ingrese nombre del nuevo cliente", "Nombre:", titulo2, Pos.CENTER_LEFT, anchoVentanaContenido, titulo2);
        TextFieldTool textFieldApellido = new TextFieldTool("Ingrese apellido del nuevo cliente", "Apellido:", titulo2, Pos.CENTER_LEFT, anchoVentanaContenido, titulo2);
        segundoPanel.getChildren().addAll(textFieldNombre, textFieldApellido);
        toolUsados.add(textFieldNombre);
        toolUsados.add(textFieldApellido);

        HBox tercerPanel = new HBox(20);
        TextFieldTool textFieldDireccion = new TextFieldTool("Ingrese direccion del nuevo cliente", "Direccion:", titulo2, Pos.CENTER_LEFT, anchoVentanaContenido, titulo2);
        TextFieldTool textFieldtelefono = new TextFieldTool("Ingrese el telefono del nuevo cliente", "Telefono:", titulo2, Pos.CENTER_LEFT, anchoVentanaContenido, titulo2);
        tercerPanel.getChildren().addAll(textFieldDireccion, textFieldtelefono);
        toolUsados.add(textFieldDireccion);
        toolUsados.add(textFieldtelefono);
        
        paneIngresoDatos.getChildren().addAll(cabecera, primerPanel, segundoPanel, tercerPanel);
        
        this.pane1.getChildren().add(paneIngresoDatos);
        
        getChildren().addAll(paneCentral);
    }
    
    public void limpiarVentana(){
        Iterator<Tool> it = toolUsados.iterator();
        
        while(it.hasNext()){
            it.next().limpiarTool();
        }
    }

    @Override
    public void establecerPaneles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
