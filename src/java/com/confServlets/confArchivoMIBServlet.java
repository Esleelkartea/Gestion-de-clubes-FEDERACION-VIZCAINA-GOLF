/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.confServlets;

import com.Negocio.Archivo;
import com.Negocio.Socio;
import com.Negocio.Usuario;
import com.dal.ArchivoDAL;
import com.dal.SocioDAL;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Julen
 */
public class confArchivoMIBServlet extends HttpServlet {
   
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

        String error="";
        String resultado="";
        String nombre="";
        String comentario="";
        String url="";

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

        ArchivoDAL archivoDAL = new ArchivoDAL();

        try
        {
           boolean fin = false;
           MultipartParser mp = new MultipartParser(request, 50 * 1024 * 1024, false, false); // 50MB
           Part part;
            while ((part = mp.readNextPart()) != null)  // recorremos el 'formulario'
            {
            if (part.isParam()) // es de tipo 'loqueseamenosfile'?? ein??
            {
                String n=part.getName();
                ParamPart p=(ParamPart)part;
                String valorcadena=p.getStringValue();
                fin=null==valorcadena;
                String id="";
                if(!fin)
                {
                    if (n.equals("ArchivoId"))
                          id=valorcadena;
                    if(n.equals("nombre"))
                        nombre=valorcadena;

                    if(n.equals("enviar"))
                        if(valorcadena.equals("Alta"))
                        {
                            if(archivoDAL.insertarArchivo(nombre, url))
                            {
                                resultado="La insercion se ha realizado correctamente";
                            }
                            else
                            {
                                resultado="La insercion no se ha podido realizar";
                            }
                            request.getSession().setAttribute("resultado", resultado);
                            request.getRequestDispatcher("confArchivoServlet").forward(request, response);
                            return;
                        }
                        else  //Eliminar foto
                            if (valorcadena.equals("Eliminar"))
                            {
                                HttpSession session = request.getSession(true);
                                Archivo a = (Archivo)session.getAttribute("archivoDetalle");
                               if(a.getIdArchivo()!=1)
                               {
                                    String[] archivos = a.getUrl().split("/");
                                    String dirUploadFiles;
                                    ServletContext sc = request.getSession().getServletContext();
                                    dirUploadFiles = sc.getRealPath("archivos");
                                    File fich=new File(dirUploadFiles,archivos[1]);
                                    fich.delete();

                                    if(archivoDAL.eliminarArchivo(a.getIdArchivo()))
                                    {
                                        resultado="La eliminacion se ha realizado correctamente";
                                    }
                                    else
                                    {
                                        resultado="La eliminacion no se ha podido realizar";
                                    }
                                    request.getSession().setAttribute("resultado", resultado);
                                }
                                request.getRequestDispatcher("confArchivoServlet").forward(request, response);
                                return;

                            }
                            else
                            if (valorcadena.equals("Modificar"))
                            {
                                HttpSession session = request.getSession(true);
                                Archivo a = (Archivo)session.getAttribute("archivoDetalle");
                                if(a.getIdArchivo() != 1)
                                {
                                    if(archivoDAL.modificarArchivo(a.getIdArchivo(), nombre))
                                    {
                                        resultado="La modificacion se ha realizado correctamente";
                                    }
                                    else
                                    {
                                        resultado="La modificacion no se ha podido realizar";
                                    }
                                    request.getSession().setAttribute("resultado", resultado);
                                }
                                request.getRequestDispatcher("confArchivoServlet").forward(request, response);
                                return;
                            }
                }
            }
            else if (part.isFile()) //es el fichero??
            {
                FilePart filePart = (FilePart)part;
                String nombreFichero = filePart.getFileName();
                 // Obtener la extension
                    String extension = nombreFichero.substring( nombreFichero.lastIndexOf( "." ) );
                    extension = extension.toLowerCase();
                    if (!extension.equals(".doc") && !extension.equals(".pdf") && !extension.equals(".docx"))
                    {
                        resultado = "El archivo debe ser .pdf , .doc o .docx";
                        request.getSession().setAttribute("resultado", resultado);
                        getServletContext().getRequestDispatcher("/confArchivoServlet").forward(request, response);
                        return;
                    }
                 //directorio donde se guardara los archivos
                String dirUploadFiles;
                // Obtengo el directorio donde guardare los archivos
                ServletContext sc = request.getSession().getServletContext();
                dirUploadFiles = sc.getRealPath("archivos");
                File archivo = new File( dirUploadFiles, nombreFichero );
                filePart.writeTo(archivo);
                url="archivos/"+nombreFichero;

            }
          }

            }catch (Exception e){
                // poner respuesta = false; si existe alguna problema
               e.printStackTrace();
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
