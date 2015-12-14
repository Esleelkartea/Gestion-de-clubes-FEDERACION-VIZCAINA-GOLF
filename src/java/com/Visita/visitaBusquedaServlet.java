/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Visita;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.Negocio.Visita;
import com.dal.SocioDAL;
import com.dal.VisitaDAL;
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
public class visitaBusquedaServlet extends HttpServlet {
   
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

        
        ArrayList<Visita> listaVisitas = new ArrayList<Visita>();
        request.getSession().setAttribute("resultadoVisitas", null);
        
        try
        {
            String strFechaInicio = request.getParameter("fechaInicio");
            request.getSession().setAttribute("strFechaInicio", strFechaInicio);
            if (strFechaInicio.equals(""))
                strFechaInicio = null;
            else
            {
                String fechaInicio[] =  strFechaInicio.split("/");
                strFechaInicio = fechaInicio[2]+ "-" + fechaInicio[1] + "-" + fechaInicio[0];
            }
          
            String strFechaFin = request.getParameter("fechaFin");
            if (strFechaFin.equals(""))
                strFechaFin = null;
            else
            {
                String fechaFin[] =  strFechaFin.split("/");
                strFechaFin = fechaFin[2]+ "-" + fechaFin[1] + "-" + fechaFin[0];
            }
            
            VisitaDAL visitaDAL = new VisitaDAL();
            listaVisitas = visitaDAL.getVisita(-1, strFechaInicio, strFechaFin);
            request.getSession().setAttribute("listaVisitas", listaVisitas);

        }
        catch(Exception ex)
        {
            request.getSession().setAttribute("listaVisitas", null);
        }
        finally
        {
            getServletContext().getRequestDispatcher("/visitas.jsp").forward(request, response);
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
