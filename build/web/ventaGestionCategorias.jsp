<%-- 
    Document   : ventaGestionCategorias
    Created on : 17-jun-2011, 10:06:05
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
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
        miCampoTexto = document.getElementById("categoria").value;
        miCampoTexto2 = document.getElementById("subcategoria").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
        {
            alert("Debes rellenar los campos Nombre Categoria y Nombre SubCategoria");
            return false;
        }
        else
        {
            return true;
        }

    }

    function validarBaja()
    {
        miCampoTexto = document.getElementById("categoria").value;
        miCampoTexto2 = document.getElementById("subcategoria").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
        {
            alert("Debes seleccionar una Categoria / SubCategoria para dar de baja");
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
<div id="submenu"><a href="ventaGestVentaServlet">Gestión de Venta de Material</a> &nbsp;| &nbsp;Gestión de Categorías de Productos &nbsp;| &nbsp;<a href="ventaGestProductoServlet">Gestión de Productos</a></div>

    <%
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>)session.getAttribute("listaCategorias");
    %>

    <div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Categorías de Productos</div>
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
                    <td class="texto_listado"><a href="ventaGestCategoriaServlet?var=<%=listaCategorias.get(i).getIdCategoria()%>"><%=listaCategorias.get(i).getCategoria()%></a></td>
                    <td class="texto_listado"><a href="ventaGestCategoriaServlet?var=<%=listaCategorias.get(i).getIdCategoria()%>"><%=listaCategorias.get(i).getSubcategoria()%></a></td>
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



    <form autocomplete="off" action="ventaGestCategoriaMIBServlet" method="post">
    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Nombre Categoría:</td>
 	  <td  class="celda_campo"><label>
                  <input name="categoria" type="text" class="campo_texto" id="categoria" size="40" value="<%=categoria%>" />
 	  </label></td>
 	  <td class="texto_formu">&nbsp;</td>
 	  </tr>
 	<tr>
 	  <td class="texto_formu">Nombre Subcategoría:</td>
 	  <td  class="celda_campo"><input name="subcategoria" type="text" class="campo_texto" id="subcategoria" size="40" value="<%=subcategoria%>" /></td>
 	  <td class="texto_formu">&nbsp;</td>
 	  </tr>
 	</table>
	</div>

        <input type="hidden"  name="categoriaId" value="<%=idCategoria%>" /><br/>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
	<input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
	</div>

</form>

</div>
<div style="clear:both;"></div>

</div>
</body>
</html>

