<%-- 
    Document   : configuracionSocios
    Created on : 08-jun-2011, 11:24:09
    Author     : Julen
--%>

<%@page import="com.Negocio.Archivo"%>
<%@page import="com.Negocio.Foto"%>
<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.TipoFamiliar"%>
<%@page import="com.Negocio.TipoSocio"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Intranet de Gestión de Clubes</title>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />

		<link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-blue2.css" title="blue2" />
        <!-- main calendar program -->
        <script type="text/javascript" src="jscalendar/calendar.js"></script>
        <!-- language for the calendar -->
        <script type="text/javascript" src="jscalendar/lang/calendar-es.js"></script>
        <!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
        <script type="text/javascript" src="jscalendar/calendar-setup.js"></script>

<script type="text/javascript" src="js/jquery.js"></script>
<!--
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

	$(".msg_formulario").hide();
	$(".msg_titulos").click(function(){
		$(this).next(".msg_formulario").slideToggle(350);
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
-->

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
    	  <td class="tdmenu"><a href="ventaGestVentaServlet">Venta Material</a></td>
    	  <td class="tdmenuSelec">Configuración</td>
    	</tr>
    </table>
</div>
         <div id="submenu"><a href="confSocioServlet">Gestión de Socios</a> &nbsp;| &nbsp;<a href="confReservaServlet">Gestión de Reservas</a> &nbsp;| &nbsp;<a href="confInvitacionServlet">Gestión de Invitaciones</a>&nbsp; |&nbsp; <a href="confPagoServlet">Gestión de Modos de Pago</a>&nbsp; |&nbsp;<a href="confProfesorServlet">Gestión de Profesores</a>&nbsp; |&nbsp;Gestión de Archivos&nbsp; |&nbsp;<a href="confUsuarioServlet">Gestión de Usuarios</a></div>

    <%
            ArrayList<Foto> listaFotos = (ArrayList<Foto>)session.getAttribute("listaFotos");
            ArrayList<Archivo> listaArchivos = (ArrayList<Archivo>)session.getAttribute("listaArchivos");
    %>


    <div id="contenido">

    <div class="marco">
	<div class="msg_head"><img src="imagenes/page_white_edit.png"/>Gestión de Fotos</div>

    <div class="msg_body">
    <div class="listado">
	<table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
            <td class="titulos_listado">Foto ID</td>
            <td class="titulos_listado">Nombre</td>
            <td class="titulos_listado">Comentario</td>
            <td class="titulos_listado">URL</td>
            <td class="titulos_listado">Foto</td>
   	</tr>


         <% if (listaFotos != null)
           {
                for (int i = 0; i<listaFotos.size(); i++)
                {
        %>
                <tr>
                    <td class="texto_listado"><a href="confFotoDetServlet?var=<%=listaFotos.get(i).getIdFoto()%>"><%=listaFotos.get(i).getIdFoto()%></a></td>
                    <td class="texto_listado"><a href="confFotoDetServlet?var=<%=listaFotos.get(i).getIdFoto()%>"><%=listaFotos.get(i).getNombre()%></a></td>
                    <td class="texto_listado"><a href="confFotoDetServlet?var=<%=listaFotos.get(i).getIdFoto()%>"><%=listaFotos.get(i).getComentario()%></a></td>
                    <td class="texto_listado"><a href="confFotoDetServlet?var=<%=listaFotos.get(i).getIdFoto()%>"><%=listaFotos.get(i).getUrl()%></a></td>
                    <td class="texto_listado"><img src="<%=listaFotos.get(i).getUrl()%>" height="30" width="30" align="middle" /></td>
                </tr>
        <%
                }
           }
        %>
           
	    </table>
	</div>

 <form autocomplete="off" action="confFotoDetServlet?var=nuevo" method="post">
    
     <div class="capas_botones">
    	<input name="enviar" type="submit" class="boton" id="alta" value="Alta" />
       
   	  </div>

     </form>
    </div>
    </div>


        <div class="marco">
	<div class="msg_head"><img src="imagenes/page_white_edit.png"/>Gestión de Archivos</div>

    <div class="msg_body">

     <div class="listado">
	    <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
            <tr>
                <td class="titulos_listado">Archivo ID</td>
                <td class="titulos_listado">Nombre</td>
                <td class="titulos_listado">URL</td>
            </tr>

             <%
             if (listaArchivos != null)
             {
                for (int i = 0; i<listaArchivos.size(); i++)
                {
            %>
                <tr>
                    <td class="texto_listado"><a href="confArchivoDetServlet?var=<%=listaArchivos.get(i).getIdArchivo()%>"><%=listaArchivos.get(i).getIdArchivo()%></a></td>
                    <td class="texto_listado"><a href="confArchivoDetServlet?var=<%=listaArchivos.get(i).getIdArchivo()%>"><%=listaArchivos.get(i).getNombre()%></a></td>
                    <td class="texto_listado"><a href="confArchivoDetServlet?var=<%=listaArchivos.get(i).getIdArchivo()%>"><%=listaArchivos.get(i).getUrl()%></a></td>
                </tr>
        <%
                }
           }

        %>
       
	</table>
	</div>
    

    <form autocomplete="off" action="confArchivoDetServlet?var=nuevo" method="post">
                
        <div class="capas_botones">
            <input name="enviar" type="submit" class="boton" id="alta" value="Alta" />
           
   	</div>
    </form>
    </div></div>
       
  </div>
<div style="clear:both;"></div>
        <%
               String rdo = (String)session.getAttribute("resultado");
               if (rdo!=null)
               {
        %>
                <h5 style="color:red"><%=rdo%></h5>
        <%
                }
        %>
</div>
</body>
</html>

