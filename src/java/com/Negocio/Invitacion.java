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
public class Invitacion {

    //Atributos
    private int idInvitacion;
    private Socio socio;
    private Date fecha;
    private String invitado;
    private int cantidad;
    private double importe;
    private TipoPago formaPago;
    private String nombre;
    private String apellidos;


    //Constructor
    public Invitacion()
    {

    }

    public Invitacion(int pIdInvitacion, Socio pSocio, Date pFecha, String pInvitado, int pCantidad, double pImporte, TipoPago pFormaPago)
    {
        idInvitacion = pIdInvitacion;
        socio = pSocio;
        fecha = pFecha;
        invitado = pInvitado;
        cantidad = pCantidad;
        importe = pImporte;
        formaPago = pFormaPago;
        nombre = pSocio.getNombre();
        apellidos = pSocio.getApellidos();
    }

    public Invitacion(Socio pSocio, Date pFecha, String pInvitado, int pCantidad, double pImporte, TipoPago pFormaPago)
    {
        socio = pSocio;
        fecha = pFecha;
        invitado = pInvitado;
        cantidad = pCantidad;
        importe = pImporte;
        formaPago = pFormaPago;
        nombre = pSocio.getNombre();
        apellidos = pSocio.getApellidos();
    }

    //Metodos Get y Set
    public int getIdInvitacion() {
        return idInvitacion;
    }

    public void setIdInvitacion(int idInvitacion) {
        this.idInvitacion = idInvitacion;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getInvitado() {
        return invitado;
    }

    public void setInvitado(String invitado) {
        this.invitado = invitado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public TipoPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(TipoPago formaPago) {
        this.formaPago = formaPago;
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

}
