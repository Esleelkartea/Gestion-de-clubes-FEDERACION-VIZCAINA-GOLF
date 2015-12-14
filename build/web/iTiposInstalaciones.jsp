<%-- 
    Document   : TiposInstalaciones
    Created on : 06-jun-2011, 10:14:27
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.Negocio.TipoInstalacion" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Intranet de Gestión de Clubes</title>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />

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
        miCampoTexto = document.getElementById("instalacion").value;
        if(miCampoTexto.length == 0)
        {
            alert("Debes rellenar el campo Tipo de Instalacion");
            return false;
        }
        else
        {
            return true;
        }
    }
    function validarBaja()
    {
        miCampoTexto = document.getElementById("instalacion").value;
        if(miCampoTexto.length == 0)
        {
            alert("Debes seleccionar un Tipo de Instalacion para dar de baja");
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

	<div id="submenu"><a href="IInstalacionesServlet">Gestión de Instalaciones</a> &nbsp;| &nbsp;Gestión Tipos de Instalaciones &nbsp;</div>

        <%
            ArrayList<TipoInstalacion> listaTipoInstalaciones = (ArrayList<TipoInstalacion>)session.getAttribute("listaTipoInstalacion");
        %>

	<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Tipos de Instalaciones</div>
	  <div class="listado">
	    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
            <td class="titulos_listado">Tipo Instalación</td>
   	</tr>

        <% if (listaTipoInstalaciones != null)
           {
                for (int i = 0; i<listaTipoInstalaciones.size(); i++)
                {
        %>
                <tr>
                    <td class="texto_listado"><a href="ITiposInstalacionesServlet?var=<%=listaTipoInstalaciones.get(i).getIdTipoInstalacion()%>"><%=listaTipoInstalaciones.get(i).getNombre()%></a></td>
                </tr>
        <%        
                }
           }
        %>
        	
	<tr>
            <td class="texto_listado">&nbsp;</td>
	</tr>

	</table>
	</div>


    <%
            TipoInstalacion tipoInstalaciones = (TipoInstalacion)session.getAttribute("tipoInstalacion");
            String nombre = "";
            String idTipoInstalaciones = "";
            if (tipoInstalaciones != null)
            {
                nombre = tipoInstalaciones.getNombre();
                idTipoInstalaciones = String.valueOf(tipoInstalaciones.getIdTipoInstalacion());
            }
     %>

    <form autocomplete="off" action="ITiposInstalacionesMIBServlet" method="post">
     <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Tipo instalación:</td>
 	  <td  class="celda_campo">
              <label><input name="tipoInstalacionesNombre" type="text" class="campo_texto" id="instalacion" size="40" value="<%=nombre%>" /></label>
              
          </td>
          
          <td class="texto_formu">&nbsp</td>
 	  <td class="celda_campo">&nbsp;</td>
 	  </tr>
  	</table>
    </div>
        
        
            <input type="hidden"  name="tipoInstalacionesId" value=<%=idTipoInstalaciones%> /><br/>
            
            <div class="capas_botones">
                <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
                <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
                <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
            </div>
        </form>

</div>
	<div style="clear:both;"></div>

</div>
</body>
</html>

