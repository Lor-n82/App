<!DOCTYPE html>
<html>
<head>
<%String usuario=(String)request.getAttribute("usuario");
String password=(String)request.getAttribute("password");
String idUsuario=(String)request.getAttribute("idUsuario");
String solicitado=(String)request.getAttribute("solicitado");
%>
    <title>Datepicker Centrado</title>
    <link rel="stylesheet" type="text/css" href="calendario.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
    <script>
    $(document).ready(function() {
        $(".animated-button").hover(
            function() {
                $(this).animate({
                    width: "150px",
                    height: "50px",
                    backgroundColor: "#79d0f1"
                }, 200);
            },
            function() {
                $(this).animate({
                    width: "120px",
                    height: "40px",
                    backgroundColor: "#4da9d3"
                }, 200);
            }
        );
        $("#switchFecha").val("Entrada");
        $("#switchFechaButton").val("Entrada");
        $("#switchFechaButton").text("Entrada");
        $("#datetimepicker").text("Pulsa para seleccionar fecha");
        
        $("#switchFechaButton").click(function(){ 
	    	let inputValue = $(this).val();
	    	if(inputValue=="Entrada"){
	    		$("#switchFecha").val("Salida");
	    		$(this).text("Salida"); 
	    		$(this).val("Salida"); 
	    	}else{
	    		$("#switchFecha").val("Entrada");
	    		$(this).text("Entrada");
	    		$(this).val("Entrada"); 
	    	}
	    });
    });

    $(function() {
        $.datetimepicker.setLocale('es');
        $("#datetimepicker").datetimepicker({
            step: 5,
            format: "d/m/Y H:i",
            startDate: new Date(),
            autoclose: true,
            buttonImageOnly: true,
            buttonImage: "/img/calendar.png"
        });
    });

    </script>
</head>
<body>
	<h1 class="titulo-azul-pastel">Solicitud de fichaje</h1>
    <div class="container">
    	<form action="/modificarFechaSeleccionada" method="post" class="form-inline">
       		<input type="text" id="datetimepicker" name="datetimepicker" class="datetimepicker-input animated-button" readonly>
	        <button type="button" name="switchFechaButton" id="switchFechaButton" class="button"></button>
	        <input type="hidden" id="switchFecha" name="switchFecha"/>
	        <input type="hidden" id="idUsuario" name="idUsuario" value="<%=idUsuario%>"/>
	        <input type="hidden" id="solicitado" name="solicitado" value="<%=solicitado%>"/>
        	<button type="submit" name="enviar" id="enviar" class="button">Enviar Petición</button>
		</form>
		<form action="/menuAcciones" method="post" class="form-inline">
			 <button type="submit" name="volverBtn" id="volverBtn" class="button">Volver</button>
			 <input type="hidden" name="volver" id="volver" class="button" value="volver"></button>
			 <input type="hidden" id="idUsuario" name="idUsuario" value="<%=idUsuario%>"/>
			 <input type="hidden" id="usuario" name="usuario" value="<%=usuario%>"/>
			 <input type="hidden" id="password" name="password" value="<%=password%>"/>
		</form>
    </div>
</body>
</html>