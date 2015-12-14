<%-- 
    Document   : reservaGestion
    Created on : 30-jun-2011, 9:39:36
    Author     : Julen
--%>

<%@page import="com.dal.TipoReservaDAL"%>
<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.dal.TipoInstalacionDAL"%>
<%@page import="com.dal.TipoPagoDAL"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.dal.SocioDAL"%>
<%@page import="com.dal.InstalacionDAL"%>
<%@page import="com.Negocio.Reserva"%>
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
        idReserva = document.getElementById("idReserva").value;
        if(idReserva == "")
        {
            alert("Debes seleccionar una reserva para dar de baja");
            return false;
        }
        else
        {

            miCampoTexto = document.getElementById("nombreMIB").value;
            miCampoTexto2 = document.getElementById("apellidosMIB").value;
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
        fechaInicio = document.getElementById("f_date_P").value;
        fechaFin = document.getElementById("f_date_w").value;
        if (fechaInicio.length != 0 && fechaFin.length != 0)
        {
            var xMes=fechaInicio.substring(3, 5);
            var xDia=fechaInicio.substring(0, 2);
            var xAno=fechaInicio.substring(6,10);
            var yMes=fechaFin.substring(3, 5);
            var yDia=fechaFin.substring(0, 2);
            var yAno=fechaFin.substring(6,10);

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
        else
        {
            return true;
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

<div id="submenu">Gestión de Reservas &nbsp;|  <a href="reservaAltaServlet">Alta de Reservas</a> | <a href="reservaCalendarioServlet">Calendario de Reservas</a>&nbsp;|&nbsp;<a href="reservaProfesorServlet">Reservas de Profesor</a>&nbsp;|&nbsp;<a href="reservaGestionProfesorServlet">Gestión de Reservas de Profesor</a></div>

<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Buscador de Reservas</div>

    <%
        ArrayList<Instalacion> listaInstalaciones = (ArrayList<Instalacion>)session.getAttribute("listaInstalaciones");
        ArrayList<TipoReserva> listaTipoReserva = (ArrayList<TipoReserva>)session.getAttribute("listaTipoReserva");
        ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");
    %>

    <form autocomplete="off" action="reservaBusquedaServlet" method="post">
	<div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
    	<td class="texto_formu" width="15%">Nombre Socio:</td>
    	<td class="celda_campo">
	<label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" /></label></td>
	<td class="texto_formu" width="15%">Apellidos Socio:</td>
    	<td class="celda_campo">
	<label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40" /></label></td>
  	</tr>

    <tr>
    	<td class="texto_formu" width="15%">Fecha de:</td>
    	<td class="celda_campo">
		<label><input name="fechaInicio" type="text" class="campo_texto" id="f_date_P" readonly="1" />
  	  <img src="jscalendar/img.gif" id="f_trigger_P"/>
  	  <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_P",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_P",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
	  </script>
  	  </label></td>
		<td class="texto_formu">Fecha a:</td>
   		<td class="celda_campo">
		<label><input name="fechaFin" type="text" class="campo_texto" id="f_date_w" readonly="1" />
  	  <img src="jscalendar/img.gif" id="f_trigger_w"/>
  	  <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_w",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_w",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
	  </script>
  	  </label></td>
  	</tr>
  	<tr>

  	  <td class="texto_formu">Instalación:</td>
  	  <td class="celda_campo">
              <select name="instalacion" class="campo_texto" id="instalacion">
                  <option></option>
                    <%
                    if (listaInstalaciones != null)
                    {
                        for(int i = 0; i<listaInstalaciones.size();i++)
                        {
                    %>
                    <option value="<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getNombre()%></option>

                    <%
                        }
                    }
                    %>

              </select></td>
	  </tr>

      <tr>
  	  <td class="texto_formu">Estado:</td>
  	  <td class="celda_campo"><select name="estadoReserva" class="campo_texto" id="estadoReserva">
                  <option></option>
                  <%
                    if (listaTipoReserva != null)
                    {
                        for(int i = 0; i<listaTipoReserva.size();i++)
                        {
                    %>
                    <option value="<%=listaTipoReserva.get(i).getIdTipoReserva()%>"><%=listaTipoReserva.get(i).getEstado()%></option>

                    <%
                        }
                    }
                    %>
                  </select></td>
  	<td class="texto_formu">Forma de pago:</td>
  	  <td class="celda_campo"><select name="formaPago" class="campo_texto" id="formaPago" >
                  <option></option>
                  <%
                    if (listaTipoPago != null)
                    {
                        for(int i = 0; i<listaTipoPago.size();i++)
                        {
                    %>
                    <option value="<%=listaTipoPago.get(i).getIdTipoPago()%>"><%=listaTipoPago.get(i).getModo()%></option>

                    <%
                        }
                    }
                    %>

                  </select></td>
	  </tr>
  	</table>
	</div>

                    <div class="capas_botones"><input name="buscar" type="submit" class="boton" id="buscar" value="Buscar" onclick="return validarBusqueda()" />
          
        </div>

    </form>

	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	  <td class="titulos_listado">Instalación</td>
   	  <td class="titulos_listado">Tipo instalación</td>
   	  <td class="titulos_listado">Fecha</td>
   	  <td class="titulos_listado">Nombre</td>
          <td class="titulos_listado">Apellidos</td>
   	  <td class="titulos_listado">Estado Reserva</td>
  	  </tr>

          <%
            ArrayList<Reserva> listaReservas = (ArrayList<Reserva>)session.getAttribute("listaReservas");
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
                int idSocio;
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
                    idSocio = listaReservas.get(i).getIdSocio();
                    unSocio = socioDAL.getSocioId(idSocio);
                    unTipoInstalacion = unaInstalacion.getTipoInstalacion();

                    int estado;
        %>
                <tr>
                    <td class="texto_listado"><a href="reservaGestionServlet?var=<%=listaReservas.get(i).getIdReserva()%>"><%=unaInstalacion.getNombre()%></a></td>
                    <td class="texto_listado"><a href="reservaGestionServlet?var=<%=listaReservas.get(i).getIdReserva()%>"><%=unTipoInstalacion.getNombre()%></a></td>
                    <td class="texto_listado"><a href="reservaGestionServlet?var=<%=listaReservas.get(i).getIdReserva()%>"><%=strFecha%></a></td>
                    <td class="texto_listado"><a href="reservaGestionServlet?var=<%=listaReservas.get(i).getIdReserva()%>"><%=unSocio.getNombre()%></a></td>
                    <td class="texto_listado"><a href="reservaGestionServlet?var=<%=listaReservas.get(i).getIdReserva()%>"><%=unSocio.getApellidos()%></a></td>
                    

                    <%
                        estado = listaReservas.get(i).getIdEstadoReserva();
                        if(estado == 1)
                        {
                    %>
                            <td class="texto_listado" style="color: red" ><%=unTipoReserva.getEstado()%></td>
                    <%
                        }
                        else
                        {
                    %>
                            <td class="texto_listado"><a href="reservaGestionServlet?var=<%=listaReservas.get(i).getIdReserva()%>"><%=unTipoReserva.getEstado()%></a></td>
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
                    <td class="texto_listado">&nbsp;</td>
                </tr>
       <%
            }
       %>

	</table>

   <div class="capas_botones_dcha">
      <form action="excelReservas.jsp" method="post">
        <input name="exportarExcel" type="submit" class="boton2" id="exportarExcel" value="Exportar a Excel" />
      </form>
    </div>

   <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Ficha de Reserva</div>
   <div class="marco">

     <div class="subtitulos">Modificar Reserva</div>

     <%
        Reserva unaReserva = (Reserva)session.getAttribute("unaReserva");
        String strNombre = "";  String strApellidos ="";
        String strInstalacion = ""; String strEstado = "";
        String strFormaPago = "";   String strFecha = "";
        String strHoraInicio = "";  String strHoraFin = "";
        int idInstalacion = 0;
        int idEstadoReserva = 0;
        int idModoPago = 0;
        int idSocio = 0;

        String idReserva ="";

        if (unaReserva != null)
        {
            strHoraInicio = String.valueOf(unaReserva.getHoraInicio());
            strHoraFin = String.valueOf(unaReserva.getHoraFin());

            strFecha = String.valueOf(unaReserva.getFecha());
            String arrFecha[] = strFecha.split("-");
            strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

            InstalacionDAL instalacionDAL = new InstalacionDAL();
            Instalacion unaInstalacion = null;
            idInstalacion = unaReserva.getIdInstalacion();
            unaInstalacion = instalacionDAL.getInstalacion(idInstalacion);
            strInstalacion = unaInstalacion.getNombre();


            TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
            TipoReserva tipoReserva = null;
            idEstadoReserva = unaReserva.getIdEstadoReserva();
            tipoReserva = tipoReservaDAL.getTipoReserva(idEstadoReserva);
            strEstado = tipoReserva.getEstado();

            TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
            TipoPago unTipoPago = null;
            idModoPago = unaReserva.getFormaPago();
            unTipoPago = tipoPagoDAL.getTipoPago(idModoPago);
            strFormaPago = unTipoPago.getModo();

            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio = null;
            idSocio = unaReserva.getIdSocio();
            unSocio = socioDAL.getSocioId(idSocio);
            strNombre = unSocio.getNombre();
            strApellidos = unSocio.getApellidos();

            idReserva = String.valueOf(unaReserva.getIdReserva());

        }
     %>

     <form autocomplete="off" action="ReservaGestionMIBServlet" method="post">

    <div class="formulario">

	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Nombre Socio:</td>
 	  <td class="celda_campo">
              <label><input name="nombre" type="text" class="campo_texto" id="nombreMIB" size="40" value="<%=strNombre%>" readonly="1" /></label></td>
 	  <td class="texto_formu" width="15%">Apellidos Socio:</td>
          <td class="celda_campo">
              <label><input name="apellidos" type="text" class="campo_texto" id="apellidosMIB" size="40" value="<%=strApellidos%>" readonly="1" /></label></td>
 	  </tr>
       	<tr>
  	  <td class="texto_formu">Instalación:</td>
  	  <td class="celda_campo">
              <select name="instalacion" class="campo_texto" id="instalacion">
             <%
                if (listaInstalaciones != null)
                {
                    for(int i = 0; i<listaInstalaciones.size();i++)
                    {
                       if (listaInstalaciones.get(i).getIdInstalacion() == idInstalacion)
                       {
            %>
                        <option name="instalacion" value="<%=listaInstalaciones.get(i).getIdInstalacion()%>" selected ><%=listaInstalaciones.get(i).getNombre()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="instalacion" value="<%=listaInstalaciones.get(i).getIdInstalacion()%>" ><%=listaInstalaciones.get(i).getNombre()%></option>
            <%
                        }
                    }
                }
            %>
              </select></td>
          <td class="texto_formu">&nbsp;</td>
          <td class="celda_campo">&nbsp;</td>
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
                       if (listaTipoReserva.get(i).getIdTipoReserva() == idEstadoReserva)
                       {
            %>
                        <option name="estadoReserva" value="<%=listaTipoReserva.get(i).getIdTipoReserva()%>" selected ><%=listaTipoReserva.get(i).getEstado()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="estadoReserva" value="<%=listaTipoReserva.get(i).getIdTipoReserva()%>" ><%=listaTipoReserva.get(i).getEstado()%></option>
            <%
                        }
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
                       if (listaTipoPago.get(i).getIdTipoPago() == idModoPago)
                       {
            %>
                        <option name="formaPago" value="<%=listaTipoPago.get(i).getIdTipoPago()%>" selected ><%=listaTipoPago.get(i).getModo()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="formaPago" value="<%=listaTipoPago.get(i).getIdTipoPago()%>" ><%=listaTipoPago.get(i).getModo()%></option>
            <%
                        }
                    }
                }
            %>

            </select></td>
	  </tr>
       	<tr>

  	  <td class="texto_formu">Fecha:</td>
              <td class="texto_formu"><label><input name="fecha" type="text" class="campo_texto" id="f_date_t" readonly="1" value="<%=strFecha%>" />
  	  <img src="jscalendar/img.gif" id="f_trigger_t"/>
  	  <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_t",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_t",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
	  </script>
  	  </label></td>
          <td class="texto_formu">&nbsp;</td>
          <td class="celda_campo">&nbsp;</td>
	  </tr>

          <%

               if(unaReserva != null)
               {
                    if(strInstalacion.contains("golf") || strInstalacion.contains("Golf") || strInstalacion.contains("GOLF"))
                    {
          %>

          <tr>
                    <td class="texto_formu" width="15%">Hora entrada:</td>
                    <td width="30%"  class="celda_campo">
                        <select name="horaInicio" class="campo_texto" id="horaInicio">
                            <%
                                for(int i=9;i<21;i++)
                                {
                                    String hora="";
                                    hora = i + ":00";
                                    for(int j = 0;j<3;j++)
                                    {
                                                switch(j)
                                                {
                                                    case 0:
                                                        hora = i + ":00";
                                                        break;
                                                    case 1:
                                                        hora = i + ":20";
                                                        break;
                                                    case 2:
                                                        hora = i + ":40";
                                                        break;
                                                }

                                                if(i==9) hora = "0"+hora;
                                                if(strHoraInicio.equals(hora+":00"))
                                                {

                                        %>
                                                    <option value="<%=hora+":00"%>" selected ><%=hora %></option>
                                         <%
                                                }
                                                else
                                                {
                                        %>
                                                    <option value="<%=hora+":00"%>" ><%=hora %></option>
                                        <%
                                                }
                                    }
                                }
                            %>
                        </select>
                    </td>
 
                </tr>
                <input type="hidden" id="golf" name="golf" value="true" />

          <%
                    }
                    else
                    {
          %>
                <input type="hidden" id="golf" name="golf" value="false" />
                <tr>
                    <td class="texto_formu" width="15%">Hora inicio:</td>
                    <td width="30%"  class="celda_campo">
                        <select name="horaInicio" class="campo_texto" id="horaInicio">
                            <%
                                for(int i=9;i<21;i++)
                                {
                                    String hora="";
                                    hora = i + ":00";
                                    if(strHoraInicio.equals(hora+":00"))
                                    {

                            %>
                                        <option value="<%=hora+":00"%>" selected ><%=hora %></option>
                             <%
                                    }
                                    else
                                    {
                            %>
                                        <option value="<%=hora+":00"%>" ><%=hora %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </td>
                    <td class="texto_formu" width="15%">Hora fin:</td>
                    <td width="30%"  class="celda_campo">
                        <select name="horaFin" class="campo_texto" id="horaFin">
                            <%
                                for(int i=10;i<22;i++)
                                {
                                    String hora="";
                                    hora = i + ":00";
                                    if(strHoraFin.equals(hora+":00"))
                                    {

                            %>
                                        <option value="<%=hora+":00"%>" selected ><%=hora %></option>
                             <%
                                    }
                                    else
                                    {
                            %>
                                        <option value="<%=hora+":00"%>" ><%=hora %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </td>
                </tr>
          <%
                    }
               }

          %>



    
  	</table>
	</div>
                    <input type="hidden" id="idReserva" name="idReserva" value="<%=idReserva%>" />

    <%
        String mib = "";
        String mibSocio = "";
        String strResultado = "";
        String resultadoMIBReservaSocio = (String)session.getAttribute("resultadoMIBReservaSocio");
        String resultadoMIB = (String)session.getAttribute("resultadoMIBReserva");
        if (resultadoMIBReservaSocio != null)
        {
            mibSocio = resultadoMIBReservaSocio;
        }
        else
        {
            if (resultadoMIB != null)
            {
                mib = resultadoMIB;
            }
        }
        session.setAttribute("resultadoMIBReservaSocio",null);
        session.setAttribute("resultadoMIBReserva",null);

        Boolean resultadoReservaDisponible = (Boolean)session.getAttribute("resultadoReservaDisponible");
        if(resultadoReservaDisponible != null)
        {
            strResultado = "La reserva en ese horario no es posible";
        }
        session.setAttribute("resultadoReservaDisponible", null);
    %>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
      <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
      <h5 style="color:red"><%=mib%><%=mibSocio%><%=strResultado%></h5>
    </div>



    </form>

    </div>

</div>
<div style="clear:both;"></div>


</div>
</body>
</html>
