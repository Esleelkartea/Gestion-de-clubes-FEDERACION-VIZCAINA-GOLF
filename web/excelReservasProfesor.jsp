<%-- 
    Document   : excelReservasProfesor
    Created on : 14-sep-2011, 11:40:58
    Author     : Julen
--%>

<%@page import="com.Negocio.ReservaProfesor"%>
<%@page import="com.Negocio.Profesor"%>
<%@page import="com.dal.ProfesorDAL"%>
<%@page import="com.Negocio.TipoReserva"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.dal.TipoReservaDAL"%>
<%@page import="com.dal.SocioDAL"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

    response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.

response.setHeader ("Content-Disposition", "attachment;filename=\"reservasProfesor.xls\"");

%>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Listado de Reservas de Profesor</title>

</head>

<body>

<TABLE>
    <tr></tr>
    <tr>
        <td></td>
        <td style="color: red; font-size: 16pt"  >Listado de Reservas de Profesor</td>
    </tr>
    <tr></tr>

<TR>
    <td></td>
    <th>Profesor</th>
    <th>Fecha</th>
    <th>Nombre</th>
    <th>Apellidos</th>
    <th>Estado de la Reserva</th>
</TR>

         <%
            ArrayList<ReservaProfesor> listaReservaProfesor = (ArrayList<ReservaProfesor>)session.getAttribute("listaReservaProfesor");
            if (listaReservaProfesor != null)
            {
                ProfesorDAL profesorDAL = new ProfesorDAL();
                SocioDAL socioDAL = new SocioDAL();
                TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
                Socio unSocio = null;
                Profesor unProfesor = null;
                TipoReserva unTipoReserva = null;
                int idProfesor;
                int idSocio;
                int idReserva;
                String strFecha;
                for (int i = 0; i<listaReservaProfesor.size(); i++)
                {
                    idProfesor = listaReservaProfesor.get(i).getIdProfesor();
                    unProfesor = profesorDAL.getProfesor(idProfesor);
                    strFecha = String.valueOf(listaReservaProfesor.get(i).getFecha());
                    String[] arrFecha = strFecha.split("-");
                    strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
                    idReserva = listaReservaProfesor.get(i).getIdEstadoReserva();
                    unTipoReserva = tipoReservaDAL.getTipoReserva(idReserva);
                    idSocio = listaReservaProfesor.get(i).getIdSocio();
                    unSocio = socioDAL.getSocioId(idSocio);

        %>

<TR>
   <td></td>
   <td><%=unProfesor.getNombre()%></td>
   <td align="center" ><%=strFecha%></td>
   <td><%=unSocio.getNombre()%></td>
   <td><%=unSocio.getApellidos()%></td>
   <td><%=unTipoReserva.getEstado()%></td>
</TR>

        <%
            }
          }
       %>


</TABLE>

</body>

</html>
