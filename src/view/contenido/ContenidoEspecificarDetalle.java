/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import modelo.Detalle_Venta;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoEspecificarDetalle extends Parent implements ContenidoCentral{
    private final int anchoVentanaContenido;
    private final int titulo1;
    private final int titulo2;
    private final int anchoVentana;
    private final int altoVentana;
    
    private VBox paneIngresoDatos;
    private Pane paneCentral;
    
    private Detalle_Venta det;
    
    private TextFieldTool textFieldCantidad;
    
    public ContenidoEspecificarDetalle(int anchoVentana, int altoVentana, int titulo1, int titulo2, Detalle_Venta det){
        this.anchoVentanaContenido = 300;
        this.titulo1 = titulo1;
        this.titulo2 = titulo2;
        this.det = det;
        this.anchoVentana =anchoVentana;
        this.altoVentana = altoVentana;
    }
    
    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        paneIngresoDatos = new VBox(20);
        paneIngresoDatos.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-20))));
        paneIngresoDatos.setAlignment(Pos.CENTER);
        paneIngresoDatos.setTranslateX(anchoVentana/ 2 - anchoVentanaContenido / 2 );
        paneIngresoDatos.setTranslateY(altoVentana / 2 - 200);
        
        paneCentral = new Pane();
        
        Rectangle bg = new Rectangle(anchoVentana, altoVentana);
        bg.setTranslateX(-10);
        bg.setTranslateY(-10);
        bg.setFill(Color.WHITE);
        bg.setOpacity(0.5);
        
        HBox cabecera = new HBox(5);
        
        BoxTextTool cabeceraTexto = new BoxTextTool("Cambiar detalles articulo", Color.BLACK, titulo1, FontWeight.BOLD);
        cabecera.getChildren().add(cabeceraTexto);
        
        HBox primero = new HBox(5);
        BoxTextTool nombreArticulo = new BoxTextTool(det.getNombre(), Color.BLACK, titulo1, FontWeight.BOLD);
        primero.getChildren().add(nombreArticulo);
        
        HBox segundoPanel = new HBox(20);
        textFieldCantidad = new TextFieldTool("Cantidad de productos", "Cantidad:", titulo2, Pos.CENTER_LEFT, anchoVentanaContenido / 2, titulo2);
        textFieldCantidad.setText(Integer.toString(det.getCantidad()));
        segundoPanel.getChildren().add(textFieldCantidad);
        toolUsados.add(textFieldCantidad);
        
        paneIngresoDatos.getChildren().addAll(cabecera, primero,segundoPanel);
        
        paneCentral.getChildren().addAll(bg, paneIngresoDatos);
        
        getChildren().addAll(paneCentral);
    }
    
    public void anadirBotones(BotonTool botonAceptar,BotonTool botonEliminar){
        HBox panebotones = new HBox(5);
        panebotones.getChildren().addAll(botonAceptar, botonEliminar);
        paneIngresoDatos.getChildren().add(panebotones);
    }
    
    public void setCantidad(){
        int nuevaCantidad = Integer.parseInt((String) textFieldCantidad.getValue());
        det.setCantidad(nuevaCantidad);
    }
}
