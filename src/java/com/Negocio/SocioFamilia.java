/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class SocioFamilia {

    //Atributos
    private int idSocioFamilia;
    private int idSocio;
    private Socio familiar;
    private TipoFamiliar tipoFamilia;

    //Constructores
    public SocioFamilia()
    {

    }

    public SocioFamilia(int pIdSocioFamilia, int pIdSocio, Socio pFamiliar, TipoFamiliar pTipoFamiliar)
    {
        idSocioFamilia = pIdSocioFamilia;
        idSocio = pIdSocio;
        familiar = pFamiliar;
        tipoFamilia = pTipoFamiliar;
    }
    
    //Metodos Get y Set
    public int getIdSocioFamilia() {
        return idSocioFamilia;
    }

    public void setIdSocioFamilia(int idSocioFamilia) {
        this.idSocioFamilia = idSocioFamilia;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public Socio getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Socio pFamiliar) {
        this.familiar = pFamiliar;
    }

    public TipoFamiliar getTipoFamilia() {
        return tipoFamilia;
    }

    public void setTipoFamilia(TipoFamiliar tipoFamilia) {
        this.tipoFamilia = tipoFamilia;
    }

}
