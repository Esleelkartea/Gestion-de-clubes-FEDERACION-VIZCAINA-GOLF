    <%-- 
    Document   : eSocios
    Created on : 08-jun-2011, 10:41:05
    Author     : Julen
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.Socio"%>
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

</script>

</head>

<body>
<div id="contenedor">
	<div class="cabecera"><img src="imagenes/cabeceraExtranet.gif" width="1024" height="84" alt="Intranet de Gestión de Clubes" /></div>

        <%
            Socio unSocio2 = (Socio)session.getAttribute("socioExtranet");
            String nombreSocio = unSocio2.getNombre();
        %>

         <div id="usu" align="right">Hola &nbsp;&nbsp;<%=nombreSocio%></div>

	<div id="menu">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    	  <td class="tdmenu"><a href="eReservaGestionServlet">RESERVAR</a></td>
    	  <td class="tdmenuSelec">DATOS SOCIO</td>
    	  <td class="tdmenu"><a href="eMenPanelServlet">PANEL DE MENSAJES</a></td>
    	</tr>
    </table>
  </div>
  <div id="contenido">

      <%
        Socio unSocio = (Socio)request.getSession().getAttribute("socio");
        Usuario unUsuario = (Usuario)request.getSession().getAttribute("usuario");
        String strIdSocio = ""; String strNumSocio = ""; String strDni = ""; String strNombre = ""; String strApellidos = ""; String strSexo = ""; String strFechaNac = "";
        String strProfesion = ""; String strAntiguedad = ""; String strIdFoto = ""; String strObservaciones = ""; String strIdTipoSocio = "";
        String strMartuberri = ""; String strDireccion = ""; String strCP = ""; String strPoblacion = ""; String strProvincia ="";
        String strTelefono1 = ""; String strTelefono2 = ""; String strFax = ""; String strEmail = ""; String strBanco = ""; String strNumCuenta = "";
        String strIdUsuario = "";
        String strNombreUsuario = ""; String strPassword = "";
        String strFoto = "";
        String strTipoPago = "";

        strIdSocio = String.valueOf(unSocio.getIdSocio());
        if(strIdSocio == null) strIdSocio ="";
        strNumSocio = unSocio.getNumSocio();
        if(strNumSocio == null) strNumSocio = "";
        strDni = unSocio.getDni();
        if(strDni == null) strDni ="";
        strNombre = unSocio.getNombre();
        if(strNombre == null) strNombre ="";
        strApellidos = unSocio.getApellidos();
        if(strApellidos == null) strApellidos ="";
        strDireccion = unSocio.getDireccion();
        if(strDireccion == null) strDireccion ="";
        strCP = unSocio.getCp(); if(strCP == null) strCP ="";
        strPoblacion = unSocio.getPoblacion();
        if(strPoblacion == null) strPoblacion ="";
        strProvincia = unSocio.getProvincia();
        if(strProvincia == null) strProvincia ="";
        strTelefono1 = unSocio.getTelefono1();
        if(strTelefono1 == null) strTelefono1 ="";
        strTelefono2 = unSocio.getTelefono2();
        if(strTelefono2 == null) strTelefono2 ="";
        if(strTelefono2.equals("null")) strTelefono2 ="";
        strFax = unSocio.getFax();
        if(strFax == null) strFax ="";
        if(strFax.equals("null")) strFax = "";
        strEmail = unSocio.getEmail();
        if(strEmail == null) strEmail ="";
        strBanco = unSocio.getBanco();
        if(strBanco == null) strBanco ="";
        strNumCuenta = unSocio.getNumCuenta();
        if(strNumCuenta == null) strNumCuenta ="";
        strObservaciones = unSocio.getObservaciones();
        if(strObservaciones.equals("null")) strObservaciones ="";
        strProfesion = unSocio.getProfesion();
        if(strProfesion == null) strProfesion ="";
        if(strProfesion.equals("null")) strProfesion = "";

        String fechaAlta;
        fechaAlta = String.valueOf(unSocio.getFechaAlta());
        String arrFecha[] = fechaAlta.split("-");
        fechaAlta = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

        String fechaNac;
        fechaNac = String.valueOf(unSocio.getFechaNac());
        String arrFechaNac[] = fechaNac.split("-");
        fechaNac = arrFechaNac[2] + "-" + arrFechaNac[1] + "-" + arrFechaNac[0];

        java.util.Calendar fecha = java.util.Calendar.getInstance();
        int anio = fecha.get(java.util.Calendar.YEAR);

        strAntiguedad = unSocio.getAntiguedad();
        if (strAntiguedad == null) strAntiguedad="";

        boolean martuberri = unSocio.isSocioMartuberri();

        strSexo = unSocio.getSexo();
        if (strSexo == null) strSexo="";

        strFoto = unSocio.getFoto().getUrl();
        strIdFoto = String.valueOf(unSocio.getFoto().getIdFoto());
        strTipoPago = String.valueOf(unSocio.getTipoPago().getModo());
        
        strIdUsuario = String.valueOf(unUsuario.getIdUsuario());

        strNombreUsuario = unUsuario.getNombre();
        if(strNombreUsuario == null) strNombreUsuario="";
        strPassword = unUsuario.getPassword();
        if (strPassword == null) strPassword="";
      %>

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Modificar Ficha de Socio</div>

    <form autocomplete="off" action="eSocioMIBServlet" method="post">
    <div class="marco2">
    <div class="titulos3">Datos Titular</div>


    <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
    	<td class="texto_formu" width="15%">Nombre:</td>
        <td width="30%"  class="celda_campo"><label><input name="nombre" type="text" class="campo_texto" id="nombreMIB" size="40" value="<%=strNombre%>" /></label></td>
		<td class="texto_formu">Apellidos:</td>
                <td class="celda_campo"><label><input name="apellidos" type="text" class="campo_texto" id="apellidosMIB" size="40" value="<%=strApellidos%>"  /></label></td>
  	</tr>
  	<tr>
   		<td class="texto_formu">Fecha alta:</td>
                <td class="celda_campo"><label><input name="fechaAlta" type="text" class="campo_texto" id="f_date_y" readonly="1" value="<%=fechaAlta%>" />
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
        <td class="celda_campo"><label><input name="numSocio" type="text" class="campo_texto" id="numSocioMIB" size="40" value="<%=strNumSocio%>"  /></label></td>
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

                        <label>Varón <input type="radio" name="sexo" id="radio" value="Varon" /></label>
                        <label> &nbsp;Mujer <input type="radio" name="sexo" id="radio" value="Mujer" /></label>
                <%
                    }
                    else
                    {
                        if(strSexo.equals("Mujer"))
                        {
                %>
                            <label>Varón <input type="radio" name="sexo" id="radio" value="Varon" /></label>
                            <label> &nbsp;Mujer <input type="radio" name="sexo" id="radio" value="Mujer" checked /></label>
                <%
                        }
                        else
                        {
                %>
                            <label>Varón <input type="radio" name="sexo" id="radio" value="Varon" checked /></label>
                            <label> &nbsp;Mujer <input type="radio" name="sexo" id="radio" value="Mujer" /></label>
                <%
                        }
                      }
                %>


        </td>
  	  </tr>
      <tr>
   		<td class="texto_formu">DNI:</td>
                <td class="celda_campo"><label><input name="dni" type="text" class="campo_texto" id="dniMIB" size="40" value="<%=strDni%>" /></label>		</td>
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
        <td class="celda_campo"><label><input name="profesion" type="text" class="campo_texto" id="profesionMIB" size="40" value="<%=strProfesion%>"  /></label></td>
    	<!--
            <td class="texto_formu">Foto:</td>
            <td class="celda_campo"><input name="foto" type="text" id="foto" value="" readonly="readonly" /></td>
         -->
    </tr>
    
    <tr>

      <td class="texto_formu">Observaciones:</td>
      <td colspan="3" class="celda_campo">
          <label><textarea name="observaciones" cols="100" rows="3" class="campo_texto" id="observacionesMIB" ><%=strObservaciones%></textarea></label>
      </td>
      <%
        if(!strFoto.equals("Sin foto"))
        {
      %>      
            <td colspan="3" class="celda_campo">
                <img src="<%=strFoto%>" height="110" width="80" />
            </td>
      <%
        }
      %>
      
      </tr>
     
    </table>
	</div>



    <div class="titulos3">Dirección y Cuenta bancaria</div>

    <div class="formulario">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
      <td class="texto_formu" width="15%">Dirección:</td>
      <td class="celda_campo"><label><input name="direccion" type="text" class="campo_texto" id="direccionMIB" size="40" value="<%=strDireccion%>" /></label></td>
	  <td class="texto_formu">CP:</td>
          <td class="celda_campo"><label><input name="cp" type="text" class="campo_texto" id="cpMIB" size="40" value="<%=strCP%>" /></label></td>
  	</tr>
  	<tr>
   	  <td class="texto_formu">Población:</td>
          <td class="celda_campo"><label><input name="poblacion" class="campo_texto" id="poblacionMIB" value="<%=strPoblacion%>" /></label></td>
      <td class="texto_formu">Provincia:</td>
      <td class="celda_campo"><label><input name="provincia" class="campo_texto" id="provinciaMIB" value="<%=strProvincia%>" /></label></td>
  	</tr>
	<tr>
   	  <td class="texto_formu">Teléfono1:</td>
          <td class="celda_campo"><label><input name="tel1" type="text" class="campo_texto" id="tel1MIB" size="40" value="<%=strTelefono1%>" /></label>		</td>
      <td class="texto_formu">Teléfono2:</td>
      <td class="celda_campo"><label><input name="tel2" type="text" class="campo_texto" id="tel2MIB" size="40" value="<%=strTelefono2%>" /></label>		</td>
  	</tr>
    <tr>
      <td class="texto_formu">Fax:</td>
      <td class="celda_campo"><label><input name="fax" type="text" class="campo_texto" id="faxMIB" size="40" value="<%=strFax%>" /></label></td>
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
      <td class="celda_campo"><label><input name="banco" type="text" class="campo_texto" id="bancoMIB" size="40" value="<%=strTipoPago%>" readonly="readonly" /></label></td>
    </tr>


    <input type="hidden"  name="idSocio" value="<%=strIdSocio%>"  />
    </table>
	</div>


     <div class="titulos3">Gestión Extranet</div>

    <div class="formulario">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Usuario:</td>
          <td class="celda_campo"><label><input name="usuario" type="text" class="campo_texto" id="usuarioMIB" size="40" value="<%=strNombreUsuario%>" /></label></td>
 	  <td class="texto_formu">Password:</td>
          <td class="celda_campo"><label><input name="password" type="text" class="campo_texto" id="passwordMIB" size="40" value="<%=strPassword%>" /></label></td>
        </tr>
        <input type="hidden"  name="idUsuario" value="<%=strIdUsuario%>"  />
        <input type="hidden"  name="idFoto" value="<%=strIdFoto%>"  />
  	</table>
	</div>

    </div>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" /></div>

</form>
  </div>


<div style="clear:both;"></div>

</div>
</body>
</html>
