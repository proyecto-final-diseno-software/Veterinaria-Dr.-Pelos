/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import modelo.Cliente;
import view.tool.BoxTextTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoPerfil extends Contenido implements ContenidoCentral{
    private int anchoVentanaConenido;
    
    private VBox panelCentral;
    private HBox panelDatos;
    private VBox clunma1;
    private VBox clunma2;
    
    private Cliente cliente;
    
    public ContenidoPerfil(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior, Cliente cliente){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        this.anchoVentanaConenido = anchoVentana - reduccionx;
        this.cliente = cliente;
    }

    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        panelCentral = new VBox(20);
        panelCentral.setTranslateX(20);
        panelDatos = new HBox(10);
        panelDatos.setAlignment(Pos.CENTER_LEFT);
        panelDatos.setBackground(new Background(new BackgroundFill(Color.rgb(240, 240, 240), new CornerRadii(5), new Insets(-10))));
        
        clunma1 = new VBox(15);
        clunma2 = new VBox(15);
        
        BoxTextTool cabeceraTexto = new BoxTextTool("Informacion cliente", Color.BLACK, titulo1, FontWeight.BOLD);
        
        BoxTextTool textoCedula = new BoxTextTool("Cedula cliente: ", Color.BLACK, titulo2, FontWeight.BOLD);
        BoxTextTool textoCedulaCliente = new BoxTextTool(cliente.getCedula(), Color.BLACK, titulo2, FontWeight.NORMAL);
      
        BoxTextTool textoNombre = new BoxTextTool("Nombre cliente: ", Color.BLACK, titulo2, FontWeight.BOLD);
        BoxTextTool textoNombreCliente = new BoxTextTool(cliente.getNombre(), Color.BLACK, titulo2, FontWeight.NORMAL);
        
        BoxTextTool textoApellido = new BoxTextTool("Apellido cliente: ", Color.BLACK, titulo2, FontWeight.BOLD);
        BoxTextTool textoApellidoCliente = new BoxTextTool(cliente.getApellido(), Color.BLACK, titulo2, FontWeight.NORMAL);
       
        BoxTextTool textoDireccion = new BoxTextTool("Direccion cliente: ", Color.BLACK, titulo2, FontWeight.BOLD);
        BoxTextTool textoDireccionCliente = new BoxTextTool(cliente.getDireccion(), Color.BLACK, titulo2, FontWeight.NORMAL);
 
        BoxTextTool textoTelefono = new BoxTextTool("Telefono cliente: ", Color.BLACK, titulo2, FontWeight.BOLD);
        BoxTextTool textoTelefonoCliente = new BoxTextTool(cliente.getNum_telefonico(), Color.BLACK, titulo2, FontWeight.NORMAL);
        
        panelCentral.getChildren().addAll(cabeceraTexto, panelDatos);
        
        clunma1.getChildren().addAll(textoCedula, textoNombre, textoApellido, textoDireccion, textoTelefono);
        clunma2.getChildren().addAll(textoCedulaCliente, textoNombreCliente, textoApellidoCliente, textoDireccionCliente, textoTelefonoCliente);
        
        panelDatos.getChildren().addAll(clunma1, clunma2);
        
        pane1.getChildren().add(panelCentral);
    }
}
