/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.confServlets;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import com.dal.UsuarioDAL;
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
public class confUsuarioMIBServlet extends HttpServlet {
   
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

        UsuarioDAL usuarioDAL = new UsuarioDAL();
        Usuario unUsuario = (Usuario)request.getSession().getAttribute("usuario");
        Usuario otroUsuario = (Usuario)request.getSession().getAttribute("otroUsuario");

        try
        {
             //Insercion usuario
            if (request.getParameter("enviar").equals("Alta"))
            {
                String strNombre = request.getParameter("nombre");
                String strPassword = request.getParameter("password");
                usuarioDAL.insertarUsuario(strNombre, strPassword);
                request.getRequestDispatcher("confUsuarioServlet").forward(request, response);
                return;
            }

            //Modificacion el usuario
            if (request.getParameter("enviar").equals("Modificacion"))
            {
                String strNombre = request.getParameter("nombre");
                String strPassword = request.getParameter("password");

                if(unUsuario.getNombre().equals(otroUsuario.getNombre())&&unUsuario.getPassword().equals(otroUsuario.getPassword()))
                {
                    //request.getSession().setAttribute("rdoUsuarioMIB", "No se puede modificar/eliminar el usuario activo");
                }
                else
                {
                    String strUsuarioId = request.getParameter("usuarioId");
                    usuarioDAL.modificarUsuario(strUsuarioId, strNombre, strPassword);
                }

                request.getRequestDispatcher("confUsuarioServlet").forward(request, response);
                return;
            }

            //Eliminar el usuario
            if (request.getParameter("enviar").equals("Baja"))
            {
                String strNombre = request.getParameter("nombre");
                String strPassword = request.getParameter("password");

                if(unUsuario.getNombre().equals(strNombre)&&unUsuario.getPassword().equals(strPassword))
                {
                    //request.getSession().setAttribute("rdoUsuarioMIB", "No se puede modificar/eliminar el usuario activo");
                }
                else
                {
                    String strUsuarioId = request.getParameter("usuarioId");
                    usuarioDAL.eliminarUsuario(strUsuarioId);
                }

                request.getRequestDispatcher("confUsuarioServlet").forward(request, response);
                return;
            }

        } 
         catch(Exception ex)
        {
            request.getRequestDispatcher("confUsuarioServlet").forward(request, response);
            out.close();
        }
        finally
        {
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
