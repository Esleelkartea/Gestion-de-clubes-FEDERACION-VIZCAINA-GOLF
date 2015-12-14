/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Socio;
import com.Negocio.SocioFamilia;
import com.Negocio.TipoFamiliar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class SocioFamiliaDAL {

    Conexion conexion;

    //Constructor
    public SocioFamiliaDAL()
    {

    }

    public SocioFamilia getSocioFamilia(int idSocioFamilia)
    {
        conexion = new Conexion();
        SocioFamilia unSocioFamilia = null;
        TipoFamiliar unTipoFamiliar = null;
        TipoFamiliarDAL tipoFamiliarDAL = new TipoFamiliarDAL();
        Socio unSocio = null;
        SocioDAL socioDAL = new SocioDAL();
        
        String consulta= "SELECT * FROM SOCIO_FAMILIA WHERE SOCIO_FAMILIA_ID = " + idSocioFamilia;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 unSocio = socioDAL.getSocioId(rs.getInt("SOCIO_FAMILIA_FAMILIAR_ID"));
                 unTipoFamiliar = tipoFamiliarDAL.getTipoFamiliar(rs.getInt("SOCIO_FAMILIA_TIPO_ID"));
                 unSocioFamilia = new SocioFamilia(rs.getInt("SOCIO_FAMILIA_ID"), rs.getInt("SOCIO_FAMILIA_SOCIO_ID"), unSocio, unTipoFamiliar);
            }
        }
        catch(Exception e)
        {
            unSocioFamilia = null;
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
            return unSocioFamilia;
        }
     }


    public ArrayList<SocioFamilia> getSocioFamilias(int pIdSocioFamilia)
    {
        conexion = new Conexion();
        ArrayList<SocioFamilia> listaSocioFamiliar = new ArrayList<SocioFamilia>();
        SocioFamilia unSocioFamilia;
        String consulta= "SELECT * FROM SOCIO_FAMILIA ";
        ResultSet rs = null;
        boolean bConsul = false;
        TipoFamiliarDAL tipoFamiliarDAL = new TipoFamiliarDAL();
        TipoFamiliar unTipoFamiliar = null;
        Socio unSocio = null;
        SocioDAL socioDAL = new SocioDAL();

        try
        {
             if (pIdSocioFamilia != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE SOCIO_FAMILIA_SOCIO_ID = " + pIdSocioFamilia;
                     bConsul=true;
                 }
            }

             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unSocio = socioDAL.getSocioId(rs.getInt("SOCIO_FAMILIA_FAMILIAR_ID"));
                unTipoFamiliar = tipoFamiliarDAL.getTipoFamiliar(rs.getInt("SOCIO_FAMILIA_TIPO_ID"));
                unSocioFamilia = new SocioFamilia(rs.getInt("SOCIO_FAMILIA_ID"), rs.getInt("SOCIO_FAMILIA_SOCIO_ID"), unSocio, unTipoFamiliar);
                listaSocioFamiliar.add(unSocioFamilia);
            }
       }
        catch(SQLException ex)
        {
            listaSocioFamiliar = null;
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
            return listaSocioFamiliar;
        }
    }


    public boolean insertarSocioFamilia(int pSocio, int pFamiliar, TipoFamiliar pTipoFamiliar)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO SOCIO_FAMILIA  (SOCIO_FAMILIA_SOCIO_ID, SOCIO_FAMILIA_FAMILIAR_ID, SOCIO_FAMILIA_TIPO_ID) VALUES (" + pSocio + "," + pFamiliar + "," + pTipoFamiliar.getIdTipoFamiliar() + ")";
        try
        {
            numFilas = conexion.getStatement().executeUpdate(consulta);
            if (numFilas == 0) exito = false;
        }
        catch(SQLException ex)
        {
            System.out.println("No se ha podido realizar la inserción.");
        }
        finally
        {
            conexion.cerrar();
            return exito;
        }
    }

    //Modifica un registro de la tabla TIPO_SOCIO de la base de datos pasandole todos los atributos
    public boolean modificarSocioFamilia(int pIdSocioFamilia, int pIdSocio, int pIdFamilia, TipoFamiliar pTipoFamiliar)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE SOCIO_FAMILIA SET SOCIO_FAMILIA_SOCIO_ID =" + pIdSocio + ", SOCIO_FAMILIA_FAMILIAR_ID=" + pIdFamilia + ", SOCIO_FAMILIA_TIPO_ID=" + pTipoFamiliar.getIdTipoFamiliar() + " WHERE SOCIO_FAMILIA_ID=" + pIdSocioFamilia;
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

    //Eliminar un registro de la tabla TIPO_INSTALACION de la base de datos
    public boolean eliminarSocioFamilia(int pIdSocioFamilia)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM SOCIO_FAMILIA WHERE SOCIO_FAMLIA_ID=" + pIdSocioFamilia;
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

    public boolean eliminarFamiliares(int pIdSocio)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM SOCIO_FAMILIA WHERE SOCIO_FAMLIA_SOCIO_ID=" + pIdSocio;
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



}
