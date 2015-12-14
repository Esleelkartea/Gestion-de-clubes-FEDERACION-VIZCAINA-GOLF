<%--
    Document   : socios
    Created on : 14-jun-2011, 16:57:06
    Author     : Julen
--%>

<%@page import="com.Negocio.TipoPago"%>
<%@page import="com.Negocio.Foto"%>
<%@page import="com.Negocio.Accion"%>
<%@page import="com.Negocio.SocioFamilia"%>
<%@page import="com.Negocio.TipoFamiliar"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.Negocio.Usuario"%>
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

<script language="JavaScript">
function validar()
{
    miCampoTexto = document.getElementById("nombreMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Nombre");
        return (false);
    }
    miCampoTexto = document.getElementById("apellidosMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Apellidos");
        return (false);
    }
    miCampoTexto = document.getElementById("f_date_y").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Fecha Alta");
        return (false);
    }
    miCampoTexto = document.getElementById("numSocioMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Número de Socio");
        return (false);
    }
    miCampoTexto = document.getElementById("dniMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo DNI");
        return (false);
    }
    miCampoTexto = document.getElementById("f_date_n").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Fecha de Nacimiento");
        return (false);
    }
    miCampoTexto = document.getElementById("usuarioMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Usuario");
        return (false);
    }
    miCampoTexto = document.getElementById("passwordMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Password");
        return (false);   
    }
    else
    {
        if(miCampoTexto.length <8)
        {
            alert("La contraseña debe tener 8 ó más caracteres");
            return false;
        }
    }   
    
    miCampoTexto = document.getElementById("direccionMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Dirección");
        return (false);
    }
    miCampoTexto = document.getElementById("cpMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo CP");
        return (false);
    }
    miCampoTexto = document.getElementById("poblacionMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Población");
        return (false);
    }
    miCampoTexto = document.getElementById("provinciaMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Password");
        return (false);
    }
    miCampoTexto = document.getElementById("tel1MIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Telefono1");
        return (false);
    }
    miCampoTexto = document.getElementById("bancoMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Banco");
        return (false);
    }
    miCampoTexto = document.getElementById("numCuentaMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Nº Cuenta");
        return (false);
    }
    miCampoTexto = document.getElementById("emailMIB").value;
    if(miCampoTexto.length == 0)
    {
        alert("Debes rellenar el campo Email");
        return (false);
    }
    else
    {
        return validarEmail(miCampoTexto);
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

function validarMensajeEmail()
{

    miCampoTexto0 = document.getElementById("nombreMIB").value;
    if(miCampoTexto0.length == 0)
    {
        alert("Debes seleccionar un socio");
        return false;
    }
    miCampoTexto = document.getElementById("asuntoEmail").value;
    miCampoTexto2 = document.getElementById("textoEmail").value;
    if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
    {
        alert("Debes rellenar los campos Asunto y Mensaje para enviar un Email");
        return false;
     }
     else
     {
         return true;
     }
}

function validarSMS()
{
    miCampoTexto0 = document.getElementById("nombreMIB").value;
    if(miCampoTexto0.length == 0)
    {
        alert("Debes seleccionar un socio");
        return false;
    }
    miCampoTexto = document.getElementById("asuntoSMS").value;
    miCampoTexto2 = document.getElementById("textoSMS").value;
    if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
    {
        alert("Debes rellenar los campos Asunto y Mensaje para enviar un Mensaje");
        return false;
     }
     else
     {
         return true;
     }
}

function validarAccion()
{
    miCampoTexto0 = document.getElementById("nombreMIB").value;
    if(miCampoTexto0.length == 0)
    {
        alert("Debes seleccionar un socio");
        return false;
    }
    miCampoTexto = document.getElementById("accion").value;
    miCampoTexto2 = document.getElementById("f_date_a").value;
    miCampoTexto3 = document.getElementById("f_date_s").value;
    if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto3.length == 0)
    {
        alert("Debes rellenar todos los campos para dar de Alta una Accion");
        return false;
     }
     else
     {
         return true;
     }
}

function validarRelacion()
{
    miCampoTexto0 = document.getElementById("nombreMIB").value;
    if(miCampoTexto0.length == 0)
    {
        alert("Debes seleccionar un socio");
        return false;
    }
    miCampoTexto = document.getElementById("nombreFamiliar").value;
    miCampoTexto2 = document.getElementById("apellidosFamiliar").value;
    if(miCampoTexto.length == 0 || miCampoTexto2.length == 0)
    {
        alert("Debes rellenar los campos Nombre y Apellidos del socio para crear una relación");
        return false;
    }
    else
    {
        return true;
    }
}

function validarBusqueda()
{
    cpInicio = document.getElementById("cpinicio").value;
    cpFin = document.getElementById("cpfin").value;
    if(cpInicio.length !=0 && cpFin !=0)
    {
        inicio = parseInt(cpInicio);
        fin = parseInt(cpFin);
        if(fin < inicio)
        {
            alert("El CP de final debe ser mayor que el CP de inicio")
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

</script>

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

</head>

<body>
<div id="contenedor">

	<div class="cabecera"><img src="imagenes/cabecera.gif" width="1024" height="95" alt="Intranet de Gestión de Clubes" /></div>

         <%
            Usuario unUsuario = (Usuario)session.getAttribute("usuario");
            String idUsuario = String.valueOf(unUsuario.getIdUsuario());
            String nombreUsuario = unUsuario.getNombre();
            java.util.Calendar fecha = java.util.Calendar.getInstance();
            int anio = fecha.get(java.util.Calendar.YEAR);
         %>

         <div id="usu" align="right">Usuario: &nbsp;&nbsp;<%=nombreUsuario%></div>

	<div id="menu">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    	  <td class="tdmenuSelec">Socios</td>
    	  <td class="tdmenu"><a href="IInstalacionesServlet">Instalaciones</a></td>
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

  <div id="contenido">

  <div class="titulos2"><img src="imagenes/page_white_edit.png" /> Buscador de Socios</div>

 <%
            ArrayList<TipoSocio> listaTipoSocio = (ArrayList<TipoSocio>)session.getAttribute("listaTipoSocio");
            ArrayList<String> listaPoblaciones = (ArrayList<String>)session.getAttribute("listaPoblaciones");
            ArrayList<String> listaProvincias = (ArrayList<String>)session.getAttribute("listaProvincias");
            ArrayList<TipoFamiliar> listaTipoFamiliares = (ArrayList<TipoFamiliar>)session.getAttribute("listaTipoFamiliares");
            ArrayList<Foto> listaFotos = (ArrayList<Foto>)session.getAttribute("listaFotos");
            ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");
        %>

        <form autocomplete="off" action="SociosBusquedaServlet" method="post">

	<div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
    	<td class="texto_formu" width="15%">Nombre:</td>
    	<td width="34%" class="celda_campo">
		<label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" /></label></td>
		<td width="16%" class="texto_formu">Apellidos:</td>
   		<td width="35%" class="celda_campo">
		<label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40" /></label></td>
  	</tr>
  	<tr>
   	<td class="texto_formu">Nº Socio:</td>
    	<td class="celda_campo">
            <label><input name="numSocio" type="text" class="campo_texto" id="numSocio" size="40" /></label></td>
    	<td class="texto_formu">Antigüedad:</td>
    	<td class="celda_campo">
		<label><select name="antiguedad" class="campo_texto" id="antiguedad">
                <option></option>
                 <%
                    for (int i = 1980; i<=anio;i++)
                    {
                 %>
                 <option value="<%=i%>" ><%=i%></option>
                 <%
                    }
                 %>
		</select></label></td>
  	</tr>

  	<tr>
  	  <td class="texto_formu">Población:</td>
  	  <td class="celda_campo">
  	  <label><select name="poblacion" class="campo_texto" id="poblacion">
                  <option value=""></option>
                  <%
                    if (listaPoblaciones != null)
                    {
                        for(int i = 0; i<listaPoblaciones.size();i++)
                        {
                    %>
                    <option value="<%=listaPoblaciones.get(i)%>"><%=listaPoblaciones.get(i)%></option>

                    <%
                        }
                    }
                    %>

              </select></label></td>
  	  <td class="texto_formu">Provincia:</td>
          <td class="celda_campo"><label><select name="provincia" class="campo_texto" id="provincia">
                <option value=""></option>
                <%
                if (listaProvincias!= null)
                {
                    for(int i = 0; i<listaProvincias.size();i++)
                    {
                %>
                    <option value="<%=listaProvincias.get(i)%>"><%=listaProvincias.get(i)%></option>

                <%
                    }
                }
                %>

          </select></label></td>

        </tr>
      <tr>
  	  <td class="texto_formu">C.P. inicio:</td>
          <td class="celda_campo"><label><input name="cpinicio" type="text" class="campo_texto" id="cpinicio" size="12" /></label></td>
  	  <td class="texto_formu">C.P. fin:</td>
          <td class="celda_campo"><label><input name="cpfin" type="text" class="campo_texto" id="cpfin" size="12" /></label></td>
      </tr>

      <tr>
    	<td class="texto_formu" width="15%">DNI:</td>
    	<td width="34%" class="celda_campo">
		<label><input name="DNI" type="text" class="campo_texto" id="DNI" size="40" /></label></td>
		<td width="16%" class="texto_formu">Sexo:</td>
   		<td width="35%" class="celda_campo"><select name="sexo" class="campo_texto" id="sexo">
   		  <option></option>
                  <option>Varon</option>
   		  <option>Mujer</option>
 		</select></td>
  	</tr>

    <tr>
    	<td class="texto_formu">Tipo de Socio:</td>
    	<td width="16%" class="celda_campo"><select name="tipoSocio" id="select">
            <option value=""></option>
            <%
                if (listaTipoSocio != null)
                {
                    for(int i = 0; i<listaTipoSocio.size();i++)
                    {
            %>
                <option value="<%=listaTipoSocio.get(i).getIdTipoSocio()%>"><%=listaTipoSocio.get(i).getNombre()%></option>

            <%
                    }
                }
            %>
            </select></td>
            
  	</tr>
    </table>
	</div>

            <div class="capas_botones"><input name="buscar" type="submit" class="boton" id="buscar" value="Buscar" onclick="return validarBusqueda()" /></div>

        </form>

	<div class="listado">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	  <td class="titulos_listado">Nº Socio &nbsp;</td>
   	  <td class="titulos_listado">DNI</td>   	  
  	  <td class="titulos_listado">Nombre</td>
          <td class="titulos_listado">Apellidos</td>
  	  <td class="titulos_listado">Antigüedad</td>
  	  <td class="titulos_listado">Dirección</td>
  	  <td class="titulos_listado">CP</td>
  	  <td class="titulos_listado">Población</td>
  	  <td class="titulos_listado">Provincia</td>
  	  <td class="titulos_listado">Teléfono1</td>
  	  <td class="titulos_listado">Teléfono2</td>
  	  <td class="titulos_listado">Fax</td>
 	</tr>

        <%
            ArrayList<Socio> listaSocios = (ArrayList<Socio>)session.getAttribute("listaSocios");
            if (listaSocios != null)
            {
                String strTelefono1="";
                String strTelefono2="";
                String strFax = "";

                for (int i = 0; i<listaSocios.size(); i++)
                {

                    if (listaSocios.get(i).getTelefono1()!=null) strTelefono1 = listaSocios.get(i).getTelefono1();
                    if (listaSocios.get(i).getTelefono2()!=null) strTelefono2 = listaSocios.get(i).getTelefono2();
                    if (listaSocios.get(i).getFax() != null) strFax = listaSocios.get(i).getFax();

        %>
            <tr>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getNumSocio()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getDni()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getNombre()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getApellidos()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getAntiguedad()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getDireccion()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getCp()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getPoblacion()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=listaSocios.get(i).getProvincia()%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=strTelefono1%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=strTelefono2%></a></td>
                    <td class="texto_listado"><a href="SociosServlet?var=<%=listaSocios.get(i).getIdSocio()%>"><%=strFax%></a></td>

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

            <form action="excelSocios.jsp" method="post">
                <input name="exportarExcel" type="submit" class="boton2" id="exportarExcel" value="Exportar a Excel" />
            </form>

        </div>

       <%
            Socio unSocio = (Socio)session.getAttribute("unSocio");
            String idSocio = "";
            String idUsuarioSocio = "";
            String email = "";

            if (unSocio != null)
            {
                idSocio = String.valueOf(unSocio.getIdSocio());
                idUsuarioSocio = String.valueOf(unSocio.getIdUsuario());
                email = unSocio.getEmail();
            }
       %>
       
       
   	

    <%
        String strNombre = ""; String strNumSocio = ""; String strDireccion = "";   String strPoblacion = ""; String strProvincia ="";
        String strCP = ""; String strTelefono1 = "";    String strTelefono2 = "";   String strFax = "";
        String strApellidos = ""; String strDNI = "";   String strProfesion = "";
        String strBanco = ""; String strNumCuenta = ""; String strEmail=""; String strObservaciones = "";
        String strAntiguedad = "";
        String fechaAlta = "";
        String fechaNac = "";
        String strFoto = "";
        String strSexo = "";
        String strNombreUsuario = ""; String strPassword = ""; String strIdUsuarioSocio = "";
        int idTipoSocio= 0;
        int idTipoPago = 0;
        boolean bMartuberri = false;
        int idFoto = 0;
        if (unSocio != null)
        {
            strNombre = unSocio.getNombre();
            strApellidos = unSocio.getApellidos();
            strDNI = unSocio.getDni();
            strPoblacion = unSocio.getPoblacion();
            strProvincia = unSocio.getProvincia();
            strNumSocio = unSocio.getNumSocio();
            strCP = unSocio.getCp();
            strDireccion = unSocio.getDireccion();
            strAntiguedad = unSocio.getAntiguedad();
            strSexo = unSocio.getSexo();

            fechaAlta = String.valueOf(unSocio.getFechaAlta());
            String arrFecha[] = fechaAlta.split("-");
            fechaAlta = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

            fechaNac = String.valueOf(unSocio.getFechaNac());
            String arrFechaNac[] = fechaNac.split("-");
            fechaNac = arrFechaNac[2] + "-" + arrFechaNac[1] + "-" + arrFechaNac[0];

            if (unSocio.getFoto().getUrl() != null)
            {
                strFoto = unSocio.getFoto().getUrl();
            }
           
            if (unSocio.getTelefono1() != null) strTelefono1 = unSocio.getTelefono1();
           
           
            if (unSocio.getBanco() != null) strBanco = unSocio.getBanco();
            if (unSocio.getNumCuenta() != null) strNumCuenta = unSocio.getNumCuenta();
            if (unSocio.getEmail() != null) strEmail = unSocio.getEmail();
            if (unSocio.getObservaciones() != null)
            {
                strObservaciones = unSocio.getObservaciones();
                if (strObservaciones.equals("null"))
                    strObservaciones = "";
            }
            else
                strObservaciones = "";
            
            if (unSocio.getFax() != null)
            {
                strFax = unSocio.getFax();
                if (strFax.equals("null"))
                    strFax = "";
            }
            else
                strFax = "";

             if (unSocio.getTelefono2() != null)
            {
                strTelefono2 = unSocio.getTelefono2();
                if (strTelefono2.equals("null"))
                    strTelefono2 = "";
            }
            else
                strTelefono2 = "";

             if (unSocio.getProfesion() != null)
            {
                strProfesion = unSocio.getProfesion();
                if (strProfesion.equals("null"))
                    strProfesion = "";
            }
            else
                strProfesion = "";


            idTipoSocio = unSocio.getTipo().getIdTipoSocio();
            idTipoPago = unSocio.getTipoPago().getIdTipoPago();
            bMartuberri = unSocio.isSocioMartuberri();

            idFoto = unSocio.getFoto().getIdFoto();

            Usuario usuarioSocio = (Usuario)session.getAttribute("usuarioSocio");
            strIdUsuarioSocio = String.valueOf(usuarioSocio.getIdUsuario());
            strNombreUsuario = usuarioSocio.getNombre();
            strPassword = usuarioSocio.getPassword();
        }

    %>


    <div class="tituloGrande">FICHA SOCIO</div>

     <div class="marco2"><div class="msg_titulos"><img src="imagenes/page_white_edit.png"/>Datos Titular</div>

    <form autocomplete="off" action="SocioMIBServlet" method="post">

    <div class="msg_formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
    	<td class="texto_formu" width="15%">Nombre:</td>
    	<td width="30%"  class="celda_campo"><label><input name="nombre" type="text" class="campo_texto" id="nombreMIB" size="40"  value="<%=strNombre%>" /></label></td>
		<td class="texto_formu">Apellidos:</td>
   		<td class="celda_campo"><label><input name="apellidos" type="text" class="campo_texto" id="apellidosMIB" size="40" value="<%=strApellidos%>" /></label></td>
  	</tr>
  	<tr>
   		<td class="texto_formu">Fecha alta:</td>
    	<td class="celda_campo"><label><input name="fechaAntiguedad" type="text" class="campo_texto" id="f_date_y" readonly="1" value="<%=fechaAlta%>" />
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
  	    </label>
        </td>
    	<td class="texto_formu">Nº Socio:</td>
    	<td class="celda_campo"><label><input name="numSocio" type="text" class="campo_texto" id="numSocioMIB" size="40" value="<%=strNumSocio%>" /></label></td>
  	</tr>
  	<tr>
  	   <td class="texto_formu">Antigüedad:</td>
  	  <td class="celda_campo">
  	    <select name="antiguedad" class="campo_texto" id="antiguedad">
                <%
                if(strAntiguedad.equals(""))
                {
                %>
                    <option value="" selected ></option>
                <%
                    for (int i = 1980; i<=anio;i++)
                    {
                 %>
                 <option value="<%=i%>" ><%=i%></option>
                 <%
                    }
                 }
                 else
                 {
                 %>
                 <option value=""></option>
                 <%
                    for (int i = 1980; i<=anio;i++)
                    {
                        if(strAntiguedad.equals(String.valueOf(i)))
                        {
                 %>
                        <option value="<%=i%>" selected ><%=i%></option>
                 <%
                        }
                        else
                        {
                 %>
                        <option value="<%=i%>" ><%=i%></option>
                 <%
                        }
                     }
                  }
                 %>
		</select>
        </td>

         <td colspan="2" class="texto_formu">
                <%
                    if (strSexo.equals(""))
                    {
                %>

                        <label>Varon <input type="radio" name="sexo" id="radio" value="Varon" /></label>
                        <label> &nbsp;Mujer <input type="radio" name="sexo" id="radio" value="Mujer" /></label>
                <%
                    }
                    else
                    {
                        if(strSexo.equals("Mujer"))
                        {
                %>
                            <label>Varon<input type="radio" name="sexo" id="radio" value="Varon" /></label>
                            <label> &nbsp;Mujer <input type="radio" name="sexo" id="radio" value="Mujer" checked /></label>
                <%
                        }
                        else
                        {
                %>
                            <label>Varon<input type="radio" name="sexo" id="radio" value="Varon" checked /></label>
                            <label> &nbsp;Mujer <input type="radio" name="sexo" id="radio" value="Mujer" /></label>
                <%
                        }
                      }
                %>
            </td>

  	  </tr>
      <tr>
   	<td class="texto_formu">DNI:</td>
    	<td class="celda_campo"><label><input name="dni" type="text" class="campo_texto" id="dniMIB" size="40" value="<%=strDNI%>" /></label>		</td>
    	<td class="texto_formu">F. nacimiento:</td>
    	<td class="celda_campo">
		<label><input name="fechaNacimiento" type="text" class="campo_texto" id="f_date_n" readonly="1" value="<%=fechaNac%>" />
  	    <img src="jscalendar/img.gif" id="f_trigger_n"/>

  	    <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_n",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_n",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
  	    </label>
        </td>
  	</tr>
    <tr>
   	<td class="texto_formu">Profesión:</td>
    	<td class="celda_campo"><label><input name="profesion" type="text" class="campo_texto" id="profesion" size="40" value="<%=strProfesion%>" /></label></td>
    	<td class="texto_formu">Foto:</td>
        <td class="celda_campo">
        <select name="foto" id="select">
            
             <%
                if (listaFotos != null)
                {
                    for(int i = 0; i<listaFotos.size();i++)
                    {
                       if (listaFotos.get(i).getIdFoto()==idFoto)
                       {
            %>
                        <option name="foto" value="<%=listaFotos.get(i).getIdFoto()%>" selected ><%=listaFotos.get(i).getNombre()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="foto" value="<%=listaFotos.get(i).getIdFoto()%>" ><%=listaFotos.get(i).getNombre()%></option>
            <%
                        }
                    }
                }
            %>
            </select>

        </td>
    </tr>

    <tr>
      <td class="texto_formu">Observaciones:</td>
      <td colspan="3" class="celda_campo">
      <label><textarea name="observaciones" cols="100" rows="3" class="campo_texto" id="observaciones" value="<%=strObservaciones%>" ><%=strObservaciones%></textarea></label>
      </td>

       <%
        if(!strFoto.equals("Sin foto") && !strFoto.equals(""))
        {
      %>
            <td colspan="3" class="celda_campo">
                <img id="imagen" src="<%=strFoto%>" height="110" width="80" />
            </td>
      <%
        }
      %>

      </tr>

      <tr>
    	<td class="texto_formu">Tipo de Socio:</td>
    	<td width="16%" class="celda_campo">
            <select name="tipoSocio" id="select">

             <%
                if (listaTipoSocio != null)
                {
                    for(int i = 0; i<listaTipoSocio.size();i++)
                    {
                       if (listaTipoSocio.get(i).getIdTipoSocio()==idTipoSocio)
                       {
            %>
                        <option name="tipoSocio" value="<%=listaTipoSocio.get(i).getIdTipoSocio()%>" selected ><%=listaTipoSocio.get(i).getNombre()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="tipoSocio" value="<%=listaTipoSocio.get(i).getIdTipoSocio()%>" ><%=listaTipoSocio.get(i).getNombre()%></option>
            <%
                        }
                    }
                }
            %>
            </select>
        </td>


        <!-- Eliminar Socio Martuberri -->
        <!-- <td width="35%" class="texto_formu">Socio Martuberri </td>
                <%
                /*if (unSocio != null)
                {
                    if (unSocio.isSocioMartuberri())
                    {*/
                %>
                   <!-- <td><label><input name="martuberri" type="checkbox" checked /></label></td> -->
                <%
                    /*}
                    else
                    {*/
                %>
                   <!-- <td><label><input name="martuberri" type="checkbox" /></label></td> -->
                <%
                    /*}
                }
                else
                {*/
                %>
                   <!-- <td><label><input name="martuberri" type="checkbox" /></label></td> -->
                <%
                 //}
                 %>
       <!-- Eliminar Socio Martuberri -->
  	</tr>

        </table>
	</div>

       <div class="msg_titulos"><img src="imagenes/page_white_edit.png"/>Gestión Extranet</div>

        <div class="formulario">
            <table width="98%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td class="texto_formu" width="15%">Usuario:</td>
                <td width="30%"  class="celda_campo"><label><input name="usuarioSocio" type="text" class="campo_texto" id="usuarioMIB" size="40"  value="<%=strNombreUsuario%>" /></label></td>
                <td class="texto_formu">Password:</td>
                <td class="celda_campo"><label><input name="usuarioPassword" type="text" class="campo_texto" id="passwordMIB" size="40" value="<%=strPassword%>" /></label></td>
  	</tr>
    </table>
	</div>

         <input type="hidden" name="idUsuarioSocio" value="<%=strIdUsuarioSocio%>" />

    <div class="msg_titulos"><img src="imagenes/page_white_edit.png"/>Dirección y Cuenta bancaria</div>

    <div class="msg_formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
            <td class="texto_formu" width="15%">Dirección:</td>
            <td class="celda_campo"><label><input name="direccion" type="text" class="campo_texto" id="direccionMIB" size="40" value="<%=strDireccion%>" /></label></td>
            <td class="texto_formu">CP:</td>
            <td class="celda_campo"><label><input name="cp" type="text" class="campo_texto" id="cpMIB" size="40" value="<%=strCP%>"  /></label></td>
  	</tr>
  	<tr>
            <td class="texto_formu">Población:</td>
            <td class="celda_campo"><label><input name="poblacion" class="campo_texto" id="poblacionMIB" size="40" value="<%=strPoblacion%>" /></label></td>
            <td class="texto_formu">Provincia:</td>
            <td class="celda_campo"><label><input name="provincia" class="campo_texto" id="provinciaMIB" size="40" value="<%=strProvincia%>" /></label></td>
  	</tr>
	<tr>
            <td class="texto_formu">Teléfono1:</td>
            <td class="celda_campo"><label><input name="tel1" type="text" class="campo_texto" id="tel1MIB" size="40" value="<%=strTelefono1%>" /></label>		</td>
            <td class="texto_formu">Teléfono2:</td>
            <td class="celda_campo"><label><input name="tel2" type="text" class="campo_texto" id="tel2" size="40" value="<%=strTelefono2%>" /></label>		</td>
  	</tr>
        <tr>
            <td class="texto_formu">Fax:</td>
            <td class="celda_campo"><label><input name="fax" type="text" class="campo_texto" id="fax" size="40" value="<%=strFax%>" /></label></td>
            <td class="texto_formu">Email:</td>
            <td class="celda_campo"><label><input name="email" type="text" class="campo_texto" id="emailMIB" size="40" value="<%=strEmail%>" /></label></td>
        </tr>
        <tr>
            <td class="texto_formu">Banco:</td>
            <td class="celda_campo"><label><input name="banco" type="text" class="campo_texto" id="bancoMIB" size="40" value="<%=strBanco%>" /></label></td>
            <td class="texto_formu">Nº Cuenta:</td>
            <td class="celda_campo"><label><input name="numCuenta" type="text" class="campo_texto" id="numCuentaMIB" size="40" value="<%=strNumCuenta%>" /></label></td>
        </tr>
        <tr>
            <td class="texto_formu">Tipo de Pago:</td>
            <td width="16%" class="celda_campo">
            <select name="tipoPago" id="select">

             <%
                if (listaTipoPago != null)
                {
                    for(int i = 0; i<listaTipoPago.size();i++)
                    {
                       if (listaTipoPago.get(i).getIdTipoPago()==idTipoPago)
                       {
            %>
                        <option name="tipoPago" value="<%=listaTipoPago.get(i).getIdTipoPago()%>" selected ><%=listaTipoPago.get(i).getModo()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="tipoPago" value="<%=listaTipoPago.get(i).getIdTipoPago()%>" ><%=listaTipoPago.get(i).getModo()%></option>
            <%
                        }
                    }
                }
            %>
            </select>
        </td>

        </tr>
    </table>
	</div>

         <input type="hidden" name="idFoto" value="<%=idFoto%>" />
      <input type="hidden" name="idSocio" value="<%=idSocio%>" />
      <input type="hidden" name="idUsuario" value="<%=idUsuario%>" />

      <%
        String resultado = (String)session.getAttribute("resultadoSocios");
        if (resultado == null)
            resultado = "";
        session.setAttribute("resultadoSocios", null);
    %>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
    <input name="enviar" type="submit" class="boton" id="baja" value="Baja" />
    <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
    <h5 style="color:red"><%=resultado%></h5>
    </div>

    </form>

    <div class="msg_titulos"><img src="imagenes/page_white_edit.png"/>Vincular Familia</div>
    <div class="msg_formulario">

   <form autocomplete="off" action="RelacionMIBServlet" method="post">

    <table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu">Nombre socio:</td>
 	  <td  class="celda_campo"><label><input name="nombreFamiliar" type="text" class="campo_texto" id="nombreFamiliar" size="40" /></label></td>
          <td class="texto_formu">Apellidos socio:</td>
 	  <td  class="celda_campo"><label><input name="apellidosFamiliar" type="text" class="campo_texto" id="apellidosFamiliar" size="40" /></label></td>
        </tr>
        <tr>
          <td  class="texto_formu">Parentesco:</td>
 	  <td  class="celda_campo">
      <label><select name="tipoFamiliar" id="select">

            <%
                if (listaTipoFamiliares != null)
                {
                    for(int i = 0; i<listaTipoFamiliares.size();i++)
                    {
            %>
                <option value="<%=listaTipoFamiliares.get(i).getIdTipoFamiliar()%>"><%=listaTipoFamiliares.get(i).getNombre()%></option>

            <%
                    }
                }
            %>

        </select></label></td>
 	  </tr>
  	</table>

        <input type="hidden" name="idSocio" value="<%=idSocio%>" />
        <%
            String resultadoRelacion = (String)session.getAttribute("relacionSocio");
            if (resultadoRelacion == null)
                resultadoRelacion = "";
            session.setAttribute("relacionSocio", null);
        %>
    <div class="capas_botones">
        <input name="crearRelacion" type="submit" class="boton" id="crearRelacion" value="Crear relacion" onclick="return validarRelacion()" />
    <h5 style="color:red"><%=resultadoRelacion%></h5>
    </div>
    </form>


      <div class="listado">
        <table width="94%" border="0" cellspacing="0" cellpadding="0" class="tablas"> <caption>Listado de familiares</caption>
          <tr>
            <td class="titulos_listado">Nombre</td>
            <td class="titulos_listado">Apellidos</td>
            <td class="titulos_listado">Tipo familiar</td>
          </tr>

          <!-- Aqui se cambian los estilos CORREGIDO -->
          <%

                ArrayList<SocioFamilia> listaFamiliaresSocio = (ArrayList<SocioFamilia>)session.getAttribute("listaFamiliaresSocio");
                if (listaFamiliaresSocio != null && listaFamiliaresSocio.size() != 0)
                {
                    for(int i = 0; i<listaFamiliaresSocio.size();i++)
                    {
             %>
                         <tr>
                             <td class="texto_listado"><%=listaFamiliaresSocio.get(i).getFamiliar().getNombre()%></td>
                             <td class="texto_listado"><%=listaFamiliaresSocio.get(i).getFamiliar().getApellidos()%></td>
                             <td class="texto_listado"><%=listaFamiliaresSocio.get(i).getTipoFamilia().getNombre()%></td>
                        </tr>
             <%
                    }
                  }
            %>
            <!-- Hasta aqui se cambian los estilos -->

          <tr>
             <td class="texto_listado">&nbsp;</td>
             <td class="texto_listado">&nbsp;</td>
             <td class="texto_listado">&nbsp;</td>
           </tr>
          </table>
      </div>

    </div>

     <div class="msg_titulos"><img src="imagenes/page_white_edit.png"/>Historial</div>

    <div class="msg_formulario">

     <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	   <td class="titulos_listado" width="25%">Fecha Inicio</td>
           <td class="titulos_listado" width="25%">Fecha Fin</td>
   	   <td class="titulos_listado" width="75%">Acción</td>
   	   </tr>

           <%
                ArrayList<Accion> listaAccionesSocio = (ArrayList<Accion>)session.getAttribute("listaAccionesSocio");
                if (listaAccionesSocio != null)
                {
                    String strFechaInicio = "";
                    String strFechaFin = "";
                    String[] arrFechaInicio;
                    String[] arrFechaFin;
                    for(int i = 0; i<listaAccionesSocio.size();i++)
                    {
                        strFechaInicio = String.valueOf(listaAccionesSocio.get(i).getFechaInicio());
                        arrFechaInicio = strFechaInicio.split("-");
                        strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];
                        strFechaFin = String.valueOf(listaAccionesSocio.get(i).getFechaFin());
                        arrFechaFin = strFechaFin.split("-");
                        strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];
             %>

            <tr>
                <td class="texto_listado"><%=strFechaInicio%></td>
                <td class="texto_listado"><%=strFechaFin%></td>
                <td class="texto_listado"><%=listaAccionesSocio.get(i).getNombre()%></td>
            </tr>

            <%
                    }
                }

            %>

            <tr>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
            </tr>

	</table>
            
      <form autocomplete="off" action="AccionMIBServlet" method="post">
        <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Fecha de:</td>
 	 <td class="celda_campo"><label><input name="fechaInicio" type="text" class="campo_texto" id="f_date_s" readonly="1" />
 	    <img src="jscalendar/img.gif" id="f_trigger_s"/>

 	    <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_s",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_s",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
 	    </label></td>
 <td class="texto_formu" width="15%">Fecha a:</td>
 	  <td class="celda_campo"><label><input name="fechaFin" type="text" class="campo_texto" id="f_date_a" readonly="1" />
 	    <img src="jscalendar/img.gif" id="f_trigger_a"/>

 	    <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_a",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_a",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
 	    </label></td>
 	  </tr>
 	<tr>
 <td class="texto_formu3">Acción:</td>
 	  <td class="celda_campo"><label>
        <input name="accion" type="text" class="campo_texto" id="accion" value="" size="44" />
 	  </label></td>

 	  <td class="texto_formu3">&nbsp;</td>
          <%
            String resultadoAccion = (String)session.getAttribute("accionSocio");
            if (resultadoAccion == null)
                resultadoAccion = "";
            session.setAttribute("accionSocio", null);
        %>
        <td class="celda_campo"><input name="enviar" type="submit" class="boton2" id="anadir" value="Alta" onclick="return validarAccion()" /></td>
        </tr>
          <tr>
              <h5 style="color:red"><%=resultadoAccion%></h5>
        </tr>

  	</table>
        <input type="hidden" name="idSocio" value="<%=idSocio%>" />
    </div>
    </form>


 </div>
    </div>

    <div class="marco">

   	<div class="msg_head"><img src="imagenes/bullet_toggle_plus.png" width="16" height="16" border="0" />Enviar E-mailing</div>
    <div class="msg_body">


   <form autocomplete="off" action="EmailMIBServlet" method="post">

    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">

     	<tr>
 	  <td class="texto_formu">Asunto:</td>
 	  <td  class="celda_campo"><input name="asunto" type="text" class="campo_texto" id="asuntoEmail" value="" size="120" /></td>
 	  </tr>
 	<tr>
 	  <td class="texto_formu">Mensaje:</td>
 	  <td  class="celda_campo"><textarea name="mensaje" cols="122" rows="3" class="campo_texto" id="textoEmail"></textarea></td>
 	  </tr>

  	</table>
    </div>

       <input type="hidden" name="email" value="<%=email%>"/>
       <div class="capas_botones"><input name="enviar" type="submit" class="boton" id="enviar" value="Enviar" onclick="return validarMensajeEmail()" />

   <%
    String error = (String)session.getAttribute("error");
    if(error != null)
    {
   %>
   <h5 style="color:red"><%=error%></h5>
   <%
    }
    session.setAttribute("error", null);
   %>
   </div>
    </form>
    </div>
    </div>
   	<div class="marco">

   	<div class="msg_head"><img src="imagenes/bullet_toggle_plus.png" width="16" height="16" border="0" />Enviar Smsing</div>
    <div class="msg_body">



        <form autocomplete="off" action="MensajeMIBServlet" method="post">


    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
 	  <td  class="texto_formu">Asunto:</td>
 	  <td class="celda_campo_uno"><input name="asunto" type="text" class="campo_texto" id="asuntoSMS" size="120" /></td>
 	</tr>
 	<tr>
 	  <td class="texto_formu">Mensaje:</td>
 	  <td  class="celda_campo"><textarea name="texto" cols="122" rows="3" class="campo_texto" id="textoSMS"></textarea></td>
 	  </tr>
 	</table>
    </div>
      <input type="hidden" name="idUsuarioSocio" value="<%=idUsuarioSocio%>" />
      <input type="hidden" name="idUsuario" value="<%=idUsuario%>" />
      <input type="hidden" name="idSocio" value="<%=idSocio%>" />

      <%
        String resultadoMensaje = (String)session.getAttribute("resultadoMensaje");
        if (resultadoMensaje == null)
            resultadoMensaje = "";
        session.setAttribute("resultadoMensaje", null);
        %>

        <div class="capas_botones"><input name="enviar" type="submit" class="boton" id="enviar" value="Enviar" onclick="return validarSMS()" />
    <h5 style="color:red"><%=resultadoMensaje%></h5>
    </div>
    </form>
    </div>

    </div>

  </div>

<div style="clear:both;"></div>

</div>
</body>
</html>
