/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Categoria;
import com.Negocio.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class ProductoDAL {

    Conexion conexion;


    //Constructor
    public ProductoDAL()
    {

    }


    public Producto getProducto(int pIdProducto)
    {
        conexion = new Conexion();
        Producto unProducto = null;
        CategoriaDAL categoriaDAL = new CategoriaDAL();
        Categoria unaCategoria = null;
        String consulta= "SELECT * FROM PRODUCTO WHERE PRODUCTO_ID = " + pIdProducto;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 unaCategoria = categoriaDAL.getCategoria(rs.getInt("PRODUCTO_CATEGORIA_ID"));
                 unProducto = new Producto(rs.getInt("PRODUCTO_ID"), unaCategoria, rs.getString("PRODUCTO_NOMBRE"),rs.getDouble("PRODUCTO_PRECIO"), rs.getString("PRODUCTO_CATEGORIA_NOMBRE"), rs.getString("PRODUCTO_CATEGORIA_SUBCATEGORIA"));
            }
        }
        catch(Exception e)
        {
            unProducto = null;
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
            return unProducto;
        }
     }

    public Producto getProducto(String pNombre)
    {
        conexion = new Conexion();
        Producto unProducto = null;
        CategoriaDAL categoriaDAL = new CategoriaDAL();
        Categoria unaCategoria = null;
        String consulta= "SELECT * FROM PRODUCTO WHERE PRODUCTO_NOMBRE ='" + pNombre + "'";
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 unaCategoria = categoriaDAL.getCategoria(rs.getInt("PRODUCTO_CATEGORIA_ID"));
                 unProducto = new Producto(rs.getInt("PRODUCTO_ID"), unaCategoria, rs.getString("PRODUCTO_NOMBRE"),rs.getDouble("PRODUCTO_PRECIO"), rs.getString("PRODUCTO_CATEGORIA_NOMBRE"), rs.getString("PRODUCTO_CATEGORIA_SUBCATEGORIA"));
            }
        }
        catch(Exception e)
        {
            unProducto = null;
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
            return unProducto;
        }
     }


    public ArrayList<Producto> getProducto(int pIdProducto, Categoria pCategoria, String pNombre, Double pPrecio, String pNombreCategoria, String pNombreSubcategoria)
    {
        conexion = new Conexion();
        ArrayList<Producto> listaProducto = new ArrayList<Producto>();
        Producto unProducto = null;
        Categoria unaCategoria = null;
        CategoriaDAL categoriaDAL = new CategoriaDAL();
        String consulta= "SELECT * FROM PRODUCTO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdProducto != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_ID = " + pIdProducto;
                     bConsul=true;
                 }
            }

             if (pCategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_CATEGORIA_ID =" + pCategoria.getIdCategoria();
                     bConsul=true;
                 }
                 else
                     consulta += " AND PRODUCTO_CATEGORIA_ID =" + pCategoria.getIdCategoria();
             }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PRODUCTO_NOMBRE = '" + pNombre + "'";
             }

             if (pNombreCategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_CATEGORIA_NOMBRE = '" + pNombreCategoria + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PRODUCTO_CATEGORIA_NOMBRE = '" + pNombreCategoria + "'";
             }

             if (pNombreSubcategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_CATEGORIA_SUBCATEGORIA = '" + pNombreSubcategoria + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PRODUCTO_CATEGORIA_SUBCATEGORIA = '" + pNombreSubcategoria + "'";
             }



            consulta += " ORDER BY PRODUCTO_CATEGORIA_ID";
            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaCategoria = categoriaDAL.getCategoria(rs.getInt("PRODUCTO_CATEGORIA_ID"));
                unProducto = new Producto(rs.getInt("PRODUCTO_ID"),unaCategoria,rs.getString("PRODUCTO_NOMBRE"), rs.getDouble("PRODUCTO_PRECIO"), rs.getString("PRODUCTO_CATEGORIA_NOMBRE"), rs.getString("PRODUCTO_CATEGORIA_SUBCATEGORIA"));
                listaProducto.add(unProducto);

            }
       }
        catch(SQLException ex)
        {
            listaProducto = null;
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
            return listaProducto;
        }
    }

    public ArrayList<Producto> getProductoStock(int pIdProducto, Categoria pCategoria, String pNombre, Double pPrecio, String pNombreCategoria, String pNombreSubcategoria)
    {
        conexion = new Conexion();
        ArrayList<Producto> listaProducto = new ArrayList<Producto>();
        Producto unProducto = null;
        Categoria unaCategoria = null;
        CategoriaDAL categoriaDAL = new CategoriaDAL();
        String consulta= "SELECT * FROM PRODUCTO ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pIdProducto != -1){
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_ID = " + pIdProducto;
                     bConsul=true;
                 }
            }

             if (pCategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_CATEGORIA_ID =" + pCategoria.getIdCategoria();
                     bConsul=true;
                 }
                 else
                     consulta += " AND PRODUCTO_CATEGORIA_ID =" + pCategoria.getIdCategoria();
             }

             if (pNombre != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_NOMBRE = '" + pNombre + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PRODUCTO_NOMBRE = '" + pNombre + "'";
             }

             if (pNombreCategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_CATEGORIA_NOMBRE = '" + pNombreCategoria + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PRODUCTO_CATEGORIA_NOMBRE = '" + pNombreCategoria + "'";
             }

             if (pNombreSubcategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE PRODUCTO_CATEGORIA_SUBCATEGORIA = '" + pNombreSubcategoria + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND PRODUCTO_CATEGORIA_SUBCATEGORIA = '" + pNombreSubcategoria + "'";
             }

             if (bConsul==false)
             {
                consulta += "WHERE PRODUCTO_STOCK=TRUE ORDER BY PRODUCTO_CATEGORIA_ID";
                bConsul=true;
             }
             else
                consulta += "AND PRODUCTO_STOCK=TRUE ORDER BY PRODUCTO_CATEGORIA_ID";

            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unaCategoria = categoriaDAL.getCategoria(rs.getInt("PRODUCTO_CATEGORIA_ID"));
                unProducto = new Producto(rs.getInt("PRODUCTO_ID"),unaCategoria,rs.getString("PRODUCTO_NOMBRE"), rs.getDouble("PRODUCTO_PRECIO"), rs.getString("PRODUCTO_CATEGORIA_NOMBRE"), rs.getString("PRODUCTO_CATEGORIA_SUBCATEGORIA"));
                listaProducto.add(unProducto);

            }
       }
        catch(SQLException ex)
        {
            listaProducto = null;
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
            return listaProducto;
        }
    }


    public boolean insertarProducto(Categoria pCategoria, String pNombre, double pPrecio)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO PRODUCTO (PRODUCTO_CATEGORIA_ID, PRODUCTO_NOMBRE, PRODUCTO_PRECIO, PRODUCTO_CATEGORIA_NOMBRE, PRODUCTO_CATEGORIA_SUBCATEGORIA, PRODUCTO_STOCK) VALUES (" + pCategoria.getIdCategoria() + ",'" + pNombre + "'," + pPrecio + ",'" + pCategoria.getCategoria() + "','" + pCategoria.getSubcategoria() + "'," + true + ")";
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

    public boolean modificarProducto(int pIdProducto, Categoria pCategoria, String pNombre, double pPrecio)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE PRODUCTO SET PRODUCTO_NOMBRE='" + pNombre + "', PRODUCTO_CATEGORIA_ID=" + pCategoria.getIdCategoria() + ", PRODUCTO_PRECIO=" + pPrecio + ", PRODUCTO_CATEGORIA_NOMBRE='" + pCategoria.getCategoria() + "', PRODUCTO_CATEGORIA_SUBCATEGORIA='" + pCategoria.getSubcategoria() + "' WHERE PRODUCTO_ID=" + pIdProducto;
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

    public boolean eliminarProducto(int pIdProducto)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM PRODUCTO WHERE PRODUCTO_ID=" + pIdProducto;
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

public boolean eliminarStock(int pIdProducto)
{
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE PRODUCTO SET PRODUCTO_STOCK=false WHERE PRODUCTO_ID=" + pIdProducto;
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
