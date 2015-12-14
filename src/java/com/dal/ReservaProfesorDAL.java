/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.ReservaProfesor;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class ReservaProfesorDAL {

    Conexion conexion;

    //Constructor
    public ReservaProfesorDAL()
    {
        
    }

    public ReservaProfesor getReservaProfesorId(int pIdReservaProfesor)
    {
        conexion = new Conexion();
        ReservaProfesor unaReservaProfesor = null;

        String consulta= "SELECT * FROM RESERVA_PROFESOR WHERE RESERVA_PROFESOR_ID= " + pIdReservaProfesor;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 unaReservaProfesor = new ReservaProfesor(rs.getInt("RESERVA_PROFESOR_ID"), rs.getInt("RESERVA_PROFESOR_PROFESOR_ID"), rs.getDate("RESERVA_PROFESOR_FECHA"),rs.getTime("RESERVA_PROFESOR_HINICIO"),rs.getTime("RESERVA_PROFESOR_HFIN"),rs.getInt("RESERVA_PROFESOR_SOCIO_ID"), rs.getDouble("RESERVA_PROFESOR_IMPORTE"), rs.getInt("RESERVA_PROFESOR_ESTADO_ID"), rs.getInt("RESERVA_PROFESOR_FORMA_PAGO_ID"));
             }
        }
        catch(Exception e)
        {
            unaReservaProfesor = null;
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
            return unaReservaProfesor;
        }
     }


    public ArrayList<ReservaProfesor> busquedaReservasProfesor(int pIdSocio, int pIdProfesor, Date pFechaInicio, Date pFechaFin, int pIdEstado, int pIdFormaPago)
    {
        conexion = new Conexion();
        ArrayList<ReservaProfesor> listaReservasProfesor = new ArrayList<ReservaProfesor>();
        ReservaProfesor unaReservaProfesor = null;

        String consulta= "SELECT * FROM RESERVA_PROFESOR ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdProfesor != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_PROFESOR_PROFESOR_ID=" + pIdProfesor;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_PROFESOR_PROFESOR_ID=" + pIdProfesor;
             }

             if (pIdSocio != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_PROFESOR_SOCIO_ID=" + pIdSocio;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_PROFESOR_SOCIO_ID=" + pIdSocio;
             }


             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (RESERVA_PROFESOR_FECHA > '" + pFechaInicio + "' OR RESERVA_PROFESOR_FECHA = '" + pFechaInicio + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (RESERVA_PROFESOR_FECHA > '" + pFechaInicio + "' OR RESERVA_PROFESOR_FECHA = '" + pFechaInicio + "')";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (RESERVA_PROFESOR_FECHA < '" + pFechaFin + "' OR RESERVA_PROFESOR_FECHA = '" + pFechaFin + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (RESERVA_PROFESOR_FECHA < '" + pFechaFin + "' OR RESERVA_PROFESOR_FECHA = '" + pFechaFin + "')";
             }

             if (pIdEstado != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_PROFESOR_ESTADO_ID=" + pIdEstado;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_PROFESOR_ESTADO_ID=" + pIdEstado;
             }

             if (pIdFormaPago != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_PROFESOR_FORMA_PAGO_ID=" + pIdFormaPago;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_PROFESOR_FORMA_PAGO_ID=" + pIdFormaPago;
             }

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaReservaProfesor = new ReservaProfesor(rs.getInt("RESERVA_PROFESOR_ID"), rs.getInt("RESERVA_PROFESOR_PROFESOR_ID"), rs.getDate("RESERVA_PROFESOR_FECHA"), rs.getTime("RESERVA_PROFESOR_HINICIO"), rs.getTime("RESERVA_PROFESOR_HFIN"), rs.getInt("RESERVA_PROFESOR_SOCIO_ID"), rs.getDouble("RESERVA_PROFESOR_IMPORTE"), rs.getInt("RESERVA_PROFESOR_ESTADO_ID"), rs.getInt("RESERVA_PROFESOR_FORMA_PAGO_ID"));
                listaReservasProfesor.add(unaReservaProfesor);
            }
       }

        catch(SQLException ex)
        {
            listaReservasProfesor = null;
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
            return listaReservasProfesor;
        }
    }


    public ArrayList<ReservaProfesor> getUnProfesorReserva(int pIdProfesor)
    {
        conexion = new Conexion();
        ArrayList<ReservaProfesor> listaProfesorReserva = new ArrayList<ReservaProfesor>();
        ReservaProfesor unaReservaProfesor;

        String consulta= "SELECT * FROM RESERVA_PROFESOR ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {

             if (pIdProfesor != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE RESERVA_PROFESOR_PROFESOR_ID = " + pIdProfesor;
                     bConsul=true;
                 }
                 else
                     consulta += " AND RESERVA_PROFESOR_PROFESOR_ID = " + pIdProfesor;
             }

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaReservaProfesor = new ReservaProfesor(rs.getInt("RESERVA_PROFESOR_ID"), rs.getInt("RESERVA_PROFESOR_PROFESOR_ID"), rs.getDate("RESERVA_PROFESOR_FECHA"),rs.getTime("RESERVA_PROFESOR_HINICIO"),rs.getTime("RESERVA_PROFESOR_HFIN"),rs.getInt("RESERVA_PROFESOR_SOCIO_ID"), rs.getDouble("RESERVA_PROFESOR_IMPORTE"), rs.getInt("RESERVA_PROFESOR_ESTADO_ID"), rs.getInt("RESERVA_PROFESOR_FORMA_PAGO_ID"));
                listaProfesorReserva.add(unaReservaProfesor);
            }
       }
        catch(SQLException ex)
        {
            listaProfesorReserva = null;
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
            return listaProfesorReserva;
        }
    }


    public ArrayList<ReservaProfesor> getReservasActualesYPendientes(int pIdSocio)
    {
        conexion = new Conexion();
        ArrayList<ReservaProfesor> listaProfesorReserva = new ArrayList<ReservaProfesor>();
        ReservaProfesor unaReservaProfesor;

        String consulta= "select * from reserva_profesor where (reserva_profesor_estado_id=1 or reserva_profesor_fecha>=curdate()) AND RESERVA_PROFESOR_SOCIO_ID = " + pIdSocio + " order by reserva_profesor_fecha";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
            
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaReservaProfesor = new ReservaProfesor(rs.getInt("RESERVA_PROFESOR_ID"), rs.getInt("RESERVA_PROFESOR_PROFESOR_ID"), rs.getDate("RESERVA_PROFESOR_FECHA"),rs.getTime("RESERVA_PROFESOR_HINICIO"),rs.getTime("RESERVA_PROFESOR_HFIN"),rs.getInt("RESERVA_PROFESOR_SOCIO_ID"), rs.getDouble("RESERVA_PROFESOR_IMPORTE"), rs.getInt("RESERVA_PROFESOR_ESTADO_ID"), rs.getInt("RESERVA_PROFESOR_FORMA_PAGO_ID"));
                listaProfesorReserva.add(unaReservaProfesor);
            }
       }
        catch(SQLException ex)
        {
            listaProfesorReserva = null;
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
            return listaProfesorReserva;
        }
    }


    public boolean insertarReservaProfesor(int pIdProfesor, Date pFecha, Time pHoraInicio, Time pHoraFin, int pIdSocio, double pImporte, int pIdEstado, int pIdFormaPago)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO RESERVA_PROFESOR (RESERVA_PROFESOR_PROFESOR_ID, RESERVA_PROFESOR_FECHA, RESERVA_PROFESOR_HINICIO, RESERVA_PROFESOR_HFIN, RESERVA_PROFESOR_SOCIO_ID, RESERVA_PROFESOR_IMPORTE, RESERVA_PROFESOR_ESTADO_ID, RESERVA_PROFESOR_FORMA_PAGO_ID) VALUES (" + pIdProfesor + ",'" + pFecha + "','" + pHoraInicio + "','" + pHoraFin + "'," + pIdSocio + "," + pImporte + "," + pIdEstado + "," + pIdFormaPago + ")";

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


    public boolean modificarReservaProfesor(int pIdReservaProfesor,int pIdProfesor, Date pFecha, Time pHoraInicio, Time pHoraFin, int pIdSocio, double pImporte, int pIdEstado, int pIdFormaPago)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE RESERVA_PROFESOR SET RESERVA_PROFESOR_PROFESOR_ID=" + pIdProfesor + ", RESERVA_PROFESOR_FECHA='" + pFecha + "', RESERVA_PROFESOR_HINICIO='" + pHoraInicio + "', RESERVA_PROFESOR_HFIN='" + pHoraFin + "', RESERVA_PROFESOR_SOCIO_ID=" + pIdSocio + ", RESERVA_PROFESOR_IMPORTE=" + pImporte + ", RESERVA_PROFESOR_ESTADO_ID=" + pIdEstado + ", RESERVA_PROFESOR_FORMA_PAGO_ID=" + pIdFormaPago +  " WHERE RESERVA_PROFESOR_ID=" + pIdReservaProfesor;
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

    public boolean eliminarReservaProfesor(int pIdReservaProfesor)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM RESERVA_PROFESOR WHERE RESERVA_PROFESOR_ID=" + pIdReservaProfesor;
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
