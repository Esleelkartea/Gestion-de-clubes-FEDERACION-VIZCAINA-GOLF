/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socios;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.MensajeDAL;
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
public class MensajeMIBServlet extends HttpServlet {
   
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
             request.getRequestDispatcher("index.jsp").forward(request, response);
             return;
        }
        else
        {
            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio;
            
            unSocio = socioDAL.getSocio(unUsuario.getIdUsuario());
            if (unSocio != null)
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        }
        
        MensajeDAL mensajeDAL = new MensajeDAL();
        String strIdSocio = "";
        try
        {
            if (request.getParameter("enviar").equals("Enviar"))
            {

                java.util.Date utilDate = new java.util.Date(); //fecha actual
                long lnMilisegundos = utilDate.getTime();
                java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);

                String strAsunto = request.getParameter("asunto");
                String strTexto = request.getParameter("texto");

                strIdSocio = request.getParameter("idSocio");
                String strIdUsuarioSocio = request.getParameter("idUsuarioSocio");
                String strIdUsuario = request.getParameter("idUsuario");
                UsuarioDAL usuarioDAL = new UsuarioDAL();


                if(mensajeDAL.insertarMensaje(sqlDate, Integer.parseInt(strIdUsuario), Integer.parseInt(strIdUsuarioSocio), strAsunto, strTexto))
                {
                    request.getSession().setAttribute("resultadoMensaje", "El mensaje se ha enviado correctamente");
                }
                else
                {
                    request.getSession().setAttribute("resultadoMensaje", "El mensaje no se ha podido enviar");
                }


            }
        }
        finally
        {
            request.getRequestDispatcher("SociosServlet?var="+strIdSocio).forward(request, response);
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
