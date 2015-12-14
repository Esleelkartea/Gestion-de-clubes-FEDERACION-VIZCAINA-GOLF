/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socios;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.AccionDAL;
import com.dal.SocioDAL;
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
public class AccionMIBServlet extends HttpServlet {
   
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

        String strIdSocio = "";
        request.getSession().setAttribute("resultadoSocios", null);
        request.getSession().setAttribute("relacionSocio", null);
        request.getSession().setAttribute("resultadoMensaje", null);
        try
        {
           if (request.getParameter("enviar").equals("Alta"))
           {
                strIdSocio = request.getParameter("idSocio");
                String strFechaInicio = request.getParameter("fechaInicio");
                String[] arrFechaInicio = strFechaInicio.split("-");
                strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                String strFechaFin = request.getParameter("fechaFin");
                String[] arrFechaFin = strFechaFin.split("-");
                strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];

                String strAccion = request.getParameter("accion");

                AccionDAL accionDAL = new AccionDAL();
                if (accionDAL.insertarAccion(strAccion, Date.valueOf(strFechaInicio), Date.valueOf(strFechaFin), Integer.parseInt(strIdSocio)))
                {
                    request.getSession().setAttribute("accionSocio", "La acción se ha insertado correctamente");
                }
                else
                {
                    request.getSession().setAttribute("accionSocio", "La acción no se ha podido insertar correctamente");
                }

           }
        }
        catch(Exception ex)
        {
            
        }


        finally
        {
            request.getRequestDispatcher("SociosServlet?var="+strIdSocio).forward(request, response);
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
