<%-- 
    Document   : eReservaAlta
    Created on : 30-jun-2011, 8:56:42
    Author     : Julen
--%>

<%@page import="com.Negocio.TipoCurso"%>
<%@page import="com.Negocio.TipoInstalacion"%>
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
    function validar()
    {
        campoBusqueda = document.getElementById("busqueda").value;
        if(campoBusqueda == "")
        {
            alert("Debes realizar primero la busqueda");
            return false;
        }
        else
        {
        miCampoTexto3 = document.getElementById("f_date_t").value;
        miCampoTexto4 = document.getElementById("f_date_d").value;
        if(miCampoTexto3.length==0 || miCampoTexto4.length==0)
        {
            alert("Debes rellenar los campos de Fechas");
            return false;
        }
        else
        {
            miCampoTexto4 = document.getElementById("horaInicio").value;
            miCampoTexto5 = document.getElementById("horaFin").value;
            arrHoraInicio = miCampoTexto4.split(":");
            arrHoraFin = miCampoTexto5.split(":");
            miCampoTexto4 = arrHoraInicio[0];
            miCampoTexto5 = arrHoraFin[0];
            horaInicio = parseInt(miCampoTexto4);
            horaFin = parseInt(miCampoTexto5);
            if (horaInicio >= horaFin)
            {
                alert("La Hora Fin debe ser mayor que Hora Inicio")
                return  false;
            }
            else
            {
                return true;
            }
        }
        }
    }

    function validarBusqueda()
    {
        miCampoTexto1 = document.getElementById("f_date_t").value;
        miCampoTexto2 = document.getElementById("f_date_d").value;
        if (miCampoTexto1.length==0 || miCampoTexto2.length==0)
        {
            alert("Debes introducir las fechas para la reserva");
            return false;
        }
        else
        {
            return true;
        }
    }

    function validarAlta()
    {
        miCampoTexto1 = document.getElementById("f_date_f").value;
        if (miCampoTexto1.length==0)
        {
            alert("Debes introducir una fecha para realizar la reserva");
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

  <div id="submenu"><a href="eReservaAltaServlet">Alta de Reservas</a> &nbsp;|  &nbsp;<a href="eReservaGestionServlet">Listado de Reservas</a>&nbsp;|&nbsp;<a href="eReservaProfesorServlet">Reservas de Profesor</a>&nbsp;|&nbsp;<a href="eReservaGestionProfesorServlet">Listado de Reservas de Profesor</a>&nbsp;|&nbsp;Alta en Cursos &nbsp;|&nbsp;<a href="eReservaGestionCursoServlet">Listado de Cursos</a></div>
	
  <%
            ArrayList<TipoCurso> listaTipoCurso = (ArrayList<TipoCurso>)session.getAttribute("listaTipoCurso");
        %>

	<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Tipos de Cursos</div>
	 
     <div class="marco">

   <form autocomplete="off" method="post">

    <div class="formulario">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">

          <tr>
              <td class="texto_formu">Tipo Curso:</td>
  	  <td class="celda_campo">


             <%
                if (listaTipoCurso != null)
                {

                    for(int i = 0; i<listaTipoCurso.size();i++)
                    {
             %>
             <a style="color: #008AFF; font-size: 12" href="eCursoTipoCursoServlet?varTipoCurso=<%=listaTipoCurso.get(i).getIdTipoCurso()%>"><%=listaTipoCurso.get(i).getNombre()%></a>
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
