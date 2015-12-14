/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class TipoConvenio {

    //Atributos
    private int idTipoConvenio;
    private String convenio;

    //Constructores
    public TipoConvenio()
    {

    }

    public TipoConvenio(int pIdTipoConvenio, String pConvenio)
    {
        idTipoConvenio = pIdTipoConvenio;
        convenio = pConvenio;
    }

    //Metodos Get y Set
    public int getIdTipoConvenio() {
        return idTipoConvenio;
    }

    public void setIdTipoConvenio(int idTipoConvenio) {
        this.idTipoConvenio = idTipoConvenio;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }



}
