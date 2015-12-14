/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.CursoInscrito;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class CursoInscritoDAL {

    Conexion conexion;

    //Constructor
    public CursoInscritoDAL()
    {

    }

    public CursoInscrito getCursoInscrito(int pIdCursoInscrito)
    {
        conexion = new Conexion();
        CursoInscrito unCursoInscrito = null;
        String consulta= "SELECT * FROM CURSO_INSCRITOS WHERE CURSO_INSCRITOS_ID = " + pIdCursoInscrito;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unCursoInscrito = new CursoInscrito(rs.getInt("CURSO_INSCRITOS_ID"), rs.getInt("CURSO_INSCRITOS_SOCIO_ID"), rs.getInt("CURSO_INSCRITOS_CURSO_ID"), rs.getDate("CURSO_INSCRITOS_FECHA"));
        }
        catch(Exception e)
        {
            unCursoInscrito = null;
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
            return unCursoInscrito;
        }
     }


    public ArrayList<CursoInscrito> getCurso(int pIdCursoInscrito, int pIdSocio, int pIdCurso, Date pFecha)
    {
        conexion = new Conexion();
        ArrayList<CursoInscrito> listaCursoInscritos = new ArrayList<CursoInscrito>();
        CursoInscrito unCursoInscrito = null;
        String consulta= "SELECT * FROM CURSO_INSCRITOS ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdCursoInscrito != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_INSCRITOS_ID = " + pIdCursoInscrito;
                     bConsul=true;
                 }
            }

            if (pIdSocio != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_INSCRITOS_SOCIO_ID = " + pIdSocio;
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_INSCRITOS_SOCIO_ID = " + pIdSocio;
             }

             if (pIdCurso != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_INSCRITOS_CURSO_ID= " + pIdCurso;
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_INSCRITOS_CURSO_ID = '" + pIdCurso;
             }

             if (pFecha != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CURSO_INSCRITOS_FECHA = '" + pFecha + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_INSCRITOS_FECHA = '" + pFecha + "'";
             }

            consulta += " ORDER BY CURSO_INSCRITOS_FECHA";

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unCursoInscrito = new CursoInscrito(rs.getInt("CURSO_INSCRITOS_ID"), rs.getInt("CURSO_INSCRITOS_SOCIO_ID"), rs.getInt("CURSO_INSCRITOS_CURSO_ID"), rs.getDate("CURSO_INSCRITOS_FECHA"));
                listaCursoInscritos.add(unCursoInscrito);
            }
       }
        catch(SQLException ex)
        {
            listaCursoInscritos = null;
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
            return listaCursoInscritos;
        }
    }


    public ArrayList<CursoInscrito> getCursoActuales(int pIdSocio)
    {
        conexion = new Conexion();
        ArrayList<CursoInscrito> listaCursoInscritos = new ArrayList<CursoInscrito>();
        CursoInscrito unCursoInscrito = null;
        String consulta= "select ci.* from curso c, curso_inscritos ci where c.curso_id=ci.curso_inscritos_curso_id and ci.curso_inscritos_socio_id=" + pIdSocio +" and c.curso_fecha_fin>curdate()";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
            consulta += " ORDER BY CURSO_INSCRITOS_FECHA";

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unCursoInscrito = new CursoInscrito(rs.getInt("CURSO_INSCRITOS_ID"), rs.getInt("CURSO_INSCRITOS_SOCIO_ID"), rs.getInt("CURSO_INSCRITOS_CURSO_ID"), rs.getDate("CURSO_INSCRITOS_FECHA"));
                listaCursoInscritos.add(unCursoInscrito);
            }
       }
        catch(SQLException ex)
        {
            listaCursoInscritos = null;
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
            return listaCursoInscritos;
        }
    }


    public boolean insertarCursoInscritos(int pIdSocio, int pIdCurso, Date pFecha)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO CURSO_INSCRITOS (CURSO_INSCRITOS_SOCIO_ID, CURSO_INSCRITOS_CURSO_ID, CURSO_INSCRITOS_FECHA) VALUES (" + pIdSocio + "," + pIdCurso + ",'" + pFecha + "')";
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

    public boolean insertarCursoInscritos(int pIdSocio, int pIdCurso)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO CURSO_INSCRITOS (CURSO_INSCRITOS_SOCIO_ID, CURSO_INSCRITOS_CURSO_ID, CURSO_INSCRITOS_FECHA) VALUES (" + pIdSocio + "," + pIdCurso + ",curdate())";
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



    public boolean modificarCursoInscritos(int pIdCursoInscritos, int pIdSocio, int pIdCurso, Date pFecha)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE CURSO_INSCRITOS SET CURSO_INSCRITOS_SOCIO_ID=" + pIdSocio + ", CURSO_INSCRITOS_CURSO_ID=" + pIdCurso + ", CURSO_INSCRITOS_FECHA='" + pFecha + "' WHERE CURSO_INSCRITOS_ID=" + pIdCursoInscritos;
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

    public boolean eliminarCursoInscrito(int pIdCursoInscrito)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM CURSO_INSCRITOS WHERE CURSO_INSCRITOS_ID=" + pIdCursoInscrito;
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
