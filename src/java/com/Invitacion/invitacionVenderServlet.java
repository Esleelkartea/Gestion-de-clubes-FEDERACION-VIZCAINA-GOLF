/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Invitacion;

import com.Negocio.Invitacion;
import com.Negocio.Socio;
import com.Negocio.TipoPago;
import com.Negocio.Usuario;
import com.dal.InvitacionDAL;
import com.dal.SocioDAL;
import com.dal.TipoPagoDAL;
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
public class invitacionVenderServlet extends HttpServlet {
   
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
            String strNombre = request.getParameter("nombre");
            String strApellidos = request.getParameter("apellidos");

            java.util.Date utilDate = new java.util.Date(); //fecha actual
            long lnMilisegundos = utilDate.getTime();
            java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
            
            String strInvitado = request.getParameter("invitado");
            String strCantidad = request.getParameter("cantidad");
            String strImporte = request.getParameter("importe");
            String strFormaPago = request.getParameter("formaPago");

            Invitacion unaInvitacion = null;
            InvitacionDAL invitacionDAL = new InvitacionDAL();

            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio = null;
            unSocio = socioDAL.getSocioInvitacion(-1, strNombre, strApellidos);

            TipoPago unTipoPago = null;
            TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
            unTipoPago = tipoPagoDAL.getTipoPago(Integer.parseInt(strFormaPago));

            
            if (unSocio == null)
                request.getSession().setAttribute("resultadoSocioInvitacion", false);
            else
            {
                request.getSession().setAttribute("resultadoSocioInvitacion", true);
                if (strInvitado.equals("") || strImporte.equals("") || strCantidad.equals(""))
                {
                    request.getSession().setAttribute("resultadoInvitacion", false);
                }
                else
                {
                    if(invitacionDAL.insertarInvitacion(unSocio, sqlDate, strInvitado, Integer.parseInt(strCantidad), Double.parseDouble(strImporte), unTipoPago))
                    {
                        request.getSession().setAttribute("resultadoInvitacion", true);
                    }
                    else
                    {
                        request.getSession().setAttribute("resultadoInvitacion", false);
                    }

                }
                
            }

        }
        catch(Exception ex)
        {
            
        }

        finally
        {
            request.getRequestDispatcher("invitacion.jsp").forward(request, response);
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
