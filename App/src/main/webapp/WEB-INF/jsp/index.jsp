<!DOCTYPE html>
<html>
<head>
<%String mensaje=(String)request.getAttribute("mensaje");%>
    <meta charset="UTF-8">
    <title>Pantalla de Login</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
	<h1 class="titulo-azul-pastel">Selecciona la opci�n</h1>
    <div class="login-container">
        <h2>Iniciar Sesi�n</h2>
        <form action="principal" method="post">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" required><br><br>
            <label for="password">Contrase�a:</label>
            <input type="password" id="password" name="password" required><br><br>
            <input type="submit"  id="login" name="login" value="Login">
            <input type="submit"  id="Registro" name="Registro" value="Registro">
        </form>
        <%if(mensaje!=null && !mensaje.equalsIgnoreCase("")){%>
        	<br>
        	<%=mensaje%>
        <%}%>
    </div>
</body>
</html>
