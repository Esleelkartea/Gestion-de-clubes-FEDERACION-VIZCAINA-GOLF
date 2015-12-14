/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Reserva;

import com.Negocio.Instalacion;
import com.Negocio.InstalacionHorario;
import com.Negocio.Reserva;
import com.Negocio.Socio;
import com.Negocio.TipoPago;
import com.Negocio.TipoReserva;
import com.Negocio.Usuario;
import com.complementos.Buscador;
import com.dal.InstalacionDAL;
import com.dal.InstalacionHorarioDAL;
import com.dal.ReservaDAL;
import com.dal.SocioDAL;
import com.dal.TipoPagoDAL;
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
public class reservaAltaMIBServlet extends HttpServlet {
   
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

        SocioDAL socioDAL = new SocioDAL();
        ReservaDAL reservaDAL = new ReservaDAL();
        InstalacionDAL instalacionDAL = new InstalacionDAL();
        InstalacionHorarioDAL instalacionHorarioDAL = new InstalacionHorarioDAL();
        Socio unSocio = null;

        request.getSession().setAttribute("resultadoReserva", null);
        request.getSession().setAttribute("resultadoReservaSocio", null);
        
        try
        {

          if (request.getParameter("enviar").equals("Buscar"))
          {              
              ArrayList<Reserva> listaReservaInstalacion = new ArrayList<Reserva>();
              ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();
              String strInstalacion = request.getParameter("instalacion");
              String strFecha = request.getParameter("fecha");
              String[] arrFecha = strFecha.split("-");
              strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
              request.getSession().setAttribute("fecha", strFecha);

              Instalacion unaInstalacion = null;
              unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(strInstalacion));

              listaReservaInstalacion = reservaDAL.getReservasDia(Integer.parseInt(strInstalacion), Date.valueOf(strFecha), Date.valueOf(strFecha));
              request.getSession().setAttribute("listaReservaInstalacion", listaReservaInstalacion);

              listaInstalacionHorario = instalacionHorarioDAL.getInstalacionHorarios(null, null, Integer.parseInt(strInstalacion));
              request.getSession().setAttribute("listaInstalacionHorario", listaInstalacionHorario);

              request.getSession().setAttribute("instalacion", strInstalacion);
              request.getSession().setAttribute("nombreInstalacion", unaInstalacion.getNombre());

           }
           if (request.getParameter("enviar").equals("Alta"))
           {

                String strInstalacion = request.getParameter("instalacion");
                String strFecha = request.getParameter("fecha");
                String[] arrFecha = strFecha.split("-");
                strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

           String strNombre = request.getParameter("nombre");
           String strApellidos = request.getParameter("apellidos");
           unSocio = socioDAL.getSocio(strNombre, strApellidos);

            if (unSocio == null)
                request.getSession().setAttribute("resultadoReservaSocio", false);
            else
            {
                request.getSession().setAttribute("resultadoReservaSocio", true);
             
                String strEstadoReserva = request.getParameter("estadoReserva");

                String strFormaPago = request.getParameter("formaPago");
               
                String strHoraInicio = request.getParameter("horaInicio");
                //String strHoraFin = request.getParameter("horaFin");

                //Validar el alta de la reserva
                ArrayList<Reserva> listaReservaInstalacion = new ArrayList<Reserva>();
                ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();

                listaInstalacionHorario = (ArrayList<InstalacionHorario>)request.getAttribute("listaInstalacionHorario");
                listaReservaInstalacion = (ArrayList<Reserva>)request.getAttribute("listaReservaInstalacion");

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
                        if(reservaDAL.insertarReserva(Integer.parseInt(strInstalacion), Date.valueOf(strFecha), Time.valueOf(strHoraInicio), Time.valueOf(strHoraFin), unSocio.getIdSocio(), importe, Integer.parseInt(strEstadoReserva), Integer.parseInt(strFormaPago)))
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
            }
          
             if (request.getParameter("enviar").equals("reservaSimple"))
             {
                TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
                String strInstalacionBusqueda = (String)request.getSession().getAttribute("instalacion");
                String hora = request.getParameter("varHora");
                String arrHora[] = hora.split("-");
                String dia = request.getParameter("varDia");




                TipoReserva tipoReserva;
                tipoReserva = tipoReservaDAL.getTipoReservaPendiente();
                //String strFormaPago = request.getParameter("formaPago");
                
                Instalacion unaInstalacion = null;
                unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(strInstalacionBusqueda));

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
                
                request.getSession().setAttribute("varDia", dia);
                //request.getSession().setAttribute("varHora", hora);
                request.getSession().setAttribute("horaInicio", hora);
                request.getSession().setAttribute("horaFin", horaFin);
                request.getSession().setAttribute("unaInstalacion", unaInstalacion);

                TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
                ArrayList<TipoPago> listaTipoPago = new ArrayList<TipoPago>();
                listaTipoPago = tipoPagoDAL.getTipoPago(-1, null);
                request.getSession().setAttribute("listaTipoPago", listaTipoPago);

                
                ArrayList<TipoReserva> listaTipoReserva = new ArrayList<TipoReserva>();
                listaTipoReserva = tipoReservaDAL.getTipoReserva(-1, null, -1);
                request.getSession().setAttribute("listaTipoReserva", listaTipoReserva);

             }

        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            request.getRequestDispatcher("reservaAlta4.jsp").forward(request, response);
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
