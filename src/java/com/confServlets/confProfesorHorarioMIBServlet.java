/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.confServlets;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.ProfesorHorarioDAL;
import com.dal.SocioDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class confProfesorHorarioMIBServlet extends HttpServlet {
   
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

        String strIdProfesor = request.getParameter("idProfesor");
        try
        {
           
           ProfesorHorarioDAL profesorHorarioDAL = new ProfesorHorarioDAL();

            //Insercion
            if (request.getParameter("enviar").equals("Alta"))
            {

                String strFechaInicio = request.getParameter("fechaInicio");
                if (strFechaInicio.equals(""))
                    strFechaInicio = null;
                else
                {
                    String[] arrFechaInicio = strFechaInicio.split("-");
                    strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];
                }

                String strFechaFin = request.getParameter("fechaFin");
                if (strFechaFin.equals(""))
                    strFechaFin = null;
                else
                {
                    String[] arrFechaFin = strFechaFin.split("-");
                    strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];
                }


                String strHoraInicio = request.getParameter("horaInicio");
                if (strHoraInicio.equals("")) strHoraInicio = null;
                String strHoraFin = request.getParameter("horaFin");
                if (strHoraFin.equals("")) strHoraFin = null;

              
                String strObservaciones = request.getParameter("observaciones");
                if (strObservaciones.equals("")) strObservaciones = null;

                profesorHorarioDAL.insertarProfesorHorario(Date.valueOf(strFechaInicio), Date.valueOf(strFechaFin), Time.valueOf(strHoraInicio), Time.valueOf(strHoraFin), strObservaciones, Integer.parseInt(strIdProfesor));
                String destino = "confProfesorServlet?var=" + strIdProfesor;
                request.getRequestDispatcher(destino).forward(request, response);
                return;
            }

            //Modificacion del tipo de instalacion
            if (request.getParameter("enviar").equals("Modificar"))
            {
                String strFechaInicio = request.getParameter("fechaInicio");
                if (strFechaInicio.equals(""))
                    strFechaInicio = null;
                else
                {
                    String[] arrFechaInicio = strFechaInicio.split("-");
                    strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];
                }

                String strFechaFin = request.getParameter("fechaFin");
                if (strFechaFin.equals(""))
                    strFechaFin = null;
                else
                {
                    String[] arrFechaFin = strFechaFin.split("-");
                    strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];
                }


                String strHoraInicio = request.getParameter("horaInicio");
                if (strHoraInicio.equals("")) strHoraInicio = null;
                String strHoraFin = request.getParameter("horaFin");
                if (strHoraFin.equals("")) strHoraFin = null;

            
                String strObservaciones = request.getParameter("observaciones");
                if (strObservaciones.equals("")) strObservaciones = null;
               
                String strIdProfesorHorario = request.getParameter("profesorHorarioId");

                profesorHorarioDAL.modificarProfesorHorario(Integer.parseInt(strIdProfesorHorario), Date.valueOf(strFechaInicio), Date.valueOf(strFechaFin), Time.valueOf(strHoraInicio), Time.valueOf(strHoraFin), strObservaciones, Integer.parseInt(strIdProfesor));
               
                String destino = "confProfesorServlet?var=" + strIdProfesor;
                request.getRequestDispatcher(destino).forward(request, response);
                return;
            }

            //Eliminar tipo instalacion
            if (request.getParameter("enviar").equals("Baja"))
            {
               
               String strIdProfesorHorario = request.getParameter("profesorHorarioId");
               profesorHorarioDAL.eliminarProfesorHorario(Integer.parseInt(strIdProfesorHorario));              
               
               String destino = "confProfesorServlet?var=" + strIdProfesor;
               request.getRequestDispatcher(destino).forward(request, response);
               return;
            }
        }
        catch(Exception ex)
        {
           String destino = "confProfesorServlet?var=" + strIdProfesor;
           request.getRequestDispatcher(destino).forward(request, response);
           return;
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
