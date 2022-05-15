package curso.java.tienda.controlador;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curso.java.tienda.recursos.AuxiliarBD;

/**
 * Servlet implementation class Idioma
 */
public class Idioma extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Idioma() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String idioma = request.getParameter("idi");
		
		if (idioma.equals("es")) {
			Locale locale = new Locale("es");
			AuxiliarBD.rb = ResourceBundle.getBundle("idioma", locale);
		}else{
			Locale locale = new Locale("en");
			AuxiliarBD.rb = ResourceBundle.getBundle("idioma", locale);
		}
		
		String m1 = AuxiliarBD.rb.getString("menu.inicio");
		String m2 = AuxiliarBD.rb.getString("menu.bicicletas");
		String m3 = AuxiliarBD.rb.getString("menu.ropa");
		String m4 = AuxiliarBD.rb.getString("menu.accesorios");
		String m5 = AuxiliarBD.rb.getString("menu.login");
		String m6 = AuxiliarBD.rb.getString("menu.registro");
		String m7 = AuxiliarBD.rb.getString("menu.generar.excel");
		String m8 = AuxiliarBD.rb.getString("menu.importar.productos");
		String m9 = AuxiliarBD.rb.getString("menu.menu.esp");
		String m10 = AuxiliarBD.rb.getString("menu.menu.eng");
		String m11 = AuxiliarBD.rb.getString("menu.menu");
		String m12 = AuxiliarBD.rb.getString("menu.menuHistorial");
		
		request.setAttribute("m1", m1);
		request.setAttribute("m2", m2);
		request.setAttribute("m3", m3);
		request.setAttribute("m4", m4);
		request.setAttribute("m5", m5);
		request.setAttribute("m6", m6);
		request.setAttribute("m7", m7);
		request.setAttribute("m8", m8);
		request.setAttribute("m9", m9);
		request.setAttribute("m10", m10);
		request.setAttribute("m11", m11);
		request.setAttribute("m12", m12);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
