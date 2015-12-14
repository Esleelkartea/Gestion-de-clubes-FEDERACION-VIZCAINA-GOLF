/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.TipoPago;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */



public class TipoPagoDAL {

    Conexion conexion;

    public TipoPagoDAL()
    {

    }


    //Otros metodos
    //Devuelve un tipo de pago de un idTipoPago
    public TipoPago getTipoPago(int idTipoPago)
    {
        conexion = new Conexion();
        TipoPago unTipoPago = null;
        String consulta= "SELECT * FROM TIPO_PAGO WHERE TIPO_PAGO_ID = " + idTipoPago;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoPago = new TipoPago(rs.getInt("TIPO_PAGO_ID"),rs.getString("TIPO_PAGO_MODO"));
        }
        catch(Exception e)
        {
            unTipoPago = null;
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
            return unTipoPago;
        }
     }


     //Devuelve un arraylist de TipoPago de una busqueda por modo y/o idTipoPago
    public ArrayList<TipoPago> getTipoPago(int pIdTipoPago, String pModo)
    {
        conexion = new Conexion();
        ArrayList<TipoPago> listaTipoPago = new ArrayList<TipoPago>();
        TipoPago unTipoPago;
        String consulta= "SELECT * FROM TIPO_PAGO";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdTipoPago != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_INSTALACION_ID = " + pIdTipoPago;
                     bConsul=true;
                 }
            }

             if (pModo != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE TIPO_PAGO_MODO = '" + pModo + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND TIPO_PAGO_MODO = '" + pModo + "'";
             }



             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unTipoPago = new TipoPago(rs.getInt("TIPO_PAGO_ID"),rs.getString("TIPO_PAGO_MODO"));
                listaTipoPago.add(unTipoPago);
            }
       }
        catch(SQLException ex)
        {
            listaTipoPago = null;
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
            return listaTipoPago;
        }
    }

    //Inserta un registro en la tabla TIPO_PAGO en la base de datos pasandole todos los campos
    public boolean insertarTipoPago(String pModo)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO TIPO_PAGO  (TIPO_PAGO_MODO) VALUES ('" + pModo + "')";
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

 //Modifica un registro de la tabla TIPO_PAGO de la base de datos pasandole todos los atributos
    public boolean modificarTipoPago(int pIdTipoPago, String pModo)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE TIPO_PAGO SET TIPO_PAGO_MODO ='" + pModo + "' WHERE TIPO_PAGO_ID=" + pIdTipoPago;
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

    //Eliminar un registro de la tabla TIPO_PAGO de la base de datos
    public boolean eliminarTipoPago(int pIdTipoPago)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM TIPO_PAGO WHERE TIPO_PAGO_ID=" + pIdTipoPago;
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
