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
public class ventaGestProductoServlet extends HttpServlet {
   
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

        ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
        listaCategorias = null;
        request.getSession().setAttribute("errorCategoriaProducto", null);

        try
        {
            CategoriaDAL categoriaDAL = new CategoriaDAL();

            Categoria unaCategoria = null;
             if(request.getParameter("var")!=null)
             {
                String idCategoria = request.getParameter("var");
                unaCategoria = categoriaDAL.getCategoria(Integer.parseInt(idCategoria));
                request.getSession().setAttribute("unaCategoria", unaCategoria);
                request.getSession().setAttribute("listaProductos", null);
             }
             else
             {
                 request.getSession().setAttribute("unaCategoria", null);
             }

            listaCategorias = categoriaDAL.getCategoria(-1, null, null);

            if(request.getParameter("varP")!=null)
            {
                Producto unProducto;
                ProductoDAL productoDAL = new ProductoDAL();
                String idProducto = request.getParameter("varP");
                unProducto = productoDAL.getProducto(Integer.parseInt(idProducto));
                request.getSession().setAttribute("unProducto", unProducto);
            }
            else
            {
                request.getSession().setAttribute("unProducto", null);
            }

          
        } 
        finally
        {
            request.getSession().setAttribute("listaCategorias", listaCategorias);
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
