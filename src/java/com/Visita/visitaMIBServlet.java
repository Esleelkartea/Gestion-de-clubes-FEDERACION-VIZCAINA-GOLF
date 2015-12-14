/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Visita;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import com.dal.VisitaDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class visitaMIBServlet extends HttpServlet {
   
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

        VisitaDAL visitaDAL = new VisitaDAL();

        try
        {
           //Insercion de la visita
            if (request.getParameter("enviar").equals("Alta"))
            {
                
                String strNombre = request.getParameter("nombre");
                String strApellidos = request.getParameter("apellidos");
                String strTelefono = request.getParameter("telefono");
                String strDireccion = request.getParameter("direccion");
                String strPoblacion = request.getParameter("poblacion");
                String strAtendido = request.getParameter("atendidoPor");
                String strFecha = request.getParameter("fecha");

                String arrFecha[] = strFecha.split("-");
                strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                if(visitaDAL.insertarVisita(strNombre, strApellidos, strTelefono, strDireccion, strPoblacion, strAtendido, Date.valueOf(strFecha)))
                {
                    request.getSession().setAttribute("resultadoVisitas", "La insercion se ha realizado correctamente.");
                }
                else
                {
                    request.getSession().setAttribute("resultadoVisitas", "La insercion no se ha podido realizar.");
                }

                request.getRequestDispatcher("visitaServlet").forward(request, response);
                return;
            }

            //Modificacion de la visita
            if (request.getParameter("enviar").equals("Modificar"))
            {
                String strIdVisita = request.getParameter("idVisita");
                String strNombre = request.getParameter("nombre");
                String strApellidos = request.getParameter("apellidos");
                String strTelefono = request.getParameter("telefono");
                String strDireccion = request.getParameter("direccion");
                String strPoblacion = request.getParameter("poblacion");
                String strAtendido = request.getParameter("atendidoPor");
                String strFecha = request.getParameter("fecha");

                String arrFecha[] = strFecha.split("-");
                strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                if(visitaDAL.modificarVisita(Integer.parseInt(strIdVisita), strNombre, strApellidos, strTelefono, strDireccion, strPoblacion, strAtendido, Date.valueOf(strFecha)))
                {
                    request.getSession().setAttribute("resultadoVisitas", "La modificacion se ha realizado correctamente.");
                }
                else
                {
                    request.getSession().setAttribute("resultadoVisitas", "La modificacion no se ha podido realizar.");
                }

                request.getRequestDispatcher("visitaServlet").forward(request, response);
                return;
            }

            //Eliminar visita
            if (request.getParameter("enviar").equals("Baja"))
            {
                String strIdVisita = request.getParameter("idVisita");
                if(visitaDAL.eliminarVisita(Integer.parseInt(strIdVisita)))
                {
                    request.getSession().setAttribute("resultadoVisitas", "La eliminacion se ha realizado correctamente.");
                }
                else
                {
                    request.getSession().setAttribute("resultadoVisitas", "La eliminacion no se ha podido realizar.");
                }


                request.getRequestDispatcher("visitaServlet").forward(request, response);
                return;
            }


        }

        catch(Exception ex)
        {
            request.getRequestDispatcher("visitaServlet").forward(request, response);
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
