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
import javafx.scene.layout.FlowPane;
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
    
    private VBox panelFondoPerfil;
    
    public ContenidoPerfil(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        this.anchoVentanaConenido = anchoVentana - reduccionx;
        
        this.panelFondoPerfil = new VBox(20);
        this.paneCentral = new HBox();
        this.colunma1 = new VBox();
        this.colunma2 = new VBox();
        this.pane1 = new FlowPane();
    }

    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        panelFondoPerfil.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-10, 0, -20, 0))));
        
        paneCentral = new HBox(10);
        paneCentral.setAlignment(Pos.CENTER_LEFT);
        
        colunma1 = new VBox(5);
        colunma2 = new VBox(5);
        
        BoxTextTool cabeceraTexto = new BoxTextTool("Informacion cliente", Color.BLACK, titulo1, FontWeight.BOLD);
        
        panelFondoPerfil.getChildren().addAll(cabeceraTexto, paneCentral);
        paneCentral.getChildren().addAll(colunma1, colunma2);
        colunma1.getChildren().add(pane1);
        
        getChildren().add(panelFondoPerfil);
    }
    
    public void setInformacionUser(Cliente cliente){
        this.pane1.getChildren().clear();
        
        HBox paneDatosCliente = new HBox(5);
        paneDatosCliente.setTranslateX(20);
        paneDatosCliente.setAlignment(Pos.CENTER_LEFT);
        
        VBox datos = new VBox(15);
        VBox datosCliente = new VBox(15);
        
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
        
        datos.getChildren().addAll(textoCedula, textoNombre, textoApellido, textoDireccion, textoTelefono);
        datosCliente.getChildren().addAll(textoCedulaCliente, textoNombreCliente, textoApellidoCliente, textoDireccionCliente, textoTelefonoCliente);
        
        paneDatosCliente.getChildren().addAll(datos, datosCliente);
        
        this.pane1.getChildren().add(paneDatosCliente);
    }

    @Override
    public void establecerPaneles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpirarContenido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
