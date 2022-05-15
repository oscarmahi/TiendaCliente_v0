<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="curso.java.tienda.vo.UsuarioVO"%>
<%@ include file="../includes/cabecera.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tienda Serbatic</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>
<body>

	<%
	String usuarioP = (String) request.getSession().getAttribute("usuario");
	String rolP = (String) request.getSession().getAttribute("rol");
	UsuarioVO usuarioMostrar = (UsuarioVO) request.getAttribute("usuarioMostrar");
	if (usuarioP == null) {
	%>

	<h1 class="cabecera text-primary fs-1 fw-bold text-center fst-italic">TodoBiciSerbatic</h1>
	<br />




	<div class="col-md-5 col-lg-5 container abs-center">
		<h4 class="mb-3 text-center">Pantalla de usuario</h4>
		<form action="UsuarioControlador" method="post"
			class="needs-validation" novalidate>
			<div class="col-md-3 border-right center mx-auto">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5">
					<img class="rounded-circle mt-5" width="150px"
						src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span
						class="text-black-50"></span><span class="text-black-50"></span>
				</div>
			</div>


			<%
			String error = (String) request.getAttribute("error");
			if (error != null) {
			%><h1 class="text-primary fs-1 fw-bold text-center fst-italic"><%=error%></h1>
			<%
			}
			%>


			<div class="row g-3">
				<div class="col-6">
					<label for="emailN" class="form-label">eMail Usuario</label> <input
						type="email" class="form-control" id="emailN" name="emailN"
						placeholder="eMail">
					<div class="invalid-feedback">Complete los datos</div>
				</div>
				<div class="col-6"></div>
				<div class="col-6">
					<label for="passwordN" class="form-label">Password</label> <input
						type="password" class="form-control" id="claveN" name="claveN"
						placeholder="password" required>
					<div class="invalid-feedback">Complete los datos</div>
				</div>
				<div class="col-6">
					<label for="passwordN2" class="form-label">Repita Password</label>
					<input type="password" class="form-control" id="claveN2"
						name="claveN2" placeholder="password" required>
					<div class="invalid-feedback">Complete los datos</div>
				</div>
				<div class="col-12">
					<label for="nombreN" class="form-label">Nombre</label> <input
						type="text" class="form-control" id="nombreN" name="nombreN"
						placeholder="nombre" required>
					<div class="invalid-feedback">Complete los datos</div>
				</div>

				<div class="col-12">
					<label for="apellido1N" class="form-label">Apellido1</label> <input
						type="text" class="form-control" id="apellido1N" name="apellido1N"
						placeholder="apellido1" required>
					<div class="invalid-feedback">Complete los datos</div>
				</div>

				<div class="col-12">
					<label for="apellido2N" class="form-label">Apellido2</label> <input
						type="text" class="form-control" id="apellido2N" name="apellido2N"
						placeholder="apellido2N" required>
					<div class="invalid-feedback">Complete los datos</div>
				</div>

				<div class="col-12">
					<label for="direccionN" class="form-label">Direccion</label> <input
						type="text" class="form-control" id="direccionN" name="direccionN"
						placeholder="direccion">
				</div>

				<div class="col-12">
					<label for="provinciaN" class="form-label">Provincia</label> <input
						type="text" class="form-control" id="provinciaN" name="provinciaN"
						placeholder="provincia">
				</div>

				<div class="col-12">
					<label for="localidadN" class="form-label">Localidad</label> <input
						type="text" class="form-control" id="localidadN" name="localidadN"
						placeholder="localidad">
				</div>

				<div class="col-6">
					<label for="telefonoN" class="form-label">Telefono</label> <input
						type="text" class="form-control" id="telefonoN" name="telefonoN"
						placeholder="Telefono">
				</div>

				<div class="col-6">
					<label for="dniN" class="form-label">DNI</label> <input type="text"
						class="form-control" id="dniN" name="dniN" placeholder="DNI">
				</div>
			</div>
			<hr class="my-4">
			<button class="w-100 btn btn-primary btn-lg" type="submit">Grabar</button>
		</form>
	</div>



	<%
	} else {
	%>
	<h1 class="cabecera text-primary fs-1 fw-bold text-center fst-italic">TodoBiciSerbatic</h1>
	<br />
	<div class="col-md-5 col-lg-5 container abs-center">
		<h4 class="mb-3 text-center">Pantalla de usuario</h4>
		<form action="UsuarioControlador" method="post"
			class="needs-validation" novalidate>
			<div class="col-md-3 border-right center mx-auto">
				<div
					class="d-flex flex-column align-items-center text-center p-3 py-5">
					<img class="rounded-circle mt-5" width="150px"
						src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span
						class="text-black-50"><%=usuarioP%></span><span
						class="text-black-50">Rol:<%=rolP%></span>
				</div>
			</div>
			<%
			String error = (String) request.getAttribute("error");
			if (error != null) {
			%><h1 class="text-primary fs-1 fw-bold text-center fst-italic"><%=error%></h1>
			<%
			}
			%>
			<div class="row g-3">
				<div class="col-6">
					<label for="email" class="form-label">eMail Usuario</label> <input
						type="text" class="form-control" id="email" name="email"
						placeholder="eMail" value=<%=usuarioP%> readonly>
					<div class="invalid-feedback">Complete los datos</div>
				</div>
				<div class="col-6"></div>
				<div class="col-6">
					<label for="passwordR" class="form-label">Password</label> <input
						type="password" class="form-control" id="clave" name="clave"
						placeholder="password" value="" required>
					<div class="invalid-feedback">Complete los datos</div>
				</div>
				<div class="col-6">
					<label for="passwordR2" class="form-label">Repita Password</label>
					<input type="password" class="form-control" id="clave2"
						name="clave2" placeholder="password" value="" required>
					<div class="invalid-feedback">Complete los datos</div>
				</div>
				<div class="col-12">
					<label for="nombre" class="form-label">Nombre</label> <input
						type="text" class="form-control" id="nombre" name="nombre"
						placeholder="nombre" value=<%=usuarioMostrar.getNombre()%>
						required>
					<div class="invalid-feedback">Complete los datos</div>

				</div>

				<div class="col-12">
					<label for="apellido1" class="form-label">Apellido1</label> <input
						type="text" class="form-control" id="apellido1" name="apellido1"
						placeholder="apellido1" value=<%=usuarioMostrar.getApellido1()%>
						required>
					<div class="invalid-feedback">Complete los datos</div>

				</div>

				<div class="col-12">
					<label for="apellido2" class="form-label">Apellido2</label> <input
						type="text" class="form-control" id="apellido2" name="apellido2"
						placeholder="apellido2" value=<%=usuarioMostrar.getApellido2()%>
						required>
					<div class="invalid-feedback">Complete los datos</div>

				</div>

				<div class="col-12">
					<label for="direccion" class="form-label">Direccion</label> <input
						type="text" class="form-control" id="direccion" name="direccion"
						placeholder="direccion" value=<%=usuarioMostrar.getDireccion()%>>
				</div>

				<div class="col-12">
					<label for="provincia" class="form-label">Provincia</label> <input
						type="text" class="form-control" id="provincia" name="provincia"
						placeholder="provincia" value=<%=usuarioMostrar.getProvincia()%>>
				</div>

				<div class="col-12">
					<label for="localidad" class="form-label">Localidad</label> <input
						type="text" class="form-control" id="localidad" name="localidad"
						placeholder="localidad" value=<%=usuarioMostrar.getLocalidad()%>>
				</div>

				<div class="col-6">
					<label for="telefono" class="form-label">Telefono</label> <input
						type="text" class="form-control" id="telefono" name="telefono"
						placeholder="Telefono" value=<%=usuarioMostrar.getTelefono()%>>
				</div>

				<div class="col-6">
					<label for="dni" class="form-label">DNI</label> <input type="text"
						class="form-control" id="dni" name="dni" placeholder="DNI"
						value=<%=usuarioMostrar.getDni()%>>
				</div>
			</div>
			<hr class="my-4">
			<button class="w-100 btn btn-primary btn-lg" type="submit">Grabar</button>
		</form>
	</div>



	<%
	}
	%>

	<%
	String error = (String) request.getAttribute("error");
	if (error != null) {
	%><h1 class="text-primary fs-1 fw-bold text-center fst-italic"><%=error%></h1>
	<%
	}
	%>



	<script>
		(function() {
			'use strict'
			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')
			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}
					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>

	<%@ include file="../includes/pie.jsp"%>
</body>
</html>