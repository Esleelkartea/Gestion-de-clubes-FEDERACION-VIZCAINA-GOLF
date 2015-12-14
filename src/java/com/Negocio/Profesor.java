/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class Profesor {

    //Atributos
    private int idProfesor;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private int idTipoInstalacion;
    private double importe;

    //Constructor
    public Profesor()
    {

    }

    public Profesor(int pIdProfesor, String pNombre, String pApellidos, String pEmail, String pTelefono, int pIdTipoInstalacion, double pImporte)
    {
        idProfesor = pIdProfesor;
        nombre = pNombre;
        apellidos = pApellidos;
        email = pEmail;
        telefono = pTelefono;
        idTipoInstalacion = pIdTipoInstalacion;
        importe = pImporte;
    }

    public Profesor(String pNombre, String pApellidos, String pEmail, String pTelefono, int pIdTipoInstalacion, double pImporte)
    {
        nombre = pNombre;
        apellidos = pApellidos;
        email = pEmail;
        telefono = pTelefono;
        idTipoInstalacion = pIdTipoInstalacion;
        importe = pImporte;
    }

    
    //Otros metodos
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdTipoInstalacion() {
        return idTipoInstalacion;
    }

    public void setIdTipoInstalacion(int idTipoInstalacion) {
        this.idTipoInstalacion = idTipoInstalacion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
    
}
