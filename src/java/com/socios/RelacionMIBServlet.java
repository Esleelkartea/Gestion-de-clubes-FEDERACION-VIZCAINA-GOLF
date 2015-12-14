/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socios;

import com.Negocio.Socio;
import com.Negocio.TipoFamiliar;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import com.dal.SocioFamiliaDAL;
import com.dal.TipoFamiliarDAL;
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
public class RelacionMIBServlet extends HttpServlet {
   
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
        String strIdSocio="";
        request.getSession().setAttribute("resultadoMensaje", null);
        request.getSession().setAttribute("resultadoSocios", null);
        request.getSession().setAttribute("relacionSocio", null);
        request.getSession().setAttribute("accionSocio",null);
        
        
        try
        {
           if (request.getParameter("crearRelacion").equals("Crear relacion"))
           {
               strIdSocio = request.getParameter("idSocio");
               String strNombreFamiliar = request.getParameter("nombreFamiliar");
               String strApellidoFamiliar = request.getParameter("apellidosFamiliar");
               String strIdTipoSocio = request.getParameter("tipoFamiliar");
               SocioDAL socioDAL = new SocioDAL();
               Socio unSocio = socioDAL.getSocio(strNombreFamiliar, strApellidoFamiliar);
               if (unSocio == null)
               {
                   request.getSession().setAttribute("relacionSocio", "No existe el socio con ese nombre y apellidos");
               }
               else
               {
                   TipoFamiliarDAL tipoFamiliarDAL = new TipoFamiliarDAL();
                   TipoFamiliar tipoFamiliar = tipoFamiliarDAL.getTipoFamiliar(Integer.parseInt(strIdTipoSocio));
                   SocioFamiliaDAL socioFamiliaDAL = new SocioFamiliaDAL();
                   socioFamiliaDAL.insertarSocioFamilia(Integer.parseInt(strIdSocio), unSocio.getIdSocio(), tipoFamiliar);
                   request.getSession().setAttribute("relacionSocio", "Se ha creado la relacion correctamente");
               }


           }
        }
        catch(Exception ex)
        {

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
