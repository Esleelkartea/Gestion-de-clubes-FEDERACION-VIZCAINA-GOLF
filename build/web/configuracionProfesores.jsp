<%--
    Document   : iInstalaciones
    Created on : 06-jun-2011, 10:50:13
    Author     : Julen
--%>

<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.Negocio.ProfesorHorario"%>
<%@page import="com.Negocio.Profesor"%>
<%@page import="com.Negocio.Usuario"%>
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

<script language="JavaScript">
  function validar2() {

     miCampoTexto = document.getElementById("nombre").value;
     miCampoTexto2 = document.getElementById("apellidos").value;
     miCampoTexto3 = document.getElementById("email").value;
     miCampoTexto4 = document.getElementById("telefono").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length==0 || miCampoTexto3.length==0 || miCampoTexto4.length==0)
     {
        alert("Debes rellenar los campos");
        return false;
     }
     else
     {
         miCampoTexto5 = document.getElementById("importe").value;
         if(isNaN(miCampoTexto5))
         {
            alert("Debes introducir datos numéricos en el campo Precio/Hora");
            return false;
         }

         if(isNaN(miCampoTexto4))
         {
            alert("Debes introducir un numero en el campo Telefono");
            return false;
         }
         else
         {
             return validarEmail(miCampoTexto3);
         }
     }
}

function validarEmail(valor) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(valor))
    {
        return (true)
    }
    else
    {
        alert("La dirección de email es incorrecta");
        return (false);
    }
}

function validar()
{
     miCampoTexto0 = document.getElementById("nombre").value;
     if (miCampoTexto0.length == 0)
     {
         alert("Debes seleccionar un profesor");
         return false;
     }
     miCampoTexto = document.getElementById("f_date_y").value;
     miCampoTexto2 = document.getElementById("f_date_t").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length==0)
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

function validarBaja()
{
    miCampoTexto3 = document.getElementById("nombre").value;
    if(miCampoTexto3.length == 0)
    {
        alert("Debes seleccionar un profesor para dar de baja")
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
            String nombreUsuario = unUsuario.getNombre();
         %>

         <div id="usu" align="right">Usuario: &nbsp;&nbsp;<%=nombreUsuario%></div>

	<div id="menu">
   <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    	  <td class="tdmenu"><a href="SociosServlet">Socios</a></td>
    	  <td class="tdmenu"><a href="IInstalacionesServlet">Instalaciones</a></td>
    	  <td class="tdmenu"><a href="reservaCalendarioServlet">Reservas</a></td>
          <td class="tdmenu"><a href="cursosServlet">Cursos</a></td>
    	  <td class="tdmenu"><a href="invitacionServlet">Invitaciones</a></td>
    	  <td class="tdmenu"><a href="convUsuConvenioServlet">Convenios & Usuario Extenos</a></td>
    	  <td class="tdmenu"><a href="visitaServlet">Visitas</a></td>
    	  <!--<td class="tdmenu"><a href="bonos.html">Bonos Deportivos</a></td>-->
    	  <td class="tdmenu"><a href="menPanelServlet">Mensajes</a></td>
    	  <td class="tdmenu"><a href="ventaGestVentaServlet">Venta Material</a></td>
    	  <td class="tdmenuSelec">Configuración</td>
    	</tr>
    </table>
</div>

	<div id="submenu"><a href="confSocioServlet">Gestión de Socios</a> &nbsp;| &nbsp;<a href="confReservaServlet">Gestión de Reservas</a> &nbsp;| &nbsp;<a href="confInvitacionServlet">Gestión de Invitaciones</a> &nbsp;| &nbsp;<a href="confPagoServlet">Gestión de Modos de Pago</a>&nbsp; | &nbsp; Gestión de Profesores &nbsp; |&nbsp;<a href="confArchivoServlet">Gestión de Archivos</a>&nbsp; |&nbsp;<a href="confUsuarioServlet">Gestión de Usuarios</a></div>

        <%
            ArrayList<Profesor> listaProfesores = (ArrayList<Profesor>)session.getAttribute("listaProfesores");
            ArrayList<TipoInstalacion> listaTipoInstalacion = (ArrayList<TipoInstalacion>)session.getAttribute("listaTipoInstalacion");
        %>

	<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Gestión de Profesores</div>
	  <div class="listado">
	    <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
            <td class="titulos_listado">Nombre</td>
            <td class="titulos_listado">Apellidos</td>
            <td class="titulos_listado">Email</td>
            <td class="titulos_listado">Telefono</td>
            <td class="titulos_listado">Precio/Hora</td>
   	</tr>

        <% if (listaProfesores != null)
           {
                for (int i = 0; i<listaProfesores.size(); i++)
                {
        %>
                <tr>
                    <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesores.get(i).getIdProfesor()%>"><%=listaProfesores.get(i).getNombre()%></a></td>
                    <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesores.get(i).getIdProfesor()%>"><%=listaProfesores.get(i).getApellidos()%></a></td>
                    <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesores.get(i).getIdProfesor()%>"><%=listaProfesores.get(i).getEmail()%></a></td>
                    <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesores.get(i).getIdProfesor()%>"><%=listaProfesores.get(i).getTelefono()%></a></td>
                    <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesores.get(i).getIdProfesor()%>"><%=listaProfesores.get(i).getImporte()%></a></td>
                </tr>

        <%
                }
           }
           else
           {
        %>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
        <%
        }
        %>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
	</table>

	</div>

    <div class="tituloGrande">FICHA PROFESOR</div>

    <div class="marco2">



     <%
        Profesor unProfesor = (Profesor)session.getAttribute("profesor");
        String idProfesor = "";
        String nombre = "";
        String apellidos = "";
        String email = "";
        String telefono = "";
        String importe = "";
        int idTipoInstalacion = 0;

        if (unProfesor != null)
        {
            idProfesor = String.valueOf(unProfesor.getIdProfesor());
            nombre = unProfesor.getNombre();
            apellidos = unProfesor.getApellidos();
            email = unProfesor.getEmail();
            telefono = unProfesor.getTelefono();
            idTipoInstalacion = unProfesor.getIdTipoInstalacion();

            importe = String.valueOf(unProfesor.getImporte());
        }
     %>


     <form autocomplete="off" action="confProfesorMIBServlet" method="post">
        <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="texto_formu" width="15%">Nombre:</td>
                <td width="30%"  class="celda_campo"><label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" value="<%=nombre%>" /></label></td>
                <td class="texto_formu" width="15%">Apellidos:</td>
                <td width="30%"  class="celda_campo"><label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40" value="<%=apellidos%>" /></label></td>
            </tr>
            <tr>
                <td class="texto_formu" width="15%">Email:</td>
                <td width="30%"  class="celda_campo"><label><input name="email" type="text" class="campo_texto" id="email" size="40" value="<%=email%>" /></label></td>
                <td class="texto_formu" width="15%">Telefono:</td>
                <td width="30%"  class="celda_campo"><label><input name="telefono" type="text" class="campo_texto" id="telefono" size="40" value="<%=telefono%>" /></label></td>
            </tr>

            <tr>
                <td class="texto_formu" width="15%">Tipo actividad:</td>
                <td width="30%"  class="celda_campo">


          <select name="tipoInstalacion" class="campo_texto" id="select">
             
                <%
                if (listaTipoInstalacion != null)
                {
                    for(int i = 0; i<listaTipoInstalacion.size();i++)
                    {
                       if (listaTipoInstalacion.get(i).getIdTipoInstalacion()== idTipoInstalacion)
                       {
            %>
                        <option name="tipoInstalacion" value="<%=listaTipoInstalacion.get(i).getIdTipoInstalacion()%>" selected ><%=listaTipoInstalacion.get(i).getNombre()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="tipoInstalacion" value="<%=listaTipoInstalacion.get(i).getIdTipoInstalacion()%>" ><%=listaTipoInstalacion.get(i).getNombre()%></option>
            <%
                        }
                    }
                }
            %>
            </select>

          </td>
          <td class="texto_formu" width="15%">Precio / Hora:</td>
                <td width="30%"  class="celda_campo"><label><input name="importe" type="text" class="campo_texto" id="importe" size="40" value="<%=importe%>" /></label></td>

            </tr>

        </table>
	</div>

                <input type="hidden" id="profesorId" name="profesorId" value=<%=idProfesor%> /><br/>

    <div class="capas_botones">
    	<input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar2()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
        <input name="enviar" type="submit" class="boton" id="modificacion" value="Modificacion" onclick="return validar2()" />
    </div>
    </form>


        <%
            ArrayList<ProfesorHorario> listaProfesorHorario = (ArrayList<ProfesorHorario>)session.getAttribute("listaProfesorHorario");

        %>


    <div class="msg_titulos"><img src="imagenes/page_white_edit.png" width="16" height="16" />Horarios no disponibles</div>

    <form autocomplete="off" action="confProfesorHorarioMIBServlet" method="post">
    <div class="msg_formulario">
     <div class="listado">
	    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	 <td class="titulos_listado">Fecha inicio</td>
   	 <td class="titulos_listado">Fecha fin</td>
   	 <td class="titulos_listado">Hora inicio</td>
   	 <td class="titulos_listado">Hora fin</td>
   	 <td class="titulos_listado">Observaciones</td>
  	  </tr>

          <%
            if (listaProfesorHorario != null)
            {
                    String strFechaInicio = "";
                    String strFechaFin = "";
                    String observaciones ="";
                for (int i = 0; i<listaProfesorHorario.size();i++)
                {
                    strFechaInicio = String.valueOf(listaProfesorHorario.get(i).getFechaInicio());
                    String arrFechaInicio[] = strFechaInicio.split("-");
                    strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                    strFechaFin = String.valueOf(listaProfesorHorario.get(i).getFechaFin());
                    String arrFechaFin[] = strFechaFin.split("-");
                    strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];

                    if(!listaProfesorHorario.get(i).getObservaciones().equals("null"))
                        observaciones = listaProfesorHorario.get(i).getObservaciones();

          %>

            <tr>
                <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesorHorario.get(i).getIdProfesor()%>&var2=<%=listaProfesorHorario.get(i).getIdProfesorHorario()%>"><%=strFechaInicio%></a></td>
                <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesorHorario.get(i).getIdProfesor()%>&var2=<%=listaProfesorHorario.get(i).getIdProfesorHorario()%>"><%=strFechaFin%></a></td>
                <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesorHorario.get(i).getIdProfesor()%>&var2=<%=listaProfesorHorario.get(i).getIdProfesorHorario()%>"><%=listaProfesorHorario.get(i).getHoraInicio()%></a></td>
                <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesorHorario.get(i).getIdProfesor()%>&var2=<%=listaProfesorHorario.get(i).getIdProfesorHorario()%>"><%=listaProfesorHorario.get(i).getHoraFin()%></a></td>
                <td class="texto_listado"><a href="confProfesorServlet?var=<%=listaProfesorHorario.get(i).getIdProfesor()%>&var2=<%=listaProfesorHorario.get(i).getIdProfesorHorario()%>"><%=listaProfesorHorario.get(i).getObservaciones()%></a></td>
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
	  </tr>

	</table>
	</div>


         <%
            ProfesorHorario unProfesorHorario =  (ProfesorHorario)session.getAttribute("unProfesorHorario");
            String strIdProfesorHorario = "";
            String strFechaInicio = "";
            String strFechaFin = "";
            String strHoraInicio = "";
            String strHoraFin = "";
            String strObservaciones = "";
            if (unProfesorHorario != null)
            {
                strIdProfesorHorario = String.valueOf(unProfesorHorario.getIdProfesorHorario());

                strFechaInicio = String.valueOf(unProfesorHorario.getFechaInicio());
                String[] arrFechaInicio = strFechaInicio.split("-");
                strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                strFechaFin = String.valueOf(unProfesorHorario.getFechaFin());
                String[] arrFechaFin = strFechaFin.split("-");
                strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];

                strHoraInicio = String.valueOf(unProfesorHorario.getHoraInicio());
                strHoraFin = String.valueOf(unProfesorHorario.getHoraFin());
                strObservaciones = unProfesorHorario.getObservaciones();

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
    	<td class="texto_formu" width="15%">Observaciones:</td>
    	<td colspan="3"  class="celda_campo">
    	  <label>
    	    <textarea name="observaciones" cols="120" rows="3" class="campo_texto" id="observaciones"><%=strObservaciones%></textarea>
    	  </label>		</td>
		</tr>
  	</table>
	</div>
    </div>


    <input type="hidden"  name="profesorHorarioId" value=<%=strIdProfesorHorario%> />
     <input type="hidden"  name="idProfesor" value=<%=idProfesor%> />
  

    <div class="capas_botones">
	<input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" />
        <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
    </div>
    </form>
   </div>

</div>
	<div style="clear:both;"></div>

</div>
</body>
</html>

