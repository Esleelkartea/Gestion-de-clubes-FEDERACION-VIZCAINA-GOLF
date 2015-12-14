/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class CategoriaDAL {

    Conexion conexion;

    //Constructor
    public CategoriaDAL()
    {

    }

    public Categoria getCategoria(int pIdCategoria)
    {
        conexion = new Conexion();
        Categoria unaCategoria = null;
        String consulta= "SELECT * FROM CATEGORIA WHERE CATEGORIA_ID = " + pIdCategoria;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unaCategoria = new Categoria(rs.getInt("CATEGORIA_ID"), rs.getString("CATEGORIA_NOMBRE"), rs.getString("CATEGORIA_SUBCATEGORIA"));
        }
        catch(Exception e)
        {
            unaCategoria = null;
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
            return unaCategoria;
        }
     }

    public Categoria getCategoria(String pNombre, String pSubcategoria)
    {
        conexion = new Conexion();
        Categoria unaCategoria = null;
        String consulta= "SELECT * FROM CATEGORIA ";
         boolean bConsul = false;

         if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CATEGORIA_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CATEGORIA_NOMBRE = '" + pNombre + "'";
             }


             if (pSubcategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CATEGORIA_SUBCATEGORIA='" + pSubcategoria + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CATEGORIA_SUBCATEGORIA='" + pSubcategoria + "'";
             }

        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
                 unaCategoria = new Categoria(rs.getInt("CATEGORIA_ID"), rs.getString("CATEGORIA_NOMBRE"), rs.getString("CATEGORIA_SUBCATEGORIA"));
        }
        catch(Exception e)
        {
            unaCategoria = null;
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
            return unaCategoria;
        }
     }



    public ArrayList<Categoria> getCategoria(int pIdCategoria, String pNombre, String pSubCategoria)
    {
        conexion = new Conexion();
        ArrayList<Categoria> listaCategoria = new ArrayList<Categoria>();
        Categoria unaCategoria = null;
        String consulta= "SELECT * FROM CATEGORIA ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdCategoria != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE CATEGORIA_ID = " + pIdCategoria;
                     bConsul=true;
                 }
            }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CATEGORIA_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CATEGORIA_NOMBRE = '" + pNombre + "'";
             }


             if (pSubCategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE CATEGORIA_SUBCATEGORIA='" + pSubCategoria + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND CATEGORIA_SUBCATEGORIA='" + pSubCategoria + "'";
             }

            consulta += " ORDER BY CATEGORIA_NOMBRE, CATEGORIA_SUBCATEGORIA";
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaCategoria = new Categoria(rs.getInt("CATEGORIA_ID"), rs.getString("CATEGORIA_NOMBRE"), rs.getString("CATEGORIA_SUBCATEGORIA"));
                listaCategoria.add(unaCategoria);

            }
       }
        catch(SQLException ex)
        {
            listaCategoria = null;
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
            return listaCategoria;
        }
    }


    public boolean insertarCategoria(String pNombre, String pSubcategoria)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO CATEGORIA (CATEGORIA_NOMBRE, CATEGORIA_SUBCATEGORIA) VALUES ('" + pNombre + "','" + pSubcategoria + "')";
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


    public boolean modificarCategoria(int pIdCategoria, String pNombre, String pSubcategoria)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE CATEGORIA SET CATEGORIA_NOMBRE='" + pNombre + "', CATEGORIA_SUBCATEGORIA='" + pSubcategoria + "' WHERE CATEGORIA_ID=" + pIdCategoria;
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

    public boolean eliminarCategoria(int pIdCategoria)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM CATEGORIA WHERE CATEGORIA_ID=" + pIdCategoria;
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
