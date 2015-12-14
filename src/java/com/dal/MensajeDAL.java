/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Mensaje;
import com.Negocio.Usuario;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class MensajeDAL {

    Conexion conexion;

    public MensajeDAL()  
    {

    }

    //Devuelve un mensaje de un idMensaje
    public Mensaje getMensaje(int idMensaje)
    {
        conexion = new Conexion();
        UsuarioDAL usuarioDAL = new UsuarioDAL();
        Usuario usuarioEmisor;
        Usuario usuarioDest;
        Mensaje unMensaje = null;
        String consulta= "SELECT * FROM MENSAJES WHERE MENSAJES_ID = " + idMensaje;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 usuarioEmisor = usuarioDAL.getUsuario(rs.getInt("MENSAJES_ID_EMISOR"));
                 usuarioDest = usuarioDAL.getUsuario(rs.getInt("MENSAJES_ID_DESTINATARIO"));
                 unMensaje = new Mensaje(rs.getInt("MENSAJES_ID"),rs.getDate("MENSAJES_FECHA"),usuarioEmisor, usuarioDest,rs.getString("MENSAJES_ASUNTO"),rs.getString("MENSAJES_TEXTO"));
            }
        }
        catch(Exception e)
        {
            unMensaje = null;
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
            return unMensaje;
        }
     }


    //Devuelve un arraylist de mensajes de una busqueda
    public ArrayList<Mensaje> getMensaje(int pIdMensaje, Date pFecha, Usuario pEmisor, Usuario pDestinatario, String pAsunto, String pTexto)
    {
        conexion = new Conexion();
        UsuarioDAL usuarioDAL = new UsuarioDAL();
        Usuario usuEmisor;
        Usuario usuDest;
        ArrayList<Mensaje> listaMensajes = new ArrayList<Mensaje>();
        Mensaje unMensaje;
        String consulta= "SELECT * FROM MENSAJES ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdMensaje != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE MENSAJES_ID = " + pIdMensaje;
                     bConsul=true;
                 }
            }

             if (pFecha != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE MENSAJES_FECHA = '" + pFecha + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND MENSAJES_FECHA = '" + pFecha + "'";
             }


              if (pEmisor != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE MENSAJES_ID_EMISOR = " + pEmisor.getIdUsuario();
                     bConsul=true;
                 }
                 else
                     consulta += " AND MENSAJES_ID_EMISOR = " + pEmisor.getIdUsuario();
             }

              if (pDestinatario != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE MENSAJES_ID_DESTINATARIO = " + pDestinatario.getIdUsuario();
                     bConsul=true;
                 }
                 else
                     consulta += " AND MENSAJES_ID_DESTINATARIO = " + pDestinatario.getIdUsuario();
             }

             if (pAsunto != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE MENSAJES_ASUNTO = '" + pAsunto + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND MENSAJES_ASUNTO = '" + pAsunto + "'";
             }

             if (pTexto != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE MENSAJES_TEXTO = '" + pTexto + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND MENSAJES_TEXTO = '" + pTexto + "'";
             }

             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                usuEmisor = usuarioDAL.getUsuario(rs.getInt("MENSAJES_ID_EMISOR"));
                usuDest = usuarioDAL.getUsuario(rs.getInt("MENSAJES_ID_DESTINATARIO"));
                unMensaje = new Mensaje(rs.getInt("MENSAJES_ID"),rs.getDate("MENSAJES_FECHA"),usuEmisor, usuDest,rs.getString("MENSAJES_ASUNTO"),rs.getString("MENSAJES_TEXTO"));
                listaMensajes.add(unMensaje);
            }
       }
        catch(SQLException ex)
        {
            listaMensajes = null;
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
            return listaMensajes;
        }
    }

     public boolean insertarMensaje(Date pFecha, int pUsuEmisor, int pUsuDest, String pAsunto, String pTexto)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO MENSAJES  (MENSAJES_FECHA, MENSAJES_ID_EMISOR, MENSAJES_ID_DESTINATARIO, MENSAJES_ASUNTO, MENSAJES_TEXTO) VALUES ('" + pFecha + "'," + pUsuEmisor + "," + pUsuDest + ",'" + pAsunto + "','" + pTexto + "')";
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

     public boolean eliminarMenaje(int pIdMensaje)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM MENSAJES WHERE MENSAJES_ID=" + pIdMensaje;
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

    public boolean eliminarMensajesEmisorReceptor(int pIdUsuario)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM MENSAJES WHERE MENSAJES_ID_EMISOR=" + pIdUsuario + " OR MENSAJES_ID_DESTINATARIO=" + pIdUsuario;
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
