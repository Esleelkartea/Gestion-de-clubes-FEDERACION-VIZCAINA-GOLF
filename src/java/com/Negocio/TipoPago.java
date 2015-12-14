/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

/**
 *
 * @author Julen
 */
public class TipoPago {
    
    //Atributos
    private int idTipoPago;
    private String modo;

    //Construtores
    public TipoPago()
    {

    }

    public TipoPago(int pIdTipoPago, String pModo)
    {
        idTipoPago = pIdTipoPago;
        modo = pModo;
    }

    //Metodos Get y Set
    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
}
