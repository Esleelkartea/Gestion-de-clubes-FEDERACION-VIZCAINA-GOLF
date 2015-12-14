/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Reserva;

import com.Negocio.Profesor;
import com.Negocio.ProfesorHorario;
import com.Negocio.ReservaProfesor;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.complementos.Buscador;
import com.dal.ProfesorHorarioDAL;
import com.dal.ReservaProfesorDAL;
import com.dal.SocioDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class reservaGestProfesorMIBServlet extends HttpServlet {
   
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

        ReservaProfesorDAL reservaHorarioDAL = new ReservaProfesorDAL();

        try
        {
           if (request.getParameter("enviar").equals("Modificar"))
           {
               String idReservaProfesor = request.getParameter("idReservaProfesor");
               String strNombre = request.getParameter("nombre");
               String strApellidos = request.getParameter("apellidos");

               SocioDAL socioDAL = new SocioDAL();
               Socio unSocio = null;
               unSocio = socioDAL.getSocio(strNombre, strApellidos);
               if (unSocio == null)
               {
                   request.getSession().setAttribute("resultadoMIBReservaSocio", "No existe ese socio");
               }
               else
               {
                   request.getSession().setAttribute("resultadoMIBReservaSocio", null);

                   String strProfesor = request.getParameter("profesor");
                   String strEstado = request.getParameter("estadoReserva");
                   String strFormaPago = request.getParameter("formaPago");

                   String strFecha = request.getParameter("fecha");
                   String[] arrFecha = strFecha.split("-");
                   strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                   String strHoraInicio = request.getParameter("horaInicio");
                   String strHoraFin = request.getParameter("horaFin");

                   //Validar el alta de la reserva
                    ArrayList<ReservaProfesor> listaReservaProfesor = new ArrayList<ReservaProfesor>();
                    ArrayList<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();
                    
                    ProfesorHorarioDAL profesorHorarioDAL = new ProfesorHorarioDAL();
                    ReservaProfesorDAL reservaProfesorDAL = new ReservaProfesorDAL();

                    if(listaProfesorHorario.size() == 0)
                    {
                        listaProfesorHorario = profesorHorarioDAL.getunProfesorHorario(Integer.parseInt(strProfesor));
                    }
                    if(listaReservaProfesor.size() == 0)
                    {
                        listaReservaProfesor = reservaProfesorDAL.getUnProfesorReserva(Integer.parseInt(strProfesor));
                    }

                    Buscador unBuscador = new Buscador();

                    boolean libre = true;
                    String arrHoraInicio[] = strHoraInicio.split(":");
                    int horaInicio = Integer.parseInt(arrHoraInicio[0]);

                    String arrHoraFin[] = strHoraFin.split(":");
                    int horaFin = Integer.parseInt(arrHoraFin[0]);
                    int i = horaInicio;

                    while(libre && i<=horaFin)
                    {
                        libre = !unBuscador.buscarFechasReservaProfesor(listaReservaProfesor, strFecha, String.valueOf(i) + ":00:00");
                        if(libre)
                        {
                            libre = !unBuscador.buscarFechasProfesor(listaProfesorHorario, strFecha, String.valueOf(i) + ":00:00");
                        }
                        i++;
                    }

                    //Fin validar el alta de la reserva
                if(libre)
                {

                    if (strHoraInicio.equals("") || strHoraFin.equals(""))
                    {
                        request.getSession().setAttribute("resultadoMIBReserva", "La modificacion de la reserva no se ha podido realizar");
                    }
                    else
                    {
                        if(reservaProfesorDAL.modificarReservaProfesor(Integer.parseInt(idReservaProfesor), Integer.parseInt(strProfesor), Date.valueOf(strFecha), Time.valueOf(strHoraInicio), Time.valueOf(strHoraFin), unSocio.getIdSocio(), 1, Integer.parseInt(strEstado), Integer.parseInt(strFormaPago)))
                        {
                            request.getSession().setAttribute("resultadoMIBReservaProfesor", "La modificacion de la reserva se ha realizado correctamente");
                        }
                        else
                        {
                            request.getSession().setAttribute("resultadoMIBReservaProfesor", "La modificacion de la reserva no se ha podido realizar");
                        }
                    }
                 }
                else
                {
                        request.getSession().setAttribute("resultadoReservaDisponible", false);
                }

               }

           }

           if (request.getParameter("enviar").equals("Baja"))
           {
                String idReservaProfesor = request.getParameter("idReservaProfesor");

                if(reservaHorarioDAL.eliminarReservaProfesor(Integer.parseInt(idReservaProfesor)))
                {
                    request.getSession().setAttribute("resultadoMIBReservaProfesor", "La eliminacion de la reserva se ha realizado correctamente");
                }
                else
                {
                    request.getSession().setAttribute("resultadoMIBReservaProfesor", "No se ha podido realizar la eliminacion de la reserva");
                }
           }
           request.getRequestDispatcher("reservaGestionProfesorServlet").forward(request, response);
           return;
        }
        catch(Exception ex)
        {
            request.getRequestDispatcher("reservaGestionProfesorServlet").forward(request, response);
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
