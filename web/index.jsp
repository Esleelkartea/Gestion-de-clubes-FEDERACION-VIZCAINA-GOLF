<%-- 
    Document   : index
    Created on : 07-jun-2011, 16:30:46
    Author     : Julen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<title>Intranet de Gestión de Clubes</title>
</head>

<body>

<div class="paginaLogin">

<form name=""  method="post"  action="LoginServlet">



	<div class="cuadroLogin">
    <div><img src="imagenes/tituloLogin.gif" width="390" height="29" alt="Intranet de gestión de clubes" /></div>



    <div class="textoLogin">Usuario:</div>
    	<div><input name="usuario" type="text" id="usuario" size="47" /></div>

    	<div class="textoLogin">Contraseña:</div>
    	<div></div>

    	<input name="password" type="password" id="password" size="47" />
   	  <div class="capas_botones"><input name="entrar" type="submit" class="boton" id="entrar" value="Entrar" />
          <%
                Boolean bError = (Boolean)session.getAttribute("errorLogin");
                if(bError != null && bError == true)
                {
           %>
                <span style="color:red">Login incorrecto</span><br/>
           <%
                }
                session.setAttribute("erroLogin", null);
           %>
          </div>
              
	</div>


</form>
</div>

</body>
</html>
