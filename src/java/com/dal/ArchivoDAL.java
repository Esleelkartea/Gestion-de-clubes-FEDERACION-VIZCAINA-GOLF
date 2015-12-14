/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Archivo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class ArchivoDAL {

    Conexion conexion;

    public ArchivoDAL()
    {

    }

    public Archivo getArchivo(int pIdArchivo)
    {
        conexion = new Conexion();
        Archivo unArchivo = null;
        String consulta= "SELECT * FROM ARCHIVO WHERE ARCHIVO_ID = " + pIdArchivo;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unArchivo = new Archivo(rs.getInt("ARCHIVO_ID"),rs.getString("ARCHIVO_NOMBRE"),rs.getString("ARCHIVO_URL"));
        }
        catch(Exception e)
        {
            unArchivo = null;
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
            return unArchivo;
        }
     }

    public ArrayList<Archivo> getArchivo(int pIdArchivo, String pNombre, String pURL)
    {
        conexion = new Conexion();
        ArrayList<Archivo> listaArchivo = new ArrayList<Archivo>();
        Archivo unArchivo;
        String consulta= "SELECT * FROM ARCHIVO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdArchivo != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE ARCHIVO_ID = " + pIdArchivo;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE ARCHIVO_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND ARCHIVO_NOMBRE = '" + pNombre + "'";
             }


             if (pURL != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE ARCHIVO_URL ='" + pURL + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND ARCHIVO_URL ='" + pURL + "'";
             }


            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unArchivo = new Archivo(rs.getInt("ARCHIVO_ID"),rs.getString("ARCHIVO_NOMBRE"),rs.getString("ARCHIVO_URL"));
                listaArchivo.add(unArchivo);
            }
       }
        catch(SQLException ex)
        {
            listaArchivo = null;
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
            return listaArchivo;
        }
    }

    public boolean insertarArchivo(String pNombre, String pURL)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO ARCHIVO (ARCHIVO_NOMBRE, ARCHIVO_URL) VALUES ('" + pNombre + "','" + pURL + "')";
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

    public boolean modificarArchivo(int pIdArchivo, String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE ARCHIVO SET ARCHIVO_NOMBRE ='" + pNombre + "' WHERE ARCHIVO_ID=" + pIdArchivo;
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

    public boolean eliminarArchivo(int pIdArchivo)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM ARCHIVO WHERE ARCHIVO_ID=" + pIdArchivo;
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
