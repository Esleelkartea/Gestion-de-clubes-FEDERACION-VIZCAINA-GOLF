<%-- 
    Document   : configuracionPagos
    Created on : 08-jun-2011, 12:55:23
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.TipoPago"%>
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

<script language="JavaScript">
function validar() {
   
   miCampoTexto = document.getElementById("nombre").value;
   miCampoTexto2 =  document.getElementById("pasword").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
     {
        alert("Debes rellenar todos los campos");
        return false;
     }
     else
     {
         if(miCampoTexto2.length <8)
         {
             alert("La contraseña debe tener 8 ó más caracteres");
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
    miCampoTexto = document.getElementById("nombre").value;
    miCampoTexto2 =  document.getElementById("pasword").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
     {
        alert("Debes seleccionar un usuario para dar de baja");
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
<div id="submenu"><a href="confSocioServlet">Gestión de Socios</a> &nbsp;| &nbsp;<a href="confReservaServlet">Gestión de Reservas</a> &nbsp;| &nbsp;<a href="confInvitacionServlet">Gestión de Invitaciones</a> &nbsp;|&nbsp; <a href="confPagoServlet">Gestión de Modos de Pago</a>&nbsp; |&nbsp;<a href="confProfesorServlet">Gestión de Profesores</a>&nbsp; |&nbsp;<a href="confArchivoServlet">Gestión de Archivos</a>&nbsp; |&nbsp;Gestión de Usuarios</div>

     <%
            ArrayList<Usuario> listaUsuarios= (ArrayList<Usuario>)session.getAttribute("listaUsuarios");
     %>

    <div id="contenido">
	<div class="titulos2"><img src="imagenes/page_white_edit.png"/>Gestión Usuarios</div>

    <div class="listado">
      <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	 <td class="titulos_listado">Nombre Usuario</td>
         <td class="titulos_listado">Password</td>
   	 </tr>

     	<% if (listaUsuarios != null)
           {
                for (int i = 0; i<listaUsuarios.size(); i++)
                {
        %>
                <tr>
                    <td class="texto_listado"><a href="confUsuarioServlet?var=<%=listaUsuarios.get(i).getIdUsuario()%>"><%=listaUsuarios.get(i).getNombre()%></a></td>
                    <td class="texto_listado"><a href="confUsuarioServlet?var=<%=listaUsuarios.get(i).getIdUsuario()%>"><%=listaUsuarios.get(i).getPassword()%></a></td>
                </tr>
                
        <%
                }
           }
           else
           {
        %>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
        <%
        }
        %>
	</table>
	</div>

     <%
        Usuario otroUsuario = (Usuario)session.getAttribute("otroUsuario");
        String idUsuario = "";
        String nombre = "";
        String password = "";

        if (otroUsuario != null)
        {
            idUsuario = String.valueOf(otroUsuario.getIdUsuario());
            nombre = otroUsuario.getNombre();
            password = otroUsuario.getPassword();
        }
     %>

     <form autocomplete="off" action="confUsuarioMIBServlet" method="post">
    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Nombre Usuario:</td>
          <td width="30%"  class="celda_campo"><label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" value="<%=nombre%>" /></label></td>
 	  <td class="texto_formu" width="15%">Password:</td>
 	  <td width="42%" class="celda_campo"><label><input name="password" type="text" class="campo_texto" id="pasword" size="40" value="<%=password%>" /></label></td>
 	  </tr>
  	</table>
    </div>

          <input type="hidden"  name="usuarioId" value=<%=idUsuario%> /><br/>

    	<div class="capas_botones">
            <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
            <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
        <input name="enviar" type="submit" class="boton" id="modificacion" value="Modificacion" onclick="return validar()" />
   	  </div>
    </form>

  </div>
<div style="clear:both;"></div>

</div>
</body>
</html>
