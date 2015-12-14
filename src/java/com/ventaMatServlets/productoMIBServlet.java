/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ventaMatServlets;

import com.Negocio.Categoria;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.CategoriaDAL;
import com.dal.ProductoDAL;
import com.dal.SocioDAL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class productoMIBServlet extends HttpServlet {
   
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
            String strCategoria = request.getParameter("categoriaProducto");
            String strSubcategoria = request.getParameter("subcategoriaProducto");
            CategoriaDAL categoriaDAL = new CategoriaDAL();
            Categoria unaCategoria = null;
            unaCategoria = categoriaDAL.getCategoria(strCategoria, strSubcategoria);
            if (unaCategoria == null)
                request.getSession().setAttribute("errorCategoriaProducto", true);
            else
            {
                ProductoDAL productoDAL = new ProductoDAL();
                request.getSession().setAttribute("errorCategoriaProducto", false);
                if (request.getParameter("enviar").equals("Alta"))
                {
                    String strProducto = request.getParameter("producto");
                    String strPrecio = request.getParameter("precio");
                    if(productoDAL.insertarProducto(unaCategoria, strProducto, Double.parseDouble(strPrecio)))
                    {
                        request.getSession().setAttribute("resultadoProducto", "La insercion se ha realizado correctamente.");
                    }
                    else
                    {
                        request.getSession().setAttribute("resultadoProducto", "La insercion no se ha podido realizar.");
                    }

                    request.getSession().setAttribute("modificacionProducto", unaCategoria);

                    request.getRequestDispatcher("/productoBusquedaServlet").forward(request, response);
                    return;
                }

                if (request.getParameter("enviar").equals("Modificar"))
                {
                    String strProducto = request.getParameter("producto");
                    String strPrecio = request.getParameter("precio");
                    String strIdProducto = request.getParameter("idProducto");
                    if(productoDAL.modificarProducto(Integer.parseInt(strIdProducto), unaCategoria, strProducto, Double.parseDouble(strPrecio)))
                    {
                        request.getSession().setAttribute("resultadoProducto", "La modificacion se ha realizado correctamente.");
                    }
                    else
                    {
                        request.getSession().setAttribute("resultadoProducto", "La modificacion no se ha podido realizar.");
                    }
                    
                    request.getRequestDispatcher("/productoBusquedaServlet").forward(request, response);
                    return;
                }

                if (request.getParameter("enviar").equals("Baja"))
                {
                    String strIdProducto = request.getParameter("idProducto");
                    if(productoDAL.eliminarStock(Integer.parseInt(strIdProducto)))
                    {
                        request.getSession().setAttribute("resultadoProducto", "La eliminacion se ha realizado correctamente.");
                    }
                    else
                    {
                        request.getSession().setAttribute("resultadoProducto", "La eliminacion no se ha podido realizar.");
                    }

                    request.getRequestDispatcher("/productoBusquedaServlet").forward(request, response);
                    return;
                }
            }
        }
        catch(Exception ex)
        {
           
        }
        finally
        {
            getServletContext().getRequestDispatcher("/productoBusquedaServlet").forward(request, response);
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
