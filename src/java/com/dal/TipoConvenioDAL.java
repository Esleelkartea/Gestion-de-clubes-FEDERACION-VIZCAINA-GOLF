/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.TipoConvenio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class TipoConvenioDAL {

    Conexion conexion;

    public TipoConvenioDAL()
    {

    }


    //Devuelve un tipo de convenio de un idTipoConvenio
    public TipoConvenio getTipoConvenio(int idTipoConvenio)
    {
        conexion = new Conexion();
        TipoConvenio unTipoConvenio = null;
        String consulta= "SELECT * FROM TIPO_CONVENIO WHERE TIPO_CONVENIO_ID = " + idTipoConvenio;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoConvenio = new TipoConvenio(rs.getInt("TIPO_CONVENIO_ID"),rs.getString("TIPO_CONVENIO_NOMBRE"));
        }
        catch(Exception e)
        {
            unTipoConvenio = null;
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
            return unTipoConvenio;
        }
     }


     //Devuelve un arraylist de TipoConvenio de una busqueda por nombre y/o idTipoConvenio
    public ArrayList<TipoConvenio> getTipoConvenio(int pIdTipoConvenio, String pNombre)
    {
        conexion = new Conexion();
        ArrayList<TipoConvenio> listaTipoConvenio = new ArrayList<TipoConvenio>();
        TipoConvenio unTipoConvenio;
        String consulta= "SELECT * FROM TIPO_CONVENIO";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdTipoConvenio != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_CONVENIO_ID = " + pIdTipoConvenio;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_CONVENIO_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND TIPO_CONVENIO_NOMBRE = '" + pNombre + "'";
             }



             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unTipoConvenio = new TipoConvenio(rs.getInt("TIPO_CONVENIO_ID"), rs.getString("TIPO_CONVENIO_NOMBRE"));
                listaTipoConvenio.add(unTipoConvenio);
            }
       }
        catch(SQLException ex)
        {
            listaTipoConvenio = null;
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
            return listaTipoConvenio;
        }
    }

    //Inserta un registro en la tabla TIPO_CONVENIO en la base de datos pasandole todos los campos
    public boolean insertarTipoConvenio(String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO TIPO_CONVENIO (TIPO_CONVENIO_NOMBRE) VALUES ('" + pNombre + "')";
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

//Modifica un registro de la tabla TIPO_CONVENIO de la base de datos pasandole todos los atributos
    public boolean modificarTipoConvenio(int pIdTipoConvenio, String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE TIPO_CONVENIO SET TIPO_CONVENIO_NOMBRE ='" + pNombre + "' WHERE TIPO_CONVENIO_ID=" + pIdTipoConvenio;
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

    //Eliminar un registro de la tabla TIPO_CONVENIO de la base de datos
    public boolean eliminarTipoConvenio(int pIdTipoConvenio)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM TIPO_CONVENIO WHERE TIPO_CONVENIO_ID=" + pIdTipoConvenio;
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
