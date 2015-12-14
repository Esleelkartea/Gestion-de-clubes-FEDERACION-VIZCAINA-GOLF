/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

import java.sql.Date;

/**
 *
 * @author Julen
 */
public class Visita {

    //Atributos
    private int idVisita;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;
    private String poblacion;
    private String atendido;
    private Date fechaVisita;
   

    //Constructores
    public Visita()
    {

    }

    public Visita(int pIdVisita, String pNombre, String pApellidos, String pTelefono, String pDireccion, String pPoblacion, String pAtendido, Date pFechaVisita)
    {
        idVisita = pIdVisita;
        nombre = pNombre;
        apellidos = pApellidos;
        telefono = pTelefono;
        direccion = pDireccion;
        poblacion = pPoblacion;
        atendido = pAtendido;
        fechaVisita = pFechaVisita;
    }

    public Visita(String pNombre, String pApellidos, String pTelefono, String pDireccion, String pPoblacion, String pAtendido, Date pFechaVisita)
    {
        nombre = pNombre;
        apellidos = pApellidos;
        telefono = pTelefono;
        direccion = pDireccion;
        poblacion = pPoblacion;
        atendido = pAtendido;
        fechaVisita = pFechaVisita;
    }

    //Metodos Get y Set
    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getAtendido() {
        return atendido;
    }

    public void setAtendido(String atendido) {
        this.atendido = atendido;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

}
