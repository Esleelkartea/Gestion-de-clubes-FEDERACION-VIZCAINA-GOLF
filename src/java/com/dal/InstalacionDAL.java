/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Instalacion;
import com.Negocio.TipoInstalacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class InstalacionDAL {

    Conexion conexion;

     //Constructor
    public InstalacionDAL()
    {

    }

    //Otros metodos
    //Devuelve una instalacion de una idInstalacion
    public Instalacion getInstalacion(int pIdInstalacion)
    {
        conexion = new Conexion();
        Instalacion unaInstalacion = null;
        TipoInstalacionDAL tipoInstalacionDAL = new TipoInstalacionDAL();
        TipoInstalacion unTipoInstalacion = null;
        String consulta= "SELECT * FROM INSTALACION WHERE INSTALACION_ID = " + pIdInstalacion;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoInstalacion = tipoInstalacionDAL.getTipoInstalacion(rs.getInt("INSTALACION_TIPO"));
                 unaInstalacion = new Instalacion(rs.getInt("INSTALACION_ID"),rs.getString("INSTALACION_NOMBRE"),unTipoInstalacion,rs.getDouble("INSTALACION_TAR_ADULTO"),rs.getDouble("INSTALACION_TAR_MENOR"));
        }
        catch(Exception e)
        {
            unaInstalacion = null;
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
            return unaInstalacion;
        }
     }

    //Devuelve un arraylist de instalaciones de una busqueda por nombre, tipo_instalacion, tarifa adulto y/o tarifa menor
    public ArrayList<Instalacion> getInstalacion(int pIdInstalacion, String pNombre, TipoInstalacion pTipoInstalacion, Double pTarifaAdulto, Double pTarifaMenor)
    {
        conexion = new Conexion();
        ArrayList<Instalacion> listaInstalacion = new ArrayList<Instalacion>();
        Instalacion unaInstalacion;
        TipoInstalacionDAL tipoInstalacionDAL = new TipoInstalacionDAL();
        TipoInstalacion unTipoInstalacion  = null;
        String consulta= "SELECT * FROM INSTALACION ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdInstalacion != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_ID = " + pIdInstalacion;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_NOMBRE = '" + pNombre + "'";
             }


             if (pTipoInstalacion != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_TIPO = " + pTipoInstalacion.getIdTipoInstalacion();
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_TIPO = " + pTipoInstalacion.getIdTipoInstalacion();
             }

             if (pTarifaAdulto != -1.0)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_TAR_ADULTO = " + pTarifaAdulto;
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_TAR_ADULTO = " + pTarifaAdulto;
             }

             if (pTarifaMenor != -1.0)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE INSTALACION_TAR_MENOR = " + pTarifaMenor;
                     bConsul=true;
                 }
                 else
                     consulta += " AND INSTALACION_TAR_MENOR = " + pTarifaMenor;
             }

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                
                unTipoInstalacion = tipoInstalacionDAL.getTipoInstalacion(rs.getInt("INSTALACION_TIPO"));
                unaInstalacion = new Instalacion(rs.getInt("INSTALACION_ID"),rs.getString("INSTALACION_NOMBRE"),unTipoInstalacion,rs.getDouble("INSTALACION_TAR_ADULTO"),rs.getDouble("INSTALACION_TAR_MENOR"));
                listaInstalacion.add(unaInstalacion);

            }
       }
        catch(SQLException ex)
        {
            listaInstalacion = null;
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
            return listaInstalacion;
        }
    }

    //Inserta un registro en la tabla INSTALACION en la base de datos pasandole todos los campos
    public boolean insertarInstalacion(String pNombre, TipoInstalacion pTipoInstalacion, double pTarifaAdulto, double pTarifaMenor)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO INSTALACION (INSTALACION_NOMBRE, INSTALACION_TIPO, INSTALACION_TAR_ADULTO, INSTALACION_TAR_MENOR) VALUES ('" + pNombre + "'," + pTipoInstalacion.getIdTipoInstalacion() + "," + pTarifaAdulto + "," + pTarifaMenor + ")";
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

    //Modifica un registro de la tabla Instalacion de la base de datos pasandole todos los atributos
    public boolean modificarInstalacion(int pIdInstalacion, String pNombre, TipoInstalacion pTipoInstalacion, double pTarifaAdulto, double pTarifaMenor)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE INSTALACION SET INSTALACION_NOMBRE ='" + pNombre + "', INSTALACION_TIPO =" + pTipoInstalacion.getIdTipoInstalacion() + ", INSTALACION_TAR_ADULTO=" + pTarifaAdulto  + ", INSTALACION_TAR_MENOR=" + pTarifaMenor + " WHERE INSTALACION_ID=" + pIdInstalacion;
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

    //Eliminar un registro de la tabla Foto de la base de datos
    public boolean eliminarInstalacion(int pIdInstalacion)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM INSTALACION WHERE INSTALACION_ID=" + pIdInstalacion;
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
