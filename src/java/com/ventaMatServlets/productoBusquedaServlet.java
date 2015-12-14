/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ventaMatServlets;

import com.Negocio.Categoria;
import com.Negocio.Producto;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.CategoriaDAL;
import com.dal.ProductoDAL;
import com.dal.SocioDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class productoBusquedaServlet extends HttpServlet {
   
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

        request.getSession().setAttribute("listaProductos", null);
        request.getSession().setAttribute("unaCategoria", null);
         request.getSession().setAttribute("unProducto", null);
         
        ArrayList<Producto> listaProducto = new ArrayList<Producto>();
        listaProducto = null;

        try
        {
            String strNombre = request.getParameter("nombre");
             if (strNombre.equals(""))
                strNombre = null;

            String strCategoria = request.getParameter("categoria");
            if (strCategoria.equals(""))
                strCategoria = null;

            String strSubcategoria = request.getParameter("subcategoria");
            if (strSubcategoria.equals(""))
                strSubcategoria = null;


            ProductoDAL productoDAL = new ProductoDAL();

            listaProducto = productoDAL.getProductoStock(-1, null, strNombre, -1.0, strCategoria, strSubcategoria);

            request.getSession().setAttribute("listaProductos", listaProducto);

            Categoria unaCategoria = (Categoria)request.getSession().getAttribute("modificacionProducto");
            if (unaCategoria != null)
            {
                listaProducto = productoDAL.getProducto(-1, unaCategoria, null, -1.0, unaCategoria.getCategoria(), unaCategoria.getSubcategoria());
            }



        }
        catch(Exception ex)
        {
            request.getSession().setAttribute("listaProductos", null);
        }
        finally
        {
            getServletContext().getRequestDispatcher("/ventaGestionProductos.jsp").forward(request, response);
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
