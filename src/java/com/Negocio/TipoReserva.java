/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class TipoReserva {

    //Atributos
    private int idTipoReserva;
    private String estado;
    private int normas;

    //Constructores
    public TipoReserva()
    {

    }

    public TipoReserva(int pIdTipoReserva, String pEstado, int pNormas)
    {
        idTipoReserva = pIdTipoReserva;
        estado = pEstado;
        normas = pNormas;
    }

    //Metodos Get y Set
    public int getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(int idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNormas() {
        return normas;
    }

    public void setNormas(int normas) {
        this.normas = normas;
    }

}
