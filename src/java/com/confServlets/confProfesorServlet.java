/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.confServlets;

import com.Negocio.Profesor;
import com.Negocio.ProfesorHorario;
import com.Negocio.Socio;
import com.Negocio.TipoInstalacion;
import com.Negocio.Usuario;
import com.dal.ProfesorDAL;
import com.dal.ProfesorHorarioDAL;
import com.dal.SocioDAL;
import com.dal.TipoInstalacionDAL;
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
public class confProfesorServlet extends HttpServlet {
   
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

        ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();
        ArrayList<TipoInstalacion> listaTipoInstalacion = new ArrayList<TipoInstalacion>();
        
        try
        {

            TipoInstalacionDAL tipoInstalacionDAL = new TipoInstalacionDAL();
            listaTipoInstalacion = tipoInstalacionDAL.getTipoInstalacion(-1, null);
            request.getSession().setAttribute("listaTipoInstalacion", listaTipoInstalacion);

            ProfesorDAL profesorDAL = new ProfesorDAL();
            listaProfesores = profesorDAL.getProfesor(-1, null, null, null, null,-1);
            ProfesorHorarioDAL profesorHorarioDAL = new ProfesorHorarioDAL();
            ProfesorHorario unProfesorHorario = null;
            request.getSession().setAttribute("unProfesorHorario", null);
            Profesor unProfesor;

            if(request.getParameter("var")!=null)
            {
                String idProfesor = request.getParameter("var");
                unProfesor = profesorDAL.getProfesor(Integer.parseInt(idProfesor));
                request.getSession().setAttribute("profesor", unProfesor);

                //Recuperar los horarios de ese profesor
                ArrayList<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();
                listaProfesorHorario = profesorHorarioDAL.getunProfesorHorario2(Integer.parseInt(idProfesor));
                request.getSession().setAttribute("listaProfesorHorario", listaProfesorHorario);
                request.getSession().setAttribute("unProfesorHorario", null);


                if(request.getParameter("var2") != null)
                {
                    String idProfesorHorario = request.getParameter("var2");

                    unProfesorHorario = profesorHorarioDAL.getProfesorHorario(Integer.parseInt(idProfesorHorario));
                    request.getSession().setAttribute("unProfesorHorario", unProfesorHorario);
                }
                else
                {
                    request.getSession().setAttribute("unProfesorHorario", null);
                }

                
            }
            else
            {
                 request.getSession().setAttribute("profesor", null);
                 request.getSession().setAttribute("listaProfesorHorario", null);
            }
            
        } 
        finally
        {
            request.getSession().setAttribute("listaProfesores", listaProfesores);
            getServletContext().getRequestDispatcher("/configuracionProfesores.jsp").forward(request, response);
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
