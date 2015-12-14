/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.convServlets;

import com.Negocio.Socio;
import com.Negocio.UsuariosConvenioPresentacion;
import com.Negocio.TipoConvenio;
import com.Negocio.Usuario;
import com.Negocio.UsuariosConvenio;
import com.dal.SocioDAL;
import com.dal.TipoConvenioDAL;
import com.dal.UsuarioConvenioDAL;
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
public class convUsuConvenioServlet extends HttpServlet {
   
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

        ArrayList<TipoConvenio> listaTipoConvenio = new ArrayList<TipoConvenio>();
        ArrayList<UsuariosConvenio> listaUsuarioConvenio = new ArrayList<UsuariosConvenio>();
      
        UsuariosConvenio unUsuarioConvenio;

        
        try
        {
            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio;
            UsuarioConvenioDAL usuarioConvenioDAL = new UsuarioConvenioDAL();
            TipoConvenioDAL tipoConvenioDAL = new TipoConvenioDAL();
            listaUsuarioConvenio = usuarioConvenioDAL.getUsuariosConvenio(-1, -1, -1);
            listaTipoConvenio = tipoConvenioDAL.getTipoConvenio(-1, null);

            request.getSession().setAttribute("listaUsuarioConvenio", listaUsuarioConvenio);


            if(request.getParameter("var")!=null)
             {
                unUsuarioConvenio = null;

                String idUsuarioConvenio = request.getParameter("var");
                unUsuarioConvenio = usuarioConvenioDAL.getUsuarioConvenio(Integer.parseInt(idUsuarioConvenio));
                                
                request.getSession().setAttribute("unUsuarioConvenio", unUsuarioConvenio);
             }
             else
             {
                 request.getSession().setAttribute("unUsuarioConvenio", null);
             }

                        
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            request.getSession().setAttribute("listaUsuarioConvenio", listaUsuarioConvenio);
            request.getSession().setAttribute("listaTipoConvenio", listaTipoConvenio);
            getServletContext().getRequestDispatcher("/convUsuarioConvenios.jsp").forward(request, response);
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
