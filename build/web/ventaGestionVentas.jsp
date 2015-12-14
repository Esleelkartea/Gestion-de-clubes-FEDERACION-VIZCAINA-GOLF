<%-- 
    Document   : ventaGestionVentas
    Created on : 20-jun-2011, 12:51:30
    Author     : Julen
--%>

<%@page import="com.Negocio.Producto"%>
<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.TipoPago"%>
<%@page import="com.Negocio.Venta"%>
<%@page import="com.Negocio.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Intranet de Gestión de Clubes</title>
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

		<link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-blue2.css" title="blue2" />
        <!-- main calendar program -->
        <script type="text/javascript" src="jscalendar/calendar.js"></script>
        <!-- language for the calendar -->
        <script type="text/javascript" src="jscalendar/lang/calendar-es.js"></script>
        <!-- the following script defines the Calendar.setup helper function, which makes
       adding a calendar a matter of 1 or 2 lines of code. -->
        <script type="text/javascript" src="jscalendar/calendar-setup.js"></script>

        <!--script para el arbol-->

<script  src="js/dhtmlxcommon.js"></script>
<script  src="js/dhtmlxtree.js"></script>
<script type="text/javascript" src="js/gestionCategorias.js"></script>

        <!--fin de script para el arbol-->


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
        miCampoTexto = document.getElementById("nombreSocio").value;
        miCampoTexto2 = document.getElementById("apellidosSocio").value;
        
        miCampoTexto4 = document.getElementById("unidades").value;
        
        miCampoTexto6 = document.getElementById("f_date_d").value;

        if(miCampoTexto.length == 0 || miCampoTexto2.length == 0 || miCampoTexto4.length == 0 || miCampoTexto6.length == 0)
        {
            alert("Debes rellenar todos los campos");
            return false;
        }
        else
        {
            if(isNaN(miCampoTexto4))
            {
                alert("El valor del campo Unidades debe ser numérico");
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
        fechaFin = document.getElementById("f_date_e").value;
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
    	  <td class="tdmenu"><a href="visitaServlet">Visitas</a></td>
    	  <!--<td class="tdmenu"><a href="bonos.html">Bonos Deportivos</a></td>-->
    	  <td class="tdmenu"><a href="menPanelServlet">Mensajes</a></td>
    	  <td class="tdmenuSelec">Venta Material</td>
    	  <td class="tdmenu"><a href="confSocioServlet">Configuración</a></td>
    	</tr>
    </table>
</div>
<div id="submenu">Gestión de Venta de Material &nbsp;| &nbsp;<a href="ventaGestCategoriaServlet">Gestión Categorías de Productos</a> &nbsp;| <a href="ventaGestProductoServlet">&nbsp;Gestión de Productos</a></div>
<%
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>)session.getAttribute("listaCategorias");
            ArrayList<TipoPago> listaTipoPago = (ArrayList<TipoPago>)session.getAttribute("listaTipoPago");
    %>

	<div id="contenido">
	<div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Buscador de Ventas de Material</div>

 <form autocomplete="off" action="ventaBusquedaServlet" method="post">
 <div class="formulario">

    <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
            <td class="titulos_listado">Categoria</td>
            <td class="titulos_listado">Subcategoria</td>
        </tr>

         <%
            if (listaCategorias != null)
           {

                for (int i = 0; i<listaCategorias.size(); i++)
                {

        %>
                <tr>
                    <td class="texto_listado"><a href="ventaGestVentaServlet?var=<%=listaCategorias.get(i).getIdCategoria()%>"><%=listaCategorias.get(i).getCategoria()%></a></td>
                    <td class="texto_listado"><a href="ventaGestVentaServlet?var=<%=listaCategorias.get(i).getIdCategoria()%>"><%=listaCategorias.get(i).getSubcategoria()%></a></td>
                </tr>
        <%
                }
           }
        %>

	
    </table>

<br/>

<%
            Categoria unaCategoria = (Categoria)session.getAttribute("unaCategoria");
            String idCategoria = "";
            String categoria = "";
            String subcategoria = "";

            if (unaCategoria != null)
            {
                categoria = unaCategoria.getCategoria();
                subcategoria = unaCategoria.getSubcategoria();
                idCategoria = String.valueOf(unaCategoria.getIdCategoria());

            }
     %>

	<table width="100%" border="0" cellpadding="0" cellspacing="0">

            <tr>
 	  <td class="texto_formu">Por Categoría:</td>
 	  <td  class="celda_campo"><label>
                  <input name="categoria" type="text" class="campo_texto" id="categoria" size="40" value="<%=categoria%>" />
 	    </label></td>
          <td class="texto_formu">Por Subcategoría:</td>
 	  <td  class="celda_campo"><label>
                  <input name="subcategoria" type="text" class="campo_texto" id="subcategoria" size="40" value="<%=subcategoria%>" />
 	    </label></td>
 	 </tr>


 	<tr>
    	<td class="texto_formu" width="15%">Por Nombre del socio:</td>
    	<td class="celda_campo">
		<label><input name="nombreSocio" type="text" class="campo_texto" id="socio" size="40" /></label></td>
		<td class="texto_formu" width="15%">Por Apellidos del socio:</td>
    	<td class="celda_campo">
		<label><input name="apellidosSocio" type="text" class="campo_texto" id="socio" size="40" /></label></td>
  	</tr>
	      <tr>

       <td class="texto_formu">Por producto: </td>
  	  <td class="celda_campo"><input name="producto" type="text" class="campo_texto" id="material" size="40" /></td>
  	  <td class="texto_formu">&nbsp;</td>
  	  <td class="celda_campo">&nbsp;</td>

	  </tr>
          
  	<tr>
  	  <td class="texto_formu">Fecha venta de:</td>
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
  	 <td class="texto_formu">Fecha venta a:</td>
  	  <td class="celda_campo">
  	    <label><input name="fechaFin" type="text" class="campo_texto" id="f_date_e" readonly="1" />
  	      <img src="jscalendar/img.gif" id="f_trigger_e"/>
  	      <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_e",     // id of the input field
        ifFormat       :    "%d/%m/%Y",      // format of the input field
        button         :    "f_trigger_e",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
	  </script>
  	      </label></td>
	  </tr>

          


    </table>
	</div>

            <div class="capas_botones"><input name="buscar" type="submit" class="boton" id="buscar" value="Buscar" onclick="return validarBusqueda()" /></div>
</form>
	<div class="listado">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
   	  <td class="titulos_listado">Producto</td>
          <td class="titulos_listado">Categoría Producto</td>
          <td class="titulos_listado">SubCategoría Producto</td>
  	  <td class="titulos_listado">Nombre</td>
          <td class="titulos_listado">Apellidos</td>
  	  <td class="titulos_listado">Fecha de venta</td>
  	  </tr>

          <%
            ArrayList<Venta> listaVenta= (ArrayList<Venta>)session.getAttribute("listaVenta");
            String strFecha;
            if (listaVenta != null)
            {

                for (int i = 0; i<listaVenta.size(); i++)
                {
                    strFecha = String.valueOf(listaVenta.get(i).getFecha());
                    String[] arrFecha = strFecha.split("-");
                    strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];
            %>

        	<tr>
                    <td class="texto_listado"><%=listaVenta.get(i).getNombreProducto()%></td>
                    <td class="texto_listado"><%=listaVenta.get(i).getNombreCategoria()%></td>
                    <td class="texto_listado"><%=listaVenta.get(i).getNombreSubcategoria()%></td>
                    <td class="texto_listado"><%=listaVenta.get(i).getNombreSocio()%></td>
                    <td class="texto_listado"><%=listaVenta.get(i).getApellidosSocio()%></td>
                    <td class="texto_listado"><%=strFecha%></td>
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
	  </tr>

           <%
           }
           %>

	</table>
	</div>

    <div class="capas_botones_dcha">

      <form action="excelVentas.jsp" method="post">
        <input name="exportarExcel" type="submit" class="boton2" id="exportarExcel" value="Exportar a Excel" />
      </form>
        
    </div>

    <%
        ArrayList<Producto> listaProductos = (ArrayList<Producto>)session.getAttribute("listaProductos");

    %>


    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Venta de Material</div>
    <div class="marco">
        <form autocomplete="off" action="ventaVenderServlet" method="post">
      <div class="formulario">

        <table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
 	  <td class="texto_formu" width="15%">Nombre del socio:</td>
 	  <td class="celda_campo">
 	    <label><input name="nombreSocio" type="text" class="campo_texto" id="nombreSocio" size="40" /></label></td>
          <td class="texto_formu" width="15%">Apellidos del socio:</td>
 	  <td class="celda_campo">
 	    <label><input name="apellidosSocio" type="text" class="campo_texto" id="apellidosSocio" size="40" /></label></td>
 	</tr>
        <tr>
          <td class="texto_formu">Fecha:</td>
 	  <td class="celda_campo"><label><input name="fecha" type="text" class="campo_texto" id="f_date_d" readonly="1" />
  	  <img src="jscalendar/img.gif" id="f_trigger_d"/>
  	  <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_d",     // id of the input field
        ifFormat       :    "%d/%m/%Y",      // format of the input field
        button         :    "f_trigger_d",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
	  </script>
  	  </label></td>
 	  </tr>
 	<tr>
 	  <td class="texto_formu">Producto:</td>
          <td class="celda_campo">
                <label><select name="producto" class="campo_texto" id="producto">
                <%
                    if (listaProductos != null)
                    {
                        for(int i = 0; i<listaProductos.size();i++)
                        {
                %>
                <option value="<%=listaProductos.get(i).getIdProducto()%>"><%=listaProductos.get(i).getProducto()%></option>
                <%
                        }
                    }    
                %>   
		</select></label>
       
          </td>
 	  </tr>
 	<tr>
 	  <td class="texto_formu">Unidades:</td>
 	  <td class="celda_campo"><input name="unidades" type="text" class="campo_texto" id="unidades" size="40" /></td>
          <!--
              <td class="texto_formu">Importe:</td>
              <td class="celda_campo"><input name="importe" type="text" class="campo_texto" id="importe" size="37" /></td>
          -->
          <td class="texto_formu">Modo de Pago:</td>
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
 	<tr>
     	  <td class="texto_formu">Pagado: &nbsp;
 	    <label>
 	      <input type="checkbox" name="pagado" id="pagado" />
 	      </label></td>
 	  <td class="celda_campo">&nbsp;</td>
 	  

 	  </tr>
  	    </table>
	</div>


        <%
        Boolean resultadoSocio = (Boolean)session.getAttribute("resultadoSocioVenta");
        Boolean resultadoProducto = (Boolean)session.getAttribute("resultadoProductoVenta");
        Boolean resultado = (Boolean)session.getAttribute("resultadoVenta");
        String strResultado ="";
        String strResultadoSocio ="";
        String strResultadoProducto ="";
        if (resultadoSocio != null)
        {
            if (resultadoSocio == false)
            {
                strResultadoSocio = "El socio no existe. ";
            }
            else
            {
                if(resultadoProducto != null)
                {
                    if(resultadoProducto == false)
                    {
                        strResultadoProducto ="No existe ese producto";
                    }
                    else
                    {
                        if (resultado != null)
                        {
                            if(resultado == false)
                            {
                                strResultado = "No se ha podido realizar la venta.";
                            }
                            else
                            {
                                strResultado = "La venta se ha realizado correctamente.";
                            }    
                        
                        }
                    }
                }
            }
         }
        session.setAttribute("resultadoSocioVenta", null);
        session.setAttribute("resultadoProductoVenta", null);
        session.setAttribute("resultadoVenta", null);
        //session.setAttribute("listaVenta", null);

    %>

    <div class="capas_botones">
        <input name="vender" type="submit" class="boton" id="vender" value="Vender" onclick="return validar()" />
      <span style="color:red"><%=strResultadoSocio%><%=strResultadoProducto%><%=strResultado%></span>
    </div>
    </form>
    </div>

  </div>
<div style="clear:both;"></div>

</div>
</body>
</html>

