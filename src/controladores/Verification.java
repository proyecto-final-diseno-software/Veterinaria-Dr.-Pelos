/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author paula
 */
public class Verification {
    private Connection con;
    private Ctr_BaseDatosProxy controlDataBase;

    public Verification() {
        this.controlDataBase = new Ctr_BaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public UserType verificacionDatosSession(String user, String pass){
        UserType tipoUsuario = UserType.INVALIDO;
        
        String cedula = controlDataBase.isUser(user, pass);
        
        if(cedula != null){
            List<Control_Session> metodos = new ArrayList<>();

            metodos.add(new Ctr_Administrador());
            metodos.add(new Ctr_Directivos());
            metodos.add(new Ctr_Jefe_Bodega());
            metodos.add(new Ctr_Personal_Caja());

            Iterator<Control_Session> it = metodos.iterator();
            Control_Session control_session;

            while(it.hasNext()){
                control_session = it.next();

                tipoUsuario = control_session.verificarSesion(cedula);

                if(tipoUsuario != null)
                    return tipoUsuario;
            }
        }
        
        return tipoUsuario;
    }
    
}
