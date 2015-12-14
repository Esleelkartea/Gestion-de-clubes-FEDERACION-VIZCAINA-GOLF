/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;


import com.Negocio.TipoSocio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Julen
 */
public class TipoSocioDAL {

    Conexion conexion;

    //Constructor
    public TipoSocioDAL()
    {

    }


    //Otros metodos
    //Devuelve un tipo de socio de un idTipoSocio
    public TipoSocio getTipoSocio(int idTipoSocio)
    {
        conexion = new Conexion();
        TipoSocio unTipoSocio = null;
        String consulta= "SELECT * FROM TIPO_SOCIO WHERE TIPO_SOCIO_ID = " + idTipoSocio;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoSocio = new TipoSocio(rs.getInt("TIPO_SOCIO_ID"),rs.getString("TIPO_SOCIO_NOMBRE"),rs.getDouble("TIPO_SOCIO_CUOTA"));
        }
        catch(Exception e)
        {
            unTipoSocio = null;
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
            return unTipoSocio;
        }
     }

    //Devuelve un arraylist de TipoSocios de una busqueda por nombre y/o idTipoSocio
    public ArrayList<TipoSocio> getTipoSocio(int pIdTipoSocio, String pNombre, double pCuota)
    {
        conexion = new Conexion();
        ArrayList<TipoSocio> listaTipoSocios = new ArrayList<TipoSocio>();
        TipoSocio unTipoSocio;
        String consulta= "SELECT * FROM TIPO_SOCIO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdTipoSocio != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_SOCIO_ID = " + pIdTipoSocio;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_SOCIO_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND TIPO_SOCIO_NOMBRE = '" + pNombre + "'";
             }

             if (pCuota != -1.0)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_SOCIO_CUOTA = " + pCuota;
                     bConsul=true;
                 }
                 else
                     consulta += " AND TIPO_SOCIO_CUOTA = " + pCuota;
             }


             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unTipoSocio = new TipoSocio(rs.getInt("TIPO_SOCIO_ID"),rs.getString("TIPO_SOCIO_NOMBRE"),rs.getDouble("TIPO_SOCIO_CUOTA"));
                listaTipoSocios.add(unTipoSocio);
            }
       }
        catch(SQLException ex)
        {
            listaTipoSocios = null;
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
            return listaTipoSocios;
        }
    }

    
    //Inserta un registro en la tabla tipoSocio en la base de datos pasandole todos los campos
    public boolean insertarTipoSocio(String pNombre, double pCuota)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO TIPO_SOCIO  (TIPO_SOCIO_NOMBRE, TIPO_SOCIO_CUOTA) VALUES ('" + pNombre + "'," + pCuota + ")";
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
    public boolean modificarTipoSocio(int pIdTipoSocio, String pNombre, double pCuota)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE TIPO_SOCIO SET TIPO_SOCIO_NOMBRE ='" + pNombre + "', TIPO_SOCIO_CUOTA=" + pCuota + " WHERE TIPO_SOCIO_ID=" + pIdTipoSocio;
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
    public boolean eliminarTipoSocio(int pIdTipoSocio)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM TIPO_SOCIO WHERE TIPO_SOCIO_ID=" + pIdTipoSocio;
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
