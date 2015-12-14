/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class TipoInvitacion {

    //Atributos
    private int idInvitacion;
    private double adulLaborable;
    private double adulFestivo;
    private double menLaborable;
    private double menFestivo;
    private int visitMax;


    //Constructor
    public TipoInvitacion()
    {

    }

    public TipoInvitacion(int pIdInvitacion, double pAdulLab, double pAdulFest, double pMenLab, double pMenFest, int pVisitMax)
    {
        idInvitacion = pIdInvitacion;
        adulFestivo = pAdulFest;
        adulLaborable = pAdulLab;
        menLaborable = pMenLab;
        menFestivo = pMenFest;
        visitMax = pVisitMax;
    }

    //Metodos Get y Set
    public int getIdInvitacion() {
        return idInvitacion;
    }

    public void setIdInvitacion(int idInvitacion) {
        this.idInvitacion = idInvitacion;
    }

    public double getAdulLaborable() {
        return adulLaborable;
    }

    public void setAdulLaborable(double adulLaborable) {
        this.adulLaborable = adulLaborable;
    }

    public double getAdulFestivo() {
        return adulFestivo;
    }

    public void setAdulFestivo(double adulFestivo) {
        this.adulFestivo = adulFestivo;
    }

    public double getMenLaborable() {
        return menLaborable;
    }

    public void setMenLaborable(double menLaborable) {
        this.menLaborable = menLaborable;
    }

    public double getMenFestivo() {
        return menFestivo;
    }

    public void setMenFestivo(double menFestivo) {
        this.menFestivo = menFestivo;
    }

    public int getVisitMax() {
        return visitMax;
    }

    public void setVisitMax(int visitMax) {
        this.visitMax = visitMax;
    }



}
