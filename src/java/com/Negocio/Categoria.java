/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class Categoria {

    //Atributos
    private int idCategoria;
    private String categoria;
    private String subcategoria;

    //Constructor
    public Categoria()
    {

    }

    public Categoria(int pIdCategoria, String pCategoria, String pSubcategoria)
    {
        idCategoria = pIdCategoria;
        categoria = pCategoria;
        subcategoria = pSubcategoria;
    }
    
    public Categoria(String pCategoria, String pSubcategoria)
    {
        categoria = pCategoria;
        subcategoria = pSubcategoria;
    }

    //Metodos Get y Set
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

}
