/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Visita;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class VisitaDAL {

    Conexion conexion;

    public VisitaDAL()
    {

    }


    public Visita getVisita(int pIdVisita)
    {
        conexion = new Conexion();
        Visita unaVisita = null;

        String consulta= "SELECT * FROM VISITA WHERE VISITA_ID = " + pIdVisita;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unaVisita = new Visita(rs.getInt("VISITA_ID"),rs.getString("VISITA_NOMBRE"),rs.getString("VISITA_APELLIDOS"), rs.getString("VISITA_TELEFONO"), rs.getString("VISITA_DIRECCION"), rs.getString("VISITA_POBLACION"), rs.getString("VISITA_ATENDIDO"), rs.getDate("VISITA_FECHA"));
        }
        catch(Exception e)
        {
            unaVisita = null;
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
            return unaVisita;
        }
     }


    public ArrayList<Visita> getVisita(int pIdVisita, String pFechaInicio, String pFechaFin)
    {
        conexion = new Conexion();
        ArrayList<Visita> listaVisitas = new ArrayList<Visita>();
        Visita unaVisita;
        
        String consulta= "SELECT * FROM VISITA ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdVisita != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE VISITA_ID = " + pIdVisita;
                     bConsul=true;
                 }
            }

             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (VISITA_FECHA > '" + pFechaInicio + "' OR VISITA_FECHA = '" + pFechaInicio + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (VISITA_FECHA > '" + pFechaInicio + "' OR VISITA_FECHA = '" + pFechaInicio + "')";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (VISITA_FECHA < '" + pFechaFin + "' OR VISITA_FECHA = '" + pFechaFin + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (VISITA_FECHA < '" + pFechaFin + "' OR VISITA_FECHA = '" + pFechaFin + "')";
             }




            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                 unaVisita = new Visita(rs.getInt("VISITA_ID"),rs.getString("VISITA_NOMBRE"), rs.getString("VISITA_APELLIDOS"), rs.getString("VISITA_TELEFONO"), rs.getString("VISITA_DIRECCION"), rs.getString("VISITA_POBLACION"), rs.getString("VISITA_ATENDIDO"), rs.getDate("VISITA_FECHA"));
                 listaVisitas.add(unaVisita);
            }
       }
        catch(SQLException ex)
        {
            listaVisitas = null;
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
            return listaVisitas;
        }
    }


    public boolean insertarVisita(String pNombre, String pApellidos, String pTelefono, String pDireccion, String pPoblacion, String pAtendido, Date pFecha)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO VISITA (VISITA_NOMBRE, VISITA_APELLIDOS, VISITA_TELEFONO, VISITA_DIRECCION, VISITA_POBLACION, VISITA_ATENDIDO, VISITA_FECHA) VALUES ('" + pNombre + "','" + pApellidos + "','" + pTelefono + "','" + pDireccion + "','" + pPoblacion + "','" + pAtendido + "','" + pFecha + "')";
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


    public boolean modificarVisita(int pIdVisita, String pNombre, String pApellidos, String pTelefono, String pDireccion, String pPoblacion, String pAtendido, Date pFecha)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE VISITA SET VISITA_NOMBRE ='" + pNombre + "', VISITA_APELLIDOS='" + pApellidos + "', VISITA_TELEFONO='" +  pTelefono + "', VISITA_DIRECCION ='" + pDireccion + "', VISITA_POBLACION='" + pPoblacion  + "' , VISITA_ATENDIDO='" + pAtendido + "', VISITA_FECHA='" + pFecha + "' WHERE VISITA_ID=" + pIdVisita;
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


    public boolean eliminarVisita(int pIdVisita)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM VISITA WHERE VISITA_ID=" + pIdVisita;
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
