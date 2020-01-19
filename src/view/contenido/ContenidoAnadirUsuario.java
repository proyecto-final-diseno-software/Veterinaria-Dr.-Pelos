/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author Gonzalez Eduardo
 */
public class ContenidoAnadirUsuario extends Parent implements ContenidoCentral{
    private final int anchoVentanaConenido;
    private final int titulo1;
    private final int titulo2;
    
    private VBox paneIngresoDatos;
    
    public ContenidoAnadirUsuario(int anchoVentanaConenido, int titulo1, int titulo2){
        this.anchoVentanaConenido = anchoVentanaConenido;
        this.titulo1 = titulo1;
        this.titulo2 = titulo2;
    }
    
    public void anadirSellecionInferior(Pane pane){
        paneIngresoDatos.getChildren().add(pane);
    }

    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        paneIngresoDatos = new VBox(20);
        
        HBox cabecera = new HBox(5);
        
        BoxTextTool cabeceraTexto = new BoxTextTool("Registro Cliente", Color.BLACK, titulo1, FontWeight.BOLD);
        cabecera.getChildren().add(cabeceraTexto);
        
        HBox primerPanel = new HBox(20);
        TextFieldTool textFieldCedula = new TextFieldTool("Ingrese documento de identificacion", "Cedula:", titulo2, Pos.CENTER_LEFT, anchoVentanaConenido * 2 + 20, titulo2);
        primerPanel.getChildren().add(textFieldCedula);
        toolUsados.add(textFieldCedula);

        HBox segundoPanel = new HBox(20);
        TextFieldTool textFieldNombre = new TextFieldTool("Ingrese nombre del nuevo cliente", "Nombre:", titulo2, Pos.CENTER_LEFT, anchoVentanaConenido, titulo2);
        TextFieldTool textFieldApellido = new TextFieldTool("Ingrese apellido del nuevo cliente", "Apellido:", titulo2, Pos.CENTER_LEFT, anchoVentanaConenido, titulo2);
        segundoPanel.getChildren().addAll(textFieldNombre, textFieldApellido);
        toolUsados.add(textFieldNombre);
        toolUsados.add(textFieldApellido);

        HBox tercerPanel = new HBox(20);
        TextFieldTool textFieldDireccion = new TextFieldTool("Ingrese direccion del nuevo cliente", "Direccion:", titulo2, Pos.CENTER_LEFT, anchoVentanaConenido, titulo2);
        TextFieldTool textFieldtelefono = new TextFieldTool("Ingrese el telefono del nuevo cliente", "Telefono:", titulo2, Pos.CENTER_LEFT, anchoVentanaConenido, titulo2);
        tercerPanel.getChildren().addAll(textFieldDireccion, textFieldtelefono);
        toolUsados.add(textFieldDireccion);
        toolUsados.add(textFieldtelefono);
        
        paneIngresoDatos.getChildren().addAll(cabecera, primerPanel, segundoPanel, tercerPanel);
        
        getChildren().add(paneIngresoDatos);
    }
}
