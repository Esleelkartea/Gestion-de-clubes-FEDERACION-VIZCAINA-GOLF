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
public class Accion {

    //Atributos
    private int idAccion;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private int idSocio;

    //Constructores
    public Accion()
    {

    }

    public Accion(int pIdAccion, String pNombre, Date pFechaInicio, Date pFechaFin, int pIdSocio)
    {
        idAccion = pIdAccion;
        nombre = pNombre;
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        idSocio = pIdSocio;
    }

    public Accion(String pNombre, Date pFechaInicio, Date pFechaFin, int pIdSocio)
    {
        nombre = pNombre;
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        idSocio = pIdSocio;
    }


   //Metodos Get y Set
    public int getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(int idAccion) {
        this.idAccion = idAccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

}
