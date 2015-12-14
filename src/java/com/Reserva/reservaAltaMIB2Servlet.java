/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Reserva;

import com.Negocio.Instalacion;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.InstalacionDAL;
import com.dal.ReservaDAL;
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
public class reservaAltaMIB2Servlet extends HttpServlet {
   
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

        try
        {
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            Socio unSocio = null;
            SocioDAL socioDAL = new SocioDAL();
            unSocio = socioDAL.getSocio(nombre, apellidos);


            if (unSocio == null)
            {
                request.getSession().setAttribute("resultadoSocio", false);
                request.getRequestDispatcher("reservaAlta4.jsp").forward(request, response);
                out.close();
            }
            else
            {
                request.getSession().setAttribute("resultadoSocio", null);
                String horaInicio = request.getParameter("horaInicio");
                String horaFin = request.getParameter("horaFin");
                String fecha = request.getParameter("fecha");
                String arrFecha[]  =fecha.split("-");
                fecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                String estado = request.getParameter("estadoReserva");
                String formaPago = request.getParameter("formaPago");

                String idInstalacion = request.getParameter("instalacion");
                InstalacionDAL instalacionDAL = new InstalacionDAL();
                Instalacion unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(idInstalacion));

                int edad = unSocio.calcularEdad(unSocio.getFechaNac());
                double importe = unaInstalacion.calcularImporte(edad);

                ReservaDAL reservaDAL = new ReservaDAL();
                if(reservaDAL.insertarReserva(Integer.parseInt(idInstalacion), Date.valueOf(fecha), Time.valueOf(horaInicio), Time.valueOf(horaFin), unSocio.getIdSocio(), importe,Integer.parseInt(estado), Integer.parseInt(formaPago)))
                {
                    request.getSession().setAttribute("resultadoReserva", true);
                }
                else
                {
                    request.getSession().setAttribute("resultadoReserva", false);
                }

                
            }
        }

        catch(Exception ex)
        {
            request.getSession().setAttribute("resultadoReserva", false);
        }
        finally
        {
            request.getRequestDispatcher("reservaCompletada.jsp").forward(request, response);
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
