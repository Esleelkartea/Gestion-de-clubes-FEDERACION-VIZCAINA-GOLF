<%--
    Document   : eReservaAlta
    Created on : 30-jun-2011, 8:56:42
    Author     : Julen
--%>

<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.Negocio.Profesor"%>
<%@page import="com.Negocio.InstalacionHorario"%>
<%@page import="com.Negocio.Reserva"%>
<%@page import="com.complementos.Buscador"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.Negocio.TipoPago"%>
<%@page import="com.Negocio.TipoReserva"%>
<%@page import="com.Negocio.Instalacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Negocio.Usuario"%>
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

<script type="text/javascript">
   

   
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
    	  <td class="tdmenuSelec">RESERVAR</td>
    	  <td class="tdmenu"><a href="eSocioServlet"> DATOS SOCIO</a></td>
    	  <td class="tdmenu"><a href="eMenPanelServlet">PANEL DE MENSAJES</a></td>
    	</tr>
    </table>
  </div>

  <div id="submenu"><a href="eReservaAltaServlet">Alta de Reservas</a> &nbsp;|  &nbsp;<a href="eReservaGestionServlet">Listado de Reservas</a>&nbsp;|&nbsp;Reservas de Profesor&nbsp;|&nbsp;<a href="eReservaGestionProfesorServlet">Listado de Reservas de Profesor</a>&nbsp;|&nbsp;<a href="eCursosInscritosServlet">Alta de Cursos</a>&nbsp;|&nbsp;<a href="eReservaGestionCursoServlet">Listado de Cursos</a></div>
	<div id="contenido">
  <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Alta de Reservas de Profesor</div>

   <%
       
       ArrayList<TipoInstalacion> listaTipoInstalacion = (ArrayList<TipoInstalacion>)session.getAttribute("listaTipoInstalacion");
  %>

  <div class="marco">

   <form autocomplete="off" method="post">

    <div class="formulario">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">

          
        <tr>
              <td class="texto_formu">Actividad:</td>
  	  <td class="celda_campo">


             <%
                if (listaTipoInstalacion != null)
                {

                    for(int i = 0; i<listaTipoInstalacion.size();i++)
                    {
             %>
             <a style="color: #008AFF" href="eReservaAltaActServlet?varActividad=<%=listaTipoInstalacion.get(i).getIdTipoInstalacion()%>"><%=listaTipoInstalacion.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                    }
                }
            %>
              </td>
          </tr>
    

  	</table>
	</div>


    </form>



    </div>

</div>
<div style="clear:both;"></div>


</div>
</body>
</html>
