/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Reserva;

import com.Negocio.Instalacion;
import com.Negocio.Reserva;
import com.Negocio.Socio;
import com.Negocio.TipoPago;
import com.Negocio.TipoReserva;
import com.Negocio.Usuario;
import com.dal.InstalacionDAL;
import com.dal.ReservaDAL;
import com.dal.SocioDAL;
import com.dal.TipoPagoDAL;
import com.dal.TipoReservaDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class reservaGestionServlet extends HttpServlet {

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
            InstalacionDAL instalacionDAL = new InstalacionDAL();
            ArrayList<Instalacion> listaInstalaciones = new ArrayList<Instalacion>();
            listaInstalaciones = instalacionDAL.getInstalacion(-1, null, null, -1.0, -1.0);
            request.getSession().setAttribute("listaInstalaciones", listaInstalaciones);

            TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
            ArrayList<TipoReserva> listaTipoReserva = new ArrayList<TipoReserva>();
            listaTipoReserva = tipoReservaDAL.getTipoReserva(-1, null, -1);
            request.getSession().setAttribute("listaTipoReserva", listaTipoReserva);

            TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
            ArrayList<TipoPago> listaTipoPago = new ArrayList<TipoPago>();
            listaTipoPago = tipoPagoDAL.getTipoPago(-1, null);
            request.getSession().setAttribute("listaTipoPago", listaTipoPago);
            


            Reserva unaReserva = null;
            ReservaDAL reservaDAL = new ReservaDAL();
            if(request.getParameter("var")!=null)
            {
                String idReserva = request.getParameter("var");
                unaReserva = reservaDAL.getReservaId(Integer.parseInt(idReserva));
                request.getSession().setAttribute("unaReserva", unaReserva);
            }
            else
            {
                request.getSession().setAttribute("unaReserva", null);
                request.getSession().setAttribute("listaReservas", null);
            }
        }
        catch(Exception ex)
        {

        }
        finally
        {
            getServletContext().getRequestDispatcher("/reservaGestion.jsp").forward(request, response);
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