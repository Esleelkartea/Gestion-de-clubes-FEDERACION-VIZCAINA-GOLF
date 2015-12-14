/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Invitacion;
import com.Negocio.InvitacionPresentacion;
import com.Negocio.Socio;
import com.Negocio.TipoPago;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class InvitacionDAL {

    Conexion conexion;
    

    public InvitacionDAL()
    {

    }

    public Invitacion getInvitacion(int pIdInvitacion)
    {
        conexion = new Conexion();
        Invitacion unaInvitacion = null;
        SocioDAL socioDAL = new SocioDAL();
        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
        Socio unSocio = null;
        TipoPago tipoPago = null;

        String consulta= "SELECT * FROM INVITACION WHERE INVITACION_ID = " + pIdInvitacion;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unSocio = socioDAL.getSocio(rs.getInt("INVITACION_SOCIO_ID"));
                 tipoPago = tipoPagoDAL.getTipoPago(rs.getInt("INVITACION_PAGO_ID"));
                 unaInvitacion = new Invitacion(rs.getInt("INVITACION_ID"), unSocio, rs.getDate("INVITACION_FECHA"), rs.getString("INVITACION_INVITADO"), rs.getInt("INVITACION_CANTIDAD"), rs.getDouble("INVITACION_IMPORTE"), tipoPago );
        }
        catch(Exception e)
        {
            unaInvitacion = null;
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
            return unaInvitacion;
        }
     }



    public ArrayList<Invitacion> getInvitacion(int pIdInvitacion, String pFechaInicio, String pFechaFin, String pInvitado, String pNombre, String pApellidos)
    {
        conexion = new Conexion();
        ArrayList<Invitacion> listaInvitacion = new ArrayList<Invitacion>();
        Invitacion unaInvitacion;
        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
        TipoPago tipoPago = null;
        SocioDAL socioDAL = new SocioDAL();
        Socio unSocio = null;

        String consulta= "SELECT * FROM INVITACION ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdInvitacion != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE INVITACION_ID = " + pIdInvitacion;
                     bConsul=true;
                 }
            }           

             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (INVITACION_FECHA > '" + pFechaInicio + "' OR INVITACION_FECHA = '" + pFechaInicio + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (INVITACION_FECHA > '" + pFechaInicio + "' OR INVITACION_FECHA = '" + pFechaInicio + "')";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (INVITACION_FECHA < '" + pFechaFin + "' OR INVITACION_FECHA = '" + pFechaFin + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (INVITACION_FECHA < '" + pFechaFin + "' OR INVITACION_FECHA = '" + pFechaFin + "')";
             }



             if (pInvitado != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INVITACION_INVITADO ='" + pInvitado + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INVITACION_INVITADO ='" + pInvitado + "'";
             }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INVITACION_NOMBRE='" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INVITACION_NOMBRE ='" + pNombre + "'";
             }
             
             if (pApellidos != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INVITACION_APELLIDOS='" + pApellidos + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INVITACION_APELLIDOS ='" + pApellidos + "'";
             }




            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                 unSocio = socioDAL.getSocioId(rs.getInt("INVITACION_SOCIO_ID"));
                 tipoPago = tipoPagoDAL.getTipoPago(rs.getInt("INVITACION_PAGO_ID"));
                 unaInvitacion = new Invitacion(rs.getInt("INVITACION_ID"), unSocio, rs.getDate("INVITACION_FECHA"), rs.getString("INVITACION_INVITADO"), rs.getInt("INVITACION_CANTIDAD"), rs.getDouble("INVITACION_IMPORTE"), tipoPago );
                 listaInvitacion.add(unaInvitacion);
            }
       }
        catch(SQLException ex)
        {
            listaInvitacion = null;
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
            return listaInvitacion;
        }
    }

  



    public boolean insertarInvitacion(Socio pSocio, Date pFecha, String pInvitado, int pCantidad, double pImporte, TipoPago pTipoPago)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO INVITACION (INVITACION_SOCIO_ID, INVITACION_FECHA, INVITACION_INVITADO, INVITACION_CANTIDAD, INVITACION_IMPORTE, INVITACION_PAGO_ID, INVITACION_NOMBRE, INVITACION_APELLIDOS) VALUES (" + pSocio.getIdSocio() + ",'" + pFecha + "','" + pInvitado + "'," + pCantidad + "," + pImporte + "," + pTipoPago.getIdTipoPago() + ",'" + pSocio.getNombre() + "','" + pSocio.getApellidos() + "')";
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


    public boolean modificarInvitacion(int pIdInvitacion, Socio pSocio, Date pFecha, String pInvitado, int pCantidad, double pImporte, TipoPago pTipoPago)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE INVITACION SET INVITACION_SOCIO_ID =" + pSocio.getIdSocio() + ", INVITACION_FECHA ='" + pFecha + "', INVITACION_INVITADO ='" +  pInvitado + "', INVITACION_CANTIDAD =" + pCantidad + ", INVITACION_IMPORTE=" + pImporte  + ", INVITACION_PAGO_ID=" + pTipoPago.getIdTipoPago() + ", INVITACION_NOMBRE='" + pSocio.getNombre() + "', INVITACION_APELLIDOS='" + pSocio.getApellidos() + "' WHERE INVITACION_ID=" + pIdInvitacion;
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


    public boolean eliminarInvitacion(int pIdInvitacion)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM INVITACION WHERE INVITACION_ID=" + pIdInvitacion;
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
