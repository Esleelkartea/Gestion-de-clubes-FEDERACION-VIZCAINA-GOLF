/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.ProfesorHorario;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class ProfesorHorarioDAL {

    Conexion conexion;

    //Constructor
    public ProfesorHorarioDAL()
    {
        
    }


    public ProfesorHorario getProfesorHorario(int pIdProfesorHorario)
    {
        conexion = new Conexion();
        ProfesorHorario unProfesorHorario = null;
        String consulta= "SELECT * FROM PROFESOR_HORARIO WHERE PROFESOR_HORARIO_ID = " + pIdProfesorHorario;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unProfesorHorario = new ProfesorHorario(rs.getInt("PROFESOR_HORARIO_ID"), rs.getDate("PROFESOR_HORARIO_FINICIO"), rs.getDate("PROFESOR_HORARIO_FFIN"), rs.getTime("PROFESOR_HORARIO_HINICIO"), rs.getTime("PROFESOR_HORARIO_HFIN"), rs.getString("PROFESOR_HORARIO_OBSERVACIONES"), rs.getInt("PROFESOR_HORARIO_PROFESOR_ID"));
        }
        catch(Exception e)
        {
            unProfesorHorario = null;
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
            return unProfesorHorario;
        }
     }


    public ArrayList<ProfesorHorario> getProfesorHorario(int pIdProfesorHorario, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, int pIdProfesor)
    {
        conexion = new Conexion();
        ArrayList<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();
        ProfesorHorario unProfesorHorario;

        String consulta= "SELECT * FROM PROFESOR_HORARIO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdProfesorHorario != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_HORARIO_ID = " + pIdProfesorHorario;
                     bConsul=true;
                 }
            }

             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_HORARIO_FINICIO = '" + pFechaInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_HORARIO_FINICIO = '" + pFechaInicio + "'";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_HORARIO_FFIN = '" + pFechaFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_HORARIO_FFIN = '" + pFechaFin + "'";
             }

             if (pHoraInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_HORARIO_HINICIO = '" + pHoraInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_HORARIO_HINICIO = '" + pHoraInicio + "'";
             }

             if (pHoraFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_HORARIO_HFIN = '" + pHoraFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_HORARIO_HFIN = '" + pHoraFin + "'";
             }

             if (pObservaciones != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_HORARIO_OBSERVACIONES = '" + pObservaciones + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_OBSERVACIONES = '" + pObservaciones + "'";
             }


             if (pIdProfesor != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_HORARIO_PROFESOR_ID = " + pIdProfesor;
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_HORARIO_PROFESOR_ID = " + pIdProfesor;
             }


            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unProfesorHorario = new ProfesorHorario(rs.getInt("PROFESOR_HORARIO_ID"), rs.getDate("PROFESOR_HORARIO_FINICIO"), rs.getDate("PROFESOR_HORARIO_FFIN"), rs.getTime("PROFESOR_HORARIO_HINICIO"), rs.getTime("PROFESOR_HORARIO_HFIN"), rs.getString("PROFESOR_HORARIO_OBSERVACIONES"), rs.getInt("PROFESOR_HORARIO_PROFESOR_ID"));
                listaProfesorHorario.add(unProfesorHorario);

            }
       }
        catch(SQLException ex)
        {
            listaProfesorHorario = null;
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
            return listaProfesorHorario;
        }
    }


    public ArrayList<ProfesorHorario> getunProfesorHorario(int pIdProfesor)
    {
        conexion = new Conexion();
        ArrayList<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();
        ProfesorHorario unProfesorHorario;

        String consulta= "SELECT * FROM PROFESOR_HORARIO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {

             if (pIdProfesor != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_HORARIO_PROFESOR_ID = " + pIdProfesor;
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_HORARIO_PROFESOR_ID = " + pIdProfesor;
             }

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unProfesorHorario = new ProfesorHorario(rs.getInt("PROFESOR_HORARIO_ID"), rs.getDate("PROFESOR_HORARIO_FINICIO"), rs.getDate("PROFESOR_HORARIO_FFIN"), rs.getTime("PROFESOR_HORARIO_HINICIO"), rs.getTime("PROFESOR_HORARIO_HFIN"), rs.getString("PROFESOR_HORARIO_OBSERVACIONES"), rs.getInt("PROFESOR_HORARIO_PROFESOR_ID"));
                listaProfesorHorario.add(unProfesorHorario);
            }
       }
        catch(SQLException ex)
        {
            listaProfesorHorario = null;
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
            return listaProfesorHorario;
        }
    }

    public ArrayList<ProfesorHorario> getunProfesorHorario2(int pIdProfesor)
    {
        conexion = new Conexion();
        ArrayList<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();
        ProfesorHorario unProfesorHorario;

        String consulta= "SELECT * FROM PROFESOR_HORARIO WHERE PROFESOR_HORARIO_PROFESOR_ID = " + pIdProfesor + " and  (profesor_horario_ffin > curdate() or profesor_horario_ffin = curdate())";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unProfesorHorario = new ProfesorHorario(rs.getInt("PROFESOR_HORARIO_ID"), rs.getDate("PROFESOR_HORARIO_FINICIO"), rs.getDate("PROFESOR_HORARIO_FFIN"), rs.getTime("PROFESOR_HORARIO_HINICIO"), rs.getTime("PROFESOR_HORARIO_HFIN"), rs.getString("PROFESOR_HORARIO_OBSERVACIONES"), rs.getInt("PROFESOR_HORARIO_PROFESOR_ID"));
                listaProfesorHorario.add(unProfesorHorario);
            }
       }
        catch(SQLException ex)
        {
            listaProfesorHorario = null;
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
            return listaProfesorHorario;
        }
    }



    public boolean insertarProfesorHorario(Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, int pIdProfesor)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO PROFESOR_HORARIO (PROFESOR_HORARIO_FINICIO, PROFESOR_HORARIO_FFIN, PROFESOR_HORARIO_HINICIO, PROFESOR_HORARIO_HFIN, PROFESOR_HORARIO_OBSERVACIONES, PROFESOR_HORARIO_PROFESOR_ID) VALUES ('" + pFechaInicio + "','" + pFechaFin + "','" + pHoraInicio + "','" + pHoraFin + "','" + pObservaciones + "'," + pIdProfesor + ")";
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

    public boolean modificarProfesorHorario(int pIdProfesorHorario, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, int pIdProfesor)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE PROFESOR_HORARIO SET PROFESOR_HORARIO_FINICIO ='" + pFechaInicio + "', PROFESOR_HORARIO_FFIN='" + pFechaFin + "', PROFESOR_HORARIO_HINICIO='" + pHoraInicio  + "', PROFESOR_HORARIO_HFIN='" + pHoraFin + "', PROFESOR_HORARIO_OBSERVACIONES='" + pObservaciones + "', PROFESOR_HORARIO_PROFESOR_ID=" + pIdProfesor + " WHERE PROFESOR_HORARIO_ID=" + pIdProfesorHorario;
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

    public boolean eliminarProfesorHorario(int pIdProfesorHorario)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM PROFESOR_HORARIO WHERE PROFESOR_HORARIO_ID=" + pIdProfesorHorario;
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

    public boolean eliminarHorariosProfesor(int pIdProfesor)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM PROFESOR_HORARIO WHERE PROFESOR_HORARIO_PROFESOR_ID=" + pIdProfesor;
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
