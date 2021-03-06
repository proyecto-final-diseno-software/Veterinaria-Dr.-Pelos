/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import controladores.CtrPersonalCaja;
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
import modelo.DetalleVenta;
import modelo.DetalleVentaProducto;
import modelo.DetalleVentaServicio;
import modelo.Documento;
import modelo.Efectivo;
import modelo.PayPal;
import modelo.Pedido;
import modelo.Ruta;
import modelo.Tarjeta;
import modelo.Venta;
import view.tool.BotonTool;
import view.tool.BoxTextTool;
import view.tool.ComBoxTool;
import view.tool.TableTool;
import view.tool.TextFieldTool;
import view.tool.Tool;
import modelo.FormaPago;

/**
 *
 * @author ADMIN
 */
public class ContenidoDetallesVenta extends Contenido implements ContenidoCentral{
    private Documento documento;
    private Documento docuementoRespaldo;
    
    private List<Tool> toolUsados;
    
    private FormaPago formaPago;
    
    private CtrPersonalCaja ctr;
    
    private HBox paneDatosPago;
    private HBox adomiciolio;
    
    private ContenidoVentas parentPerteneciente;
    
    private double montoExtraEnvio;
    
    public ContenidoDetallesVenta(int reduccionx, int reduccionY, int anchoVentana, int altoVentana, int anchoColunma1, int anchoColunma2, int anchoLateral, int altoSuperior, Documento documento, ContenidoVentas parentPerteneciente){
        super(reduccionx, reduccionY, anchoVentana, altoVentana, anchoColunma1, anchoColunma2, anchoLateral, altoSuperior);
        
        this.documento = documento;
        
        this.colunma1 = new VBox(20);
        this.colunma1.setTranslateX(200);
        this.colunma1.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), new Insets(-20))));
        
        this.paneFondo = new Pane();
        this.parentPerteneciente = parentPerteneciente;
        this.paneDatosPago = new HBox(5);
        
        this.toolUsados = new ArrayList<>();
        
        this.ctr = new CtrPersonalCaja();
        
        docuementoRespaldo = new Cotizacion(((Venta) documento).getSubtotal(), documento.getFecha(), documento.getNumeroFactura(), documento.getPersonalCaja(), documento.getCliente(), documento.getCarrito());
    
        this.montoExtraEnvio = ((double) 2 * documento.getCarrito().size());
    }
    
    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        this.toolUsados = toolUsados;
        
        Rectangle bg = new Rectangle(anchoVentana - reduccionX + 20, altoVentana - reduccionY + 20);
        bg.setFill(Color.WHITE);
        bg.setOpacity(0.7);
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
        
        List<String> listaAdomicilio = new ArrayList<>();
        listaAdomicilio.add("Si");
        listaAdomicilio.add("No");
        
        adomiciolio = new HBox(5);
        ComBoxTool comboDomicilio = new ComBoxTool(150, "¿Servicio a domicilio?" , listaAdomicilio, titulo2);
        BoxTextTool cajaMentoExtra = new BoxTextTool("Monto extra "+ montoExtraEnvio +"$", Color.BLACK, titulo2, FontWeight.NORMAL);
        cajaMentoExtra.setVisible(false);
        comboDomicilio.getCombo().setOnAction(domicilio -> {
            if(documento instanceof Venta)
                cambiarEnvio(comboDomicilio, cajaMentoExtra);
        });
        
        adomiciolio.getChildren().addAll(comboDomicilio,cajaMentoExtra);
        
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
        
        HBox paneBotones = new HBox(5);
        BotonTool botonCancelar = new BotonTool("Cancelar", titulo2 - 1, 150, 40, Color.RED);
        botonCancelar.setOnMousePressed(cerrar -> parentPerteneciente.getPaneFondo().getChildren().remove(this));
        
        BotonTool botonConfirmar = new BotonTool("Confirmar", titulo2 - 1, 150, 40, this.colorClaro);
        paneBotones.getChildren().addAll(botonCancelar, botonConfirmar);
        
        botonConfirmar.setOnMousePressed(confirmaVenta -> comprobarValides(comboDomicilio));
        
        colunma1.getChildren().addAll(cabecera, primero, adomiciolio, tablaRegistros, paneBotones);
        paneFondo.getChildren().addAll(bg, colunma1);
        
        getChildren().add(paneFondo);
    }
    
    private void comprobarValides(ComBoxTool comboDomicilio){
        if(this.comprobarCampos(this.toolUsados)){
                if(documento instanceof Venta){
                    if(!comboDomicilio.isEmplyTool()){
                        ((Venta) documento).sumarExtra();

                        if(crearFormaPago()){
                            ((Venta) documento).setFormaPagoID(formaPago);

                            if(ctr.insertVenta((Venta) documento)){
                                ((Venta) documento).generarFactura();
                                parentPerteneciente.getPaneFondo().getChildren().remove(this);
                                parentPerteneciente.limpirarContenido();
                                guardarDetallesVenta(documento.getCarrito());
                            }
                        }
                    }
                } else {
                    documento.actualizarReferencias();
                    if(ctr.insertCotizacion((Cotizacion) documento)){
                        ((Cotizacion) documento).generarCotizacion();
                        parentPerteneciente.getPaneFondo().getChildren().remove(this);
                        parentPerteneciente.limpirarContenido();
                        guardarDetallesVenta(documento.getCarrito());
                    }
                }
            }
    }
    
    private void cambiarMetodoPago(ComBoxTool combo){
        this.toolUsados.clear();
        this.toolUsados.add(combo);
        
        paneDatosPago.getChildren().clear();
        
        switch((String) combo.getValue()){
            case "Efectivo":
                adomiciolio.setVisible(true);
                if(documento instanceof Cotizacion)
                    inventirRefrencias();
                formaPago = new Efectivo();
            break;
            
            case "Targeta":
                adomiciolio.setVisible(true);
                if(documento instanceof Cotizacion)
                    inventirRefrencias();
                TextFieldTool numCuenta = new TextFieldTool("Ingrese numero cuenta", "Numero cuenta:", titulo2, Pos.CENTER_LEFT, 400, titulo2);
                this.toolUsados.add(numCuenta);
                paneDatosPago.getChildren().add(numCuenta);
                formaPago = new Tarjeta();
            break;
            
            case "PayPal":
                adomiciolio.setVisible(true);
                if(documento instanceof Cotizacion)
                    inventirRefrencias();
                TextFieldTool correoElectronico = new TextFieldTool("Ingrese el correo electronico", "Correo Electronico:", titulo2, Pos.CENTER_LEFT, 400, titulo2);
                this.toolUsados.add(correoElectronico);
                paneDatosPago.getChildren().add(correoElectronico);
                formaPago = new PayPal();
            break; 
               
            default:
                adomiciolio.setVisible(false);
                if(documento instanceof Venta)
                    inventirRefrencias();
            break;
        }
    }
    
    private void cambiarEnvio(ComBoxTool combo, BoxTextTool text){
        switch((String) combo.getValue()){
            case "Si":
                text.setVisible(true);
                ((Venta) documento).setMontoExtraEnvio(montoExtraEnvio);
                ((Venta) documento).setPedido(new Pedido("Entrega cliente:" + documento.getCliente().getCedula(),documento.getPersonalCaja().getSucursal(), documento.getCliente(), new Ruta(documento.getCliente().getDireccion())));
            break;
            
            default:
                text.setVisible(false);
                ((Venta) documento).setPedido(null);
                ((Venta) documento).setMontoExtraEnvio(0);
            break;
        }
    }
    
    private boolean crearFormaPago(){
        String nmbre =  " A nombre de ";
        if(this.comprobarCampos(this.toolUsados)){
            if(formaPago instanceof Efectivo){
                ((Efectivo) formaPago).setCantidad_efectivo(((Venta) documento).getTotal());
                ((Efectivo) formaPago).setDescripcion("Se pago en efecto: " + ((Venta) documento).getTotal() + nmbre + documento.getCliente().getCedula());
                ((Efectivo) formaPago).setImpuesto(0.12f);
                return true;
            } else if(formaPago instanceof Tarjeta){
                ((Tarjeta) formaPago).setNumCuenta((String) toolUsados.get(1).getValue());
                ((Tarjeta) formaPago).setDescripcion("Se pago con targeta: " + ((Tarjeta) formaPago).getNumCuenta() + nmbre + documento.getCliente().getCedula());
                ((Tarjeta) formaPago).setImpuesto(0.12f);
                return true;
            } else if(formaPago instanceof PayPal){
                ((PayPal) formaPago).setCorreoElectronico((String) toolUsados.get(1).getValue());
                ((PayPal) formaPago).setDescripcion("Se pago con el correo: " + ((PayPal) formaPago).getCorreoElectronico() + nmbre + documento.getCliente().getCedula());
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
        Iterator<DetalleVenta> it = this.documento.getCarrito().iterator();
        
        while(it.hasNext()){
            List<String> datosPorDetalle = new ArrayList<>();
            
            DetalleVenta tempDet = it.next();
            
            String codigo = "";
            String precioUnitario = "";
            String precioTotal = "";
            
            if(tempDet instanceof DetalleVentaProducto){
                codigo = Integer.toString(((DetalleVentaProducto) tempDet).getProducto().getIdProducto());
                precioUnitario = Double.toString(((DetalleVentaProducto) tempDet).getProducto().getPrecioUnitario());
                precioTotal = Double.toString(((DetalleVentaProducto) tempDet).getProducto().getPrecioUnitario() * tempDet.getCantidad());
            }else{
                codigo = Integer.toString(((DetalleVentaServicio) tempDet).getServicio().getIdServicio());
                precioUnitario = Double.toString(((DetalleVentaServicio) tempDet).getServicio().getPrecio());
                precioTotal = Double.toString(((DetalleVentaServicio) tempDet).getServicio().getPrecio() * tempDet.getCantidad());
            }
            
            String cantidad = Integer.toString(tempDet.getCantidad());
            
            datosPorDetalle.add(codigo);
            datosPorDetalle.add(cantidad);
            datosPorDetalle.add(precioUnitario);
            datosPorDetalle.add(precioTotal);
            
            table.anadirItem(datosPorDetalle, null);
        }
    }
    
    private void guardarDetallesVenta(List<DetalleVenta> itemsCarrito){
        Iterator<DetalleVenta> it = itemsCarrito.iterator();
        
        while(it.hasNext()){
            DetalleVenta det = it.next();
            ctr.guardarDetalleVenta(det);
        }
    }

    @Override
    public void limpirarContenido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
