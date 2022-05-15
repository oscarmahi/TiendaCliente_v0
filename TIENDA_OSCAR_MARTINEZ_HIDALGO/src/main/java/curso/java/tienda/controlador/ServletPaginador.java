package curso.java.tienda.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.modelo.ProductoModel;
import curso.java.tienda.vo.ProductoVO;

/**
 * Servlet implementation class ServletPaginador
 */
public class ServletPaginador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPaginador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//System.out.println(request.getParameter("select"));

		// resultados a visualizar por defecto
		int total;
		if (request.getParameter("total") == null) {
			total = 5;
		} else {
			total = Integer.parseInt(request.getParameter("total"));
		}

		String numPagina;
		// pagina consultada

		// Si no hay parametro pagina establecemos la pagina a 1
		if (request.getParameter("page") == null) {
			numPagina = "1";
		} else {
			// si no la sacamos de la peticion
			numPagina = request.getParameter("page");
		}

		// Parseamos el valor de la pagina para utilizarla como limite
		int pageid = Integer.parseInt(numPagina);

		// Comprobamos el valor de la lista select y sacamos el numero de paginas de
		// resultado
		if (request.getParameter("select") != null) {
			String paginasResultado = request.getParameter("select");
			total = Integer.parseInt(paginasResultado);
		}

		// Hacemos el movimiento de las paginas
		if (pageid == 1) {
		} else {
			pageid = pageid - 1;
			pageid = pageid * total + 1;
		}
		// Cargamos la lista con la consulta hecha por los limites que hemos calculado
		ArrayList<ProductoVO> lista=null;
		try {
			lista = new ProductoModel().listarProductorPaginados(new ArrayList<ProductoVO>(),pageid, total);
		} catch (Exception e) {
			System.out.println("error en servletpaginador linea 77");
			e.printStackTrace();
		}


		// metemos los atributos en el request
		request.setAttribute("listaProductos", lista);
		request.setAttribute("pagina", numPagina);
		request.setAttribute("total", total);
		//habria que calcular primero la parte entera de la división
		//luego el resto de la división, si no es exacto habría que contar con una pagina mas
		//sumar los dos resultados
		try {
			request.setAttribute("numPaginas", new ProductoModel().getNumeroProductos()/total);
		} catch (Exception e) {
			System.out.println("error en servletpaginador linea 92");
			e.printStackTrace();
		}

		// redirigimos
		request.getRequestDispatcher("./jsp/productos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
