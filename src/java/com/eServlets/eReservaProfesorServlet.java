/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eServlets;

import com.Negocio.TipoInstalacion;
import com.Negocio.TipoPago;
import com.Negocio.TipoReserva;
import com.Negocio.Usuario;
import com.dal.TipoInstalacionDAL;
import com.dal.TipoPagoDAL;
import com.dal.TipoReservaDAL;
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
public class eReservaProfesorServlet extends HttpServlet {
   
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
        Usuario unUsuario = (Usuario)request.getSession().getAttribute("usuario");
        if(request.getSession().getAttribute("usuario") == null)
        {
             request.getRequestDispatcher("/index.jsp").forward(request, response);
             return;
        }

        try
        {
            
            TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
            ArrayList<TipoPago> listaTipoPago = new ArrayList<TipoPago>();
            listaTipoPago = tipoPagoDAL.getTipoPago(-1, null);
            request.getSession().setAttribute("listaTipoPago", listaTipoPago);

            TipoInstalacionDAL tipoInstalacionDAL = new TipoInstalacionDAL();
            ArrayList<TipoInstalacion> listaTipoInstalacion = new ArrayList<TipoInstalacion>();
            listaTipoInstalacion = tipoInstalacionDAL.getTipoInstalacion(-1, null);
            request.getSession().setAttribute("listaTipoInstalacion", listaTipoInstalacion);

            request.getSession().setAttribute("listaProfesores", null);
        }
        finally
        {
            getServletContext().getRequestDispatcher("/eReservaProfesor.jsp").forward(request, response);
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
