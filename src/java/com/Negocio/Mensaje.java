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
public class Mensaje {

    //Atributo
    private int idMensaje;
    private Date fecha;
    private Usuario usuEmisor;
    private Usuario usuDestinatario;
    private String asunto;
    private String mensaje;

    //Constructor
    public Mensaje()
    {

    }

    public Mensaje(int pIdMensaje, Date pFecha, Usuario pUsuEmisor, Usuario pUsuDestinatario, String pAsunto, String pMensaje)
    {
        idMensaje = pIdMensaje;
        fecha = pFecha;
        usuEmisor = pUsuEmisor;
        usuDestinatario = pUsuDestinatario;
        asunto = pAsunto;
        mensaje = pMensaje;
    }

    public Mensaje(Date pFecha, Usuario pUsuEmisor, Usuario pUsuDestinatario, String pAsunto, String pMensaje)
    {
        fecha = pFecha;
        usuEmisor = pUsuEmisor;
        usuDestinatario = pUsuDestinatario;
        asunto = pAsunto;
        mensaje = pMensaje;
    }

    //Metodos Get y Set
    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuEmisor() {
        return usuEmisor;
    }

    public void setUsuEmisor(Usuario usuEmisor) {
        this.usuEmisor = usuEmisor;
    }

    public Usuario getUsuDestinatario() {
        return usuDestinatario;
    }

    public void setUsuDestinatario(Usuario usuDestinatario) {
        this.usuDestinatario = usuDestinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
   
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

}
