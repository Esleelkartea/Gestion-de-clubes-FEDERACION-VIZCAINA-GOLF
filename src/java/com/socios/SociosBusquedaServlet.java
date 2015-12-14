package com.socios;

import com.Negocio.Socio;
import com.Negocio.TipoSocio;
import com.Negocio.Usuario;
import com.dal.SocioDAL;
import com.dal.TipoSocioDAL;
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
public class SociosBusquedaServlet extends HttpServlet {
   
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
        request.getSession().setAttribute("resultadoSocios", null);
        request.getSession().setAttribute("relacionSocio", null);
        request.getSession().setAttribute("resultadoMensaje", null);
        request.getSession().setAttribute("accionSocio", null);
        request.getSession().setAttribute("resultadoMIBReserva", null);
        ArrayList<Socio> listaSocios = new ArrayList<Socio>();

        try
        {
            String strNombre = request.getParameter("nombre");
            if (strNombre.equals(""))
                strNombre = null;

            String strApellidos = request.getParameter("apellidos");
            if (strApellidos.equals(""))
                strApellidos = null;

            String strNumSocio = request.getParameter("numSocio");
            if (strNumSocio.equals(""))
                strNumSocio = null;

            String strAntiguedad = request.getParameter("antiguedad");
            if (strAntiguedad.equals(""))
                strAntiguedad = null;

            String strPoblacion = request.getParameter("poblacion");
            if (strPoblacion.equals(""))
                strPoblacion = null;

            String strProvincia = request.getParameter("provincia");
            if (strProvincia.equals(""))
                strProvincia = null;

            String strCPInicio = request.getParameter("cpinicio");
            if (strCPInicio.equals(""))
                strCPInicio = null;

            String strCPFin = request.getParameter("cpfin");
            if (strCPFin.equals(""))
                strCPFin = null;

            String strDNI = request.getParameter("DNI");
            if (strDNI.equals(""))
                strDNI = null;

            String strSexo = request.getParameter("sexo");
            if (strSexo.equals(""))
                strSexo = null;

            String strTipoSocio = request.getParameter("tipoSocio");
            TipoSocio unTipoSocio;
            if (strTipoSocio.equals(""))
                unTipoSocio = null;
            else
            {
                TipoSocioDAL tipoSocioDAL = new TipoSocioDAL();
                unTipoSocio = tipoSocioDAL.getTipoSocio(Integer.parseInt(strTipoSocio));
            }

            SocioDAL socioDAL = new SocioDAL();

            listaSocios = socioDAL.busquedaSocio(strNumSocio, strDNI, strNombre, strApellidos, strSexo, strAntiguedad, unTipoSocio, null, strCPInicio, strCPFin, strPoblacion, strProvincia);
            request.getSession().setAttribute("listaSocios", listaSocios);
            request.getSession().setAttribute("unSocio", null);


        }
        catch(Exception ex)
        {
            request.getSession().setAttribute("listaSocios", listaSocios);
        }
        finally
        {
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
