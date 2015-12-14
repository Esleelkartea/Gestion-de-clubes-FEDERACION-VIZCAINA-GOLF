/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class Familiar {

    //Atributos
    private int idFamiliar;
    private Socio idSocio;
    private Socio idSocioFamiliar;
    private TipoFamiliar idTipoFamiliar;

    //Constructores
    public Familiar()
    {

    }


    //Metodos Get y Set
    public int getIdFamiliar() {
        return idFamiliar;
    }
   
    public void setIdFamiliar(int idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public TipoFamiliar getIdTipoFamiliar() {
        return idTipoFamiliar;
    }

    public void setIdTipoFamiliar(TipoFamiliar idTipoFamiliar) {
        this.idTipoFamiliar = idTipoFamiliar;
    }

    public Socio getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Socio idSocio) {
        this.idSocio = idSocio;
    }

    public Socio getIdSocioFamiliar() {
        return idSocioFamiliar;
    }

    public void setIdSocioFamiliar(Socio idSocioFamiliar) {
        this.idSocioFamiliar = idSocioFamiliar;
    }
}
