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
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paula
 */
public class Cotizacion extends Documento{
    private double valor;

    public Cotizacion() {}

    public Cotizacion(double valor, LocalDate fecha, int numeroFactura, PersonalCaja personalCaja, Cliente cliente, List<DetalleVenta> carrito) {
        super(fecha, numeroFactura, personalCaja, cliente, carrito);
        this.valor = valor;
    }
    
    
    public void agregarDetalle(DetalleVenta detalle){
        detalle.setDocumento(this);
        valor += detalle.calcularPrecio();
        carrito.add(detalle);
    }
    
    public void generarCotizacion(){
        String ruta = "res/cotizaciones/cotizacion" + this.idDocumento+".txt";
        File archivo = new File(ruta);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write(cotizacionEncabezado());
            bw.write(productosCotizacion());
        } catch (IOException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private String cotizacionEncabezado(){
        return "\t*VETERINARIA DR. PELOS*\t\t\n"+
                "\t  **COTIZACION**\n"+
                String.format("Fecha de emision: %tF \n",this.getFecha() )+
                "\tRUC:0987654321 \n\tGUAYAQUIL-ECUADOR\n"+
                "Cliente   : "+this.cliente.getNombre()+" "+ this.cliente.getApellido()+
                "\nCED/RUC  : "+ this.cliente.getCedula()+
                "\nTelefono : "+ this.cliente.getNumTelefonico()+
                "\n-----------------------------------------------\n"+
                String.format("|%-18s|", "Producto")+String.format("|%-8s|", "Cantidad")+String.format("|%-8s|\n", "Pre.Total")+
                "-----------------------------------------------\n";
    }
    private String productosCotizacion(){
        java.util.Iterator<DetalleVenta> it = this.getCarrito().listIterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()){
            DetalleVenta d = it.next();
            sb.append(String.format("%-20s %-10s %-10s\n",d.getNombre(),d.getCantidad(),d.calcularPrecio()));
        }
        sb.append(String.format("\t Total : %-10s",this.getValor()));

        return sb.toString();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public void calcularMonto(List<DetalleVenta> itemsCarrito) {
        this.carrito = itemsCarrito;
        
        java.util.Iterator<DetalleVenta> it = itemsCarrito.iterator();
        
        double monto = 0;
        
        while(it.hasNext()){
            DetalleVenta det = it.next();
            det.setDocumento(this);
            monto += det.calcularPrecio();
        }
        
        this.setValor(monto);
    }

    @Override
    public boolean comprobarValides() {
        if(this.fecha == null)
            return false;
        if(this.numeroFactura == 0)
            return false;
        if(this.valor == 0)
            return false;
        if(this.personalCaja == null)
            return false;
        if(this.cliente == null)
            return false;
        
        return this.personalCaja != null;
    }
}
