/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eServlets;

import com.Negocio.Profesor;
import com.Negocio.ProfesorHorario;
import com.Negocio.ReservaProfesor;
import com.Negocio.Socio;
import com.Negocio.TipoReserva;
import com.Negocio.Usuario;
import com.complementos.Buscador;
import com.dal.ProfesorDAL;
import com.dal.ProfesorHorarioDAL;
import com.dal.ReservaProfesorDAL;
import com.dal.SocioDAL;
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
public class eReservaProfesorMIBServlet extends HttpServlet {
   
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
        ProfesorDAL profesorDAL = new ProfesorDAL();
        ProfesorHorarioDAL profesorHorarioDAL = new ProfesorHorarioDAL();
        ReservaProfesorDAL reservaProfesorDAL = new ReservaProfesorDAL();
        SocioDAL socioDAL = new SocioDAL();

        try
        {
            if (request.getParameter("enviar").equals("Buscar"))
            {
                /*
                String strFecha = request.getParameter("fecha");
                String[] arrFecha = strFecha.split("-");
                strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
                request.getSession().setAttribute("fecha", strFecha);
                String strHoraInicio = request.getParameter("horaInicio");
                request.getSession().setAttribute("horaInicio", strHoraInicio);
                String strHoraFin = request.getParameter("horaFin");
                request.getSession().setAttribute("horaFin", strHoraFin);
                String strIdTipoInstalacion = request.getParameter("tipoInstalacion");
                request.getSession().setAttribute("tipoInstalacion", strIdTipoInstalacion);

                ArrayList<Profesor> listaProfesoresAuxiliar = new ArrayList<Profesor>();
                listaProfesoresAuxiliar = profesorDAL.getProfesor(-1, null, null, null, null,Integer.parseInt(strIdTipoInstalacion));

                ArrayList<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();
                ArrayList<ReservaProfesor> listaReservas = new ArrayList<ReservaProfesor>();
                ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();
                Profesor unProfesor;
                Buscador buscador = new Buscador();

                String arrHoraInicio[] = strHoraInicio.split(":");
                int horaInicio = Integer.parseInt(arrHoraInicio[0]);

                String arrHoraFin[] = strHoraFin.split(":");
                int horaFin = Integer.parseInt(arrHoraFin[0]);
                boolean libre;
                int j;
                for(int i = 0;i<listaProfesoresAuxiliar.size();i++)
                {
                    unProfesor = listaProfesoresAuxiliar.get(i);
                    listaProfesorHorario = profesorHorarioDAL.getunProfesorHorario(unProfesor.getIdProfesor());
                    listaReservas = reservaProfesorDAL.getUnProfesorReserva(unProfesor.getIdProfesor());

                    libre = true;
                    j = horaInicio;

                    while(libre && j<=horaFin)
                    {
                        libre = !buscador.buscarFechasReservaProfesor(listaReservas, strFecha, String.valueOf(j) + ":00:00");

                        if(libre)
                        {
                            libre = !buscador.buscarFechasProfesor(listaProfesorHorario, strFecha, String.valueOf(j) + ":00:00");
                        }
                        j++;
                    }
                    if (libre)
                    {
                        listaProfesores.add(unProfesor);
                    }
                }

                request.getSession().setAttribute("listaProfesores", listaProfesores);
                 
                 */
                
               ArrayList<ReservaProfesor> listaReservaProfesores = new ArrayList<ReservaProfesor>();
               ArrayList<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();

               String strIdProfesor = (String)request.getSession().getAttribute("profesor");


               String strFechaInicio = request.getParameter("fechaInicio");
               String[] arrFecha = strFechaInicio.split("-");
               strFechaInicio = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
               request.getSession().setAttribute("fechaNuevaInicio", strFechaInicio);

               String strFechaFin = request.getParameter("fechaFin");
               String[] arrFechaFin = strFechaFin.split("-");
               strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];
               request.getSession().setAttribute("fechaNuevaFin", strFechaFin);


               listaReservaProfesores = reservaProfesorDAL.busquedaReservasProfesor(-1, Integer.parseInt(strIdProfesor), Date.valueOf(strFechaInicio), Date.valueOf(strFechaFin), -1, -1);
               request.getSession().setAttribute("listaReservaProfesores", listaReservaProfesores);

               listaProfesorHorario = profesorHorarioDAL.getProfesorHorario(-1, null, null, null, null, null, Integer.parseInt(strIdProfesor));
               request.getSession().setAttribute("listaProfesorHorario", listaProfesorHorario);
                
               request.getRequestDispatcher("eReservaProfesor3.jsp").forward(request, response);
               return;
            }

                   
           if (request.getParameter("enviar").equals("Alta"))
           {

                TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
                TipoReserva tipoReserva;
                tipoReserva = tipoReservaDAL.getTipoReservaPendiente();

                String strFecha = request.getParameter("fecha");
                String[] arrFecha = strFecha.split("-");
                strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                String strFormaPago = request.getParameter("formaPago");

                String strHoraInicio = request.getParameter("horaInicio");
                String strHoraFin = request.getParameter("horaFin");

                String strProfesor = request.getParameter("profesor");

                Profesor unProfesor = null;
                unProfesor = profesorDAL.getProfesor(Integer.parseInt(strProfesor));

                double importe = unProfesor.getImporte();
                    
                if(reservaProfesorDAL.insertarReservaProfesor(Integer.parseInt(strProfesor), Date.valueOf(strFecha), Time.valueOf(strHoraInicio), Time.valueOf(strHoraFin), unSocio.getIdSocio(), importe, tipoReserva.getIdTipoReserva(), Integer.parseInt(strFormaPago)))
                {
                    request.getSession().setAttribute("resultadoReservaProfesor", true);
                }
                else
                {
                    request.getSession().setAttribute("resultadoReservaProfesor", false);
                }
            }

            if (request.getParameter("enviar").equals("reservaSimple"))
            {
                String strIdProfesor = (String)request.getSession().getAttribute("profesor");
                String hora = request.getParameter("varHora");
                String dia = request.getParameter("varDia");

                TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
                TipoReserva tipoReserva;
                tipoReserva = tipoReservaDAL.getTipoReservaPendiente();

                Profesor unProfesor = null;
                unProfesor = profesorDAL.getProfesor(Integer.parseInt(strIdProfesor));

                double importe = unProfesor.getImporte();

                hora = hora + ":00";
                Time horaInicio = Time.valueOf(hora);
                
                String horaFin = hora;
                String arrHoraFin[] = horaFin.split(":");
                int iHoraFin = Integer.parseInt(arrHoraFin[0]);
                iHoraFin++;
                horaFin = String.valueOf(iHoraFin)+":00:00";

                Time tHoraFin= Time.valueOf(horaFin);

                if(reservaProfesorDAL.insertarReservaProfesor(Integer.parseInt(strIdProfesor), Date.valueOf(dia), horaInicio, tHoraFin, unSocio.getIdSocio(), importe, tipoReserva.getIdTipoReserva(), unSocio.getTipoPago().getIdTipoPago()))
                {
                    request.getSession().setAttribute("resultadoReservaProfesor", true);
                }
                else
                {
                    request.getSession().setAttribute("resultadoReservaProfesor", false);
                }
                
            }

        }
        catch(Exception ex)
        {
            request.getSession().setAttribute("resultadoReservaProfesor", false);
        }
        finally
        {
            getServletContext().getRequestDispatcher("/eReservaProfesorCompletada.jsp").forward(request, response);
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
