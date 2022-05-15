package curso.java.tienda.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.servicio.ProductoService;
import curso.java.tienda.servicio.UsuarioService;
import curso.java.tienda.vo.ProductoVO;
import curso.java.tienda.vo.UsuarioVO;

/**
 * Servlet implementation class Controlador
 */


public class Controlador extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String usua = request.getParameter("usuario");//obtengo un parametro con nombre usuario
		String pass = request.getParameter("password");//obtengo un parametro con nombre password
	
		//los parametros vienen de un formulario y los atributos los seteo y geteo
		if ((usua.length() != 0) && (pass.length()) != 0){
			UsuarioVO usuario = new UsuarioVO(usua, pass);
			
			//valido el usuario
			int idUsuario = 0;
			idUsuario = new UsuarioService().validarUsuario(usuario);
			String rol = new UsuarioService().obtenerRol(idUsuario);
			//System.out.println("rol: "+rol);
			
			String idUsuarioS = idUsuario+"";
			if (idUsuario != 0) {
				//aqui muestro los pedidos		
				request.getSession().setAttribute("usuario", usua);
				request.getSession().setAttribute("idUsuario", idUsuarioS);
				request.getSession().setAttribute("rol", rol);
				
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				System.out.println("else linea 147");
				//request.setAttribute("error", "El usuario no existe en la BBDD");
				request.getSession().setAttribute("usuario", null);
				request.getSession().setAttribute("idUsuario", null);
				request.getSession().setAttribute("rol", null);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}			
		}else {
			System.out.println("else linea 157");
			//request.setAttribute("error", "error");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}		
	}
}
