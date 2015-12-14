<%-- 
    Document   : eMenPanel
    Created on : 15-jun-2011, 21:44:47
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.Negocio.Mensaje"%>
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

</head>

<body>
<div id="contenedor">
	<div class="cabecera"><img src="imagenes/cabeceraExtranet.gif" width="1024" height="84" alt="Intranet de Gestión de Clubes" /></div>

        <%
            Socio unSocio2 = (Socio)session.getAttribute("socioExtranet");
            String nombreSocio = unSocio2.getNombre();
            Usuario unUsuario = (Usuario)session.getAttribute("usuario");
            String idUsuario = String.valueOf(unUsuario.getIdUsuario());
            String nombreUsuario = unUsuario.getNombre();
         %>

         <div id="usu" align="right">Hola &nbsp;&nbsp;<%=nombreSocio%></div>

	<div id="menu">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    	  <td class="tdmenu"><a href="eReservaGestionServlet">RESERVAR</a></td>
    	  <td class="tdmenu"><a href="eSocioServlet"> DATOS SOCIO</a></td>
    	  <td class="tdmenuSelec">PANEL DE MENSAJES</td>
    	</tr>
    </table>
  </div>

  	<div id="submenu">Panel de Entrada&nbsp;| &nbsp;<a href="eMenAltaServlet">Nuevo Mensaje</a></div>

  <%
            ArrayList<Mensaje> listaMensajes= (ArrayList<Mensaje>)session.getAttribute("listaMensajes");
  %>

  <div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" />Panel de Entrada de Mensajes</div>
	<div class="listado">
	<table width="97%" border="0" cellspacing="0" cellpadding="0">

          <% if (listaMensajes != null && listaMensajes.size()!=0)
           {
          %>
                <tr>
                    <td class="titulos_listadoMens" width="8%"><img src="imagenes/calendar.png" width="16" height="16" /> Fecha</td>
                    <td class="titulos_listadoMens" width="17%"><img src="imagenes/status_offline.png" width="16" height="16" /> Usuario</td>
                    <td class="titulos_listadoMens" width="65%"><img src="imagenes/page_white_text.png" width="16" height="16" /> Mensaje</td>
                    <td class="titulos_listadoMens" width="10%"></td>
                </tr>
          <%
                for (int i = 0; i<listaMensajes.size(); i++)
                {
        %>
             <form action="eMenPanelMIBServlet?var=<%=listaMensajes.get(i).getIdMensaje()%>" method="post">
                <tr>
                    <td class="texto_listadoMs"><%=listaMensajes.get(i).getFecha()%></td>
                    <td class="texto_listadoMs"><%=listaMensajes.get(i).getUsuEmisor().getNombre()%></td>
                    <td class="texto_listadoMs"><%=listaMensajes.get(i).getMensaje() %></td>
                    <td><div class="capas_botones"><input name="borrar" type="submit" class="boton2" value="Borrar" /></div></td>
                </tr>
             </form>
        <%
                }
           }
           else
           {
        %>
                 <tr>
                    <td class="texto_listadoMs">&nbsp;</td>
                    <td class="texto_listadoMs">&nbsp;</td>
                    <td class="texto_listadoMs">&nbsp;</td>
                </tr>

                <span style="color:red">No tienes mensajes</span>
        <%
        }
        %>

	</table>
	</div>
  </div>
	<div style="clear:both;"></div>

</div>
</body>
</html>