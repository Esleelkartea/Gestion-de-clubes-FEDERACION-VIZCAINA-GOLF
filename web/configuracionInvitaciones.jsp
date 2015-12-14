<%-- 
    Document   : configuracionInvitaciones
    Created on : 09-jun-2011, 16:24:48
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.TipoInvitacion"%>
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

<script language="JavaScript">
function validar() {

     miCampoTexto = document.getElementById("importeCuota").value;
     miCampoTexto2 = document.getElementById("visitas").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length==0)
     {
        alert("Debes rellenar los campos");
        return false;
     }
     else
     {
         if(isNaN(miCampoTexto) || isNaN(miCampoTexto2))
         {
            alert("Debes introducir datos numéricos en los campos");
            return false;
         }    
         else
         {
            return true;
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
<div id="submenu"><a href="confSocioServlet">Gestión de Socios</a> &nbsp;| &nbsp;<a href="confReservaServlet">Gestión de Reservas</a> &nbsp;| &nbsp;Gestión de Invitaciones&nbsp; |&nbsp; <a href="confPagoServlet">Gestión de Modos de Pago</a>&nbsp; |&nbsp;<a href="confProfesorServlet">Gestión de Profesores</a>&nbsp; |&nbsp;<a href="confArchivoServlet">Gestión de Archivos</a>&nbsp; |&nbsp;<a href="confUsuarioServlet">Gestión de Usuarios</a></div>

 <%
            TipoInvitacion tipoInvitacion = (TipoInvitacion)session.getAttribute("tipoInvitacion");
            String maxInv = "";
            String adulLab = "---";
            String adulFest = "---";
            String menLab = "---";
            String menFest = "---";

            if (tipoInvitacion != null)
            {

                if (tipoInvitacion.getVisitMax()!= 0)
                    maxInv = String.valueOf(tipoInvitacion.getVisitMax());
                if (tipoInvitacion.getAdulLaborable() != 0)
                    adulLab = String.valueOf(tipoInvitacion.getAdulLaborable()) + "€";
                if (tipoInvitacion.getAdulFestivo() != 0)
                    adulFest = String.valueOf(tipoInvitacion.getAdulFestivo()) + "€";
                if (tipoInvitacion.getMenLaborable() != 0)
                    menLab = String.valueOf(tipoInvitacion.getMenLaborable())+ "€";
                if (tipoInvitacion.getMenFestivo() != 0)
                    menFest = String.valueOf(tipoInvitacion.getMenFestivo()) + "€";
             }
%>

<div id="contenido">

	<div class="titulos2"><img src="imagenes/page_white_edit.png"/>Gestión Cuotas de Invitaciones</div>


       
  <form autocomplete="off" action="confInvitMIBServlet" method="post">

    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Tipo cuota:</td>
 	  <td width="30%"  class="celda_campo"><label>
 	    <select name="edad" id="select">
 	      <option>Adulto</option>
 	      <option>Menores 18 años</option>
 	      </select>
 	    </label></td>
 	  <td width="14%" class="texto_formu">Día semana:</td>
 	  <td width="42%" class="celda_campo"><label>
 	    <select name="dias" id="select">
 	      <option>Laborables</option>
 	      <option>Festivos</option>
        </select>
 	    </label></td>
 	  </tr>
 	<tr>
 	  <td class="texto_formu">Importe cuota:</td>
 	  <td  class="celda_campo"><label><input name="importeCuota" type="text" class="campo_texto" id="importeCuota" size="40" /></label></td>
 	  <td class="texto_formu">Visitas max. por año a precio normal:</td>
 	  <td class="celda_campo"><label><input name="antelacion" type="text" class="campo_texto" id="visitas" value="<%=maxInv%>" size="13" /></label></td>
 	  </tr>
  	</table>
    </div>
    <div class="capas_botones">
    	<input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" />
        <input name="enviar" type="submit" class="boton" id="modificacion" value="Modificacion" onclick="return validar()" />
   	  </div>
   </form>


    <div class="listado">
	<table width="40%" border="0" cellspacing="0" cellpadding="0">
  	<tr>
   	 <td>&nbsp;</td>
   	 <td class="titulos_general">LABORABLES</td>
   	 <td class="titulos_general">FESTIVOS</td>
  	  </tr>
	<tr>
    	<td class="titulos_general">Adulto</td>
        <td class="texto_general"><%=adulLab%></td>
        <td class="texto_general"><%=adulFest%></td>
    	</tr>
	<tr>
	  <td class="titulos_general">Menores de 18</td>
          <td class="texto_general"><%=menLab%></td>
          <td class="texto_general"><%=menFest%></td>
	  </tr>
	</table>
	</div>



  </div>
<div style="clear:both;"></div>

</div>
</body>
</html>

