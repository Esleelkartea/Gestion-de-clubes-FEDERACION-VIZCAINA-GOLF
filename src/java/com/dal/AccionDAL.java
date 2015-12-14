/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Accion;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Julen
 */
public class AccionDAL {

    //Atributos
    Conexion conexion;

    //Constructores
    public AccionDAL()
    {

    }

     //Otros metodos
    //Devuelve una ACCION de un idAccion
    public Accion getAccion(int idAccion)
    {
        conexion = new Conexion();
        Accion unaAccion = null;
        String consulta= "SELECT * FROM ACCION WHERE ACCION_ID = " + idAccion;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unaAccion = new Accion(rs.getInt("ACCION_ID"),rs.getString("ACCION_NOMBRE"), rs.getDate("ACCION_FECHAINICIO"), rs.getDate("ACCION_FECHAFIN"), rs.getInt("ACCION_SOCIO_ID"));
        }
        catch(Exception e)
        {
            unaAccion = null;
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
            return unaAccion;
        }
     }

    //Devuelve un arraylist de TipoInstalacion de una busqueda por nombre y/o idTipoInstalacion
    public ArrayList<Accion> getAccion(int pIdAccion, String pNombre)
    {
        conexion = new Conexion();
        ArrayList<Accion> listaAcciones = new ArrayList<Accion>();
        Accion unaAccion = null;
        String consulta= "SELECT * FROM ACCION";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdAccion != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE ACCION_ID = " + pIdAccion;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE ACCION_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND ACCION_NOMBRE = '" + pNombre + "'";
             }



             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaAccion = new Accion(rs.getInt("ACCION_ID"),rs.getString("ACCION_NOMBRE"), rs.getDate("ACCION_FECHAINICIO"), rs.getDate("ACCION_FECHAFIN"), rs.getInt("ACCION_SOCIO_ID"));
                listaAcciones.add(unaAccion);
            }
       }
        catch(SQLException ex)
        {
            listaAcciones = null;
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
            return listaAcciones;
        }
    }


     public ArrayList<Accion> getAccionesSocio(int pIdSocio)
    {
        conexion = new Conexion();
        ArrayList<Accion> listaAcciones = new ArrayList<Accion>();
        Accion unaAccion = null;
        String consulta= "SELECT * FROM ACCION WHERE ACCION_SOCIO_ID=" + pIdSocio;
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {

             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaAccion = new Accion(rs.getInt("ACCION_ID"),rs.getString("ACCION_NOMBRE"), rs.getDate("ACCION_FECHAINICIO"), rs.getDate("ACCION_FECHAFIN"), rs.getInt("ACCION_SOCIO_ID"));
                listaAcciones.add(unaAccion);
            }
       }
        catch(SQLException ex)
        {
            listaAcciones = null;
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
            return listaAcciones;
        }
    }

    //Inserta un registro en la tabla ACCION en la base de datos pasandole todos los campos
    public boolean insertarAccion(String pNombre, Date pFechaInicio, Date pFechaFin, int pIdSocio)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO ACCION  (ACCION_NOMBRE, ACCION_FECHAINICIO, ACCION_FECHAFIN, ACCION_SOCIO_ID) VALUES ('" + pNombre + "','" + pFechaInicio + "','" + pFechaFin + "'," + pIdSocio + ")";
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

    //Modifica un registro de la tabla ACCION de la base de datos pasandole todos los atributos
    public boolean modificarAccion(int pIdAccion, String pNombre, Date pFechaInicio, Date pFechaFin, int pIdSocio)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE ACCION SET ACCION_NOMBRE ='" + pNombre + "', ACCION_FECHAINICIO='" + pFechaInicio + "', ACCION_FECHAFIN='" + pFechaFin + "'  WHERE ACCION_ID=" + pIdAccion;
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

    //Eliminar un registro de la tabla ACCION de la base de datos
    public boolean eliminarAccion(int pIdAccion)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM ACCION WHERE ACCION_ID=" + pIdAccion;
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

    public boolean eliminarAccionesSocio(int pIdSocio)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM ACCION WHERE ACCION_SOCIO_ID=" + pIdSocio;
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
