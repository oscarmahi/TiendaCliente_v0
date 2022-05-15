package curso.java.tienda.controlador;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.servicio.PedidoService;
import curso.java.tienda.servicio.UsuarioService;
import curso.java.tienda.vo.UsuarioVO;

/**
 * Servlet implementation class UsuarioControlador
 */
public class UsuarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioControlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// ver si el usuario existe y si es asi pasarle los parametros con los valores
		// de la BBDD
		String idUsuario = (String) request.getSession().getAttribute("idUsuario");

		if (idUsuario != null) {
			// TODO: ACCEDER A LA BBDD PARA TRAER LOS DATOS
			UsuarioVO usuario = new UsuarioService().obtenerDatosUsaurio(idUsuario);
			// TODO: MANDAR LOS DATOS A LA VISTA Y MOSTRARLOS
			request.setAttribute("usuarioMostrar", usuario);
			request.getRequestDispatcher("./jsp/usuarios.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("./jsp/usuarios.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idUsuario = (String) request.getSession().getAttribute("idUsuario");
		String eMail = (String) request.getParameter("emailN");

		if (idUsuario == null) {
			String pass1 = (String) request.getParameter("claveN");
			String pass2 = (String) request.getParameter("claveN2");
			if (pass1.equals(pass2)) {
				String dni = (String) request.getParameter("dniN");
				if ((new UsuarioService().validarEmail(eMail) == 0) && (new UsuarioService().validarDNI(dni))) {
					// validar y grabar datos del usuario
					if (new UsuarioService().validarFormatoEmail((String) request.getParameter("emailN"))) {
						UsuarioVO usuario = new UsuarioVO(2, (String) request.getParameter("emailN"),
								(String) request.getParameter("claveN"), (String) request.getParameter("nombreN"),
								(String) request.getParameter("apellido1N"),
								(String) request.getParameter("apellido2N"),
								(String) request.getParameter("direccionN"),
								(String) request.getParameter("provinciaN"),
								(String) request.getParameter("localidadN"), (String) request.getParameter("telefonoN"),
								(String) request.getParameter("dniN"));

						boolean ok = new UsuarioService().grabarUsuario(usuario);
						// LLAMO AL METODO PARA MANDAR EMAIL CON EL AVISO DE UN NUEVO USUARIO. LE PASO
						// EL EMAIL
						if (ok) {
							new UsuarioService().mandarEmail(request.getParameter("emailN"));
						}
						request.getRequestDispatcher("index.jsp").forward(request, response);

					} else {
						// TODO: mandar mensaje de error en la validacion del email
						request.getRequestDispatcher("./jsp/usuarios.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("error",
							"El email ya está en uso, formato incorrecto de DNI. Intente con otro.");
					request.getRequestDispatcher("./jsp/usuarios.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", "Los campos password han de ser iguales.");
				request.getRequestDispatcher("./jsp/usuarios.jsp").forward(request, response);
			}

		} else {
			String pass1 = (String) request.getParameter("clave");
			String pass2 = (String) request.getParameter("clave2");
			if (pass1.equals(pass2)) {
			String dni = (String) request.getParameter("dni");
			// System.out.println(dni);
			// System.out.println(new UsuarioService().validarDNI(dni));
			// System.out.println("despies"+dni);
			// validar y actualizar datos del usuario
			if (new UsuarioService().validarFormatoEmail((String) request.getParameter("email"))
					&& (new UsuarioService().validarDNI(dni))) {
				// System.out.println("entra en el if");
				UsuarioVO usuario = new UsuarioVO(2, (String) request.getParameter("email"),
						(String) request.getParameter("clave"), (String) request.getParameter("nombre"),
						(String) request.getParameter("apellido1"), (String) request.getParameter("apellido2"),
						(String) request.getParameter("direccion"), (String) request.getParameter("provincia"),
						(String) request.getParameter("localidad"), (String) request.getParameter("telefono"),
						(String) request.getParameter("dni"));
				boolean ok = new UsuarioService().actualizarUsuario(Integer.parseInt(idUsuario), usuario);
				request.getRequestDispatcher("index.jsp").forward(request, response);

			} else {
				// TODO: mandar mensaje de error en la validacion del email
				String idU = (String) request.getSession().getAttribute("idUsuario");
				UsuarioVO usuario = new UsuarioService().obtenerDatosUsaurio(idUsuario);
				request.setAttribute("error", "El email ya está en uso, formato incorrecto de DNI. Intente con otro.");
				request.setAttribute("usuarioMostrar", usuario);
				request.getRequestDispatcher("./jsp/usuarios.jsp").forward(request, response);
			}
			}else {
				String idU = (String) request.getSession().getAttribute("idUsuario");
				UsuarioVO usuario = new UsuarioService().obtenerDatosUsaurio(idUsuario);
				request.setAttribute("error", "Los campos password han de ser iguales.");
				request.setAttribute("usuarioMostrar", usuario);
				request.getRequestDispatcher("./jsp/usuarios.jsp").forward(request, response);
			}
		}
	}

}
