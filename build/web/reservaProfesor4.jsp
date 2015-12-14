<%-- 
    Document   : reservaProfesor
    Created on : 09-sep-2011, 9:29:03
    Author     : Julen
--%>

<%@page import="com.Negocio.Curso"%>
<%@page import="com.Negocio.ProfesorHorario"%>
<%@page import="com.Negocio.ReservaProfesor"%>
<%@page import="com.complementos.Buscador"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.Negocio.Profesor"%>
<%@page import="com.Negocio.TipoPago"%>
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
        busqueda = document.getElementById("busqueda").value;
        if (busqueda=="false")
        {
            alert("Debes realizar la búsqueda del profesor");
            return false;
        }
        else
        {
            miCampoTexto = document.getElementById("nombre").value;
            miCampoTexto2 = document.getElementById("apellidos").value;
            miCampoTexto3 = document.getElementById("f_date_t").value;
            if(miCampoTexto.length==0 || miCampoTexto2.length==0 || miCampoTexto3.length==0)
            {
                alert("Debes rellenar todos los campos");
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
        if (miCampoTexto1.length==0)
        {
            alert("Debes introducir la fecha de la reserva");
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
</script>


</head>

<body>
<div id="contenedor">
	<div class="cabecera"><img src="imagenes/cabecera.gif" width="1024" height="95" alt="Intranet de Gestión de Clubes" /></div>

        <%
            Usuario unUsuario = (Usuario)session.getAttribute("usuario");
            String idUsuario = String.valueOf(unUsuario.getIdUsuario());
            String nombreUsuario = unUsuario.getNombre();
        %>

         <div id="usu" align="right">Usuario: &nbsp;&nbsp;<%=nombreUsuario%></div>

	<div id="menu">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    	  <td class="tdmenu"><a href="SociosServlet">Socios</a></td>
    	  <td class="tdmenu"><a href="IInstalacionesServlet">Instalaciones</a></td>
    	  <td class="tdmenuSelec">Reservas</td>
          <td class="tdmenu"><a href="cursosServlet">Cursos</a></td>
    	  <td class="tdmenu"><a href="invitacionServlet">Invitaciones</a></td>
    	  <td class="tdmenu"><a href="convUsuConvenioServlet">Convenios & Usuario Extenos</a></td>
    	  <td class="tdmenu"><a href="visitaServlet">Visitas</a></td>
    	  <!--<td class="tdmenu"><a href="bonos.html">Bonos Deportivos</a></td>-->
    	  <td class="tdmenu"><a href="menPanelServlet">Mensajes</a></td>
    	  <td class="tdmenu"><a href="ventaGestVentaServlet">Venta Material</a></td>
    	  <td class="tdmenu"><a href="confSocioServlet">Configuración</a></td>
    	</tr>
    </table>
</div>

<div id="submenu"><a href="reservaGestionServlet">Gestión de Reservas</a> &nbsp;|&nbsp;<a href="reservaAltaServlet">Alta de Reservas</a> &nbsp; |<a href="reservaCalendarioServlet"> Calendario de Reservas</a>&nbsp;|&nbsp; Reservas de Profesor&nbsp;|&nbsp;<a href="reservaGestionProfesorServlet">Gestión de Reservas de Profesor</a></div>

<div id="contenido">
  <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Alta de Reservas con Profesor</div>
  <%

       ArrayList<TipoInstalacion> listaTipoInstalacion = (ArrayList<TipoInstalacion>)session.getAttribute("listaTipoInstalacion");
       ArrayList<Profesor> listaProfesores = (ArrayList<Profesor>)session.getAttribute("listaProfesores");
  %>

   <div class="marco">

   <form autocomplete="off" action="reservaProfesorMIBServlet" method="post">

    <div class="formulario">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">


        <tr>
              <td class="texto_formu">Actividad:</td>
  	  <td class="celda_campo">


             <%
                if (listaTipoInstalacion != null)
                {
                    String idActividad = (String)session.getAttribute("idActividad");
                    for(int i = 0; i<listaTipoInstalacion.size();i++)
                    {
                        if(listaTipoInstalacion.get(i).getIdTipoInstalacion() == Integer.parseInt(idActividad))
                        {
             %>
             <a style="color:red " href="reservaProfesor1Servlet?varActividad=<%=listaTipoInstalacion.get(i).getIdTipoInstalacion()%>"><%=listaTipoInstalacion.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                        }
                        else
                        {
            %>

             <a style="color: #008AFF" href="reservaProfesor1Servlet?varActividad=<%=listaTipoInstalacion.get(i).getIdTipoInstalacion()%>"><%=listaTipoInstalacion.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                        }
                    }
                }
            %>
              </td>
          </tr>

           <tr>
              <td class="texto_formu">Profesor:</td>
  	  <td class="celda_campo">


             <%
                if (listaProfesores != null && listaProfesores.size()!=0)
                {
                    String idProfesor = (String)session.getAttribute("profesor");
                    for(int i = 0; i<listaProfesores.size();i++)
                    {
                        if(listaProfesores.get(i).getIdProfesor() == Integer.parseInt(idProfesor))
                        {
             %>
             <a style="color: red" href="reservaProfesor2Servlet?varProfesor=<%=listaProfesores.get(i).getIdProfesor()%>"><%=listaProfesores.get(i).getNombre()%></a>
             &nbsp;&nbsp;
             <%
                        }
                        else
                        {
            %>

             <a style="color: #008AFF" href="reservaProfesor2Servlet?varProfesor=<%=listaProfesores.get(i).getIdProfesor()%>"><%=listaProfesores.get(i).getNombre()%></a>
             &nbsp;&nbsp;


            <%
                        }
                    }
                }
                else
                {
            %>
                <span style="color: red" >No hay profesores para esa actividad</span>
            <%
                }
            %>
              </td>
          </tr>


  	</table>


</div>
  </form>
            <%
                ArrayList<TipoReserva> listaTipoReserva = (ArrayList<TipoReserva>)session.getAttribute("listaTipoReserva");
                ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");
                String dia = (String)session.getAttribute("varDia");
                String horaInicio = (String)session.getAttribute("horaInicio");
                String horaFin = (String)session.getAttribute("horaFin");
                Profesor unProfesor = (Profesor)session.getAttribute("unProfesor");
                
                String fecha[] = dia.split("-");
                dia = fecha[2] + "-" + fecha[1] + "-" + fecha[0];

                horaInicio += ":00";

                int idProfesor = unProfesor.getIdProfesor();

            %>


            <form autocomplete="off" action="reservaProfesorMIB2Servlet" method="post">
        <div class="formulario">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">

                <tr>
                    <td class="texto_formu" width="15%">Fecha:</td>
                    <td width="30%"  class="celda_campo"><label><input name="fecha" type="text" class="campo_texto" id="fecha" size="40" value="<%=dia%>" readonly /></label></td>
                </tr>
                <tr>
                    <td class="texto_formu" width="15%">Hora Inicio:</td>
                    <td width="30%"  class="celda_campo"><label><input name="horaInicio" type="text" class="campo_texto" id="horaInicio" size="40" value="<%=horaInicio%>" readonly /></label></td>
                    <td class="texto_formu" width="15%">Hora Fin:</td>
                    <td width="30%"  class="celda_campo"><label><input name="horaFin" type="text" class="campo_texto" id="horaFin" size="40" value="<%=horaFin%>" readonly /></label></td>

                </tr>


                <tr>
                    <td class="texto_formu" width="15%">Nombre Socio:</td>
                    <td width="30%"  class="celda_campo"><label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40"/></label></td>
                    <td class="texto_formu" width="15%">Apellidos Socio:</td>
                    <td width="30%"  class="celda_campo"><label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40"/></label></td>

                </tr>

                <tr>
  	  <td class="texto_formu">Estado:</td>
  	  <td class="celda_campo">
              <select name="estadoReserva" class="campo_texto" id="estadoReserva">
  	    <%
                if (listaTipoReserva != null)
                {
                    for(int i = 0; i<listaTipoReserva.size();i++)
                    {

            %>
                        <option name="estadoReserva" value="<%=listaTipoReserva.get(i).getIdTipoReserva()%>" ><%=listaTipoReserva.get(i).getEstado()%></option>
            <%

                    }
                }
            %>
  	  </select></td>
          <td class="texto_formu">Forma de pago:</td>
  	  <td class="celda_campo">
              <select name="formaPago" class="campo_texto" id="formaPago">
             <%
                if (listaTipoPago != null)
                {
                    for(int i = 0; i<listaTipoPago.size();i++)
                    {

            %>
                        <option name="formaPago" value="<%=listaTipoPago.get(i).getIdTipoPago()%>" ><%=listaTipoPago.get(i).getModo()%></option>
            <%
                    }
                }
            %>

            </select></td>
	  </tr>

            </table>
        </div>


        <%
            Boolean resultadoSocio = (Boolean)session.getAttribute("resultadoSocio");
            String strResultado ="";
            if (resultadoSocio != null)
            {
                if (resultadoSocio == false)
                {
                    strResultado= "El socio no existe. ";
                }
            }
            session.setAttribute("resultadoSocio", null);
        %>

        <div class="capas_botones">
            <input type="hidden" name="profesor" id="profesor" value="<%=idProfesor%>" />
            <input name="enviar" type="submit" class="boton" id="alta" value="Reservar" onclick="return validar()" />
            <span style="color:red"><%=strResultado%></span>
   	</div>
    </form>
   

    <div class="leyenda">
    <img src="imagenes/rojo.gif"/> &nbsp;Reservado&nbsp;&nbsp;&nbsp;<img src="imagenes/verde.gif"/> &nbsp;Cursos&nbsp;&nbsp;&nbsp; <img src="imagenes/azul.gif"/> &nbsp;Profesor no Disponible
    </div>

    </div>

</div>
<div style="clear:both;"></div>


</div>
</body>
</html>
