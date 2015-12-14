/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.TipoCurso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class TipoCursoDAL {

    Conexion conexion;

    //Constructor
    public TipoCursoDAL()
    {

    }

    public TipoCurso getTipoCurso(int pIdTipoCurso)
    {
        conexion = new Conexion();
        TipoCurso unTipoCurso = null;
        String consulta= "SELECT * FROM TIPO_CURSO WHERE TIPO_CURSO_ID = " + pIdTipoCurso;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoCurso = new TipoCurso(rs.getInt("TIPO_CURSO_ID"), rs.getString("TIPO_CURSO_NOMBRE"));
        }
        catch(Exception e)
        {
            unTipoCurso = null;
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
            return unTipoCurso;
        }
     }


    public ArrayList<TipoCurso> getTipoCurso(int pIdTipoCurso, String pNombre)
    {
        conexion = new Conexion();
        ArrayList<TipoCurso> listaTiposCursos = new ArrayList<TipoCurso>();
        TipoCurso unTipoCurso = null;
        String consulta= "SELECT * FROM TIPO_CURSO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdTipoCurso != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_CURSO_ID = " + pIdTipoCurso;
                     bConsul=true;
                 }
            }

            if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_CURSO_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CURSO_INSCRITOS_SOCIO_ID = '" + pNombre + "'";
             }

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unTipoCurso = new TipoCurso(rs.getInt("TIPO_CURSO_ID"), rs.getString("TIPO_CURSO_NOMBRE"));
                listaTiposCursos.add(unTipoCurso);
            }
       }
        catch(SQLException ex)
        {
            listaTiposCursos = null;
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
            return listaTiposCursos;
        }
    }

    public boolean insertarTipoCurso(String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO TIPO_CURSO (TIPO_CURSO_NOMBRE) VALUES ('" + pNombre + "')";
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

    public boolean modificarTipoCurso(int pIdTipoCurso, String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE TIPO_CURSO SET TIPO_CURSO_NOMBRE='" + pNombre + "' WHERE TIPO_CURSO_ID = " + pIdTipoCurso;
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

    public boolean eliminarTipoCurso(int pIdTipoCurso)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM TIPO_CURSO WHERE TIPO_CURSO_ID=" + pIdTipoCurso;
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
