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
import modelo.Detalle_VentaProducto;
import modelo.Detalle_VentaServicio;
import modelo.Documento;
import modelo.Efectivo;
import modelo.Forma_pago;
import modelo.PayPal;
import modelo.Tarjeta;
import modelo.Venta;
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
public class ContenidoDetallesVenta extends Contenido implements ContenidoCentral{
    private Documento documento;
    private Documento docuementoRespaldo;
    
    private List<Tool> toolUsados;
    
    private Forma_pago formaPago;
    
    private Ctr_Personal_Caja ctr;
    
    private HBox paneDatosPago;
    private HBox paneBotones;
    
    private ContenidoVentas parentPerteneciente;
    
    public ContenidoDetallesVenta(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior, Documento documento, ContenidoVentas parentPerteneciente){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        
        this.documento = documento;
        
        this.colunma1 = new VBox(20);
        this.colunma1.setTranslateX(200);
        this.colunma1.setTranslateY(100);
        this.colunma1.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-20))));
        
        this.paneFondo = new Pane();
        this.parentPerteneciente = parentPerteneciente;
        this.paneDatosPago = new HBox(5);
        
        this.toolUsados = new ArrayList<>();
        
        this.ctr = new Ctr_Personal_Caja();
        
        docuementoRespaldo = new Cotizacion(((Venta) documento).getSubtotal(), documento.getFecha(), documento.getNumeroFactura(), documento.getPersonalCaja(), documento.getCliente(), documento.getCarrito());
    }
    
    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        this.toolUsados = toolUsados;
        
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
        this.toolUsados.add(comboTipoPago);
        
        primero.getChildren().addAll(comboTipoPago, paneDatosPago);
        
        List<String> lista = new ArrayList<>();
        lista.add("CODIGO");
        lista.add("CANT.");
        lista.add("PRECIO UNIT.");
        lista.add("PRECIOTOTAL");
        
        TableTool tablaRegistros = new TableTool(450, lista, "", (titulo3 / 4) * 3);
        anadirItems(tablaRegistros);
        
        List<String> subtotal = new ArrayList<>();
        subtotal.add("");subtotal.add("");subtotal.add("");
        if(documento instanceof Venta)
            subtotal.add(Double.toString(((Venta) documento).getTotal()));
        else
            subtotal.add(Double.toString(((Cotizacion) documento).getValor()));
        tablaRegistros.anadirItem(subtotal, null);
        
        paneBotones = new HBox(5);
        BotonTool botonCancelar = new BotonTool("Cancelar", titulo2 - 1, 150, 40, Color.RED);
        botonCancelar.setOnMousePressed(cerrar -> parentPerteneciente.getPaneFondo().getChildren().remove(this));
        
        BotonTool botonConfirmar = new BotonTool("Confirmar", titulo2 - 1, 150, 40, this.colorClaro);
        paneBotones.getChildren().addAll(botonCancelar, botonConfirmar);
        
        botonConfirmar.setOnMousePressed(confirmaVenta -> {
            if(this.comprobarCampos(this.toolUsados)){
                if(documento instanceof Venta){

                    if(crearFormaPago()){
                        ((Venta) documento).setForma_pago_ID(formaPago);

                        if(ctr.insertVenta((Venta) documento)){
                            guardarDetallesVenta(documento.getCarrito());
                        } else {
                            //paneError.getChildren().add(new BoxTextTool("\nError al registrar venta", Color.RED, titulo2, FontWeight.NORMAL));   
                        }
                    }
                } else {
                    documento.actualizarReferencias();
                    if(ctr.insertCotizacion((Cotizacion) documento))
                        guardarDetallesVenta(documento.getCarrito());
                    else{
                        //paneError.getChildren().add(new BoxTextTool("\nError al registrar venta", Color.RED, titulo2, FontWeight.NORMAL));  
                    }
                }

                parentPerteneciente.getPaneFondo().getChildren().remove(this);
                parentPerteneciente.limpirarContenido();
            }
        });
        
        colunma1.getChildren().addAll(cabecera, primero, tablaRegistros, paneBotones);
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
                TextFieldTool num_cuenta = new TextFieldTool("Ingrese numero cuenta", "Numero cuenta:", titulo2, Pos.CENTER_LEFT, 400, titulo2);
                this.toolUsados.add(num_cuenta);
                paneDatosPago.getChildren().add(num_cuenta);
                formaPago = new Tarjeta();
            break;
            
            case "PayPal":
                if(documento instanceof Cotizacion)
                    inventirRefrencias();
                TextFieldTool correoElectronico = new TextFieldTool("Ingrese el correo electronico", "Correo Electronico:", titulo2, Pos.CENTER_LEFT, 400, titulo2);
                this.toolUsados.add(correoElectronico);
                paneDatosPago.getChildren().add(correoElectronico);
                formaPago = new PayPal();
            break; 
               
            case "Cotizacion":
                if(documento instanceof Venta)
                    inventirRefrencias();
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
                ((Efectivo) formaPago).setDescripcion("Se pago en efecto: " + ((Venta) documento).getTotal() + " A nombre de " + documento.getCliente().getCedula());
                ((Efectivo) formaPago).setImpuesto(0.12f);
                return true;
            } else if(formaPago instanceof Tarjeta){
                ((Tarjeta) formaPago).setNum_cuenta((String) toolUsados.get(1).getValue());
                ((Tarjeta) formaPago).setDescripcion("Se pago con targeta: " + ((Tarjeta) formaPago).getNum_cuenta() + " A nombre de " + documento.getCliente().getCedula());
                ((Tarjeta) formaPago).setImpuesto(0.12f);
                return true;
            } else if(formaPago instanceof PayPal){
                ((PayPal) formaPago).setCorreo_electronico((String) toolUsados.get(1).getValue());
                ((PayPal) formaPago).setDescripcion("Se pago con el correo: " + ((PayPal) formaPago).getCorreo_electronico() + " A nombre de " + documento.getCliente().getCedula());
                ((PayPal) formaPago).setImpuesto(0.12f);
                return true;
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
    
    private void anadirItems(TableTool table){
        Iterator<Detalle_Venta> it = this.documento.getCarrito().iterator();
        
        while(it.hasNext()){
            List<String> datosPorDetalle = new ArrayList<>();
            
            Detalle_Venta tempDet = it.next();
            
            String codigo = "";
            String precioUnitario = "";
            String precioTotal = "";
            
            if(tempDet instanceof Detalle_VentaProducto){
                codigo = Integer.toString(((Detalle_VentaProducto) tempDet).getProducto().getId_producto());
                precioUnitario = Double.toString(((Detalle_VentaProducto) tempDet).getProducto().getPrecioUnitario());
                precioTotal = Double.toString(((Detalle_VentaProducto) tempDet).getProducto().getPrecioUnitario() * tempDet.getCantidad());
            }else{
                codigo = Integer.toString(((Detalle_VentaServicio) tempDet).getServicio().getId_servicio());
                precioUnitario = Double.toString(((Detalle_VentaServicio) tempDet).getServicio().getPrecio());
                precioTotal = Double.toString(((Detalle_VentaServicio) tempDet).getServicio().getPrecio() * tempDet.getCantidad());
            }
            
            String cantidad = Integer.toString(tempDet.getCantidad());
            
            datosPorDetalle.add(codigo);
            datosPorDetalle.add(cantidad);
            datosPorDetalle.add(precioUnitario);
            datosPorDetalle.add(precioTotal);
            
            table.anadirItem(datosPorDetalle, null);
        }
    }
    
    private void guardarDetallesVenta(List<Detalle_Venta> itemsCarrito){
        Iterator<Detalle_Venta> it = itemsCarrito.iterator();
        
        while(it.hasNext()){
            Detalle_Venta det = it.next();
            ctr.guardarDetalleVenta(det);
        }
    }

    @Override
    public void limpirarContenido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
