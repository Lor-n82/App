<!DOCTYPE html>
<html>
<head>
<%String mensaje=(String)request.getAttribute("mensaje");%>
    <meta charset="UTF-8">
    <title>Pantalla de Login</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
    <div class="login-container">
        <h2>Iniciar Sesi�n</h2>
        <form action="principal" method="post">
            <label for="username">Usuario:</label>
            <input type="text" id="username" name="username" required><br><br>
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
