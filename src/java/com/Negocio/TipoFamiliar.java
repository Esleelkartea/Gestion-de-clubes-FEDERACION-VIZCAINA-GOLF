/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class TipoFamiliar {

    //Atributos
    private int idTipoFamiliar;
    private String nombre;

    //Constructores
    public TipoFamiliar()
    {

    }

    public TipoFamiliar(int pIdParentesco, String pNombre)
    {
        idTipoFamiliar = pIdParentesco;
        nombre = pNombre;
    }

   //Metodos Get y Set
    public int getIdTipoFamiliar() {
        return idTipoFamiliar;
    }

    public void setIdTipoFamiliar(int idTipoFamiliaro) {
        this.idTipoFamiliar = idTipoFamiliaro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
