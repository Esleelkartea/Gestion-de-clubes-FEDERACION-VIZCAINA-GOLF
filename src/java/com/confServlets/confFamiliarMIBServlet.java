/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.confServlets;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import com.dal.TipoFamiliarDAL;
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
public class confFamiliarMIBServlet extends HttpServlet {
   
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

        TipoFamiliarDAL tipoFamiliarDAL = new TipoFamiliarDAL();
        
        
        try
        {

            if (request.getParameter("enviar").equals("Alta"))
            {
                String strNombre = request.getParameter("tipoParentesco");
                tipoFamiliarDAL.insertarTipoFamiliar(strNombre);
                request.getRequestDispatcher("confSocioServlet").forward(request, response);
                return;
            }

            if (request.getParameter("enviar").equals("Modificar"))
            {
                String strIdTipoFamiliar = request.getParameter("tipoSociosId");
                String strNombre = request.getParameter("tipoParentesco");
                tipoFamiliarDAL.modificarTipoFamiliar(Integer.parseInt(strIdTipoFamiliar), strNombre);
                request.getRequestDispatcher("confSocioServlet").forward(request, response);
                return;
            }

            if (request.getParameter("enviar").equals("Baja"))
            {
                String strIdTipoFamiliar = request.getParameter("tipoSociosId");
                tipoFamiliarDAL.eliminarTipoFamiliar(Integer.parseInt(strIdTipoFamiliar));
                request.getRequestDispatcher("confSocioServlet").forward(request, response);
                return;
            }
        }
        catch(Exception ex)
        {
            request.getRequestDispatcher("confSocioServlet").forward(request, response);
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
