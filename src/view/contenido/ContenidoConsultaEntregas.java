/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.ComBoxTool;
import view.tool.TableTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoConsultaEntregas extends Contenido implements ContenidoCentral{
    
    private TableTool tablaProductos;
    
    public ContenidoConsultaEntregas(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        
        this.paneFondo = new Pane();
        paneFondo.setPrefWidth(anchoVentana - this.reduccionX);
        paneFondo.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-10, 0, -20, 0))));
        
        this.colunma1 = new VBox(20);
        colunma1.setTranslateX(20);
        this.colunma1.setAlignment(Pos.TOP_LEFT);
        
        this.pane1 = new FlowPane();
    }

    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        int anchoField = (anchoVentana - 80) / 4;
        
        HBox Tutuo = new HBox(5);
        BoxTextTool cabeceraTexto = new BoxTextTool("Busqueda de envios", Color.BLACK, titulo1, FontWeight.BOLD);
        Tutuo.getChildren().add(cabeceraTexto);
        
        HBox cabecera = new HBox(5);
        
        TextFieldTool busquedaID = new TextFieldTool("Buscar por id", "ID:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        toolUsados.add(busquedaID);
        
        TextFieldTool busquedaCliente = new TextFieldTool("Buscar por Cliente", "Cliente:", titulo2, Pos.CENTER_LEFT, anchoField, titulo2);
        toolUsados.add(busquedaCliente);
        
        List<String> listaTipo = new ArrayList<>();
        listaTipo.add("Mercaderia");
        listaTipo.add("Mascota");
        ComBoxTool<String> comboTipo = new ComBoxTool(anchoField, "Tipo:" , listaTipo, titulo2);
        toolUsados.add(comboTipo);
        
        BotonTool BotonBuscarProducto = new BotonTool("Buscar", titulo2, 100, titulo2 * 2 + 3, Color.GAINSBORO);
        BotonBuscarProducto.setTranslateY(16);
        
        cabecera.getChildren().addAll(busquedaID, busquedaCliente, comboTipo, BotonBuscarProducto);
        
        List<String> camposMercaderia = new ArrayList<>();
        camposMercaderia.add("Codigo");
        camposMercaderia.add("Fecha");
        camposMercaderia.add("Monto Total");
        camposMercaderia.add("Descuento");
        
        camposMercaderia.add("");
        
        List<String> camposMascotas = new ArrayList<>();
        camposMascotas.add("Codigo");
        camposMascotas.add("Nombre");
        camposMascotas.add("Raza");
        camposMascotas.add("");
        
        BotonBuscarProducto.setOnMousePressed(buscarProducto -> {
            
        });
        
        tablaProductos = new TableTool(anchoVentana - this.reduccionX - 40, camposMercaderia, "No hay articulos con esta descripcion", titulo2);
        pane1.getChildren().add(tablaProductos);
        
        colunma1.getChildren().addAll(Tutuo, cabecera, pane1);
        
        paneFondo.getChildren().add(colunma1);
        
        getChildren().add(paneFondo);
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
