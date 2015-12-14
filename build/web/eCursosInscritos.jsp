<%-- 
    Document   : TiposInstalaciones
    Created on : 06-jun-2011, 10:14:27
    Author     : Julen
--%>

<%@page import="com.Negocio.Socio"%>
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
    

</script>


</head>

<body>
<div id="contenedor">
	<div class="cabecera"><img src="imagenes/cabeceraExtranet.gif" width="1024" height="84" alt="Intranet de Gestión de Clubes" /></div>

       <%
            Socio unSocio2 = (Socio)session.getAttribute("socioExtranet");
            String nombreSocio = unSocio2.getNombre();
            Usuario unUsuario = (Usuario)session.getAttribute("usuario");
            String idUsuario = String.valueOf(unUsuario.getIdUsuario());
            String nombreUsuario = unUsuario.getNombre();
         %>

         <div id="usu" align="right">Hola &nbsp;&nbsp;<%=nombreSocio%></div>

	<div id="menu">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    	  <td class="tdmenuSelec">RESERVAR</td>
    	  <td class="tdmenu"><a href="eSocioServlet"> DATOS SOCIO</a></td>
    	  <td class="tdmenu"><a href="eMenPanelServlet">PANEL DE MENSAJES</a></td>
    	</tr>
    </table>
  </div>

  <div id="submenu"><a href="eReservaAltaServlet">Alta de Reservas</a> &nbsp;|  &nbsp;<a href="eReservaGestionServlet">Listado de Reservas</a>&nbsp;|&nbsp;<a href="eReservaProfesorServlet">Reservas de Profesor</a>&nbsp;|&nbsp;<a href="eReservaGestionProfesorServlet">Listado de Reservas de Profesor</a>&nbsp;|&nbsp;Alta en Cursos &nbsp;|&nbsp;<a href="eReservaGestionCursoServlet">Listado de Cursos</a></div>

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
             <a style="color:red " href="eCursoTipoCursoServlet?varTipoCurso=<%=listaTipoCurso.get(i).getIdTipoCurso()%>"><%=listaTipoCurso.get(i).getNombre()%></a>
             &nbsp;&nbsp;
            <%
                        }
                        else
                        {
            %>

             <a style="color: #008AFF" href="eCursoTipoCursoServlet?varTipoCurso=<%=listaTipoCurso.get(i).getIdTipoCurso()%>"><%=listaTipoCurso.get(i).getNombre()%></a>
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
                if (listaCursos != null && listaCursos.size()!=0)
                {

                    for(int i = 0; i<listaCursos.size();i++)
                    {
             %>
             <a style="color: #008AFF" href="eCursoCursoServlet?varCurso=<%=listaCursos.get(i).getCursoId()%>"><%=listaCursos.get(i).getCursoNombre()%></a>
             &nbsp;&nbsp;
            <%
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

    </div>

</div>

<div style="clear:both;"></div>


</div>
</body>
</html>

