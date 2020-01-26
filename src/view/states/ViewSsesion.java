/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.states;

import controladores.Verification;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import view.tool.BotonTool;
import view.tool.TextFieldTool;

/**
 *
 * @author ADMIN
 */
public class ViewSsesion extends Ventana{
    private Verification verificacion;
    
    @Override
    public void mostrarVentana(Stage primaryStage){
        verificacion = new Verification();
        
        this.root = new Pane();
        this.root.setPrefSize(anchoVentana, altoVentana);
        
        PrincipalContenedor ventana = new PrincipalContenedor();
        
        this.root.getChildren().add(ventana);
        Scene scene = new Scene(this.root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    void cambiarVentana(Pane root) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class PrincipalContenedor extends Parent{
        private VBox contenedor;
        
        public PrincipalContenedor(){
            contenedor = new VBox(15);
            contenedor.setTranslateX(anchoVentana / 2 - anchoVentana / 8);
            contenedor.setTranslateY(anchoVentana / 2 - anchoVentana / 3);
            contenedor.setAlignment(Pos.CENTER);
            
            logo = new ImageView(new Image("util/logo.png"));
            logo.setFitHeight(116);
            logo.setFitWidth(96);
            
            Rectangle fondo = new Rectangle(anchoVentana, altoVentana);
            fondo.setFill(Color.rgb(250, 250, 250));
            
            Rectangle cuadroCentral = new Rectangle(anchoVentana / 3, altoVentana / 2);
            cuadroCentral.setOpacity(0.3);
            cuadroCentral.setFill(colorClaro);
            cuadroCentral.setTranslateX(anchoVentana / 2 - anchoVentana / 6);
            cuadroCentral.setTranslateY(altoVentana / 2 - altoVentana / 4);
            cuadroCentral.setArcWidth(30);
            cuadroCentral.setArcHeight(30);
            
            TextFieldTool entradoUser = new TextFieldTool("Ingrese su usuario" ,20 , Pos.CENTER, anchoVentana / 4, 50);
            
            TextFieldTool entradoPassword = new TextFieldTool("Ingrese su contraseña", 20 , Pos.CENTER, anchoVentana / 4, 50);
            
            BotonTool buttonLogin = new BotonTool("Login", 25, anchoVentana / 4, 45, colorOscuro);
            
            buttonLogin.setOnMousePressed(cambioVentana -> {
                if(!entradoUser.isEmplyTool() && !entradoPassword.isEmplyTool()){
                    switch(verificacion.verificacionDatosSession((String) entradoUser.getValue(),(String) entradoPassword.getValue())){
                        case PERSONAL_CAJA:
                            ViewPersonalCaja newContent = new ViewPersonalCaja((String) entradoUser.getValue());
                            newContent.crearVentana(null, anchoVentana, altoVentana);
                            newContent.cambiarVentana(root);
                            break;
                            
                        case ADMINISTRADOR:
                            ViewAdministrador newContentAdmin = new ViewAdministrador();
                            newContentAdmin.crearVentana(null, anchoVentana, altoVentana);
                            newContentAdmin.cambiarVentana(root);
                            break;
                            
                        case DIRECTIVO:
                            ViewDirectivos newContentDirectivos = new ViewDirectivos();
                            newContentDirectivos.crearVentana(null, anchoVentana, altoVentana);
                            newContentDirectivos.cambiarVentana(root);
                            break;
                            
                        case JEFE_BODEGA:
                            ViewJefeBodega newContentBodega = new ViewJefeBodega();
                            newContentBodega.crearVentana(null, anchoVentana, altoVentana);
                            newContentBodega.cambiarVentana(root);
                            break;
                        
                        default:
                            
                            break;
                    }
                }
            });
            
            contenedor.getChildren().addAll(logo, entradoUser, entradoPassword, buttonLogin);
            
            getChildren().addAll(fondo, cuadroCentral, contenedor);
            
        }
    }
}
