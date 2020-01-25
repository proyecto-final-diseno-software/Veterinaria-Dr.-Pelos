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
    private CtrBaseDatosProxy controlDataBase;

    public Verification() {
        this.controlDataBase = new CtrBaseDatosProxy();
        this.con = controlDataBase.getConnection();
    }
    
    public UserType verificacionDatosSession(String user, String pass){
        UserType tipoUsuario = UserType.INVALIDO;
        
        String cedula = controlDataBase.isUser(user, pass);
        
        if(cedula != null){
            List<ControlSession> metodos = new ArrayList<>();

            metodos.add(new CtrAdministrador());
            metodos.add(new CtrDirectivos());
            metodos.add(new CtrJefeBodega());
            metodos.add(new CtrPersonalCaja());

            Iterator<ControlSession> it = metodos.iterator();
            ControlSession control_session;

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
