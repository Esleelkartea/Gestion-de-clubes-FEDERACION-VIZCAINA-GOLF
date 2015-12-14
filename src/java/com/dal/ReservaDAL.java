/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Reserva;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class ReservaDAL {

    Conexion conexion;

    public ReservaDAL()
    {

    }


    public Reserva getReservaId(int pIdReserva)
    {
        conexion = new Conexion();
        Reserva unaReserva = null;
        
        String consulta= "SELECT * FROM RESERVA WHERE RESERVA_ID= " + pIdReserva;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 unaReserva = new Reserva(rs.getInt("RESERVA_ID"), rs.getInt("RESERVA_INSTALACION_ID"), rs.getDate("RESERVA_FECHA"),rs.getTime("RESERVA_HORA_INICIO"),rs.getTime("RESERVA_HORA_FIN"),rs.getInt("RESERVA_SOCIO_ID"), rs.getDouble("RESERVA_IMPORTE"), rs.getInt("RESERVA_ESTADO_ID"), rs.getInt("RESERVA_FORMA_PAGO_ID"));
            }
        }
        catch(Exception e)
        {
            unaReserva = null;
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
            return unaReserva;
        }
     }

    public ArrayList<Reserva> getReservasDia(int pIdInstalacion, Date pFechaInicio, Date pFechaFin)
    {
        conexion = new Conexion();
        ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
        Reserva unaReserva = null;

        String consulta= "SELECT * FROM RESERVA ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdInstalacion != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_INSTALACION_ID=" + pIdInstalacion;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_INSTALACION_ID=" + pIdInstalacion;
             }


             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (RESERVA_FECHA > '" + pFechaInicio + "' OR RESERVA_FECHA = '" + pFechaInicio + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (RESERVA_FECHA > '" + pFechaInicio + "' OR RESERVA_FECHA = '" + pFechaInicio + "')";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (RESERVA_FECHA < '" + pFechaFin + "' OR RESERVA_FECHA = '" + pFechaFin + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (RESERVA_FECHA < '" + pFechaFin + "' OR RESERVA_FECHA = '" + pFechaFin + "')";
             }

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaReserva = new Reserva(rs.getInt("RESERVA_ID"), rs.getInt("RESERVA_INSTALACION_ID"), rs.getDate("RESERVA_FECHA"), rs.getTime("RESERVA_HORA_INICIO"), rs.getTime("RESERVA_HORA_FIN"), rs.getInt("RESERVA_SOCIO_ID"), rs.getDouble("RESERVA_IMPORTE"), rs.getInt("RESERVA_ESTADO_ID"), rs.getInt("RESERVA_FORMA_PAGO_ID"));
                listaReservas.add(unaReserva);
            }
       }

        catch(SQLException ex)
        {
            listaReservas = null;
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
            return listaReservas;
        }
    }


    public ArrayList<Reserva> getReservasActualesYPendientes(int pIdSocio)
    {
        conexion = new Conexion();
        ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
        Reserva unaReserva = null;

        String consulta= "select * from reserva where (reserva_estado_id=1 or reserva_fecha>=curdate()) and reserva_socio_id=" + pIdSocio + " order by reserva_fecha;";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaReserva = new Reserva(rs.getInt("RESERVA_ID"), rs.getInt("RESERVA_INSTALACION_ID"), rs.getDate("RESERVA_FECHA"), rs.getTime("RESERVA_HORA_INICIO"), rs.getTime("RESERVA_HORA_FIN"), rs.getInt("RESERVA_SOCIO_ID"), rs.getDouble("RESERVA_IMPORTE"), rs.getInt("RESERVA_ESTADO_ID"), rs.getInt("RESERVA_FORMA_PAGO_ID"));
                listaReservas.add(unaReserva);
            }
       }

        catch(SQLException ex)
        {
            listaReservas = null;
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
            return listaReservas;
        }
    }


    public ArrayList<Reserva> getReservasDia2(int pIdInstalacion, Date pFechaInicio, Date pFechaFin, int pIdReserva)
    {
        conexion = new Conexion();
        ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
        Reserva unaReserva = null;

        String consulta= "SELECT * FROM RESERVA ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdInstalacion != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_INSTALACION_ID=" + pIdInstalacion;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_INSTALACION_ID=" + pIdInstalacion;
             }


             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (RESERVA_FECHA > '" + pFechaInicio + "' OR RESERVA_FECHA = '" + pFechaInicio + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (RESERVA_FECHA > '" + pFechaInicio + "' OR RESERVA_FECHA = '" + pFechaInicio + "')";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (RESERVA_FECHA < '" + pFechaFin + "' OR RESERVA_FECHA = '" + pFechaFin + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (RESERVA_FECHA < '" + pFechaFin + "' OR RESERVA_FECHA = '" + pFechaFin + "')";
             }

             
             if (pIdReserva != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_ID<>" + pIdReserva;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_ID<>" + pIdReserva;
             }




            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaReserva = new Reserva(rs.getInt("RESERVA_ID"), rs.getInt("RESERVA_INSTALACION_ID"), rs.getDate("RESERVA_FECHA"), rs.getTime("RESERVA_HORA_INICIO"), rs.getTime("RESERVA_HORA_FIN"), rs.getInt("RESERVA_SOCIO_ID"), rs.getDouble("RESERVA_IMPORTE"), rs.getInt("RESERVA_ESTADO_ID"), rs.getInt("RESERVA_FORMA_PAGO_ID"));
                listaReservas.add(unaReserva);
            }
       }

        catch(SQLException ex)
        {
            listaReservas = null;
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
            return listaReservas;
        }
    }



    public ArrayList<Reserva> busquedaReservas(int pIdSocio, int pIdInstalacion, Date pFechaInicio, Date pFechaFin, int pIdEstado, int pIdFormaPago)
    {
        conexion = new Conexion();
        ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
        Reserva unaReserva = null;

        String consulta= "SELECT * FROM RESERVA ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdInstalacion != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_INSTALACION_ID=" + pIdInstalacion;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_INSTALACION_ID=" + pIdInstalacion;
             }

             if (pIdSocio != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_SOCIO_ID=" + pIdSocio;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_SOCIO_ID=" + pIdSocio;
             }


             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (RESERVA_FECHA > '" + pFechaInicio + "' OR RESERVA_FECHA = '" + pFechaInicio + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (RESERVA_FECHA > '" + pFechaInicio + "' OR RESERVA_FECHA = '" + pFechaInicio + "')";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (RESERVA_FECHA < '" + pFechaFin + "' OR RESERVA_FECHA = '" + pFechaFin + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (RESERVA_FECHA < '" + pFechaFin + "' OR RESERVA_FECHA = '" + pFechaFin + "')";
             }

             if (pIdEstado != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_ESTADO_ID=" + pIdEstado;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_ESTADO_ID=" + pIdEstado;
             }

             if (pIdFormaPago != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_FORMA_PAGO_ID=" + pIdFormaPago;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_FORMA_PAGO_ID=" + pIdFormaPago;
             }

            rs = conexion.getStatement().executeQuery(consulta);
             while(rs.next())
            {
                unaReserva = new Reserva(rs.getInt("RESERVA_ID"), rs.getInt("RESERVA_INSTALACION_ID"), rs.getDate("RESERVA_FECHA"), rs.getTime("RESERVA_HORA_INICIO"), rs.getTime("RESERVA_HORA_FIN"), rs.getInt("RESERVA_SOCIO_ID"), rs.getDouble("RESERVA_IMPORTE"), rs.getInt("RESERVA_ESTADO_ID"), rs.getInt("RESERVA_FORMA_PAGO_ID"));
                listaReservas.add(unaReserva);
            }
       }

        catch(SQLException ex)
        {
            listaReservas = null;
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
            return listaReservas;
        }
    }

    public boolean insertarReserva(int pIdInstalacion, Date pFecha, Time pHoraInicio, Time pHoraFin, int pIdSocio, double pImporte, int pIdEstado, int pIdFormaPago)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO RESERVA (RESERVA_INSTALACION_ID, RESERVA_FECHA, RESERVA_HORA_INICIO, RESERVA_HORA_FIN, RESERVA_SOCIO_ID, RESERVA_IMPORTE, RESERVA_ESTADO_ID, RESERVA_FORMA_PAGO_ID) VALUES (" + pIdInstalacion + ",'" + pFecha + "','" + pHoraInicio + "','" + pHoraFin + "'," + pIdSocio + "," + pImporte + "," + pIdEstado + "," + pIdFormaPago + ")";

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


    public boolean modificarReserva(int pIdReserva,int pIdInstalacion, Date pFecha, Time pHoraInicio, Time pHoraFin, int pIdSocio, double pImporte, int pIdEstado, int pIdFormaPago)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE RESERVA SET RESERVA_INSTALACION_ID=" + pIdInstalacion + ", RESERVA_FECHA='" + pFecha + "', RESERVA_HORA_INICIO='" + pHoraInicio + "', RESERVA_HORA_FIN='" + pHoraFin + "', RESERVA_SOCIO_ID=" + pIdSocio + ", RESERVA_IMPORTE=" + pImporte + ", RESERVA_ESTADO_ID=" + pIdEstado + ", RESERVA_FORMA_PAGO_ID=" + pIdFormaPago +  " WHERE RESERVA_ID=" + pIdReserva;
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

    public boolean eliminarReserva(int pIdReserva)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM RESERVA WHERE RESERVA_ID=" + pIdReserva;
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

    public boolean eliminarReservasInstalacion(int pIdInstalacion)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM RESERVA WHERE RESERVA_INSTALACION_ID=" + pIdInstalacion;
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
