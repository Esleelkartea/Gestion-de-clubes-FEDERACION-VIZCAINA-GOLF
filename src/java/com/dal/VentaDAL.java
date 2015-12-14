/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dal;

import com.Negocio.Producto;
import com.Negocio.Socio;
import com.Negocio.TipoPago;
import com.Negocio.Venta;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class VentaDAL {

    Conexion conexion;

    public VentaDAL()
    {

    }

    public Venta getVenta(int pIdVenta)
    {
        conexion = new Conexion();
        Venta unaVenta = null;
        Socio unSocio = null;
        Producto unProducto = null;
        TipoPago unTipoPago = null;
        ProductoDAL productoDAL = new ProductoDAL();
        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
        SocioDAL socioDAL = new SocioDAL();

        String consulta= "SELECT * FROM VENTA WHERE VENTA_ID = " + pIdVenta;
        ResultSet rs = null;
        try
        {
             rs = conexion.getStatement().executeQuery(consulta);
             if(rs.next())
             {
                 unSocio = socioDAL.getSocio(rs.getInt("VENTA_SOCIO_ID"));
                 unProducto = productoDAL.getProducto(rs.getInt("VENTA_PRODUCTO_ID"));
                 unTipoPago = tipoPagoDAL.getTipoPago(rs.getInt("VENTA_TIPO_PAGO_ID"));
                 unaVenta = new Venta(rs.getInt("VENTA_ID"),unSocio, rs.getDate("VENTA_FECHA"), unProducto, unTipoPago, rs.getBoolean("VENTA_PAGADO"), rs.getInt("VENTA_UNIDADES"), rs.getDouble("VENTA_IMPORTE"), rs.getString("VENTA_SOCIO_NOMBRE"), rs.getString("VENTA_SOCIO_APELLIDOS"), rs.getString("VENTA_CATEGORIA"), rs.getString("VENTA_SUBCATEGORIA"), rs.getString("VENTA_PRODUCTO_NOMBRE"));
              }
        }
        catch(Exception e)
        {
            unaVenta = null;
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
            return unaVenta;
        }
     }


    public ArrayList<Venta> getVenta(String pCategoria, String pSubCategoria, String pNombreSocio, String pApellidosSocio, String pProducto, Date pFechaInicio, Date pFechaFin)
    {
        conexion = new Conexion();
        ArrayList<Venta> listaVenta = new ArrayList<Venta>();
        Venta unaVenta;
        Socio unSocio = null;
        Producto unProducto = null;
        TipoPago unTipoPago = null;
        ProductoDAL productoDAL = new ProductoDAL();
        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
        SocioDAL socioDAL = new SocioDAL();

        String consulta= "SELECT * FROM VENTA ";
        ResultSet rs = null;
        boolean bConsul = false;

        try
        {
             if (pFechaInicio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (VENTA_FECHA > '" + pFechaInicio + "' OR VENTA_FECHA = '" + pFechaInicio + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (VENTA_FECHA > '" + pFechaInicio + "' OR VENTA_FECHA = '" + pFechaInicio + "')";
             }

             if (pFechaFin != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE (VENTA_FECHA < '" + pFechaFin + "' OR VENTA_FECHA = '" + pFechaFin + "')";
                     bConsul=true;
                 }
                 else
                     consulta += " AND (VENTA_FECHA < '" + pFechaFin + "' OR VENTA_FECHA = '" + pFechaFin + "')";
             }

            if (pCategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE VENTA_CATEGORIA = '" + pCategoria + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND VENTA_CATEGORIA = '" + pCategoria + "'";
             }

             if (pSubCategoria != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE VENTA_SUBCATEGORIA = '" + pSubCategoria + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND VENTA_SUBCATEGORIA = '" + pSubCategoria + "'";
             }
             
              if (pNombreSocio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE VENTA_SOCIO_NOMBRE = '" + pNombreSocio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND VENTA_SOCIO_NOMBRE = '" + pNombreSocio + "'";
             }

             if (pApellidosSocio != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE VENTA_SOCIO_APELLIDOS = '" + pApellidosSocio + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND VENTA_SOCIO_APELLIDOS = '" + pApellidosSocio + "'";
             }

             if (pProducto != null)
             {
                 if (bConsul==false)
                 {
                     consulta += "WHERE VENTA_PRODUCTO_NOMBRE = '" + pProducto + "'";
                     bConsul=true;
                 }
                 else
                     consulta += " AND VENTA_PRODUCTO_NOMBRE = '" + pProducto + "'";
             }





            rs = conexion.getStatement().executeQuery(consulta);
            while(rs.next())
            {
                unSocio = socioDAL.getSocio(rs.getInt("VENTA_SOCIO_ID"));
                unProducto = productoDAL.getProducto(rs.getInt("VENTA_PRODUCTO_ID"));
                unTipoPago = tipoPagoDAL.getTipoPago(rs.getInt("VENTA_TIPO_PAGO_ID"));

                unaVenta = new Venta(rs.getInt("VENTA_ID"), unSocio, rs.getDate("VENTA_FECHA"), unProducto, unTipoPago, rs.getBoolean("VENTA_PAGADO"), rs.getInt("VENTA_UNIDADES"), rs.getDouble("VENTA_IMPORTE"),rs.getString("VENTA_CATEGORIA"), rs.getString("VENTA_SUBCATEGORIA"), rs.getString("VENTA_SOCIO_NOMBRE"), rs.getString("VENTA_SOCIO_APELLIDOS"),rs.getString("VENTA_PRODUCTO_NOMBRE"));
                listaVenta.add(unaVenta);
            }
       }
        catch(SQLException ex)
        {
            listaVenta = null;
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
            return listaVenta;
        }
    }


    public boolean insertarVenta(Socio pSocio, Date pFecha, Producto pProducto, TipoPago pTipoPago, boolean pPagado, int pUnidades, double pImporte)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "INSERT INTO VENTA (VENTA_SOCIO_ID, VENTA_FECHA, VENTA_PRODUCTO_ID, VENTA_TIPO_PAGO_ID, VENTA_PAGADO, VENTA_UNIDADES, VENTA_IMPORTE, VENTA_SOCIO_NOMBRE, VENTA_SOCIO_APELLIDOS, VENTA_CATEGORIA, VENTA_SUBCATEGORIA, VENTA_PRODUCTO_NOMBRE) VALUES (" + pSocio.getIdSocio() + ",'" + pFecha + "'," + pProducto.getIdProducto() + "," + pTipoPago.getIdTipoPago() + "," + pPagado + "," + pUnidades + "," + pImporte + ",'" + pSocio.getNombre() + "','" + pSocio.getApellidos() + "','" + pProducto.getCategoria() + "','" + pProducto.getSubcategoria() + "','" + pProducto.getProducto() + "')";
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


    public boolean modificarVenta(int pIdVenta, Socio pSocio, Date pFecha, Producto pProducto, TipoPago pTipoPago, boolean pPagado)
    {
        boolean exito = true;
        int numFilas;
        conexion = new Conexion();
        String consulta= "UPDATE VENTA SET VENTA_SOCIO =" + pSocio.getIdSocio() + ", VENTA_FECHA='" + pFecha + "', VENTA_PRODUCTO_ID=" +  pProducto.getIdProducto() + ", VENTA_TIPO_PAGO_ID =" + pTipoPago.getIdTipoPago() + ", VENTA_PAGADO=" + pPagado  + " WHERE VENTA_ID=" + pIdVenta;
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


    public boolean eliminarVenta(int pIdVenta)
    {
        boolean exito = true;
        int numFilas;
        conexion= new Conexion();
        String consulta="DELETE FROM VENTA WHERE VENTA_ID=" + pIdVenta;
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
