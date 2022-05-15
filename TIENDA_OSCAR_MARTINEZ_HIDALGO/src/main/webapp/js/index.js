/**
 * 
 */
function validar() {
	//obteniendo el valor que se puso en campo text del formulario
	miCampoTexto = document.getElementById("usuario").value;
	//la condición
	if (miCampoTexto.length == 0) {
		alert("no se puede dejar vacio el campo nombre");
		document.getElementById("mensaje").style.display = "block";
		return false;
	}
	miCampoTexto = document.getElementById("password").value;

	if (miCampoTexto.length == 0) {
		alert("no se puede dejar vacio el campo password");
		document.getElementById("mensaje").style.display = "block";
		return false;
	}
	return true;
}

