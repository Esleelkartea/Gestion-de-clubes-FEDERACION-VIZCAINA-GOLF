/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.confServlets;

import com.Negocio.Socio;
import com.Negocio.TipoInvitacion;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import com.dal.TipoInvitacionDAL;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class confInvitMIBServlet extends HttpServlet {
   
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

        TipoInvitacionDAL tipoInvitacionDAL = new TipoInvitacionDAL();


        try
        {
        if (request.getParameter("enviar").equals("Alta") || request.getParameter("enviar").equals("Modificacion"))
        {
            String strMaxVisitas = request.getParameter("antelacion");
            String strImporte = request.getParameter("importeCuota");

            String strEdad = request.getParameter("edad");
            String strDias = request.getParameter("dias");

            TipoInvitacion tipoInvitacion = (TipoInvitacion)request.getSession().getAttribute("tipoInvitacion");
            double adulLab = tipoInvitacion.getAdulLaborable();
            double adulFest = tipoInvitacion.getAdulFestivo();
            double menLab = tipoInvitacion.getMenLaborable();
            double menFest = tipoInvitacion.getMenFestivo();

            int intMaxVisitas = tipoInvitacion.getVisitMax();
            if (!strMaxVisitas.equals(""))
            {
                if (intMaxVisitas != Integer.parseInt(strMaxVisitas))
                {
                    intMaxVisitas = Integer.parseInt(strMaxVisitas);
                }
            }
            if (!strImporte.equals(""))
            {
                if(strEdad.equals("Adulto"))
                {
                    if (strDias.equals("Laborables"))
                         adulLab = Double.parseDouble(strImporte);
                    else
                         adulFest = Double.parseDouble(strImporte);
                }
            else
            {
                if (strDias.equals("Laborables"))
                     menLab = Double.parseDouble(strImporte);
                else
                     menFest = Double.parseDouble(strImporte);
            }
            }
            tipoInvitacionDAL.modificarTipoInvitacion(1, adulLab, adulFest, menLab, menFest, intMaxVisitas);

        }
        if (request.getParameter("enviar").equals("Baja"))
        {

            String strMaxVisitas = request.getParameter("antelacion");
            String strImporte = request.getParameter("importeCuota");

            String strEdad = request.getParameter("edad");
            String strDias = request.getParameter("dias");

            TipoInvitacion tipoInvitacion = (TipoInvitacion)request.getSession().getAttribute("tipoInvitacion");
            double adulLab = tipoInvitacion.getAdulLaborable();
            double adulFest = tipoInvitacion.getAdulFestivo();
            double menLab = tipoInvitacion.getMenLaborable();
            double menFest = tipoInvitacion.getMenFestivo();

            int intMaxVisitas = tipoInvitacion.getVisitMax();
            if (!strMaxVisitas.equals(""))
            {
                if (intMaxVisitas != Integer.parseInt(strMaxVisitas))
                {
                    intMaxVisitas = Integer.parseInt(strMaxVisitas);
                }
            }


            if (strImporte.equals(""))
            {
                if(strEdad.equals("Adulto"))
                {
                    if (strDias.equals("Laborables"))
                         adulLab = 0.0;
                    else
                         adulFest = 0.0;
                }
            else
            {
                if (strDias.equals("Laborables"))
                     menLab = 0.0;
                else
                     menFest = 0.0;
            }
            }
            tipoInvitacionDAL.modificarTipoInvitacion(1, adulLab, adulFest, menLab, menFest, intMaxVisitas);

        }

        }

        catch(Exception ex)
        {
            
        }
        
        finally
        {
            request.getRequestDispatcher("confInvitacionServlet").forward(request, response);
            return;
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
