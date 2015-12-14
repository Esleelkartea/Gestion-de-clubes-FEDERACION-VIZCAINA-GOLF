/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.InstalacionHorario;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class InstalacionHorarioDAL {

    Conexion conexion;

    public InstalacionHorarioDAL()
    {

    }


    public InstalacionHorario getInstalacionHorario(int pIdInstalacionHorario)
    {
        conexion = new Conexion();
        InstalacionHorario unaInstalacionHorario = null;
        String consulta= "SELECT * FROM INSTALACION_HORARIO WHERE INSTALACION_HORARIO_ID = " + pIdInstalacionHorario;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unaInstalacionHorario = new InstalacionHorario(rs.getInt("INSTALACION_HORARIO_ID"), rs.getDate("INSTALACION_HORARIO_FINICIO"), rs.getDate("INSTALACION_HORARIO_FFIN"), rs.getTime("INSTALACION_HORARIO_HINICIO"), rs.getTime("INSTALACION_HORARIO_HFIN"), rs.getString("INSTALACION_HORARIO_OBSERVACIONES"), rs.getBoolean("INSTALACION_HORARIO_LUNES"), rs.getBoolean("INSTALACION_HORARIO_MARTES"), rs.getBoolean("INSTALACION_HORARIO_MIERCOLES"), rs.getBoolean("INSTALACION_HORARIO_JUEVES"), rs.getBoolean("INSTALACION_HORARIO_VIERNES"), rs.getBoolean("INSTALACION_HORARIO_SABADO"), rs.getBoolean("INSTALACION_HORARIO_DOMINGO"), rs.getInt("INSTALACION_HORARIO_INSTALACION_ID"));
        }
        catch(Exception e)
        {
            unaInstalacionHorario = null;
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
            return unaInstalacionHorario;
        }
     }



    public ArrayList<InstalacionHorario> getInstalacionHorario(int pIdInstalacionHorario, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, int pIdInstalacion)
    {
        conexion = new Conexion();
        ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();
        InstalacionHorario unaInstalacionHorario;
       
        String consulta= "SELECT * FROM INSTALACION_HORARIO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdInstalacionHorario != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_ID = " + pIdInstalacionHorario;
                     bConsul=true;
                 }
            }

             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_FINICIO = '" + pFechaInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_FINICIO = '" + pFechaInicio + "'";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_FFIN = '" + pFechaFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_FFIN = '" + pFechaFin + "'";
             }

             if (pHoraInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_HINICIO = '" + pHoraInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_HINICIO = '" + pHoraInicio + "'";
             }

             if (pHoraFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_HFIN = '" + pHoraFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_HFIN = '" + pHoraFin + "'";
             }

             if (pObservaciones != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_OBSERVACIONES = '" + pObservaciones + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_OBSERVACIONES = '" + pObservaciones + "'";
             }

            
             if (pIdInstalacion != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_INSTALACION_ID = " + pIdInstalacion;
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_INSTALACION_ID = " + pIdInstalacion;
             }

             

              
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaInstalacionHorario = new InstalacionHorario(rs.getInt("INSTALACION_HORARIO_ID"),rs.getDate("INSTALACION_HORARIO_FINICIO"), rs.getDate("INSTALACION_HORARIO_FFIN"), rs.getTime("INSTALACION_HORARIO_HINICIO"), rs.getTime("INSTALACION_HORARIO_HFIN"), rs.getString("INSTALACION_HORARIO_OBSERVACIONES"),  rs.getBoolean("INSTALACION_HORARIO_LUNES"), rs.getBoolean("INSTALACION_HORARIO_MARTES"), rs.getBoolean("INSTALACION_HORARIO_MIERCOLES"), rs.getBoolean("INSTALACION_HORARIO_JUEVES"), rs.getBoolean("INSTALACION_HORARIO_VIERNES"), rs.getBoolean("INSTALACION_HORARIO_SABADO"), rs.getBoolean("INSTALACION_HORARIO_DOMINGO"), rs.getInt("INSTALACION_HORARIO_INSTALACION_ID"));
                //   unaInstalacionHorario = new InstalacionHorario(rs.getInt("INSTALACION_HORARIO_ID"), rs.getDate("INSTALACION_HORARIO_FINICIO"), rs.getDate("INSTALACION_HORARIO_FFIN"), rs.getTime("INSTALACION_HORARIO_HINICIO"), rs.getTime("INSTALACION_HORARIO_HFIN"), rs.getString("INSTALACION_HORARIO_OBSERVACIONES"), rs.getString("INSTALACION_HORARIO_DIAS"));
                listaInstalacionHorario.add(unaInstalacionHorario);

            }
       }
        catch(SQLException ex)
        {
            listaInstalacionHorario = null;
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
            return listaInstalacionHorario;
        }
    }



    public ArrayList<InstalacionHorario> getInstalacionHorarios(Date pFechaInicio, Date pFechaFin, int pIdInstalacion)
    {
        conexion = new Conexion();
        ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();
        InstalacionHorario unaInstalacionHorario;

        String consulta= "SELECT * FROM INSTALACION_HORARIO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_FINICIO = '" + pFechaInicio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_FINICIO = '" + pFechaInicio + "'";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_FFIN = '" + pFechaFin + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_FFIN = '" + pFechaFin + "'";
             }       

             if (pIdInstalacion != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_HORARIO_INSTALACION_ID = " + pIdInstalacion;
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_HORARIO_INSTALACION_ID = " + pIdInstalacion;
             }




            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaInstalacionHorario = new InstalacionHorario(rs.getInt("INSTALACION_HORARIO_ID"),rs.getDate("INSTALACION_HORARIO_FINICIO"), rs.getDate("INSTALACION_HORARIO_FFIN"), rs.getTime("INSTALACION_HORARIO_HINICIO"), rs.getTime("INSTALACION_HORARIO_HFIN"), rs.getString("INSTALACION_HORARIO_OBSERVACIONES"),  rs.getBoolean("INSTALACION_HORARIO_LUNES"), rs.getBoolean("INSTALACION_HORARIO_MARTES"), rs.getBoolean("INSTALACION_HORARIO_MIERCOLES"), rs.getBoolean("INSTALACION_HORARIO_JUEVES"), rs.getBoolean("INSTALACION_HORARIO_VIERNES"), rs.getBoolean("INSTALACION_HORARIO_SABADO"), rs.getBoolean("INSTALACION_HORARIO_DOMINGO"), rs.getInt("INSTALACION_HORARIO_INSTALACION_ID"));
                //   unaInstalacionHorario = new InstalacionHorario(rs.getInt("INSTALACION_HORARIO_ID"), rs.getDate("INSTALACION_HORARIO_FINICIO"), rs.getDate("INSTALACION_HORARIO_FFIN"), rs.getTime("INSTALACION_HORARIO_HINICIO"), rs.getTime("INSTALACION_HORARIO_HFIN"), rs.getString("INSTALACION_HORARIO_OBSERVACIONES"), rs.getString("INSTALACION_HORARIO_DIAS"));
                listaInstalacionHorario.add(unaInstalacionHorario);

            }
       }
        catch(SQLException ex)
        {
            listaInstalacionHorario = null;
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
            return listaInstalacionHorario;
        }
    }



    public ArrayList<InstalacionHorario> getInstalHorario(int pIdInstalacion)
    {
        conexion = new Conexion();
        ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();
        InstalacionHorario unaInstalacionHorario;

        String consulta= "SELECT * FROM INSTALACION_HORARIO WHERE INSTALACION_HORARIO_INSTALACION_ID = " + pIdInstalacion + " and  (instalacion_horario_ffin > curdate() or instalacion_horario_ffin = curdate());";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaInstalacionHorario = new InstalacionHorario(rs.getInt("INSTALACION_HORARIO_ID"),rs.getDate("INSTALACION_HORARIO_FINICIO"), rs.getDate("INSTALACION_HORARIO_FFIN"), rs.getTime("INSTALACION_HORARIO_HINICIO"), rs.getTime("INSTALACION_HORARIO_HFIN"), rs.getString("INSTALACION_HORARIO_OBSERVACIONES"),  rs.getBoolean("INSTALACION_HORARIO_LUNES"), rs.getBoolean("INSTALACION_HORARIO_MARTES"), rs.getBoolean("INSTALACION_HORARIO_MIERCOLES"), rs.getBoolean("INSTALACION_HORARIO_JUEVES"), rs.getBoolean("INSTALACION_HORARIO_VIERNES"), rs.getBoolean("INSTALACION_HORARIO_SABADO"), rs.getBoolean("INSTALACION_HORARIO_DOMINGO"), rs.getInt("INSTALACION_HORARIO_INSTALACION_ID"));
                listaInstalacionHorario.add(unaInstalacionHorario);
            }
       }
        catch(SQLException ex)
        {
            listaInstalacionHorario = null;
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
            return listaInstalacionHorario;
        }
    }

    public boolean insertarInstalacionHorario(Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, int pIdInstalacion, boolean pLunes, boolean pMartes, boolean pMiercoles, boolean pJueves, boolean pViernes, boolean pSabado, boolean pDomingo)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO INSTALACION_HORARIO (INSTALACION_HORARIO_FINICIO, INSTALACION_HORARIO_FFIN, INSTALACION_HORARIO_HINICIO, INSTALACION_HORARIO_HFIN, INSTALACION_HORARIO_OBSERVACIONES, INSTALACION_HORARIO_INSTALACION_ID, INSTALACION_HORARIO_LUNES, INSTALACION_HORARIO_MARTES, INSTALACION_HORARIO_MIERCOLES, INSTALACION_HORARIO_JUEVES, INSTALACION_HORARIO_VIERNES, INSTALACION_HORARIO_SABADO, INSTALACION_HORARIO_DOMINGO) VALUES ('" + pFechaInicio + "','" + pFechaFin + "','" + pHoraInicio + "','" + pHoraFin + "','" + pObservaciones + "'," + pIdInstalacion + "," + pLunes + "," + pMartes + "," + pMiercoles + "," + pJueves + "," + pViernes + "," + pSabado + "," + pDomingo + ")";
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


    public boolean modificarInstalacionHorario(int pIdInstalacionHorario, Date pFechaInicio, Date pFechaFin, Time pHoraInicio, Time pHoraFin, String pObservaciones, int pIdInstalacion, boolean pLunes, boolean pMartes, boolean pMiercoles, boolean pJueves, boolean pViernes, boolean pSabado, boolean pDomingo)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE INSTALACION_HORARIO SET INSTALACION_HORARIO_FINICIO ='" + pFechaInicio + "', INSTALACION_HORARIO_FFIN='" + pFechaFin + "', INSTALACION_HORARIO_HINICIO='" + pHoraInicio  + "', INSTALACION_HORARIO_HFIN='" + pHoraFin + "', INSTALACION_HORARIO_OBSERVACIONES='" + pObservaciones + "', INSTALACION_HORARIO_INSTALACION_ID=" + pIdInstalacion + ", INSTALACION_HORARIO_LUNES=" + pLunes + ", INSTALACION_HORARIO_MARTES=" + pMartes + ", INSTALACION_HORARIO_MIERCOLES=" + pMiercoles + ", INSTALACION_HORARIO_JUEVES=" + pJueves + ", INSTALACION_HORARIO_VIERNES=" + pViernes + ", INSTALACION_HORARIO_SABADO=" + pSabado + ", INSTALACION_HORARIO_DOMINGO=" + pDomingo + " WHERE INSTALACION_HORARIO_ID=" + pIdInstalacionHorario;
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


    public boolean eliminarInstalacionHorario(int pIdInstalacionHorario)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM INSTALACION_HORARIO WHERE INSTALACION_HORARIO_ID=" + pIdInstalacionHorario;
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


    //borrar todas las instalacion_horario dada una instalacion
    public boolean eliminarInstalacionesHorario(int pIdInstalacion)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM INSTALACION_HORARIO WHERE INSTALACION_HORARIO_INSTALACION_ID=" + pIdInstalacion;
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
