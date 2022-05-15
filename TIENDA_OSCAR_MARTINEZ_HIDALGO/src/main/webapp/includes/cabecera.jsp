<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bicicletas Serbatic</title>
<script type="text/javascript" src="./js/index.js"></script>
</head>
<body>
	<%
	String usuarioCabecera = (String) request.getSession().getAttribute("usuario");
	String rolCabecera = (String) request.getSession().getAttribute("rol");
	String m1 = (String) request.getAttribute("m1");
	String m2 = (String) request.getAttribute("m2");
	String m3 = (String) request.getAttribute("m3");
	String m4 = (String) request.getAttribute("m4");
	String m5 = (String) request.getAttribute("m5");
	String m6 = (String) request.getAttribute("m6");
	String m7 = (String) request.getAttribute("m7");
	String m8 = (String) request.getAttribute("m8");
	String m9 = (String) request.getAttribute("m9");
	String m10 = (String) request.getAttribute("m10");
	String m11 = (String) request.getAttribute("m11");
	String m12 = (String) request.getAttribute("m12");
    if (m1==null){m1="Inicio";}
    if (m2==null){m2="Bicicletas";}
    if (m3==null){m3="Ropa";}
    if (m4==null){m4="Accesorios";}
    if (m5==null){m5="Login";}
    if (m6==null){m6="Registro";}
    if (m7==null){m7="Generar Excel con Productos";}
    if (m8==null){m8="Importar Productos con Excel";}
    if (m9==null){m9="Menu en Español";}
    if (m10==null){m10="Menu en Ingles";}
    if (m11==null){m11="Menu de Opciones";}
    if (m12==null){m12="Ver Historial de Pedidos";}
	if (usuarioCabecera == null) {
		usuarioCabecera = "";
	}
	%>


    <% if ((rolCabecera != null) && (rolCabecera.equals("cliente"))){ %>
	<div class="container">
		<header
			class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
			<img src="./imagenes/logo-blue-2.png"></img> 
			<a href="index.jsp"
				class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
				<svg class="bi me-2" height="32"><use href="" /></svg> <span class="fs-4 text-primary"> </span>
			</a>
			<ul class="nav nav-pills">
				<li class="nav-item"><a href="index.jsp"
					class="nav-link active" aria-current="page"><%=m1 %></a></li>
				<li class="nav-item"><a href="Catalogo" class="nav-link"><%=m2 %></a></li>
				<li class="nav-item"><a href="#" class="nav-link"><%=m3 %></a></li>
				<li class="nav-item"><a href="#" class="nav-link"><%=m4 %></a></li>
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#exampleModal" data-bs-whatever="@mdo"><%=m5 %></button>
				<li class="nav-item"><a href="UsuarioControlador" class="nav-link"><%=m6%></a></li>
                <div class="dropdown">
                    <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    <%=m11 %>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <li><a class="dropdown-item" href="Idioma?idi=es"><%=m9 %></a></li>
                        <li><a class="dropdown-item" href="Idioma?idi=en"><%=m10 %></a></li>
                        <li><a class="dropdown-item" href="HistorialPedidos"><%=m12 %></a></li>
                    </ul>
                </div>				
				<li class="nav-item"><a href="#" class="nav-link"> </a></li>
				<form action="CerrarTodo">
					<button type="submit" class="btn btn-primary">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-open-fill" viewBox="0 0 16 16">
	  						<path d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15H1.5zM11 2h.5a.5.5 0 0 1 .5.5V15h-1V2zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
						</svg>
					</button>
				</form>
			</ul>
			<% if (usuarioCabecera.length() != 0){%>
			     <span id="usuarioCabecera" class="nav-link text-danger">Cliente: <%=usuarioCabecera%></span>
			<%	
			}else{%>
				<span id="usuarioCabecera" class="nav-link text-danger"></span>
			<%
			}
			%>
			<span id="usuarioCab" value="<%=usuarioCabecera%>"></span>
		</header>
	</div>

    <%
    }else if (rolCabecera == null){
    %>
        <div class="container">
        <header
            class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <img src="./imagenes/logo-blue-2.png"></img> 
            <a href="index.jsp"
                class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <svg class="bi me-2" height="32"><use href="" /></svg> <span class="fs-4 text-primary"> </span>
            </a>
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="index.jsp"
                    class="nav-link active" aria-current="page"><%=m1 %></a></li>
                <li class="nav-item"><a href="Catalogo" class="nav-link"><%=m2 %></a></li>
                <li class="nav-item"><a href="#" class="nav-link"><%=m3 %></a></li>
                <li class="nav-item"><a href="#" class="nav-link"><%=m4 %></a></li>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" data-bs-whatever="@mdo"><%=m5 %></button>
                <li class="nav-item"><a href="UsuarioControlador" class="nav-link"><%=m6%></a></li>
                
                <li class="nav-item"><a href="#" class="nav-link"> </a></li>
                <form action="CerrarTodo">
                    <button type="submit" class="btn btn-primary">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-open-fill" viewBox="0 0 16 16">
                            <path d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15H1.5zM11 2h.5a.5.5 0 0 1 .5.5V15h-1V2zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
                        </svg>
                    </button>
                </form>
            </ul>
            <% if (usuarioCabecera.length() != 0){%>
                <span id="usuarioCabecera" class="nav-link text-danger">Usuario: <%=usuarioCabecera%>--><%=rolCabecera%></span>
            <%  
            }else{%>
                <span id="usuarioCabecera" class="nav-link text-danger">--></span>
            <%
            }
            %>
            <span id="usuarioCab" value="<%=usuarioCabecera%>"></span>
        </header>
    </div>
  
    <%
    }else if ((rolCabecera != null) && (rolCabecera.equals("administrador"))){
    %>
    
    <div class="container">
        <header
            class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <img src="./imagenes/logo-blue-2.png"></img> 
            <a href="index.jsp"
                class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <svg class="bi me-2" height="32"><use href="" /></svg> <span class="fs-4 text-primary"> </span>
            </a>
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="index.jsp"
                    class="nav-link active" aria-current="page"><%=m1 %></a></li>
                <li class="nav-item"><a href="Catalogo" class="nav-link"><%=m2 %></a></li>
                <li class="nav-item"><a href="#" class="nav-link"><%=m3 %></a></li>
                <li class="nav-item"><a href="#" class="nav-link"><%=m4 %></a></li>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" data-bs-whatever="@mdo"><%=m5 %></button>
                <li class="nav-item"><a href="UsuarioControlador" class="nav-link"><%=m6%></a></li>
                <div class="dropdown">
                    <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    <%=m11 %>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <li><a class="dropdown-item" href="ExcelExportar"><%=m7 %></a></li>
                        <li><a class="dropdown-item" href="ExcelImportar"><%=m8 %></a></li>
                        <li><a class="dropdown-item" href="Idioma?idi=es"><%=m9 %></a></li>
                        <li><a class="dropdown-item" href="Idioma?idi=en"><%=m10 %></a></li>
                    </ul>
                </div>              
                <li class="nav-item"><a href="#" class="nav-link"> </a></li>
                <form action="CerrarTodo">
                    <button type="submit" class="btn btn-primary">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-open-fill" viewBox="0 0 16 16">
                            <path d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15H1.5zM11 2h.5a.5.5 0 0 1 .5.5V15h-1V2zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
                        </svg>
                    </button>
                </form>
            </ul>
            <% if (usuarioCabecera.length() != 0){%>
                <span id="usuarioCabecera" class="nav-link text-danger">Empleado: <%=usuarioCabecera%></span>
            <%  
            }else{%>
                <span id="usuarioCabecera" class="nav-link text-danger">--></span>
            <%
            }
            %>
            <span id="usuarioCab" value="<%=usuarioCabecera%>"></span>
        </header>
    </div>
    
    
    
    
   <%
    }else if ((rolCabecera != null) && (rolCabecera.equals("empleado"))){
    %>
    
    <div class="container">
        <header
            class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <img src="./imagenes/logo-blue-2.png"></img> 
            <a href="index.jsp"
                class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
                <svg class="bi me-2" height="32"><use href="" /></svg> <span class="fs-4 text-primary"> </span>
            </a>
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="index.jsp"
                    class="nav-link active" aria-current="page"><%=m1 %></a></li>
                <li class="nav-item"><a href="Catalogo" class="nav-link"><%=m2 %></a></li>
                <li class="nav-item"><a href="#" class="nav-link"><%=m3 %></a></li>
                <li class="nav-item"><a href="#" class="nav-link"><%=m4 %></a></li>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" data-bs-whatever="@mdo"><%=m5 %></button>
                <li class="nav-item"><a href="UsuarioControlador" class="nav-link"><%=m6%></a></li>
                <div class="dropdown">
                    <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    <%=m11 %>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <li><a class="dropdown-item" href="Idioma?idi=es"><%=m9 %></a></li>
                        <li><a class="dropdown-item" href="Idioma?idi=en"><%=m10 %></a></li>
                        <li><a class="dropdown-item" href="HistorialPedidos"><%=m12 %></a></li>
                    </ul>
                </div>              
                <li class="nav-item"><a href="#" class="nav-link"> </a></li>
                <form action="CerrarTodo">
                    <button type="submit" class="btn btn-primary">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-open-fill" viewBox="0 0 16 16">
                            <path d="M1.5 15a.5.5 0 0 0 0 1h13a.5.5 0 0 0 0-1H13V2.5A1.5 1.5 0 0 0 11.5 1H11V.5a.5.5 0 0 0-.57-.495l-7 1A.5.5 0 0 0 3 1.5V15H1.5zM11 2h.5a.5.5 0 0 1 .5.5V15h-1V2zm-2.5 8c-.276 0-.5-.448-.5-1s.224-1 .5-1 .5.448.5 1-.224 1-.5 1z"/>
                        </svg>
                    </button>
                </form>
            </ul>
            <% if (usuarioCabecera.length() != 0){%>
                <span id="usuarioCabecera" class="nav-link text-danger">Empleado: <%=usuarioCabecera%></span>
            <%  
            }else{%>
                <span id="usuarioCabecera" class="nav-link text-danger">--></span>
            <%
            }
            %>
            <span id="usuarioCab" value="<%=usuarioCabecera%>"></span>
        </header>
    </div>    
    <%} %>
    
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Login</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form name="login" action="Controlador" method="post"
						class="needs-validation" novalidate>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">Usuario(email):</label>
							<input type="email" class="form-control" id="usuario"
								name="usuario" required>
							<div class="invalid-feedback">Complete los datos</div>
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">Password:</label>
							<input type="password" class="form-control" id="password"
								name="password" required>
							<div class="invalid-feedback">Complete los datos</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Cerrar</button>
							<button type="submit" class="btn btn-primary">Enviar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

    <!-- script para validar los campos con bootstrap -->
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

    <!-- script para lanzar un mensaje por pantalla cada 3 minutos si el usuario no está logado y/o registrado -->
	<script type="text/javascript">
		function mostrarMensaje() {
			//obtenemos elemento del DOM por id
			var enlace = document.getElementById("usuarioCab");
			var valor = enlace.getAttribute("value");
			//console.log(valor);
			if (valor == "") {
				alert("Aún no te has registrado. No podras comprar y beneficiarte de nuestros descuentos.");
			}
		}
		setInterval(mostrarMensaje, 180000);
	</script>

</body>
</html>