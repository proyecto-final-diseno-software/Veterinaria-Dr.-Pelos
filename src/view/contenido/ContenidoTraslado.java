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
import modelo.Cliente;
import modelo.Mascota;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
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
    
    private BoxTextTool datoCliente;
    private TableTool tableMascotas;
    
    private BoxTextTool error;
    
    private String tipo;
    private String cabeceraField;
    
    public ContenidoTraslado(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        
        this.error = new BoxTextTool("Hubo un error con el estado de esta mascota", Color.RED, titulo2, FontWeight.BOLD);
        this.ctrCaja = new Ctr_Personal_Caja();
        
        this.toolUsados = new ArrayList<>();
        this.colunma1 = new VBox(20);
        
        this.pane1 = new FlowPane();
        this.pane1.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-10, 0, -20, 0))));
        this.pane1.setPrefWrapLength(anchoVentana - reduccionx);
    }
    
    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        colunma1.setTranslateX(10);
        
        List<String> datosMascotas = new ArrayList<>();
        datosMascotas.add("Codigo");datosMascotas.add("Nombre");
        datosMascotas.add("Raza");
        datosMascotas.add("Estado");
        datosMascotas.add("Accion");
        
        datoCliente = new BoxTextTool("Mascotas del usuario: ", Color.BLACK, titulo2, FontWeight.BOLD);
        
        tableMascotas = new TableTool(anchoVentana - reduccionX - 35, datosMascotas, "No hay mascotas disponibles",titulo2);
        
        HBox cabecera = new HBox(5);
        BoxTextTool cabeceraTexto = new BoxTextTool(tipo, Color.BLACK, titulo1, FontWeight.BOLD);
        cabecera.getChildren().add(cabeceraTexto);
        
        Pane paneError = new Pane();
        Pane mensajeError = new Pane(); 
        
        int mitadVentanaActiva = (anchoVentana - reduccionX) / 2 - 25;
        
        HBox primero = new HBox(20);
        TextFieldTool textFieldCliente = new TextFieldTool(cabeceraField ,titulo2, Pos.CENTER, mitadVentanaActiva, 40);
        BotonTool botonBuscar = new BotonTool("Buscar", titulo2 - 1, 90, 40, ColorOscuro);
        botonBuscar.setOnMousePressed(buscar -> {
            paneError.getChildren().clear();
            if(!textFieldCliente.isEmplyTool()){
                Cliente cli = ctrCaja.selectCliente((String) textFieldCliente.getValue()).get(0);
                if(cli != null){
                    establecerTrasladoMascotas(cli);
                } else 
                    paneError.getChildren().add(new BoxTextTool("Cliente no existente", Color.RED, 10, FontWeight.NORMAL));
            } else 
                paneError.getChildren().add(new BoxTextTool("Ingrese la cedula del cliente correctamente", Color.RED, 10, FontWeight.NORMAL));
        });
        primero.getChildren().addAll(textFieldCliente, botonBuscar);
        
        colunma1.getChildren().addAll(cabecera, primero, mensajeError);
        colunma1.getChildren().addAll(datoCliente, tableMascotas);
        
        pane1.getChildren().add(colunma1);
        
        getChildren().add(pane1);
    }
    
    public void establecerDatosAdicionales(String tipo, String cabeceraField){
        this.tipo = tipo;
        this.cabeceraField = cabeceraField;
    }
    
    private void establecerTrasladoMascotas(Cliente cliente){
        //ComBoxTool escogerRuta = new ComBoxTool(250, "Ruta:", new ArrayList<>(), titulo2);
        //ComBoxTool escogerRepartidor = new ComBoxTool(250, "Repartidor:", new ArrayList<>(), titulo2);
        
        //toolUsados.add(escogerRuta);
        //toolUsados.add(escogerRepartidor);
        
        datoCliente.setText("Mascotas del usuario: " + cliente.getCedula());
        mascotasCliente = ctrCaja.selectMascotasCliente(cliente);
        anadirMascotasTabla();
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
        colunma1.getChildren().remove(error);
        
        if(ctrCaja.setEstadoMascota(mas)){
            mas.setEstado(nuevoEstado);
            anadirMascotasTabla();
        } else 
            colunma1.getChildren().add(error);
    }

    @Override
    public void establecerPaneles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
