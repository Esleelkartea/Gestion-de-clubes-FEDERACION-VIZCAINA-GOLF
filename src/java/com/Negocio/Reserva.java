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
public class Reserva {

    //Atributos
    private int idReserva;
    private int idInstalacion;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private int idSocio;
    private double importe;
    private int idEstadoReserva;
    private int formaPago;


    //Constructor
    public Reserva()
    {

    }

    public Reserva(int pIdReserva, int pIdInstalacion, Date pFecha, Time pHoraInicio, Time pHoraFin, int pIdSocio, double pImporte, int pIdEstadoReserva, int pIdFormaPago)
    {
        idReserva = pIdReserva;
        idInstalacion = pIdInstalacion;
        fecha = pFecha;
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
        idSocio = pIdSocio;
        importe = pImporte;
        idEstadoReserva = pIdEstadoReserva;
        formaPago = pIdFormaPago;
    }


    //Metodos Get y Set
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(int idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getIdEstadoReserva() {
        return idEstadoReserva;
    }

    public void setIdEstadoReserva(int idEstadoReserva) {
        this.idEstadoReserva = idEstadoReserva;
    }

    public int getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(int formaPago) {
        this.formaPago = formaPago;
    }

}
