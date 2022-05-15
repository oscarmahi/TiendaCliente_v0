package curso.java.tienda.servicio;

import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import curso.java.tienda.modelo.UsuarioModel;
import curso.java.tienda.vo.UsuarioVO;

public class UsuarioService {

	public int validarUsuario(UsuarioVO usuario) {
		int ok=0;
		try {
			ok = new UsuarioModel().verificarUsuario(usuario);
		} catch (SQLException e) {
			System.out.println("Error al consultar el usuario");
			e.printStackTrace();
		}
		return ok;
	}
	
	public int validarEmail(String email) {
		int ok=0;
		try {
			ok = new UsuarioModel().verificarEmailUsuario(email);
		} catch (SQLException e) {
			System.out.println("Error al consultar el usuario");
			e.printStackTrace();
		}
		return ok;
	}
	
	public boolean validarFormatoEmail(String email) {
		// Patrón para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);
		if (mather.find() == true) {
			return true;
		} else {
			return false;
		}
	}

	public String obtenerRol(int idUsuario) {
		String rolS="";
		try {
			rolS = new UsuarioModel().obtenerRol(idUsuario);
			//System.out.println("roll en service: "+rolS);
		} catch (Exception e) {
			System.out.println("Error al consultar el rol del usuario");
			e.printStackTrace();
		}
		return rolS;
	}

	public UsuarioVO obtenerDatosUsaurio(String idUsuario) {

		UsuarioVO usuario=null;
		try {
			usuario = new UsuarioModel().obtenerDatosUsuarioBBDD(Integer.parseInt(idUsuario));
		} catch (Exception e) {
			System.out.println("Error en obtenerDatosUsuario. linea 38");
			e.printStackTrace();
		}
		
		return usuario;
	}

	public boolean grabarUsuario(UsuarioVO usuario) {
		boolean ok = false;
		
		try {
			ok = new UsuarioModel().grabarUsuarioBBDD(usuario);
		} catch (Exception e) {
			System.out.println("Error en grabarUsuario. linea 51");
			e.printStackTrace();
		}
		
		return ok;
	}

	public boolean actualizarUsuario(int idUsuario, UsuarioVO usuario) {
		boolean ok = false;
		
		try {
			ok = new UsuarioModel().actualizarUsuarioBBDD(idUsuario, usuario);
		} catch (Exception e) {
			System.out.println("Error en actualizarUsuario. linea 64");
			e.printStackTrace();
		}
		
		return ok;
	}
	
	
	public boolean validarDNI(String dni) {
		String letraMayuscula = ""; //Guardaremos la letra introducida en formato mayúscula
		// Aquí excluimos cadenas distintas a 9 caracteres que debe tener un dni y también si el último caracter no es una letra
		if(dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false ) {
			return false;
		}
		// Al superar la primera restricción, la letra la pasamos a mayúscula
		letraMayuscula = (dni.substring(8)).toUpperCase();
		// Por último validamos que sólo tengo 8 dígitos entre los 8 primeros caracteres y que la letra introducida es igual a la de la ecuación
		// Llamamos a los métodos privados de la clase soloNumeros() y letraDNI()
		if(soloNumeros(dni) == true && letraDNI(dni).equals(letraMayuscula)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	private boolean soloNumeros(String dni) {
		int i, j = 0;
		String numero = ""; // Es el número que se comprueba uno a uno por si hay alguna letra entre los 8 primeros dígitos
		String miDNI = ""; // Guardamos en una cadena los números para después calcular la letra
		String[] unoNueve = {"0","1","2","3","4","5","6","7","8","9"};

		for(i = 0; i < dni.length() - 1; i++) {
			numero = dni.substring(i, i+1);

			for(j = 0; j < unoNueve.length; j++) {
				if(numero.equals(unoNueve[j])) {
					miDNI += unoNueve[j];
				}
			}
		}
		if(miDNI.length() != 8) {
			return false;
		}
		else {
			return true;
		}
	}

	private String letraDNI(String dni) {
		// El método es privado porque lo voy a usar internamente en esta clase, no se necesita fuera de ella
		// pasar miNumero a integer
		int miDNI = Integer.parseInt(dni.substring(0,8));
		int resto = 0;
		String miLetra = "";
		String[] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
		resto = miDNI % 23;
		miLetra = asignacionLetra[resto];
		return miLetra;
	}

	public boolean mandarEmail(String email) {
		System.out.println("Ha entrado en enviar email");
		
		//variable que si no pasa nada, llega al final del método, pone su valor a true y lo devuelve
		boolean todoOk = false;
		String mail="oscarmahi@gmail.com";
		String titulo="Nuevo Usuario";
		String cuerpo="Se ha registrado un nuevo usuario en BiciSerbatic con eMail: "+email;

		// Establecemos los parametros del host o servidor
		String smtpServer = "smtp.gmail.com";
		String smtpPort = "587";
		final String USER = "oscarmahi@gmail.com";
		final String PASS = "vinsdckcaklywhqv";

		// Propiedades del SMTP
		Properties propiedadesSMTP = new Properties();

		propiedadesSMTP.setProperty("mail.smtp.host", smtpServer);
		propiedadesSMTP.setProperty("mail.smtp.port", smtpPort);
		propiedadesSMTP.setProperty("mail.smtp.user", USER);
		propiedadesSMTP.setProperty("mail.smtp.pass", PASS);
		propiedadesSMTP.setProperty("mail.smtp.ssl", Boolean.TRUE.toString());
		propiedadesSMTP.setProperty("mail.smtp.auth", Boolean.TRUE.toString());
		propiedadesSMTP.setProperty("mail.smtp.starttls.enable", Boolean.TRUE.toString());
		propiedadesSMTP.put("mail.debug", "false");

		// Creamos la sesion para el servicio
		Session sesion = Session.getDefaultInstance(propiedadesSMTP, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER, PASS);
			}
		});

		// Creamos el mensaje con la sesion que acabamos de crear
		Message msg = new MimeMessage(sesion);

		// Definimos el titulo y cuerto del mensaje poara enviarlo al servicio
		cuerpo = cuerpo + " -> "+(new Date()).toString();

		try {
			msg.setFrom(new InternetAddress("info@santander.com"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			msg.setSubject(titulo);
			msg.setContent(cuerpo, "text/plain");

			Transport.send(msg);
		} catch (MessagingException e1) {
			System.out.println("ecxcepcion en MandarMensaje");
			e1.printStackTrace();
		}
		System.out.println("Mensaje enviado");
		todoOk = true;
		System.out.println(todoOk);
		return todoOk;		
	}
	
}
