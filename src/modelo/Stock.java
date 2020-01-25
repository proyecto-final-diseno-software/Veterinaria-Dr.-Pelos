/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author paula
 */
public class Stock {
    private int idStock;
    private int stock;
    private Producto producto;
    private Sucursal sucur;

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Sucursal getSucur() {
        return sucur;
    }

    public void setSucur(Sucursal sucur) {
        this.sucur = sucur;
    }
    
    public void actualizarStock(){
        //En construccion
    }
}
