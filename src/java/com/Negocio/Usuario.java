/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class Usuario {

    //Atributos
    private int idUsuario;
    private String nombre;
    private String password;


    //Constructores
    public Usuario()
    {

    }

    public Usuario(String pNombre, String pPassword)
    {
        nombre = pNombre;
        password = pPassword;
    }

    public Usuario(int pIdUsuario, String pNombre, String pPassword)
    {
        idUsuario = pIdUsuario;
        nombre = pNombre;
        password = pPassword;
    }

   
    // Metodos Get y Set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
