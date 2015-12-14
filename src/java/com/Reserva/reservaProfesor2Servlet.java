/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Reserva;

import com.Negocio.Curso;
import com.Negocio.Profesor;
import com.Negocio.ProfesorHorario;
import com.Negocio.ReservaProfesor;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.CursoDAL;
import com.dal.ProfesorDAL;
import com.dal.ProfesorHorarioDAL;
import com.dal.ReservaProfesorDAL;
import com.dal.SocioDAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Julen
 */
public class reservaProfesor2Servlet extends HttpServlet {
   
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

        try
        {
           String idProfesor = request.getParameter("varProfesor");
            request.getSession().setAttribute("profesor", idProfesor);

            ProfesorDAL profesorDAL = new ProfesorDAL();
            Profesor unProfesor = null;
            unProfesor = profesorDAL.getProfesor(Integer.parseInt(idProfesor));
            String nombreProfesor = unProfesor.getNombre();
            request.getSession().setAttribute("nombreProfesor", nombreProfesor);

            ProfesorHorarioDAL profesorHorarioDAL = new ProfesorHorarioDAL();
            ReservaProfesorDAL reservaHorarioDAL = new ReservaProfesorDAL();
            ArrayList<ProfesorHorario> listaProfesorHorario = new ArrayList<ProfesorHorario>();
            ArrayList<ReservaProfesor> listaReservaProfesor = new ArrayList<ReservaProfesor>();
            ArrayList<Curso> listaCursos = new ArrayList<Curso>();


            String fechaHoy;
            java.util.Date fecha = new java.util.Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            fechaHoy = formato.format(fecha);

            Calendar ci=Calendar.getInstance();
            ci.setTime(formato.parse(fechaHoy));

            Calendar i=(Calendar)ci.clone();
            for(int j = 0;j<6;j++)
            {
                i.add(Calendar.DATE, 1);
            }

            String fechaFin = formato.format(i.getTime());

            request.getSession().setAttribute("fechaHoy", fechaHoy);
            request.getSession().setAttribute("fechaFin", fechaFin);



            listaProfesorHorario = profesorHorarioDAL.getProfesorHorario(-1, null, null, null, null, null, Integer.parseInt(idProfesor));
            request.getSession().setAttribute("listaProfesorHorario", listaProfesorHorario);

            listaReservaProfesor = reservaHorarioDAL.busquedaReservasProfesor(-1, Integer.parseInt(idProfesor), Date.valueOf(fechaHoy), Date.valueOf(fechaFin), -1, -1);
            request.getSession().setAttribute("listaReservaProfesor", listaReservaProfesor);

            CursoDAL cursoDAL = new CursoDAL();
            listaCursos = cursoDAL.getCursoProfesor(Integer.parseInt(idProfesor));
            request.getSession().setAttribute("listaCursos", listaCursos);
        }
        catch(Exception ex)
        {

        }
        finally
        {
            getServletContext().getRequestDispatcher("/reservaProfesor2.jsp").forward(request, response);
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
