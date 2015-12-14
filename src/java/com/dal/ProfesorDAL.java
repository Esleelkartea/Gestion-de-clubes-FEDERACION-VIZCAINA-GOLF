/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Profesor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class ProfesorDAL {

    Conexion conexion;

    //Constructor
    public ProfesorDAL()
    {

    }


    public Profesor getProfesor(int pIdProfesor)
    {
        conexion = new Conexion();
        Profesor unProfesor = null;
        String consulta= "SELECT * FROM PROFESOR WHERE PROFESOR_ID = " + pIdProfesor;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 unProfesor = new Profesor(rs.getInt("PROFESOR_ID"), rs.getString("PROFESOR_NOMBRE"), rs.getString("PROFESOR_APELLIDOS"), rs.getString("PROFESOR_EMAIL"), rs.getString("PROFESOR_TELEFONO"),rs.getInt("PROFESOR_TIPO_INSTALACION_ID"),rs.getDouble("PROFESOR_IMPORTE"));
            }
        }
        catch(Exception e)
        {
            unProfesor = null;
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
            return unProfesor;
        }
     }


    public ArrayList<Profesor> getProfesor(int pIdProfesor, String pNombre, String pApellidos, String pEmail, String pTelefono, int pIdTipoInstalacion)
    {
        conexion = new Conexion();
        ArrayList<Profesor> listaProfesor = new ArrayList<Profesor>();
        Profesor unProfesor = null;
        String consulta= "SELECT * FROM PROFESOR ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdProfesor != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_ID = " + pIdProfesor;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_NOMBRE = '" + pNombre + "'";
             }

             if (pApellidos != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_APELLIDOS = '" + pApellidos + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_APELLIDOS = '" + pApellidos + "'";
             }

             if (pEmail != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_EMAIL = '" + pEmail + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_EMAIL = '" + pEmail + "'";
             }

             if (pIdTipoInstalacion != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PROFESOR_TIPO_INSTALACION_ID = " + pIdTipoInstalacion;
                     bConsul=true;
                 }
                 else
                     consulta += " AND PROFESOR_TIPO_INSTALACION_ID = " + pIdTipoInstalacion;
             }

          
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {               
                unProfesor = new Profesor(rs.getInt("PROFESOR_ID"),rs.getString("PROFESOR_NOMBRE"), rs.getString("PROFESOR_APELLIDOS"), rs.getString("PROFESOR_EMAIL"), rs.getString("PROFESOR_TELEFONO"),rs.getInt("PROFESOR_TIPO_INSTALACION_ID"),rs.getDouble("PROFESOR_IMPORTE"));
                listaProfesor.add(unProfesor);
            }
       }
        catch(SQLException ex)
        {
            listaProfesor = null;
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
            return listaProfesor;
        }
    }


    public boolean insertarProfesor(String pNombre, String pApellidos, String pEmail, String pTelefono, int pIdTipoInstalacion, double pImporte)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO PROFESOR (PROFESOR_NOMBRE, PROFESOR_APELLIDOS, PROFESOR_EMAIL, PROFESOR_TELEFONO, PROFESOR_TIPO_INSTALACION_ID, PROFESOR_IMPORTE) VALUES ('" + pNombre + "','" + pApellidos + "','" + pEmail + "','" + pTelefono + "'," + pIdTipoInstalacion + "," + pImporte + ")";
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

    public boolean modificarProfesor(int pIdProfesor, String pNombre, String pApellidos, String pEmail, String pTelefono, int pIdTipoInstalacion, double pImporte)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE PROFESOR SET PROFESOR_NOMBRE='" + pNombre + "', PROFESOR_APELLIDOS='" + pApellidos + "', PROFESOR_EMAIL='" + pEmail + "', PROFESOR_TELEFONO='" + pTelefono + "', PROFESOR_TIPO_INSTALACION_ID=" + pIdTipoInstalacion + ", PROFESOR_IMPORTE=" + pImporte + " WHERE PROFESOR_ID=" + pIdProfesor;
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

    public boolean eliminarProfesor(int pIdProfesor)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM PROFESOR WHERE PROFESOR_ID=" + pIdProfesor;
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




    public ArrayList<Profesor> getProfesorActividad(int pIdTipoInstalacion)
    {
        conexion = new Conexion();
        ArrayList<Profesor> listaProfesor = new ArrayList<Profesor>();
        Profesor unProfesor = null;
        String consulta= "SELECT * FROM PROFESOR WHERE PROFESOR_TIPO_INSTALACION_ID = " + pIdTipoInstalacion;
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unProfesor = new Profesor(rs.getInt("PROFESOR_ID"),rs.getString("PROFESOR_NOMBRE"), rs.getString("PROFESOR_APELLIDOS"), rs.getString("PROFESOR_EMAIL"), rs.getString("PROFESOR_TELEFONO"),rs.getInt("PROFESOR_TIPO_INSTALACION_ID"),rs.getDouble("PROFESOR_IMPORTE"));
                listaProfesor.add(unProfesor);
            }
       }
        catch(SQLException ex)
        {
            listaProfesor = null;
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
            return listaProfesor;
        }
    }

}
