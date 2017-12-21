import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Statement;

/**
 * Created by alanv on 21/12/2017.
 */
public class Main {

    public static void main(String[] args) {

        try {
            Connect.setUrl("jdbc:mysql://localhost:3306/adatos?user=usuario1&password=usuario1");
            Statement statement = Connect.getConnetion().createStatement();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String[] values = new String[3];
            //Campo a obtener
            values[0] = "titulo";
            //Tabla
            values[1] = "libros";
            //Sentencia WHERE
            values[2] = "titulo LIKE '%java%'";

            Connect.print(Connect.doSelect(values, statement));

            //3A
            System.out.println("introduce tabla para obtener id");
            String tabla = reader.readLine();
            System.out.println("introduce campo de la tabla");
            String campo = reader.readLine();
            System.out.println(Connect.getNewId(statement, tabla, campo));

            //3B
            System.out.println("Introduce el codigo para insertar/actualizar");
            String sentence = reader.readLine();

            System.out.println("Numbers of rows affected: " + Connect.insertRow(statement, sentence));

            //3C
            Connect.insertRow(statement,
                    "INSERT INTO autores (codigoautor,nombre,direccion,telefono) " +
                            "VALUES ('"+ Connect.getNewId(statement,"autores","codigoautor")+"', 'alan','pelayo','12345')");

            //3D
            Connect.insertRow(statement,
                    "UPDATE libros SET fecha = CURRENT_DATE WHERE codigolibro = 3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
