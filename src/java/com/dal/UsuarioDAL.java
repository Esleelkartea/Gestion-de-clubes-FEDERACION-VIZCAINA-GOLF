/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class UsuarioDAL {

    Conexion conexion;


    public Usuario getUsuario(String pNombre, String pPassword)
    {
        conexion = new Conexion();
        Usuario unUsuario = null;
        String consulta= "SELECT * FROM USUARIO WHERE USUARIO_NOMBRE = '" + pNombre + "' AND USUARIO_PASSWORD='" + pPassword + "'";
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unUsuario = new Usuario(rs.getInt("USUARIO_ID"),rs.getString("USUARIO_NOMBRE"),rs.getString("USUARIO_PASSWORD"));
        }
        catch(Exception e)
        {
            unUsuario = null;
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
            return unUsuario;
        }
    }

    public Usuario getUsuario(int pIdUsuario)
    {
        conexion = new Conexion();
        Usuario unUsuario = null;
        String consulta= "SELECT * FROM USUARIO WHERE USUARIO_ID= " + pIdUsuario;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unUsuario = new Usuario(rs.getInt("USUARIO_ID"),rs.getString("USUARIO_NOMBRE"),rs.getString("USUARIO_PASSWORD"));
        }
        catch(Exception e)
        {
            unUsuario = null;
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
            return unUsuario;
        }
    }

    public Usuario getUsuario(String pNombre)
    {
        conexion = new Conexion();
        Usuario unUsuario = null;
        String consulta= "SELECT * FROM USUARIO WHERE USUARIO_NOMBRE= '" + pNombre + "'";
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unUsuario = new Usuario(rs.getInt("USUARIO_ID"),rs.getString("USUARIO_NOMBRE"),rs.getString("USUARIO_PASSWORD"));
        }
        catch(Exception e)
        {
            unUsuario = null;
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
            return unUsuario;
        }
    }

    public ArrayList<Usuario> getListaUsuarios(int pIdUsuario, String pNombre, String pPassword)
    {
        conexion = new Conexion();
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        Usuario unUsuario;
        String consulta= "SELECT * FROM USUARIO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdUsuario != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE USUARIO_ID = " + pIdUsuario;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE USUARIO_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND USUARIO_NOMBRE = '" + pNombre + "'";
             }

             if (pPassword != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE USUARIO_PASSWORD = " + pPassword + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND USUARIO_PASSWORD = " + pPassword + "'";
             }


             rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unUsuario = new Usuario(rs.getInt("USUARIO_ID"), rs.getString("USUARIO_NOMBRE"), rs.getString("USUARIO_PASSWORD"));
                listaUsuarios.add(unUsuario);
            }
       }
        catch(SQLException ex)
        {
            listaUsuarios = null;
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
            return listaUsuarios;
        }
    }


    public boolean insertarUsuario(String pNombre, String pPassword)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO USUARIO (USUARIO_NOMBRE, USUARIO_PASSWORD) VALUES ('" + pNombre + "','" + pPassword + "')";
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

    public boolean modificarUsuario(String pIdUsuario, String pNombre, String pPassword)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE USUARIO SET USUARIO_NOMBRE ='" + pNombre + "', USUARIO_PASSWORD='" + pPassword + "' WHERE USUARIO_ID=" + pIdUsuario;
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

    public boolean eliminarUsuario(String pIdUsuario)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta="DELETE FROM USUARIO WHERE USUARIO_ID=" + pIdUsuario;
        try
        {
            numFilas = conexion.getStatement().executeUpdate(consulta);

            if (numFilas == 0){
                exito = false;
            }
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
