/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Reserva;

import com.Negocio.Instalacion;
import com.Negocio.InstalacionHorario;
import com.Negocio.Reserva;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.InstalacionDAL;
import com.dal.InstalacionHorarioDAL;
import com.dal.ReservaDAL;
import com.dal.SocioDAL;
import com.dal.TipoReservaDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class reservaCalendario3Servlet extends HttpServlet {
   
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

        ReservaDAL reservaDAL = new ReservaDAL();
        InstalacionDAL instalacionDAL = new InstalacionDAL();

        request.getSession().setAttribute("resultadoReserva", null);

        TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
        try
        {
           String strInstalacion = (String)request.getSession().getAttribute("instalacion");
           if (request.getParameter("enviar").equals("Buscar"))
           {
                InstalacionHorarioDAL instalacionHorarioDAL = new InstalacionHorarioDAL();
                ArrayList<Reserva> listaReservaInstalacion = new ArrayList<Reserva>();
                ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();

                String strFechaInicio = request.getParameter("fechaInicio");
                String[] arrFecha = strFechaInicio.split("-");
                strFechaInicio = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
                request.getSession().setAttribute("fechaNuevaInicio", strFechaInicio);

                String strFechaFin = request.getParameter("fechaFin");
                String[] arrFechaFin = strFechaFin.split("-");
                strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];
                request.getSession().setAttribute("fechaNuevaFin", strFechaFin);



                Instalacion unaInstalacion = null;
                unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(strInstalacion));

                listaReservaInstalacion = reservaDAL.getReservasDia(Integer.parseInt(strInstalacion), Date.valueOf(strFechaInicio), Date.valueOf(strFechaFin));
                request.getSession().setAttribute("listaReservaInstalacion", listaReservaInstalacion);

                listaInstalacionHorario = instalacionHorarioDAL.getInstalacionHorarios(null, null, Integer.parseInt(strInstalacion));
                request.getSession().setAttribute("listaInstalacionHorario", listaInstalacionHorario);

               request.getRequestDispatcher("reservaCalendario3.jsp").forward(request, response);
               return;
           }
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
