<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../includes/cabecera.jsp"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Tienda Serbatic</title>
	<!-- <link rel="stylesheet" href="index.css"/> -->	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
		rel="stylesheet" 
		integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
		crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
		crossorigin="anonymous"></script>		
	<script type="text/javascript" src="index.js"></script>
</head>
<body>

	<h1 class="cabecera">Tienda Serbatic</h1>
	<br/>
	<fieldset style="width:20%">
	<legend>Formulario de login</legend>
	<form name="login" action="Controlador" method="post" onsubmit="return validar()">
		<label>Email: </label>
		<input type="text" placeholder="email" id="usuario" name="usuario"/><br/><br/>
		<label>Password: </label>
		<input type="text" placeholder="password" id="password" name="password"/><br/><br/>
		<input type="submit" name="enviar" value="Entrar"/>
		<div id="mensaje" style="color: red; display: none">Existe algun campo vacío en el formulario</div>
	</form>
	</fieldset>
	<br/><br/>
	<%
	String error = (String) request.getAttribute("error");
	if (error !=null){
		out.print(error);
	}

	%>
<%@ include file="../includes/pie.jsp"%>
</body>
</html>
