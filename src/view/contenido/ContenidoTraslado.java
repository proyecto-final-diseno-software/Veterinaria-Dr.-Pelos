/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import modelo.Cliente;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.ComBoxTool;
import view.tool.TableTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author Eduardo Gonzalez
 */
public class ContenidoTraslado extends Parent{
    private int titulo2;
    private Color ColorOscuro;
    
    public ContenidoTraslado(int reduccionx, String tipo, String cabeceraField, int titulo1, int titulo2, Color ColorOscuro, int anchoVentana){
        this.titulo2 = titulo2;
        this.ColorOscuro = ColorOscuro;
        
        List<Tool> toolUsados = new ArrayList<>();
        
        Pane mensajeError = new Pane();
        
        VBox paneIngresoDatos = new VBox(20);
        
        HBox cabecera = new HBox(5);
        BoxTextTool cabeceraTexto = new BoxTextTool(tipo, Color.BLACK, titulo1, FontWeight.BOLD);
        cabecera.getChildren().add(cabeceraTexto);
        
        int mitadVentanaActiva = (anchoVentana - reduccionx) / 2 - 25;
        
        HBox primero = new HBox(20);
        TextFieldTool textFieldBuscador = new TextFieldTool(cabeceraField ,titulo2, Pos.CENTER, mitadVentanaActiva, 40);
        BotonTool botonBuscar = new BotonTool("Buscar", titulo2 - 1, 90, 40, ColorOscuro);
        botonBuscar.setOnMousePressed(buscar -> {
            if(!textFieldBuscador.isEmplyTool())
                establecerTrasladoMascotas(anchoVentana - reduccionx - 35, null, paneIngresoDatos, toolUsados);
        });
        primero.getChildren().addAll(textFieldBuscador, botonBuscar);
        
        paneIngresoDatos.getChildren().addAll(cabecera, primero, mensajeError);
        
        getChildren().add(paneIngresoDatos);
    }
    
    private void establecerTrasladoMascotas(int ancho, Cliente cliente, VBox pane, List<Tool> tools){
            ComBoxTool escogerRuta = new ComBoxTool(250, "Ruta:", new ArrayList<>(), titulo2);
            ComBoxTool escogerRepartidor = new ComBoxTool(250, "Repartidor:", new ArrayList<>(), titulo2);
            
            tools.add(escogerRuta);
            tools.add(escogerRepartidor);
            
            List<String> datosMascotas = new ArrayList<>();
            datosMascotas.add("Codigo");
            datosMascotas.add("Nombre");
            datosMascotas.add("Raza");
            datosMascotas.add("Estado");
            datosMascotas.add("");
            
            TableTool tableMascotas = new TableTool(ancho, datosMascotas, "No hay mascotas disponibles");
            
            BotonTool botonRecogerMascota = new BotonTool("Recoger Mascota", titulo2, 200, titulo2 * 2, ColorOscuro);
            botonRecogerMascota.setOnMousePressed(recogerMascota -> {
            });
            
            pane.getChildren().addAll(escogerRuta, escogerRepartidor, tableMascotas, botonRecogerMascota);
        }
}
