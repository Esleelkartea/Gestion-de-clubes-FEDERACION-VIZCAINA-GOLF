/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class Archivo {

    //Atributos
    private int idArchivo;
    private String nombre;
    private String url;

    //Constructor
    public Archivo()
    {

    }
    public Archivo(int pIdArchivo, String pNombre, String pURL)
    {
        idArchivo = pIdArchivo;
        nombre = pNombre;
        url = pURL;
    }
    public Archivo(String pNombre, String pURL)
    {
        nombre = pNombre;
        url = pURL;
    }

    //Metodos Get y Set
    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
