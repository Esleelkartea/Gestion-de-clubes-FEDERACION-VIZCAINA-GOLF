/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Curso;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class CursoDAL {

    Conexion conexion;

    //Constructor
    public CursoDAL()
    {

    }

    public Curso getCurso(int pIdCurso)
    {
        conexion = new Conexion();
        Curso unCurso = null;
        String consulta= "SELECT * FROM CURSO WHERE CURSO_ID = " + pIdCurso;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unCurso = new Curso(rs.getInt("CURSO_ID"),rs.getString("CURSO_NOMBRE"), rs.getInt("CURSO_PROFESOR_ID"), rs.getDate("CURSO_FECHA_INICIO"), rs.getDate("CURSO_FECHA_FIN"),rs.getTime("CURSO_HORA_INICIO"), rs.getTime("CURSO_HORA_FIN"), rs.getBoolean("CURSO_LUNES"), rs.getBoolean("CURSO_MARTES"), rs.getBoolean("CURSO_MIERCOLES"), rs.getBoolean("CURSO_JUEVES"), rs.getBoolean("CURSO_VIERNES"), rs.getBoolean("CURSO_SABADO"), rs.getBoolean("CURSO_DOMINGO"), rs.getDouble("CURSO_PRECIO"), rs.getInt("CURSO_NUMMAX"), rs.getInt("CURSO_APUNTADOS"), rs.getDate("CURSO_FECHA_INSCRIPCION"), rs.getInt("CURSO_TIPO_CURSO_ID"), rs.getInt("CURSO_INSTALACION_ID"), rs.getBoolean("CURSO_FINALIZADO"));
                 
        }
        catch(Exception e)
        {
            unCurso = null;
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
            return unCurso;
        }
     }


    public ArrayList<Curso> getCurso(int pIdCurso, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, int pIdTipoCurso, int pIdInstalacion)
    {
        conexion = new Conexion();
        ArrayList<Curso> listaCursos = new ArrayList<Curso>();
        Curso unCurso = null;
        String consulta= "SELECT * FROM CURSO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdCurso != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_ID = " + pIdCurso;
                     bConsul=true;
                 }
            }

            if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_FECHA_INICIO = '" + pFechaInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_FECHA_INICIO = '" + pFechaInicio + "'";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_FECHA_FIN = '" + pFechaFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_FECHA_FIN = '" + pFechaFin + "'";
             }

             if (pHoraInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_HORA_INICIO = '" + pHoraInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_HORA_INICIO = '" + pHoraInicio + "'";
             }

             if (pHoraFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_HORA_FIN = '" + pHoraFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_HORA_FIN = '" + pHoraFin + "'";
             }

             if (pIdTipoCurso != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_TIPO_CURSO_ID = " + pIdTipoCurso;
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_TIPO_CURSO_ID = '" +  pIdTipoCurso;
             }

             if (pIdInstalacion != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_INSTALACION_ID = " + pIdInstalacion;
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_INSTALACION_ID = '" +  pIdInstalacion;
             }
           
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unCurso = new Curso(rs.getInt("CURSO_ID"),rs.getString("CURSO_NOMBRE"), rs.getInt("CURSO_PROFESOR_ID"), rs.getDate("CURSO_FECHA_INICIO"), rs.getDate("CURSO_FECHA_FIN"),rs.getTime("CURSO_HORA_INICIO"), rs.getTime("CURSO_HORA_FIN"), rs.getBoolean("CURSO_LUNES"), rs.getBoolean("CURSO_MARTES"), rs.getBoolean("CURSO_MIERCOLES"), rs.getBoolean("CURSO_JUEVES"), rs.getBoolean("CURSO_VIERNES"), rs.getBoolean("CURSO_SABADO"), rs.getBoolean("CURSO_DOMINGO"), rs.getDouble("CURSO_PRECIO"), rs.getInt("CURSO_NUMMAX"), rs.getInt("CURSO_APUNTADOS"), rs.getDate("CURSO_FECHA_INSCRIPCION"), rs.getInt("CURSO_TIPO_CURSO_ID"), rs.getInt("CURSO_INSTALACION_ID"), rs.getBoolean("CURSO_FINALIZADO"));
                listaCursos.add(unCurso);
            }
       }
        catch(SQLException ex)
        {
            listaCursos = null;
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
            return listaCursos;
        }
    }


    public ArrayList<Curso> getCursoActuales(int pIdCurso, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, int pIdTipoCurso, int pIdInstalacion)
    {
        conexion = new Conexion();
        ArrayList<Curso> listaCursos = new ArrayList<Curso>();
        Curso unCurso = null;
        String consulta= "SELECT * FROM CURSO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdCurso != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_ID = " + pIdCurso;
                     bConsul=true;
                 }
            }

            if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_FECHA_INICIO = '" + pFechaInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_FECHA_INICIO = '" + pFechaInicio + "'";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_FECHA_FIN = '" + pFechaFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_FECHA_FIN = '" + pFechaFin + "'";
             }

             if (pHoraInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_HORA_INICIO = '" + pHoraInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_HORA_INICIO = '" + pHoraInicio + "'";
             }

             if (pHoraFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_HORA_FIN = '" + pHoraFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_HORA_FIN = '" + pHoraFin + "'";
             }

             if (pIdTipoCurso != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_TIPO_CURSO_ID = " + pIdTipoCurso;
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_TIPO_CURSO_ID = '" +  pIdTipoCurso;
             }

             if (pIdInstalacion != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_INSTALACION_ID = " + pIdInstalacion;
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_INSTALACION_ID = '" +  pIdInstalacion;
             }


             if (bConsul==false)
                 {
                     consulta += " WHERE CURSO_FINALIZADO = false";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_FINALIZADO = false";


            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unCurso = new Curso(rs.getInt("CURSO_ID"),rs.getString("CURSO_NOMBRE"), rs.getInt("CURSO_PROFESOR_ID"), rs.getDate("CURSO_FECHA_INICIO"), rs.getDate("CURSO_FECHA_FIN"),rs.getTime("CURSO_HORA_INICIO"), rs.getTime("CURSO_HORA_FIN"), rs.getBoolean("CURSO_LUNES"), rs.getBoolean("CURSO_MARTES"), rs.getBoolean("CURSO_MIERCOLES"), rs.getBoolean("CURSO_JUEVES"), rs.getBoolean("CURSO_VIERNES"), rs.getBoolean("CURSO_SABADO"), rs.getBoolean("CURSO_DOMINGO"), rs.getDouble("CURSO_PRECIO"), rs.getInt("CURSO_NUMMAX"), rs.getInt("CURSO_APUNTADOS"), rs.getDate("CURSO_FECHA_INSCRIPCION"), rs.getInt("CURSO_TIPO_CURSO_ID"), rs.getInt("CURSO_INSTALACION_ID"), rs.getBoolean("CURSO_FINALIZADO"));
                listaCursos.add(unCurso);
            }
       }
        catch(SQLException ex)
        {
            listaCursos = null;
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
            return listaCursos;
        }
    }



    public ArrayList<Curso> getCursoProfesor(int pIdProfesor)
    {
        conexion = new Conexion();
        ArrayList<Curso> listaCursos = new ArrayList<Curso>();
        Curso unCurso = null;
        String consulta= "SELECT * FROM CURSO WHERE CURSO_PROFESOR_ID=" + pIdProfesor + " AND CURSO_FINALIZADO = false";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unCurso = new Curso(rs.getInt("CURSO_ID"),rs.getString("CURSO_NOMBRE"), rs.getInt("CURSO_PROFESOR_ID"), rs.getDate("CURSO_FECHA_INICIO"), rs.getDate("CURSO_FECHA_FIN"),rs.getTime("CURSO_HORA_INICIO"), rs.getTime("CURSO_HORA_FIN"), rs.getBoolean("CURSO_LUNES"), rs.getBoolean("CURSO_MARTES"), rs.getBoolean("CURSO_MIERCOLES"), rs.getBoolean("CURSO_JUEVES"), rs.getBoolean("CURSO_VIERNES"), rs.getBoolean("CURSO_SABADO"), rs.getBoolean("CURSO_DOMINGO"), rs.getDouble("CURSO_PRECIO"), rs.getInt("CURSO_NUMMAX"), rs.getInt("CURSO_APUNTADOS"), rs.getDate("CURSO_FECHA_INSCRIPCION"), rs.getInt("CURSO_TIPO_CURSO_ID"), rs.getInt("CURSO_INSTALACION_ID"), rs.getBoolean("CURSO_FINALIZADO"));
                listaCursos.add(unCurso);
            }
       }
        catch(SQLException ex)
        {
            listaCursos = null;
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
            return listaCursos;
        }
    }


    public boolean insertarCurso(String pNombre, int pIdProfesor, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, boolean pLunes, boolean pMartes, boolean pMiercoles, boolean pJueves, boolean pViernes, boolean pSabado, boolean pDomingo, double pPrecio, int pNumMax, int pNumAsis, Date pFechaInscripcion, int pTipoCurso, int pIdInstalacion, boolean pFin)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO CURSO (CURSO_NOMBRE, CURSO_PROFESOR_ID, CURSO_FECHA_INICIO, CURSO_FECHA_FIN, CURSO_HORA_INICIO, CURSO_HORA_FIN, CURSO_LUNES, CURSO_MARTES, CURSO_MIERCOLES, CURSO_JUEVES, CURSO_VIERNES, CURSO_SABADO, CURSO_DOMINGO, CURSO_PRECIO, CURSO_NUMMAX, CURSO_APUNTADOS, CURSO_FECHA_INSCRIPCION, CURSO_TIPO_CURSO_ID, CURSO_INSTALACION_ID, CURSO_FINALIZADO) VALUES ('" + pNombre + "'," + pIdProfesor + ",'" + pFechaInicio + "','" + pFechaFin + "','" + pHoraInicio + "','" + pHoraFin + "'," + pLunes + "," + pMartes + "," + pMiercoles + "," + pJueves + "," + pViernes + "," + pSabado + "," + pDomingo + "," + pPrecio + "," + pNumMax + "," + pNumAsis + ",'" + pFechaInscripcion + "'," + pTipoCurso + "," + pIdInstalacion + "," + pFin + ")";
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


    public boolean modificarCurso(int pIdCurso, String pNombre, int pIdProfesor, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, boolean pLunes, boolean pMartes, boolean pMiercoles, boolean pJueves, boolean pViernes, boolean pSabado, boolean pDomingo, double pPrecio, int pNumMax, int pNumAsis, Date pFechaInscripcion, int pTipoCurso, int pIdInstalacion, boolean pFin)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE CURSO SET CURSO_NOMBRE='" + pNombre + "', CURSO_PROFESOR_ID=" + pIdProfesor + ", CURSO_FECHA_INICIO='" + pFechaInicio + "', CURSO_FECHA_FIN='" + pFechaFin + "', CURSO_HORA_INICIO='" + pHoraInicio + "', CURSO_HORA_FIN='" + pHoraFin + "', CURSO_LUNES=" + pLunes + ", CURSO_MARTES=" + pMartes + ", CURSO_MIERCOLES=" + pMiercoles + ", CURSO_JUEVES=" + pJueves + ", CURSO_VIERNES=" + pViernes + ", CURSO_SABADO=" + pSabado + ", CURSO_DOMINGO=" + pDomingo + ", CURSO_PRECIO=" + pPrecio + ", CURSO_NUMMAX=" + pNumMax + ", CURSO_APUNTADOS=" + pNumAsis + ", CURSO_FECHA_INSCRIPCION='" + pFechaInscripcion + "', CURSO_TIPO_CURSO_ID=" + pTipoCurso + ", CURSO_INSTALACION_ID=" + pIdInstalacion + ", CURSO_FINALIZADO=" + pFin + " WHERE CURSO_ID=" + pIdCurso;
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

    public boolean sumarAsistente(int pIdCurso)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE CURSO SET CURSO_APUNTADOS= CURSO_APUNTADOS + 1 WHERE CURSO_ID=" + pIdCurso;
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

    public boolean restarAsistente(int pIdCurso)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE CURSO SET CURSO_APUNTADOS= CURSO_APUNTADOS - 1 WHERE CURSO_ID=" + pIdCurso;
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

    public boolean finalizarCurso(int pIdCurso)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE CURSO SET CURSO_FINALIZADO = true WHERE CURSO_ID=" + pIdCurso;
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


    public boolean eliminarCurso(int pIdCurso)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM CURSO WHERE CURSO_ID=" + pIdCurso;
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
