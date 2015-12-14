/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Socio;
import com.Negocio.TipoConvenio;
import com.Negocio.Usuario;
import com.Negocio.UsuariosConvenio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class UsuarioConvenioDAL {

    Conexion conexion;

    public UsuarioConvenioDAL()
    {

    }

    public UsuariosConvenio getUsuarioConvenio(int idUsuarioConvenio)
    {
        conexion = new Conexion();
        UsuariosConvenio unUsuarioConvenio = null;
        String consulta= "SELECT * FROM USUARIO_CONVENIO WHERE USUARIO_CONVENIO_ID = " + idUsuarioConvenio;
        ResultSet rs = null;
        SocioDAL socioDAL = new SocioDAL();
        Socio unSocio = null;
        TipoConvenioDAL tipoConvenioDAL = new TipoConvenioDAL();
        TipoConvenio tipoConvenio = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 unSocio = socioDAL.getSocioId(rs.getInt("USUARIO_CONVENIO_USU_ID"));
                 tipoConvenio = tipoConvenioDAL.getTipoConvenio(rs.getInt("USUARIO_CONVENIO_TIPCONV_ID"));
                 unUsuarioConvenio = new UsuariosConvenio(rs.getInt("USUARIO_CONVENIO_ID"), unSocio, tipoConvenio);
             }
        }
        catch(Exception e)
        {
            unUsuarioConvenio = null;
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
            return unUsuarioConvenio;
        }
     }


    public ArrayList<UsuariosConvenio> getUsuariosConvenio(int pIdUsuarioConvenio, int pIdUsuario, int pIdTipoConvenio)
    {
        conexion = new Conexion();
        ArrayList<UsuariosConvenio> listaUsuariosConvenio = new ArrayList<UsuariosConvenio>();
        UsuariosConvenio unUsuarioConvenio;
        SocioDAL socioDAL = new SocioDAL();
        Socio unSocio = null;
        TipoConvenioDAL tipoConvenioDAL = new TipoConvenioDAL();
        TipoConvenio tipoConvenio = null;
        String consulta= "SELECT * FROM USUARIO_CONVENIO";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdTipoConvenio != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE USUARIO_CONVENIO_ID = " + pIdUsuarioConvenio;
                     bConsul=true;
                 }
            }

             if (pIdUsuario != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE USUARIO_CONVENIO_USU_ID = " + pIdUsuario;
                     bConsul=true;
                 }
                 else
                     consulta += " AND USUARIO_CONVENIO_USU_ID = " + pIdUsuario;
             }

             if (pIdUsuario != -1)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE USUARIO_CONVENIO_TIPCONV_ID = " + pIdTipoConvenio;
                     bConsul=true;
                 }
                 else
                     consulta += " AND USUARIO_CONVENIO_TIPCONV_I = " + pIdTipoConvenio;
             }

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unSocio = socioDAL.getSocioId(rs.getInt("USUARIO_CONVENIO_USU_ID"));
                tipoConvenio = tipoConvenioDAL.getTipoConvenio(rs.getInt("USUARIO_CONVENIO_TIPCONV_ID"));
                unUsuarioConvenio = new UsuariosConvenio(rs.getInt("USUARIO_CONVENIO_ID"), unSocio, tipoConvenio);

                listaUsuariosConvenio.add(unUsuarioConvenio);
            }
       }
        catch(SQLException ex)
        {
            listaUsuariosConvenio = null;
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
            return listaUsuariosConvenio;
        }
    }

    public boolean insertarUsuarioConvenio(int pIdSocio, int pIdTipoConvenio)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO USUARIO_CONVENIO (USUARIO_CONVENIO_USU_ID, USUARIO_CONVENIO_TIPCONV_ID) VALUES (" + pIdSocio + "," + pIdTipoConvenio + ")";
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

    public boolean modificarUsuarioConvenio(int pIdUsuarioConvenio, int pIdSocio, int pIdTipoConvenio)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE USUARIO_CONVENIO SET USUARIO_CONVENIO_USU_ID =" + pIdSocio +", USUARIO_CONVENIO_TIPCONV_ID=" + pIdTipoConvenio + " WHERE USUARIO_CONVENIO_ID=" + pIdUsuarioConvenio;
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


    public boolean modificarConvenio(int pIdUsuarioConvenio, int pIdTipoConvenio)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE USUARIO_CONVENIO SET USUARIO_CONVENIO_TIPCONV_ID=" + pIdTipoConvenio + " WHERE USUARIO_CONVENIO_ID=" + pIdUsuarioConvenio;
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


    public boolean eliminarUsuarioConvenio(int pIdUsuarioConvenio)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM USUARIO_CONVENIO WHERE USUARIO_CONVENIO_ID=" + pIdUsuarioConvenio;
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
