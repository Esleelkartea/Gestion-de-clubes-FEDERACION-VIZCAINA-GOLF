/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socios;

import com.Negocio.Foto;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.FotoDAL;
import com.dal.MensajeDAL;
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
public class SocioMIBServlet extends HttpServlet {
   
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
        
        SocioDAL socioDAL = new SocioDAL();
        FotoDAL fotoDAL = new FotoDAL();
        UsuarioDAL usuarioDAL = new UsuarioDAL();
        request.getSession().setAttribute("relacionSocio", null);
        
        try
        {
           if (request.getParameter("enviar").equals("Alta"))
           {
                String strNombre = request.getParameter("nombre");
                String strApellidos = request.getParameter("apellidos");
                String strSexo = request.getParameter("sexo");
                String strPoblacion = request.getParameter("poblacion");
                String strProvincia = request.getParameter("provincia");
                String strDireccion = request.getParameter("direccion");
                String strCP = request.getParameter("cp");
                String strNumSocio = request.getParameter("numSocio");
                String strAntiguedad = request.getParameter("antiguedad");
                if (strAntiguedad.equals("")) strAntiguedad = null;
                String strDNI = request.getParameter("dni");
                String strProfesion = request.getParameter("profesion");
                String strObservaciones = request.getParameter("observaciones");
                //if (strObservaciones.equals("")) strObservaciones = null;

                String strTipoSocio = request.getParameter("tipoSocio");
                String strTipoPago = request.getParameter("tipoPago");

                String strFechaNac = request.getParameter("fechaNacimiento");
                if(strFechaNac.equals(""))
                    strFechaNac = null;
                else
                {
                    String arrFechaNac[] = strFechaNac.split("-");
                    strFechaNac = arrFechaNac[2] + "-" + arrFechaNac[1] + "-" + arrFechaNac[0];
                }

                String strFechaAlta = request.getParameter("fechaAntiguedad");
                if(strFechaAlta.equals(""))
                    strFechaAlta = null;
                else
                {
                    String arrFechaAlta[] = strFechaAlta.split("-");
                    strFechaAlta = arrFechaAlta[2] + "-" + arrFechaAlta[1] + "-" + arrFechaAlta[0];
                }

                String strTelefono1 = request.getParameter("tel1");
                //if (strTelefono1.equals("")) strTelefono1 = null;
                String strTelefono2 = request.getParameter("tel2");
                //if (strTelefono2.equals("")) strTelefono2 = null;
                String strFax = request.getParameter("fax");
                //if (strFax.equals("")) strFax = null;
                String strEmail = request.getParameter("email");
                //if (strEmail.equals("")) strEmail = null;

                String strBanco = request.getParameter("banco");
                String strNumCuenta = request.getParameter("numCuenta");
                /*String strMartuberri = request.getParameter("martuberri");
                boolean martuberri = false;
                if (strMartuberri != null)
                {
                    if (strMartuberri.equals("on")) martuberri = true;
                }*/
 
                String strFoto = request.getParameter("foto");
                if(strFoto.equals("")) strFoto = "1";
                

                String strIdUsuario = request.getParameter("idUsuario");

                String strUsuario = request.getParameter("usuarioSocio");
                String strPassword = request.getParameter("usuarioPassword");
                
                usuarioDAL.insertarUsuario(strUsuario, strPassword);
                Usuario unUsuario = usuarioDAL.getUsuario(strUsuario, strPassword);
      

                if(socioDAL.insertarSocio(strNumSocio, strDNI, strNombre, strApellidos, strSexo, Date.valueOf(strFechaNac), strProfesion, strAntiguedad,Integer.parseInt(strFoto), strObservaciones, Integer.parseInt(strTipoSocio), false, strDireccion, strCP, strPoblacion, strProvincia, strTelefono1, strTelefono2, strFax, strEmail, strBanco, strNumCuenta, unUsuario.getIdUsuario(),Date.valueOf(strFechaAlta), Integer.parseInt(strTipoPago)))
                {
                    request.getSession().setAttribute("resultadoSocios", "La insercion se ha realizado correctamente.");
                }
                else
                {
                    request.getSession().setAttribute("resultadoSocios", "La insercion no se ha podido realizar.");
                }

                request.getRequestDispatcher("SociosServlet").forward(request, response);
                return;

               
           }

           if (request.getParameter("enviar").equals("Modificar"))
           {
                String strNombre = request.getParameter("nombre");
                String strApellidos = request.getParameter("apellidos");
                String strSexo = request.getParameter("sexo");
                String strPoblacion = request.getParameter("poblacion");
                String strProvincia = request.getParameter("provincia");
                String strDireccion = request.getParameter("direccion");
                String strCP = request.getParameter("cp");
                String strNumSocio = request.getParameter("numSocio");
                String strAntiguedad = request.getParameter("antiguedad");
                if (strAntiguedad.equals("")) strAntiguedad = null;
                String strDNI = request.getParameter("dni");
                String strProfesion = request.getParameter("profesion");
                String strObservaciones = request.getParameter("observaciones");
                //if (strObservaciones.equals("")) strObservaciones = null;

                String strTipoSocio = request.getParameter("tipoSocio");
                String strTipoPago = request.getParameter("tipoPago");

                String strFechaNac = request.getParameter("fechaNacimiento");
                String arrFechaNac[] = strFechaNac.split("-");
                strFechaNac = arrFechaNac[2] + "-" + arrFechaNac[1] + "-" + arrFechaNac[0];

                String strFechaAlta = request.getParameter("fechaAntiguedad");
                String arrFechaAlta[] = strFechaAlta.split("-");
                strFechaAlta = arrFechaAlta[2] + "-" + arrFechaAlta[1] + "-" + arrFechaAlta[0];

                String strTelefono1 = request.getParameter("tel1");
                //if (strTelefono1.equals("")) strTelefono1 = null;
                String strTelefono2 = request.getParameter("tel2");
                //if (strTelefono2.equals("")) strTelefono2 = null;
                String strFax = request.getParameter("fax");
                //if (strFax.equals("")) strFax = null;
                String strEmail = request.getParameter("email");
                //if (strEmail.equals("")) strEmail = null;

                String strBanco = request.getParameter("banco");
                String strNumCuenta = request.getParameter("numCuenta");
                /*String strMartuberri = request.getParameter("martuberri");
                boolean martuberri = false;
                if (strMartuberri != null)
                {
                    if (strMartuberri.equals("on")) martuberri = true;
                }*/
                
                String strFoto = request.getParameter("foto");                          

                String strIdUsuario = request.getParameter("idUsuarioSocio");

                String strUsuario = request.getParameter("usuarioSocio");
                String strPassword = request.getParameter("usuarioPassword");
                usuarioDAL.modificarUsuario(strIdUsuario, strUsuario, strPassword);

                String strIdSocio = request.getParameter("idSocio");

                
                if(socioDAL.modificarSocio(Integer.parseInt(strIdSocio), strNumSocio, strDNI, strNombre, strApellidos, strSexo, Date.valueOf(strFechaNac), strProfesion, strAntiguedad, Integer.parseInt(strFoto), strObservaciones, Integer.parseInt(strTipoSocio), false, strDireccion, strCP, strPoblacion, strProvincia, strTelefono1, strTelefono2, strFax, strEmail, strBanco, strNumCuenta, Integer.parseInt(strIdUsuario), Date.valueOf(strFechaAlta), Integer.parseInt(strTipoPago)))
                {
                    request.getSession().setAttribute("resultadoSocios", "La modificacion se ha realizado correctamente.");
                }
                else
                {
                    request.getSession().setAttribute("resultadoSocios", "La modificacion no se ha podido realizar.");
                }

                request.getRequestDispatcher("SociosServlet").forward(request, response);
                return;


           }

           if (request.getParameter("enviar").equals("Baja"))
           {

               String strIdSocio = request.getParameter("idSocio");
               String strIdUsuario = request.getParameter("idUsuarioSocio");
              
               
              
               if (socioDAL.eliminarSocio(Integer.parseInt(strIdSocio)))
               {
                   request.getSession().setAttribute("resultadoSocios", "La eliminacion se ha realizado correctamente.");
                   usuarioDAL.eliminarUsuario(strIdUsuario);
                   MensajeDAL mensajeDAL = new MensajeDAL();
                   mensajeDAL.eliminarMensajesEmisorReceptor(Integer.parseInt(strIdUsuario));
               }
               else
                {
                    request.getSession().setAttribute("resultadoSocios", "La eliminacion no se ha podido realizar.");
                }              

           }

        }
        catch(Exception ex)
        {
           
        }
        finally
        {
            request.getRequestDispatcher("SociosServlet").forward(request, response);
            return;
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
