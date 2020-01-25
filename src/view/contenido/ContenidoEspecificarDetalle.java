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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import modelo.DetalleVenta;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoEspecificarDetalle extends Contenido implements ContenidoCentral{
    private final int anchoVentanaContenido;
    
    private DetalleVenta det;
    
    private TextFieldTool textFieldCantidad;
    
    public ContenidoEspecificarDetalle(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior, DetalleVenta det){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        this.anchoVentanaContenido = 300;
        this.det = det;
        
        this.paneFondo = new Pane();
        this.colunma1 = new VBox(20);
    }
    
    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        colunma1.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-20))));
        colunma1.setAlignment(Pos.CENTER);
        colunma1.setTranslateX((anchoVentana - reduccionX + 20)/2 - 150);
        colunma1.setTranslateY((altoVentana - reduccionY + 20)/2 - 150);
        
        Rectangle bg = new Rectangle(anchoVentana - reduccionX + 20, altoVentana - reduccionY + 20);
        bg.setFill(Color.WHITE);
        bg.setOpacity(0.5);
        bg.setTranslateY(-10);
        
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
        
        colunma1.getChildren().addAll(cabecera, primero, segundoPanel);
        paneFondo.getChildren().addAll(bg, colunma1);
        
        getChildren().add(paneFondo);
    }
    
    public void anadirBotones(BotonTool botonAceptar,BotonTool botonEliminar){
        HBox panebotones = new HBox(5);
        panebotones.getChildren().addAll(botonAceptar, botonEliminar);
        colunma1.getChildren().add(panebotones);
    }
    
    public void setCantidad(){
        int nuevaCantidad = Integer.parseInt((String) textFieldCantidad.getValue());
        det.setCantidad(nuevaCantidad);
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
