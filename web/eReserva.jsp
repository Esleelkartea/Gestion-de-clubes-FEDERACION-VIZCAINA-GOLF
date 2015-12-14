<%-- 
    Document   : eReserva
    Created on : 30-jun-2011, 9:36:12
    Author     : Julen
--%>

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
    function validar()
    {
        miCampoTexto3 = document.getElementById("f_date_t").value;
        if(miCampoTexto3.length==0)
        {
            alert("Debes rellenar el campo Fecha");
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

    function validarBaja()
    {
        idReserva = document.getElementById("idReserva").value;
        if(idReserva == "")
        {
            alert("Debes seleccionar una reserva para dar de baja");
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

         <div id="submenu"><a href="eReservaAltaServlet">Alta de Reservas</a> &nbsp;|  &nbsp;Listado de Reservas&nbsp;|&nbsp;<a href="eReservaProfesorServlet">Reservas de Profesor</a>&nbsp;|&nbsp;<a href="eReservaGestionProfesorServlet">Listado de Reservas de Profesor</a>&nbsp;|&nbsp;<a href="eCursosInscritosServlet">Alta de Cursos</a>&nbsp;|&nbsp;<a href="eReservaGestionCursoServlet">Listado de Cursos</a></div>
	<div id="contenido">


   	  <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Gestión de Reservas</div>
          <br /><br />
    <%
        ArrayList<Instalacion> listaInstalaciones = (ArrayList<Instalacion>)session.getAttribute("listaInstalaciones");
        ArrayList<TipoReserva> listaTipoReserva = (ArrayList<TipoReserva>)session.getAttribute("listaTipoReserva");
        ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");
        ArrayList<Reserva> listaReservas = (ArrayList<Reserva>)session.getAttribute("listaReservas");
    %>
    
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	  <td class="titulos_listado">Tipo instalación</td>
          <td class="titulos_listado">Instalación</td>
   	  <td class="titulos_listado">Fecha</td>
   	  <td class="titulos_listado">Hora Inicio</td>
   	  <td class="titulos_listado">Estado Reserva</td>
  	  </tr>

        <%
            if (listaReservas != null)
            {
                InstalacionDAL instalacionDAL = new InstalacionDAL();
                SocioDAL socioDAL = new SocioDAL();
                TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
                TipoInstalacionDAL tipoInstalacionDAL = new TipoInstalacionDAL();
                TipoInstalacion unTipoInstalacion;
                Socio unSocio = null;
                Instalacion unaInstalacion = null;
                TipoReserva unTipoReserva = null;
                int idInstalacion;
                
                int idReserva;
                String strFecha;
                for (int i = 0; i<listaReservas.size(); i++)
                {

                    idInstalacion = listaReservas.get(i).getIdInstalacion();
                    unaInstalacion = instalacionDAL.getInstalacion(idInstalacion);
                    strFecha = String.valueOf(listaReservas.get(i).getFecha());
                    String[] arrFecha = strFecha.split("-");
                    strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
                    idReserva = listaReservas.get(i).getIdEstadoReserva();
                    unTipoReserva = tipoReservaDAL.getTipoReserva(idReserva);
                    unTipoInstalacion = unaInstalacion.getTipoInstalacion();
        %>

                 <tr>
                    <td class="texto_listado"><%=unTipoInstalacion.getNombre()%></td>
                    <td class="texto_listado"><%=unaInstalacion.getNombre()%></td>
                    <td class="texto_listado"><%=strFecha%></td>
                    <td class="texto_listado"><%=listaReservas.get(i).getHoraInicio()%></td>
                    
                    <%
                        if(listaReservas.get(i).getIdEstadoReserva()==1)
                        {
                    %>
                            <td class="texto_listado" style="color: red" ><%=unTipoReserva.getEstado()%></td>
                    <%
                        }
                        else
                        {
                    %>
                            <td class="texto_listado"><%=unTipoReserva.getEstado()%></td>
                    <%
                        }
                    %>
                </tr>

        <%
                }
        %>
        <tr>
            <td class="texto_listado">&nbsp;</td>
            <td class="texto_listado">&nbsp;</td>
            <td class="texto_listado">&nbsp;</td>
            <td class="texto_listado">&nbsp;</td>
            <td class="texto_listado">&nbsp;</td>
        </tr>
         <%
           }
           else
           {
            %>

           <tr>
            <td class="texto_listado">&nbsp;</td>
            <td class="texto_listado">&nbsp;</td>
            <td class="texto_listado">&nbsp;</td>
            <td class="texto_listado">&nbsp;</td>
            <td class="texto_listado">&nbsp;</td>
    	</tr>

         <%
            }
       %>


	</table>

 <!--

    03/10/2011  Quitar la posibilidad de modificar las reservas en la Extranet
                Mirar version anterior

 -->


</div>
<div style="clear:both;"></div>


</div>
</body>
</html>

