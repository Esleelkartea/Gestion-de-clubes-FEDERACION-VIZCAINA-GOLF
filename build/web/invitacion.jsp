<%-- 
    Document   : invitacion
    Created on : 15-jun-2011, 11:17:41
    Author     : Julen
--%>

<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.Invitacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.Negocio.TipoPago"%>
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
    function validarInvitacion()
    {
        miCampoTexto = document.getElementById("nombre").value;
        miCampoTexto2 = document.getElementById("apellidos").value;
        miCampoTexto3 = document.getElementById("nombreInvitado").value;
        miCampoTexto4 = document.getElementById("cantidad").value;
        miCampoTexto5 = document.getElementById("importe").value;
     
        if(miCampoTexto.length==0 || miCampoTexto2.length==0 || miCampoTexto3.length==0 || miCampoTexto4.length==0 || miCampoTexto5.length==0)
        {
            alert("Debes rellenar todos los campos");
            return false;
        }
        else
        {
            if(isNaN(miCampoTexto4) || isNaN(miCampoTexto5))
            {
                alert("Debes introducir datos numéricos en los campos Cantidad e Importe");
                return false;
            }
            else
            {
                return true;
            }
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
    	  <td class="tdmenuSelec">Invitaciones</td>
    	  <td class="tdmenu"><a href="convUsuConvenioServlet">Convenios & Usuario Extenos</a></td>
    	  <td class="tdmenu"><a href="visitaServlet">Visitas</a></td>
    	  <!--<td class="tdmenu"><a href="bonos.html">Bonos Deportivos</a></td>-->
    	  <td class="tdmenu"><a href="menPanelServlet">Mensajes</a></td>
    	  <td class="tdmenu"><a href="ventaGestVentaServlet">Venta Material</a></td>
    	  <td class="tdmenu"><a href="confSocioServlet">Configuración</a></td>
    	</tr>
    </table>
</div>

 <%      
            ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");

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

	<div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Buscador de Invitaciones</div>
        <form autocomplete="off" action="invitacionBusquedaServlet" method="post">
        <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
            <td class="texto_formu" width="15%">Nombre:</td>
            <td class="celda_campo"><label><input name="nombre" type="text" class="campo_texto" id="socio" size="40" /></label></td>
            <td class="texto_formu">Apellidos:</td>
            <td class="celda_campo"><label><input name="apellidos" type="text" class="campo_texto" id="invitado" size="40" /></label></td>
  	</tr>

        <tr>
            <td class="texto_formu">Invitado:</td>
            <td class="celda_campo"><label><input name="invitado" type="text" class="campo_texto" id="invitado" size="40" /></label></td>
  	</tr>

  	<tr>
  	  <td class="texto_formu">Fecha de:</td>
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
            <input name="buscar" type="submit" class="boton" id="buscar" value="Buscar" onclick="return validarBusqueda()" />
	</div>

        </form>

	<div class="listado">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	 <td class="titulos_listado">Fecha</td>
   	 <td class="titulos_listado">Nombre</td>
         <td class="titulos_listado">Apellidos</td>
   	 <td class="titulos_listado">Invitado</td>
  	 <td class="titulos_listado">Cantidad</td>
  	 <td class="titulos_listado">Importe</td>
  	</tr>

        <%
            ArrayList<Invitacion> listaInvitaciones = (ArrayList<Invitacion>)session.getAttribute("listaInvitaciones");
            if (listaInvitaciones != null)
            {
                int cantidad = 0;
                double importe = 0.0;
                String strFecha = "";
                for (int i = 0; i<listaInvitaciones.size(); i++)
                {
                   cantidad += listaInvitaciones.get(i).getCantidad();
                   importe += listaInvitaciones.get(i).getImporte();
                   strFecha = String.valueOf(listaInvitaciones.get(i).getFecha());
                   String arrFecha[] = strFecha.split("-");
                   strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
        %>
                <tr>
                    <td class="texto_listado"><%=strFecha%></td>
                    <td class="texto_listado"><%=listaInvitaciones.get(i).getNombre()%></td>
                    <td class="texto_listado"><%=listaInvitaciones.get(i).getApellidos()%></td>
                    <td class="texto_listado"><%=listaInvitaciones.get(i).getInvitado()%></td>
                    <td class="texto_listado"><%=listaInvitaciones.get(i).getCantidad()%></td>
                    <td class="texto_listado"><%=listaInvitaciones.get(i).getImporte()%></td>
                </tr>
        <%
                }
        %>
        <tr>
	  <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
          <td class="texto_listado">&nbsp;</td>
          <td class="texto_listado_total">Cantidad total: <%=cantidad%></td>
	  <td class="texto_listado_total">Importe total: <%=importe%></td>
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
	  <td class="texto_listado_total">&nbsp;</td>
	  <td class="texto_listado_total">&nbsp;</td>
	  </tr>

          <%
           }
           %>
	</table>
	</div>


    <div class="capas_botones_dcha">
        <form action="excelInvitacion.jsp" method="post">
            <input name="exportarExcel" type="submit" class="boton2" id="exportarExcel" value="Exportar a Excel" />
        </form>
    </div>


   <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Venta de Invitaciones</div>
   <div class="marco">

   <form autocomplete="off" action="invitacionVenderServlet" method="post">

     <div class="formulario">
       <table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Nombre:</td>
 	  <td class="celda_campo">
 	    <label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" /></label>		</td>
          <td class="texto_formu" width="15%">Apellidos:</td>
 	  <td class="celda_campo">
 	    <label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40" /></label>		</td>

         </tr>
         
      <tr>
 	  <td class="texto_formu" width="15%">Invitado:</td>
 	  <td class="celda_campo">
 	    <label><input name="invitado" type="text" class="campo_texto" id="nombreInvitado" size="40" /></label>		</td>
 	  <td class="texto_formu">Cantidad:</td>
 	  <td class="celda_campo">
 	    <label><input name="cantidad" type="text" class="campo_texto" id="cantidad" size="40" /></label>		</td>
 	  </tr>
      <tr>
        <td class="texto_formu">Importe:</td>
        <td class="celda_campo"><label>
          <input name="importe" type="text" class="campo_texto" id="importe" size="40" />
        </label></td>
         	<td class="texto_formu">Forma de pago:</td>
  	  <td class="celda_campo">
              <select name="formaPago" class="campo_texto" id="formaPago">
              <%
                if(listaTipoPago != null)
                {
                    for (int i = 0; i<listaTipoPago.size(); i++)
                    {
              %>
                    <option value="<%=listaTipoPago.get(i).getIdTipoPago()%>"><%=listaTipoPago.get(i).getModo()%></option>
              <%
                    }
                }
              %>

              </select></td>
      </tr>
 	</table>
	</div>

       <%
        Boolean resultadoSocio = (Boolean)session.getAttribute("resultadoSocioInvitacion");
        Boolean resultado = (Boolean)session.getAttribute("resultadoInvitacion");
        String strResultado ="";
        String strResultadoSocio ="";
        if (resultadoSocio != null)
        {
            if (resultadoSocio == false)
            {
                strResultadoSocio = "El socio no existe. ";
            }
            if(resultado != null)
            {
                if(resultado == false)
                {
                    strResultado ="No se ha podido realizar la inserción";
                }
                else
                {
                    strResultado = "La insercion se ha realizado correctamente.";
                }
            }
        }
        session.setAttribute("resultadoSocioInvitacion", null);
        session.setAttribute("resultadoInvitacion", null);

    %>


    <div class="capas_botones"><input name="vender" type="submit" class="boton" id="vender" value="Vender" onclick="return validarInvitacion()" />
        <span style="color:red"><%=strResultadoSocio%><%=strResultado%></span>
    </div>
    </form>
   </div>


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

