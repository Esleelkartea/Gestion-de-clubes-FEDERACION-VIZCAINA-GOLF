/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.TipoInvitacion;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Julen
 */
public class TipoInvitacionDAL {

    Conexion conexion;

    public TipoInvitacionDAL()
    {

    }

    public TipoInvitacion getTipoInvitacion(int idTipoInvitacion)
    {
        conexion = new Conexion();
        TipoInvitacion unTipoInvitacion = null;
        String consulta= "SELECT * FROM TIPO_INVITACION WHERE TIPO_INVITACION_ID = " + idTipoInvitacion;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unTipoInvitacion = new TipoInvitacion(rs.getInt("TIPO_INVITACION_ID"),rs.getDouble("TIPO_INV_ADUL_LAB"),rs.getDouble("TIPO_INV_ADUL_FEST"),rs.getDouble("TIPO_INV_MEN_LAB"),rs.getDouble("TIPO_INV_MEN_FEST"),rs.getInt("TIPO_INV_MAXVISITAS"));
                 
        }
        catch(Exception e)
        {
            unTipoInvitacion = null;
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
            return unTipoInvitacion;
        }
     }


   public boolean modificarTipoInvitacion(int pIdTipoInvitacion, double pAdulLab, double pAdulFest, double pMenLab, double pMenFest, int pMaxVisit)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE TIPO_INVITACION SET TIPO_INV_ADUL_LAB =" + pAdulLab + ", TIPO_INV_ADUL_FEST=" + pAdulFest + ", TIPO_INV_MEN_LAB = " + pMenLab + ", TIPO_INV_MEN_FEST = " + pMenFest + ", TIPO_INV_MAXVISITAS = " + pMaxVisit + " WHERE TIPO_INVITACION_ID=" + pIdTipoInvitacion;
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


}
