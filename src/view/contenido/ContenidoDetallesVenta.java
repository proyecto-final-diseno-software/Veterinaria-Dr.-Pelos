/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contenido;

import java.util.List;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.Detalle_Venta;
import modelo.Venta;
import view.tool.Tool;

/**
 *
 * @author ADMIN
 */
public class ContenidoDetallesVenta extends StackPane implements ContenidoCentral{
    private Venta venta;
    private List<Detalle_Venta> itemsCarrito;
    
    protected int anchoVentana;
    protected int altoVentana;
        
    protected FlowPane pane1;
    protected Pane paneFondo;
    
    protected int titulo3;
    protected int titulo2;
    protected int titulo1;
    protected Color ColorOscuro;
    protected Color colorClaro;

    public ContenidoDetallesVenta(Venta venta, List<Detalle_Venta> itemsCarrito, int anchoVentana, int altoVentana, FlowPane pane1, Pane paneFondo, int titulo3, int titulo2, int titulo1, Color ColorOscuro, Color colorClaro) {
        this.venta = venta;
        this.itemsCarrito = itemsCarrito;
        this.anchoVentana = anchoVentana;
        this.altoVentana = altoVentana;
        this.pane1 = pane1;
        this.paneFondo = paneFondo;
        this.titulo3 = titulo3;
        this.titulo2 = titulo2;
        this.titulo1 = titulo1;
        this.ColorOscuro = ColorOscuro;
        this.colorClaro = colorClaro;
    }

    @Override
    public void crearContenidoCentral(List<Tool> toolUsados) {
        Rectangle bg = new Rectangle(anchoVentana, altoVentana);
        bg.setFill(Color.WHITE);
        bg.setOpacity(0.5);
        
        
        
        getChildren().addAll(bg);
    }
    
    
}
