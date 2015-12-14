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
public class InvitacionPresentacion {

    //Atributos
    private String nombre;
    private String apellidos;
    private Date fecha;
    private String invitado;
    private int cantidad;
    private double importe;


    public InvitacionPresentacion()
    {

    }

    public InvitacionPresentacion(String pNombre, String pApellidos, Date pFecha, String pInvitado, int pCantidad, double pImporte)
    {
        nombre = pNombre;
        apellidos = pApellidos;
        fecha = pFecha;
        invitado = pInvitado;
        cantidad = pCantidad;
        importe = pImporte;
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
}
