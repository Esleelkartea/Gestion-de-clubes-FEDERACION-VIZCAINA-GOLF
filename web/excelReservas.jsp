<%-- 
    Document   : excelReservas
    Created on : 02-ago-2011, 13:05:23
    Author     : Julen
--%>

<%@page import="com.Negocio.TipoReserva"%>
<%@page import="com.Negocio.Instalacion"%>
<%@page import="com.dal.TipoInstalacionDAL"%>
<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.dal.TipoReservaDAL"%>
<%@page import="com.dal.SocioDAL"%>
<%@page import="com.dal.InstalacionDAL"%>
<%@page import="com.Negocio.Reserva"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

    response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.

response.setHeader ("Content-Disposition", "attachment;filename=\"reservas.xls\"");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Listado de Reservas</title>

</head>

<body>

<TABLE>
     <tr></tr>
    <tr>
        <td></td>
        <td style="color: red; font-size: 16pt"  >Listado de Reservas</td>
    </tr>
    <tr></tr>

<TR>
    <td></td>
    <th>Instalacion</th>
    <th>Tipo de Instalacion</th>
    <th>Fecha</th>
    <th>Nombre</th>
    <th>Apellidos</th>
    <th>Estado de la Reserva</th>

</TR>

         <%
            ArrayList<Reserva> listaReservas = (ArrayList<Reserva>)session.getAttribute("listaReservas");
            if (listaReservas != null)
            {
                InstalacionDAL instalacionDAL = new InstalacionDAL();
                SocioDAL socioDAL = new SocioDAL();
                TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
                TipoInstalacionDAL tipoInstalacionDAL = new TipoInstalacionDAL();
                TipoInstalacion unTipoInstalacion;
                Socio unSocio = null;
                Instalacion unaInstalacion = null;
                TipoReserva unTipoReserva = null;
                int idInstalacion;
                int idSocio;
                int idReserva;
                String strFecha;
                for (int i = 0; i<listaReservas.size(); i++)
                {
                    idInstalacion = listaReservas.get(i).getIdInstalacion();
                    unaInstalacion = instalacionDAL.getInstalacion(idInstalacion);
                    strFecha = String.valueOf(listaReservas.get(i).getFecha());
                    String[] arrFecha = strFecha.split("-");
                    strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
                    idReserva = listaReservas.get(i).getIdEstadoReserva();
                    unTipoReserva = tipoReservaDAL.getTipoReserva(idReserva);
                    idSocio = listaReservas.get(i).getIdSocio();
                    unSocio = socioDAL.getSocioId(idSocio);
                    unTipoInstalacion = unaInstalacion.getTipoInstalacion();


        %>

<TR>
   <td></td>
   <td><%=unaInstalacion.getNombre()%></td>
   <td><%=unTipoInstalacion.getNombre()%></td>
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




