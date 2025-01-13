<!DOCTYPE html>
<%@page import="com.example.App.utils.DebugUtils"%>
<html>
<head>
<%  String idUsuario=(String)request.getAttribute("idUsuario");
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
    <div class="container">
	    <form action="accionPrincipalFichaje" method="post">
	        <button type="submit" name="fichar" id="fichar" class="button" value="<%=fichar%>"><%=fichar%></button>
	        <input type="hidden" id="idUsuario" name="idUsuario" value="<%=idUsuario%>"/>
		</form>
		<form action="accionPrincipalModificar" method="post">
			<button name="modificar" id="modificar" class="button" value="OK">Modificar</button>
		</form>
		<form action="/" method="post">
	        <button name="volver" id="volver" class="button">Volver</button>
		</form>
    </div>
</body>
</html>
