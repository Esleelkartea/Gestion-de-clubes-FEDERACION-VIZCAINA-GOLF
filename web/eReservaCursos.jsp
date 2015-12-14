<%-- 
    Document   : eReserva
    Created on : 30-jun-2011, 9:36:12
    Author     : Julen
--%>

<%@page import="com.Negocio.Curso"%>
<%@page import="com.dal.CursoDAL"%>
<%@page import="com.Negocio.CursoInscrito"%>
<%@page import="com.Negocio.Profesor"%>
<%@page import="com.dal.ProfesorDAL"%>
<%@page import="com.Negocio.ReservaProfesor"%>
<%@page import="com.dal.TipoPagoDAL"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.dal.TipoInstalacionDAL"%>
<%@page import="com.dal.TipoReservaDAL"%>
<%@page import="com.dal.SocioDAL"%>
<%@page import="com.dal.InstalacionDAL"%>
<%@page import="com.Negocio.Reserva"%>
<%@page import="com.Negocio.TipoPago"%>
<%@page import="com.Negocio.Instalacion"%>
<%@page import="com.Negocio.TipoReserva"%>
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

  <div id="submenu"><a href="eReservaAltaServlet">Alta de Reservas</a> &nbsp;|  &nbsp;<a href="eReservaGestionServlet">Listado de Reservas</a>&nbsp;|&nbsp;<a href="eReservaProfesorServlet">Reservas de Profesor</a>&nbsp;|&nbsp; <a href="eReservaGestionProfesorServlet">Listado de Reservas de Profesor</a>&nbsp;|&nbsp;<a href="eCursosInscritosServlet">Alta de Cursos</a>&nbsp;|&nbsp; Listado de Cursos &nbsp;</div>
	<div id="contenido">


   	  <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Cursos Inscritos no finalizados</div>
          <br /><br />
    <%
        ArrayList<CursoInscrito> listaCursoInscritos = (ArrayList<CursoInscrito>)session.getAttribute("listaCursoInscritos");
        
    %>
    
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	

        <%
            if (listaCursoInscritos != null && listaCursoInscritos.size() != 0)
            {
          %>
          <tr>
              <td class="titulos_listado">Curso</td>
              <td class="titulos_listado">Fecha Inicio</td>
              <td class="titulos_listado">Fecha Fin</td>
              <td class="titulos_listado">Hora Inicio</td>
              <td class="titulos_listado">Hora Fin</td>
              <td class="titulos_listado">Dias</td>
  	  </tr>
          
          <%
                String strFecha = "";
                String strFechaF = "";
                String nombre = "";
                String hInicio = "";
                String hFin = "";
                String dias = "";
                for (int i = 0; i<listaCursoInscritos.size(); i++)
                {
                    dias = "";
                    CursoDAL cursoDAL = new CursoDAL();
                    Curso unCurso = null;
                    unCurso = cursoDAL.getCurso(listaCursoInscritos.get(i).getIdCurso());

                    strFecha = String.valueOf(unCurso.getFechaInicio());
                    String[] arrFecha = strFecha.split("-");
                    strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                    strFechaF = String.valueOf(unCurso.getFechaFin());
                    String[] arrFechaF = strFechaF.split("-");
                    strFechaF = arrFechaF[2] + "-" + arrFechaF[1] + "-" + arrFechaF[0];
                    
                    nombre = unCurso.getCursoNombre();
                    hInicio = String.valueOf(unCurso.getHoraInicio());
                    hFin = String.valueOf(unCurso.getHoraFin());

                    if(unCurso.isLunes()) dias += " Lunes ";
                    if(unCurso.isMartes()) dias += " Martes ";
                    if(unCurso.isMiercoles()) dias += " Miercoles ";
                    if(unCurso.isJueves()) dias += " Jueves ";
                    if(unCurso.isViernes()) dias += " Viernes ";
                    if(unCurso.isSabado()) dias += " Sabado ";
                    if(unCurso.isDomingo()) dias += " Domingo ";
                    
        %>

                 <tr>
                    <td class="texto_listado"><%=nombre%></td>
                    <td class="texto_listado"><%=strFecha%></td>
                    <td class="texto_listado"><%=strFechaF%></td>
                    <td class="texto_listado"><%=hInicio%></td>
                    <td class="texto_listado"><%=hFin%></td>
                    <td class="texto_listado"><%=dias%></td>
                    
             
                </tr>

        <%
                }
        %>
        
         <%
           }
           else
           {
            %>

           <span style="color:red">Actualmente no tienes ningún curso</span>

         <%
            }
       %>


	</table>

</div>
<div style="clear:both;"></div>


</div>
</body>
</html>

