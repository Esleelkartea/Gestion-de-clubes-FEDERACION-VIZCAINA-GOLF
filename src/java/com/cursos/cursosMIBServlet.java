/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cursos;

import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.CursoDAL;
import com.dal.SocioDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class cursosMIBServlet extends HttpServlet {
   
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

        CursoDAL cursoDAL = new CursoDAL();

        try
        {
           //Insercion 
            if (request.getParameter("enviar").equals("Alta"))
            {
                String strNombreCurso = request.getParameter("nombreCurso");
                String strIdTipoCurso = request.getParameter("tipoCurso");
                String strIdProfesor = request.getParameter("profesor");
                String strIdInstalacion = request.getParameter("instalacion");

                String strFechaInicio = request.getParameter("fechaInicio");
                String arrFechaInicio[] = strFechaInicio.split("-");
                strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                String strFechaFin = request.getParameter("fechaFin");
                String arrFechaFin[] = strFechaFin.split("-");
                strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];

                String strHoraInicio = request.getParameter("horaInicio");
                String strHoraFin = request.getParameter("horaFin");

                boolean lunes = false;
                boolean martes = false;
                boolean miercoles = false;
                boolean jueves = false;
                boolean viernes = false;
                boolean sabado = false;
                boolean domingo = false;
                String strLunes = request.getParameter("lunes");
                String strMartes = request.getParameter("martes");
                String strMiercoles = request.getParameter("miercoles");
                String strJueves = request.getParameter("jueves");
                String strViernes = request.getParameter("viernes");
                String strSabado = request.getParameter("sabado");
                String strDomingo = request.getParameter("domingo");

                if (strLunes != null)
                {
                    if (strLunes.equals("lunes"))
                        lunes = true;
                }
                if (strMartes != null)
                {
                    if (strMartes.equals("martes"))
                        martes = true;
                }
                if (strMiercoles != null)
                {
                    if (strMiercoles.equals("miercoles"))
                        miercoles = true;
                }
                if (strJueves != null)
                {
                    if (strJueves.equals("jueves"))
                        jueves = true;
                }
                if (strViernes != null)
                {
                    if (strViernes.equals("viernes"))
                        viernes = true;
                }
                if (strSabado != null)
                {
                    if (strSabado.equals("sabado"))
                        sabado = true;
                }
                if (strDomingo != null)
                {
                    if (strDomingo.equals("domingo"))
                        domingo = true;
                }


                String strPrecio = request.getParameter("precio");
                String strNumMax = request.getParameter("numMax");
                String strApuntados = request.getParameter("apuntados");

                String strFechaInscripcion = request.getParameter("fechaInscripcion");
                String arrFechaInscripcion[] = strFechaInscripcion.split("-");
                strFechaInscripcion = arrFechaInscripcion[2] + "-" + arrFechaInscripcion[1] + "-" + arrFechaInscripcion[0];

                cursoDAL.insertarCurso(strNombreCurso, Integer.parseInt(strIdProfesor), Date.valueOf(strFechaInicio), Date.valueOf(strFechaFin), Time.valueOf(strHoraInicio) , Time.valueOf(strHoraFin), lunes, martes, miercoles, jueves, viernes, sabado, domingo, Double.valueOf(strPrecio), Integer.parseInt(strNumMax), 0, Date.valueOf(strFechaInscripcion), Integer.parseInt(strIdTipoCurso), Integer.parseInt(strIdInstalacion), false);
                
                request.getRequestDispatcher("cursosServlet").forward(request, response);
                return;
            }

            //Modificacion del tipo de instalacion
            if (request.getParameter("enviar").equals("Modificar"))
            {
                String strIdCurso = request.getParameter("cursoId");
                
                
                String strNombreCurso = request.getParameter("nombreCurso");
                String strIdTipoCurso = request.getParameter("tipoCurso");
                String strIdProfesor = request.getParameter("profesor");
                String strIdInstalacion = request.getParameter("instalacion");

                String strFechaInicio = request.getParameter("fechaInicio");
                String arrFechaInicio[] = strFechaInicio.split("-");
                strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                String strFechaFin = request.getParameter("fechaFin");
                String arrFechaFin[] = strFechaFin.split("-");
                strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];

                String strHoraInicio = request.getParameter("horaInicio");
                String strHoraFin = request.getParameter("horaFin");

                boolean lunes = false;
                boolean martes = false;
                boolean miercoles = false;
                boolean jueves = false;
                boolean viernes = false;
                boolean sabado = false;
                boolean domingo = false;
                String strLunes = request.getParameter("lunes");
                String strMartes = request.getParameter("martes");
                String strMiercoles = request.getParameter("miercoles");
                String strJueves = request.getParameter("jueves");
                String strViernes = request.getParameter("viernes");
                String strSabado = request.getParameter("sabado");
                String strDomingo = request.getParameter("domingo");

                if (strLunes != null)
                {
                    if (strLunes.equals("lunes"))
                        lunes = true;
                }
                if (strMartes != null)
                {
                    if (strMartes.equals("martes"))
                        martes = true;
                }
                if (strMiercoles != null)
                {
                    if (strMiercoles.equals("miercoles"))
                        miercoles = true;
                }
                if (strJueves != null)
                {
                    if (strJueves.equals("jueves"))
                        jueves = true;
                }
                if (strViernes != null)
                {
                    if (strViernes.equals("viernes"))
                        viernes = true;
                }
                if (strSabado != null)
                {
                    if (strSabado.equals("sabado"))
                        sabado = true;
                }
                if (strDomingo != null)
                {
                    if (strDomingo.equals("domingo"))
                        domingo = true;
                }


                String strPrecio = request.getParameter("precio");
                String strNumMax = request.getParameter("numMax");
                String strApuntados = request.getParameter("apuntados");

                String strFechaInscripcion = request.getParameter("fechaInscripcion");
                String arrFechaInscripcion[] = strFechaInscripcion.split("-");
                strFechaInscripcion = arrFechaInscripcion[2] + "-" + arrFechaInscripcion[1] + "-" + arrFechaInscripcion[0];

                cursoDAL.modificarCurso(Integer.parseInt(strIdCurso), strNombreCurso, Integer.parseInt(strIdProfesor), Date.valueOf(strFechaInicio), Date.valueOf(strFechaFin), Time.valueOf(strHoraInicio) , Time.valueOf(strHoraFin), lunes, martes, miercoles, jueves, viernes, sabado, domingo, Double.valueOf(strPrecio), Integer.parseInt(strNumMax), Integer.parseInt(strApuntados), Date.valueOf(strFechaInscripcion), Integer.parseInt(strIdTipoCurso), Integer.parseInt(strIdInstalacion), false);

                request.getRequestDispatcher("cursosServlet").forward(request, response);
                return;


            }

            //Eliminar tipo instalacion
            if (request.getParameter("enviar").equals("Baja"))
            {

                String strIdCurso = request.getParameter("cursoId");
                cursoDAL.eliminarCurso(Integer.parseInt(strIdCurso));
                request.getRequestDispatcher("cursosServlet").forward(request, response);
                return;
            }

            //Eliminar tipo instalacion
            if (request.getParameter("enviar").equals("Finalizar"))
            {
                String strIdCurso = request.getParameter("cursoId");
                cursoDAL.finalizarCurso(Integer.parseInt(strIdCurso));
                request.getRequestDispatcher("cursosServlet").forward(request, response);
                return;
            }

        }
        finally
        {
            request.getRequestDispatcher("cursosServlet").forward(request, response);
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
