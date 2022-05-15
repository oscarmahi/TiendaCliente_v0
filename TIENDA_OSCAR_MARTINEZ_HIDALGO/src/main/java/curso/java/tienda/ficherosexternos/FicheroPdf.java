package curso.java.tienda.ficherosexternos;

import java.awt.Desktop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//import curso.java.trivial.ClaseLog;
//import curso.java.trivial.auxiliares.Auxiliar;
import curso.java.tienda.recursos.Constantes;
import curso.java.tienda.vo.PedidoVO;
import curso.java.tienda.vo.ProductoVO;
import curso.java.tienda.vo.UsuarioVO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class FicheroPdf{
	
	
	/**
	 * Metodo para generar el fichero pdf
	 * @param nick
	 * @param logP
	 */
	public static void generarPdf(HashMap<String, String> datosE, PedidoVO pedido, UsuarioVO usuario, ArrayList<ProductoVO> carrito, String applicationPath) {

		// TODO Auto-generated method stub
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);

		try {
			// Obtenemos la instancia del archivo a utilizar
			//System.out.println(applicationPath+Constantes.FICHERO_PDF);
			writer = PdfWriter.getInstance(documento, new FileOutputStream(applicationPath+"/pdf/"+Constantes.FICHERO_PDF));
	
			// Para insertar cabeceras/pies en todas las paginas
			writer.setPageEvent(new PDFHeaderFooter(applicationPath));

			// Abrimos el documento para edicion
			documento.open();

			// PARRAFO - caracteristicas
			Paragraph paragraph = new Paragraph();
			Paragraph paragraph2 = new Paragraph();
			
			paragraph.setSpacingBefore(5);
			paragraph.add("\n");
			String font = "Sans";
			float tamanno = 15;
			int style = Font.UNDERLINE;
			BaseColor color = BaseColor.RED;

			paragraph2.setSpacingBefore(5);
			paragraph2.add("\n");
			String font2 = "Sans";
			float tamanno2 = 11;
			BaseColor color2 = BaseColor.BLACK;
			
			paragraph2.setAlignment(Element.ALIGN_CENTER);
			paragraph2.setFont(new Font(FontFactory.getFont(font2, tamanno2, color2)));
			
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setFont(new Font(FontFactory.getFont(font, tamanno, style, color)));
			
			paragraph.add("EMPRESA-----------------------");
			paragraph.add("\n");
			documento.add(paragraph);
			paragraph2.add(datosE.get("nombre.empresa"));
			paragraph2.add("\n");
     		paragraph2.add(datosE.get("cif.empresa"));
			paragraph2.add("\n");
			paragraph2.add(datosE.get("direccion.empresa"));
			paragraph2.add("\n");
			paragraph2.add(datosE.get("poblacion.empresa"));
			paragraph2.add("\n");
			paragraph2.add(datosE.get("cp.empresa") +"-"+datosE.get("provincia.empresa"));
			paragraph2.add("\n");
			documento.add(paragraph2);
			paragraph.clear();
			paragraph2.clear();
			paragraph.add("CLIENTE-----------------------");
			paragraph.add("\n");
			documento.add(paragraph);
			paragraph2.add("Nombre: "+usuario.getNombre());
			paragraph2.add("\n");
			paragraph2.add("DNI: "+usuario.getDni());
			paragraph2.add("\n");
			paragraph2.add("Apellido1: "+usuario.getApellido1());
			paragraph2.add("\n");
			paragraph2.add("Apellido2: "+usuario.getApellido2());
			paragraph2.add("\n");
			paragraph2.add("Direccion: "+usuario.getDireccion());
			paragraph2.add("\n");
			documento.add(paragraph2);
			
			paragraph.clear();
			paragraph2.clear();

			paragraph.add("DATOS DE PEDIDO---------------");
			paragraph.add("\n");
			documento.add(paragraph);
			
			paragraph.clear();
			
			paragraph.setSpacingBefore(5);
			paragraph.add("\n");
			font = "Sans";
			tamanno = 11;
			style = Font.NORMAL;
			color = BaseColor.BLUE;
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setFont(new Font(FontFactory.getFont(font, tamanno, style, color)));
			
			paragraph.add("Fecha: "+pedido.getFecha());
			paragraph.add("\n");
			paragraph.add("Numero de Factura Temporal: "+pedido.getNum_factura());
			paragraph.add("\n");
			paragraph.add("Metodo de Pago: "+pedido.getMetodo_pago());
			paragraph.add("\n");
			paragraph.add("Total :"+pedido.getTotal()+"");
			paragraph.add("\n");
			paragraph.add("Estado: "+pedido.getEstado());
			paragraph.add("\n");

			documento.add(paragraph);
			paragraph.clear();
			
			paragraph.add("\n");
			paragraph.add("- Datos del pedido- ");
			paragraph.add("\n");
			paragraph.add("\n");
			documento.add(paragraph);
			paragraph.clear();
			
			
			//Instanciamos una tabla de X columnas
		    PdfPTable tabla = new PdfPTable(4);
		    
		    //Ancho de cada columna
	        int[] values = new int[]{30,40,20,20};
	        tabla.setWidths(values);
		    tabla.setWidthPercentage(new Float(100));
		    
		    Phrase texto = new Phrase("Nombre");
		    PdfPCell cabecera1 = new PdfPCell(texto);
			cabecera1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera1.setBorderWidth(1);
		    
		    texto = new Phrase("Descripcion");
		    PdfPCell cabecera2 = new PdfPCell(texto);
			cabecera2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera2.setBorderWidth(1);
		    

		    texto = new Phrase("Precio");
		    PdfPCell cabecera3 = new PdfPCell(texto);
			cabecera3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera3.setBorderWidth(1);
		    
		    texto = new Phrase("Cantidad");
		    PdfPCell cabecera4 = new PdfPCell(texto);
			cabecera4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera4.setBorderWidth(1);
			
			tabla.addCell(cabecera1);
			tabla.addCell(cabecera2);
			tabla.addCell(cabecera3);
			tabla.addCell(cabecera4);
			
			//PARRAFOS PARA ESCRIBIR EL ARRAY DE STRINGS CON LOS DATOS DE LA PARTIDA---------------------------
			for (int i = 0; i < carrito.size(); i++) {
		    
			    tabla.addCell(carrito.get(i).getNombre());
			    tabla.addCell(carrito.get(i).getDescripcion());
			    tabla.addCell(carrito.get(i).getPrecio()+"");
			    tabla.addCell(carrito.get(i).getCantidad()+"");
			    tabla.completeRow();

			}
			documento.add(tabla);
			
			documento.close(); // Cerramos el documento
			writer.close(); // Cerramos writer
			try {
				File path = new File(applicationPath+Constantes.FICHERO_PDF);
				Desktop.getDesktop().open(path);
			} catch (IOException ex) {
				//ClaseLog.miLog.debug("Excepcion en Generar pdf");
				System.out.println("Excepcion 1 en Generar pdf");
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			//ClaseLog.miLog.debug("Excepcion en Generar pdf");
			System.out.println("Excepcion 2 en Generar pdf");
			ex.getMessage();
		}
	}

	
}
