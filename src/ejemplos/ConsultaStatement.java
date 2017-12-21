package ejemplos;

import java.sql.*;

/**
 * Ejemplo de uso de JDBC:
 * -Se establece la cadena de conexi�n con la base de datos Libros
 * -Se obtienen todos los libros del autor cuyo codigo=5
 * -Se muestran los datos por pantalla 
 */
public class ConsultaStatement {

   /*public static void main(String[] args) {
      try {
         Conexion.setURL("jdbc:mysql://localhost:3306/Libros?user=usuario1&password=usuario1");
         Statement sentenciaSQL = Connect.getC().createStatement();
          
         String codigo="5";
         
         StringBuffer sb = new StringBuffer();
         
         sb.append("SELECT Autores.nombre, Libros.titulo FROM AutorLibro,Autores,Libros");
         sb.append(" WHERE ( (Autores.CodigoAutor = AutorLibro.CodAutor)");
         sb.append(" AND (Libros.CodigoLibro = AutorLibro.CodLibro)");
         sb.append(" AND (Autores.CodigoAutor = ");
         sb.append(codigo);
         sb.append("))");
         
         System.out.println(sb.toString());
         
         ResultSet res =sentenciaSQL.executeQuery(sb.toString());
         
         
         String nombre;
         String titulo;
         res.beforeFirst();
         
         while (res.next()) {
         	System.out.print("\n\nAUTOR:  ");
         	nombre = res.getString("Nombre").toString();
         	System.out.println(nombre);
         	System.out.print("TITULO:  ");
				titulo = res.getString("Titulo").toString();
         	System.out.println(titulo);
         	System.out.println("-----------------------");
         }
         
			Conexion.desconecta();
         
      } catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
      }
   }*/
}
