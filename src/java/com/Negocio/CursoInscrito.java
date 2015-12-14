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
public class CursoInscrito {

    //Atributos
    private int idCursoinscrito;
    private int idSocio;
    private int idCurso;
    private Date fechaInscripcion;

    //Constructores
    public CursoInscrito()
    {

    }

    public CursoInscrito(int pIdCursoInscrito, int pIdSocio, int pIdCurso, Date pFechaInscripcion)
    {
        idCursoinscrito = pIdCursoInscrito;
        idSocio = pIdSocio;
        idCurso = pIdCurso;
        fechaInscripcion = pFechaInscripcion;
    }

    public CursoInscrito(int pIdSocio, int pIdCurso, Date pFechaInscripcion)
    {
        idSocio = pIdSocio;
        idCurso = pIdCurso;
        fechaInscripcion = pFechaInscripcion;
    }


    //Metodos Get y Set
    public int getIdCursoinscrito() {
        return idCursoinscrito;
    }

    public void setIdCursoinscrito(int idCursoinscrito) {
        this.idCursoinscrito = idCursoinscrito;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }



}
