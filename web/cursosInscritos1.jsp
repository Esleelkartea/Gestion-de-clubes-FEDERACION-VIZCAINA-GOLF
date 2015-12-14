<%-- 
    Document   : TiposInstalaciones
    Created on : 06-jun-2011, 10:14:27
    Author     : Julen
--%>

<%@page import="com.dal.CursoDAL"%>
<%@page import="com.Negocio.Socio"%>
<%@page import="com.dal.SocioDAL"%>
<%@page import="java.sql.Date"%>
<%@page import="com.Negocio.CursoInscrito"%>
<%@page import="com.Negocio.Curso"%>
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
function validar()
{
    nombre = document.getElementById("nombre").value;
    apellidos = document.getElementById("apellidos").value;
    fechaInscripcion = document.getElementById("f_date_y").value;
    if(nombre.length == 0 || apellidos.length == 0 || fechaInscripcion.length == 0)
    {
        alert("Debes rellenar todos los campos");
        return false;
    }
    else
    {
        return true;
    }

}

function validarBaja()
{
    idInscrito = document.getElementById("idInscrito").value;
    if (idInscrito.length == 0)
    {
        alert("Debes seleccionar un socio para dar de baja en el curso");
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

	<div id="submenu"><a href="cursosServlet">Gestión de Cursos</a> &nbsp;| &nbsp;<a href="TiposCursosServlet">Gestión Tipos de Cursos</a> &nbsp;| &nbsp;Gestión de Inscritos</div>

        <%
            ArrayList<TipoCurso> listaTipoCurso = (ArrayList<TipoCurso>)session.getAttribute("listaTipoCurso");
            ArrayList<Curso> listaCursos = (ArrayList<Curso>)session.getAttribute("listaCursos");
        %>

	<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Tipos de Cursos</div>
	 
     <div class="marco">

   <form autocomplete="off" method="post">

    <div class="formulario">

      <table width="100%" border="0" cellpadding="0" cellspacing="0">

          <tr>
              <td class="texto_formu">Tipo Curso:</td>
  	  <td class="celda_campo">


             <%
                if (listaTipoCurso != null)
                {
                    String idTipoCurso = (String)session.getAttribute("tipoCurso");
                    for(int i = 0; i<listaTipoCurso.size();i++)
                    {
                        if(listaTipoCurso.get(i).getIdTipoCurso() == Integer.parseInt(idTipoCurso))
                        {
             %>
             <a style="color:red " href="cursoTipoCursoServlet?varTipoCurso=<%=listaTipoCurso.get(i).getIdTipoCurso()%>"><%=listaTipoCurso.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                        }
                        else
                        {
            %>

             <a style="color: #008AFF" href="cursoTipoCursoServlet?varTipoCurso=<%=listaTipoCurso.get(i).getIdTipoCurso()%>"><%=listaTipoCurso.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                        }
                    }
                }
            %>
              </td>
          </tr>

           <tr>
              <td class="texto_formu">Curso:</td>
  	  <td class="celda_campo">


             <%
                String idCurso = (String)session.getAttribute("curso");
                if (listaCursos != null && listaCursos.size()!=0)
                {
                    
                    for(int i = 0; i<listaCursos.size();i++)
                    {
                        if(listaCursos.get(i).getCursoId() == Integer.parseInt(idCurso))
                        {
             %>
                          <a style="color: red" href="cursoCursoServlet?varCurso=<%=listaCursos.get(i).getCursoId()%>"><%=listaCursos.get(i).getCursoNombre()%></a>
                            &nbsp;&nbsp;
              <%
                        }
                        else
                        {
            %>

                        <a style="color: #008AFF" href="cursoCursoServlet?varCurso=<%=listaCursos.get(i).getCursoId()%>"><%=listaCursos.get(i).getCursoNombre()%></a>
                            &nbsp;&nbsp;
            <%
                        }
                    }
                }
                else
                {
            %>
                <span style="color: red" >No hay Cursos</span>
            <%
                }
            %>
              </td>
          </tr>

  	</table>
	</div>

 </form>

              <div class="listado">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	

        <%
            ArrayList<CursoInscrito> listaSociosInscritos = (ArrayList<CursoInscrito>)session.getAttribute("listaSociosInscritos");
            if(listaSociosInscritos != null && listaSociosInscritos.size() != 0)
            {
         %>
            <tr>
                    <td class="titulos_listado">Fecha Inscripcion</td>
                    <td class="titulos_listado">Nombre</td>
                    <td class="titulos_listado">Apellidos</td>
            </tr>

         <%
                String strFecha = "";
                SocioDAL socioDAL = new SocioDAL();
                Socio unSocio = null;
                int cant = 0;
                int total = 0;
                Curso unCurso = null;
                CursoDAL cursoDAL = new CursoDAL();
                unCurso = cursoDAL.getCurso(listaSociosInscritos.get(0).getIdCurso());
                total = unCurso.getNumMax();
                for(int i = 0; i<listaSociosInscritos.size();i++)
                {
                    Date dFecha = listaSociosInscritos.get(i).getFechaInscripcion();
                    strFecha = String.valueOf(dFecha);
                    String arrFecha[] = strFecha.split("-");
                    strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                    unSocio = socioDAL.getSocioId(listaSociosInscritos.get(i).getIdSocio());
                    cant++;
        %>
                
                <tr>
                    <td class="texto_listado"><a href="cursoCursoServlet?varCurso=<%=idCurso%>&var=<%=listaSociosInscritos.get(i).getIdCursoinscrito()%>"><%=strFecha%></a></td>
                    <td class="texto_listado"><a href="cursoCursoServlet?varCurso=<%=idCurso%>&var=<%=listaSociosInscritos.get(i).getIdCursoinscrito()%>"><%=unSocio.getNombre()%></a></td>
                    <td class="texto_listado"><a href="cursoCursoServlet?varCurso=<%=idCurso%>&var=<%=listaSociosInscritos.get(i).getIdCursoinscrito()%>"><%=unSocio.getApellidos()%></a></td>
                </tr>
        <%
                 }
        %>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado">&nbsp;</td>
                <td class="texto_listado_total">Total: <%=cant%> de <%=total%></td>
        <%
            }
            else
            {
        %>
            <span style="color: red" >No hay Socios Inscritos</span>

        <%
            }
        %>
	</table>
	</div>

       <div class="tituloGrande">FICHA INSCRIPCIÓN</div>

   <div class="marco2">



     <%
            
            CursoInscrito unaInscripcion = (CursoInscrito)session.getAttribute("unaInscripcion");;
            SocioDAL socioDAL = new SocioDAL();
            Socio unSocio = null;
            Date dFecha;
            String strFecha = "";
            String nombre = "";
            String apellidos = "";
            String idInscrito = "";
            if (unaInscripcion != null)
            {
                unSocio = socioDAL.getSocioId(unaInscripcion.getIdSocio());
                nombre = unSocio.getNombre();
                apellidos = unSocio.getApellidos();
                dFecha = unaInscripcion.getFechaInscripcion();
                strFecha = String.valueOf(dFecha);
                String arrFecha[] = strFecha.split("-");
                strFecha = arrFecha[2] + "-" + arrFecha[1] + "-" + arrFecha[0];

                idInscrito = String.valueOf(unaInscripcion.getIdCursoinscrito());

             }
     %>


     <form autocomplete="off" action="cursosInscritosMIBServlet" method="post">
        <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
            <td class="texto_formu">Nombre:</td>
            <td class="celda_campo">
            <label><input name="nombre" type="text" class="campo_texto" id="nombre" size="40" value="<%=nombre%>" /></label>		</td>

            <td class="texto_formu">Apellidos:</td>
            <td class="celda_campo">
            <label><input name="apellidos" type="text" class="campo_texto" id="apellidos" size="40" value="<%=apellidos%>" /></label>		</td>

        </tr>

          <tr>
          
  	  <td class="texto_formu" width="15%">Fecha Inscripcion:</td>
    	<td  class="celda_campo">
            <label><input name="fechaInscripcion" type="text" class="campo_texto" id="f_date_y" readonly="1" value="<%=strFecha%>" />
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
  	    </label>		</td>
  	  </tr>

  	</table>
	</div>

            <input type="hidden" id="idInscrito"  name="idInscrito" value="<%=idInscrito%>" /><br/>

        <%
            Boolean resultadoSocio = (Boolean)session.getAttribute("resultadoSocio");
            Boolean resultadoAsistencia = (Boolean)session.getAttribute("resultadoAsistencia");
            String strResultadoSocio = "";
            String strResultadoAsistencia = "";
            if (resultadoSocio != null)
            {
                if (resultadoSocio == false)
                {
                    strResultadoSocio = "El socio no existe. ";
                }
            }    

            if (resultadoAsistencia != null)
            {
                if (resultadoAsistencia == false)
                {
                    strResultadoAsistencia = "El numero de plazas esta completo. ";
                }
            }
      
            session.setAttribute("resultadoSocio", null);
            session.setAttribute("resultadoAsistencia", null);
        %>


    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
        <span style="color:red"><%=strResultadoSocio%><%=strResultadoAsistencia%></span>
    </div>
    </form>





   </div>

    </div>

</div>
	<div style="clear:both;"></div>

</div>
</body>
</html>

