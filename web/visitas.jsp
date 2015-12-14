<%-- 
    Document   : visita
    Created on : 16-jun-2011, 10:54:12
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.Visita"%>
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

<script language="JavaScript">
function validar() {

     miCampoTexto = document.getElementById("nombre").value;
     miCampoTexto2 = document.getElementById("apellidos").value;
     miCampoTexto3 = document.getElementById("telefono").value;
     miCampoTexto4 = document.getElementById("direccion").value;
     miCampoTexto5 = document.getElementById("poblacion").value;
     miCampoTexto6 = document.getElementById("atendidoPor").value;
     miCampoTexto7 = document.getElementById("f_date_d").value;

     if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0 || miCampoTexto4.length == 0 || miCampoTexto5.length == 0 || miCampoTexto6.length == 0 || miCampoTexto7.length == 0)
     {
        alert("Debes rellenar todos los campos");
        return false;
     }
     else
     {
         return true;
     }
}

function validarBaja() {

     miCampoTexto = document.getElementById("nombre").value;
     miCampoTexto2 = document.getElementById("apellidos").value;
     miCampoTexto3 = document.getElementById("telefono").value;
     miCampoTexto4 = document.getElementById("direccion").value;
     miCampoTexto5 = document.getElementById("poblacion").value;
     miCampoTexto6 = document.getElementById("atendidoPor").value;
     miCampoTexto7 = document.getElementById("f_date_d").value;

     if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0 || miCampoTexto4.length == 0 || miCampoTexto5.length == 0 || miCampoTexto6.length == 0 || miCampoTexto7.length == 0)
     {
        alert("Debes seleccionar una Visita para dar de baja");
        return false;
     }
     else
     {
         return true;
     }
}

function validarBusqueda()
    {
        fechaInicio = document.getElementById("f_date_P").value;
        fechaFin = document.getElementById("f_date_f").value;
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
    	  <td class="tdmenuSelec">Visitas</td>
    	  <!--<td class="tdmenu"><a href="bonos.html">Bonos Deportivos</a></td>-->
    	  <td class="tdmenu"><a href="menPanelServlet">Mensajes</a></td>
    	  <td class="tdmenu"><a href="ventaGestVentaServlet">Venta Material</a></td>
    	  <td class="tdmenu"><a href="confSocioServlet">Configuración</a></td>
    	</tr>
    </table>
</div>

	<div id="contenido">

	<div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Buscador de Visitas</div>
	<form autocomplete="off" action="visitaBusquedaServlet" method="post">
        <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">

  	<tr>
  	  <td width="15%" class="texto_formu">Fecha de:</td>
  	  <td class="celda_campo">
  	    <label><input name="fechaInicio" type="text" class="campo_texto" id="f_date_P" readonly="1" />
  	      <img src="jscalendar/img.gif" id="f_trigger_P"/>

  	      <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_P",     // id of the input field
        ifFormat       :    "%d/%m/%Y",      // format of the input field
        button         :    "f_trigger_P",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
  	      </label></td>
  	  <td class="texto_formu">Fecha a:</td>
  	  <td class="celda_campo">
  	    <label><input name="fechaFin" type="text" class="campo_texto" id="f_date_f" readonly="1" />
  	      <img src="jscalendar/img.gif" id="f_trigger_f"/>

  	      <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_f",     // id of the input field
        ifFormat       :    "%d/%m/%Y",      // format of the input field
        button         :    "f_trigger_f",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
  	      </label>
  	    </td>
	  </tr>
    </table>
	</div>

	<div class="capas_botones">
            <input name="buscar" type="submit" class="boton" id="buscar" value="Buscar" onclick="return validarBusqueda()" /> &nbsp;&nbsp;
    
	</div>

        </form>

	<div class="listado">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	 <td class="titulos_listado">Fecha</td>
   	 <td class="titulos_listado">Nombre</td>
         <td class="titulos_listado">Apellidos</td>
   	 <td class="titulos_listado">Teléfono</td>
  	  <td class="titulos_listado">Dirección</td>
  	  <td class="titulos_listado">Población</td>
  	  <td class="titulos_listado">Atendido por</td>
  	  </tr>

          <%
            ArrayList<Visita> listaVisitas = (ArrayList<Visita>)session.getAttribute("listaVisitas");
            if (listaVisitas != null)
            {
                String strFecha = "";
                for (int i = 0; i<listaVisitas.size(); i++)
                {
                   strFecha = String.valueOf(listaVisitas.get(i).getFechaVisita());
                   String arrFecha[] = strFecha.split("-");
                   strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
                   
          %>
                <tr>
                    <td class="texto_listado"><a href="visitaServlet?var=<%=listaVisitas.get(i).getIdVisita()%>"><%=strFecha%></a></td>
                    <td class="texto_listado"><a href="visitaServlet?var=<%=listaVisitas.get(i).getIdVisita()%>"><%=listaVisitas.get(i).getNombre()%></a></td>
                    <td class="texto_listado"><a href="visitaServlet?var=<%=listaVisitas.get(i).getIdVisita()%>"><%=listaVisitas.get(i).getApellidos()%></a></td>
                    <td class="texto_listado"><a href="visitaServlet?var=<%=listaVisitas.get(i).getIdVisita()%>"><%=listaVisitas.get(i).getTelefono()%></a></td>
                    <td class="texto_listado"><a href="visitaServlet?var=<%=listaVisitas.get(i).getIdVisita()%>"><%=listaVisitas.get(i).getDireccion()%></a></td>
                    <td class="texto_listado"><a href="visitaServlet?var=<%=listaVisitas.get(i).getIdVisita()%>"><%=listaVisitas.get(i).getPoblacion()%></a></td>
                    <td class="texto_listado"><a href="visitaServlet?var=<%=listaVisitas.get(i).getIdVisita()%>"><%=listaVisitas.get(i).getAtendido()%></a></td>
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
	  <td class="texto_listado">&nbsp;</td>
	  </tr>

       <%
            }
       
       %>

	</table>
	</div>
    <div class="capas_botones_dcha">
      <form action="excelVisitas.jsp" method="post">
        <input name="exportarExcel" type="submit" class="boton2" id="exportarExcel" value="Exportar a Excel" />
      </form>
    </div>

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Datos Visita</div>

     <%
            Visita unaVisita= (Visita)session.getAttribute("unaVisita");
            String idVisita = "";
            String fecha ="";
            String nombre = "";
            String apellidos= "";
            String telefono = "";
            String direccion = "";
            String poblacion = "";
            String atendido = "";
            if (unaVisita != null)
            {
                idVisita = String.valueOf(unaVisita.getIdVisita());
                fecha = String.valueOf(unaVisita.getFechaVisita());
                String arrFecha[] = fecha.split("-");
                fecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];


                nombre = unaVisita.getNombre();
                apellidos = unaVisita.getApellidos();
                telefono = unaVisita.getTelefono();
                direccion = unaVisita.getDireccion();
                poblacion = unaVisita.getPoblacion();
                atendido = unaVisita.getAtendido();
                
            }
     %>



     <form autocomplete="off" action="visitaMIBServlet" method="post">
    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
                <td width="15%" class="texto_formu">Fecha:</td>
  	  <td class="celda_campo">
              <label><input name="fecha" type="text" class="campo_texto" id="f_date_d" readonly="1" value="<%=fecha%>" />
  	      <img src="jscalendar/img.gif" id="f_trigger_d"/>

  	      <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_d",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_d",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
  	      </label></td>
        </tr>



        <tr>
            <td class="texto_formu">Nombre:</td>
                <td class="celda_campo">
                    <label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" value="<%=nombre%>" /></label>
                </td>

             <td class="texto_formu">Apellidos:</td>
                <td class="celda_campo">
                    <label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40" value="<%=apellidos%>" /></label>
                </td>

        </tr>

  	<tr>
   		<td class="texto_formu">Teléfono:</td>
                <td class="celda_campo">
                    <label><input name="telefono" type="text" class="campo_texto" id="telefono" size="40" value="<%=telefono%>" /></label>
                </td>

                <td class="texto_formu">Dirección:</td>
                <td class="celda_campo">
                    <label><input name="direccion" type="text" class="campo_texto" id="direccion" size="40" value="<%=direccion%>" /></label>		</td>
  	</tr>
      <tr>
        <td class="texto_formu">Población:</td>
        <td class="celda_campo"><label><input name="poblacion" type="text" class="campo_texto" id="poblacion" size="40" value="<%=poblacion%>" /></label>		</td>
        <td class="texto_formu">Atendido por:</td>
        <td class="celda_campo">
            <label><input name="atendidoPor" type="text" class="campo_texto" id="atendidoPor" size="40" value="<%=atendido%>" /></label>
          </td>
      </tr>
    </table>
	</div>

    <input type="hidden"  name="idVisita" value=<%=idVisita%> /><br/>

    <%
        String resultado = (String)session.getAttribute("resultadoVisitas");
        if (resultado == null)
            resultado = "";
        session.setAttribute("resultadoVisitas", null);
    %>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
	<input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
    </div>
</form>

</div>
<div style="clear:both;"></div>
<h4 style="color:red"><%=resultado%></h4>

</div>
</body>
</html>

