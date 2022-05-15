package curso.java.tienda.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import curso.java.tienda.recursos.AuxiliarBD;

public class Conexion {
    
	//static String bd = "tienda_oscar_martinez_hidalgo";
	//static String login = "root";
	//static String password = "";
	//static String host = "127.0.0.1"; //localhost
	
	static String bd = AuxiliarBD.getParametro("bbdd.nombre");
	static String login = AuxiliarBD.getParametro("bbdd.login");
	static String password = AuxiliarBD.getParametro("bbdd.pass");
	static String host = AuxiliarBD.getParametro("bbdd.host");
	
	static String url = "jdbc:mysql://";
	static Connection conexion; //atributo para  guardar el objeto Connection
        	
    public static Connection getConexion() {
	    if (conexion == null) {
	    	crearConexion();
	    }
	    return conexion;
    }
    
    // devuelve true si se ha creado correctamente
    public static boolean crearConexion() {
	    try {
	        //cargo el driver
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexion = DriverManager.getConnection(url + host + "/"+ bd, login, password);    
             
	        //poder controlar el commit y el rollback, sino hace el commit automaticamente y no puedo hacer rollback
            conexion.setAutoCommit(false);
	        
	    } catch (SQLException e) {
	    	return false;
	    }
	    catch (Exception e) {
	    	return false;
	    }
	    return true;
    }

    public static void desconectar(){
    	try {
            conexion.close();
            conexion = null;
            System.out.println("La conexion a la  base de datos " + bd + " ha terminado");
    	
    	} catch (SQLException e) {
    		System.out.println("Error al cerrar la conexion");
        }
    }
   
}
