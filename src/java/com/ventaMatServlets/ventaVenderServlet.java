/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ventaMatServlets;

import com.Negocio.Producto;
import com.Negocio.Socio;
import com.Negocio.TipoPago;
import com.Negocio.Usuario;
import com.dal.ProductoDAL;
import com.dal.SocioDAL;
import com.dal.TipoPagoDAL;
import com.dal.VentaDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class ventaVenderServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Comprobacion del login
         if(request.getSession().getAttribute("usuario") == null)
        {
             request.getRequestDispatcher("index.jsp").forward(request, response);
             return;
        }
        else
        {
            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio;
            Usuario unUsuario = (Usuario)request.getSession().getAttribute("usuario");
            unSocio = socioDAL.getSocio(unUsuario.getIdUsuario());
            if (unSocio != null)
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        }

        try
        {
            String strNombre = request.getParameter("nombreSocio");
            String strApellidos = request.getParameter("apellidosSocio");
            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio = socioDAL.getSocio(strNombre, strApellidos);
        
             if (unSocio == null)
                request.getSession().setAttribute("resultadoSocioVenta", false);
            else
            {
                request.getSession().setAttribute("resultadoSocioVenta", true);
                String strProducto = request.getParameter("producto");
                ProductoDAL productoDAL = new ProductoDAL();
                Producto unProducto = null;
                unProducto = productoDAL.getProducto(Integer.parseInt(strProducto));
                if (unProducto == null)
                {
                    request.getSession().setAttribute("resultadoProductoVenta", false);
                }
                else
                {
                    request.getSession().setAttribute("resultadoProductoVenta", true);
                    String strUnidades = request.getParameter("unidades");
                    //String strImporte = request.getParameter("importe");
                    String strPago = request.getParameter("formaPago");
                    String strFecha = request.getParameter("fecha");
                    String strPagado = request.getParameter("pagado");

                    boolean pagado = true;
                    if (strPagado == null)
                        pagado = false;

                    if(strUnidades.equals("") || strPago.equals("") || strFecha.equals(""))
                    {
                        request.getSession().setAttribute("resultadoVenta", false);
                    }
                    else
                    {
                        request.getSession().setAttribute("resultadoVenta", true);
                        
                        TipoPago unTipoPago = null;
                        TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
                        unTipoPago = tipoPagoDAL.getTipoPago(Integer.parseInt(strPago));
                        VentaDAL ventaDAL = new VentaDAL();
                        Date fecha;

                        String fechaFin[] =  strFecha.split("/");
                        strFecha = fechaFin[2]+ "-" + fechaFin[1] + "-" + fechaFin[0];
                        fecha = Date.valueOf(strFecha);
                        
                        ventaDAL.insertarVenta(unSocio, fecha, unProducto, unTipoPago, pagado, Integer.parseInt(strUnidades), unProducto.getPrecio()*Integer.parseInt(strUnidades));
                    } 
                }
            }       

        }
        catch(Exception ex)
        {
          
        }
        finally
        {
            request.getRequestDispatcher("ventaGestionVentas.jsp").forward(request, response);
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
