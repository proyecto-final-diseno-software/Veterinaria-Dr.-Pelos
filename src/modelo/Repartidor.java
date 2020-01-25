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
public class Repartidor extends Persona{
    private String idRepetidor;
    private String matriculaVehiculo;
    private String telefono;
    private Sucursal sucur;

    public Repartidor(String cedula, String nombre, String apellido) {
        super(cedula, nombre, apellido);
    }

    public String getIdRepetidor() {
        return idRepetidor;
    }

    public void setIdRepetidor(String idRepetidor) {
        this.idRepetidor = idRepetidor;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Sucursal getSucur() {
        return sucur;
    }

    public void setSucur(Sucursal sucur) {
        this.sucur = sucur;
    }
    
    
    
    public void asignarRuta(){
        //En construccion
    }
}
