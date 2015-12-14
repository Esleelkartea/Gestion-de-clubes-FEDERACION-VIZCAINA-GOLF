/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Negocio.Instalacion;
import com.Negocio.InstalacionHorario;
import com.Negocio.Socio;
import com.Negocio.TipoInstalacion;
import com.Negocio.Usuario;
import com.dal.InstalacionDAL;
import com.dal.InstalacionHorarioDAL;
import com.dal.SocioDAL;
import com.dal.TipoInstalacionDAL;
import java.util.ArrayList;

/**
 *
 * @author Julen
 */
public class IInstalacionesServlet extends HttpServlet {
   
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

        ArrayList<TipoInstalacion> listaTipoInstalaciones = new ArrayList<TipoInstalacion>();
        ArrayList<Instalacion> listaInstalaciones = new ArrayList<Instalacion>();

        try
        {
            TipoInstalacionDAL tipoInstalacionDAL = new TipoInstalacionDAL();
            listaTipoInstalaciones = tipoInstalacionDAL.getTipoInstalacion(-1, null);
            InstalacionDAL instalacionDAL = new InstalacionDAL();
            listaInstalaciones = instalacionDAL.getInstalacion(-1, null, null, -1.0, -1.0);
            Instalacion unaInstalacion;
            InstalacionHorarioDAL instalacionHorarioDAL = new InstalacionHorarioDAL();
            InstalacionHorario unaInstalacionHorario = null;
            request.getSession().setAttribute("unaInstalacionHorario", null);

             if(request.getParameter("var")!=null)
             {
                
               String idInstalacion = request.getParameter("var");
               unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(idInstalacion));
               request.getSession().setAttribute("instalacion", unaInstalacion);

               //Recuperar los horarios de esa instalacion
               ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();
               listaInstalacionHorario = instalacionHorarioDAL.getInstalHorario(unaInstalacion.getIdInstalacion());
               request.getSession().setAttribute("listaInstalacionHorario", listaInstalacionHorario);
               request.getSession().setAttribute("unaInstalacionHorario", null);

               if(request.getParameter("var2") != null)
               {
                  String idInstalacionHorario = request.getParameter("var2");
                  
                  unaInstalacionHorario = instalacionHorarioDAL.getInstalacionHorario(Integer.parseInt(idInstalacionHorario));
                  request.getSession().setAttribute("unaInstalacionHorario", unaInstalacionHorario);
               }
               else
               {
                   request.getSession().setAttribute("unaInstalacionHorario", null);
               }


             }
             else
             {
                 request.getSession().setAttribute("instalacion", null);
                 request.getSession().setAttribute("listaInstalacionHorario", null);
             }
        }
        catch(Exception ex)
        {

        }


        finally
        {
            request.getSession().setAttribute("listaTipoInstalaciones", listaTipoInstalaciones);
            request.getSession().setAttribute("listaInstalaciones", listaInstalaciones);
            getServletContext().getRequestDispatcher("/iInstalaciones.jsp").forward(request, response);
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
