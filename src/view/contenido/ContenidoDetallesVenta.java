/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import controladores.Ctr_Personal_Caja;
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
import modelo.Cotizacion;
import modelo.Detalle_Venta;
import modelo.Documento;
import modelo.Efectivo;
import modelo.Forma_pago;
import modelo.PayPal;
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
    private Documento docuementoRespaldo;
    
    private HBox paneDatosPago;
    
    private List<Tool> toolUsados;
    
    private Forma_pago formaPago;
    
    private Ctr_Personal_Caja ctr;
    
    public ContenidoDetallesVenta(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior, Documento documento){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        
        this.documento = documento;
        
        this.colunma1 = new VBox(20);
        this.colunma1.setTranslateX((anchoVentana - reduccionX + 20)/2 - 150);
        this.colunma1.setTranslateY((altoVentana - reduccionY + 20)/2 - 150);
        
        this.setTranslateX(20);
        
        this.paneFondo = new Pane();
        this.paneFondo.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-20))));
        
        this.paneDatosPago = new HBox(5);
        
        this.toolUsados = new ArrayList<>();
        
        this.ctr = new Ctr_Personal_Caja();
        
        docuementoRespaldo = new Cotizacion(((Venta) documento).getSubtotal(), documento.getFecha(), documento.getNumeroFactura(), documento.getPersonalCaja(), documento.getCliente(), documento.getCarrito());
    }
    
    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        Rectangle bg = new Rectangle(anchoVentana - reduccionX + 20, altoVentana - reduccionY + 20);
        bg.setFill(Color.WHITE);
        bg.setOpacity(0.1);
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
                
                crearFormaPago();
                ((Venta) documento).setForma_pago_ID(formaPago);
                
                if(ctr.insertVenta((Venta) documento))
                    guardarDetallesVenta(documento.getCarrito());
                else {
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
        
        colunma1.getChildren().addAll(cabecera, primero, botonCancelar, botonConfirmar);
        paneFondo.getChildren().addAll(bg, colunma1);
        
        getChildren().add(paneFondo);
    }
    
    private void cambiarMetodoPago(ComBoxTool combo){
        this.toolUsados.clear();
        this.toolUsados.add(combo);
        
        paneDatosPago.getChildren().clear();
        
        switch((String) combo.getValue()){
            case "Efectivo":
                if(documento instanceof Cotizacion)
                    inventirRefrencias();
                formaPago = new Efectivo();
            break;
            
            case "Targeta":
                if(documento instanceof Cotizacion)
                    inventirRefrencias();
                TextFieldTool num_cuenta = new TextFieldTool("Ingrese numero cuenta", "Numero cuenta:", titulo2, Pos.CENTER_LEFT, 200, titulo2);
                this.toolUsados.add(num_cuenta);
                paneDatosPago.getChildren().add(num_cuenta);
                formaPago = new Tarjeta();
            break;
            
            case "PayPal":
                if(documento instanceof Cotizacion)
                    inventirRefrencias();
                TextFieldTool correoElectronico = new TextFieldTool("Ingrese el correo electronico", "Correo Electronico:", titulo2, Pos.CENTER_LEFT, 200, titulo2);
                this.toolUsados.add(correoElectronico);
                paneDatosPago.getChildren().add(correoElectronico);
                formaPago = new PayPal();
                
            case "Cotizacion":
                if(documento instanceof Venta)
                    inventirRefrencias();
                formaPago = new PayPal();
            break;
        }
    }
    
    public void anadirBotones(BotonTool botonAceptar,BotonTool botonEliminar){
        HBox panebotones = new HBox(5);
        panebotones.getChildren().addAll(botonAceptar, botonEliminar);
        colunma1.getChildren().add(panebotones);
    }
    
    private boolean crearFormaPago(){
        
        if(this.comprobarCampos(this.toolUsados)){
            if(formaPago instanceof Efectivo){
                ((Efectivo) formaPago).setCantidad_efectivo(((Venta) documento).getTotal());
                ((Efectivo) formaPago).setDescripcion("Se pago en efecto:" + ((Venta) documento).getTotal() + "A nombre de " + documento.getCliente().getCedula());
                ((Efectivo) formaPago).setImpuesto(0.12f);
            } else if(formaPago instanceof Tarjeta){
                ((Tarjeta) formaPago).setNum_cuenta((String) toolUsados.get(1).getValue());
                ((Tarjeta) formaPago).setDescripcion("Se pago con targeta:" + ((Tarjeta) formaPago).getNum_cuenta() + "A nombre de " + documento.getCliente().getCedula());
                ((Tarjeta) formaPago).setImpuesto(0.12f);
            } else if(formaPago instanceof PayPal){
                ((PayPal) formaPago).setCorreo_electronico((String) toolUsados.get(1).getValue());
                ((PayPal) formaPago).setDescripcion("Se pago con targeta:" + ((PayPal) formaPago).getCorreo_electronico() + "A nombre de " + documento.getCliente().getCedula());
                ((PayPal) formaPago).setImpuesto(0.12f);
            }
        }
        
        return false;
    }

    @Override
    public void establecerPaneles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void inventirRefrencias(){
        Documento temp = documento;
        documento = docuementoRespaldo;
        docuementoRespaldo = temp;
    }
    
    private void guardarDetallesVenta(List<Detalle_Venta> itemsCarrito){
        Iterator<Detalle_Venta> it = itemsCarrito.iterator();
        
        while(it.hasNext()){
            Detalle_Venta det = it.next();
            ctr.guardarDetalleVenta(det);
        }
    }
    
}
