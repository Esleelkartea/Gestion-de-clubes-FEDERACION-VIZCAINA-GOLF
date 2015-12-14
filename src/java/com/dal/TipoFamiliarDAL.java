/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;


import com.Negocio.TipoFamiliar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Julen
 */
public class TipoFamiliarDAL {

    //Atributos
    Conexion conexion;

    //Constructores
    public TipoFamiliarDAL()
    {

    }


   //Otros metodos
    //Devuelve un tipo de familiar de un idTipoFamiliar
    public TipoFamiliar getTipoFamiliar(int idTipoFamiliar)
    {
        conexion = new Conexion();
        TipoFamiliar unTipoFamiliar = null;
        String consulta= "SELECT * FROM TIPO_FAMILIAR WHERE TIPO_FAMILIAR_ID = " + idTipoFamiliar;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoFamiliar = new TipoFamiliar(rs.getInt("TIPO_FAMILIAR_ID"),rs.getString("TIPO_FAMILIAR_NOMBRE"));
        }
        catch(Exception e)
        {
            unTipoFamiliar = null;
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
            return unTipoFamiliar;
        }
     }

    //Devuelve un arraylist de TipoFamiliar de una busqueda por descripcion y/o idTipoInstalacion
    public ArrayList<TipoFamiliar> getTipoFamiliar(int pIdTipoFamiliar, String pNombre)
    {
        conexion = new Conexion();
        ArrayList<TipoFamiliar> listaTipoFamiliar = new ArrayList<TipoFamiliar>();
        TipoFamiliar unTipoFamiliar;
        String consulta= "SELECT * FROM TIPO_FAMILIAR";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdTipoFamiliar != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_FAMILIAR_ID = " + pIdTipoFamiliar;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_FAMILIAR_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND TIPO_FAMILIAR_NOMBRE = '" + pNombre + "'";
             }



             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unTipoFamiliar = new TipoFamiliar(rs.getInt("TIPO_FAMILIAR_ID"),rs.getString("TIPO_FAMILIAR_NOMBRE"));
                listaTipoFamiliar.add(unTipoFamiliar);
            }
       }
        catch(SQLException ex)
        {
            unTipoFamiliar = null;
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
            return listaTipoFamiliar;
        }
    }


    //Inserta un registro en la tabla tipoFamiliar en la base de datos pasandole todos los campos
    public boolean insertarTipoFamiliar(String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO TIPO_FAMILIAR (TIPO_FAMILIAR_NOMBRE) VALUES ('" + pNombre + "')";
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

    //Modifica un registro de la tabla TIPO_FAMILIAR de la base de datos pasandole todos los atributos
    public boolean modificarTipoFamiliar(int pIdTipoFamiliar, String pNombre)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE TIPO_FAMILIAR SET TIPO_FAMILIAR_NOMBRE ='" + pNombre + "' WHERE TIPO_FAMILIAR_ID=" + pIdTipoFamiliar;
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


    //Eliminar un registro de la tabla TIPO_FAMILIAR de la base de datos
    public boolean eliminarTipoFamiliar(int pIdTipoFamiliar)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM TIPO_FAMILIAR WHERE TIPO_FAMILIAR_ID=" + pIdTipoFamiliar;
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
