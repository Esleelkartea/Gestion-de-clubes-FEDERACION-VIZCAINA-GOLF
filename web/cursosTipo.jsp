<%-- 
    Document   : TiposInstalaciones
    Created on : 06-jun-2011, 10:14:27
    Author     : Julen
--%>

<%@page import="com.Negocio.TipoCurso"%>
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
        miCampoTexto = document.getElementById("tipoCursoNombre").value;
        if(miCampoTexto.length == 0)
        {
            alert("Debes rellenar el campo Tipo de Curso");
            return false;
        }
        else
        {
            return true;
        }
    }
    function validarBaja()
    {
        miCampoTexto = document.getElementById("tipoCursoNombre").value;
        if(miCampoTexto.length == 0)
        {
            alert("Debes seleccionar un Tipo de Curso para dar de baja");
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
          <td class="tdmenuSelec">Cursos</td>
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

	<div id="submenu"><a href="cursosServlet">Gestión de Cursos</a> &nbsp;| &nbsp;Gestión Tipos de Cursos&nbsp;| &nbsp;<a href="cursosInscritosServlet">Gestión de Inscritos</a></div>

        <%
            ArrayList<TipoCurso> listaTipoCurso = (ArrayList<TipoCurso>)session.getAttribute("listaTipoCurso");
        %>

	<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Tipos de Cursos</div>
	  <div class="listado">
	    <table width="90%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
            <td class="titulos_listado">Tipo Curso</td>
   	</tr>

        <% if (listaTipoCurso != null)
           {
                for (int i = 0; i<listaTipoCurso.size(); i++)
                {
        %>
                <tr>
                    <td class="texto_listado"><a href="TiposCursosServlet?var=<%=listaTipoCurso.get(i).getIdTipoCurso()%>"><%=listaTipoCurso.get(i).getNombre()%></a></td>
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
            TipoCurso unTipoCurso = (TipoCurso)session.getAttribute("tipoCurso");
            String nombre = "";
            String idTipoCurso = "";
            if (unTipoCurso != null)
            {
                nombre = unTipoCurso.getNombre();
                idTipoCurso = String.valueOf(unTipoCurso.getIdTipoCurso());
            }
     %>

    <form autocomplete="off" action="TiposCursoMIBServlet" method="post">
     <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Tipo curso:</td>
 	  <td  class="celda_campo">
              <label><input name="tipoCursoNombre" type="text" class="campo_texto" id="tipoCursoNombre" size="40" value="<%=nombre%>" /></label>
              
          </td>
          
          <td class="texto_formu">&nbsp</td>
 	  <td class="celda_campo">&nbsp;</td>
 	  </tr>
  	</table>
    </div>
        
        
            <input type="hidden"  name="tipoCursoId" value=<%=idTipoCurso%> /><br/>
            
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

