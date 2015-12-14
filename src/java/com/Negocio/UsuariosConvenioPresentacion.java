/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class UsuariosConvenioPresentacion {

    //Atributos
    private int idUsuarioConvenio;
    private int idUsuario;
    private String nombre;
    private String apellidos;
    private boolean carnet;
    private int idConvenio;
    private String convenio;


    //Constructores
    public UsuariosConvenioPresentacion()
    {

    }

    public UsuariosConvenioPresentacion(int pIdUsuarioConvenio, int pIdUsuario, String pNombre, String pApellidos, boolean pCarnet, int pIdConvenio, String pConvenio)
    {
        idUsuarioConvenio = pIdUsuarioConvenio;
        idUsuario = pIdUsuario;
        nombre = pNombre;
        apellidos = pApellidos;
        carnet = pCarnet;
        idConvenio = pIdConvenio;
        convenio = pConvenio;

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public boolean isCarnet() {
        return carnet;
    }

    public void setCarnet(boolean carnet) {
        this.carnet = carnet;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public int getIdUsuarioConvenio() {
        return idUsuarioConvenio;
    }

    public void setIdUsuarioConvenio(int idUsuarioConvenio) {
        this.idUsuarioConvenio = idUsuarioConvenio;
    }

    public int getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }
}
