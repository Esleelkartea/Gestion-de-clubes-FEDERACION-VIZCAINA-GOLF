/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Foto;
import com.Negocio.Socio;
import com.Negocio.TipoPago;
import com.Negocio.TipoSocio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Julen
 */
public class SocioDAL {

    Conexion conexion;

    //Constructor
    public SocioDAL()
    {

    }


    //Otros metodos

    public Socio getSocioId(int pIdSocio)
    {
        conexion = new Conexion();
        Socio unSocio = null;
        Foto unaFoto = null;
        FotoDAL fotoDAL = new FotoDAL();
        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
        TipoPago unTipoPago = null;
        TipoSocio unTipoSocio = null;
        TipoSocioDAL tipoSocioDAL = new TipoSocioDAL();

        String consulta= "SELECT * FROM SOCIO WHERE SOCIO_ID= " + pIdSocio;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                unaFoto = fotoDAL.getFoto(String.valueOf(rs.getInt("SOCIO_IDFOTO")));
                unTipoSocio = tipoSocioDAL.getTipoSocio(rs.getInt("SOCIO_TIPO"));
                unTipoPago = tipoPagoDAL.getTipoPago(rs.getInt("SOCIO_TIPO_PAGO"));
                unSocio = new Socio(rs.getInt("SOCIO_ID"),rs.getString("SOCIO_NUM_SOCIO"), rs.getString("SOCIO_DNI"),rs.getString("SOCIO_NOMBRE"),rs.getString("SOCIO_APELLIDOS"),rs.getString("SOCIO_SEXO"),rs.getDate("SOCIO_FECHANAC"),rs.getString("SOCIO_PROFESION"),rs.getString("SOCIO_ANTIGUEDAD"),rs.getDate("SOCIO_FECHAALTA"),  unaFoto,rs.getString("SOCIO_OBSERVACIONES"), unTipoSocio, rs.getBoolean("SOCIO_MARTUBERRI"), rs.getString("SOCIO_DIRECCION"), rs.getString("SOCIO_CP"), rs.getString("SOCIO_POBLACION"), rs.getString("SOCIO_PROVINCIA"), rs.getString("SOCIO_TELEFONO1"), rs.getString("SOCIO_TELEFONO2"), rs.getString("SOCIO_EMAIL"), rs.getString("SOCIO_FAX"), rs.getString("SOCIO_BANCO"), rs.getString("SOCIO_NUMCUENTA"), rs.getInt("SOCIO_IDUSUARIO"),unTipoPago);
            }
        }
        catch(Exception e)
        {
            unSocio = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return unSocio;
        }
     }


    //Devuelve un socio de una idUsuario
    public Socio getSocio(int pIdUsuario)
    {
        conexion = new Conexion();
        Socio unSocio = null;
        Foto unaFoto = null;
        FotoDAL fotoDAL = new FotoDAL();
        TipoSocio unTipoSocio = null;
        TipoSocioDAL tipoSocioDAL = new TipoSocioDAL();
        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
        TipoPago unTipoPago = null;

        String consulta= "SELECT * FROM SOCIO WHERE SOCIO_IDUSUARIO= " + pIdUsuario;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                unaFoto = fotoDAL.getFoto(String.valueOf(rs.getInt("SOCIO_IDFOTO")));
                unTipoSocio = tipoSocioDAL.getTipoSocio(rs.getInt("SOCIO_TIPO"));
                unTipoPago = tipoPagoDAL.getTipoPago(rs.getInt("SOCIO_TIPO_PAGO"));
                unSocio = new Socio(rs.getInt("SOCIO_ID"),rs.getString("SOCIO_NUM_SOCIO"), rs.getString("SOCIO_DNI"),rs.getString("SOCIO_NOMBRE"),rs.getString("SOCIO_APELLIDOS"),rs.getString("SOCIO_SEXO"),rs.getDate("SOCIO_FECHANAC"),rs.getString("SOCIO_PROFESION"),rs.getString("SOCIO_ANTIGUEDAD"),rs.getDate("SOCIO_FECHAALTA"),  unaFoto,rs.getString("SOCIO_OBSERVACIONES"), unTipoSocio, rs.getBoolean("SOCIO_MARTUBERRI"), rs.getString("SOCIO_DIRECCION"), rs.getString("SOCIO_CP"), rs.getString("SOCIO_POBLACION"), rs.getString("SOCIO_PROVINCIA"), rs.getString("SOCIO_TELEFONO1"), rs.getString("SOCIO_TELEFONO2"), rs.getString("SOCIO_EMAIL"), rs.getString("SOCIO_FAX"), rs.getString("SOCIO_BANCO"), rs.getString("SOCIO_NUMCUENTA"), rs.getInt("SOCIO_IDUSUARIO"),unTipoPago);
            }
        }
        catch(Exception e)
        {
            unSocio = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return unSocio;
        }
     }


    public Socio getSocioConvenio(int pIdUsuario)
    {
        conexion = new Conexion();
        Socio unSocio = null;


        String consulta= "SELECT * FROM SOCIO WHERE SOCIO_IDUSUARIO= " + pIdUsuario;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unSocio = new Socio(rs.getInt("SOCIO_ID"),rs.getString("SOCIO_NUM_SOCIO"), rs.getString("SOCIO_DNI"),rs.getString("SOCIO_NOMBRE"),rs.getString("SOCIO_APELLIDOS"),null,null,null,null,null,null,null,null,rs.getBoolean("SOCIO_MARTUBERRI"), null, null, null, null, null, null, null, null, null, null, rs.getInt("SOCIO_IDUSUARIO"),null);

        }
        catch(Exception e)
        {
            unSocio = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return unSocio;
        }
     }


        public Socio getSocio(String pNombre, String pApellidos)
    {
        conexion = new Conexion();
        Socio unSocio = null;


        String consulta= "SELECT * FROM SOCIO WHERE SOCIO_NOMBRE='" + pNombre + "' AND SOCIO_APELLIDOS='" + pApellidos + "'";
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unSocio = new Socio(rs.getInt("SOCIO_ID"),rs.getString("SOCIO_NUM_SOCIO"), rs.getString("SOCIO_DNI"),rs.getString("SOCIO_NOMBRE"),rs.getString("SOCIO_APELLIDOS"),null,rs.getDate("SOCIO_FECHANAC"),null,null,null,null,null,null,rs.getBoolean("SOCIO_MARTUBERRI"), null, null, null, null, null, null, null, null, null, null, rs.getInt("SOCIO_IDUSUARIO"),null);

        }
        catch(Exception e)
        {
            unSocio = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return unSocio;
        }
     }


    public Socio getSocio(int pIdUsuario, String pNombre, String pApellidos)
    {
        conexion = new Conexion();
        Socio unSocio = null;


        String consulta= "SELECT * FROM SOCIO WHERE SOCIO_IDUSUARIO= " + pIdUsuario + " AND SOCIO_NOMBRE='" + pNombre + "' AND SOCIO_APELLIDOS='" + pApellidos + "'";
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unSocio = new Socio(rs.getInt("SOCIO_ID"),rs.getString("SOCIO_NUM_SOCIO"), rs.getString("SOCIO_DNI"),rs.getString("SOCIO_NOMBRE"),rs.getString("SOCIO_APELLIDOS"),null,null,null,null,null,null,null,null,rs.getBoolean("SOCIO_MARTUBERRI"), null, null, null, null, null, null, null, null, null, null, rs.getInt("SOCIO_IDUSUARIO"),null);

        }
        catch(Exception e)
        {
            unSocio = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return unSocio;
        }
     }

    public Socio getSocioInvitacion(int pIdUsuario, String pNombre, String pApellidos)
    {
        conexion = new Conexion();
        Socio unSocio = null;


        String consulta= "SELECT * FROM SOCIO ";
        ResultSet rs = null;
        boolean bConsul = false;
        try
        {
            if (pIdUsuario != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_IDUSUARIO = " + pIdUsuario;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_NOMBRE ='" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_NOMBRE ='" + pNombre + "'";
             }


             if (pApellidos != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_APELLIDOS ='" + pApellidos + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_APELLIDOS ='" + pApellidos + "'";
             }


             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unSocio = new Socio(rs.getInt("SOCIO_ID"),rs.getString("SOCIO_NUM_SOCIO"), rs.getString("SOCIO_DNI"),rs.getString("SOCIO_NOMBRE"),rs.getString("SOCIO_APELLIDOS"),null,null,null,null,null,null,null,null,rs.getBoolean("SOCIO_MARTUBERRI"), null, null, null, null, null, null, null, null, null, null,  rs.getInt("SOCIO_IDUSUARIO"),null);

        }
        catch(Exception e)
        {
            unSocio = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return unSocio;
        }
     }



    public ArrayList<String> getPoblacion()
    {
        conexion = new Conexion();
        ArrayList<String> listaPoblaciones = new ArrayList<String>();
        String unaPoblacion;
        String consulta= "SELECT DISTINCT SOCIO_POBLACION FROM SOCIO ORDER BY SOCIO_POBLACION";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
              rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaPoblacion = rs.getString("SOCIO_POBLACION");
                listaPoblaciones.add(unaPoblacion);
            }
       }
        catch(SQLException ex)
        {
            listaPoblaciones = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return listaPoblaciones;
        }
    }

    public ArrayList<String> getProvincia()
    {
        conexion = new Conexion();
        ArrayList<String> listaProvincias = new ArrayList<String>();
        String unaProvincia;
        String consulta= "SELECT DISTINCT SOCIO_PROVINCIA FROM SOCIO ORDER BY SOCIO_PROVINCIA";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaProvincia = rs.getString("SOCIO_PROVINCIA");
                listaProvincias.add(unaProvincia);
            }
       }
        catch(SQLException ex)
        {
            listaProvincias = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return listaProvincias;
        }
    }


    public ArrayList<Socio> getSocio(int pIdSocio, String pNumSocio, String pDni, String pNombre, String pApellidos,  String pSexo ,Date pFechaNac, String pProfesion, String pAntiguedad, Foto pFoto, String pObservaciones, TipoSocio pTipoSocio, boolean pMartuberri, String pDireccion, String pCP, String pPoblacion, String pProvincia, String pTelefono1, String pTelefono2, String pFax, String pEmail, String pBanco, String pNumCuenta, int pIdUsuario)
    {
        conexion = new Conexion();
        ArrayList<Socio> listaSocios = new ArrayList<Socio>();
        Socio unSocio = null;
        FotoDAL fotoDAL = new FotoDAL();
        Foto unaFoto = null;
        TipoSocioDAL tipoSocioDAL = new TipoSocioDAL();
        TipoSocio tipoSocio = null;
        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
        TipoPago unTipoPago = null;

        String consulta= "SELECT * FROM SOCIO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdSocio != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_ID = " + pIdSocio;
                     bConsul=true;
                 }
            }

             if (pNumSocio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_NUM_SOCIO='" + pNumSocio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_NUM_SOCIO='" + pNumSocio + "'";
             }

             if (pDni != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_DNI='" + pDni + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_DNI='" + pDni + "'";
             }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_NOMBRE='" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_NOMBRE='" + pNombre + "'";
             }

             if (pApellidos != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_APELLIDOS='" + pApellidos + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_APELLIDOS='" + pApellidos + "'";
             }

             if (pSexo != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_SEXO='" + pSexo + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_SEXO='" + pSexo + "'";
             }

             if (pFechaNac != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_FECHANAC='" + pFechaNac + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_FECHANAC='" + pFechaNac + "'";
             }

             if (pProfesion != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_PROFESION='" + pProfesion + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_PROFESION='" + pProfesion + "'";
             }

             if (pAntiguedad != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_ANTIGUEDAD='" + pAntiguedad + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_ANTIGUEDAD='" + pAntiguedad + "'";
             }

             if (pProfesion != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_ANTIGUEDAD='" + pAntiguedad + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_ANTIGUEDAD='" + pAntiguedad + "'";
             }

             if (pFoto != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_TIPO=" + pFoto.getIdFoto();
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_TIPO=" + pFoto.getIdFoto();
             }

            /*
             if (pMartuberri != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_MARTUBERRI=" + pFoto.getIdFoto();
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_MARTUBERRI=" + pFoto.getIdFoto();
             }
             */
             
             if (pDireccion != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_DIRECCION='" + pDireccion + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_DIRECCION='" + pDireccion + "'";
             }

             if (pCP != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_CP='" + pCP + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_CP='" + pCP + "'";
             }

             if (pPoblacion != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_POBLACION='" + pPoblacion + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_POBLACION='" + pPoblacion + "'";
             }

             if (pProvincia != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_PROVINCIA='" + pProvincia + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_PROVINCIA='" + pProvincia + "'";
             }

             if (pTelefono1 != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_TELEFONO1='" + pTelefono1 + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_TELEFONO1='" + pTelefono1 + "'";
             }

             if (pTelefono2 != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_TELEFONO2='" + pTelefono2 + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_TELEFONO2='" + pTelefono2 + "'";
             }

             if (pFax != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_FAX='" + pFax + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_FAX='" + pFax + "'";
             }

             if (pEmail != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_EMAIL='" + pEmail + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_EMAIL='" + pEmail + "'";
             }

             if (pBanco != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_BANCO='" + pBanco + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_BANCO='" + pBanco + "'";
             }

             if (pNumCuenta != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_NUMCUENTA='" + pNumCuenta + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_NUMCUENTA='" + pNumCuenta + "'";
             }

             if (pIdUsuario != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_IDUSUARIO='" + pIdUsuario;
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_IDUSUARIO='" + pIdUsuario;
             }


           
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                tipoSocio = tipoSocioDAL.getTipoSocio(rs.getInt("SOCIO_TIPO"));
                unaFoto = fotoDAL.getFoto(rs.getString("SOCIO_IDFOTO"));
                unTipoPago = tipoPagoDAL.getTipoPago(rs.getInt("SOCIO_TIPO_PAGO"));
                unSocio = new Socio(rs.getInt("SOCIO_ID"),rs.getString("SOCIO_NUM_SOCIO"),rs.getString("SOCIO_DNI"),rs.getString("SOCIO_NOMBRE"),rs.getString("SOCIO_APELLIDOS"),rs.getString("SOCIO_SEXO"),rs.getDate("SOCIO_FECHANAC"),rs.getString("SOCIO_PROFESION"),rs.getString("SOCIO_ANTIGUEDAD"),rs.getDate("SOCIO_FECHAALTA"), unaFoto, rs.getString("SOCIO_OBSERVACIONES"), tipoSocio, rs.getBoolean("SOCIO_MARTUBERRI"),rs.getString("SOCIO_DIRECCION"),rs.getString("SOCIO_CP"),rs.getString("SOCIO_POBLACION"),rs.getString("SOCIO_PROVINCIA"), rs.getString("SOCIO_TELEFONO1"),rs.getString("SOCIO_TELEFONO2"),rs.getString("SOCIO_EMAIL"),rs.getString("SOCIO_FAX"),rs.getString("SOCIO_BANCO"),rs.getString("SOCIO_NUMCUENTA"),rs.getInt("SOCIO_IDUSUARIO"),unTipoPago);

                listaSocios.add(unSocio);
            }
       }

        catch(SQLException ex)
        {
            listaSocios = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return listaSocios;
        }
}

    public ArrayList<Socio> busquedaSocio(String pNumSocio, String pDni, String pNombre, String pApellidos,  String pSexo ,String pAntiguedad, TipoSocio pTipoSocio, String pMartuberri,String pCPInicio, String pCPFinal, String pPoblacion, String pProvincia)
    {
        conexion = new Conexion();
        ArrayList<Socio> listaSocios = new ArrayList<Socio>();
        Socio unSocio = null;
        FotoDAL fotoDAL = new FotoDAL();
        Foto unaFoto = null;
        TipoSocioDAL tipoSocioDAL = new TipoSocioDAL();
        TipoSocio tipoSocio = null;
        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
        TipoPago unTipoPago = null;

        String consulta= "SELECT * FROM SOCIO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {

             if (pNumSocio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_NUM_SOCIO='" + pNumSocio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_NUM_SOCIO='" + pNumSocio + "'";
             }

             if (pDni != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_DNI='" + pDni + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_DNI='" + pDni + "'";
             }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_NOMBRE='" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_NOMBRE='" + pNombre + "'";
             }

             if (pApellidos != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_APELLIDOS='" + pApellidos + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_APELLIDOS='" + pApellidos + "'";
             }

             if (pSexo != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_SEXO='" + pSexo + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_SEXO='" + pSexo + "'";
             }

             if (pAntiguedad != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_ANTIGUEDAD='" + pAntiguedad + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_ANTIGUEDAD='" + pAntiguedad + "'";
             }


             if (pTipoSocio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_TIPO=" + pTipoSocio.getIdTipoSocio();
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_TIPO=" + pTipoSocio.getIdTipoSocio();
             }

            
             if (pMartuberri != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_MARTUBERRI=" + pMartuberri;
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_MARTUBERRI=" + pMartuberri;
             }
             



             if (pCPInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (SOCIO_CP>'" + pCPInicio + "' OR SOCIO_CP = '" + pCPInicio + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (SOCIO_CP>'" + pCPInicio + "' OR SOCIO_CP = '" + pCPInicio + "')";
             }
             
              if (pCPFinal != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (SOCIO_CP<'" + pCPFinal + "' OR SOCIO_CP = '" + pCPFinal + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (SOCIO_CP<'" + pCPFinal + "' OR SOCIO_CP = '" + pCPFinal + "')";
             }


             if (pPoblacion != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_POBLACION='" + pPoblacion + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_POBLACION='" + pPoblacion + "'";
             }

             if (pProvincia != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_PROVINCIA='" + pProvincia + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND SOCIO_PROVINCIA='" + pProvincia + "'";
             }

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                tipoSocio = tipoSocioDAL.getTipoSocio(rs.getInt("SOCIO_TIPO"));
                unaFoto = fotoDAL.getFoto(rs.getString("SOCIO_IDFOTO"));
                unTipoPago = tipoPagoDAL.getTipoPago(rs.getInt("SOCIO_TIPO_PAGO"));
                unSocio = new Socio(rs.getInt("SOCIO_ID"),rs.getString("SOCIO_NUM_SOCIO"),rs.getString("SOCIO_DNI"),rs.getString("SOCIO_NOMBRE"),rs.getString("SOCIO_APELLIDOS"),rs.getString("SOCIO_SEXO"),rs.getDate("SOCIO_FECHANAC"),rs.getString("SOCIO_PROFESION"),rs.getString("SOCIO_ANTIGUEDAD"),rs.getDate("SOCIO_FECHAALTA"), unaFoto, rs.getString("SOCIO_OBSERVACIONES"), tipoSocio, rs.getBoolean("SOCIO_MARTUBERRI"),rs.getString("SOCIO_DIRECCION"),rs.getString("SOCIO_CP"),rs.getString("SOCIO_POBLACION"),rs.getString("SOCIO_PROVINCIA"), rs.getString("SOCIO_TELEFONO1"),rs.getString("SOCIO_TELEFONO2"),rs.getString("SOCIO_EMAIL"),rs.getString("SOCIO_FAX"),rs.getString("SOCIO_BANCO"),rs.getString("SOCIO_NUMCUENTA"),rs.getInt("SOCIO_IDUSUARIO"),unTipoPago);

                listaSocios.add(unSocio);
            }
       }

        catch(SQLException ex)
        {
            listaSocios = null;
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException ex)
                {
                    System.out.println("No se ha podido cerrar el ResulSet.");
                }
            }
            conexion.cerrar();
            return listaSocios;
        }
}


   public boolean modificarSocio(int pIdSocio, String pNumSocio, String pDNI, String pNombre, String pApellidos, String pSexo, Date pFechaNac, String pProfesion, String pAntiguedad, int pIdFoto, String pObservaciones, int pIdTipoSocio, boolean pMartuberri, String pDireccion, String pCP, String pPoblacion, String pProvincia, String pTelefono1, String pTelefono2, String pFax, String pEmail, String pBanco, String pNumCuenta, int pIdUsuario, Date pFechaAlta, int pIdTipoPago)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE SOCIO SET SOCIO_NUM_SOCIO='" + pNumSocio + "', SOCIO_DNI='" + pDNI + "', SOCIO_NOMBRE='" + pNombre + "', SOCIO_APELLIDOS='" + pApellidos + "', SOCIO_SEXO='" + pSexo + "', SOCIO_FECHANAC='" + pFechaNac + "', SOCIO_PROFESION='" + pProfesion + "', SOCIO_ANTIGUEDAD='" + pAntiguedad + "', SOCIO_IDFOTO=" + pIdFoto + ", SOCIO_OBSERVACIONES='" + pObservaciones + "', SOCIO_TIPO=" + pIdTipoSocio + ", SOCIO_MARTUBERRI=" + pMartuberri + ", SOCIO_DIRECCION='" + pDireccion + "', SOCIO_CP='" + pCP + "', SOCIO_POBLACION='" + pPoblacion + "', SOCIO_PROVINCIA='" + pProvincia + "', SOCIO_TELEFONO1='" + pTelefono1 + "', SOCIO_TELEFONO2='" + pTelefono2 + "', SOCIO_FAX='" + pFax + "', SOCIO_FAX='" + pFax + "', SOCIO_EMAIL='" + pEmail + "', SOCIO_BANCO='" + pBanco + "', SOCIO_NUMCUENTA='" + pNumCuenta + "', SOCIO_IDUSUARIO=" + pIdUsuario + ", SOCIO_FECHAALTA='" + pFechaAlta + "', SOCIO_TIPO_PAGO=" + pIdTipoPago + " WHERE SOCIO_ID=" + pIdSocio;
        try
        {
            numFilas = conexion.getStatement().executeUpdate(consulta);
            if (numFilas == 0) exito = false;
        }
        catch(SQLException ex)
        {
            System.out.println("No se ha podido realizar la modificación.");
        }
        finally
        {
            conexion.cerrar();
            return exito;
        }

    }


   public boolean insertarSocio(String pNumSocio, String pDNI, String pNombre, String pApellidos, String pSexo, Date pFechaNac, String pProfesion, String pAntiguedad, int pIdFoto, String pObservaciones, int pIdTipoSocio, boolean pMartuberri, String pDireccion, String pCP, String pPoblacion, String pProvincia, String pTelefono1, String pTelefono2, String pFax, String pEmail, String pBanco, String pNumCuenta, int pIdUsuario, Date pFechaAlta, int pIdTipoPago)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO SOCIO (SOCIO_NUM_SOCIO, SOCIO_DNI, SOCIO_NOMBRE, SOCIO_APELLIDOS, SOCIO_SEXO, SOCIO_FECHANAC, SOCIO_PROFESION, SOCIO_ANTIGUEDAD, SOCIO_IDFOTO, SOCIO_OBSERVACIONES, SOCIO_TIPO, SOCIO_MARTUBERRI, SOCIO_DIRECCION, SOCIO_CP, SOCIO_POBLACION, SOCIO_PROVINCIA, SOCIO_TELEFONO1, SOCIO_TELEFONO2, SOCIO_FAX, SOCIO_EMAIL, SOCIO_BANCO, SOCIO_NUMCUENTA, SOCIO_IDUSUARIO, SOCIO_FECHAALTA, SOCIO_TIPO_PAGO) VALUES ('" + pNumSocio + "','" + pDNI + "','" + pNombre + "','" + pApellidos + "','" + pSexo + "','" + pFechaNac + "','" + pProfesion + "','" + pAntiguedad + "'," + pIdFoto + ",'" + pObservaciones + "'," + pIdTipoSocio + "," + pMartuberri + ",'" + pDireccion + "','" + pCP + "','" + pPoblacion + "','" + pProvincia + "','" + pTelefono1 + "','" + pTelefono2 + "','" + pFax + "','" + pEmail + "','" + pBanco + "','" + pNumCuenta + "'," + pIdUsuario + ",'" + pFechaAlta + "'," + pIdTipoPago + ")";
        
        try
        {
            numFilas = conexion.getStatement().executeUpdate(consulta);
            if (numFilas == 0) exito = false;
        }
        catch(SQLException ex)
        {
            System.out.println("No se ha podido realizar la modificación.");
        }
        finally
        {
            conexion.cerrar();
            return exito;
        }

    }


    public boolean eliminarSocio(int pIdSocio)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM SOCIO WHERE SOCIO_ID=" + pIdSocio;
        try
        {
            numFilas = conexion.getStatement().executeUpdate(consulta);
            if (numFilas == 0) exito = false;
        }
        catch(SQLException ex)
        {
            System.out.println("No se ha podido realizar la eliminación.");
        }
        finally
        {
            conexion.cerrar();
            return exito;
        }
    }

    public boolean quitarFoto(int pIdFoto)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE SOCIO SET SOCIO_IDFOTO = 1 WHERE SOCIO_IDFOTO=" + pIdFoto;
        try
        {
            numFilas = conexion.getStatement().executeUpdate(consulta);
            if (numFilas == 0) exito = false;
        }
        catch(SQLException ex)
        {
            System.out.println("No se ha podido realizar la modificación.");
        }
        finally
        {
            conexion.cerrar();
            return exito;
        }

    }

}
