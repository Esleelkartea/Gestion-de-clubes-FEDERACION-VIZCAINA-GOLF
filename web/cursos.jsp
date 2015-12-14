<%-- 
    Document   : iInstalaciones
    Created on : 06-jun-2011, 10:50:13
    Author     : Julen
--%>

<%@page import="java.sql.Date"%>
<%@page import="com.Negocio.Profesor"%>
<%@page import="com.dal.TipoCursoDAL"%>
<%@page import="com.Negocio.TipoCurso"%>
<%@page import="com.Negocio.Curso"%>
<%@page import="com.Negocio.Usuario"%>
<%@page import="com.Negocio.InstalacionHorario"%>
<%@page import="com.Negocio.TipoInstalacion"%>
<%@page import="com.dal.TipoInstalacionDAL"%>
<%@page import="com.Negocio.Instalacion"%>
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
<script type="text/javascript">
     function validar()
     {
         campoNombre = document.getElementById("nombreCurso").value;
         fechaInicio = document.getElementById("f_date_y").value;
         fechaFin = document.getElementById("f_date_t").value;
         precio = document.getElementById("precio").value;
         fechaInscripcion = document.getElementById("f_date_d").value;
         numMax = document.getElementById("numMax").value;

        if(campoNombre.length == 0 || fechaInicio.length == 0 || fechaFin.length == 0 || precio.length == 0 || fechaInscripcion.length == 0 || numMax.length == 0)
        {
            alert("Debes rellenar todos los campos");
            return false;
        }
        else
        {
            if (isNaN(precio) || isNaN(numMax))
            {
                alert("Los campos Numero Maximo y Precio deben ser numéricos")
                return false;
            }
            else
            {
                return validarFecha();
            }
        }
     }


     function validarFecha()
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
                            return validarHora();
                        }
                    }
                    else
                    {
                        return validarHora();
                    }
                }
            }
            else
            {
                return validarHora();
            }
        }
     }



     function validarBaja()
     {
         miCampoTexto = document.getElementById("cursoId").value;
         if(miCampoTexto.length == 0)
         {
            alert("Debes seleccionar un Curso para dar de baja");
            return false;
        }
        else
        {
            return true;
        }
     }

     

function validarHora()
{
    miCampoTexto3 = document.getElementById("horaInicio").value;
    miCampoTexto4 = document.getElementById("horaFin").value;
    arrHoraInicio = miCampoTexto3.split(":");
    arrHoraFin = miCampoTexto4.split(":");
    miCampoTexto3 = arrHoraInicio[0];
    miCampoTexto4 = arrHoraFin[0];
    horaInicio = parseInt(miCampoTexto3);
    horaFin = parseInt(miCampoTexto4);
    if (horaInicio >= horaFin)
    {
        alert("La Hora Fin debe ser mayor que Hora Inicio")
        return  false;
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

         <div id="submenu">Gestión de Cursos &nbsp;| &nbsp;<a href="TiposCursosServlet">Gestión Tipos de Cursos</a> &nbsp;| &nbsp;<a href="cursosInscritosServlet">Gestión de Inscritos</a> </div>

        <%
            ArrayList<Curso> listaCursos = (ArrayList<Curso>)session.getAttribute("listaCursos");
            ArrayList<TipoCurso> listaTipoCursos = (ArrayList<TipoCurso>)session.getAttribute("listaTipoCursos");
            ArrayList<Instalacion> listaInstalaciones = (ArrayList<Instalacion>)session.getAttribute("listaInstalaciones");
            ArrayList<Profesor> listaProfesores = (ArrayList<Profesor>)session.getAttribute("listaProfesores");
            
        %>

	<div id="contenido">

    <div class="titulos2"><img src="imagenes/page_white_edit.png" width="16" height="16" /> Datos Curso</div>
	  <div class="listado">
	    <table width="97%" border="0" cellspacing="0" cellpadding="0" class="tablas">
  	<tr>
            <td class="titulos_listado">Tipo Curso</td>
            <td class="titulos_listado">Curso</td>
            <td class="titulos_listado">Fecha Inicio</td>
            <td class="titulos_listado">Fecha Fin</td>
            <td class="titulos_listado">Inscritos</td>
  	</tr>

        <% 
           if (listaCursos != null)
           {
                TipoCursoDAL tipoCursoDAL = new TipoCursoDAL();
                TipoCurso unTipoCurso = null;
                Date fechaInicio;
                Date fechaFin;
                for (int i = 0; i<listaCursos.size(); i++)
                {
                   fechaInicio = listaCursos.get(i).getFechaInicio();
                   String strFechaInicio = String.valueOf(fechaInicio);
                   String arrFechaInicio[] = strFechaInicio.split("-");
                   strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                   fechaFin = listaCursos.get(i).getFechaFin();
                   String strFechaFin = String.valueOf(fechaFin);
                   String arrFechaFin[] = strFechaFin.split("-");
                   strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];

                   unTipoCurso = tipoCursoDAL.getTipoCurso(listaCursos.get(i).getIdTipoCurso());
        %>
                <tr>
                    <td class="texto_listado"><a href="cursosServlet?var=<%=listaCursos.get(i).getCursoId()%>"><%=unTipoCurso.getNombre()%></a></td>
                    <td class="texto_listado"><a href="cursosServlet?var=<%=listaCursos.get(i).getCursoId()%>"><%=listaCursos.get(i).getCursoNombre()%></a></td>
                    <td class="texto_listado"><a href="cursosServlet?var=<%=listaCursos.get(i).getCursoId()%>"><%=strFechaInicio%></a></td>
                    <td class="texto_listado"><a href="cursosServlet?var=<%=listaCursos.get(i).getCursoId()%>"><%=strFechaFin%></a></td>
                    <td class="texto_listado"><a href="cursosServlet?var=<%=listaCursos.get(i).getCursoId()%>"><%=listaCursos.get(i).getNumApuntados()%></a></td>
                </tr>
        <%
                }
           }
        %>

	<tr>
          <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
          <td class="texto_listado">&nbsp;</td>
	  <td class="texto_listado">&nbsp;</td>
	</tr>

	</table>
  
	</div>

    <div class="tituloGrande">FICHA CURSO</div>
   
   <div class="marco2">



     <%
            Curso unCurso = (Curso)session.getAttribute("curso");
            String nombreCurso = "";
            String tarifa = "";
            String strIdInstalacion = "";
            String strIdTipoCurso = "";
            String strIdProfesor = "";
            String strFechaInicio = "";
            String strFechaFin = "";
            String strFechaIns = "";

            String strHoraInicio = "";
            String strHoraFin = "";
            
            String idCurso = "";

            int idProfesor = 0;
            int idInstalacion = 0;
            int idTipoCurso = 0;

            String strPrecio = "";
            String strNumMax = "";
            String strApuntados = "";
            

            if (unCurso != null)
            {
                nombreCurso = unCurso.getCursoNombre();
                tarifa = String.valueOf(unCurso.getImporte());
                strIdInstalacion = String.valueOf(unCurso.getIdInstalacion());
                strIdTipoCurso = String.valueOf(unCurso.getIdTipoCurso());

                strFechaInicio = String.valueOf(unCurso.getFechaInicio());
                String[] arrFechaInicio = strFechaInicio.split("-");
                strFechaInicio = arrFechaInicio[2] + "-" + arrFechaInicio[1] + "-" + arrFechaInicio[0];

                strFechaFin = String.valueOf(unCurso.getFechaFin());
                String[] arrFechaFin = strFechaFin.split("-");
                strFechaFin = arrFechaFin[2] + "-" + arrFechaFin[1] + "-" + arrFechaFin[0];

                strFechaIns = String.valueOf(unCurso.getFechaInscripción());
                String[] arrFechaIns = strFechaIns.split("-");
                strFechaIns = arrFechaIns[2] + "-" + arrFechaIns[1] + "-" + arrFechaIns[0];

                strHoraInicio = String.valueOf(unCurso.getHoraInicio());
                strHoraFin = String.valueOf(unCurso.getHoraFin());

                idProfesor = unCurso.getProfesorId();
                idInstalacion = unCurso.getIdInstalacion();
                idTipoCurso = unCurso.getIdTipoCurso();

                idCurso = String.valueOf(unCurso.getCursoId());

                strPrecio = String.valueOf(unCurso.getImporte());
                strNumMax = String.valueOf(unCurso.getNumMax());
                strApuntados = String.valueOf(unCurso.getNumApuntados());

                session.setAttribute("curso", null);
            }
     %>


     <form autocomplete="off" action="cursosMIBServlet" method="post">
        <div class="formulario">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
 	<tr>
    	<td class="texto_formu">Tipo Curso</td>
  	   <td class="celda_campo">
        <select name="tipoCurso" id="tipoCurso">

             <%
                if (listaTipoCursos != null)
                {
                    for(int i = 0; i<listaTipoCursos.size();i++)
                    {
                       if (listaTipoCursos.get(i).getIdTipoCurso()==idTipoCurso)
                       {
            %>
                        <option name="tipoCurso" value="<%=listaTipoCursos.get(i).getIdTipoCurso()%>" selected ><%=listaTipoCursos.get(i).getNombre()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="tipoCurso" value="<%=listaTipoCursos.get(i).getIdTipoCurso()%>" ><%=listaTipoCursos.get(i).getNombre()%></option>
            <%
                        }
                    }
                }
            %>
            </select>

        </td>

		<td class="texto_formu">Nombre del Curso:</td>
   		<td class="celda_campo">
                    <label><input name="nombreCurso" type="text" class="campo_texto" id="nombreCurso" size="40" value="<%=nombreCurso%>" /></label>		</td>
  	</tr>

  	<tr>
            <td class="texto_formu">Profesor:</td>
  	   <td class="celda_campo">
        <select name="profesor" id="profesor">

             <%
                if (listaProfesores != null)
                {
                    for(int i = 0; i<listaProfesores.size();i++)
                    {
                       if (listaProfesores.get(i).getIdProfesor()==idProfesor)
                       {
            %>
                        <option name="profesor" value="<%=listaProfesores.get(i).getIdProfesor()%>" selected ><%=listaProfesores.get(i).getNombre()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="profesor" value="<%=listaProfesores.get(i).getIdProfesor()%>" ><%=listaProfesores.get(i).getNombre()%></option>
            <%
                        }
                    }
                }
            %>
            </select>

        </td>

        <td class="texto_formu">Instalación:</td>
  	   <td class="celda_campo">
        <select name="instalacion" id="instalacion">

             <%
                if (listaInstalaciones != null)
                {
                    for(int i = 0; i<listaInstalaciones.size();i++)
                    {
                       if (listaInstalaciones.get(i).getIdInstalacion()==idInstalacion)
                       {
            %>
                        <option name="instalacion" value="<%=listaInstalaciones.get(i).getIdInstalacion()%>" selected ><%=listaInstalaciones.get(i).getNombre()%></option>
            <%
                        }
                        else
                        {
            %>
                        <option name="instalacion" value="<%=listaInstalaciones.get(i).getIdInstalacion()%>" ><%=listaInstalaciones.get(i).getNombre()%></option>
            <%
                        }
                    }
                }
            %>
            </select>

        </td>

        </tr>
          
          <tr>
    	<td class="texto_formu" width="15%">Fecha de:</td>
    	<td  class="celda_campo">
            <label><input name="fechaInicio" type="text" class="campo_texto" id="f_date_y" readonly="1" value="<%=strFechaInicio%>" />
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
		<td class="texto_formu">Fecha a:</td>
   		<td class="celda_campo">
		<label>
                    <input name="fechaFin" type="text" class="campo_texto" id="f_date_t" readonly="1" value="<%=strFechaFin%>" />
		  <img src="jscalendar/img.gif" id="f_trigger_t"/>

  	    <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_t",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_t",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
  	    </label>		</td>
  	</tr>


          <tr>
            <td class="texto_formu" width="15%">Hora inicio:</td>
            <td width="30%"  class="celda_campo">
                <select name="horaInicio" class="campo_texto" id="horaInicio">
                    <%
                        for(int i=9;i<21;i++)
                        {
                            String hora="";
                            hora = i + ":00";
                            if(strHoraInicio.equals(hora+":00"))
                            {

                    %>
                                <option value="<%=hora+":00"%>" selected ><%=hora %></option>
                     <%
                            }
                            else
                            {
                    %>
                                <option value="<%=hora+":00"%>" ><%=hora %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
            <td class="texto_formu" width="15%">Hora fin:</td>
            <td width="30%"  class="celda_campo">
                <select name="horaFin" class="campo_texto" id="horaFin">
                    <%
                        for(int i=10;i<22;i++)
                        {
                            String hora="";
                            hora = i + ":00";
                            if(strHoraFin.equals(hora+":00"))
                            {

                    %>
                                <option value="<%=hora+":00"%>" selected ><%=hora %></option>
                     <%
                            }
                            else
                            {
                    %>
                                <option value="<%=hora+":00"%>" ><%=hora %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
        </tr> <tr>
  	<td colspan="4" class="texto_formu2">
           
        <br />

        <%
            if  (unCurso != null)
            {
                if (unCurso.isLunes())
                {
        %>
                <input name="lunes" type="checkbox" value="lunes" checked /> Lunes &nbsp;&nbsp;
        <%      }  else  {  %>
                <input name="lunes" type="checkbox" value="lunes"/> Lunes &nbsp;&nbsp;
        
        <%      } if (unCurso.isMartes()) { %>
                <input name="martes" type="checkbox" value="martes" checked /> Martes &nbsp;&nbsp;
        <%      }  else  { %>
                <input name="martes" type="checkbox" value="martes" /> Martes &nbsp;&nbsp;
        
         <%     }   if (unCurso.isMiercoles()) {  %>
                <input name="miercoles" type="checkbox" value="miercoles" checked /> Miércoles &nbsp;&nbsp;
         <%      }  else  { %>
                <input name="miercoles" type="checkbox" value="miercoles" /> Miércoles &nbsp;&nbsp;
         
         <%     }    if (unCurso.isJueves())  {   %>
                <input name="jueves" type="checkbox" value="jueves" checked /> Jueves &nbsp;&nbsp;
          <%      }  else  {  %>
                <input name="jueves" type="checkbox" value="jueves" /> Jueves &nbsp;&nbsp;
           
          <%    }   if (unCurso.isViernes())  {   %>
                <input name="viernes" type="checkbox" value="viernes" checked /> Viernes &nbsp;&nbsp;
          <%      }  else  {   %>
                <input name="viernes" type="checkbox" value="viernes" /> Viernes &nbsp;&nbsp;
           
          <%   }  if (unCurso.isSabado()) {   %>
                <input name="sabado" type="checkbox" value="sabado" checked /> Sábado &nbsp;&nbsp;
          <%      }  else  {   %>
                <input name="sabado" type="checkbox" value="sabado" /> Sábado &nbsp;&nbsp;
            
          <%      }    if (unCurso.isDomingo())   {   %>
                <input name="domingo" type="checkbox" value="domingo" checked /> Domingo
          <%      }  else  {  %>
                <input name="domingo" type="checkbox" value="domingo" /> Domingo
           <%
                }
              }
            else
              {
           %>

          <input name="lunes" type="checkbox" value="lunes" /> Lunes &nbsp;&nbsp; 
  	  <input name="martes" type="checkbox" value="martes" /> Martes &nbsp;&nbsp;
	  <input name="miercoles" type="checkbox" value="miercoles" /> Miércoles &nbsp;&nbsp;
  	  <input name="jueves" type="checkbox" value="jueves" /> Jueves &nbsp;&nbsp;
  	  <input name="viernes" type="checkbox" value="viernes" /> Viernes &nbsp;&nbsp;
  	  <input name="sabado" type="checkbox" value="sabado" /> Sábado &nbsp;&nbsp;
  	  <input name="domingo" type="checkbox" value="domingo" /> Domingo
	</td>

        <%
        }
        %>
        
  </tr>

          <tr>
  	  <td class="texto_formu">Fecha inscripción:</td>
   		<td class="celda_campo">
		<label>
                    <input name="fechaInscripcion" type="text" class="campo_texto" id="f_date_d" readonly="1" value="<%=strFechaIns%>" />
		  <img src="jscalendar/img.gif" id="f_trigger_d"/>

  	    <script type="text/javascript">
    	Calendar.setup({
        inputField     :    "f_date_d",     // id of the input field
        ifFormat       :    "%d-%m-%Y",      // format of the input field
        button         :    "f_trigger_d",  // trigger for the calendar (button ID)
        align          :    "Tl",           // alignment (defaults to "Bl")
        singleClick    :    true
   		});
		</script>
  	    </label>		</td>
  	  <td class="texto_formu">Número Maximo:</td>
          <td class="celda_campo"><label><input name="numMax" type="text" class="campo_texto" id="numMax" size="40" value="<%=strNumMax%>"  /></label></td>
	  </tr>


          <tr>
  	  <td class="texto_formu">Precio:</td>
  	  <td class="celda_campo">
              <label><input name="precio" type="text" class="campo_texto" id="precio" size="40" value="<%=strPrecio%>"   /></label>
  	    </td>
  	  <td class="texto_formu">Inscripciones:</td>
          <td class="celda_campo"><label><input name="apuntados" type="text" class="campo_texto" id="apuntados" size="40" value="<%=strApuntados%>" readonly/></label></td>
	  </tr>


  	</table>
	</div>

        <input type="hidden" id="cursoId"  name="cursoId" value="<%=idCurso%>" /><br/>

    <div class="capas_botones">
        <input name="enviar" type="submit" class="boton" id="alta" value="Alta" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="baja" value="Baja" onclick="return validarBaja()" />
        <input name="enviar" type="submit" class="boton" id="modificar" value="Modificar" onclick="return validar()" />
        <input name="enviar" type="submit" class="boton" id="finalizar" value="Finalizar" onclick="return validarBaja()" />
    </div>
    </form>


      

   
   </div>

</div>
	<div style="clear:both;"></div>

</div>
</body>
</html>

