/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class Foto {
    //Atributos
    private int idFoto;
    private String nombre;
    private String url;
    private String comentario;

    //Constructores
    public Foto()
    {

    }

    public Foto(int pIdFoto, String pNombre, String pUrl, String pComentario)
    {
        idFoto = pIdFoto;
        nombre = pNombre;
        url = pUrl;
        comentario = pComentario;
    }

    public Foto(String pNombre, String pUrl, String pComentario)
    {
        nombre = pNombre;
        url = pUrl;
        comentario = pComentario;
    }

    //Metodos Get y Set
    public int getIdFoto() {
        return idFoto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
