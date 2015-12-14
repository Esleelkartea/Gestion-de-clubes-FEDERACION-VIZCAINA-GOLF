<%--
    Document   : excelMaterial
    Created on : 02-ago-2011, 12:56:53
    Author     : Julen
--%>

<%@page import="com.Negocio.Venta"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

    response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.

response.setHeader ("Content-Disposition", "attachment;filename=\"ventas.xls\"");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Listado de Ventas</title>

</head>

<body>

    
<TABLE>

    <tr></tr>
    <tr>
        <td></td>
        <td style="color: red; font-size: 16pt"  >Listado de Ventas</td>
    </tr>
    <tr></tr>

<TR>
    <td></td>
    <th>Producto</th>
    <th>Categoria Producto</th>
    <th>SubCategoria Producto</th>
    <th>Nombre</th>
    <th>Apellidos</th>
    <th>Fecha de Venta</th>

</TR>

         <%
            ArrayList<Venta> listaVenta= (ArrayList<Venta>)session.getAttribute("listaVenta");
            if (listaVenta != null)
            {

                for (int i = 0; i<listaVenta.size(); i++)
                {
            %>


<TR>
   <td></td>
   <td><%=listaVenta.get(i).getNombreProducto()%></td>
   <td><%=listaVenta.get(i).getNombreCategoria()%></td>
   <td><%=listaVenta.get(i).getNombreSubcategoria()%></td>
   <td><%=listaVenta.get(i).getNombreSocio()%></td>
   <td><%=listaVenta.get(i).getApellidosSocio()%></td>
   <td align="center" ><%=listaVenta.get(i).getFecha()%></td>
</TR>


        <%
            }
          }
       %>


</TABLE>

</body>

</html>



