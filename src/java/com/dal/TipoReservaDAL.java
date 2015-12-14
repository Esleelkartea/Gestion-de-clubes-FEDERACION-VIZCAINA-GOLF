/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.TipoReserva;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class TipoReservaDAL {

    Conexion conexion;

    public TipoReservaDAL()
    {

    }


    public TipoReserva getTipoReserva(int idTipoReserva)
    {
        conexion = new Conexion();
        TipoReserva unTipoReserva = null;
        String consulta= "SELECT * FROM TIPO_RESERVA WHERE TIPO_RESERVA_ID = " + idTipoReserva;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoReserva = new TipoReserva(rs.getInt("TIPO_RESERVA_ID"), rs.getString("TIPO_RESERVA_ESTADO"), rs.getInt("TIPO_RESERVA_NORMAS"));

        }
        catch(Exception e)
        {
            unTipoReserva = null;
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
            return unTipoReserva;
        }
     }


    public TipoReserva getTipoReservaPendiente()
    {
        conexion = new Conexion();
        TipoReserva unTipoReserva = null;
        String consulta= "SELECT * FROM TIPO_RESERVA WHERE TIPO_RESERVA_ESTADO = 'Pendiente de Pago'";
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoReserva = new TipoReserva(rs.getInt("TIPO_RESERVA_ID"), rs.getString("TIPO_RESERVA_ESTADO"), rs.getInt("TIPO_RESERVA_NORMAS"));

        }
        catch(Exception e)
        {
            unTipoReserva = null;
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
            return unTipoReserva;
        }
     }


    public ArrayList<TipoReserva> getTipoReserva(int pIdTipoReserva, String pEstado, int pNormas)
    {
        conexion = new Conexion();
        ArrayList<TipoReserva> listaTipoReserva = new ArrayList<TipoReserva>();
        TipoReserva unTipoReserva;
        String consulta= "SELECT * FROM TIPO_RESERVA";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdTipoReserva != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_RESERVA_ID = " + pIdTipoReserva;
                     bConsul=true;
                 }
            }

             if (pEstado != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_RESERVA_ESTADO = '" + pEstado + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND TIPO_RESERVA_ESTADO = '" + pEstado + "'";
             }


             if (pNormas != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_RESERVA_NORMAS = " + pNormas;
                     bConsul=true;
                 }
                 else
                     consulta += " AND TIPO_RESERVA_NORMAS = " + pNormas;
             }



             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unTipoReserva = new TipoReserva(rs.getInt("TIPO_RESERVA_ID"), rs.getString("TIPO_RESERVA_ESTADO"), rs.getInt("TIPO_RESERVA_NORMAS"));
                listaTipoReserva.add(unTipoReserva);
            }
       }
        catch(SQLException ex)
        {
            listaTipoReserva = null;
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
            return listaTipoReserva;
        }
    }


    public boolean insertarTipoReserva(String pEstado, int pNormas)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO TIPO_RESERVA  (TIPO_RESERVA_ESTADO, TIPO_RESERVA_NORMAS) VALUES ('" + pEstado + "'," + pNormas + ")";
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


    public boolean modificarTipoReserva(int pIdTipoReserva, String pEstado, int pNormas)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE TIPO_RESERVA SET TIPO_RESERVA_ESTADO ='" + pEstado + "', TIPO_RESERVA_NORMAS=" + pNormas + " WHERE TIPO_RESERVA_ID=" + pIdTipoReserva;
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

    public boolean eliminarTipoReserva(int pIdTipoReserva)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM TIPO_RESERVA WHERE TIPO_RESERVA_ID=" + pIdTipoReserva;
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
