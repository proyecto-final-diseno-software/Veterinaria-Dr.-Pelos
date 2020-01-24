/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tool;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author ADMIN
 */
public class CheckQuestion  extends StackPane implements Tool{
    private VBox paneCentral;
    private HBox paneRespuestas;
    private RadioButton opcionSi;
    private RadioButton opcionNo;
    private RadioButton opcionNA;
    private ToggleGroup respuesta;
    
    private String pregunta;

    public CheckQuestion(String pregunta, int tamano) {
        paneCentral = new VBox(10);
        paneRespuestas = new HBox(30);
        this.pregunta = pregunta;
        
        //BoxText mensajePregunta = new BoxText(pregunta, tamano, Color.rgb(0, 0, 0), FontWeight.NORMAL, Pos.CENTER_LEFT,0 , 0, null);
        
        respuesta = new ToggleGroup();
        opcionSi = new RadioButton("Si");
        opcionNo = new RadioButton("No");
        opcionNA = new RadioButton("N/A");
        
        opcionSi.setToggleGroup(respuesta);
        opcionNo.setToggleGroup(respuesta);
        opcionNA.setToggleGroup(respuesta);
        
        paneRespuestas.getChildren().addAll(opcionSi, opcionNo, opcionNA);
       
        //paneCentral.getChildren().addAll(mensajePregunta, paneRespuestas);
        
        getChildren().addAll(paneCentral);
    }
    
    public CheckQuestion(String pregunta, int tamano, String res) {
        paneCentral = new VBox(10);
        paneRespuestas = new HBox(30);
        this.pregunta = pregunta;
        
        //BoxText mensajePregunta = new BoxText(pregunta, tamano, Color.rgb(0, 0, 0), FontWeight.NORMAL, Pos.CENTER_LEFT,0 , 0, null);
        
        respuesta = new ToggleGroup();
        opcionSi = new RadioButton("Si");
        opcionNo = new RadioButton("No");
        opcionNA = new RadioButton("N/A");
        
        opcionSi.setToggleGroup(respuesta);
        opcionNo.setToggleGroup(respuesta);
        opcionNA.setToggleGroup(respuesta);
        
        if(res.equals("Si"))
            respuesta.selectToggle(opcionSi);
        else if(res.equals("No"))
            respuesta.selectToggle(opcionNo);
        else
            respuesta.selectToggle(opcionNA);
        
        opcionSi.setDisable(true);
        opcionNo.setDisable(true);
        opcionNA.setDisable(true);
        
        paneRespuestas.getChildren().addAll(opcionSi, opcionNo, opcionNA);
       
        //paneCentral.getChildren().addAll(mensajePregunta, paneRespuestas);
        
        getChildren().addAll(paneCentral);
    }
    
    public boolean isContestada(){
        if(opcionSi.isSelected() || opcionNA.isSelected() || opcionNo.isSelected())
            return true;
        return false;
    }
    
    public void clearSelection(){
        respuesta.selectToggle(null);
    }

    public String getPregunta() {
        return pregunta;
    }

    @Override
    public boolean isEmplyTool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue() {
        if(opcionSi.isSelected())
            return "Si";
        else if(opcionNo.isSelected())
            return "No";
        return "No aplica";
    }

    @Override
    public void limpiarTool() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
