/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class UsuariosConvenio {

    /*
     UsuariosConvenio hace referencia a los socios convenidos
     aunque se mantiene el nombre de usuarios

     */

    //Atributos
    private int idUsuariosConvenios;
    private Socio idSocio;
    private TipoConvenio convenio;


    //Constructor
    public UsuariosConvenio()
    {

    }

    public UsuariosConvenio(int pIdUsuariosConvenio, Socio pSocio, TipoConvenio pConvenio)
    {
        idUsuariosConvenios = pIdUsuariosConvenio;
        idSocio = pSocio;
        convenio = pConvenio;
    }

   //Metodos Get y Set
    public int getIdUsuariosConvenios() {
        return idUsuariosConvenios;
    }

    public void setIdUsuariosConvenios(int idUsuariosConvenios) {
        this.idUsuariosConvenios = idUsuariosConvenios;
    }
   
    public TipoConvenio getConvenio() {
        return convenio;
    }

    public void setConvenio(TipoConvenio convenio) {
        this.convenio = convenio;
    }

    public Socio getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Socio idSocio) {
        this.idSocio = idSocio;
    }

}
