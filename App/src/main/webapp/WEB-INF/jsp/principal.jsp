<!DOCTYPE html>
<html>
<head>
    <title>Página con Botones</title>
    <link rel="stylesheet" type="text/css" href="principal.css">
</head>
<body>
    <div class="container">
	    <form action="accionPrincipalFichaje" method="post">
	        <button name="fichar" id="fichar" class="button" value="OK">Entrada</button>
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
