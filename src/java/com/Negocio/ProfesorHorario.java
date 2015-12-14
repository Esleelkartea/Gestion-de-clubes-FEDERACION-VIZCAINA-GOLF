/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Julen
 */
public class ProfesorHorario {

    //Atributos
    private int idProfesorHorario;
    private Date fechaInicio;
    private Date fechaFin;
    private Time horaInicio;
    private Time horaFin;
    private String observaciones;
    private int idProfesor;

    //Constructor
    public ProfesorHorario()
    {

    }

    public ProfesorHorario(int pIdProfesorHorario, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, int pIdProfesor)
    {
        idProfesorHorario = pIdProfesorHorario;
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
        observaciones = pObservaciones;
        idProfesor = pIdProfesor;
    }

    public ProfesorHorario(Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, int pIdProfesor)
    {
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
        observaciones = pObservaciones;
        idProfesor = pIdProfesor;
    }

    //Metodos Get y Set
    public int getIdProfesorHorario() {
        return idProfesorHorario;
    }

    public void setIdProfesorHorario(int idProfesorHorario) {
        this.idProfesorHorario = idProfesorHorario;
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

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

}
