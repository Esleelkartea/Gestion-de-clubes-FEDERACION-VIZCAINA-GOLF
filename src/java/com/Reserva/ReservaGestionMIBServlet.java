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
import com.complementos.Buscador;
import com.dal.InstalacionDAL;
import com.dal.InstalacionHorarioDAL;
import com.dal.ReservaDAL;
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
public class ReservaGestionMIBServlet extends HttpServlet {
   
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
        InstalacionHorarioDAL instalacionHorarioDAL = new InstalacionHorarioDAL();
        
        try
        {
           if (request.getParameter("enviar").equals("Modificar"))
           {
                String idReserva = request.getParameter("idReserva");
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
                    Reserva unaReserva = new Reserva();
                    Instalacion unaInstalacion = null;

                    int edad = unSocio.calcularEdad(unSocio.getFechaNac());

                    String strInstalacion  = request.getParameter("instalacion");

                    InstalacionDAL instalacionDAL = new InstalacionDAL();
                    unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(strInstalacion));
                    double importe = unaInstalacion.calcularImporte(edad);

                    String strEstado = request.getParameter("estadoReserva");
                    String strFormaPago = request.getParameter("formaPago");

                    String strFecha = request.getParameter("fecha");
                    String[] arrFecha = strFecha.split("-");
                    strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                    String strHoraInicio = request.getParameter("horaInicio");
                    

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
                        listaReservaInstalacion = reservaDAL.getReservasDia2(Integer.parseInt(strInstalacion), Date.valueOf(strFecha), Date.valueOf(strFecha),Integer.parseInt(idReserva));
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
                        if (reservaDAL.modificarReserva(Integer.parseInt(idReserva), Integer.parseInt(strInstalacion), Date.valueOf(strFecha), Time.valueOf(strHoraInicio), Time.valueOf(strHoraFin), unSocio.getIdSocio(), importe, Integer.parseInt(strEstado), Integer.parseInt(strFormaPago)))
                        {
                            request.getSession().setAttribute("resultadoMIBReserva", "La modificacion de la reserva se ha realizado correctamente");
                        }
                        else
                        {
                            request.getSession().setAttribute("resultadoMIBReserva", "La modificacion de la reserva no se ha podido realizar");
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
                String idReserva = request.getParameter("idReserva");
                
                if (reservaDAL.eliminarReserva(Integer.parseInt(idReserva)))
                {
                    request.getSession().setAttribute("resultadoMIBReserva", "La eliminacion de la reserva se ha realizado correctamente");
                }
                else
                {
                    request.getSession().setAttribute("resultadoMIBReserva", "No se ha podido realizar la eliminacion de la reserva");
                }

           }

           request.getRequestDispatcher("reservaGestionServlet").forward(request, response);
           return;



        }
        catch(Exception ex)
        {
            request.getRequestDispatcher("reservaGestionServlet").forward(request, response);
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
