/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eServlets;

import com.Negocio.Instalacion;
import com.Negocio.InstalacionHorario;
import com.Negocio.Reserva;
import com.Negocio.Socio;
import com.Negocio.TipoReserva;
import com.Negocio.Usuario;
import com.complementos.Buscador;
import com.dal.InstalacionDAL;
import com.dal.InstalacionHorarioDAL;
import com.dal.ReservaDAL;
import com.dal.TipoReservaDAL;
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
public class eReservaAltaMIBServlet extends HttpServlet {

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

        Socio unSocio = (Socio)request.getSession().getAttribute("socioExtranet");
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

               request.getRequestDispatcher("eReservaAlta3.jsp").forward(request, response);

           }
           if (request.getParameter("enviar").equals("Alta"))
           {
                
                TipoReserva tipoReserva;
                tipoReserva = tipoReservaDAL.getTipoReservaPendiente();

                //String strFormaPago = request.getParameter("formaPago");

                String strFecha = request.getParameter("fechaReserva");
                String[] arrFecha = strFecha.split("-");
                strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                String strHoraInicio = request.getParameter("horaInicio");
                //String strHoraFin = request.getParameter("horaFin");


                 //Validar el alta de la reserva
                ArrayList<Reserva> listaReservaInstalacion = new ArrayList<Reserva>();
                ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();

                listaInstalacionHorario = (ArrayList<InstalacionHorario>)request.getAttribute("listaInstalacionHorario");
                listaReservaInstalacion = (ArrayList<Reserva>)request.getAttribute("listaReservaInstalacion");
                InstalacionHorarioDAL instalacionHorarioDAL = new InstalacionHorarioDAL();

                if(listaInstalacionHorario == null)
                {
                    listaInstalacionHorario = instalacionHorarioDAL.getInstalacionHorarios(null, null, Integer.parseInt(strInstalacion));
                }
                if (listaReservaInstalacion == null)
                {
                    listaReservaInstalacion = reservaDAL.getReservasDia(Integer.parseInt(strInstalacion), Date.valueOf(strFecha), Date.valueOf(strFecha));
                }

                Buscador buscador = new Buscador();
                boolean libre = true;
                String arrHoraInicio[] = strHoraInicio.split(":");
                int horaInicio = Integer.parseInt(arrHoraInicio[0]);

                String strGolf = request.getParameter("golf");
                boolean golf = Boolean.parseBoolean(strGolf);

                String strHoraFin;
                if(!golf)
                {
                    strHoraFin = request.getParameter("horaFin");
                    String arrHoraFin[] = strHoraFin.split(":");
                    int horaFin = Integer.parseInt(arrHoraFin[0]);
                    int i = horaInicio;

                    while(libre && i<=horaFin)
                    {
                        libre = !buscador.buscarFechasReserva(listaReservaInstalacion, strFecha, String.valueOf(i) + ":00:00");
                        if(libre)
                        {
                            libre = !buscador.buscarFechasInstalacion(listaInstalacionHorario, strFecha, String.valueOf(i) + ":00:00");
                        }
                        i++;
                    }
                }
                else
                {
                    String min = arrHoraInicio[1];
                    strHoraFin = strHoraInicio;
                    libre = !buscador.buscarFechasInstalacion(listaInstalacionHorario, strFecha, horaInicio + ":00:00");
                    if(libre)
                    {
                        libre = !buscador.buscarFechasReserva2(listaReservaInstalacion, strFecha, horaInicio + ":" + min +":00");
                    }
                }

                  //Fin validar el alta de la reserva

               if(libre)
               {
                    Date fechaNac = unSocio.getFechaNac();
                    int edad = unSocio.calcularEdad(fechaNac);
                    Instalacion unaInstalacion = null;
                    unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(strInstalacion));
                    double importe = unaInstalacion.calcularImporte(edad);

                    if (strFecha.equals("") || strHoraInicio.equals("") || strHoraFin.equals(""))
                    {
                        request.getSession().setAttribute("resultadoReserva", false);
                    }
                    else
                    {
                        if(reservaDAL.insertarReserva(Integer.parseInt(strInstalacion), Date.valueOf(strFecha), Time.valueOf(strHoraInicio), Time.valueOf(strHoraFin), unSocio.getIdSocio(),importe,tipoReserva.getIdTipoReserva(), unSocio.getTipoPago().getIdTipoPago()))
                        {
                            request.getSession().setAttribute("resultadoReserva", true);
                        }
                        else
                        {
                            request.getSession().setAttribute("resultadoReserva", false);
                        }
                    }
                }
               else
               {
                    request.getSession().setAttribute("resultadoReservaDisponible", false);
               }
            }
             if (request.getParameter("enviar").equals("reservaSimple"))
             {
                String strInstalacionBusqueda = (String)request.getSession().getAttribute("instalacion");
                String hora = request.getParameter("varHora");
                String arrHora[] = hora.split("-");
                String dia = request.getParameter("varDia");

                TipoReserva tipoReserva;
                tipoReserva = tipoReservaDAL.getTipoReservaPendiente();
                //String strFormaPago = request.getParameter("formaPago");
                Date fechaNac = unSocio.getFechaNac();
                int edad = unSocio.calcularEdad(fechaNac);
                Instalacion unaInstalacion = null;
                unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(strInstalacionBusqueda));
                double importe = unaInstalacion.calcularImporte(edad);

                
                String horaFin = "";

                if(unaInstalacion.getNombre().contains("golf") || unaInstalacion.getNombre().contains("Golf") || unaInstalacion.getNombre().contains("GOLF"))
                {
                    horaFin = hora;
                    String arrHoraFin[] = horaFin.split(":");
                    int iHoraFin = Integer.parseInt(arrHoraFin[0]);
                    int iHoraMinFin = Integer.parseInt(arrHoraFin[1]);
                    switch(iHoraMinFin)
                    {
                        case 20:
                            iHoraMinFin += 20;
                            break;
                        case 40:
                            iHoraMinFin = 00;
                            iHoraFin++;
                            break;
                        case 00:
                            iHoraMinFin += 20;
                            break;
                    }
                    horaFin = String.valueOf(iHoraFin) + ":" + String.valueOf(iHoraMinFin) + ":00";
                }
                else
                {
                    horaFin = hora;
                    String arrHoraFin[] = horaFin.split(":");
                    int iHoraFin = Integer.parseInt(arrHoraFin[0]);
                    iHoraFin++;
                    horaFin = String.valueOf(iHoraFin)+":00:00";
                } 

                Time tHoraFin= Time.valueOf(horaFin);

                hora = hora + ":00";
                Time horaInicio = Time.valueOf(hora);

                if(reservaDAL.insertarReserva(Integer.parseInt(strInstalacionBusqueda), Date.valueOf(dia), horaInicio, tHoraFin, unSocio.getIdSocio(),importe,tipoReserva.getIdTipoReserva(), unSocio.getTipoPago().getIdTipoPago()))
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
            request.getRequestDispatcher("eReservaCompletada.jsp").forward(request, response);
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
