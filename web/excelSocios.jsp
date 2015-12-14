<%-- 
    Document   : excelSocios
    Created on : 02-ago-2011, 12:07:31
    Author     : Julen
--%>

<%@page import="com.Negocio.Socio"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

    response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.

response.setHeader ("Content-Disposition", "attachment;filename=\"socios.xls\"");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Listado de Socios</title>

</head>

<body>

    <TABLE border="0" >
    <tr></tr>
    <tr>
        <td></td>
        <td style="color: red; font-size: 16pt"  >Listado de Socios</td>
    </tr>
    <tr></tr>

<TR>
    <td></td>
    <th>Numero Socio</th>
    <th>DNI</th>
    <th>Nombre</th>
    <th>Apellidos</th>
    <th>Antiguedad</th>
    <th>Direccion</th>
    <th>CP</th>
    <th>Poblacion</th>
    <th>Provincia</th>
    <th>Telefono1</th>
    <th>Telefono2</th>
    <th>Fax</th>

</TR>

        <%
            ArrayList<Socio> listaSocios = (ArrayList<Socio>)session.getAttribute("listaSocios");
            if (listaSocios != null)
            {
                String strTelefono1="";
                String strTelefono2="";
                String strFax = "";

                for (int i = 0; i<listaSocios.size(); i++)
                {

                    if (listaSocios.get(i).getTelefono1()!=null) strTelefono1 = listaSocios.get(i).getTelefono1();
                    if (listaSocios.get(i).getTelefono2()!=null) strTelefono2 = listaSocios.get(i).getTelefono2();
                    if (listaSocios.get(i).getFax() != null) strFax = listaSocios.get(i).getFax();
        %>

<TR>
   <td></td>
   <td><%=listaSocios.get(i).getNumSocio()%></td>
   <td><%=listaSocios.get(i).getDni()%></td>
   <td><%=listaSocios.get(i).getNombre()%></td>
   <td><%=listaSocios.get(i).getApellidos()%></td>
   <td><%=listaSocios.get(i).getAntiguedad()%></td>
   <td><%=listaSocios.get(i).getDireccion()%></td>
   <td><%=listaSocios.get(i).getCp()%></td>
   <td><%=listaSocios.get(i).getPoblacion()%></td>
   <td><%=listaSocios.get(i).getProvincia()%></td>
   <td><%=strTelefono1%></td>
   <td><%=strTelefono2%></td>
   <td><%=strFax%></td>

</TR>

        <%
            }
          }
       %>

</TABLE>

</body>

</html>
