<%-- 
    Document   : reserva
    Created on : 28-jun-2011, 15:51:14
    Author     : Julen
--%>

<%@page import="com.Negocio.TipoPago"%>
<%@page import="com.Negocio.TipoReserva"%>
<%@page import="com.Negocio.Curso"%>
<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.complementos.Buscador"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="com.Negocio.InstalacionHorario"%>
<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.Instalacion"%>
<%@page import="com.Negocio.Reserva"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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
        miCampoTexto = document.getElementById("f_date_q").value;
        miCampoTexto2 = document.getElementById("f_date_d").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
        {
            alert("Debes rellenar los campos de las fechas");
            return false;
        }
        else
        {
            var xMes=miCampoTexto.substring(3, 5);
            var xDia=miCampoTexto.substring(0, 2);
            var xAno=miCampoTexto.substring(6,10);
            var yMes=miCampoTexto2.substring(3, 5);
            var yDia=miCampoTexto2.substring(0, 2);
            var yAno=miCampoTexto2.substring(6,10);

            if(xAno > yAno)
            {
                alert("La fecha final debe ser posterior a la inicial");
                return false;
            }
            else
            {
                if (xAno == yAno)
                {
                    if (xMes > yMes)
                    {
                        alert("La fecha final debe ser posterior a la inicial");
                        return false;
                    }
                    else
                    {
                        if (xMes == yMes)
                        {
                            if (xDia > yDia)
                            {
                                alert("La fecha final debe ser posterior a la inicial");
                                return false;
                            }
                            else
                            {
                                return true;
                            }
                        }
                        else
                        {
                            return true;
                        }
                    }
                }
                else
                {
                    return true;
                }
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

<div id="submenu"><a href="reservaGestionServlet">Gestión de Reservas</a> &nbsp;|  Alta de Reservas |<a href="reservaCalendarioServlet"> Calendario de Reservas</a>&nbsp;|&nbsp;<a href="reservaProfesorServlet">Reservas de Profesor</a>&nbsp;|&nbsp;<a href="reservaGestionProfesorServlet">Gestión de Reservas de Profesor</a></div>

<div id="contenido">
  <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Alta de Reservas por Instalación</div>

  <%
        ArrayList<TipoInstalacion> listaTipoInstalaciones = (ArrayList<TipoInstalacion>)session.getAttribute("listaTipoInstalaciones");
        ArrayList<Instalacion> listaInstalaciones = (ArrayList<Instalacion>)session.getAttribute("listaInstalaciones");
  %>
  <form autocomplete="off" action="ReservaCalendarioBusquedaServlet" method="post">
   
    <div class="formulario">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">

         <tr>
              <td class="texto_formu">Tipo Instalación:</td>
  	  <td class="celda_campo">


             <%
                if (listaTipoInstalaciones != null)
                {
                    String idTipoInstalacion = (String)session.getAttribute("tipoInstalacion");
                    for(int i = 0; i<listaTipoInstalaciones.size();i++)
                    {
                        if(listaTipoInstalaciones.get(i).getIdTipoInstalacion() == Integer.parseInt(idTipoInstalacion))
                        {
             %>
             <a style="color:red " href="reservaAlta1Servlet?varTipoInstalacion=<%=listaTipoInstalaciones.get(i).getIdTipoInstalacion()%>"><%=listaTipoInstalaciones.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                        }
                        else
                        {
            %>

             <a style="color: #008AFF" href="reservaAlta1Servlet?varTipoInstalacion=<%=listaTipoInstalaciones.get(i).getIdTipoInstalacion()%>"><%=listaTipoInstalaciones.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                        }
                    }
                }
            %>
              </td>
          </tr>

            <tr>
              <td class="texto_formu">Instalación:</td>
  	  <td class="celda_campo">


             <%
                if (listaInstalaciones != null && listaInstalaciones.size()!=0)
                {
                    String idInstalacion = (String)session.getAttribute("instalacion");
                    for(int i = 0; i<listaInstalaciones.size();i++)
                    {
                        if(listaInstalaciones.get(i).getIdInstalacion() == Integer.parseInt(idInstalacion))
                        {
             %>
             <a style="color: red" href="reservaAlta2Servlet?varInstalacion=<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getNombre()%></a>
             &nbsp;&nbsp;
              <%
                        }
                        else
                        {
            %>
              <a style="color: #008AFF" href="reservaAlta2Servlet?varInstalacion=<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getNombre()%></a>
             &nbsp;&nbsp;

            <%
                        }

                    }
                }
                else
                {
            %>
                <span style="color: red" >No hay instalaciones</span>
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
                Instalacion unaInstalacion = (Instalacion)session.getAttribute("unaInstalacion");
               

            %>

          <form autocomplete="off" action="reservaAltaMIB2Servlet" method="post">
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
            <input type="hidden" name="instalacion" id="instalacion" value="<%=unaInstalacion.getIdInstalacion()%>"
        <div class="capas_botones">
            <input name="enviar" type="submit" class="boton" id="alta" value="Reservar" onclick="return validar()" />
   	</div>
    </form>


    



             
 
 

    

   
       


</div>
<div style="clear:both;"></div>


</div>
</body>
</html>

