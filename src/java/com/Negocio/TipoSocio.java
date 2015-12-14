/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class TipoSocio {

    //Atributos
    private int idTipoSocio;
    private String nombre;
    private double cuota;

    //Constructores
    public TipoSocio()
    {

    }

    public TipoSocio(int pIdTipoSocio, String pNombre, double pCuota)
    {
        idTipoSocio = pIdTipoSocio;
        nombre = pNombre;
        cuota = pCuota;
    }

    public TipoSocio(String pNombre, double pCuota)
    {
        nombre = pNombre;
        cuota = pCuota;
    }

    //Metodos Get y Set
    public int getIdTipoSocio() {
        return idTipoSocio;
    }
    
    public void setIdTipoSocio(int idTipoSocio) {
        this.idTipoSocio = idTipoSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String tipo) {
        this.nombre = tipo;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

}
