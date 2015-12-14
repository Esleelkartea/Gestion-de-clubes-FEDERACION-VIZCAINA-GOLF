/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socios;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class EmailMIBServlet extends HttpServlet {
   
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

        String error = "";
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

        try
        {
            String mensaje = request.getParameter("mensaje");
            String asunto = request.getParameter("asunto");
            String emailDestino = request.getParameter("email");
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "bittorasteinza@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("bittorasteinza@campus-web.com"));

            //String mailKFC="bittorasteinza@campus-web.com";
            
            String usumail=emailDestino;
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(usumail));
            message.setSubject(asunto);

            message.setText(mensaje);
            //message.setText("Acabas de hacer un pedido en KFC!!!");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("bittorasteinza@gmail.com", "phiphi3735");
            t.sendMessage(message, message.getAllRecipients());
            error = "Mensaje enviado correctamente";
            // Cierre.
            t.close();        

        }
        catch(Exception ex)
        {
            error = "Error al enviar el mensaje";
            out.println(ex);
        }
        finally
        {
            request.getSession().setAttribute("error", error);
            getServletContext().getRequestDispatcher("/socios.jsp").forward(request, response);
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
