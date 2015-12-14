/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.confServlets;

import com.Negocio.Socio;
import com.Negocio.TipoFamiliar;
import com.Negocio.TipoSocio;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import com.dal.TipoFamiliarDAL;
import com.dal.TipoSocioDAL;
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
public class confSocioServlet extends HttpServlet {
   
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

        ArrayList<TipoSocio> listaTipoSocio = new ArrayList<TipoSocio>();
        ArrayList<TipoFamiliar> listaTipoFamiliar = new ArrayList<TipoFamiliar>();
        try
        {
            TipoSocioDAL tipoSocioDAL = new TipoSocioDAL();
            listaTipoSocio = tipoSocioDAL.getTipoSocio(-1, null, -1.0);

            TipoFamiliarDAL tipoFamiliarDAL = new TipoFamiliarDAL();
            listaTipoFamiliar = tipoFamiliarDAL.getTipoFamiliar(-1, null);

            TipoFamiliar unTipoFamiliar;
            TipoSocio unTipoSocio;
             if(request.getParameter("var")!=null)
             {
                String idTipoSocio = request.getParameter("var");
                unTipoSocio = tipoSocioDAL.getTipoSocio(Integer.parseInt(idTipoSocio));
                request.getSession().setAttribute("tipoSocio", unTipoSocio);

             }
             else
             {
                 request.getSession().setAttribute("tipoSocio", null);
             }

            if(request.getParameter("var2")!=null)
             {
                String idTipoFamiliar = request.getParameter("var2");
                unTipoFamiliar = tipoFamiliarDAL.getTipoFamiliar(Integer.parseInt(idTipoFamiliar));
                request.getSession().setAttribute("tipoFamiliar", unTipoFamiliar);

             }
             else
             {
                 request.getSession().setAttribute("tipoFamiliar", null);
             }


        }
        finally
        {
            request.getSession().setAttribute("listaTipoSocio", listaTipoSocio);
            request.getSession().setAttribute("listaTipoFamiliar", listaTipoFamiliar);
            getServletContext().getRequestDispatcher("/configuracionSocios.jsp").forward(request, response);
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


