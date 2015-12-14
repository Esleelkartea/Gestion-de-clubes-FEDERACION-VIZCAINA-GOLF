/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.socios;

import com.Negocio.Accion;
import com.Negocio.Foto;
import com.Negocio.Socio;
import com.Negocio.SocioFamilia;
import com.Negocio.TipoFamiliar;
import com.Negocio.TipoPago;
import com.Negocio.TipoSocio;
import com.Negocio.Usuario;
import com.dal.AccionDAL;
import com.dal.FotoDAL;
import com.dal.SocioDAL;
import com.dal.SocioFamiliaDAL;
import com.dal.TipoFamiliarDAL;
import com.dal.TipoPagoDAL;
import com.dal.TipoSocioDAL;
import com.dal.UsuarioDAL;
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
public class SociosServlet extends HttpServlet {
   
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

        ArrayList<TipoSocio> listaTipoSocio = new ArrayList<TipoSocio>();
        ArrayList<String> listaPoblaciones = new ArrayList<String>();
        ArrayList<String> listaProvincias = new ArrayList<String>();
        ArrayList<TipoFamiliar> listaTipoFamiliares = new ArrayList<TipoFamiliar>();
        ArrayList<SocioFamilia> listaFamiliaresSocio = new ArrayList<SocioFamilia>();
        ArrayList<Accion> listaAccionesSocio = new ArrayList<Accion>();
        ArrayList<Foto> listaFotos = new ArrayList<Foto>();
        ArrayList<TipoPago> listaTipoPago = new ArrayList<TipoPago>();

        try
        {
            FotoDAL fotoDAL = new FotoDAL();
            listaFotos = fotoDAL.getFoto(null, null, null, null);
            request.getSession().setAttribute("listaFotos", listaFotos);
            TipoSocioDAL tipoSocioDAL = new TipoSocioDAL();
            SocioDAL socioDAL = new SocioDAL();
            listaTipoSocio = tipoSocioDAL.getTipoSocio(-1, null, -1.0);
            request.getSession().setAttribute("listaTipoSocio", listaTipoSocio);
            listaPoblaciones = socioDAL.getPoblacion();
            request.getSession().setAttribute("listaPoblaciones", listaPoblaciones);
            listaProvincias = socioDAL.getProvincia();
            request.getSession().setAttribute("listaProvincias", listaProvincias);
            TipoFamiliarDAL tipoFamiliarDAL = new TipoFamiliarDAL();
            listaTipoFamiliares = tipoFamiliarDAL.getTipoFamiliar(-1, null);
            request.getSession().setAttribute("listaTipoFamiliares", listaTipoFamiliares);
            request.getSession().setAttribute("listaFamiliaresSocio", null);
            request.getSession().setAttribute("listaAccionesSocio", null);

            TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
            listaTipoPago = tipoPagoDAL.getTipoPago(-1, null);
            request.getSession().setAttribute("listaTipoPago", listaTipoPago);

            Socio unSocio = null;

            if(request.getParameter("var")!=null)
             {
                String idSocio = request.getParameter("var");
                unSocio = socioDAL.getSocioId(Integer.parseInt(idSocio));
                request.getSession().setAttribute("unSocio", unSocio);

                UsuarioDAL usuarioDAL = new UsuarioDAL();
                Usuario unUsuario = usuarioDAL.getUsuario(unSocio.getIdUsuario());
                request.getSession().setAttribute("usuarioSocio", unUsuario);

                SocioFamiliaDAL socioFamiliaDAL = new SocioFamiliaDAL();
                listaFamiliaresSocio = socioFamiliaDAL.getSocioFamilias(unSocio.getIdSocio());
                request.getSession().setAttribute("listaFamiliaresSocio", listaFamiliaresSocio);
                AccionDAL accionDAL = new AccionDAL();
                listaAccionesSocio = accionDAL.getAccionesSocio(unSocio.getIdSocio());
                request.getSession().setAttribute("listaAccionesSocio", listaAccionesSocio);

             }
             else
             {
                 request.getSession().setAttribute("unSocio", null);
                 request.getSession().setAttribute("listaSocios", null);
             }

        }
        finally
        {
            request.getSession().setAttribute("listaTipoSocio", listaTipoSocio);
            getServletContext().getRequestDispatcher("/socios.jsp").forward(request, response);
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
