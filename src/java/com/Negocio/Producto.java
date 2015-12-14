/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class Producto {

    //Atributos
    private int idProducto;
    private Categoria idCategoria;
    private String producto;
    private double precio;
    private String categoria;
    private String subcategoria;

    //Constructores
    public Producto()
    {

    }

    public Producto(int pIdProducto, Categoria pIdCategoria, String pProducto, double pPrecio, String pCategoria, String pSubcategoria)
    {
        idProducto = pIdProducto;
        idCategoria = pIdCategoria;
        producto = pProducto;
        precio = pPrecio;
        categoria = pCategoria;
        subcategoria = pSubcategoria;
    }

    public Producto(Categoria pIdCategoria, String pProducto, double pPrecio, String pCategoria, String pSubcategoria)
    {
        idCategoria = pIdCategoria;
        producto = pProducto;
        precio = pPrecio;
        categoria = pCategoria;
        subcategoria = pSubcategoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
