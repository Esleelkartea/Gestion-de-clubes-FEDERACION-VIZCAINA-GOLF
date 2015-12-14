/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlets;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import com.dal.UsuarioDAL;
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
public class LoginServlet extends HttpServlet {
   
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
        try
        {
            String strNombre = request.getParameter("usuario");
            String strPassword = request.getParameter("password");
            
            UsuarioDAL usuarioDAL = new UsuarioDAL();
            Usuario unUsuario;
            unUsuario = usuarioDAL.getUsuario(strNombre, strPassword);

           if (unUsuario == null)
            {
                request.getSession().setAttribute("errorLogin", true);
                request.getSession().setAttribute("usuario", null);
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
            else
            {
                request.getSession().setAttribute("usuario", unUsuario);
                request.getSession().setAttribute("errorLogin", false);
                request.getSession().setAttribute("resultadoSocioInvitacion", null);
                request.getSession().setAttribute("resultadoInvitacion", null);
                request.getSession().setAttribute("resultadoMensaje", null);
                request.getSession().setAttribute("listaInvitaciones", null);
                request.getSession().setAttribute("listaVenta", null);
                request.getSession().setAttribute("resultado", null);

                request.getSession().setAttribute("resultadoSocioVenta", null);
                request.getSession().setAttribute("resultadoProductoVenta", null);
                request.getSession().setAttribute("resultadoVenta", null);
                request.getSession().setAttribute("resultadoMIBReservaSocio",null);
                request.getSession().setAttribute("resultadoMIBReserva",null);
                request.getSession().setAttribute("relacionSocio", null);
                request.getSession().setAttribute("error", null);
                request.getSession().setAttribute("fechaInicio", null);
                request.getSession().setAttribute("fechaFin", null);
                request.getSession().setAttribute("resultadoReservaDisponible", null);

                request.getSession().setAttribute("instalacion", null);

                SocioDAL socioDAL = new SocioDAL();
                Socio unSocio;
                unSocio = socioDAL.getSocio(unUsuario.getIdUsuario());
                if (unSocio == null)
                    //El usuario NO es socio, se redirige a la intranet
                    getServletContext().getRequestDispatcher("/SociosServlet").forward(request, response);
                else
             
                    //El usuario es socio, se redirige a la extranet
                    request.getSession().setAttribute("socioExtranet", unSocio);
                    getServletContext().getRequestDispatcher("/eReservaGestionServlet").forward(request, response);
                    return;
            }        
        }
        catch(Exception ex)
        {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
