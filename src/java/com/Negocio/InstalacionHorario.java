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
public class InstalacionHorario {

    //Atributos
    private int idInstalacionHorario;
    private Date fechaInicio;
    private Date fechaFin;
    private Time horaInicio;
    private Time horaFin;
    private String observaciones;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;

    private int idInstalacion;


    //Constructor
    public InstalacionHorario()
    {

    }

    public InstalacionHorario(int pIdInstalacionHorario, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, boolean pLunes, boolean pMartes, boolean pMiercoles, boolean pJueves, boolean pViernes, boolean pSabado, boolean pDomingo, int pIdInstalacion)
    {
        idInstalacionHorario = pIdInstalacionHorario;
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
        observaciones = pObservaciones;
        lunes = pLunes;
        martes = pMartes;
        miercoles = pMiercoles;
        jueves = pJueves;
        viernes = pViernes;
        sabado = pSabado;
        domingo = pDomingo;
        idInstalacion = pIdInstalacion;
    }


    public InstalacionHorario(Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones,  boolean pLunes, boolean pMartes, boolean pMiercoles, boolean pJueves, boolean pViernes, boolean pSabado, boolean pDomingo, int pIdInstalacion)
    {
        fechaFin = pFechaFin;
        fechaFin = pFechaFin;
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
        observaciones = pObservaciones;
        lunes = pLunes;
        martes = pMartes;
        miercoles = pMiercoles;
        jueves = pJueves;
        viernes = pViernes;
        sabado = pSabado;
        domingo = pDomingo;
        idInstalacion = pIdInstalacion;
    }

    //Metodos Get y Set
    public int getIdInstalacionHorario() {
        return idInstalacionHorario;
    }

    public void setIdInstalacionHorario(int idInstalacionHorario) {
        this.idInstalacionHorario = idInstalacionHorario;
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

    public boolean isLunes() {
        return lunes;
    }

    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    public boolean isMartes() {
        return martes;
    }

    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    public boolean isMiercoles() {
        return miercoles;
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    public boolean isJueves() {
        return jueves;
    }

    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    public boolean isViernes() {
        return viernes;
    }

    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public int getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(int idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

}
