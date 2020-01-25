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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paula
 */
public class Cotizacion extends Documento{
    private double valor;

    public Cotizacion() {
        this.fecha = LocalDate.now();
        carrito = new LinkedList<>();
    }
    
    public void agregarDetalle(Detalle_Venta detalle){
        detalle.setDocumento(this);
        valor += detalle.calcularPrecio();
        carrito.add(detalle);
    }
    
    public static void generarCotizacion(Cotizacion cotizacion){
        String ruta = "res/cotizaciones/cotizacion" +cotizacion.id_documento+".txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(cotizacionEncabezado(cotizacion));
            bw.write(productosCotizacion(cotizacion));
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private static String cotizacionEncabezado(Cotizacion cotizacion){
        String s = "\t*VETERINARIA DR. PELOS*\t\t\n"+
                "\t  **COTIZACION**\n"+
                String.format("Fecha de emision: %tF \n",cotizacion.getFecha() )+
                "\tRUC:0987654321 \n\tGUAYAQUIL-ECUADOR\n"+
                "Cliente   : "+cotizacion.cliente.getNombre()+" "+ cotizacion.cliente.getApellido()+
                "\nCED/RUC  : "+ cotizacion.cliente.getCedula()+
                "\nTelefono : "+ cotizacion.cliente.getNum_telefonico()+
                "\n-----------------------------------------------\n"+
                String.format("|%-18s|", "Producto")+String.format("|%-8s|", "Cantidad")+String.format("|%-8s|\n", "Pre.Total")+
                "-----------------------------------------------\n";
        return s;
    }
    private static String productosCotizacion(Cotizacion cotizacion){
        java.util.Iterator<Detalle_Venta> it = cotizacion.getDetalles().listIterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()){
            Detalle_Venta d = it.next();
            sb.append(String.format("%-20s %-10s %-10s\n",d.getNombre(),d.getCantidad(),d.calcularPrecio()));
        }
        sb.append(String.format("\t Total : %-10s",cotizacion.getValor()));

        return sb.toString();
    }
    
    
    
//    public void agregarServicio(VentaServicio detalleS){
//        valor += detalleS.precioServicio();
//    }
    
//    private void calcularValorTotal(){
//        
//    }

    public List<Detalle_Venta> getDetalles() {
        return carrito;
    }
    
    public Personal_Caja getPersonal_caja() {
        return personalCaja;
    }

    public void setPersonal_caja(Personal_Caja personal_caja) {
        this.personalCaja = personal_caja;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
