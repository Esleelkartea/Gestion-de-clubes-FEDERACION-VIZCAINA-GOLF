/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cursos;

import com.Negocio.Curso;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.CursoDAL;
import com.dal.CursoInscritoDAL;
import com.dal.SocioDAL;
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
public class cursosInscritosMIBServlet extends HttpServlet {
   
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

        CursoInscritoDAL cursoInscritoDAL = new CursoInscritoDAL();

        String strCurso = (String)request.getSession().getAttribute("curso");

        try
        {
            String strNombre = request.getParameter("nombre");
            String strApellidos = request.getParameter("apellidos");

            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio = null;
            unSocio = socioDAL.getSocio(strNombre, strApellidos);
            String destino = "cursoCursoServlet";
            if (unSocio == null)
            {
                request.getSession().setAttribute("resultadoSocio", false);
                request.getRequestDispatcher(destino).forward(request, response);
                return;
            }
            else
            {
                request.getSession().setAttribute("resultadoSocio", null);
                
                CursoDAL cursoDAL = new CursoDAL();
                //Insercion
                if (request.getParameter("enviar").equals("Alta"))
                {
                    
                    Curso unCurso = null;
                    unCurso = cursoDAL.getCurso(Integer.parseInt(strCurso));
                    if(unCurso.getNumApuntados()<unCurso.getNumMax())
                    {
                        String fecha = request.getParameter("fechaInscripcion");
                        String arrFecha[] = fecha.split("-");
                        fecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                        //int idCurso = (Integer)request.getSession().getAttribute("curso");
                        cursoInscritoDAL.insertarCursoInscritos(unSocio.getIdSocio(), Integer.parseInt(strCurso), Date.valueOf(fecha));
                        cursoDAL.sumarAsistente(Integer.parseInt(strCurso));
                        request.getSession().setAttribute("resultadoAsistencia", null);
                    }
                    else
                    {
                        request.getSession().setAttribute("resultadoAsistencia", false);
                    }

                    
                    request.getRequestDispatcher(destino).forward(request, response);
                    return;
                }

                //Eliminar
                if (request.getParameter("enviar").equals("Baja"))
                {
                    String strIdInscrito = request.getParameter("idInscrito");
                    cursoInscritoDAL.eliminarCursoInscrito(Integer.parseInt(strIdInscrito));
                    cursoDAL.restarAsistente(Integer.parseInt(strCurso));
                    request.getRequestDispatcher(destino).forward(request, response);
                    return;
                }
            }
        }
        finally
        {
            //request.getRequestDispatcher("cursoCursoServlet").forward(request, response);
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
