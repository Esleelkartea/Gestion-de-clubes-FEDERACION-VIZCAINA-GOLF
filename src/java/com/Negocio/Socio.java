/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Negocio;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Julen
 */
public class Socio {

    //Atributos
    private int idSocio;
    private String numSocio;
    private String dni;
    private String nombre;
    private String apellidos;
    private String sexo;
    private Date fechaNac;
    private String profesion;

    //private int nivel;
    
    private String antiguedad;
    private Date fechaAlta;
    private Foto foto;
    private String observaciones;
    private TipoSocio tipo;
    private boolean socioMartuberri;

    private String direccion;
    private String cp;
    private String poblacion;
    private String provincia;

    private String telefono1;
    private String telefono2;
    private String fax;
    private String email;

    private String banco;
    private String numCuenta;

      
    private int idUsuario;

    private TipoPago tipoPago;

    //Constructores
    public Socio()
    {

    }


    public Socio(String pNumSocio, String pDni, String pNombre, String pApellidos, String pSexo, Date pFechaNac, String pProfesion, String pAntiguedad,Date pFechaAlta, Foto pFoto, String pObservaciones, TipoSocio pTipo, boolean pMartuberri, String pDireccion, String pCP, String pPoblacion, String pProvincia, String pTelefono1, String pTelefono2, String pEmail, String pFax, String pBanco, String pNumCuenta, ArrayList<Historial> pHistorial, ArrayList<Familiar> pFamiliares, int pIdUsuario, TipoPago pTipoPago)
    {
        
        numSocio = pNumSocio;
        dni = pDni;
        nombre = pNombre;
        apellidos = pApellidos;
        sexo = pSexo;
        fechaNac = pFechaNac;
        profesion = pProfesion;

        antiguedad = pAntiguedad;
        fechaAlta = pFechaAlta;
        foto = pFoto;
        observaciones = pObservaciones;
        tipo = pTipo;
        socioMartuberri = pMartuberri;

        direccion = pDireccion;
        cp = pCP;
        poblacion = pPoblacion;
        provincia = pProvincia;
        telefono1 = pTelefono1;
        telefono2 = pTelefono2;
        fax = pFax;
        email = pEmail;

        banco = pBanco;
        numCuenta = pNumCuenta;
     
        
        idUsuario = pIdUsuario;

        tipoPago = pTipoPago;

    }

    public Socio(int pIdSocio, String pNumSocio, String pDni, String pNombre, String pApellidos, String pSexo, Date pFechaNac, String pProfesion, String pAntiguedad, Date pFechaAlta, Foto pFoto, String pObservaciones, TipoSocio pTipo, boolean pMartuberri, String pDireccion, String pCP, String pPoblacion, String pProvincia, String pTelefono1, String pTelefono2, String pEmail, String pFax, String pBanco, String pNumCuenta, int pIdUsuario, TipoPago pTipoPago)
    {
        idSocio = pIdSocio;
        numSocio = pNumSocio;
        dni = pDni;
        nombre = pNombre;
        apellidos = pApellidos;
        sexo = pSexo;
        fechaNac = pFechaNac;
        profesion = pProfesion;

        antiguedad = pAntiguedad;
        fechaAlta = pFechaAlta;
        foto = pFoto;
        observaciones = pObservaciones;
        tipo = pTipo;
        socioMartuberri = pMartuberri;

        direccion = pDireccion;
        cp = pCP;
        poblacion = pPoblacion;
        provincia = pProvincia;
        telefono1 = pTelefono1;
        telefono2 = pTelefono2;
        fax = pFax;
        email = pEmail;

        banco = pBanco;
        numCuenta = pNumCuenta;

        idUsuario = pIdUsuario;

        tipoPago = pTipoPago;
    }

    
    //Metodos Get y Set
    public int getIdSocio() {
        return idSocio;
    }
    
    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(String numSocio) {
        this.numSocio = numSocio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getObservaciones() {
        return observaciones;
    }
 
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoSocio getTipo() {
        return tipo;
    }

    public void setTipo(TipoSocio tipo) {
        this.tipo = tipo;
    }

    public boolean isSocioMartuberri() {
        return socioMartuberri;
    }

    public void setSocioMartuberri(boolean socioMartuberri) {
        this.socioMartuberri = socioMartuberri;
    }
   

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    /*public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }*/
   
    public int getIdUsuario() {
        return idUsuario;
    }
   
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    
    public int calcularEdad(Date pFechaNac)
    {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(pFechaNac);
	Calendar today = Calendar.getInstance();
	int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
	dateOfBirth.add(Calendar.YEAR, age);
	if (today.before(dateOfBirth))
	        age--;
	    return age;
    }

    /**
     * @return the tipoPago
     */
    public TipoPago getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

   

}
