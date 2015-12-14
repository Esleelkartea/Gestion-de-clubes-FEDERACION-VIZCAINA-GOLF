/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import java.sql.*;
import java.util.ArrayList;
import com.Negocio.Foto;

/**
 *
 * @author Julen
 */
public class FotoDAL {

    //Atributos
    Conexion conexion;

    //Constructor
    public FotoDAL()
    {

    }

    //Otros metodos
    //Devuelve una foto de un idFoto
    public Foto getFoto(String pIdFoto)
    {
        conexion = new Conexion();
        Foto unaFoto = null;
        String consulta= "SELECT * FROM FOTO WHERE FOTO_ID = " + pIdFoto;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unaFoto = new Foto(rs.getInt("FOTO_ID"),rs.getString("FOTO_NOMBRE"),rs.getString("FOTO_URL"),rs.getString("FOTO_COMENTARIO"));
        }
        catch(Exception e)
        {
            unaFoto = null;
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
            return unaFoto;
        }
     }

    //Devuelve una foto dado un nombre y un comentario
     public Foto getFoto(String pNombre, String pComentario)
    {
        conexion = new Conexion();
        Foto unaFoto = null;
        String consulta= "SELECT * FROM FOTO WHERE FOTO_NOMBRE = '" + pNombre+"' AND FOTO_COMENTARIO='"+pComentario+"'";
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unaFoto = new Foto(rs.getInt("FOTO_ID"),rs.getString("FOTO_NOMBRE"),rs.getString("FOTO_URL"),rs.getString("FOTO_COMENTARIO"));
        }
        catch(Exception e)
        {
            unaFoto = null;
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
            return unaFoto;
        }
     }

    //Devuelve un arraylist de fotos de una busqueda por nombre, idFoto, url y/o comentario
    public ArrayList<Foto> getFoto(String pIdFoto, String pNombre, String pURL, String pComentario)
    {
        conexion = new Conexion();
        ArrayList<Foto> listaFotos = new ArrayList<Foto>();
        Foto unaFoto;
        String consulta= "SELECT * FROM FOTO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdFoto != null){
                 if (bConsul==false)
                 {
                     consulta += "WHERE FOTO_ID = " + pIdFoto;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE FOTO_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND FOTO_NOMBRE = '" + pNombre + "'";
             }


             if (pURL != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE FOTO_URL = '" + pURL + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND FOTO_URL = '" + pURL + "'";
             }

             if (pComentario != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE FOTO_COMENTARIO LIKE '%" + pComentario + "%'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND FOTO_COMENTARIO LIKE '%" + pComentario + "%'";
             }

             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaFoto = new Foto(rs.getInt("FOTO_ID"),rs.getString("FOTO_NOMBRE"),rs.getString("FOTO_URL"),rs.getString("FOTO_COMENTARIO"));
                listaFotos.add(unaFoto);
            }
       }
        catch(SQLException ex)
        {
            listaFotos = null;
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
            return listaFotos;
        }
    }

    //Inserta un registro en la tabla foto en la base de datos pasandole todos los campos
    public boolean insertarFoto(String pNombre, String pURL, String pComentario)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO FOTO  (FOTO_NOMBRE, FOTO_URL, FOTO_COMENTARIO) VALUES ('" + pNombre + "','" + pURL + "','" + pComentario + "')";
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

    //Modifica un registro de la tabla Foto de la base de datos pasandole todos los atributos
    public boolean modificarFoto(String pIdFoto, String pURL, String pNombre, String pComentario)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE FOTO SET FOTO_NOMBRE='" + pNombre + "', FOTO_URL ='" + pURL + "', FOTO_COMENTARIO ='" + pComentario + "' WHERE FOTO_ID=" + pIdFoto;
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

    //Modifica un registro de la tabla Foto de la base de datos pasandole IdFoto, nombre y comentario
    public boolean modificarFoto(String pIdFoto, String pNombre, String pComentario)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE FOTO SET FOTO_NOMBRE ='" + pNombre + "', FOTO_COMENTARIO ='" + pComentario + "' WHERE FOTO_ID=" + pIdFoto;
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

    //Eliminar un registro de la tabla Foto de la base de datos
    public boolean eliminarFoto(String pIdFoto)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM FOTO WHERE FOTO_ID=" + pIdFoto;
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

    public boolean quitarFoto(String pIdFoto)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE FOTO SET FOTO_ID = 1 WHERE FOTO_ID=" + pIdFoto;
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

