/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class Instalacion {

    //Atributos
    private int idInstalacion;
    private String nombre;
    private TipoInstalacion tipoInstalacion;
    private double tarifaAdulto;
    private double tarifaMenor;

    //Constructores
    public Instalacion()
    {

    }

    public Instalacion(int pIdInstalacion, String pNombre, TipoInstalacion pTipoInstalacion, double pTarifaAdulto, double pTarifaMenor)
    {
        idInstalacion = pIdInstalacion;
        nombre = pNombre;
        tipoInstalacion = pTipoInstalacion;
        tarifaAdulto = pTarifaAdulto;
        tarifaMenor = pTarifaMenor;
    }

    public Instalacion(String pNombre, TipoInstalacion pTipoInstalacion, double pTarifaAdulto, double pTarifaMenor)
    {
        nombre = pNombre;
        tipoInstalacion = pTipoInstalacion;
        tarifaAdulto = pTarifaAdulto;
        tarifaMenor = pTarifaMenor;
    }

    //Metodos Get y Set
    public int getIdInstalacion() {
        return idInstalacion;
    }

    public void setIdInstalacion(int idInstalacion) {
        this.idInstalacion = idInstalacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoInstalacion getTipoInstalacion() {
        return tipoInstalacion;
    }

    public void setTipoInstalacion(TipoInstalacion tipoInstalacion) {
        this.tipoInstalacion = tipoInstalacion;
    }

    public double getTarifaAdulto() {
        return tarifaAdulto;
    }

    public void setTarifaAdulto(double tarifaAdulto) {
        this.tarifaAdulto = tarifaAdulto;
    }

    public double getTarifaMenor() {
        return tarifaMenor;
    }

    public void setTarifaMenor(double tarifaMenor) {
        this.tarifaMenor = tarifaMenor;
    }

    public double calcularImporte(int pEdad)
    {
        if (pEdad < 18)
            return this.tarifaMenor;
        else
            return this.tarifaAdulto;
    }

}
