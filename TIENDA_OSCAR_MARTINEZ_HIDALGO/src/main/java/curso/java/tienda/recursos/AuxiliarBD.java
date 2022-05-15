package curso.java.tienda.recursos;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import java.net.URL;

//import curso.java.trivial.ClaseLog;

/**
 * Funciones auxiliares de BBDD para acceder alfichero de configuraion de la BBDD
 */
public class AuxiliarBD {
	private static Properties propertiesBD = null;
	public static ResourceBundle rb = null;

	private AuxiliarBD(String fichero) {
		propertiesBD = new Properties();
		try {
			propertiesBD.load(new FileInputStream(fichero));
		} catch (IOException ex) {
			//ClaseLog.miLog.debug("NO existe el fichero de propiedades");
			System.out.println("NO existe el fichero de propiedades");
			ex.printStackTrace();
		}
	}

	/**
	 * Implementando Singleton
	 *
	 * @return
	 */
	public static AuxiliarBD getInstance(String fichero) {
		return new AuxiliarBD(fichero);
	}

	public static String getParametro(String parametro) {
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL appResourceURL = loader.getResource("bbdd.properties");
		String ficheroConf = appResourceURL.getPath();
	
		//String ficheroConf = "./properties/bd.properties";

		if (propertiesBD == null) {
			AuxiliarBD.getInstance(ficheroConf);
		}

		String param = propertiesBD.getProperty(parametro);
		return param;
	}
	
//	public static ResourceBundle idiomaIngles() {
//		
//		Locale locale = new Locale("en");
//		ResourceBundle rb = ResourceBundle.getBundle("idioma", locale);
//		return rb;
//	}
//	
//	public static ResourceBundle idiomaEspanol() {
//		
//		Locale locale = new Locale("es");
//		ResourceBundle rb = ResourceBundle.getBundle("idioma", locale);
//		return rb;
//	}

	
}
