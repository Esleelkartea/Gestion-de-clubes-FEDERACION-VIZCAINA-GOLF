<%-- 
    Document   : eReservaAlta
    Created on : 30-jun-2011, 8:56:42
    Author     : Julen
--%>

<%@page import="com.Negocio.Curso"%>
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

  <div id="submenu">Alta de Reservas &nbsp;|  &nbsp;<a href="eReservaGestionServlet">Listado de Reservas</a>&nbsp;|&nbsp;<a href="eReservaProfesorServlet">Reservas de Profesor</a>&nbsp;|&nbsp;<a href="eReservaGestionProfesorServlet">Listado de Reservas de Profesor</a>&nbsp;|&nbsp;<a href="eCursosInscritosServlet">Alta de Cursos</a>&nbsp;|&nbsp;<a href="eReservaGestionCursoServlet">Listado de Cursos</a></div>
	<div id="contenido">
  <div class="titulos2"><img src="imagenes/page_white_edit.png"/>Alta de Reservas</div>

   <%
        ArrayList<Instalacion> listaInstalaciones = (ArrayList<Instalacion>)session.getAttribute("listaInstalaciones");
        ArrayList<TipoReserva> listaTipoReserva = (ArrayList<TipoReserva>)session.getAttribute("listaTipoReserva");
        ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");
        ArrayList<TipoInstalacion> listaTipoInstalaciones = (ArrayList<TipoInstalacion>)session.getAttribute("listaTipoInstalaciones");


  %>

  <div class="marco">

   <form autocomplete="off" action="eReservaAltaMIBServlet" method="post">

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
             <a style="color:red " href="eReservaAltaTipoServlet?varTipoInstalacion=<%=listaTipoInstalaciones.get(i).getIdTipoInstalacion()%>"><%=listaTipoInstalaciones.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                        }
                        else
                        {
            %>

             <a style="color: #008AFF" href="eReservaAltaTipoServlet?varTipoInstalacion=<%=listaTipoInstalaciones.get(i).getIdTipoInstalacion()%>"><%=listaTipoInstalaciones.get(i).getNombre()%></a>
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
             <a style="color: red" href="eReservaAltaInstalacionServlet?varInstalacion=<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getNombre()%></a>
             &nbsp;&nbsp;
              <%
                        }
                        else
                        {
            %>
              <a style="color: #008AFF" href="eReservaAltaInstalacionServlet?varInstalacion=<%=listaInstalaciones.get(i).getIdInstalacion()%>"><%=listaInstalaciones.get(i).getNombre()%></a>
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
              <br />

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
 
        </table>
	</div>
            
 </form>
         <!-------------------------------------------
                        CALENDARIO
         -------------------------------------------->

  <%
         ArrayList<Reserva> listaReservas = (ArrayList<Reserva>)session.getAttribute("listaReservaInstalacion");
         ArrayList<InstalacionHorario> listaInstalacionHorario = (ArrayList<InstalacionHorario>)session.getAttribute("listaInstalacionHorario");

         ArrayList<Curso> listaCursos = (ArrayList<Curso>)session.getAttribute("listaCursos");
   %>

  <div class="listadoBot">
  <%
   String fInicio = (String)session.getAttribute("fechaHoy");
   String fFin = (String)session.getAttribute("fechaFin");
   String nombreInstalacion = (String)session.getAttribute("nombreInstalacion");
  if(fInicio!= null)
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
            ci.setTime(sdfi.parse(fInicio));
            SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd");
            Calendar cf=Calendar.getInstance();
            cf.setTime(sdff.parse(fFin));

            Buscador buscador = new Buscador();
            boolean enc;
            Calendar i=(Calendar)ci.clone();
            for(;i.compareTo(cf)<=0;i.add(Calendar.DATE, 1) )
            {
                SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
                String st=s.format(i.getTime());
        %>

            <td class="titulos_general"><%=st%></td>
            <% } %>

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

                for(;k.compareTo(cf)<=0;k.add(Calendar.DATE, 1))
                {
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
                            boolean enc3 = buscador.buscarFechasCurso(listaCursos, sst, hora + ":00");
                            if(enc3)
                            {
              %>
                                <td class="texto_generalCurso">CURSOS</td>

              <%
                            }
                            else
                            {
              %>

              <td class="texto_general"><a href="eReservaAltaMIBServlet?varHora=<%=hora%>&varDia=<%=sst%>&enviar=reservaSimple">LIBRE</a></td>
            <%              }
                        }
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
            ci.setTime(sdfi.parse(fInicio));
            SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd");
            Calendar cf=Calendar.getInstance();
            cf.setTime(sdff.parse(fFin));

            Buscador buscador = new Buscador();
            boolean enc;
            Calendar i=(Calendar)ci.clone();
            for(;i.compareTo(cf)<=0;i.add(Calendar.DATE, 1) )
            {
                SimpleDateFormat s=new SimpleDateFormat("dd-MM-yyyy");
                String st=s.format(i.getTime());
        %>

            <td class="titulos_general"><%=st%></td>
            <% } %>

  	</tr>

        <%
                String hora;
                for(int j = 9;j<21;j++)
                {

                    hora = j + ":00";
        %>
        <tr>
            <td class="titulos_general"><%=hora%></td>

            <%
            Calendar k=(Calendar)ci.clone();

                for(;k.compareTo(cf)<=0;k.add(Calendar.DATE, 1))
                {
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
                        boolean enc2 = buscador.buscarFechasInstalacion(listaInstalacionHorario, sst, hora + ":00");
                        if(enc2)
                        {
            %>
                            <td class="texto_generalNoDis">RESERVADO</td>
             <%
                        }
                        else
                        {
                            boolean enc3 = buscador.buscarFechasCurso(listaCursos, sst, hora + ":00");
                            if(enc3)
                            {
              %>
                                <td class="texto_generalCurso">CURSOS</td>

              <%
                            }
                            else
                            {
              %>

              <td class="texto_general"><a href="eReservaAltaMIBServlet?varHora=<%=hora%>&varDia=<%=sst%>&enviar=reservaSimple">LIBRE</a></td>
            <%              }
                        }
                    }
                }
            %>
        </tr>

        <%

            }
        %>

	</table>
             <% }} %>
    </div>

    <div class="leyenda">
    <img src="imagenes/rojo.gif"/> &nbsp;Reservado&nbsp;&nbsp;&nbsp; <img src="imagenes/verde.gif"/> &nbsp;Cursos&nbsp;&nbsp;&nbsp; <img src="imagenes/azul.gif"/> &nbsp;No Disponible por actividades programadas en las instalaciones
    </div>

    <form action="eReservaAlta3.jsp">
        <div class="capas_botones">
            <input name="enviar" type="submit" class="boton" id="alta" value="Otras Fechas"/>
        </div>

    </form>
   
    </div>

</div>
<div style="clear:both;"></div>


</div>
</body>
</html>
