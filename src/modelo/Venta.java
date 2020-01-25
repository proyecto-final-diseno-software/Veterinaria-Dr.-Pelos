/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paula
 */
public class Venta extends Documento{
    private double subtotal;
    private double total;
    private double descuento;
    private FormaPago formaPagoID;
    
    private double montoExtraEnvio;
    private Pedido pedido;
    
    @Override
    public void calcularMonto(List<DetalleVenta> itemsCarrito){
        this.carrito = itemsCarrito;
        
        Iterator<DetalleVenta> it = itemsCarrito.iterator();
        
        double monto = 0;
        
        while(it.hasNext()){
            DetalleVenta det = it.next();
            det.setDocumento(this);
            monto += det.calcularPrecio();
        }
        
        this.setSubtotal(monto);
        this.total = subtotal - descuento;
    }
    
    @Override
    public boolean comprobarValides(){
        if(this.fecha == null)
            return false;
        if(this.numeroFactura == 0)
            return false;
        if(this.subtotal == 0)
            return false;
        if(this.total == 0)
            return false;
        if(this.personalCaja == null)
            return false;
        if(this.cliente == null)
            return false;
        
        return this.personalCaja != null;
    }
    
    public void generarFactura(){
        String ruta = "res/facturas/factura" +this.numeroFactura+".txt";
        File archivo = new File(ruta);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(facturaEncabezado());
            bw.write(productosFactura());
        } catch (IOException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private String facturaEncabezado(){
        return "\t*VETERINARIA DR. PELOS*\t\t\nNro. Factura: "+this.numeroFactura+
                String.format("\nFecha de emision: %tF \n",this.getFecha() )+
                "\tRUC:0987654321 \n\tGUAYAQUIL-ECUADOR\n"+
                "Cliente   : "+ this.cliente.getNombre()+" "+ this.cliente.getApellido()+
                "\nCED/RUC  : "+ this.cliente.getCedula()+
                "\nTelefono : "+ this.cliente.getNumTelefonico()+
                "\n-----------------------------------------------\n"+
                String.format("|%-18s|", "Producto")+String.format("|%-8s|", "Cantidad")+String.format("|%-8s|\n", "Pre.Total")+
                "-----------------------------------------------\n"
                ;
    }
    private String productosFactura(){
        Iterator<DetalleVenta> it = this.getCarrito().listIterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()){
            DetalleVenta d = it.next();
            sb.append(String.format("%-20s %-10s %-10s\n",d.getNombre(),d.getCantidad(),d.calcularPrecio()));
        }
        sb.append(String.format("\t Subtotal : %-10s",this.subtotal));
        sb.append(String.format("\t Descuento: %-10s",this.descuento));
        sb.append(String.format("\n\t Total    : %-10s",this.total));

        return sb.toString();
    }
    
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public FormaPago getFormaPagoID() {
        return formaPagoID;
    }

    public void setFormaPagoID(FormaPago formaPagoID) {
        this.formaPagoID = formaPagoID;
    }

    public double getMontoExtraEnvio() {
        return montoExtraEnvio;
    }

    public void setMontoExtraEnvio(double montoExtraEnvio) {
        this.montoExtraEnvio = montoExtraEnvio;
    }
    
    public void sumarExtra(){
        setTotal(this.total + this.montoExtraEnvio);
    }
    
    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    
    
    
    
}
