<%-- 
    Document   : convUsuarioConvenios
    Created on : 10-jun-2011, 11:09:58
    Author     : Julen
--%>

<%@page import="com.dal.TipoConvenioDAL"%>
<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.UsuariosConvenioPresentacion"%>
<%@page import="com.Negocio.UsuariosConvenio"%>
<%@page import="com.Negocio.TipoConvenio"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<script language="JavaScript">
function validar() {

     miCampoTexto = document.getElementById("nombre").value;
     miCampoTexto2 = document.getElementById("apellidos").value;
     if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
     {
        alert("Debes rellenar los campos Nombre y Apellidos");
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
     if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
     {
        alert("Debes seleccionar un Socio para dar de baja");
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
    	  <td class="tdmenuSelec">Convenios & Usuario Extenos</td>
    	  <td class="tdmenu"><a href="visitaServlet">Visitas</a></td>
    	  <!--<td class="tdmenu"><a href="bonos.html">Bonos Deportivos</a></td>-->
    	  <td class="tdmenu"><a href="menPanelServlet">Mensajes</a></td>
    	  <td class="tdmenu"><a href="ventaGestVentaServlet">Venta Material</a></td>
    	  <td class="tdmenu"><a href="confSocioServlet">Configuración</a></td>
    	</tr>
    </table>
</div>

<div id="submenu">Gestión Socios de Convenios &nbsp;| &nbsp;<a href="convGestConveniosServlet">Gestión de Convenios</a></div>


    <%
            ArrayList<TipoConvenio> listaTipoConvenios= (ArrayList<TipoConvenio>)session.getAttribute("listaTipoConvenio");
            ArrayList<UsuariosConvenio> listaUsuarioConvenio= (ArrayList<UsuariosConvenio>)session.getAttribute("listaUsuarioConvenio");
    %>

	<div id="contenido">
	<div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Gestionar Socios Convenios</div>
    <div class="listado">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
        <tr>
          <td class="titulos_listado">Nº</td>
          <td class="titulos_listado">Nombre</td>
          <td class="titulos_listado">Apellidos</td>
          <td class="titulos_listado">Convenio</td>
        </tr>

        <%
           if (listaUsuarioConvenio != null)
           {
                for (int i = 0; i<listaUsuarioConvenio.size(); i++)
                {
        %>

        <tr>
            <td class="texto_listado"><a href="convUsuConvenioServlet?var=<%=listaUsuarioConvenio.get(i).getIdUsuariosConvenios()%>"><%=listaUsuarioConvenio.get(i).getIdSocio().getNumSocio()%></a></td>
            <td class="texto_listado"><a href="convUsuConvenioServlet?var=<%=listaUsuarioConvenio.get(i).getIdUsuariosConvenios()%>"><%=listaUsuarioConvenio.get(i).getIdSocio().getNombre()%></a></td>
            <td class="texto_listado"><a href="convUsuConvenioServlet?var=<%=listaUsuarioConvenio.get(i).getIdUsuariosConvenios()%>"><%=listaUsuarioConvenio.get(i).getIdSocio().getApellidos()%></a></td>
            <td class="texto_listado"><a href="convUsuConvenioServlet?var=<%=listaUsuarioConvenio.get(i).getIdUsuariosConvenios()%>"><%=listaUsuarioConvenio.get(i).getConvenio().getConvenio()%></a></td>
            
        </tr>

        <%
                }
           }
           else
           {
         %>
            <td class="texto_listado">&nbps;</td>
            <td class="texto_listado">&nbps;</td>
            <td class="texto_listado">&nbps;</td>
            <td class="texto_listado">&nbps;</td>
         <%
            }
         %>
  
      </table>
         
    </div>

        <%
            UsuariosConvenio unUsuarioConvenio = (UsuariosConvenio)session.getAttribute("unUsuarioConvenio");
            String idUsuarioConvenio = "";
            String idSocio = "";
            String numSocio = "";
            String nombre = "";
            String apellidos = "";
            boolean martiartu = false;
            String idConvenio = "";
            String convenio = "";

            if (unUsuarioConvenio != null)
            {
                idUsuarioConvenio = String.valueOf(unUsuarioConvenio.getIdUsuariosConvenios());
                idSocio = String.valueOf(unUsuarioConvenio.getIdSocio().getIdSocio());
                numSocio = unUsuarioConvenio.getIdSocio().getNumSocio();
                nombre = unUsuarioConvenio.getIdSocio().getNombre();
                apellidos = unUsuarioConvenio.getIdSocio().getApellidos();
                martiartu = unUsuarioConvenio.getIdSocio().isSocioMartuberri();
                idConvenio = String.valueOf(unUsuarioConvenio.getConvenio().getIdTipoConvenio());
                convenio = unUsuarioConvenio.getConvenio().getConvenio();
            }
        %>

        <form autocomplete="off" action="convUsuConvenioMIBServlet" method="post">

    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
          <td class="texto_formu">Nombre:</td>
   	  <td class="celda_campo"><label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" value="<%=nombre%>" /></label></td>
          <td class="texto_formu">Apellidos:</td>
          <td class="celda_campo"><label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40" value="<%=apellidos%>" /></label></td>
        </tr>
	<tr>
	  <td class="texto_formu">Convenio:</td>
	  <td class="celda_campo"><label>

          <%
                if(unUsuarioConvenio != null)
                {
          %>
                <select name="convenio" class="campo_texto" id="convenio">
          <%
                TipoConvenio tipoConvenio;
                for (int i = 0; i<listaTipoConvenios.size();i++)
                {
                    tipoConvenio = listaTipoConvenios.get(i);
                    if (tipoConvenio.getIdTipoConvenio()==unUsuarioConvenio.getConvenio().getIdTipoConvenio())
                    {
          %>
                <option value="<%=tipoConvenio.getIdTipoConvenio()%>" selected ><%=tipoConvenio.getConvenio() %></option>
          <%
                    }
                    else
                    {
          %>
                <option value="<%=tipoConvenio.getIdTipoConvenio()%>" ><%=tipoConvenio.getConvenio() %></option>

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

	    <select name="convenio" class="campo_texto" id="convenio">
                <%
                   TipoConvenio unTipoConvenio= null;
                   for (int i=0; i<listaTipoConvenios.size();i++)
                   {
                        unTipoConvenio = listaTipoConvenios.get(i);
                 %>
                 <option value="<%=unTipoConvenio.getIdTipoConvenio()%>" ><%=unTipoConvenio.getConvenio() %></option>
                 <%
                    }
                 %>
             </select>

              <%
                }
              %>
               
	    </label></td>
	  <td class="texto_formu">&nbsp;</td>
	  <td class="celda_campo">&nbsp;</td>
	  </tr>
    </table>
	</div>
             
              <input type="hidden"  name="idUsuarioConvenio" value="<%=idUsuarioConvenio%>"  />
              <input type="hidden"  name="idSocio" value="<%=idSocio%>"  />           
              
    <%
        Boolean resultado = (Boolean)session.getAttribute("resultado");
        String strResultado ="";
        if (resultado != null)
        {
            if (resultado == false)
            {
                strResultado = "El socio no existe";
            }
        }
        request.getSession().setAttribute("resultado", null);
    %>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
	<input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
        <span style="color:red"><%=strResultado%></span>
         
    </div>
    
        </form>

</div>
<div style="clear:both;"></div>


</div>
</body>
</html>

