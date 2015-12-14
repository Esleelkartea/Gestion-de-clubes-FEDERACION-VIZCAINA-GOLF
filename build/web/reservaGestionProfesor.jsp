<%--
    Document   : reservaGestionProfesor
    Created on : 14-sep-2011, 9:49:54
    Author     : Julen
--%>

<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.dal.ReservaProfesorDAL"%>
<%@page import="com.dal.TipoPagoDAL"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.dal.TipoReservaDAL"%>
<%@page import="com.dal.ProfesorDAL"%>
<%@page import="com.dal.SocioDAL"%>
<%@page import="com.Negocio.ReservaProfesor"%>
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
        idReservaProfesor = document.getElementById("idReservaProfesor").value;
        if (idReservaProfesor == 0)
        {
            alert("Debes introducir una reserva a eliminar");
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

<div id="submenu"><a href="reservaGestionServlet">Gestión de Reservas</a> &nbsp;|  <a href="reservaAltaServlet">Alta de Reservas</a> | <a href="reservaCalendarioServlet">Calendario de Reservas</a>&nbsp;|&nbsp;<a href="reservaProfesorServlet">Reservas de Profesor</a>&nbsp;|&nbsp;Gestión de Reservas de Profesor</div>

<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Buscador de Reservas de Profesor</div>

    <%
        ArrayList<Profesor> listaProfesores = (ArrayList<Profesor>)session.getAttribute("listaProfesor");
        ArrayList<TipoReserva> listaTipoReserva = (ArrayList<TipoReserva>)session.getAttribute("listaTipoReserva");
        ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");
        ArrayList<TipoInstalacion> listaTipoInstalacion = (ArrayList<TipoInstalacion>)session.getAttribute("listaTipoInstalacion");
    %>

    <form autocomplete="off" action="reservaBusquedaProfesorServlet" method="post">
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

  	  <td class="texto_formu">Profesor:</td>
  	  <td class="celda_campo">
              <select name="profesor" class="campo_texto" id="profesor">
                  <option></option>
                    <%
                    if (listaProfesores != null)
                    {
                        for(int i = 0; i<listaProfesores.size();i++)
                        {
                    %>
                    <option value="<%=listaProfesores.get(i).getIdProfesor()%>"><%=listaProfesores.get(i).getNombre()%></option>

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
   	  <td class="titulos_listado">Profesor</td>
   	  <td class="titulos_listado">Fecha</td>
   	  <td class="titulos_listado">Nombre</td>
          <td class="titulos_listado">Apellidos</td>
   	  <td class="titulos_listado">Estado Reserva</td>
  	  </tr>

          <%
            ArrayList<ReservaProfesor> listaReservaProfesor = (ArrayList<ReservaProfesor>)session.getAttribute("listaReservaProfesor");
            if (listaReservaProfesor != null)
            {

                ProfesorDAL profesorDAL = new ProfesorDAL();
                SocioDAL socioDAL = new SocioDAL();
                TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
                Profesor unProfesor = null;
                Socio unSocio = null;
                TipoReserva unTipoReserva = null;
                int idProfesor;
                int idSocio;
                int idReserva;
                String strFecha;
                for (int i = 0; i<listaReservaProfesor.size(); i++)
                {
                    idProfesor = listaReservaProfesor.get(i).getIdProfesor();
                    unProfesor = profesorDAL.getProfesor(idProfesor);
                    strFecha = String.valueOf(listaReservaProfesor.get(i).getFecha());
                    String[] arrFecha = strFecha.split("-");
                    strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
                    idReserva = listaReservaProfesor.get(i).getIdEstadoReserva();
                    unTipoReserva = tipoReservaDAL.getTipoReserva(idReserva);
                    idSocio = listaReservaProfesor.get(i).getIdSocio();
                    unSocio = socioDAL.getSocioId(idSocio);

                    int estado;

        %>
       
                <tr>
                    <td class="texto_listado"><a href="reservaGestionProfesorServlet?var=<%=listaReservaProfesor.get(i).getIdReservaProfesor()%>"><%=unProfesor.getNombre()%></a></td>
                    <td class="texto_listado"><a href="reservaGestionProfesorServlet?var=<%=listaReservaProfesor.get(i).getIdReservaProfesor()%>"><%=strFecha%></a></td>
                    <td class="texto_listado"><a href="reservaGestionProfesorServlet?var=<%=listaReservaProfesor.get(i).getIdReservaProfesor()%>"><%=unSocio.getNombre()%></a></td>
                    <td class="texto_listado"><a href="reservaGestionProfesorServlet?var=<%=listaReservaProfesor.get(i).getIdReservaProfesor()%>"><%=unSocio.getApellidos()%></a></td>
                    
                    <%
                        estado = listaReservaProfesor.get(i).getIdEstadoReserva();
                        if(estado == 1)
                        {
                    %>
                            <td class="texto_listado" style="color: red" ><%=unTipoReserva.getEstado()%></td>
                    <%
                        }
                        else
                        {
                    %>
                            <td class="texto_listado"><a href="reservaGestionServlet?var=<%=listaReservaProfesor.get(i).getIdReservaProfesor()%>"><%=unTipoReserva.getEstado()%></a></td>
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

   <div class="capas_botones_dcha">
      <form action="excelReservasProfesor.jsp" method="post">
        <input name="exportarExcel" type="submit" class="boton2" id="exportarExcel" value="Exportar a Excel" />
      </form>
    </div>

   <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Ficha de Reserva</div>
   <div class="marco">

     <div class="subtitulos">Modificar Reserva</div>

     <%
        ReservaProfesor unaReservaProfesor = (ReservaProfesor)session.getAttribute("unaReservaProfesor");
        String strNombre = "";  String strApellidos ="";
        String strHoraInicio = "";  String strHoraFin = "";
        String strEstado = "";  String strFormaPago = "";
        String strFecha = "";
        String strProfesor = "";
        String strIdReservaProfesor = "";

        int idSocio = 0;
        int idEstadoReserva = 0;
        int idModoPago = 0;
        int idProfesor = 0;
        int idReservaProfesor = 0;

        if(unaReservaProfesor != null)
        {
            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio = null;
            idSocio = unaReservaProfesor.getIdSocio();
            unSocio = socioDAL.getSocioId(idSocio);
            strNombre = unSocio.getNombre();
            strApellidos = unSocio.getApellidos();

            TipoReservaDAL tipoReservaDAL = new TipoReservaDAL();
            TipoReserva tipoReserva = null;
            idEstadoReserva = unaReservaProfesor.getIdEstadoReserva();
            tipoReserva = tipoReservaDAL.getTipoReserva(idEstadoReserva);
            strEstado = tipoReserva.getEstado();

            TipoPagoDAL tipoPagoDAL = new TipoPagoDAL();
            TipoPago unTipoPago = null;
            idModoPago = unaReservaProfesor.getFormaPago();
            unTipoPago = tipoPagoDAL.getTipoPago(idModoPago);
            strFormaPago = unTipoPago.getModo();

            strHoraInicio = String.valueOf(unaReservaProfesor.getHoraInicio());
            strHoraFin = String.valueOf(unaReservaProfesor.getHoraFin());

            strFecha = String.valueOf(unaReservaProfesor.getFecha());
            String arrFecha[] = strFecha.split("-");
            strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

            ProfesorDAL profesorDAL = new ProfesorDAL();
            Profesor unProfesor = null;
            idProfesor = unaReservaProfesor.getIdProfesor();
            unProfesor = profesorDAL.getProfesor(idProfesor);
            strProfesor = unProfesor.getNombre();

            idReservaProfesor = unaReservaProfesor.getIdReservaProfesor();

        }
     %>

     <form autocomplete="off" action="reservaGestProfesorMIBServlet" method="post">

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
  	  <td class="texto_formu">Profesor:</td>
  	  <td class="celda_campo">
              <select name="profesor" class="campo_texto" id="profesor">
              <%
                if (listaProfesores != null)
                {
                    for(int i = 0; i<listaProfesores.size();i++)
                    {
                       if (listaProfesores.get(i).getIdProfesor() == idProfesor)
                       {
            %>
                        <option name="profesor" value="<%=listaProfesores.get(i).getIdProfesor()%>" selected ><%=listaProfesores.get(i).getNombre()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="profesor" value="<%=listaProfesores.get(i).getIdProfesor()%>" ><%=listaProfesores.get(i).getNombre()%></option>
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
  	</table>
	</div>
                    <input type="hidden" id="idReservaProfesor" name="idReservaProfesor" value="<%=idReservaProfesor%>" />

    <%
        String resultadoMIBReservaSocio = (String)session.getAttribute("resultadoMIBReservaProfesor");
        String resultadoSocio = (String)session.getAttribute("resultadoMIBReservaSocio");

        String strResultado = "";
        String strResultadoSocio = "";
        if (resultadoSocio != null)
        {
            strResultadoSocio = resultadoSocio;
        }
        else
        {
            if (resultadoMIBReservaSocio != null)
            {
                strResultado = resultadoMIBReservaSocio;
            }
        }
        session.setAttribute("resultadoMIBReservaProfesor", null);
        session.setAttribute("resultadoSocio", null);

        Boolean resultadoReservaDisponible = (Boolean)session.getAttribute("resultadoReservaDisponible");
        String strDisponible = "";
        if(resultadoReservaDisponible != null)
        {
            strDisponible = "La reserva en ese horario no es posible";
        }
        session.setAttribute("resultadoReservaDisponible", null);


    %>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
        <h5 style="color:red"><%=strResultadoSocio%><%=strResultado%><%=strDisponible%></h5>
    </div>



    </form>


    </div>

</div>
<div style="clear:both;"></div>


</div>
</body>
</html>

