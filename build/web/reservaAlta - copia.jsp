<%-- 
    Document   : reservaAlta
    Created on : 29-jun-2011, 11:57:29
    Author     : Julen
--%>

<%@page import="com.Negocio.InstalacionHorario"%>
<%@page import="com.Negocio.Reserva"%>
<%@page import="com.complementos.Buscador"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        campoBusqueda = document.getElementById("busqueda").value;
        if(campoBusqueda == "")
        {
            alert("Debes realizar primero la busqueda");
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

         <div id="submenu" align="right">Usuario: &nbsp;&nbsp;<%=nombreUsuario%></div>

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
  <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Alta de Reservas</div>
  <%
        ArrayList<Instalacion> listaInstalaciones = (ArrayList<Instalacion>)session.getAttribute("listaInstalaciones");
        ArrayList<TipoReserva> listaTipoReserva = (ArrayList<TipoReserva>)session.getAttribute("listaTipoReserva");
        ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");
        String instalacion = "";
  %>
  <div class="marco">

  <form autocomplete="off" action="reservaAltaMIBServlet" method="post">

    

    <div class="formulario">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        
          <%
            String fecha2 = (String)session.getAttribute("fecha");
            String strIdInstalacion = (String)session.getAttribute("instalacion");
            String nombreInstalacion = (String)session.getAttribute("nombreInstalacion");
            
            if(fecha2 != null)
            {
                String arrFecha[] = fecha2.split("-");
                fecha2 = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
                int idInstalacion = Integer.valueOf(strIdInstalacion);
                instalacion = strIdInstalacion;
          %>


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

                  <td class="texto_formu">Fecha:</td>
              <td class="texto_formu"><label><input name="fecha" type="text" class="campo_texto" id="f_date_t" readonly="1"  value="<%=fecha2%>" />
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

              </tr>

              <%
                }
                else
                {
              %>

              <tr>
              <td class="texto_formu">Instalación:</td>
              <td class="celda_campo">
              <label><select name="instalacion" class="campo_texto" id="instalacion">
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

                  </select></label></td>

                  <td class="texto_formu">Fecha:</td>
              <td class="texto_formu"><label><input name="fecha" type="text" class="campo_texto" id="f_date_t" readonly="1" />
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

              </tr>

              <%
              }

              %>
              <input id="busqueda" name="busqueda" type="hidden" value="<%=instalacion%>" />
              <tr>
                  <td>
                    <div class="capas_botones">
                        <input name="enviar" type="submit" class="boton" id="buscar" value="Buscar" onclick="return validarBusqueda()" />
                    </div>
                   </td>
              </tr>

            <tr>
              <td class="texto_formu" width="15%">Nombre socio:</td>
              <td class="celda_campo">
                <label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" /></label></td>
              <td class="texto_formu" width="15%">Apellidos socio:</td>
              <td class="celda_campo">
                <label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40" /></label></td>
              </tr>

            <tr>
              <td class="texto_formu">Forma de pago:</td>
              <td class="celda_campo"><label>
                      <select name="formaPago" class="campo_texto" id="formaPago" >
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

                      </select>
                  </label></td>

                   <td class="texto_formu">Estado:</td>
              <td class="celda_campo"><label>
                      <select name="estadoReserva" class="campo_texto" id="estadoReserva">
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
                      </select>
                  </label></td>
              </tr>

         <%
            if(nombreInstalacion != null)
            {
                    if(nombreInstalacion.contains("golf") || nombreInstalacion.contains("Golf") || nombreInstalacion.contains("GOLF"))
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
                                        %>
                                            <option value="<%=hora+":00"%>" ><%=hora %></option>
                                        <%
                                                 }
                                            }
                                        %>
                                    </select>
                                </td>
                                
                            </tr>
                            <input type="hidden" id="golf" name="golf" value="true" />
                 <%
                    session.setAttribute("nombreInstalacion", null);
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
                                        %>
                                            <option value="<%=hora+":00"%>" ><%=hora %></option>
                                        <%
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
                                        %>
                                            <option value="<%=hora+":00"%>" ><%=hora %></option>
                                        <%
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

        <%
            Boolean resultadoReservaSocio = (Boolean)session.getAttribute("resultadoReservaSocio");
            String strResultadoSocio ="";
            Boolean resultado = (Boolean)session.getAttribute("resultadoReserva");
            String strResultado ="";
            if (resultadoReservaSocio != null)
            {
                if (resultadoReservaSocio == false)
                {
                    strResultadoSocio = "El socio no existe ";
                }
                if(resultado != null)
                {
                    if(resultado == false)
                    {
                        strResultado =" No se ha podido realizar la inserción";
                    }
                    else
                    {
                        strResultado = "La insercion se ha realizado correctamente.";
                    }
                }
            }
            Boolean resultadoReservaDisponible = (Boolean)session.getAttribute("resultadoReservaDisponible");
            if(resultadoReservaDisponible != null)
            {
                strResultado = "La reserva en ese horario no es posible";
            }
            session.setAttribute("resultadoReservaDisponible", null);
        %>


      <div class="capas_botones">
          <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
      <span style="color:red"><%=strResultadoSocio%><%=strResultado%></span>
    </div>


        <!-------------------------------------------
                        CALENDARIO
         -------------------------------------------->

     <%
         ArrayList<Reserva> listaReservas = (ArrayList<Reserva>)session.getAttribute("listaReservaInstalacion");
         ArrayList<InstalacionHorario> listaInstalacionHorario = (ArrayList<InstalacionHorario>)session.getAttribute("listaInstalacionHorario");
         boolean busqueda = (Boolean)session.getAttribute("busqueda");


   %>

   <div class="listadoBot">
  <%
   String fecha = (String)session.getAttribute("fecha");
   
  if(fecha!= null)
  {
      if(nombreInstalacion.contains("Golf") || nombreInstalacion.contains("GOLF") || nombreInstalacion.contains("golf"))
      {

   %>


   <table width="90%" border="0" cellpadding="0" cellspacing="0" class="tablas2">
  	<tr>
   	 <td style="text-align:right; float:right;">

        </td>

        <%
            SimpleDateFormat sdfi=new SimpleDateFormat("yyyy-MM-dd");
            Calendar ci=Calendar.getInstance();
            ci.setTime(sdfi.parse(fecha));

            Buscador buscador = new Buscador();
            boolean enc;
            Calendar i=(Calendar)ci.clone();
            SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
            String st=s.format(i.getTime());
        %>

            <td class="titulos_general"><%=st%></td>

  	</tr>
        <%
                String hora = "";
                for(int j = 9;j<21;j++)
                {
                    for(int l = 0;l<3;l++)
                     {
                        switch(l)
                        {
                            case 0:
                                hora = j + ":00";
                                break;
                            case 1:
                                hora = j + ":20";
                                break;
                            case 2:
                                hora = j + ":40";
                                break;
                         }
        %>
        <tr>
            <td class="titulos_general"><%=hora%></td>

            <%
            Calendar k=(Calendar)ci.clone();

                    SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
                    String sst=ss.format(k.getTime());
                    enc = buscador.buscarFechasReserva2(listaReservas,sst, hora +":00");
                    if (enc)
                    {
            %>
                        <td class="texto_generalRes">RESERVADO</td>
            <%
                    }
                    else
                    {
                        boolean enc2 = buscador.buscarFechasInstalacion(listaInstalacionHorario, sst, hora + ":00");
                        if(enc2)
                        {
            %>
                            <td class="texto_generalNoDis">RESERVADO</td>
             <%
                        }
                        else
                        {
              %>
                        <td class="texto_general">LIBRE</td>
            <%
                        }
                    }
            %>
        </tr>

        <%
                }
            }
        %>

	</table>


   <%
      }
      else
      {
   %>


    <table width="90%" border="0" cellpadding="0" cellspacing="0" class="tablas2">
  	<tr>
   	 <td style="text-align:right; float:right;">

        </td>

        <%
            SimpleDateFormat sdfi=new SimpleDateFormat("yyyy-MM-dd");
            Calendar ci=Calendar.getInstance();
            ci.setTime(sdfi.parse(fecha));
            
            Buscador buscador = new Buscador();
            boolean enc;
            Calendar i=(Calendar)ci.clone();
            SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
            String st=s.format(i.getTime());
        %>

            <td class="titulos_general"><%=st%></td>           

  	</tr>
        <%
                for(int j = 9;j<21;j++)
                {
        %>
        <tr>
            <td class="titulos_general"><%=j+":00 a " + (j+1) + ":00"%></td>

            <%
            Calendar k=(Calendar)ci.clone();
               
                    SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
                    String sst=ss.format(k.getTime());
                    enc = buscador.buscarFechasReserva(listaReservas,sst, j +":00");
                    if (enc)
                    {
            %>
                        <td class="texto_generalRes">RESERVADO</td>
            <%
                    }
                    else
                    {
                        boolean enc2 = buscador.buscarFechasInstalacion(listaInstalacionHorario, sst, j + ":00");
                        if(enc2)
                        {
            %>
                            <td class="texto_generalNoDis">RESERVADO</td>
             <%
                        }
                        else
                        {
              %>
                        <td class="texto_general">LIBRE</td>
            <%
                        }
                    }              
            %>
        </tr>

        <%
            }
        %>

	</table>
        <% }}

            session.setAttribute("fecha", null);
            session.setAttribute("instalacion", null);
            
            %>
    </div>
<div class="leyenda">
            <img src="imagenes/rojo.gif"/> &nbsp;Reservado&nbsp;&nbsp;&nbsp; <img src="imagenes/azul.gif"/> &nbsp;No Disponible por actividades programadas en las instalaciones
        </div>
   
        <!-------------------------------------------
                        FIN CALENDARIO
         -------------------------------------------->

    </form>


    </div>

</div>
<div style="clear:both;"></div>


</div>
</body>
</html>

