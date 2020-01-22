/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author paula
 */
public class Verification {
    
    public UserType verificacionDatosSession(String user, String pass){
        List<Control_Session> metodos = new ArrayList<>();
        
        metodos.add(new Ctr_Administrador());
        metodos.add(new Ctr_Directivos());
        metodos.add(new Ctr_Jefe_Bodega());
        metodos.add(new Ctr_Personal_Caja());
        
        Iterator<Control_Session> it = metodos.iterator();
        
        UserType tipoUsuario = null;
        Control_Session control_session;
        
        while(it.hasNext()){
            control_session = it.next();
            
            tipoUsuario = control_session.verificarSesion(user, pass);
            
            if(tipoUsuario != null)
                return tipoUsuario;
        }
        
        return UserType.INVALIDO;
    }
    
}
