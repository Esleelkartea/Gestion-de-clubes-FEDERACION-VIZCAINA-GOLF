/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class TipoInstalacion {

    //Atributos
    private int idTipoInstalacion;
    private String nombre;

    //Constructores
    public TipoInstalacion()
    {

    }

    public TipoInstalacion(int pIdTipoInstalacion, String pNombre)
    {
        idTipoInstalacion = pIdTipoInstalacion;
        nombre = pNombre;
    }

    public TipoInstalacion(String pNombre)
    {
        nombre = pNombre;
    }

    public int getIdTipoInstalacion() {
        return idTipoInstalacion;
    }
   
    public void setIdTipoInstalacion(int idTipoInstalacion) {
        this.idTipoInstalacion = idTipoInstalacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
