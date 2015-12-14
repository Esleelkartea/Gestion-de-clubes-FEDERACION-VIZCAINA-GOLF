<%-- 
    Document   : iInstalaciones
    Created on : 06-jun-2011, 10:50:13
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.InstalacionHorario"%>
<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.dal.TipoInstalacionDAL"%>
<%@page import="com.Negocio.Instalacion"%>
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
<!--
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
-->
<script type="text/javascript">
     function validar()
     {
         miCampoTexto = document.getElementById("instalacion").value;
         miCampoTexto2 = document.getElementById("tipoInstalacion").value;
         miCampoTexto3 = document.getElementById("tarifaAdulto").value;
         miCampoTexto4 = document.getElementById("tarifaMenor").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0 || miCampoTexto4.length == 0)
        {
            alert("Debes rellenar todos los campos");
            return false;
        }
        else
        {
            if (isNaN(miCampoTexto3) || isNaN(miCampoTexto4))
            {
                alert("Los campos Tarifa Adulto y Tarifa Menor deben ser numéricos")
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
         miCampoTexto = document.getElementById("instalacion").value;
         miCampoTexto2 = document.getElementById("tipoInstalacion").value;
         miCampoTexto3 = document.getElementById("tarifaAdulto").value;
         miCampoTexto4 = document.getElementById("tarifaMenor").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0 || miCampoTexto4.length == 0)
        {
            alert("Debes seleccionar una Instalacion para dar de baja");
            return false;
        }
        else
        {
            return true;
        }
     }

     function validarBaja2()
     {
        miCampoTexto0 = document.getElementById("instalacion").value;
        miCampoTexto = document.getElementById("f_date_y").value;
        miCampoTexto2 = document.getElementById("f_date_t").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
        {
            alert("Debes seleccionar un Horario para dar de baja");
            return false;
        }
        else
        {
            return true;
        }

     }


function validar2()
{
    miCampoTexto0 = document.getElementById("instalacion").value;
    miCampoTexto = document.getElementById("f_date_y").value;
    miCampoTexto2 = document.getElementById("f_date_t").value;
    if(miCampoTexto0.length == 0) 
    {
        alert("Debes seleccionar una Instalacion");
        return false;
    }

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
                            return validarHora();
                        }
                    }
                    else
                    {
                        return validarHora();
                    }
                }
            }
            else
            {
                return validarHora();
            }
        }
     }
}

function validarHora()
{
    miCampoTexto3 = document.getElementById("horaInicio").value;
    miCampoTexto4 = document.getElementById("horaFin").value;
    arrHoraInicio = miCampoTexto3.split(":");
    arrHoraFin = miCampoTexto4.split(":");
    miCampoTexto3 = arrHoraInicio[0];
    miCampoTexto4 = arrHoraFin[0];
    horaInicio = parseInt(miCampoTexto3);
    horaFin = parseInt(miCampoTexto4);
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
    	  <td class="tdmenuSelec">Instalaciones</td>
    	  <td class="tdmenu"><a href="reservaCalendarioServlet">Reservas</a></td>
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

	<div id="submenu">Gestión de Instalaciones &nbsp;| &nbsp;<a href="ITiposInstalacionesServlet">Gestión Tipos de Instalaciones</a> &nbsp;</div>

        <%
            ArrayList<Instalacion> listaInstalaciones = (ArrayList<Instalacion>)session.getAttribute("listaInstalaciones");
            ArrayList<TipoInstalacion> listaTipoInstalaciones = (ArrayList<TipoInstalacion>)session.getAttribute("listaTipoInstalaciones");
        %>

	<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Datos Instalaciones</div>
	  <div class="listado">
	    <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
            <td class="titulos_listado">Instalación</td>
            <td class="titulos_listado">Tipo Instalación</td>
            <td class="titulos_listado">Tarifa Adulto</td>
            <td class="titulos_listado">Tarifa Menor</td>
  	</tr>

        <% 
            if (listaInstalaciones != null)
           {
               
                for (int i = 0; i<listaInstalaciones.size(); i++)
                {
                    
        %>
                <tr>
                    <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getNombre()%></a></td>
                    <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getTipoInstalacion().getNombre()%></a></td>
                    <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getTarifaAdulto()%></a></td>
                    <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getTarifaMenor()%></a></td>
                </tr>
        <%
                }
           }
        %>

	<tr>
          <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
	</tr>

	</table>
  
	</div>

    <div class="tituloGrande">FICHA INSTALACIÓN</div>
   
    <div class="marco2">



     <%
            Instalacion unaInstalacion = (Instalacion)session.getAttribute("instalacion");
            String nombre = "";
            String tarifaAdulto = "";
            String tarifaMenor = "";
            String idInstalacion = "";
            session.setAttribute("instalacion", null);

            if (unaInstalacion != null)
            {
                nombre = unaInstalacion.getNombre();
                tarifaAdulto = String.valueOf(unaInstalacion.getTarifaAdulto());
                tarifaMenor = String.valueOf(unaInstalacion.getTarifaMenor());
                idInstalacion = String.valueOf(unaInstalacion.getIdInstalacion());
            }
     %>


     <form autocomplete="off" action="IInstalacionesMIBServlet" method="post">
        <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
    	<td class="texto_formu" width="15%">Tipo instalación:</td>
    	<td  class="celda_campo">
		<label>
  	    
           <%
           if(unaInstalacion !=  null)
           {       
           %>
            <select name="tipoInstalacion" class="celda_campo" id="tipoInstalacion">
                <%
                    TipoInstalacion unTipoInstalacion = null;
                    for (int i=0; i<listaTipoInstalaciones.size(); i++)
                    {
                        unTipoInstalacion = listaTipoInstalaciones.get(i);
                        if(unaInstalacion.getTipoInstalacion().getIdTipoInstalacion()==unTipoInstalacion.getIdTipoInstalacion())
                        {
                 %>
                            <option value="<%=unTipoInstalacion.getIdTipoInstalacion()%>" selected><%=unTipoInstalacion.getNombre() %></option>
                 <%
                        }
                        else
                        {
                 %>
                            <option value="<%=unTipoInstalacion.getIdTipoInstalacion()%>" ><%=unTipoInstalacion.getNombre() %></option>
                 <%
                        }
                     }
                 %>
                
	      </select>
            <%
            }
            else
            {
            %>
                <select name="tipoInstalacion" class="celda_campo" id="tipoInstalacion">
                <%
                    TipoInstalacion unTipoInstalacion = null;
                    for (int i=0; i<listaTipoInstalaciones.size(); i++)
                    {
                        unTipoInstalacion = listaTipoInstalaciones.get(i);
                 %>
                    <option value="<%=unTipoInstalacion.getIdTipoInstalacion()%>" ><%=unTipoInstalacion.getNombre() %></option>
                 <%
                    }
                 %>

	      </select>

              <%
                }
              %>

	    </label>		</td>
		<td class="texto_formu">Instalación:</td>
   		<td class="celda_campo">
		<label><input name="instalacion" type="text" class="campo_texto" id="instalacion" size="40" value="<%=nombre%>" /></label>		</td>
  	</tr>
  	<tr>
  	  <td class="texto_formu">Tarifa adulto:</td>
  	  <td class="celda_campo">
  	    <label><input name="tarifaAdulto" type="text" class="campo_texto" id="tarifaAdulto" size="40"  value="<%=tarifaAdulto%>" /></label>
  	    </td>
  	  <td class="texto_formu">Tarifa menor:</td>
  	  <td class="celda_campo"><label><input name="tarifaMenor" type="text" class="campo_texto" id="tarifaMenor" size="40"  value="<%=tarifaMenor%>" /></label></td>
	  </tr>
  	</table>
	</div>

          <input type="hidden"  name="instalacionesId" value=<%=idInstalacion%> /><br/>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
        <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
    </div>
    </form>


        <%
            ArrayList<InstalacionHorario> listaInstalacionHorario = (ArrayList<InstalacionHorario>)session.getAttribute("listaInstalacionHorario");
        %>


    <div class="msg_titulos"><img src="imagenes/page_white_edit.png" width="16" height="16" />Horarios no disponibles</div>

    <form autocomplete="off" action="IInstalacionHorarioMIBServlet" method="post">
    <div class="msg_formulario">
     <div class="listado">
	    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	 <td class="titulos_listado">Fecha inicio</td>
   	 <td class="titulos_listado">Fecha fin</td>
   	 <td class="titulos_listado">Días</td>
   	 <td class="titulos_listado">Hora inicio</td>
   	 <td class="titulos_listado">Hora fin</td>
   	 <td class="titulos_listado">Observaciones</td>
  	  </tr>

          <%
            if (listaInstalacionHorario != null)
            {
                    String strFechaInicio = "";
                    String strFechaFin = "";
                    String strDias = "";

                for (int i = 0; i<listaInstalacionHorario.size();i++)
                {
                    strDias = "";

                    strFechaInicio = String.valueOf(listaInstalacionHorario.get(i).getFechaInicio());
                    String arrFechaInicio[] = strFechaInicio.split("-");
                    strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                    strFechaFin = String.valueOf(listaInstalacionHorario.get(i).getFechaFin());
                    String arrFechaFin[] = strFechaFin.split("-");
                    strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];
                    if (listaInstalacionHorario.get(i).isLunes()) strDias = strDias + "Lunes ";
                    if (listaInstalacionHorario.get(i).isMartes()) strDias = strDias + "Martes ";
                    if (listaInstalacionHorario.get(i).isMiercoles()) strDias = strDias + "Miercoles ";
                    if (listaInstalacionHorario.get(i).isJueves()) strDias = strDias + "Jueves ";
                    if (listaInstalacionHorario.get(i).isViernes()) strDias = strDias + "Viernes ";
                    if (listaInstalacionHorario.get(i).isSabado()) strDias = strDias + "Sabado ";
                    if (listaInstalacionHorario.get(i).isDomingo()) strDias = strDias + "Domingo ";

          %>

            <tr>
                <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalacionHorario.get(i).getIdInstalacion()%>&var2=<%=listaInstalacionHorario.get(i).getIdInstalacionHorario()%>"><%=strFechaInicio%></a></td>
                <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalacionHorario.get(i).getIdInstalacion()%>&var2=<%=listaInstalacionHorario.get(i).getIdInstalacionHorario()%>"><%=strFechaFin%></a></td>
                <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalacionHorario.get(i).getIdInstalacion()%>&var2=<%=listaInstalacionHorario.get(i).getIdInstalacionHorario()%>"><%=strDias%></a></td>
                <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalacionHorario.get(i).getIdInstalacion()%>&var2=<%=listaInstalacionHorario.get(i).getIdInstalacionHorario()%>"><%=listaInstalacionHorario.get(i).getHoraInicio()%></a></td>
                <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalacionHorario.get(i).getIdInstalacion()%>&var2=<%=listaInstalacionHorario.get(i).getIdInstalacionHorario()%>"><%=listaInstalacionHorario.get(i).getHoraFin()%></a></td>
                <td class="texto_listado"><a href="IInstalacionesServlet?var=<%=listaInstalacionHorario.get(i).getIdInstalacion()%>&var2=<%=listaInstalacionHorario.get(i).getIdInstalacionHorario()%>"><%=listaInstalacionHorario.get(i).getObservaciones()%></a></td>
	  </tr>
          

         <%
                }
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
	
	</table>
	</div>


         <%
            InstalacionHorario unaInstalacionHorario =  (InstalacionHorario)session.getAttribute("unaInstalacionHorario");
            String strIdInstalacionHorario = "";
            String strFechaInicio = "";
            String strFechaFin = "";
            String strHoraInicio = "";
            String strHoraFin = "";
            String strObservaciones = "";
            if (unaInstalacionHorario != null)
            {
                strIdInstalacionHorario = String.valueOf(unaInstalacionHorario.getIdInstalacionHorario());

                strFechaInicio = String.valueOf(unaInstalacionHorario.getFechaInicio());
                String[] arrFechaInicio = strFechaInicio.split("-");
                strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                strFechaFin = String.valueOf(unaInstalacionHorario.getFechaFin());
                String[] arrFechaFin = strFechaFin.split("-");
                strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];

                strHoraInicio = String.valueOf(unaInstalacionHorario.getHoraInicio());
                strHoraFin = String.valueOf(unaInstalacionHorario.getHoraFin());
                strObservaciones = unaInstalacionHorario.getObservaciones();

                boolean lunes = unaInstalacionHorario.isLunes();
                boolean martes = unaInstalacionHorario.isMartes();
                boolean miercoles = unaInstalacionHorario.isMiercoles();
                boolean jueves = unaInstalacionHorario.isJueves();
                boolean viernes = unaInstalacionHorario.isViernes();
                boolean sabado = unaInstalacionHorario.isSabado();
                boolean domingo = unaInstalacionHorario.isDomingo();
            }

     %>





	<div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
    	<td class="texto_formu" width="15%">Fecha de:</td>
    	<td  class="celda_campo">
            <label><input name="fechaInicio" type="text" class="campo_texto" id="f_date_y" readonly="1" value="<%=strFechaInicio%>" />
  	    <img src="jscalendar/img.gif" id="f_trigger_y"/>

  	    <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_y",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_y",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
  	    </label>		</td>
		<td class="texto_formu">Fecha a:</td>
   		<td class="celda_campo">
		<label>
                    <input name="fechaFin" type="text" class="campo_texto" id="f_date_t" readonly="1" value="<%=strFechaFin%>" />
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
  	    </label>		</td>
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


    <tr>
  	<td colspan="4" class="texto_formu2">
           

        <%
            if  (unaInstalacionHorario != null)
            {
                if (unaInstalacionHorario.isLunes())
                {
        %>
                <input name="lunes" type="checkbox" value="lunes" checked /> Lunes &nbsp;&nbsp;
        <%      }  else  {  %>
                <input name="lunes" type="checkbox" value="lunes"/> Lunes &nbsp;&nbsp;
        
        <%      } if (unaInstalacionHorario.isMartes()) { %>
                <input name="martes" type="checkbox" value="martes" checked /> Martes &nbsp;&nbsp;
        <%      }  else  { %>
                <input name="martes" type="checkbox" value="martes" /> Martes &nbsp;&nbsp;
        
         <%     }   if (unaInstalacionHorario.isMiercoles()) {  %>
                <input name="miercoles" type="checkbox" value="miercoles" checked /> Miércoles &nbsp;&nbsp;
         <%      }  else  { %>
                <input name="miercoles" type="checkbox" value="miercoles" /> Miércoles &nbsp;&nbsp;
         
         <%     }    if (unaInstalacionHorario.isJueves())  {   %>
                <input name="jueves" type="checkbox" value="jueves" checked /> Jueves &nbsp;&nbsp;
          <%      }  else  {  %>
                <input name="jueves" type="checkbox" value="jueves" /> Jueves &nbsp;&nbsp;
           
          <%    }   if (unaInstalacionHorario.isViernes())  {   %>
                <input name="viernes" type="checkbox" value="viernes" checked /> Viernes &nbsp;&nbsp;
          <%      }  else  {   %>
                <input name="viernes" type="checkbox" value="viernes" /> Viernes &nbsp;&nbsp;
           
          <%   }  if (unaInstalacionHorario.isSabado()) {   %>
                <input name="sabado" type="checkbox" value="sabado" checked /> Sábado &nbsp;&nbsp;
          <%      }  else  {   %>
                <input name="sabado" type="checkbox" value="sabado" /> Sábado &nbsp;&nbsp;
            
          <%      }    if (unaInstalacionHorario.isDomingo())   {   %>
                <input name="domingo" type="checkbox" value="domingo" checked /> Domingo
          <%      }  else  {  %>
                <input name="domingo" type="checkbox" value="domingo" /> Domingo
           <%
                }
              }
            else
              {
           %>

          <input name="lunes" type="checkbox" value="lunes" /> Lunes &nbsp;&nbsp; 
  	  <input name="martes" type="checkbox" value="martes" /> Martes &nbsp;&nbsp;
	  <input name="miercoles" type="checkbox" value="miercoles" /> Miércoles &nbsp;&nbsp;
  	  <input name="jueves" type="checkbox" value="jueves" /> Jueves &nbsp;&nbsp;
  	  <input name="viernes" type="checkbox" value="viernes" /> Viernes &nbsp;&nbsp;
  	  <input name="sabado" type="checkbox" value="sabado" /> Sábado &nbsp;&nbsp;
  	  <input name="domingo" type="checkbox" value="domingo" /> Domingo
	</td>

        <%
        }
        %>

  </tr>

           	<tr>
    	<td class="texto_formu" width="15%">Observaciones:</td>
    	<td colspan="3"  class="celda_campo">
    	  <label>
    	    <textarea name="observaciones" cols="120" rows="3" class="campo_texto" id="observaciones"><%=strObservaciones%></textarea>
    	  </label>		</td>
		</tr>
  	</table>
	</div>
    </div>

    <input type="hidden"  name="instalacionHorarioId" value=<%=strIdInstalacionHorario%> />
    <input type="hidden"  name="instalacionesId" value=<%=idInstalacion%> />         

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar2()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja2()" />
        <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar2()" />
    </div>
    </form>
   </div>

</div>
	<div style="clear:both;"></div>

</div>
</body>
</html>

