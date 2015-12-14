<%-- 
    Document   : excelInvitacion
    Created on : 02-ago-2011, 12:34:58
    Author     : Julen
--%>

<%@page import="com.Negocio.Invitacion"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

    response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.

response.setHeader ("Content-Disposition", "attachment;filename=\"invitaciones.xls\"");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Listado de Instalaciones</title>

</head>

<body>

<TABLE>
    <tr></tr>
    <tr>
        <td></td>
        <td style="color: red; font-size: 16pt"  >Listado de Invitaciones</td>
    </tr>
    <tr></tr>

<TR>
<td></td>
<th>Fecha</th>
<th>Nombre</th>
<th>Apellidos</th>
<th>Invitado</th>
<th>Cantidad</th>
<th>Importe</th>

</TR>

         <%
            ArrayList<Invitacion> listaInvitaciones = (ArrayList<Invitacion>)session.getAttribute("listaInvitaciones");
            if (listaInvitaciones != null)
            {
                int cantidad = 0;
                double importe = 0.0;
                String strFecha = "";
                for (int i = 0; i<listaInvitaciones.size(); i++)
                {
                   cantidad += listaInvitaciones.get(i).getCantidad();
                   importe += listaInvitaciones.get(i).getImporte();
                   strFecha = String.valueOf(listaInvitaciones.get(i).getFecha());
                   String arrFecha[] = strFecha.split("-");
                   strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
        %>


<TR>
    <td></td>
    <td align="center"><%=strFecha%></td>
   <td><%=listaInvitaciones.get(i).getNombre()%></td>
   <td><%=listaInvitaciones.get(i).getApellidos()%></td>
   <td><%=listaInvitaciones.get(i).getInvitado()%></td>
   <td align="center"><%=listaInvitaciones.get(i).getCantidad()%></td>
   <td align="center"><%=listaInvitaciones.get(i).getImporte()%></td>
  
</TR>


        <%
            }
          }
       %>


</TABLE>

</body>

</html>

