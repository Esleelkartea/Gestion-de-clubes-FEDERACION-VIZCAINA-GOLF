/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

import java.sql.Date;

/**
 *
 * @author Julen
 */
public class Venta {

    //Atributos
    private int idVenta;
    private Socio socio;
    private Date fecha;
    private Producto producto;
    private TipoPago tipoPago;
    private boolean pagado;
    private int unidades;
    private double importe;
    private String nombreCategoria;
    private String nombreSubcategoria;
    private String nombreSocio;
    private String apellidosSocio;
    private String nombreProducto;


    //Constructores
    public Venta()
    {

    }

    public Venta(int pIdVenta, Socio pSocio, Date pFecha, Producto pProducto, TipoPago pTipoPago, boolean pPagado, int pUnidades, double pImporte, String pNombreCategoria, String pNombreSubcategoria, String pNombreSocio, String pApellidosSocio, String pNombreProducto)
    {
        idVenta = pIdVenta;
        socio = pSocio;
        fecha = pFecha;
        producto = pProducto;
        tipoPago = pTipoPago;
        pagado = pPagado;
        unidades = pUnidades;
        importe = pImporte;
        nombreCategoria = pNombreCategoria;
        nombreSubcategoria = pNombreSubcategoria;
        nombreSocio = pNombreSocio;
        apellidosSocio = pApellidosSocio;
        nombreProducto = pNombreProducto;
    }

    public Venta(Socio pSocio, Date pFecha, Producto pProducto, TipoPago pTipoPago, boolean pPagado,int pUnidades, double pImporte, String pNombreCategoria, String pNombreSubcategoria, String pNombreSocio, String pApellidosSocio, String pNombreProducto)
    {
        socio = pSocio;
        fecha = pFecha;
        producto = pProducto;
        tipoPago = pTipoPago;
        pagado = pPagado;
        unidades = pUnidades;
        importe = pImporte;
        nombreCategoria = pNombreCategoria;
        nombreSubcategoria = pNombreSubcategoria;
        nombreSocio = pNombreSocio;
        apellidosSocio = pApellidosSocio;
        nombreProducto = pNombreProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreSubcategoria() {
        return nombreSubcategoria;
    }

    public void setNombreSubcategoria(String nombreSubcategoria) {
        this.nombreSubcategoria = nombreSubcategoria;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getApellidosSocio() {
        return apellidosSocio;
    }

    public void setApellidosSocio(String apellidosSocio) {
        this.apellidosSocio = apellidosSocio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

}
