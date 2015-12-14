<%-- 
    Document   : configuracionSocios
    Created on : 08-jun-2011, 11:24:09
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.TipoFamiliar"%>
<%@page import="com.Negocio.TipoSocio"%>
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
function validar() {

     miCampoTexto = document.getElementById("tipoParentesco").value;
     if(miCampoTexto.length == 0)
     {
        alert("Debes rellenar el campo de Tipo Parentesco");
        return false;
     }
     else
     {
         return true;
     }
}



</script>
<script language="JavaScript">
function validar2() {

     miCampoTexto = document.getElementById("tipoSocio").value;
     miCampoTexto2 = document.getElementById("cuota").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length==0)
     {
        alert("Debes rellenar los campos");
        return false;
     }
     else
     {
         if(isNaN(miCampoTexto2))
         {
            alert("Debes introducir un numero en el campo Cuota");
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
    miCampoTexto = document.getElementById("tipoSocio").value;
     miCampoTexto2 = document.getElementById("cuota").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length==0)
     {
        alert("Debes seleccionar un Tipo Usuario para dar de baja");
        return false;
     }
     else
     {
         return true;
     }
}

function validarBaja2()
{
     miCampoTexto = document.getElementById("tipoParentesco").value;
     if(miCampoTexto.length == 0)
     {
        alert("Debes seleccionar un Tipo Parentesco para dar de baja");
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
         <div id="submenu">Gestión de Socios &nbsp;| &nbsp;<a href="confReservaServlet">Gestión de Reservas</a> &nbsp;| &nbsp;<a href="confInvitacionServlet">Gestión de Invitaciones</a>&nbsp; |&nbsp; <a href="confPagoServlet">Gestión de Modos de Pago</a>&nbsp; |&nbsp;<a href="confProfesorServlet">Gestión de Profesores</a>&nbsp; |&nbsp;<a href="confArchivoServlet">Gestión de Archivos</a>&nbsp; |&nbsp;<a href="confUsuarioServlet">Gestión de Usuarios</a></div>

    <%
            ArrayList<TipoSocio> listaTipoSocios = (ArrayList<TipoSocio>)session.getAttribute("listaTipoSocio");
            ArrayList<TipoFamiliar> listaTipoFamiliar = (ArrayList<TipoFamiliar>)session.getAttribute("listaTipoFamiliar");
    %>


    <div id="contenido">

    <div class="marco">
	<div class="msg_head"><img src="imagenes/page_white_edit.png"/>Gestión  Tipos de Socio y Cuotas</div>

    <div class="msg_body">
    <div class="listado">
	<table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	 <td class="titulos_listado">Tipos de Socio</td>
   	 <td class="titulos_listado">Cuotas</td>
   	 </tr>


         <% if (listaTipoSocios != null)
           {
                for (int i = 0; i<listaTipoSocios.size(); i++)
                {
        %>
                <tr>
                    <td class="texto_listado"><a href="confSocioServlet?var=<%=listaTipoSocios.get(i).getIdTipoSocio()%>"><%=listaTipoSocios.get(i).getNombre()%></a></td>
                    <td class="texto_listado"><a href="confSocioServlet?var=<%=listaTipoSocios.get(i).getIdTipoSocio()%>"><%=listaTipoSocios.get(i).getCuota()%></a></td>
                </tr>
        <%
                }
           }
        %>
	

	    </table>
	</div>


        <%
            TipoSocio tipoSocio = (TipoSocio)session.getAttribute("tipoSocio");
            String idTipoSocio = "";
            String nombre = "";
            String cuota = "";
            if (tipoSocio != null)
            {
                idTipoSocio = String.valueOf(tipoSocio.getIdTipoSocio());
                nombre = tipoSocio.getNombre();
                cuota = String.valueOf(tipoSocio.getCuota());
            }
     %>

 <form autocomplete="off" action="confSocioMIBServlet" method="post">
    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Tipo Socio:</td>
          <td width="30%"  class="celda_campo"><label><input name="tipoSocio" type="text" class="campo_texto" id="tipoSocio" size="40" value="<%=nombre%>" /></label></td>
 	  <td width="14%" class="texto_formu">Cuota:</td>
          <td width="42%" class="celda_campo"><label><input name="cuota" type="text" class="campo_texto" id="cuota" size="40"value="<%=cuota%>"  /></label></td>
 	  </tr>
  	</table>
    </div>

     <input type="hidden"  name="tipoSociosId" value=<%=idTipoSocio%> /><br/>

     <div class="capas_botones">
    	<input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar2()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
        <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar2()" />
   	  </div>

     </form>
    </div>
    </div>


        <div class="marco">
	<div class="msg_head"><img src="imagenes/page_white_edit.png"/>Gestión  Parentescos</div>

    <div class="msg_body">

     <div class="listado">
	    <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
            <tr>
                <td class="titulos_listado">Tipos de Parentesco</td>
            </tr>

            <% if (listaTipoFamiliar != null)
                {
                    for (int i = 0; i<listaTipoFamiliar.size(); i++)
                    {
            %>
                    <tr>
                        <td class="texto_listado"><a href="confSocioServlet?var2=<%=listaTipoFamiliar.get(i).getIdTipoFamiliar()%>"><%=listaTipoFamiliar.get(i).getNombre()%></a></td>
                    </tr>
            <%
                    }
                }
            %>



	</table>
	</div>

      <%
            TipoFamiliar tipoFamiliar = (TipoFamiliar)session.getAttribute("tipoFamiliar");
            String idTipoFamiliar = "";
            String nombreTipoFamiliar = "";

            if (tipoFamiliar != null)
            {
                idTipoFamiliar = String.valueOf(tipoFamiliar.getIdTipoFamiliar());
                nombreTipoFamiliar = tipoFamiliar.getNombre();
            }
     %>

    <form autocomplete="off" action="confFamiliarMIBServlet" method="post">
        <div class="formulario">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td class="texto_formu" width="15%">Tipo Parentesco:</td>
                    <td width="30%"  class="celda_campo"><label><input name="tipoParentesco" type="text" class="campo_texto" id="tipoParentesco" size="40" value="<%=nombreTipoFamiliar%>" /></label></td>

                                     
                </tr>
            </table>
        </div>
        <input type="hidden"  name="tipoSociosId" value=<%=idTipoFamiliar%> /><br/>
        <div class="capas_botones">
            <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
            <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja2()" />
            <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
   	</div>
    </form>
    </div></div>
        
  </div>
<div style="clear:both;"></div>

</div>
</body>
</html>

