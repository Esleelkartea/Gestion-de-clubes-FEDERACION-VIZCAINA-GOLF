<%-- 
    Document   : eMenAlta
    Created on : 15-jun-2011, 21:56:30
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.Socio"%>
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
        miCampoTexto = document.getElementById("destinatarios").value;
        miCampoTexto2 = document.getElementById("asunto").value;
        miCampoTexto3 = document.getElementById("texto").value;
        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0)
        {
            alert("Debes rellenar todos los campos");
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
    	  <td class="tdmenu"><a href="eReservaGestionServlet">RESERVAR</a></td>
    	  <td class="tdmenu"><a href="eSocioServlet"> DATOS SOCIO</a></td>
    	  <td class="tdmenuSelec">PANEL DE MENSAJES</td>
    	</tr>
    </table>
  </div>

  	<div id="submenu"><a href="eMenPanelServlet">Panel de Entrada</a> &nbsp;| &nbsp;Nuevo Mensaje</div>

  <div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" />Componer Mensaje</div>
     <form autocomplete="off" action="eMenAltaMIBServlet" method="post">
    <div class="formulario">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td  class="texto_formu">Destinatario(s):</td>
 	  <td class="celda_campo_uno">
 	    <label><input name="destinatarios" type="text" class="campo_texto" id="destinatarios" size="134" /></label></td>
 	  </tr>
 	<tr>
 	  <td  class="texto_formu">Asunto:</td>
 	  <td class="celda_campo_uno"><input name="asunto" type="text" class="campo_texto" id="asunto" size="134" /></td>
 	  </tr>
 	<tr>
 	  <td  class="texto_formu">Texto:</td>
 	  <td class="celda_campo_uno"><textarea name="texto" cols="136" rows="3" class="campo_texto" id="texto"></textarea></td>
 	  </tr>


  	</table>
	</div>

         <div class="capas_botones"><input name="enviar" type="submit" class="boton2" value="Enviar" onclick="return validar()" /></div>
    </form>

</div>
        <%
    String resultado = (String)session.getAttribute("resultadoMensaje");
    //boolean resultado = (Boolean)session.getAttribute("resultadoMensaje");
    String strResultado ="";
    if (resultado != null)
    {
        strResultado = resultado;
    }
session.setAttribute("resultadoMensaje", null);
%>
	<div style="clear:both;"></div>
        <h4 style="color:red"><%=strResultado%></h4>

</div>
</body>
</html>

