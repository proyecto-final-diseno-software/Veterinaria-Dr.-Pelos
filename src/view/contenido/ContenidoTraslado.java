/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import controladores.Ctr_Personal_Caja;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import modelo.Cliente;
import modelo.Mascota;
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
public class ContenidoTraslado extends Contenido implements ContenidoCentral{
    private Ctr_Personal_Caja ctrCaja;
    
    private List<Tool> toolUsados;
    private List<Mascota> mascotasCliente;
    
    private VBox paneIngresoDatos;
    
    private TableTool tableMascotas;
    
    private BoxTextTool error;
    
    private String tipo;
    private String cabeceraField;
    
    public ContenidoTraslado(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        
        this.error = new BoxTextTool("Hubo un error con el estado de esta mascota", Color.RED, titulo2, FontWeight.BOLD);
        this.ctrCaja = new Ctr_Personal_Caja();
        
        toolUsados = new ArrayList<>();
        paneIngresoDatos = new VBox(20);
    }
    
    @Override
    public void establecerPaneles(FlowPane pane1, FlowPane pane2, FlowPane pane3, FlowPane pane4, Pane paneFondo){
        this.pane1 = pane1;
        paneIngresoDatos.setTranslateX(20);
    }
    
    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        HBox cabecera = new HBox(5);
        BoxTextTool cabeceraTexto = new BoxTextTool(tipo, Color.BLACK, titulo1, FontWeight.BOLD);
        cabecera.getChildren().add(cabeceraTexto);
        
        Pane paneError = new Pane();
        Pane mensajeError = new Pane(); 
        
        int mitadVentanaActiva = (anchoVentana - reduccionx) / 2 - 25;
        
        HBox primero = new HBox(20);
        TextFieldTool textFieldCliente = new TextFieldTool(cabeceraField ,titulo2, Pos.CENTER, mitadVentanaActiva, 40);
        BotonTool botonBuscar = new BotonTool("Buscar", titulo2 - 1, 90, 40, ColorOscuro);
        botonBuscar.setOnMousePressed(buscar -> {
            paneError.getChildren().clear();
            if(!textFieldCliente.isEmplyTool()){
                Cliente cli = ctrCaja.selectRetornarCliente((String) textFieldCliente.getValue());
                if(cli != null){
                    establecerTrasladoMascotas(anchoVentana - reduccionx - 35, cli);
                } else 
                    paneError.getChildren().add(new BoxTextTool("Cliente no existente", Color.RED, 10, FontWeight.NORMAL));
            } else 
                paneError.getChildren().add(new BoxTextTool("Ingrese la cedula del cliente correctamente", Color.RED, 10, FontWeight.NORMAL));
        });
        primero.getChildren().addAll(textFieldCliente, botonBuscar);
        
        paneIngresoDatos.getChildren().addAll(cabecera, primero, mensajeError);
        
        pane1.getChildren().add(paneIngresoDatos);
    }
    
    public void establecerDatosAdicionales(String tipo, String cabeceraField){
        this.tipo = tipo;
        this.cabeceraField = cabeceraField;
    }
    
    private void establecerTrasladoMascotas(int ancho, Cliente cliente){
        //ComBoxTool escogerRuta = new ComBoxTool(250, "Ruta:", new ArrayList<>(), titulo2);
        //ComBoxTool escogerRepartidor = new ComBoxTool(250, "Repartidor:", new ArrayList<>(), titulo2);
        
        //toolUsados.add(escogerRuta);
        //toolUsados.add(escogerRepartidor);
        
        BoxTextTool cabeceraTexto = new BoxTextTool("Mascotas del usuario: " + cliente.getCedula(), Color.BLACK, titulo2, FontWeight.BOLD);
        
        List<String> datosMascotas = new ArrayList<>();
        datosMascotas.add("Codigo");datosMascotas.add("Nombre");
        datosMascotas.add("Raza");
        datosMascotas.add("Estado");
        datosMascotas.add("Accion");
        
        tableMascotas = new TableTool(ancho, datosMascotas, "No hay mascotas disponibles",titulo2);
        
        mascotasCliente = ctrCaja.selectMascotasCliente(cliente);
        
        anadirMascotasTabla();
        
        paneIngresoDatos.getChildren().addAll(cabeceraTexto, tableMascotas);
    }
    
    private void anadirMascotasTabla(){
        tableMascotas.limpiarContenido();
        
        ListIterator<Mascota> it = mascotasCliente.listIterator();
        
        while(it.hasNext()){
            Mascota mas = it.next();
            BotonTool accionBoton;
            
            switch (mas.getEstado()) {
                case "Translado a sucursal":
                    accionBoton = new BotonTool("Finalizar Traslado", titulo2 - 1, 200, 40, ColorOscuro);
                    accionBoton.setOnMousePressed(cambiarEstado -> {
                        cambiarEstado(mas, "Sucursal");
                    });
                    break;
                case "Translado a domicilio":
                    accionBoton = new BotonTool("Finalizar Traslado", titulo2 - 1, 200, 40, ColorOscuro);
                    accionBoton.setOnMousePressed(cambiarEstado -> {
                        cambiarEstado(mas, "Domicilio");
                    });
                    break;
                case "Sucursal":
                    accionBoton = new BotonTool("Transladar domicilio", titulo2 - 1, 200, 40, Color.BLUE);
                    accionBoton.setOnMousePressed(cambiarEstado -> {
                        cambiarEstado(mas, "Translado a domicilio");
                    });
                    break;
                case "Domicilio":
                    accionBoton = new BotonTool("Transladar sucursal", titulo2 - 1, 200, 40, Color.GREEN);
                    accionBoton.setOnMousePressed(cambiarEstado -> {
                        cambiarEstado(mas, "Translado a sucursal");
                    });
                    break;
                default:
                    accionBoton = new BotonTool("Error", titulo2 , 200, 40, Color.RED);
                    break;
            }
            tableMascotas.anadirItem(mas.retornarAllData(), accionBoton);
        }
    }
    
    private void cambiarEstado(Mascota mas, String nuevoEstado){
        paneIngresoDatos.getChildren().remove(error);
        
        if(ctrCaja.setEstadoMascota(mas)){
            mas.setEstado(nuevoEstado);
            anadirMascotasTabla();
        } else 
            paneIngresoDatos.getChildren().add(error);
        
    }
}
