<%--  
    Document   : ventaGestionProductos
    Created on : 17-jun-2011, 12:13:39
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.Producto"%>
<%@page import="com.Negocio.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Intranet de Gestión de Clubes</title>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

		<link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-blue2.css" title="blue2" />
        <!-- main calendar program -->
        <script type="text/javascript" src="jscalendar/calendar.js"></script>
        <!-- language for the calendar -->
        <script type="text/javascript" src="jscalendar/lang/calendar-es.js"></script>
        <!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
        <script type="text/javascript" src="jscalendar/calendar-setup.js"></script>

        <!--script para el arbol-->

<script  src="js/dhtmlxcommon.js"></script>
<script  src="js/dhtmlxtree.js"></script>
<script type="text/javascript" src="js/gestionCategorias.js"></script>

        <!--fin de script para el arbol-->


<script type="text/javascript">
    $(document).ready(function() {
        if($('#menorcheckbox').is(':checked')){
            $('div.menor').show();

     }else{
            $('div.menor').hide();
        }

	$(".msg_body").hide();
	$(".msg_head").click(function(){
		$(this).next(".msg_body").slideToggle(350);
	});

	$(".avanzada_body").hide();
	$(".avanzada").click(function(){
		$(this).next(".avanzada_body").slideToggle(350);
	});

    });

    $(document).ready(function() {
        if($('#menorcheckboxdos').is(':checked')){
            $('div.menoresultados').show();

        }else{
            $('div.menoresultados').hide();
        }
    });
	function cambiarEstado(){
        if($('#menorcheckbox').is(':checked')){
            $('div.menor').show('slow');
        }else{
            $('div.menor').hide('slow');
        }
    }
	function cambiarEstado2(){
        if($('#menorcheckboxdos').is(':checked')){
            $('div.menoresultados').show('slow');
        }else{
            $('div.menoresultados').hide('slow');
        }
    }
</script>
<script type="text/javascript">
    function validar()
    {
        miCampoTexto = document.getElementById("categoriaProducto").value;
        miCampoTexto2 = document.getElementById("subcategoriaProducto").value;
        miCampoTexto3 = document.getElementById("producto").value;
        miCampoTexto4 = document.getElementById("precio").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0 || miCampoTexto4.length == 0)
        {
            alert("Debes rellenar todos los campos");
            return false;
        }
        else
        {
            if(isNaN(miCampoTexto4))
            {
                alert("El valor del campo Precio debe ser numérico");
                return false;
            }
            else
            {
                return true;
            }
            
        }
    }
    function validarBaja()
    {
        miCampoTexto = document.getElementById("categoriaProducto").value;
        miCampoTexto2 = document.getElementById("subcategoriaProducto").value;
        miCampoTexto3 = document.getElementById("producto").value;
        miCampoTexto4 = document.getElementById("precio").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0 || miCampoTexto4.length == 0)
        {
            alert("Debes seleccionar un producto para dar de baja");
            return false;
        }
        else
        {
            return true;

        }
    }
</script>


</head>

<body>
<div id="contenedor">
	<div class="cabecera"><img src="imagenes/cabecera.gif" width="1024" height="95" alt="Intranet de Gestión de Clubes" /></div>

        <%
            Usuario unUsuario = (Usuario)session.getAttribute("usuario");
            String nombreUsuario = unUsuario.getNombre();
         %>

         <div id="usu" align="right">Usuario: &nbsp;&nbsp;<%=nombreUsuario%></div>

	<div id="menu">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    	  <td class="tdmenu"><a href="SociosServlet">Socios</a></td>
    	  <td class="tdmenu"><a href="IInstalacionesServlet">Instalaciones</a></td>
    	  <td class="tdmenu"><a href="reservaCalendarioServlet">Reservas</a></td>
          <td class="tdmenu"><a href="cursosServlet">Cursos</a></td>
    	  <td class="tdmenu"><a href="invitacionServlet">Invitaciones</a></td>
    	  <td class="tdmenu"><a href="convUsuConvenioServlet">Convenios & Usuario Extenos</a></td>
    	  <td class="tdmenu"><a href="visitaServlet">Visitas</a></td>
    	  <!--<td class="tdmenu"><a href="bonos.html">Bonos Deportivos</a></td>-->
    	  <td class="tdmenu"><a href="menPanelServlet">Mensajes</a></td>
    	  <td class="tdmenuSelec">Venta Material</td>
    	  <td class="tdmenu"><a href="confSocioServlet">Configuración</a></td>
    	</tr>
    </table>
</div>
<div id="submenu"><a href="ventaGestVentaServlet">Gestión de Venta de Material</a> &nbsp;| &nbsp;<a href="ventaGestCategoriaServlet">Gestión Categorías de Productos</a>&nbsp; | &nbsp;Gestión de Productos</div>

    <%
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>)session.getAttribute("listaCategorias");
    %>
	<div id="contenido">


      <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Buscador de Productos</div>

      <div class="listado">
    <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
            <td class="titulos_listado">Categoria</td>
            <td class="titulos_listado">Subcategoria</td>
        </tr>

         <%
            if (listaCategorias != null)
           {

                for (int i = 0; i<listaCategorias.size(); i++)
                {

        %>
                <tr>
                    <td class="texto_listado"><a href="ventaGestProductoServlet?var=<%=listaCategorias.get(i).getIdCategoria()%>"><%=listaCategorias.get(i).getCategoria()%></a></td>
                    <td class="texto_listado"><a href="ventaGestProductoServlet?var=<%=listaCategorias.get(i).getIdCategoria()%>"><%=listaCategorias.get(i).getSubcategoria()%></a></td>
                </tr>
        <%
                }
           }
        %>

	<tr>
          <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
	</tr>


    </table>
    </div>

       <%
            Categoria unaCategoria = (Categoria)session.getAttribute("unaCategoria");
            String idCategoria = "";
            String categoria = "";
            String subcategoria = "";

            if (unaCategoria != null)
            {
                categoria = unaCategoria.getCategoria();
                subcategoria = unaCategoria.getSubcategoria();
                idCategoria = String.valueOf(unaCategoria.getIdCategoria());

            }
     %>


     <form autocomplete="off" action="productoBusquedaServlet" method="post">
      <div class="formulario">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Por Producto:</td>
 	  <td  class="celda_campo"><label>
 	    <input name="nombre" type="text" class="campo_texto" id="nombre" size="40" />
 	    </label></td>
 	  <td class="texto_formu">&nbsp;</td>
 	  <td class="celda_campo">&nbsp;</td>
 	  </tr>
 	<tr>
 	  <td class="texto_formu">Por Categoría:</td>
 	  <td  class="celda_campo"><label>
                  <input name="categoria" type="text" class="campo_texto" id="categoria" size="40" value="<%=categoria%>" />
 	    </label></td>
          <td class="texto_formu">Por Subcategoría:</td> 
 	  <td  class="celda_campo"><label>
 	    <input name="subcategoria" type="text" class="campo_texto" id="subcategoria" size="40" value="<%=subcategoria%>" />
 	    </label></td>
 	 </tr>

        </table>
      </div>
     
      <div class="capas_botones">
	<input name="buscar" type="submit" class="boton" id="buscar" value="Buscar" />
	</div>
        </form>


      <div class="listado">
	    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	 <td class="titulos_listado">Categoría de Producto</td>
   	 <td class="titulos_listado">SubCategoría de Producto</td>
   	 <td class="titulos_listado">Producto</td> 
   	 <td class="titulos_listado">Precio</td> 
   	 </tr>

          <%
            ArrayList<Producto> listaProducto= (ArrayList<Producto>)session.getAttribute("listaProductos");
            if (listaProducto != null)
            {
                
                for (int i = 0; i<listaProducto.size(); i++)
                {
            %>
                <tr>
                    <td class="texto_listado"><a href="ventaGestProductoServlet?varP=<%=listaProducto.get(i).getIdProducto()%>"><%=listaProducto.get(i).getIdCategoria().getCategoria()%></a></td>
                    <td class="texto_listado"><a href="ventaGestProductoServlet?varP=<%=listaProducto.get(i).getIdProducto()%>"><%=listaProducto.get(i).getIdCategoria().getSubcategoria()%></a></td>
                    <td class="texto_listado"><a href="ventaGestProductoServlet?varP=<%=listaProducto.get(i).getIdProducto()%>"><%=listaProducto.get(i).getProducto()%></a></td>
                    <td class="texto_listado"><a href="ventaGestProductoServlet?varP=<%=listaProducto.get(i).getIdProducto()%>"><%=listaProducto.get(i).getPrecio()%></a></td>
                </tr>
              <%
                }
              %>
                    <tr>
                        <td class="texto_listado">&nbsp;</td>
                        <td class="texto_listado">&nbsp;</td>
                        <td class="texto_listado">&nbsp;</td>
                        <td class="texto_listado">&nbsp;</td>
                    </tr>
               <%
                }
               else
                {
              %>
              <tr>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
              </tr>
              <%
                }
              %>
	
        </table>
	</div>


     <%
            Producto unProducto = (Producto)session.getAttribute("unProducto");
            String idProducto = "";
            String nombreProducto = "";
            String categoriaProducto = "";
            String subcategoriaProducto = "";
            String precioProducto = "";
            String idCategoriaProducto = "";

            if (unProducto != null)
            {
                idProducto = String.valueOf(unProducto.getIdProducto());
                nombreProducto = unProducto.getProducto();
                categoriaProducto = unProducto.getCategoria();
                subcategoriaProducto = unProducto.getSubcategoria();
                precioProducto = String.valueOf(unProducto.getPrecio());
                idCategoriaProducto = String.valueOf(unProducto.getIdCategoria().getIdCategoria());

            }
            session.setAttribute("listaProductos", null);
            session.setAttribute("listaProducto", null);
     %>


    <div class="titulos"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Ficha de Producto</div>
     <form autocomplete="off" action="productoMIBServlet" method="post">
    <div class="formulario">
	<table width="90%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Categoría:</td>
 	  <td  class="celda_campo"><label>
                  <input name="categoriaProducto" type="text" class="campo_texto" id="categoriaProducto" size="40" value="<%=categoriaProducto%>" />
 	  </label></td>
 	  <td class="texto_formu" width="15%">SubCategoria:</td>
 	  <td  class="celda_campo"><label>
                  <input name="subcategoriaProducto" type="text" class="campo_texto" id="subcategoriaProducto" size="40" value="<%=subcategoriaProducto%>" />
 	  </label></td>
 	  </tr>
 	<tr>
 	  <td class="texto_formu">Producto:</td>
          <td  class="celda_campo"><input name="producto" type="text" class="campo_texto" id="producto" size="40" value="<%=nombreProducto%>" /></td>
 	  <td class="texto_formu">Precio:</td>
          <td class="texto_formu"><input name="precio" type="text" class="campo_texto" id="precio" size="40" value="<%=precioProducto%>" /></td>
 	  </tr>
 	</table>
	</div>

      <%
        Boolean error = (Boolean)session.getAttribute("errorCategoriaProducto");
        String strResultado ="";
        if (error != null)
        {
            if (error == true)
            {
                strResultado = "No existe esa Categoria/SubCategoria";
            }
        }
        session.setAttribute("errorCategoriaProducto", null);
        session.setAttribute("listaProductos", null);
    %>

     <input type="hidden"  name="idProducto" value=<%=idProducto%> /><br/>


     <%
        String resultadoProducto = (String)session.getAttribute("resultadoProducto");
        if(resultadoProducto == null)
        {
               resultadoProducto = "";
        }
        session.setAttribute("resultadoProducto", null);
    %>

      <div class="capas_botones">
          <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
          <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
	<input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
        <span style="color:red"><%=strResultado%><%=resultadoProducto%></span>
	</div>
    </form>

</div>
<div style="clear:both;"></div>

</div>
</body>
</html>


