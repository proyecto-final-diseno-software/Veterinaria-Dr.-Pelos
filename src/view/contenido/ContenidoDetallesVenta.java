/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import controladores.Ctr_Personal_Caja;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
import modelo.Cliente;
import modelo.Cotizacion;
import modelo.Detalle_Venta;
import modelo.Documento;
import modelo.Efectivo;
import modelo.Forma_pago;
import modelo.PayPal;
import modelo.Personal_Caja;
import modelo.Tarjeta;
import modelo.Venta;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.ComBoxTool;
import view.tool.TextFieldTool;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoDetallesVenta extends Contenido implements ContenidoCentral{
    private Documento documento;
    private Documento documentoCotizacion;
    
    private HBox paneDatosPago;
    
    private List<Tool> toolUsados;
    
    private Forma_pago formaPago;
    
    private Ctr_Personal_Caja ctr;
    
    public ContenidoDetallesVenta(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior, Documento documento){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        
        this.documento = documento;
        
        this.colunma1 = new VBox(20);
        this.setTranslateX(20);
        
        this.paneFondo = new Pane();
        this.paneFondo.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-20))));
        this.paneFondo.setTranslateX((anchoVentana - reduccionX + 20)/2 - 150);
        this.paneFondo.setTranslateY((altoVentana - reduccionY + 20)/2 - 150);
        
        this.paneDatosPago = new HBox(5);
        
        this.toolUsados = new ArrayList<>();
        
        this.ctr = new Ctr_Personal_Caja();
    }
    
    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        Rectangle bg = new Rectangle(anchoVentana - reduccionX + 20, altoVentana - reduccionY + 20);
        bg.setFill(Color.WHITE);
        bg.setOpacity(0.5);
        bg.setTranslateY(-10);
        
        HBox cabecera = new HBox(5);
        
        BoxTextTool cabeceraTexto = new BoxTextTool("Confirmar Metodo Pago:", Color.BLACK, titulo1, FontWeight.BOLD);
        cabecera.getChildren().add(cabeceraTexto);
        
        List<String> listaTipoPago = new ArrayList<>();
        listaTipoPago.add("Efectivo");
        listaTipoPago.add("Targeta");
        listaTipoPago.add("PayPal");
        listaTipoPago.add("Cotizacion");
                
        HBox primero = new HBox(5);
        ComBoxTool comboTipoPago = new ComBoxTool(150, "Tipo pago:" , listaTipoPago, titulo2);
        comboTipoPago.getCombo().setOnAction(cambiarMetodoPago -> cambiarMetodoPago(comboTipoPago));
        toolUsados.add(comboTipoPago);
        
        primero.getChildren().addAll(comboTipoPago, paneDatosPago);
        
        BotonTool botonCancelar = new BotonTool("Cancelar", titulo2 - 1, 200, 40, Color.RED);
        
        BotonTool botonConfirmar = new BotonTool("Confirmar", titulo2 - 1, 200, 40, this.colorClaro);
        
        botonConfirmar.setOnMousePressed(confirmaVenta -> {
            
            if(documento instanceof Venta){
                //documento.
                if(ctr.insertVenta((Venta) documento))
                    guardarDetallesVenta(documento.getCarrito());
                else{
                    //paneError.getChildren().add(new BoxTextTool("\nError al registrar venta", Color.RED, titulo2, FontWeight.NORMAL));   
                }
            } else {
                if(ctr.insertCotizacion((Cotizacion) documento))
                    guardarDetallesVenta(documento.getCarrito());
                else{
                    //paneError.getChildren().add(new BoxTextTool("\nError al registrar venta", Color.RED, titulo2, FontWeight.NORMAL));  
                }
            }
        });
        
        colunma1.getChildren().addAll(cabecera, primero);
        paneFondo.getChildren().addAll(bg, colunma1);
        
        getChildren().add(paneFondo);
    }
    
    private void cambiarMetodoPago(ComBoxTool combo){
        this.toolUsados.clear();
        this.toolUsados.add(combo);
        
        paneDatosPago.getChildren().clear();
        
        switch((String) combo.getValue()){
            case "Efectivo":
                formaPago = new Efectivo();
            break;
            
            case "Targeta":
                TextFieldTool num_cuenta = new TextFieldTool("Ingrese numero cuenta", "Numero cuenta:", titulo2, Pos.CENTER_LEFT, 200, titulo2);
                this.toolUsados.add(num_cuenta);
                paneDatosPago.getChildren().add(num_cuenta);
                formaPago = new Tarjeta();
            break;
            
            case "PayPal":
                TextFieldTool correoElectronico = new TextFieldTool("Ingrese el correo electronico", "Correo Electronico:", titulo2, Pos.CENTER_LEFT, 200, titulo2);
                this.toolUsados.add(correoElectronico);
                paneDatosPago.getChildren().add(correoElectronico);
                formaPago = new PayPal();
                
            case "Cotizacion":
                if(documento instanceof Venta)
                    tranformarCotizacion();
                formaPago = new PayPal();
            break;
        }
    }
    
    public void anadirBotones(BotonTool botonAceptar,BotonTool botonEliminar){
        HBox panebotones = new HBox(5);
        panebotones.getChildren().addAll(botonAceptar, botonEliminar);
        colunma1.getChildren().add(panebotones);
    }

    @Override
    public void establecerPaneles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void tranformarCotizacion(){
        documentoCotizacion = new Cotizacion(((Venta) documento).getSubtotal(), documento.getFecha(), documento.getNumeroFactura(), documento.getPersonalCaja(), documento.getCliente(), documento.getCarrito());
    }
    
    private void guardarDetallesVenta(List<Detalle_Venta> itemsCarrito){
        Iterator<Detalle_Venta> it = itemsCarrito.iterator();
        
        while(it.hasNext()){
            Detalle_Venta det = it.next();
            ctr.guardarDetalleVenta(det);
        }
    }
    
}
