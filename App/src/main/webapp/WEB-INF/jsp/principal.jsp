<!DOCTYPE html>
<%@page import="com.example.App.utils.DebugUtils"%>
<html>
<head>
<%  String idUsuario=(String)request.getAttribute("idUsuario");
	String usuario=(String)request.getAttribute("usuario");
	String password=(String)request.getAttribute("password");
	String mensaje=(String)request.getAttribute("mensaje");
	String fichar="Entrada";
	if(request.getAttribute("estadoFichaje")!=null){
		fichar = (String)request.getAttribute("estadoFichaje");
	}
	DebugUtils debug = new DebugUtils();
	debug.info(fichar);
%>
    <title>Página con Botones</title>
    <link rel="stylesheet" type="text/css" href="principal.css">
</head>
<body>
	<h1 class="titulo-azul-pastel">Selecciona la opción</h1>
    <div class="container">
	    <form action="accionPrincipalFichaje" method="post">
	        <button type="submit" name="fichar" id="fichar" class="button" value="<%=fichar%>"><%=fichar%></button>
	        <input type="hidden" id="idUsuario" name="idUsuario" value="<%=idUsuario%>"/>
	        <input type="hidden" id="usuario" name="usuario" value="<%=usuario%>"/>
	        <input type="hidden" id="password" name="password" value="<%=password%>"/>
		</form>
		<%if(mensaje!=null){%>
			<%=mensaje %>
		<%}%>
		<form action="accionPrincipalModificar" method="post">
			<button name="modificar" id="modificar" class="button" value="1">Modificar</button>
			<input type="hidden" id="idUsuario" name="idUsuario" value="<%=idUsuario%>"/>
			<input type="hidden" id="usuario" name="usuario" value="<%=usuario%>"/>
	        <input type="hidden" id="password" name="password" value="<%=password%>"/>
		</form>
		<form action="/" method="post">
	        <button name="volver" id="volver" class="button">Salir</button>
		</form>
    </div>
</body>
</html>
