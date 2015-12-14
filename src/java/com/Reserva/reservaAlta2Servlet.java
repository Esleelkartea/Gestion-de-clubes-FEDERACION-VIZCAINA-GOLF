/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Reserva;

import com.Negocio.Curso;
import com.Negocio.Instalacion;
import com.Negocio.InstalacionHorario;
import com.Negocio.Reserva;
import com.Negocio.Socio;
import com.Negocio.TipoInstalacion;
import com.Negocio.Usuario;
import com.dal.CursoDAL;
import com.dal.InstalacionDAL;
import com.dal.InstalacionHorarioDAL;
import com.dal.ReservaDAL;
import com.dal.SocioDAL;
import com.dal.TipoInstalacionDAL;
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
public class reservaAlta2Servlet extends HttpServlet {
   
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
            String idInstalacion = request.getParameter("varInstalacion");
            request.getSession().setAttribute("instalacion", idInstalacion);

            InstalacionDAL instalacionDAL = new InstalacionDAL();
            Instalacion unaInstalacion = null;
            unaInstalacion = instalacionDAL.getInstalacion(Integer.parseInt(idInstalacion));
            String nombreInstalacion = unaInstalacion.getNombre();
            request.getSession().setAttribute("nombreInstalacion", nombreInstalacion);

            InstalacionHorarioDAL instalacionHorarioDAL = new InstalacionHorarioDAL();
            ReservaDAL reservaDAL = new ReservaDAL();
            ArrayList<Reserva> listaReservaInstalacion = new ArrayList<Reserva>();
            ArrayList<InstalacionHorario> listaInstalacionHorario = new ArrayList<InstalacionHorario>();

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

            String strTipoInstalacion = (String)request.getSession().getAttribute("tipoInstalacion");
            listaReservaInstalacion = reservaDAL.getReservasDia(Integer.parseInt(idInstalacion), Date.valueOf(fechaHoy), Date.valueOf(fechaFin));
            request.getSession().setAttribute("listaReservaInstalacion", listaReservaInstalacion);

            listaInstalacionHorario = instalacionHorarioDAL.getInstalacionHorarios(null, null, Integer.parseInt(idInstalacion));
            request.getSession().setAttribute("listaInstalacionHorario", listaInstalacionHorario);

            //Añadido
            ArrayList<Curso> listaCursos = new ArrayList<Curso>();
            CursoDAL cursoDAL = new CursoDAL();
            listaCursos = cursoDAL.getCursoActuales(-1, null, null, null, null, -1, Integer.parseInt(idInstalacion));
            request.getSession().setAttribute("listaCursos", listaCursos);
        }
        catch(Exception ex)
        {

        }
        finally
        {
            getServletContext().getRequestDispatcher("/reservaAlta2.jsp").forward(request, response);
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
