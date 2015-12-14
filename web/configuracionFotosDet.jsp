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
<script language="JavaScript">
function validar() {

     miCampoTexto = document.getElementById("nombre").value;
     miCampoTexto2 = document.getElementById("comentario").value;
     miCampoTexto3 = document.getElementById("fichero").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0)
     {
        alert("Debes rellenar todos los campos");
        return false;
     }
     else
     {
         return true;
     }
}
function validar2() {

     miCampoTexto = document.getElementById("nombre").value;
     miCampoTexto2 = document.getElementById("comentario").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
     {
        alert("Debes rellenar todos los campos");
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
    	  <td class="tdmenu"><a href="ventaGestVentaServlet">Venta Material</a></td>
    	  <td class="tdmenuSelec">Configuración</td>
    	</tr>
    </table>
</div>
         <div id="submenu"><a href="confSocioServlet">Gestión de Socios</a> &nbsp;| &nbsp;<a href="confReservaServlet">Gestión de Reservas</a> &nbsp;| &nbsp;<a href="confInvitacionServlet">Gestión de Invitaciones</a>&nbsp; |&nbsp; <a href="confPagoServlet">Gestión de Modos de Pago</a>&nbsp; |&nbsp;<a href="confProfesorServlet">Gestión de Profesores</a>&nbsp; |&nbsp;<a href="confArchivoServlet">Gestión de Archivos</a>&nbsp; |&nbsp;<a href="confUsuarioServlet">Gestión de Usuarios</a></div>

    <%
            ArrayList<Foto> listaFotos = (ArrayList<Foto>)session.getAttribute("listaFotos");
          
    %>


    <div id="contenido">

    <div class="marco">
	<div class="msg_head"><img src="imagenes/page_white_edit.png"/>Gestión de Fotos</div>

    <div class="msg_body">
    
 
   <form enctype="multipart/form-data" autocomplete="off" action="confFotoMIBServlet" method="post" >

       <%
            Foto unaFoto = (Foto)session.getAttribute("fotoDetalle");

            if (unaFoto == null)
            {
       %>

       <div class="formulario">
           <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="texto_formu" width="15%">Nombre:</td>
                <td width="34%" class="celda_campo">
		<label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" /></label></td>
            </tr>
            <tr>
                <td class="texto_formu" width="15%">Comentario:</td>
                <td width="34%" class="celda_campo">
		<label><input name="comentario" type="text" class="campo_texto" id="comentario" size="40" /></label></td>
            </tr>
            <tr>
                <td class="texto_formu" width="15%">URL:</td>
                <td width="34%" class="celda_campo">
		<label><input name="fichero" type="file" class="boton" id="fichero" size="100" /></label></td>
            </tr>

           </table>
       </div>

     <div class="capas_botones">
    	<input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
       
     </div>

            <%
            }
            else
            {
             %>

            <%-- Modificar y borrar --%>

          <div class="formulario">
           <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                <td class="texto_formu" width="15%">Nombre:</td>
                <td width="34%" class="celda_campo">
                    <label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" value="<%=unaFoto.getNombre()%>" /></label></td>
            </tr>
            <tr>
                <td class="texto_formu" width="15%">Comentario:</td>
                <td width="34%" class="celda_campo">
                    <label><input name="comentario" type="text" class="campo_texto" id="comentario" size="40" value="<%=unaFoto.getComentario()%>" /></label></td>
            </tr>
           </table>
          </div>

        <div class="capas_botones">
            <input name="enviar" type="submit" class="boton" id="alta" value="Modificar" onclick="return validar2()" />
            <input name="enviar" type="submit" class="boton" id="alta" value="Eliminar" />
        </div>

             <%
             }
             %>

     </form>
    </div>
    </div>


        
  </div>
<div style="clear:both;"></div>

</div>
</body>
</html>

