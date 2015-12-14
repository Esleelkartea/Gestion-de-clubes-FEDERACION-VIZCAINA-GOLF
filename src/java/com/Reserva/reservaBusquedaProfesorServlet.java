/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Reserva;

import com.Negocio.ReservaProfesor;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.ReservaProfesorDAL;
import com.dal.SocioDAL;
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
public class reservaBusquedaProfesorServlet extends HttpServlet {
   
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

        SocioDAL socioDAL = new SocioDAL();

        //Comprobacion del login
         if(request.getSession().getAttribute("usuario") == null)
        {
             request.getRequestDispatcher("index.jsp").forward(request, response);
             return;
        }
        else
        {

            Socio unSocio;
            Usuario unUsuario = (Usuario)request.getSession().getAttribute("usuario");
            unSocio = socioDAL.getSocio(unUsuario.getIdUsuario());
            if (unSocio != null)
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        }

        ArrayList<ReservaProfesor> listaReservaProfesor = new ArrayList<ReservaProfesor>();
        ReservaProfesorDAL reservaProfesorDAL = new ReservaProfesorDAL();

        try
        {
            String strNombre = request.getParameter("nombre");
            String strApellidos = request.getParameter("apellidos");

            Socio unSocio = null;
            int idSocio = 0;
            if(!strNombre.equals("") || !strApellidos.equals(""))
            {
                unSocio = socioDAL.getSocio(strNombre, strApellidos);

                if (unSocio == null)
                    idSocio = 0;
                else
                    idSocio = unSocio.getIdSocio();
            }
            else
                idSocio = -1;


            String strEstadoReserva = request.getParameter("estadoReserva");
            int estadoReserva;
            if (strEstadoReserva.equals(""))
                estadoReserva = -1;
            else
                estadoReserva = Integer.parseInt(strEstadoReserva);


            String strFormaPago = request.getParameter("formaPago");
            int formaPago;
            if (strFormaPago.equals(""))
                formaPago = -1;
            else
                formaPago = Integer.parseInt(strFormaPago);


            String strFechaInicio = request.getParameter("fechaInicio");
            Date fechaInicio;
            if (strFechaInicio.equals(""))
                fechaInicio = null;
            else
            {
                String[] arrFechaInicio = strFechaInicio.split("-");
                strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];
                fechaInicio = Date.valueOf(strFechaInicio);
            }

            String strFechaFin = request.getParameter("fechaFin");
            Date fechaFin;
            if (strFechaFin.equals(""))
                fechaFin = null;
            else
            {
                String[] arrFechaFin = strFechaFin.split("-");
                strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];
                fechaFin = Date.valueOf(strFechaFin);
            }

            String strProfesor = request.getParameter("profesor");
            int profesor;
            if (strProfesor.equals(""))
                profesor = -1;
            else
                profesor = Integer.parseInt(strProfesor);

            listaReservaProfesor = reservaProfesorDAL.busquedaReservasProfesor(idSocio, profesor, fechaInicio, fechaFin, estadoReserva, formaPago);
            request.getSession().setAttribute("listaReservaProfesor", listaReservaProfesor);
            request.getSession().setAttribute("unaReservaProfesor", null);

        }
        catch(Exception ex)
        {

        }
        finally
        {
            getServletContext().getRequestDispatcher("/reservaGestionProfesor.jsp").forward(request, response);
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
