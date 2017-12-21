import ejemplos.Conexion;

import java.sql.*;

/**
 * Created by alanv on 21/12/2017.
 */

public class Connect {

    private static Connection con;
    private static String url;

    public static void setUrl(String url) {
        Connect.url = url;
    }

    public static Connection getConnetion() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (Exception e) {
                System.out.println("No se ha encontrado el driver JDBC");
            }

            try {
                con = DriverManager.getConnection(url);
            } catch (SQLException sqle) {
                System.out.println(url);
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("SQLState: " + sqle.getSQLState());
                System.out.println("VendorError: " + sqle.getErrorCode());
            }
        }
        return con;
    }

    public static ResultSet doSelect(String[] values, Statement statement){

        try {

            StringBuffer sb = new StringBuffer();
            sb.append("SELECT ");

            if(values[0].equals("")) sb.append("* ");
                else sb.append(values[0]);

            sb.append(" ");

            sb.append("FROM ");

            if(values[1].equals("")) return null;
                else sb.append(values[1]);

            sb.append(" ");

            if(!values[2].equals("")){
                sb.append("WHERE ");
                sb.append(values[2]);
            }
            return statement.executeQuery(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    public static void print(ResultSet res){

        try {
            ResultSetMetaData resMD = res.getMetaData();
            res.beforeFirst();

            System.out.println(resMD.getColumnCount());
            System.out.println(resMD.getColumnName(1));

            System.out.println(resMD.getTableName(1));

            while (res.next()) {
                resMD = res.getMetaData();
                /*System.out.println(resMD.getColumnName(1));
                System.out.print(res.getString(resMD.getColumnName(1)));*/

                for(int i = 1; i <= resMD.getColumnCount(); i++){
                    System.out.print(resMD.getColumnName(i) + ": ");
                    System.out.print(res.getString(resMD.getColumnName(i)));
                    System.out.println(" ");
                }
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNewId(Statement statement,String tabla, String id){

        //Estaba pensado para poder hacer cualquier combinacion de tabla/valores y clausula

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ");
        sb.append(id);
        sb.append(" ");
        sb.append("FROM ");
        sb.append(tabla);
        sb.append(" ");
        sb.append("ORDER BY ");
        sb.append(id);
        sb.append(" ");
        sb.append("DESC LIMIT 1");

        try {
            ResultSet result = statement.executeQuery(sb.toString());
            result.beforeFirst();
            result.next();
            return Integer.parseInt(result.getString(id))+1;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return 0;
    }

    public static int insertRow(Statement statement, String order){

        try {
            PreparedStatement ps = con.prepareStatement(order);

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return 0;
    }

    public static void close() {
        if (con != null) {
            try {
                con.close();
                con=null;
            } catch (SQLException sqle) {
                System.out.println("SQLException: " + sqle.getMessage());
                System.out.println("SQLState: " + sqle.getSQLState());
                System.out.println("VendorError: " + sqle.getErrorCode());

            }
        }
    }
}
