/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iServlets;

import com.Negocio.Socio;
import com.Negocio.TipoInstalacion;
import com.Negocio.Usuario;
import com.dal.InstalacionDAL;
import com.dal.InstalacionHorarioDAL;
import com.dal.SocioDAL;
import com.dal.TipoInstalacionDAL;
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
public class IInstalacionesMIBServlet extends HttpServlet {
   
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


        InstalacionDAL instalacionDAL = new InstalacionDAL();
        TipoInstalacionDAL tipoInstalacionDAL = new TipoInstalacionDAL();
        TipoInstalacion tipoInstalacion = null;
        try
        {
            //Insercion del tipo de instalacion
            if (request.getParameter("enviar").equals("Alta"))
            {
                String strNombreInstalacion = request.getParameter("instalacion");
                String strTarifaAdulto = request.getParameter("tarifaAdulto");
                String strTarifaMenor = request.getParameter("tarifaMenor");
                String strTipoInstalacion = request.getParameter("tipoInstalacion");
                tipoInstalacion = tipoInstalacionDAL.getTipoInstalacion(Integer.parseInt(strTipoInstalacion));

                instalacionDAL.insertarInstalacion(strNombreInstalacion,tipoInstalacion, Double.parseDouble(strTarifaAdulto), Double.parseDouble(strTarifaMenor));             

                request.getRequestDispatcher("IInstalacionesServlet").forward(request, response);
                return;
            }

            //Modificacion del tipo de instalacion
            if (request.getParameter("enviar").equals("Modificar"))
            {
                String strNombreInstalacion = request.getParameter("instalacion");
                String strTarifaAdulto = request.getParameter("tarifaAdulto");
                String strTarifaMenor = request.getParameter("tarifaMenor");
                String strTipoInstalacion = request.getParameter("tipoInstalacion");
                String strIdInstalacion = request.getParameter("instalacionesId");
                tipoInstalacion = tipoInstalacionDAL.getTipoInstalacion(Integer.parseInt(strTipoInstalacion));

                instalacionDAL.modificarInstalacion(Integer.parseInt(strIdInstalacion), strNombreInstalacion, tipoInstalacion, Double.parseDouble(strTarifaAdulto), Double.parseDouble(strTarifaMenor));
               
                request.getRequestDispatcher("IInstalacionesServlet").forward(request, response);
                return;
            }

            //Eliminar tipo instalacion
            if (request.getParameter("enviar").equals("Baja"))
            {

                String strIdInstalacion = request.getParameter("instalacionesId");
                InstalacionHorarioDAL instalacionHorarioDAL = new InstalacionHorarioDAL();
                instalacionHorarioDAL.eliminarInstalacionesHorario(Integer.parseInt(strIdInstalacion));
                
                instalacionDAL.eliminarInstalacion(Integer.parseInt(strIdInstalacion));
                
              
                request.getRequestDispatcher("IInstalacionesServlet").forward(request, response);
                return;
            }
            
        }

        catch(Exception ex)
        {
            request.getRequestDispatcher("IInstalacionesServlet").forward(request, response);
            out.close();
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
