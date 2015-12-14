/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.TipoInstalacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class TipoInstalacionDAL {

    Conexion conexion;

    public TipoInstalacionDAL()
    {

    }

    //Otros metodos
    //Devuelve un tipo de instalacion de un idTipoInstalacion
    public TipoInstalacion getTipoInstalacion(int idTipoInstalacion)
    {
        conexion = new Conexion();
        TipoInstalacion unaTipoInstalacion = null;
        String consulta= "SELECT * FROM TIPO_INSTALACION WHERE TIPO_INSTALACION_ID = " + idTipoInstalacion;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unaTipoInstalacion = new TipoInstalacion(rs.getInt("TIPO_INSTALACION_ID"),rs.getString("TIPO_INSTALACION_NOMBRE"));
        }
        catch(Exception e)
        {
            unaTipoInstalacion = null;
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
            return unaTipoInstalacion;
        }
     }

    //Devuelve un arraylist de TipoInstalacion de una busqueda por nombre y/o idTipoInstalacion
    public ArrayList<TipoInstalacion> getTipoInstalacion(int pIdTipoInstalacion, String pNombre)
    {
        conexion = new Conexion();
        ArrayList<TipoInstalacion> listaTipoInstalacion = new ArrayList<TipoInstalacion>();
        TipoInstalacion unaTipoInstalacion;
        String consulta= "SELECT * FROM TIPO_INSTALACION ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdTipoInstalacion != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_INSTALACION_ID = " + pIdTipoInstalacion;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_INSTALACION_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND TIPO_INSTALACION_NOMBRE = '" + pNombre + "'";
             }

            

             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaTipoInstalacion = new TipoInstalacion(rs.getInt("TIPO_INSTALACION_ID"),rs.getString("TIPO_INSTALACION_NOMBRE"));
                listaTipoInstalacion.add(unaTipoInstalacion);
            }
       }
        catch(SQLException ex)
        {
            listaTipoInstalacion = null;
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
            return listaTipoInstalacion;
        }
    }


    //Inserta un registro en la tabla tipoInstalacion en la base de datos pasandole todos los campos
    public boolean insertarTipoInstalacion(String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO TIPO_INSTALACION  (TIPO_INSTALACION_NOMBRE) VALUES ('" + pNombre + "')";
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


    //Modifica un registro de la tabla TIPO_INSTALACION de la base de datos pasandole todos los atributos
    public boolean modificarTipoInstalacion(int pIdTipoInstalacion, String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE TIPO_INSTALACION SET TIPO_INSTALACION_NOMBRE ='" + pNombre + "' WHERE TIPO_INSTALACION_ID=" + pIdTipoInstalacion;
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
    public boolean eliminarTipoInstalacion(int pIdTipoInstalacion)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM TIPO_INSTALACION WHERE TIPO_INSTALACION_ID=" + pIdTipoInstalacion;
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
