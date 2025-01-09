<!DOCTYPE html>
<html>
<head>
    <title>Datepicker Centrado</title>
    <link rel="stylesheet" type="text/css" href="calendario.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
    <script>
    $(document).ready(function() { $(".animated-button").hover( function() { $(this).animate({ width: "150px", height: "50px", backgroundColor: "#79d0f1" }, 200); }, function() { $(this).animate({ width: "120px", height: "40px", backgroundColor: "#4da9d3" }, 200); } ); });
        $(function() {
        	$.datetimepicker.setLocale('es');
            $("#datetimepicker").datetimepicker({step: 5, format: "d/m/Y H:i", startDate: new Date(), autoclose: true, buttonImageOnly: true, buttonImage: "/img/calendar.png"});
        });
    </script>
</head>
<body>
    <div class="container">
        <input type="button" id="datetimepicker" class="datetimepicker-input animated-button">
        <form action="/principal" method="post">
	        <button name="volver" id="volver" class="button">Volver</button>
		</form>
    </div>
</body>
</html>