/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.eServlets;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.FotoDAL;
import com.dal.SocioDAL;
import com.dal.UsuarioDAL;
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
public class eSocioMIBServlet extends HttpServlet {
   
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
        
        SocioDAL socioDAL = new SocioDAL();
        UsuarioDAL usuarioDAL = new UsuarioDAL();
        String strIdUsuario = null;
        
        try
        {
            if (request.getParameter("enviar").equals("Modificar"))
            {
                String strNombreUsuario = request.getParameter("usuario");
                String strPassword = request.getParameter("password");
                strIdUsuario = request.getParameter("idUsuario");
                usuarioDAL.modificarUsuario(strIdUsuario, strNombreUsuario, strPassword);

                String strIdSocio = request.getParameter("idSocio");

                String strNombre = request.getParameter("nombre");
                if (strNombre.equals("")) strNombre = null;
                String strApellidos = request.getParameter("apellidos");
                if (strApellidos.equals(null)) strApellidos = null;
                String strNumSocio = request.getParameter("numSocio");
                if (strNumSocio.equals("")) strNumSocio = null;
                String strAntiguedad = request.getParameter("antiguedad");

                String strDni = request.getParameter("dni");
                if (strDni.equals("")) strDni = null;
                String strProfesion = request.getParameter("profesion");
                if (strProfesion.equals("")) strProfesion = null;
                String strObservaciones = request.getParameter("observaciones");
                if (strObservaciones.equals("")) strObservaciones = null;
                String strDireccion = request.getParameter("direccion");
                if (strDireccion.equals("")) strDireccion = null;
                String strCP = request.getParameter("cp");
                if (strCP.equals("")) strCP = null;
                String strPoblacion = request.getParameter("poblacion");
                if (strPoblacion.equals("")) strPoblacion = null;
                String strProvincia = request.getParameter("provincia");
                if (strProvincia.equals("")) strProvincia = null;
                String strTelefono1 = request.getParameter("tel1");
                if (strTelefono1.equals("")) strTelefono1 = null;
                String strTelefono2 = request.getParameter("tel2");
                if (strTelefono2.equals("")) strTelefono2 = null;
                String strFax = request.getParameter("fax");
                if (strFax.equals("")) strFax = null;
                String strEmail = request.getParameter("email");
                if (strEmail.equals("")) strEmail = null;
                String strBanco = request.getParameter("banco");
                if (strBanco.equals("")) strBanco = null;
                String strNumCuenta = request.getParameter("numCuenta");
                if (strNumCuenta.equals("")) strNumCuenta = null;

                String strFechaAlta = request.getParameter("fechaAlta");
                if (strFechaAlta.equals(""))
                    strFechaAlta = null;
                else
                {
                String[] arrFechaAlta = strFechaAlta.split("-");
                strFechaAlta = arrFechaAlta[2] + "-" + arrFechaAlta[1] + "-" + arrFechaAlta[0];
                }

                String strFechaNac = request.getParameter("fechaNacimiento");
                if (strFechaNac.equals(""))
                    strFechaNac = null;
                else
                {
                String[] arrFechaNac = strFechaNac.split("-");
                strFechaNac = arrFechaNac[2] + "-" + arrFechaNac[1] + "-" + arrFechaNac[0];
                }

                String strSexo = request.getParameter("sexo");

                
                String strFoto = request.getParameter("foto");
                Socio unSocio = (Socio)request.getSession().getAttribute("socio");
                
                socioDAL.modificarSocio(Integer.parseInt(strIdSocio), strNumSocio, strDni, strNombre, strApellidos, strSexo, Date.valueOf(strFechaNac), strProfesion, strAntiguedad, unSocio.getFoto().getIdFoto() ,strObservaciones, unSocio.getTipo().getIdTipoSocio(), false, strDireccion, strCP, strPoblacion, strProvincia, strTelefono1, strTelefono2, strFax, strEmail, strBanco, strNumCuenta, unUsuario.getIdUsuario(), Date.valueOf(strFechaAlta), unSocio.getTipoPago().getIdTipoPago());

            }

            unUsuario = usuarioDAL.getUsuario(Integer.parseInt(strIdUsuario));
            request.getSession().setAttribute("usuario", unUsuario);
            Socio socioNuevo;
            socioNuevo = socioDAL.getSocio(Integer.parseInt(strIdUsuario));
            request.getSession().setAttribute("socio", socioNuevo);
            request.getSession().setAttribute("socioExtranet", socioNuevo);
            request.getRequestDispatcher("eSocioServlet").forward(request, response);
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
