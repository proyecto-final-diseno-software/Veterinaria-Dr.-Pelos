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
    private Forma_pago forma_pago_ID;
    
    private double montoExtraEnvio;
    private Pedido pedido;
    
    @Override
    public void calcularMonto(List<Detalle_Venta> itemsCarrito){
        this.carrito = itemsCarrito;
        
        Iterator<Detalle_Venta> it = itemsCarrito.iterator();
        
        double monto = 0;
        
        while(it.hasNext()){
            Detalle_Venta det = it.next();
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
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(facturaEncabezado());
            bw.write(productosFactura());
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    private String facturaEncabezado(){
        String s = "\t*VETERINARIA DR. PELOS*\t\t\nNro. Factura: "+this.numeroFactura+
                String.format("\nFecha de emision: %tF \n",this.getFecha() )+
                "\tRUC:0987654321 \n\tGUAYAQUIL-ECUADOR\n"+
                "Cliente   : "+ this.cliente.getNombre()+" "+ this.cliente.getApellido()+
                "\nCED/RUC  : "+ this.cliente.getCedula()+
                "\nTelefono : "+ this.cliente.getNum_telefonico()+
                "\n-----------------------------------------------\n"+
                String.format("|%-18s|", "Producto")+String.format("|%-8s|", "Cantidad")+String.format("|%-8s|\n", "Pre.Total")+
                "-----------------------------------------------\n"
                ;
        return s;
    }
    private String productosFactura(){
        Iterator<Detalle_Venta> it = this.getCarrito().listIterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()){
            Detalle_Venta d = it.next();
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

    public Forma_pago getForma_pago_ID() {
        return forma_pago_ID;
    }

    public void setForma_pago_ID(Forma_pago forma_pago_ID) {
        this.forma_pago_ID = forma_pago_ID;
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
    
    
}
