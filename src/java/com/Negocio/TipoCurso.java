/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class TipoCurso {

    //Atributos
    private int idTipoCurso;
    private String nombre;

    //Constructores
    public TipoCurso()
    {

    }

    public TipoCurso(int pIdTipoCurso, String pNombre)
    {
        idTipoCurso = pIdTipoCurso;
        nombre = pNombre;
    }

    //Metodos Get y Set
    public int getIdTipoCurso() {
        return idTipoCurso;
    }

    public void setIdTipoCurso(int idTipoCurso) {
        this.idTipoCurso = idTipoCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
