/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author ADMIN
 */
public class BotonTool extends StackPane implements Tool{
    private Text text;
    private Text simbolo;
    private boolean presionado;
    private Rectangle bg;
    private String tipoLetra = "Arial";
    
    public BotonTool(String texto, int tamanoLetra, int base,int altura, Color color){
        
        text = new Text(texto);
        text.setFont(text.getFont().font(tipoLetra ,FontWeight.BOLD, tamanoLetra));
        text.setFill(Color.WHITE);
            
        bg = new Rectangle(base, altura);
        bg.setOpacity(0.8);
        bg.setFill(color);
        bg.setEffect(new GaussianBlur(1));
        bg.setArcWidth(10);
        bg.setArcHeight(10);
            
        setOnMouseEntered(event -> bg.setOpacity(1));
            
        setOnMouseExited(event -> bg.setOpacity(0.8));
        
        setAlignment(Pos.CENTER);
        getChildren().addAll(bg,text);
    }
        
        public BotonTool(String texto, int tamanoLetra, int base, int altura, boolean desplegable){
            text = new Text(texto);
            text.setFont(text.getFont().font(tipoLetra , tamanoLetra));
            text.setFill(Color.rgb(255, 255, 255));
            text.setTranslateX(20);
            
            if(desplegable){
                simbolo = new Text("<");
                simbolo.setFont(text.getFont().font(tipoLetra, tamanoLetra));
                simbolo.setFill(Color.rgb(255, 255, 255));
                simbolo.setTranslateX(base - 30);
                getChildren().add(simbolo);
            }
            
            bg = new Rectangle(base, altura);
            bg.setOpacity(0);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(1));
            bg.setArcWidth(5);
            bg.setArcHeight(5);
            
            setAlignment(Pos.CENTER_LEFT);
            getChildren().addAll(bg, text);
            
            setOnMouseEntered(event -> bg.setOpacity(0.2));
            
            setOnMouseExited(event -> {
                if(!presionado)
                    bg.setOpacity(0);
            });
            
            setOnMousePressed(event -> {});
            
            setOnMouseReleased(event -> setEffect(null));
        }
        
    public void setText(String text){
        this.text.setText(text);
    }
    
    public void marcar(){
        if(!presionado){
            if(simbolo != null)
                simbolo.setText("v");
            bg.setOpacity(0.2);
        }
        presionado = true;
    }
    
    public void desmarcar(){
        if(presionado){
            bg.setOpacity(0);
            if(simbolo != null)
                simbolo.setText("<");
        }
        presionado = false;
    }

    public boolean isPresionado() {
        return presionado;
    }

    @Override
    public boolean isEmplyTool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiarTool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
