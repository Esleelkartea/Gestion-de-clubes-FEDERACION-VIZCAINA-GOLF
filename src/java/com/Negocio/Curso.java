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
public class Curso {

    //Atributos
    private int cursoId;
    private String cursoNombre;
    private int profesorId;
    private Date fechaInicio;
    private Date fechaFin;
    private Time horaInicio;
    private Time horaFin;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;
    private double importe;
    private int numMax;
    private int numApuntados;

    private Date fechaInscripción;

    private int idTipoCurso;

    private int idInstalacion;

    private boolean finalizado;

    //Constructores
    public Curso()
    {

    }

    public Curso(int pIdCurso, String pNombreCurso, int pProfesorId, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, boolean pLunes, boolean pMartes, boolean pMiercoles, boolean pJueves, boolean pViernes, boolean pSabado, boolean pDomingo, double pImporte, int pNumMax, int pNumAsistencia, Date pFechaInscripcion, int pTipoCurso, int pIdInstalacion, boolean pFin)
    {
        cursoId = pIdCurso;
        cursoNombre = pNombreCurso;
        profesorId = pProfesorId;
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
        lunes = pLunes;
        martes = pMartes;
        miercoles = pMiercoles;
        jueves = pJueves;
        viernes = pViernes;
        sabado = pSabado;
        domingo = pDomingo;
        importe = pImporte;
        numMax = pNumMax;
        numApuntados = pNumAsistencia;
        fechaInscripción = pFechaInscripcion;
        idTipoCurso = pTipoCurso;
        idInstalacion = pIdInstalacion;

        finalizado = pFin;
    }

    public Curso(String pNombreCurso, int pProfesorId, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, boolean pLunes, boolean pMartes, boolean pMiercoles, boolean pJueves, boolean pViernes, boolean pSabado, boolean pDomingo, double pImporte, int pNumMax, int pNumAsistencia, Date pFechaInscripcion, int pTipoCurso, int pIdInstalacion, boolean pFin)
    {
        cursoNombre = pNombreCurso;
        profesorId = pProfesorId;
        fechaInicio = pFechaInicio;
        fechaFin = pFechaFin;
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
        lunes = pLunes;
        martes = pMartes;
        miercoles = pMiercoles;
        jueves = pJueves;
        viernes = pViernes;
        sabado = pSabado;
        domingo = pDomingo;
        importe = pImporte;
        numMax = pNumMax;
        numApuntados = pNumAsistencia;
        fechaInscripción = pFechaInscripcion;
        idTipoCurso = pTipoCurso;
        idInstalacion = pIdInstalacion;

        finalizado = pFin;
    }


    //Metodos Get y Set
    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
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

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getNumMax() {
        return numMax;
    }

    public void setNumMax(int numMax) {
        this.numMax = numMax;
    }

    public int getNumApuntados() {
        return numApuntados;
    }

    public void setNumApuntados(int numApuntados) {
        this.numApuntados = numApuntados;
    }

    public Date getFechaInscripción() {
        return fechaInscripción;
    }

    public void setFechaInscripción(Date fechaInscripción) {
        this.fechaInscripción = fechaInscripción;
    }

    public int getIdTipoCurso() {
        return idTipoCurso;
    }

    public void setIdTipoCurso(int idTipoCurso) {
        this.idTipoCurso = idTipoCurso;
    }

    public int getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(int idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

}
