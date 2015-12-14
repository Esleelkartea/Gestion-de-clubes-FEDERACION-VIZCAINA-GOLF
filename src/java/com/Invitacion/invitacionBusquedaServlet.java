/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Invitacion;

import com.Negocio.Invitacion;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.InvitacionDAL;
import com.dal.SocioDAL;
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
public class invitacionBusquedaServlet extends HttpServlet {
   
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


        request.getSession().setAttribute("resultadoSocioInvitacion", null);
        request.getSession().setAttribute("resultadoInvitacion", null);

        ArrayList<Invitacion> listaInvitaciones = new ArrayList<Invitacion>();

        try
        {

            
            String strNombre = request.getParameter("nombre");
            if (strNombre.equals(""))
                strNombre = null;

            String strApellidos = request.getParameter("apellidos");
            if (strApellidos.equals("")) 
                strApellidos = null;

            String strInvitado = request.getParameter("invitado");
            if (strInvitado.equals(""))
                strInvitado = null;

            String strFechaInicio = request.getParameter("fechaInicio");
            if (strFechaInicio.equals(""))
                strFechaInicio = null;
            else
            {
                String fechaInicio[] =  strFechaInicio.split("/");
                strFechaInicio = fechaInicio[2]+ "-" + fechaInicio[1] + "-" + fechaInicio[0];
            }

            String strFechaFin = request.getParameter("fechaFin");
            if (strFechaFin.equals(""))
                strFechaFin = null;
            else
            {
                String fechaFin[] =  strFechaFin.split("/");
                strFechaFin = fechaFin[2]+ "-" + fechaFin[1] + "-" + fechaFin[0];
            }

            InvitacionDAL invitacionDAL = new InvitacionDAL();
            listaInvitaciones = invitacionDAL.getInvitacion(-1, strFechaInicio, strFechaFin, strInvitado, strNombre, strApellidos);
            request.getSession().setAttribute("listaInvitaciones", listaInvitaciones);
            

        }
        catch(Exception ex)
        {
            request.getSession().setAttribute("listaInvitaciones", null);
        }
        finally
        {
            
            getServletContext().getRequestDispatcher("/invitacion.jsp").forward(request, response);
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
