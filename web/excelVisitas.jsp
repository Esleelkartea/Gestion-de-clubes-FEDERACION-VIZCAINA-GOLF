<%-- 
    Document   : excelVisitas
    Created on : 02-ago-2011, 12:47:29
    Author     : Julen
--%>

<%@page import="com.Negocio.Visita"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

    response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.

response.setHeader ("Content-Disposition", "attachment;filename=\"visitas.xls\"");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Listado de Visitas</title>

</head>

<body>

<TABLE>

    <tr></tr>
    <tr>
        <td></td>
        <td style="color: red; font-size: 16pt"  >Listado de Visitas</td>
    </tr>
    <tr></tr>

<TR>
<td></td>
<th>Fecha</th>
<th>Nombre</th>
<th>Apellidos</th>
<th>Telefono</th>
<th>Direccion</th>
<th>Poblacion</th>
<th>Atendido por</th>

</TR>

         <%
            ArrayList<Visita> listaVisitas = (ArrayList<Visita>)session.getAttribute("listaVisitas");
            if (listaVisitas != null)
            {
                String strFecha = "";
                for (int i = 0; i<listaVisitas.size(); i++)
                {
                   strFecha = String.valueOf(listaVisitas.get(i).getFechaVisita());
                   String arrFecha[] = strFecha.split("-");
                   strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

          %>


<TR>
   <td></td>
   <td align="center" ><%=strFecha%></td>
   <td><%=listaVisitas.get(i).getNombre()%></td>
   <td><%=listaVisitas.get(i).getApellidos()%></td>
   <td><%=listaVisitas.get(i).getTelefono()%></td>
   <td><%=listaVisitas.get(i).getDireccion()%></td>
   <td><%=listaVisitas.get(i).getPoblacion()%></td>
   <td><%=listaVisitas.get(i).getAtendido()%></td>
</TR>


        <%
            }
          }
       %>


</TABLE>

</body>

</html>


