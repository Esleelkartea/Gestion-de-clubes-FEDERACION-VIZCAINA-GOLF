/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cursos;

import com.Negocio.Curso;
import com.Negocio.Instalacion;
import com.Negocio.Profesor;
import com.Negocio.Socio;
import com.Negocio.TipoCurso;
import com.Negocio.Usuario;
import com.dal.CursoDAL;
import com.dal.InstalacionDAL;
import com.dal.ProfesorDAL;
import com.dal.SocioDAL;
import com.dal.TipoCursoDAL;
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
public class cursosServlet extends HttpServlet {
   
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

        ArrayList<TipoCurso> listaTipoCursos = new ArrayList<TipoCurso>();
        ArrayList<Curso> listaCursos = new ArrayList<Curso>();
        ArrayList<Instalacion> listaInstalaciones = new ArrayList<Instalacion>();
        ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>();

        try
        {
            TipoCursoDAL tipoCursoDAL = new TipoCursoDAL();
            listaTipoCursos = tipoCursoDAL.getTipoCurso(-1, null);
            CursoDAL cursoDAL = new CursoDAL();
            listaCursos = cursoDAL.getCursoActuales(-1, null, null, null, null, -1, -1);
            InstalacionDAL instalacionDAL = new InstalacionDAL();
            listaInstalaciones = instalacionDAL.getInstalacion(-1, null, null, -1.0, -1.0);
            ProfesorDAL profesorDAL = new ProfesorDAL();
            listaProfesores = profesorDAL.getProfesor(-1, null, null, null, null, -1);

            Curso unCurso = null;

            request.getSession().setAttribute("listaTipoCursos", listaTipoCursos);
            request.getSession().setAttribute("listaCursos", listaCursos);
            request.getSession().setAttribute("listaInstalaciones", listaInstalaciones);
            request.getSession().setAttribute("listaProfesores", listaProfesores);

            request.getSession().setAttribute("curso", null);

            if(request.getParameter("var")!=null)
            {
               String idCurso = request.getParameter("var");
               unCurso = cursoDAL.getCurso(Integer.parseInt(idCurso));
               request.getSession().setAttribute("curso", unCurso);
            }
            else
            {
                request.getSession().setAttribute("curso", null);
            }
            

        }
        finally
        {
            getServletContext().getRequestDispatcher("/cursos.jsp").forward(request, response);
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
